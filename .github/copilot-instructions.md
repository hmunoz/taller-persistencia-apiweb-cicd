# Testing Automatizado con JUnit 5.13

## 1. Nombre claro y descriptivo

- Nombra los métodos de test siguiendo este patrón:  
  **`cuestionAEvaluar_resultadoEsperado`**
- Agrega la anotacion `@DisplayName` explicando en lenguaje natural el objetivo del test con este formato:

```java
  @DisplayName("CuestonAEvaluar resultadoEsperado")
  ```

## 2. No uses Mock, Stubs o Fakes

- Los tests unitarios corren todos en memoria ejutando el código real.
- No uses mocks, stubs o fakes, ya que no son necesarios y complican la lectura del test.
- Solo usaremos mocks en consumo de servicios externos (pagos online, emails, etc).

## 3. Estructura del test

- Cada test debe tener la siguiente estructura:
  ```java
  @Test
  @DisplayName("Nombre del test")
  void testNombreDelMetodo() {
      // Setup: Preparar el escenario
      // Ejercitación: Ejecutar la acción a probar
      // Verificación: Verificar el resultado esperado
  }
  ```
- Incorpora comentarios de la estructura del test para facilitar la comprensión del código.

## 4. Un solo caso de prueba por test

- Cada test debe evaluar un único caso de prueba.
- Si necesitas evaluar múltiples casos, crea un test separado para cada uno.

## 5. Usa Asserts claros y descriptivos

- Utiliza aserciones claras y descriptivas agregando mensajes que expliquen el propósito de la verificación.
    - Por ejemplo:
      ```java
      assertEquals(expectedValue, actualValue, "El valor esperado no coincide con el valor actual");
      ```

## 6. Probá Casos límites

- Valores nulos (null)
- Listas vacías o inputs vacíos
- Números negativos o fuera de rango
- Estados inválidos o excepciones esperadas

## 7. Verificá excepciones correctamente

- Utiliza `assertThrows` para verificar que se lanza la excepción esperada.
- En general mi código lanzará RuntimeException, pero verificaló primero.
- Ejemplo:
  ```java
    var ex = assertThrows(RuntimeException.class, () -> {
            // Código que debería lanzar la excepción 
        });
        assertEquals("Mensaje de error esperado", ex.getMessage());
    ```
- Sobre el "Mensaje de error esperado", antes de poner a constante dura en el test verifica que la constante definida en
  el codigo real y si es asi úsala.

## 8. Testing de Integración

- Usamos test-data.sql como set up inicial de la BD.
- Siempre usar como beforeEach el truncate ya que resetea la base de datos despues de cada test que corre:

```java
void beforeEach() {
    emf.getSchemaManager().truncate();
}
```

- No incluyas casos de tests que pueden ser testeados con tests unitarios.