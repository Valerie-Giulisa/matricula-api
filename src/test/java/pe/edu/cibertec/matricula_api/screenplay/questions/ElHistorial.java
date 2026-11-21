package pe.edu.cibertec.matricula_api.screenplay.questions;

import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Question;

import java.util.Collections;
import java.util.List;
import java.util.Map;

public class ElHistorial {

    @SuppressWarnings("unchecked")
    private static List<Map<String, Object>> cursos() {
        return (List<Map<String, Object>>) (List<?>) SerenityRest.lastResponse().jsonPath().getList("cursos");
    }

    private static Map<String, Object> curso(String codigoCurso) {
        return cursos().stream()
                .filter(c -> codigoCurso.equals(c.get("codigoCurso")))
                .findFirst()
                .orElse(Collections.emptyMap());
    }

    public static Question<String> nombreAlumna() {
        return Question.about("el alumno del historial")
                .answeredBy(actor -> SerenityRest.lastResponse().jsonPath().getString("alumno"));
    }

    public static Question<Integer> cantidadDeCursos() {
        return Question.about("la cantidad de cursos del historial")
                .answeredBy(actor -> cursos().size());
    }

    public static Question<Double> notaDe(String codigoCurso) {
        return Question.about("la nota de " + codigoCurso + " en el historial")
                .answeredBy(actor -> ((Number) curso(codigoCurso).get("nota")).doubleValue());
    }

    public static Question<String> condicionDe(String codigoCurso) {
        return Question.about("la condicion de " + codigoCurso + " en el historial")
                .answeredBy(actor -> String.valueOf(curso(codigoCurso).get("condicion")));
    }

    public static Question<Integer> creditosAprobados() {
        return Question.about("los creditos aprobados del historial")
                .answeredBy(actor -> SerenityRest.lastResponse().jsonPath().getInt("creditosAprobados"));
    }

    public static Question<Double> promedioPonderado() {
        return Question.about("el promedio ponderado del historial")
                .answeredBy(actor -> SerenityRest.lastResponse().jsonPath().getDouble("promedioPonderado"));
    }
}
