# Taller - Persistencia, Servicios Web y Testing Automatizado

- Para persistencia usaremos JPA 3.2 y [Hibernate 7](https://docs.jboss.org/hibernate/orm/7.0/introduction/html_single/Hibernate_Introduction.html)

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

## Requerimientos

- Una persona conoce sus números de teléfono
- Una persona tiene un nombre, un apellido y un DNI.
- El nombre y el apellido no puede debe tener mas de 35 caracteres ni menos de 2.
- El DNI son solo numeros, de 7 u 8 dígitos.
- No deben existir personas con el mismo DNI.
- Un número de telefono tiene un código de área y el número en sí.
- El código de áre tiene 4 dígitos el número un máximo de 7 caracteres y mínimo de 6.

## Testing Unitario

## Persistencia

## Testing Integracion 1

- [Hibernate 7 Docs](https://docs.jboss.org/hibernate/orm/7.0/introduction/html_single/Hibernate_Introduction.html#testing).

## Servicios Web 

En términos de arquitectura de software, un `servicio` es una aplicación o proceso que se encuentra *escuchando* en un determinado host y puerto. Esperando recibir solicitudes de otros programas (clientes).
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

| Acción                 | URI ejemplo                      | Descripción                           |
|------------------------|----------------------------------|---------------------------------------|
| Obtener todos          | `GET /users`                     | Lista de usuarios                      |
| Obtener uno            | `GET /users/{id}`                | Usuario por ID                         |
| Sub-recursos           | `GET /users/{id}/posts`          | Posts del usuario                      |
| Filtro con query params| `GET /products?category=zapatos` | Filtrar productos por categoría        |

---

### 🔸 POST

| Acción                 | URI ejemplo                  | Descripción                           |
|------------------------|------------------------------|----------------------------------------|
| Crear recurso          | `POST /users`                | Crear un nuevo usuario                 |
| Crear sub-recurso      | `POST /users/{id}/telefonos` | Crear un post para ese usuario         |

---

### 🔸 PUT

| Acción                 | URI ejemplo                    | Descripción                           |
|------------------------|---------------------------------|----------------------------------------|
| Reemplazar recurso     | `PUT /users/{id}`              | Reemplaza completamente al usuario     |

---

### 🔸 DELETE

| Acción               | URI ejemplo         | Descripción                 |
|----------------------|---------------------|-----------------------------|
| Eliminar recurso     | `DELETE /users/{id}`| Borra un usuario por ID     |

## Otros Casos

| Caso                    | URI ejemplo                   | Descripción                              |
|-------------------------|-------------------------------|-------------------------------------------|
| Login                   | `POST /auth/login`            | Autenticación                             |
| Logout                  | `POST /auth/logout`           | Cierre de sesión                          |
| Acción puntual          | `POST /orders/{id}/cancel`    | Cancelar una orden                        |

## ✅ Códigos de respuesta recomendados

| Método | Código recomendado          | Cuándo usarlo                          |
|--------|-----------------------------|----------------------------------------|
| GET    | `200 OK`                    | Recurso(s) obtenido(s) correctamente   |
| POST   | `201 Created`               | Recurso creado exitosamente            |
| PUT    | `200 OK` / `204 No Content` | Actualización o creación de recurso    |
| DELETE | `200 OK` / `204 No Content` | Ok o Eliminación exitosa sin contenido |

###  SpringBoot

### Exception Handling Global
- Queremos manejar las excepciones de forma global y para ello el framework Web que usamos en general nos da una forma de hacerlo.
- Usar `@RestControllerAdvice` para anotar una clase que maneje excepciones globalmente.
- Dentro de esa clase, podemos definir métodos que manejen excepciones específicas usando `@ExceptionHandler(Exception.class)`.

### Testing Integracion 2
- MockMvc and WebTestClient: [Spring Docs](https://docs.spring.io/spring-framework/reference/testing.html).
- MockMvc ejecuta el controller y todo el stack en memoria, sin servidor, sin red. Perfecto para tests de integración rápidos y realistas a nivel de capa web.
- La otra es WebTestCliente como cliente y levantar un server real con @SpringBootTest(webEnvironment = WebEnvironment.DEFINED_PORT o RANDOM_PORT))
- Teniendo Tests escritos unitario y de integración a nivel servicio. ¿Qué podemos testear de la capa web?  
  - Todo lo relacionado a las pocas líneas de código que debería haber en el controlador. Pero principalmente:
    - Que lleguen bien los parametros
    - Que retorne el json que esperamos en el formato que esperamos
    - Que retorne errores en el formato que esperamos.
