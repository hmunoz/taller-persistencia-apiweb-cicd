package unrn.model;

import util.EmfBuilder;

public class Main {
    public static void main(String[] args) {
        var emf = new EmfBuilder().clientAndServer().withDropAndCreateDDL().build();
        emf.runInTransaction(em -> {
            var n1 = new NumeroTelefono("1234", "456633");
            var n2 = new NumeroTelefono("1234", "788944");
            var c = new Contacto("Juan Perez");
            c.nuevoNumero(n1);
            c.nuevoNumero(n2);
            em.persist(c);
        });
    }
}
