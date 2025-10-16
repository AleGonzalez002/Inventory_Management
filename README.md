Estructura:

LoginController:
-Controla la interfaz de login.
-Valida campos vacíos.
-Muestra errores en un label y utiliza DatabaseStatusChecker para alertas de conexión.
-Llama al LoginService para autenticar usuarios.

LoginService:
-Se encarga de la lógica de negocio.
-Valida que los campos no sean vacíos.
-Llama al LoginDao para verificar usuario y contraseña exactos.

LoginDao:
-Interactúa directamente con la base de datos.
-Ejecuta consultas SQL para validar usuarios.
-Mapea resultados a LoginEntity.

LoginEntity:
-Clase modelo que representa un usuario.
-Contiene atributos como idUsuario, nombre, usuario, contrasena, rol y fechaRegistro.

DatabaseConnection:
-Proporciona la conexión a MySQL utilizando JDBC.

DatabaseStatusChecker:
-Verifica si la base de datos está disponible.
-Muestra alertas gráficas si la conexión falla.

login.fxml:
-Interfaz gráfica de login en JavaFX.
-Contiene campos de usuario, contraseña, botón de login y etiqueta para errores.