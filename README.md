# Taller - Persistencia, Servicios Web y Testing Automatizado

- Para persistencia usaremos JPA 3.2 y [Hibernate 7](https://docs.jboss.org/hibernate/orm/7.0/introduction/html_single/Hibernate_Introduction.html)

## Diseño

- Bottom-up vs Top-Down: Ninguno de los enfoques es el único ni definitivo; ambos tienen su lugar dependiendo de lo que estés haciendo. En soluciones grandes o empresariales, donde parte del diseño proviene de arquitectos (o ya existe de antemano), uno podría comenzar con el enfoque de estilo Top-Down. Por otro lado, cuando te encontrás en una situación donde no estás seguro de cómo debería ser tu código (o cómo debería encajar con otras partes del sistema), puede ser más fácil empezar con un componente de bajo nivel e ir dejándolo evolucionar a medida que se agregan más pruebas, refactorizaciones y requisitos.

## Testing Unitario

## Persistencia

## Testing Integracion 1
- [Hibernate 7 Docs] (https://docs.jboss.org/hibernate/orm/7.0/introduction/html_single/Hibernate_Introduction.html#testing).
## Servicios Web 

###  SpringBoot
### Testing Integracion 2
- MockMvc and WebTestClient: [Spring Docs](https://docs.spring.io/spring-framework/reference/testing.html).  
  - MockMvc ejecuta el controller y todo el stack en memoria, sin servidor, sin red. Perfecto para tests de integración rápidos y realistas a nivel de capa web. 
  - La otra es WebTestCliente como cliente y levantar un server real con @SpringBootTest(webEnvironment = WebEnvironment.DEFINED_PORT o RANDOM_PORT))