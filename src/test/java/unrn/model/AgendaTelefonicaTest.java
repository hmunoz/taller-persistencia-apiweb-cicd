package unrn.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class AgendaTelefonicaTest {

    @Test
    @DisplayName("Agregar un contacto nuevo lo almacena en la agenda")
    void agregarContactoNuevo_contactoSeAgrega() {
        // Setup
//        AgendaTelefonica agenda = new AgendaTelefonica();
//        NumeroTelefono telefono = new NumeroTelefono("2991", "1234567");
//        // Ejercitación
//        agenda.agregarContacto("Juan", telefono);
//        // Verificación
//        List<Contacto> contactos = agenda.listarContactos();
//        assertEquals(1, contactos.size(), "Debe haber un contacto en la agenda");
//        assertTrue(contactos.get(0).esDe("Juan"), "El contacto agregado debe ser 'Juan'");
    }

    @Test
    @DisplayName("Agregar un número a un contacto existente lo agrega correctamente")
    void agregarNumeroAContactoExistente_numeroSeAgrega() {
        // Setup
//        AgendaTelefonica agenda = new AgendaTelefonica();
//        NumeroTelefono telefono1 = new NumeroTelefono("2991", "1234567");
//        NumeroTelefono telefono2 = new NumeroTelefono("2991", "7654321");
//        agenda.agregarContacto("Ana", telefono1);
//        // Ejercitación
//        agenda.agregarContacto("Ana", telefono2);
//        // Verificación
//        List<Contacto> contactos = agenda.listarContactos();
//        assertEquals(1, contactos.size(), "Debe haber un solo contacto");
    }

    @Test
    @DisplayName("Listar contactos en agenda vacía devuelve lista vacía")
    void listarContactos_agendaVacia_listaVacia() {
        // Setup
//        AgendaTelefonica agenda = new AgendaTelefonica();
//        // Ejercitación
//        List<Contacto> contactos = agenda.listarContactos();
//        // Verificación
//        assertTrue(contactos.isEmpty(), "La lista debe estar vacía si no hay contactos");
    }

    @Test
    @DisplayName("La lista de contactos es solo lectura")
    void listarContactos_listaSoloLectura() {
        // Setup
//        AgendaTelefonica agenda = new AgendaTelefonica();
//        NumeroTelefono telefono = new NumeroTelefono("2991", "1234567");
//        agenda.agregarContacto("Pedro", telefono);
//        List<Contacto> contactos = agenda.listarContactos();
//        // Verificación
//        assertThrows(UnsupportedOperationException.class, () -> contactos.add(new Contacto("Otro")), "No se puede modificar la lista de contactos");
    }
}

