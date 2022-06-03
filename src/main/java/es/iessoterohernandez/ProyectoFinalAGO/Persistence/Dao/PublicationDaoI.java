package es.iessoterohernandez.ProyectoFinalAGO.Persistence.Dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import es.iessoterohernandez.ProyectoFinalAGO.Persistence.Entity.Publication;

/**
 * Interfaz de Persistencia. Entidad: Publicaciones
 * 
 * @author agadelao
 *
 */
@Repository
public interface PublicationDaoI extends JpaRepository<Publication, Long>, CustomizedPublicationDao {

	/**
	 * Recupera las publicaciones s/n activas ordenadas por año de publicación -
	 * Descendiente
	 * 
	 * @param activo
	 * @return List<Publication>
	 */
	public List<Publication> findByActiveOrderByYearPublicationDesc(Boolean activo);

	/**
	 * Recupera las publicaciones s/n activas ordenadas por año de publicación -
	 * Ascendente
	 * 
	 * @param activo
	 * @return List<Publication>
	 */
	public List<Publication> findByActiveOrderByYearPublicationAsc(Boolean activo);

	/**
	 * Recupera las publicaciones s/n activas por año de publicación
	 * 
	 * @param year
	 * @param activo
	 * @return List<Publication>
	 */
	public List<Publication> findByYearPublicationAndActive(int year, Boolean activo);

	/**
	 * Recupera una publicación por su Id
	 * 
	 * @param Id
	 * @return Publication
	 */
	public Publication findByIdPublication(Long Id);

}
