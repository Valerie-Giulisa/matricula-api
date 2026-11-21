package pe.edu.cibertec.matricula_api.screenplay.task;

import io.restassured.http.ContentType;
import net.serenitybdd.annotations.Step;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.rest.interactions.Patch;

import java.util.Map;

public class Pagar implements Task {

    private final String metodo;

    public Pagar(String metodo) {
        this.metodo = metodo;
    }

    public static Pagar laMatriculaCon(String metodo) {
        return new Pagar(metodo);
    }

    @Override
    @Step("{0} paga la matricula con #metodo")
    public <T extends Actor> void performAs(T actor) {
        Integer id = actor.recall("idMatricula");
        actor.attemptsTo(
                Patch.to("/api/matriculas/{id}/pagar")
                        .with(req -> req.contentType(ContentType.JSON)
                                .header("Authorization", "Bearer " + actor.recall("token"))
                                .pathParam("id", id)
                                .body(Map.of("metodo", metodo)))
        );
    }
}
