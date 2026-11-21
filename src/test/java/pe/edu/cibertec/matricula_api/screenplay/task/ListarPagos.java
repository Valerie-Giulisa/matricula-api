package pe.edu.cibertec.matricula_api.screenplay.task;

import net.serenitybdd.annotations.Step;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.rest.interactions.Get;

public class ListarPagos implements Task {

    @Override
    @Step("{0} consulta los pagos")
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                Get.resource("/api/pagos")
                        .with(req -> req.header("Authorization", "Bearer " + actor.recall("token")))
        );
    }

    public static ListarPagos todos() {
        return new ListarPagos();
    }
}
