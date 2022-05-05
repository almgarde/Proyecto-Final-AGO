package es.iessoterohernandez.ProyectoFinalAGO.Persistence.Dao;

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
public interface PublicationDaoI extends JpaRepository<Publication, Long> {

}
