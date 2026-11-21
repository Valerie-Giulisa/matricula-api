package pe.edu.cibertec.matricula_api.screenplay.task;

import net.serenitybdd.annotations.Step;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.rest.interactions.Post;

public class CerrarSesion implements Task {

    @Override
    @Step("{0} cierra sesion")
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                Post.to("/api/auth/logout")
                        .with(req -> req.header("Authorization", "Bearer " + actor.recall("token")))
        );
    }

    public static CerrarSesion ahora() {
        return new CerrarSesion();
    }
}
