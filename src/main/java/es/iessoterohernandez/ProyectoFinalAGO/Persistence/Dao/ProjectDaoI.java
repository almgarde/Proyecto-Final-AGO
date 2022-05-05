package es.iessoterohernandez.ProyectoFinalAGO.Persistence.Dao;

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

}
