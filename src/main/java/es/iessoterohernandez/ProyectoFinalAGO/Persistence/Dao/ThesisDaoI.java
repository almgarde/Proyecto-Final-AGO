package es.iessoterohernandez.ProyectoFinalAGO.Persistence.Dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import es.iessoterohernandez.ProyectoFinalAGO.Persistence.Entity.Facility;
import es.iessoterohernandez.ProyectoFinalAGO.Persistence.Entity.Link;
import es.iessoterohernandez.ProyectoFinalAGO.Persistence.Entity.Thesis;

/**
 * Interfaz de Persistencia. Entidad: Tesis doctorales
 * 
 * @author agadelao
 *
 */
@Repository
public interface ThesisDaoI extends JpaRepository<Thesis, Long> {
	
	/**
	 * Recupera las tesis por su Id
	 * 
	 * @param techCat
	 * @param activo
	 * @return List<Facility>
	 */
	public Thesis findByIdThesis(Long Id);
	
	/**
	 * Recupera las tesis s/n activos
	 * 
	 * @param activo
	 * @return List<Link>
	 */
	public List<Thesis> findByActive(Boolean activo);

}
