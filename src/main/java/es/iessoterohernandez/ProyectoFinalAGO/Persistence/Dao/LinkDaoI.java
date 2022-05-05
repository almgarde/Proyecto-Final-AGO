package es.iessoterohernandez.ProyectoFinalAGO.Persistence.Dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import es.iessoterohernandez.ProyectoFinalAGO.Persistence.Entity.Link;

/**
 * Interfaz de Persistencia. Entidad: Links
 * 
 * @author agadelao
 *
 */
@Repository
public interface LinkDaoI extends JpaRepository<Link, Long> {

}
