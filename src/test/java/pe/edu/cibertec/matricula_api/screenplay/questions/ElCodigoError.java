package pe.edu.cibertec.matricula_api.screenplay.questions;

import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Question;

public class ElCodigoError {

    public static Question<String> delResponse() {
        return Question.about("el codigo del error")
                .answeredBy(actor -> SerenityRest.lastResponse().jsonPath().getString("codigo"));
    }
}
