package pe.edu.cibertec.matricula_api.screenplay.task;

import io.restassured.http.ContentType;
import net.serenitybdd.annotations.Step;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.rest.interactions.Post;

import java.util.Map;

public class IntentarIniciarSesion implements Task {

    private final String usuario;
    private final String clave;

    public IntentarIniciarSesion(String usuario, String clave) {
        this.usuario = usuario;
        this.clave = clave;
    }

    public static IntentarIniciarSesion con(String usuario, String clave) {
        return new IntentarIniciarSesion(usuario, clave);
    }

    @Override
    @Step("{0} intenta iniciar sesion como #usuario")
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                Post.to("/api/auth/login")
                        .with(req -> req.contentType(ContentType.JSON)
                                .body(Map.of("username", usuario, "password", clave)))
        );
    }
}
