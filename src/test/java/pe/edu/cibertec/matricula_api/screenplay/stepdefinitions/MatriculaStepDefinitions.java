package pe.edu.cibertec.matricula_api.screenplay.stepdefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pe.edu.cibertec.matricula_api.screenplay.questions.ElPago;
import pe.edu.cibertec.matricula_api.screenplay.questions.LaRespuesta;
import pe.edu.cibertec.matricula_api.screenplay.task.Cotizar;
import pe.edu.cibertec.matricula_api.screenplay.task.IniciarSesion;
import pe.edu.cibertec.matricula_api.screenplay.task.ListarPagos;
import pe.edu.cibertec.matricula_api.screenplay.task.Matricularse;
import pe.edu.cibertec.matricula_api.screenplay.task.Pagar;

import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static net.serenitybdd.screenplay.actors.OnStage.theActorInTheSpotlight;
import static org.hamcrest.Matchers.equalTo;

public class MatriculaStepDefinitions {

    @Given("{word} inicia sesion con el codigo {string} y la clave {string}")
    public void iniciaSesion(String nombre, String codigo, String clave) {
        theActorInTheSpotlight().attemptsTo(IniciarSesion.con(codigo, clave));
    }

    @When("{word} cotiza el curso {string} del alumno {string}")
    public void cotizaElCurso(String nombre, String curso, String alumno) {
        theActorInTheSpotlight().attemptsTo(Cotizar.elCurso(alumno, curso));
    }

    @Then("los creditos son {int}")
    public void losCreditosSon(int creditos) {
        theActorInTheSpotlight().should(seeThat(LaRespuesta.campoEntero("creditos"), equalTo(creditos)));
    }

    @Then("el subtotal es {word}")
    public void elSubtotalEs(String subtotal) {
        theActorInTheSpotlight().should(seeThat(LaRespuesta.campoDecimal("subtotal"),
                equalTo(Double.parseDouble(subtotal.replace(',', '.')))));
    }

    @Then("el descuento es {int}")
    public void elDescuentoEs(int descuento) {
        theActorInTheSpotlight().should(seeThat(LaRespuesta.campoEntero("descuento"), equalTo(descuento)));
    }

    @Then("el total es {word}")
    public void elTotalEs(String total) {
        theActorInTheSpotlight().should(seeThat(LaRespuesta.campoDecimal("total"),
                equalTo(Double.parseDouble(total.replace(',', '.')))));
    }

    @When("{word} se matricula en el curso {string} del alumno {string}")
    public void seMatricula(String nombre, String curso, String alumno) {
        theActorInTheSpotlight().attemptsTo(Matricularse.en(alumno, curso));
    }

    @When("{word} intenta matricularse en el curso {string} del alumno {string}")
    public void intentaMatricularse(String nombre, String curso, String alumno) {
        theActorInTheSpotlight().attemptsTo(Matricularse.en(alumno, curso));
    }

    @Then("el estado de la matricula es {string}")
    public void elEstadoDeLaMatriculaEs(String estado) {
        theActorInTheSpotlight().should(seeThat(LaRespuesta.campo("estado"), equalTo(estado)));
    }

    @Then("el monto de la matricula es {word}")
    public void elMontoDeLaMatriculaEs(String monto) {
        theActorInTheSpotlight().should(seeThat(LaRespuesta.campoDecimal("monto"),
                equalTo(Double.parseDouble(monto.replace(',', '.')))));
    }

    @When("{word} paga la matricula con el metodo {string}")
    public void pagaLaMatricula(String nombre, String metodo) {
        theActorInTheSpotlight().attemptsTo(Pagar.laMatriculaCon(metodo));
    }

    @Then("la fecha de pago tiene valor")
    public void laFechaDePagoTieneValor() {
        theActorInTheSpotlight().should(seeThat(LaRespuesta.campoConValor("fechaPago"), equalTo(true)));
    }

    @When("{word} consulta los pagos")
    public void consultaLosPagos(String nombre) {
        theActorInTheSpotlight().attemptsTo(ListarPagos.todos());
    }

    @Then("existe el pago de la matricula")
    public void existeElPagoDeLaMatricula() {
        theActorInTheSpotlight().should(seeThat(ElPago.existeParaLaMatricula(), equalTo(true)));
    }

    @Then("el metodo del pago es {string}")
    public void elMetodoDelPagoEs(String metodo) {
        theActorInTheSpotlight().should(seeThat(ElPago.metodo(), equalTo(metodo)));
    }

    @Then("el numero de operacion tiene formato OP-000000")
    public void elNumeroDeOperacionTieneFormato() {
        theActorInTheSpotlight().should(seeThat(ElPago.numeroOperacionValido(), equalTo(true)));
    }
}
