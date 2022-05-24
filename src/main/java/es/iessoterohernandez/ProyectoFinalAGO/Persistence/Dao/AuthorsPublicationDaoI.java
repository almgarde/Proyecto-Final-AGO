package es.iessoterohernandez.ProyectoFinalAGO.Persistence.Dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import es.iessoterohernandez.ProyectoFinalAGO.Persistence.Entity.AuthorsPublication;
import es.iessoterohernandez.ProyectoFinalAGO.Persistence.Entity.Publication;

/**
 * Interfaz de Persistencia. Entidad: Autores - Publicaciones
 * 
 * @author agadelao
 *
 */
@Repository
public interface AuthorsPublicationDaoI extends JpaRepository<AuthorsPublication, Long> {
	
	/**
	 * Recupera los autores por el Id de la publicación
	 * 
	 * @param techCat
	 * @param activo
	 * @return List<Facility>
	 */
	public List<AuthorsPublication> findByIdPublication(Publication p);


}
