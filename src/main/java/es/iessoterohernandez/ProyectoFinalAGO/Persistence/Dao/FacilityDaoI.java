package es.iessoterohernandez.ProyectoFinalAGO.Persistence.Dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import es.iessoterohernandez.ProyectoFinalAGO.Persistence.Entity.Facility;

/**
 * Interfaz de Persistencia. Entidad: Servicios de investigación
 * 
 * @author agadelao
 *
 */
@Repository
public interface FacilityDaoI extends JpaRepository<Facility, Long> {

}
