package pe.edu.cibertec.matricula_api.screenplay.stepdefinitions;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.actors.OnlineCast;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Hooks {

    private static final String NOMBRE = "Valerie Cavero";
    private static final String CODIGO = "i202504206";

    @Before
    public void beforeScenario(Scenario escenario) {
        OnStage.setTheStage(new OnlineCast());
        System.out.println("============================================================");
        System.out.println("Estudiante: " + NOMBRE);
        System.out.println("Codigo: " + CODIGO);
        System.out.println("Fecha y hora: " +
                LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")));
        System.out.println("Escenario: " + escenario.getName());
        System.out.println("============================================================");
    }

    @After
    public void afterScenario() {
        OnStage.drawTheCurtain();
    }
}
