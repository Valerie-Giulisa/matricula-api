# language: es
@P2
Característica: Seguridad de la sesión

  Antecedentes:
    Dado que Pedro consulta la API de matricula
    Y Pedro obtiene su token con el codigo "i202610006" y la clave "Cibertec2026"

  Escenario: Cierre de sesión
    Cuando Pedro cierra sesion
    Entonces el codigo de respuesta es 204
    Cuando Pedro consulta su perfil con el token "token"
    Entonces el codigo de respuesta es 401
    Y el codigo del error es "TOKEN_INVALIDO"
    Y la respuesta cumple el esquema "error.json"

  Escenario: Rotación del refresh token
    Cuando Pedro renueva su token
    Entonces el codigo de respuesta es 200
    Cuando Pedro consulta su perfil con el token "accessTokenNuevo"
    Entonces el codigo de respuesta es 200
    Cuando Pedro reutiliza el refreshToken original
    Entonces el codigo de respuesta es 401
    Y el codigo del error es "REFRESH_REUTILIZADO"
    Y la respuesta cumple el esquema "error.json"
    Cuando Pedro consulta su perfil con el token "accessTokenNuevo"
    Entonces el codigo de respuesta es 401
    Y el codigo del error es "TOKEN_INVALIDO"
    Y la respuesta cumple el esquema "error.json"

  Escenario: Alumno sin permiso de administrador
    Cuando Pedro intenta listar todos los alumnos
    Entonces el codigo de respuesta es 403
    Y el codigo del error es "ACCESO_DENEGADO"
    Y la respuesta cumple el esquema "error.json"

  Esquema del escenario: Bloqueo de cuenta por intentos fallidos
    Dado que Jorge consulta la API de matricula
    Cuando Jorge intenta iniciar sesion con el codigo "i202610004" y la clave "<clave>"
    Entonces el codigo de respuesta es <status>
    Y el codigo del error es "<codigo>"
    Y la respuesta cumple el esquema "error.json"

    Ejemplos:
      | clave        | status | codigo                 |
      | ClaveErrada1 | 401    | CREDENCIALES_INVALIDAS |
      | ClaveErrada2 | 401    | CREDENCIALES_INVALIDAS |
      | ClaveErrada3 | 401    | CREDENCIALES_INVALIDAS |
      | ClaveErrada4 | 401    | CREDENCIALES_INVALIDAS |
      | ClaveErrada5 | 423    | CUENTA_BLOQUEADA       |
      | Cibertec2026 | 423    | CUENTA_BLOQUEADA       |
