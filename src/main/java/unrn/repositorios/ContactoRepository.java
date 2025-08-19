package unrn.repositorios;

import jakarta.persistence.EntityManager;
import unrn.model.Contacto;

import java.util.List;
import java.util.Optional;

public interface ContactoRepository {
    static ContactoRepository repositoryOf(EntityManager em, int pageSize) {
        return new JpaContactoRepository(em, pageSize);
    }

    Optional<Contacto> buscarPorNombre(String nombre);

    void agregar(Contacto contacto);

    List<Contacto> listar(int pageNumber);
}
