package es.iessoterohernandez.ProyectoFinalAGO.Persistence.Dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import es.iessoterohernandez.ProyectoFinalAGO.Persistence.Entity.Project;

/**
 * Interfaz de Persistencia. Entidad: Proyectos
 * 
 * @author agadelao
 *
 */
@Repository
public interface ProjectDaoI extends JpaRepository<Project, Long> {

	/**
	 * Recupera un proyecto por su Id
	 * 
	 * @param Id
	 * @return
	 */
	public Project findByIdProject(Long Id);

	/**
	 * Recupera los proyectos s/n activos
	 * 
	 * @param activo
	 * @return
	 */
	public List<Project> findByActive(Boolean activo);

}
