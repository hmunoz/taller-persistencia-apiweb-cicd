package unrn.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ContactoTest {

  @Test
  @DisplayName("Crear contacto con nombre válido lo instancia correctamente")
  void crearContacto_nombreValido_instanciaCorrecta() {
    // Setup & Ejercitación
    Contacto contacto = new Contacto("Juan");
    // Verificación
    assertTrue(contacto.esDe("Juan"), "El contacto debe tener el nombre 'Juan'");
  }

  @Test
  @DisplayName("Crear contacto con nombre mayor a 35 caracteres lanza excepción")
  void crearContacto_nombreMuyLargo_lanzaExcepcion() {
    // Setup
    String nombreLargo = "a".repeat(36);
    // Ejercitación & Verificación
    var ex = assertThrows(RuntimeException.class, () -> new Contacto(nombreLargo));
    assertEquals(
        Contacto.ERROR_NOMBRE_LARGO, ex.getMessage(), "Debe lanzar excepción por nombre largo");
  }

  @Test
  @DisplayName("Crear contacto con nombre menor a 2 caracteres lanza excepción")
  void crearContacto_nombreMuyCorto_lanzaExcepcion() {
    // Setup
    String nombreCorto = "A";
    // Ejercitación & Verificación
    var ex = assertThrows(RuntimeException.class, () -> new Contacto(nombreCorto));
    assertEquals(
        Contacto.ERROR_NOMBRE_CORTO, ex.getMessage(), "Debe lanzar excepción por nombre corto");
  }

  @Test
  @DisplayName("Agregar un nuevo número a un contacto lo almacena correctamente")
  void nuevoNumero_agregaNumero() {
    // Setup
    Contacto contacto = new Contacto("Ana");
    NumeroTelefono telefono = new NumeroTelefono("2991", "1234567");
    // Ejercitación
    contacto.nuevoNumero(telefono);
    // Verificación
    assertTrue(contacto.esDe("Ana"), "El contacto debe seguir siendo 'Ana'");
  }

  @Test
  @DisplayName("El método es devuelve true si el nombre coincide")
  void es_De_nombreCoincide_true() {
    // Setup
    Contacto contacto = new Contacto("Pedro");
    // Ejercitación & Verificación
    assertTrue(contacto.esDe("Pedro"), "Debe devolver true si el nombre coincide");
  }

  @Test
  @DisplayName("El método es devuelve false si el nombre no coincide")
  void es_De_nombreNoCoincide_false() {
    // Setup
    Contacto contacto = new Contacto("Pedro");
    // Ejercitación & Verificación
    assertFalse(contacto.esDe("Juan"), "Debe devolver false si el nombre no coincide");
  }
}
