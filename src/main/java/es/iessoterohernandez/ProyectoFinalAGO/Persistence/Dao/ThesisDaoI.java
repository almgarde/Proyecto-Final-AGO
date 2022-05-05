package es.iessoterohernandez.ProyectoFinalAGO.Persistence.Dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import es.iessoterohernandez.ProyectoFinalAGO.Persistence.Entity.Thesis;

/**
 * Interfaz de Persistencia. Entidad: Tesis doctorales
 * 
 * @author agadelao
 *
 */
@Repository
public interface ThesisDaoI extends JpaRepository<Thesis, Long> {

}
