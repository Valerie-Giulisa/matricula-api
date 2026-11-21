package pe.edu.cibertec.matricula_api.screenplay.stepdefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pe.edu.cibertec.matricula_api.screenplay.questions.ElCodigoError;
import pe.edu.cibertec.matricula_api.screenplay.questions.ElHistorial;
import pe.edu.cibertec.matricula_api.screenplay.task.BuscarMatricula;
import pe.edu.cibertec.matricula_api.screenplay.task.CalificarMatricula;
import pe.edu.cibertec.matricula_api.screenplay.task.ConsultarHistorial;
import pe.edu.cibertec.matricula_api.screenplay.task.IniciarSesion;

import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static net.serenitybdd.screenplay.actors.OnStage.theActorCalled;
import static net.serenitybdd.screenplay.actors.OnStage.theActorInTheSpotlight;
import static org.hamcrest.Matchers.equalTo;

public class CalificacionStepDefinitions {

    private double numero(String valor) {
        return Double.parseDouble(valor.replace(',', '.'));
    }

    @Given("que {word} se autentica con el codigo {string} y la clave {string}")
    public void queSeAutentica(String nombre, String codigo, String clave) {
        theActorCalled(nombre).attemptsTo(IniciarSesion.con(codigo, clave));
    }

    @Given("{word} se autentica con el codigo {string} y la clave {string}")
    public void seAutentica(String nombre, String codigo, String clave) {
        theActorCalled(nombre).attemptsTo(IniciarSesion.con(codigo, clave));
    }

    @Given("{word} busca la matricula del curso {string} y guarda el id como {string}")
    public void buscaLaMatricula(String nombre, String curso, String claveMemoria) {
        theActorCalled(nombre).attemptsTo(BuscarMatricula.elCurso(curso, claveMemoria));
    }

    @When("{word} califica la matricula guardada como {string} de {word} con la nota {word}")
    public void calificaLaMatricula(String docente, String claveMemoria, String alumno, String nota) {
        Integer id = theActorCalled(alumno).recall(claveMemoria);
        theActorCalled(docente).attemptsTo(CalificarMatricula.conId(id, numero(nota)));
    }

    @When("{word} consulta el historial de {string}")
    public void consultaElHistorial(String nombre, String codigoAlumno) {
        theActorCalled(nombre).attemptsTo(ConsultarHistorial.de(codigoAlumno));
    }

    @Then("el alumno del historial es {string}")
    public void elAlumnoDelHistorialEs(String nombre) {
        theActorInTheSpotlight().should(seeThat(ElHistorial.nombreAlumna(), equalTo(nombre)));
    }

    @Then("el historial tiene {int} cursos")
    public void elHistorialTieneCursos(int cantidad) {
        theActorInTheSpotlight().should(seeThat(ElHistorial.cantidadDeCursos(), equalTo(cantidad)));
    }

    @Then("la nota de {string} en el historial es {word}")
    public void laNotaEnElHistorialEs(String curso, String nota) {
        theActorInTheSpotlight().should(seeThat(ElHistorial.notaDe(curso), equalTo(numero(nota))));
    }

    @Then("la condicion de {string} en el historial es {string}")
    public void laCondicionEnElHistorialEs(String curso, String condicion) {
        theActorInTheSpotlight().should(seeThat(ElHistorial.condicionDe(curso), equalTo(condicion)));
    }

    @Then("los creditos aprobados son {int}")
    public void losCreditosAprobadosSon(int creditos) {
        theActorInTheSpotlight().should(seeThat(ElHistorial.creditosAprobados(), equalTo(creditos)));
    }

    @Then("el promedio ponderado es {word}")
    public void elPromedioPonderadoEs(String promedio) {
        theActorInTheSpotlight().should(seeThat(ElHistorial.promedioPonderado(), equalTo(numero(promedio))));
    }

    @Then("el codigo de error de calificacion es {string}")
    public void elCodigoDeErrorDeCalificacionEs(String codigo) {
        theActorInTheSpotlight().should(seeThat(ElCodigoError.delResponse(), equalTo(codigo)));
    }
}
