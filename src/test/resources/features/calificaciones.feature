# language: es
@P3
Característica: Calificación de matrículas

  Antecedentes:
    Dado que Rosa consulta la API de matricula
    Y Rosa se autentica con el codigo "i202610005" y la clave "Cibertec2026"
    Y Rosa busca la matricula del curso "PI5620" y guarda el id como "idPI"
    Y Rosa busca la matricula del curso "PS5511" y guarda el id como "idPS"

  Esquema del escenario: El docente registra la nota y el historial se actualiza
    Dado que Marco se autentica con el codigo "D0001" y la clave "Cibertec2026"
    Cuando Marco califica la matricula guardada como "idPI" de Rosa con la nota <nota>
    Entonces el codigo de respuesta es 200
    Cuando Rosa consulta el historial de "i202610005"
    Entonces el codigo de respuesta es 200
    Y el alumno del historial es "Rosa Medina"
    Y el historial tiene 3 cursos
    Y la nota de "PI5620" en el historial es <nota>
    Y la condicion de "PI5620" en el historial es "<condicion>"
    Y los creditos aprobados son <creditos>
    Y el promedio ponderado es <promedio>
    Y la respuesta cumple el esquema "historial.json"

    Ejemplos:
      | nota | condicion   | creditos | promedio |
      | 16   | APROBADO    | 13       | 15.69    |
      | 12.5 | DESAPROBADO | 7        | 14.08    |

  Escenario: Un docente que no dicta el curso intenta registrar la nota
    Dado que Lucia se autentica con el codigo "D0002" y la clave "Cibertec2026"
    Cuando Lucia califica la matricula guardada como "idPI" de Rosa con la nota "16"
    Entonces el codigo de respuesta es 403
    Y el codigo de error de calificacion es "ACCESO_DENEGADO"
    Y la respuesta cumple el esquema "error.json"

  Escenario: No se puede calificar una matricula que no está pagada
    Dado que Marco se autentica con el codigo "D0001" y la clave "Cibertec2026"
    Cuando Marco califica la matricula guardada como "idPS" de Rosa con la nota "16"
    Entonces el codigo de respuesta es 409
    Y el codigo de error de calificacion es "MATRICULA_NO_PAGADA"
    Y la respuesta cumple el esquema "error.json"
