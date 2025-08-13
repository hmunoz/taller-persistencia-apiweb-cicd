package unrn.model;

import jakarta.persistence.EntityManagerFactory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class AgendaTelefonica {
    private final List<Contacto> contactos;
    private final EntityManagerFactory emf;

    public AgendaTelefonica(EntityManagerFactory emf) {
        this.emf = emf;
        contactos = new ArrayList<>();
    }

    public void agregarContacto(String nombre, NumeroTelefono telefono) {
        emf.runInTransaction(em -> {
            var existe = em.createQuery("from Contacto where c.nombre = :nombre", Contacto.class);
            existe.setParameter("nombre", nombre);
            var contacto = Optional.ofNullable(existe.getSingleResultOrNull());
            contacto.ifPresentOrElse(
                    (c) -> {
                        c.nuevoNumero(telefono);
                    }, () -> {
                        Contacto nuevo = new Contacto(nombre);
                        nuevo.nuevoNumero(telefono);
                        contactos.add(nuevo);
                    });
        });
    }

    List<Contacto> listarContactos() {
        return Collections.unmodifiableList(contactos);
    }
}