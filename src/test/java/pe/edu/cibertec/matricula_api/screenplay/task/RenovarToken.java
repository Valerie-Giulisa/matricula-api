package pe.edu.cibertec.matricula_api.screenplay.task;

import io.restassured.http.ContentType;
import net.serenitybdd.annotations.Step;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.rest.interactions.Post;

import java.util.Map;

public class RenovarToken implements Task {

    private final String refreshToken;

    public RenovarToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public static RenovarToken conElRefreshGuardado() {
        return new RenovarToken(null);
    }

    public static RenovarToken con(String refreshToken) {
        return new RenovarToken(refreshToken);
    }

    @Override
    @Step("{0} renueva el token")
    public <T extends Actor> void performAs(T actor) {
        String token = refreshToken != null ? refreshToken : actor.recall("refreshToken");
        actor.attemptsTo(
                Post.to("/api/auth/refresh")
                        .with(req -> req.contentType(ContentType.JSON)
                                .body(Map.of("refreshToken", token)))
        );
        if (SerenityRest.lastResponse().statusCode() == 200) {
            actor.remember("accessTokenNuevo",
                    SerenityRest.lastResponse().jsonPath().getString("accessToken"));
            actor.remember("refreshTokenNuevo",
                    SerenityRest.lastResponse().jsonPath().getString("refreshToken"));
        }
    }
}
