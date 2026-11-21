package pe.edu.cibertec.matricula_api.screenplay.questions;

import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.rest.interactions.Get;

public class LaRespuesta {

    public static Question<Integer> codigoDeEstado(){
        return  Question.about("el codigo de estado")
                .answeredBy( actor -> SerenityRest.lastResponse().statusCode());
    }

    public static Question<String> campo(String ruta){
        return Question.about("el campo "+ ruta)
                .answeredBy(actor -> SerenityRest.lastResponse().jsonPath().get(ruta));
    }

    public static Question<Integer> cantidadDeElementos(){
        return Question.about("la cantidad de elementos")
                .answeredBy(actor -> SerenityRest.lastResponse().jsonPath().getList("$").size());
    }

    public static Question<Integer> campoEntero(String ruta){
        return Question.about("el campo entero " + ruta)
                .answeredBy(actor -> SerenityRest.lastResponse().jsonPath().getInt(ruta));
    }

    public static Question<Double> campoDecimal(String ruta){
        return Question.about("el campo decimal " + ruta)
                .answeredBy(actor -> SerenityRest.lastResponse().jsonPath().getDouble(ruta));
    }

    public static Question<Boolean> campoConValor(String ruta){
        return Question.about("el campo " + ruta + " tiene valor")
                .answeredBy(actor -> {
                    Object valor = SerenityRest.lastResponse().jsonPath().get(ruta);
                    return valor != null && !String.valueOf(valor).isBlank();
                });
    }

}
