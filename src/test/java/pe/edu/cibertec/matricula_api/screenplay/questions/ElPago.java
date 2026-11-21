package pe.edu.cibertec.matricula_api.screenplay.questions;

import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;

import java.util.List;
import java.util.Map;

public class ElPago {

    private static Map<String, Object> pagoDeLaMatricula(Actor actor) {
        Integer id = actor.recall("idMatricula");
        List<Map<String, Object>> pagos = SerenityRest.lastResponse().jsonPath().getList("$");
        return pagos.stream()
                .filter(p -> p.get("idMatricula") instanceof Number
                        && ((Number) p.get("idMatricula")).intValue() == id)
                .findFirst()
                .orElse(null);
    }

    public static Question<Boolean> existeParaLaMatricula() {
        return Question.about("el pago de la matricula")
                .answeredBy(actor -> pagoDeLaMatricula(actor) != null);
    }

    public static Question<String> metodo() {
        return Question.about("el metodo del pago")
                .answeredBy(actor -> String.valueOf(pagoDeLaMatricula(actor).get("metodo")));
    }

    public static Question<String> numeroOperacion() {
        return Question.about("el numero de operacion del pago")
                .answeredBy(actor -> String.valueOf(pagoDeLaMatricula(actor).get("numeroOperacion")));
    }

    public static Question<Boolean> numeroOperacionValido() {
        return Question.about("el formato del numero de operacion")
                .answeredBy(actor -> {
                    String numero = String.valueOf(pagoDeLaMatricula(actor).get("numeroOperacion"));
                    return numero.matches("OP-\\d{6}");
                });
    }
}
