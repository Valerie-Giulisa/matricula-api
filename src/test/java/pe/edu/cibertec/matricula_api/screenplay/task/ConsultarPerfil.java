package pe.edu.cibertec.matricula_api.screenplay.task;

import net.serenitybdd.annotations.Step;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.rest.interactions.Get;

public class ConsultarPerfil implements Task {

    private final String claveToken;

    public ConsultarPerfil(String claveToken) {
        this.claveToken = claveToken;
    }

    public static ConsultarPerfil con(String claveToken) {
        return new ConsultarPerfil(claveToken);
    }

    @Override
    @Step("{0} consulta su perfil con #claveToken")
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                Get.resource("/api/auth/perfil")
                        .with(req -> req.header("Authorization", "Bearer " + actor.recall(claveToken)))
        );
    }
}
