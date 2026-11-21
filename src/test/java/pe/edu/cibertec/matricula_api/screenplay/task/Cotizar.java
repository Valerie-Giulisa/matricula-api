package pe.edu.cibertec.matricula_api.screenplay.task;

import io.restassured.http.ContentType;
import net.serenitybdd.annotations.Step;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.rest.interactions.Post;

import java.util.List;
import java.util.Map;

public class Cotizar implements Task {

    private final String alumno;
    private final String curso;

    public Cotizar(String alumno, String curso) {
        this.alumno = alumno;
        this.curso = curso;
    }

    public static Cotizar elCurso(String alumno, String curso) {
        return new Cotizar(alumno, curso);
    }

    @Override
    @Step("{0} cotiza el curso #curso de #alumno")
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                Post.to("/api/matriculas/cotizacion")
                        .with(req -> req.contentType(ContentType.JSON)
                                .header("Authorization", "Bearer " + actor.recall("token"))
                                .body(Map.of("codigoAlumno", alumno, "cursos", List.of(curso))))
        );
    }
}
