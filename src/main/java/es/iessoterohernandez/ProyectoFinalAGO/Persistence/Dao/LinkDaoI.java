package es.iessoterohernandez.ProyectoFinalAGO.Persistence.Dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import es.iessoterohernandez.ProyectoFinalAGO.Persistence.Entity.Link;
import es.iessoterohernandez.ProyectoFinalAGO.Persistence.Entity.Member;
import es.iessoterohernandez.ProyectoFinalAGO.Persistence.Entity.ProfessionalCategory;

/**
 * Interfaz de Persistencia. Entidad: Links
 * 
 * @author agadelao
 *
 */
@Repository
public interface LinkDaoI extends JpaRepository<Link, Long> {
	
	/**
	 * Recupera las links s/n activos
	 * 
	 * @param activo
	 * @return List<Link>
	 */
	public List<Link> findByActive(Boolean activo);

}
