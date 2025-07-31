# Taller - Persistencia, Servicios Web y Testing Automatizado

- Para persistencia usaremos JPA 3.2 y [Hibernate 7](https://docs.jboss.org/hibernate/orm/7.0/introduction/html_single/Hibernate_Introduction.html)

## Diseño

- Bottom-up vs Top-Down: Ninguno de los enfoques es el único ni definitivo; ambos tienen su lugar dependiendo de lo que estés haciendo. En soluciones grandes o empresariales, donde parte del diseño proviene de arquitectos (o ya existe de antemano), uno podría comenzar con el enfoque de estilo Top-Down. Por otro lado, cuando te encontrás en una situación donde no estás seguro de cómo debería ser tu código (o cómo debería encajar con otras partes del sistema), puede ser más fácil empezar con un componente de bajo nivel e ir dejándolo evolucionar a medida que se agregan más pruebas, refactorizaciones y requisitos.

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
- Teniendo Tests escritos unitario y de integración a nivel servicio, podemos probar la capa web.  
  - Todo lo relacionado a las pocas lineas de código que deberia haber en el controlador. Pero principalmente:
    - Qee lleguen bien los parametros
    - Que retorne el json que esperamos en el formato que esperamos
    - Que retorne errores en el formato que esperamos.
