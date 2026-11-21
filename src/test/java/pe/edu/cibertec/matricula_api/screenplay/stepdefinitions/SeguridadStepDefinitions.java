package pe.edu.cibertec.matricula_api.screenplay.stepdefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pe.edu.cibertec.matricula_api.screenplay.questions.ElCodigoError;
import pe.edu.cibertec.matricula_api.screenplay.task.CerrarSesion;
import pe.edu.cibertec.matricula_api.screenplay.task.ConsultarPerfil;
import pe.edu.cibertec.matricula_api.screenplay.task.IniciarSesion;
import pe.edu.cibertec.matricula_api.screenplay.task.IntentarIniciarSesion;
import pe.edu.cibertec.matricula_api.screenplay.task.ListarAlumnos;
import pe.edu.cibertec.matricula_api.screenplay.task.RenovarToken;

import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static net.serenitybdd.screenplay.actors.OnStage.theActorCalled;
import static net.serenitybdd.screenplay.actors.OnStage.theActorInTheSpotlight;
import static org.hamcrest.Matchers.equalTo;

public class SeguridadStepDefinitions {

    @Given("{word} obtiene su token con el codigo {string} y la clave {string}")
    public void obtieneSuToken(String nombre, String codigo, String clave) {
        theActorCalled(nombre).attemptsTo(IniciarSesion.con(codigo, clave));
    }

    @When("{word} renueva su token")
    public void renuevaSuToken(String nombre) {
        theActorCalled(nombre).attemptsTo(RenovarToken.conElRefreshGuardado());
    }

    @When("{word} reutiliza el refreshToken original")
    public void reutilizaElRefreshTokenOriginal(String nombre) {
        theActorCalled(nombre).attemptsTo(RenovarToken.con(theActorCalled(nombre).recall("refreshToken")));
    }

    @When("{word} cierra sesion")
    public void cierraSesion(String nombre) {
        theActorCalled(nombre).attemptsTo(CerrarSesion.ahora());
    }

    @When("{word} consulta su perfil con el token {string}")
    public void consultaSuPerfil(String nombre, String claveToken) {
        theActorCalled(nombre).attemptsTo(ConsultarPerfil.con(claveToken));
    }

    @When("{word} intenta listar todos los alumnos")
    public void intentaListarTodosLosAlumnos(String nombre) {
        theActorCalled(nombre).attemptsTo(ListarAlumnos.todos());
    }

    @When("{word} intenta iniciar sesion con el codigo {string} y la clave {string}")
    public void intentaIniciarSesion(String nombre, String codigo, String clave) {
        theActorCalled(nombre).attemptsTo(IntentarIniciarSesion.con(codigo, clave));
    }

    @Then("el codigo del error es {string}")
    public void elCodigoDelErrorEs(String codigo) {
        theActorInTheSpotlight().should(seeThat(ElCodigoError.delResponse(), equalTo(codigo)));
    }
}
