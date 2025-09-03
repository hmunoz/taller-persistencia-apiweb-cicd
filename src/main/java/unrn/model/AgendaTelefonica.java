package unrn.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class AgendaTelefonica {
  private final List<Contacto> contactos;

  public AgendaTelefonica() {
    contactos = new ArrayList<>();
  }

  public void agregarContacto(String nombre, NumeroTelefono telefono) {
    var first = contactos.stream().filter(c -> c.esDe(nombre)).findFirst();
    first.ifPresentOrElse(
        (c) -> {
          c.nuevoNumero(telefono);
        },
        () -> {
          Contacto nuevo = new Contacto(nombre);
          nuevo.nuevoNumero(telefono);
          contactos.add(nuevo);
        });
  }

  List<Contacto> listarContactos() {
    return Collections.unmodifiableList(contactos);
  }
}
