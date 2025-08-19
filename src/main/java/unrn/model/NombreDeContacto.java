package unrn.model;

import jakarta.persistence.Embeddable;
import lombok.*;

@Embeddable
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter(AccessLevel.PRIVATE)
@Setter(AccessLevel.PRIVATE)
@EqualsAndHashCode(of = "nombre")
public class NombreDeContacto {
    static final String ERROR_NOMBRE_LARGO = "El nombre no debe tener más de 35 caracteres";
    static final String ERROR_NOMBRE_CORTO = "El nombre debe tener al menos 2 caracteres";

    private String nombre;

    public NombreDeContacto(String nombre) {
        assertNombreLargo(nombre);
        assertNombreCorto(nombre);
        this.nombre = nombre;
    }

    public String nombre() {
        return nombre;
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
}
