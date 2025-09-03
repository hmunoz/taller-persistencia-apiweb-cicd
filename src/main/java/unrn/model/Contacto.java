package unrn.model;

import java.util.ArrayList;
import java.util.List;

public class Contacto {
  static final String ERROR_NOMBRE_LARGO = "El nombre no debe tener más de 35 caracteres";
  static final String ERROR_NOMBRE_CORTO = "El nombre debe tener al menos 2 caracteres";
  private final String nombre;
  private final List<NumeroTelefono> telefonos;

  public Contacto(String nombre) {
    assertNombreLargo(nombre);
    assertNombreCorto(nombre);
    this.nombre = nombre;
    this.telefonos = new ArrayList<>();
  }

  public void nuevoNumero(NumeroTelefono numeroTelefono) {
    this.telefonos.add(numeroTelefono);
  }

  private void assertNombreLargo(String nombre) {
    if (nombre.length() > 35) {
      throw new RuntimeException(ERROR_NOMBRE_LARGO);
    }
  }

  private void assertNombreCorto(String nombre) {
    if (nombre.length() < 2) {
      throw new RuntimeException(ERROR_NOMBRE_CORTO);
    }
  }

  public boolean esDe(String nombre) {
    return this.nombre.equals(nombre);
  }
}
