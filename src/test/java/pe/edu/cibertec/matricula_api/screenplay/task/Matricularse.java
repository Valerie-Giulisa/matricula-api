package pe.edu.cibertec.matricula_api.screenplay.task;

import io.restassured.http.ContentType;
import net.serenitybdd.annotations.Step;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.rest.interactions.Post;

import java.util.Map;

public class Matricularse implements Task {

    private final String alumno;
    private final String curso;

    public Matricularse(String alumno, String curso) {
        this.alumno = alumno;
        this.curso = curso;
    }

    public static Matricularse en(String alumno, String curso) {
        return new Matricularse(alumno, curso);
    }


    @Override
    @Step("{0} matricula al alumno #alumno en el curso #curso")
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                Post.to("/api/matriculas")
                        .with(req -> req.contentType(ContentType.JSON)
                                .header("Authorization", "Bearer " + actor.recall("token"))
                                .body(
                                        Map.of("codigoAlumno", alumno, "codigoCurso", curso)
                                )
                        )
        );
        if (SerenityRest.lastResponse().statusCode() == 201) {
            actor.remember("idMatricula", SerenityRest.lastResponse().jsonPath().getInt("id"));
        }
    }
}
