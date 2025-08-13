# Taller - Persistencia, Servicios Web y Testing Automatizado

## Diseño Bottom-up vs Top-Down

El diseño Top-Down consiste en comenzar con una visión general del sistema y descomponerla en partes más pequeñas y
específicas. Primero se define la arquitectura, luego se diseñan los componentes individuales.

El diseño Bottom-up consiste en construir primero componentes individuales o de bajo nivel (en Objetos es el Modelo de
Dominio), y luego integrarlos con compontes de más alto nivel formando subsistemas y finalmente el sistema completo.

## TDD Inside-out vs Outside-in

El enfoque **inside-out** comienza escribiendo tests unitarios de bajo nivel sobre las clases del dominio, y
gradualmente se construye hacia fuera, integrando otros componentes del sistema.

- Se empieza por el **núcleo de la lógica de negocio**. En objetos, el modelo de dominio.
- Los tests unitarios guían la implementación de clases individuales.
- Luego se agregan capas externas como servicios, controladores, APIs.
- Conocido como: método clásico, Chicago school.
- **Menos riesgo** de escribir lógica fuera del modelo de dominio.
- Poca necesidad de usar fakes/mocks.

El enfoque **outside-in** comienza escribiendo tests de aceptación o de alto nivel desde el punto de vista del usuario o
cliente. Luego se escriben los tests de colaboración entre objetos (con mocks) para guiar el diseño interno.

- Se parte del **comportamiento visible del sistema**.
- Primero se escriben tests de aceptación o de capa externa (como APIs o UI).
- Se crean objetos simulados (*fakes*) para las dependencias internas aún no implementadas.
- El diseño interno emerge a medida que se satisfacen esos contratos.
- Conocido como: London school, Mockist style
- Aparece en el libro *Growing Object-Oriented Software, Guided by Tests*, de Steve Freeman y Nat Pryce.

## Requerimientos (Contactos Telefónicos)

- Un contacto conoce una lista de números de teléfono
- Un contacto posee un nombre que no debe tener más de 35 caracteres ni menos de 2.
- Un número de teléfono tiene un código de área y el número en sí.
- El código de áre tiene 4 dígitos el número un máximo de 7 caracteres y mínimo de 6.
- Una agenda conoce todos sus contactos y permite listarlos.
- La agenda permite agregar contactos.
- No deben existir contactos con el mismo nombre.

## Testing Unitario

- Usamos JUnit
- Nombre claro y descriptivo de la forma: *cuestionATestear_resultadoEsperado*
- Estructura del test:
    - Setup: Preparar el escenario
    - Ejercitación: Ejecutar la acción a probar
    - Verificación: Comprobar el resultado esperado
- Utiliza mensajes que expliquen el propósito de la verificación.
    - Por ejemplo:
      ```java
      assertEquals(expectedValue, actualValue, "El valor esperado no coincide con el valor actual");
      ```
- Verifica excepciones correctamente
    - Utiliza `assertThrows` para verificar que se lanza la excepción esperada.
    - Ejemplo:
      ```java
        var ex = assertThrows(RuntimeException.class, () -> {
                // Código que debería lanzar la excepción 
            });
            assertEquals("Mensaje de error esperado", ex.getMessage());
        ```

## Persistencia

- Para persistencia usaremos JPA 3.2
  y [Hibernate 7](https://docs.jboss.org/hibernate/orm/7.0/introduction/html_single/Hibernate_Introduction.html)

- Agregamos dependencias en pom.xml
- Definimos cual clase representa el sistema.
    - Será `AgendaTelefonica`
    - No la vamos a mapear como entidad porque manejar los contactos como collecion mapeada uno a muchos, dado que la
      cantidad de contactos puede ser grande sabemos que no performa bien.
    - Esta clase representa la entrada a la lógica de negocios del sistema.
    - Responsabilidades:
        - Sus servicios reciben tipos primitivos, o estructuras de datos simples.
        - Crea instancias del modelo, invoca sus servicios.
        - persiste o remueve si es necesario.
- Agrego Mapeos
    - Entidades con Id`@Entity`, `@Id`
    - Lombok: `@NoArgsConstructor(access = AccessLevel.PROTECTED)`, `@Getter(AccessLevel.PRIVATE)`, `@Setter(
      AccessLevel.PRIVATE)`
    - Y relaciones.

## Testing Integracion Servicios

- [Hibernate 7 Docs](https://docs.jboss.org/hibernate/orm/7.0/introduction/html_single/Hibernate_Introduction.html#testing).
- Solo cambiamos la BD real para usar una en memoria.

## Servicios Web

En términos de arquitectura de software, un `servicio` es una aplicación o proceso que se encuentra *escuchando* en un
determinado host y puerto. Esperando recibir solicitudes de otros programas (clientes).
Un **servicio web** es un tipo especial de servicio que:

- Utiliza protocolos web como HTTP o HTTPS para comunicarse,
- Expone su funcionalidad a través de URLs,

Se llama *web* porque se construye sobre tecnologías propias de la web (como HTTP, URIs y formatos como JSON o XML).

Los servicios web permiten:

- Separar el frontend (cliente) del backend (servidor),
- Reutilizar lógica de negocio o datos en distintas interfaces (por ejemplo, web, móvil, otros sistemas),

### Reglas generales para nombres de URIs API REST

- ✅ Usar **nombres de recursos en plural**
- ✅ Usar **nombres sustantivos, no verbos**
- ✅ Evitar extensiones como `.json`, `.xml` en la URI
- ✅ El **verbo va en el método HTTP**, no en la URI

### 🔸 GET

| Acción                  | URI ejemplo                      | Descripción                     |
|-------------------------|----------------------------------|---------------------------------|
| Obtener todos           | `GET /users`                     | Lista de usuarios               |
| Obtener uno             | `GET /users/{id}`                | Usuario por ID                  |
| Sub-recursos            | `GET /users/{id}/posts`          | Posts del usuario               |
| Filtro con query params | `GET /products?category=zapatos` | Filtrar productos por categoría |

---

### 🔸 POST

| Acción            | URI ejemplo                  | Descripción                    |
|-------------------|------------------------------|--------------------------------|
| Crear recurso     | `POST /users`                | Crear un nuevo usuario         |
| Crear sub-recurso | `POST /users/{id}/telefonos` | Crear un post para ese usuario |

---

### 🔸 PUT

| Acción             | URI ejemplo       | Descripción                        |
|--------------------|-------------------|------------------------------------|
| Reemplazar recurso | `PUT /users/{id}` | Reemplaza completamente al usuario |

---

### 🔸 DELETE

| Acción           | URI ejemplo          | Descripción             |
|------------------|----------------------|-------------------------|
| Eliminar recurso | `DELETE /users/{id}` | Borra un usuario por ID |

## Otros Casos

| Caso           | URI ejemplo                | Descripción        |
|----------------|----------------------------|--------------------|
| Login          | `POST /auth/login`         | Autenticación      |
| Logout         | `POST /auth/logout`        | Cierre de sesión   |
| Acción puntual | `POST /orders/{id}/cancel` | Cancelar una orden |

## ✅ Códigos de respuesta recomendados

| Método | Código recomendado          | Cuándo usarlo                          |
|--------|-----------------------------|----------------------------------------|
| GET    | `200 OK`                    | Recurso(s) obtenido(s) correctamente   |
| POST   | `201 Created`               | Recurso creado exitosamente            |
| PUT    | `200 OK` / `204 No Content` | Actualización o creación de recurso    |
| DELETE | `200 OK` / `204 No Content` | Ok o Eliminación exitosa sin contenido |

### SpringBoot

### Exception Handling Global

- Queremos manejar las excepciones de forma global y para ello el framework Web que usamos en general nos da una forma
  de hacerlo.
- Usar `@RestControllerAdvice` para anotar una clase que maneje excepciones globalmente.
- Dentro de esa clase, podemos definir métodos que manejen excepciones específicas usando
  `@ExceptionHandler(Exception.class)`.

### Testing Integracion Servicios Web

- MockMvc and WebTestClient: [Spring Docs](https://docs.spring.io/spring-framework/reference/testing.html).
- MockMvc ejecuta el controller y todo el stack en memoria, sin servidor, sin red. Perfecto para tests de integración
  rápidos y realistas a nivel de capa web.
- La otra es WebTestCliente como cliente y levantar un server real con @SpringBootTest(webEnvironment =
  WebEnvironment.DEFINED_PORT o RANDOM_PORT))
- Teniendo Tests escritos unitario y de integración a nivel servicio. ¿Qué podemos testear de la capa web?
    - Todo lo relacionado a las pocas líneas de código que debería haber en el controlador. Pero principalmente:
        - Que lleguen bien los parametros
        - Que retorne el json que esperamos en el formato que esperamos
        - Que retorne errores en el formato que esperamos.

## Troubleshooting

- Si tenes este error:
    - jakarta.servlet.ServletException: Request processing failed: java.lang.IllegalArgumentException: Name for argument
      of type [int] not specified, and parameter name information not available via reflection. Ensure that the compiler
      uses the '-parameters' flag.
- En Settings > Build, Execution, Deployment > Compiler > Java Compiler, en Javac Options, agregar:
    - -parameters
- Luego ReBuild Project