package unrn.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class NumeroTelefonoTest {

  @Test
  @DisplayName("Crear número válido instancia correctamente el objeto")
  void crearNumero_valido_instanciaCorrecta() {
    // Setup: Preparar el escenario
    // Ejercitación: Ejecutar la acción a probar
    NumeroTelefono numero = new NumeroTelefono("2991", "1234567");
    // Verificación: Verificar el resultado esperado
    assertNotNull(numero, "El número de teléfono debe instanciarse correctamente");
  }

  @Test
  @DisplayName("Crear número con prefijo nulo lanza excepción")
  void crearNumero_prefijoNulo_lanzaExcepcion() {
    // Setup: Preparar el escenario
    // Ejercitación y Verificación: Ejecutar la acción a probar y verificar el resultado esperado
    var ex = assertThrows(RuntimeException.class, () -> new NumeroTelefono(null, "1234567"));
    assertEquals(
        NumeroTelefono.ERROR_CODIGO_INVALIDO,
        ex.getMessage(),
        "Debe lanzar excepción por prefijo nulo");
  }

  @Test
  @DisplayName("Crear número con número nulo lanza excepción")
  void crearNumero_numeroNulo_lanzaExcepcion() {
    // Setup: Preparar el escenario
    // Ejercitación y Verificación: Ejecutar la acción a probar y verificar el resultado esperado
    var ex = assertThrows(RuntimeException.class, () -> new NumeroTelefono("2991", null));
    assertEquals(
        NumeroTelefono.ERROR_NUMERO_INVALIDO,
        ex.getMessage(),
        "Debe lanzar excepción por número nulo");
  }

  @Test
  @DisplayName("Crear número con número demasiado largo lanza excepción")
  void crearNumero_numeroLargo_lanzaExcepcion() {
    // Setup: Preparar el escenario
    String numeroLargo = "12345678";
    // Ejercitación y Verificación: Ejecutar la acción a probar y verificar el resultado esperado
    var ex = assertThrows(RuntimeException.class, () -> new NumeroTelefono("2991", numeroLargo));
    assertEquals(
        NumeroTelefono.ERROR_NUMERO_LARGO,
        ex.getMessage(),
        "Debe lanzar excepción por número largo");
  }

  @Test
  @DisplayName("Crear número con número demasiado corto lanza excepción")
  void crearNumero_numeroCorto_lanzaExcepcion() {
    // Setup: Preparar el escenario
    String numeroCorto = "12345";
    // Ejercitación y Verificación: Ejecutar la acción a probar y verificar el resultado esperado
    var ex = assertThrows(RuntimeException.class, () -> new NumeroTelefono("2991", numeroCorto));
    assertEquals(
        NumeroTelefono.ERROR_NUMERO_CORTO,
        ex.getMessage(),
        "Debe lanzar excepción por número corto");
  }

  @Test
  @DisplayName("Crear número válido retorna codigo area + numero")
  void crearNumberoValido_retornaCodigoMasNumero() {
    var numero = new NumeroTelefono("2652", "788987");
    assertEquals("2652 788987", numero.numero());
  }
}
