# language: es
@P1
Característica: Matrícula del alumno con D=6
  Como alumno de CIBERTEC
  Quiero cotizar, matricularme y pagar mi curso
  Para dejar registrada mi matrícula en el periodo abierto

  Antecedentes:
    Dado que Pedro consulta la API de matricula
    Y Pedro inicia sesion con el codigo "i202610006" y la clave "Cibertec2026"

  Escenario: Cotizar el curso
    Cuando Pedro cotiza el curso "DM5512" del alumno "i202610006"
    Entonces el codigo de respuesta es 200
    Y los creditos son 4
    Y el subtotal es 480.0
    Y el descuento es 0
    Y el total es 480.0
    Y la respuesta cumple el esquema "cotizacion.json"

  Escenario: Matricularse y pagar
    Cuando Pedro se matricula en el curso "DM5512" del alumno "i202610006"
    Entonces el codigo de respuesta es 201
    Y el estado de la matricula es "REGISTRADA"
    Y el monto de la matricula es 480.0
    Y la respuesta cumple el esquema "matricula.json"
    Cuando Pedro paga la matricula con el metodo "TRANSFERENCIA"
    Entonces el codigo de respuesta es 200
    Y el estado de la matricula es "PAGADA"
    Y la fecha de pago tiene valor
    Y la respuesta cumple el esquema "matricula.json"
    Cuando Pedro consulta los pagos
    Entonces el codigo de respuesta es 200
    Y existe el pago de la matricula
    Y el metodo del pago es "TRANSFERENCIA"
    Y el numero de operacion tiene formato OP-000000

  Escenario: Regla de negocio que debe fallar
    Cuando Pedro intenta matricularse en el curso "QA5517" del alumno "i202610006"
    Entonces el codigo de respuesta es 422
    Y el codigo de error es "PRERREQUISITO_PENDIENTE"
    Y la respuesta cumple el esquema "error.json"
