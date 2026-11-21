package pe.edu.cibertec.matricula_api.screenplay.task;

import net.serenitybdd.annotations.Step;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.rest.interactions.Get;

import java.util.List;
import java.util.Map;

public class BuscarMatricula implements Task {

    private final String curso;
    private final String claveMemoria;

    public BuscarMatricula(String curso, String claveMemoria) {
        this.curso = curso;
        this.claveMemoria = claveMemoria;
    }

    public static BuscarMatricula elCurso(String curso, String claveMemoria) {
        return new BuscarMatricula(curso, claveMemoria);
    }

    @Override
    @Step("{0} busca la matricula de #curso y guarda el id como #claveMemoria")
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                Get.resource("/api/matriculas")
                        .with(req -> req.queryParam("curso", curso)
                                .header("Authorization", "Bearer " + actor.recall("token")))
        );
        List<Map<String, Object>> matriculas = SerenityRest.lastResponse().jsonPath().getList("$");
        matriculas.stream()
                .filter(m -> curso.equals(m.get("codigoCurso")))
                .findFirst()
                .ifPresent(m -> actor.remember(claveMemoria, ((Number) m.get("id")).intValue()));
    }
}
