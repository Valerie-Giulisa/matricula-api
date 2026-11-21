package pe.edu.cibertec.matricula_api.screenplay.task;

import io.restassured.http.ContentType;
import net.serenitybdd.annotations.Step;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.rest.interactions.Patch;

import java.util.Map;

public class CalificarMatricula implements Task {

    private final Integer id;
    private final double nota;

    public CalificarMatricula(Integer id, double nota) {
        this.id = id;
        this.nota = nota;
    }

    public static CalificarMatricula conId(Integer id, double nota) {
        return new CalificarMatricula(id, nota);
    }

    @Override
    @Step("{0} califica la matricula {id} con nota #nota")
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                Patch.to("/api/matriculas/{id}/nota")
                        .with(req -> req.contentType(ContentType.JSON)
                                .header("Authorization", "Bearer " + actor.recall("token"))
                                .pathParam("id", id)
                                .body(Map.of("nota", nota)))
        );
    }
}
