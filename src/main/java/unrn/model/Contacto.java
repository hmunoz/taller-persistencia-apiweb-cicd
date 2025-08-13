package unrn.model;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter(AccessLevel.PRIVATE)
@Setter(AccessLevel.PRIVATE)
public class Contacto {
    static final String ERROR_NOMBRE_LARGO = "El nombre no debe tener más de 35 caracteres";
    static final String ERROR_NOMBRE_CORTO = "El nombre debe tener al menos 2 caracteres";
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String nombre;
    @OneToMany(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "contacto_id")
    private List<NumeroTelefono> telefonos;

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

