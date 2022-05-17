package es.iessoterohernandez.ProyectoFinalAGO.Persistence.Dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import es.iessoterohernandez.ProyectoFinalAGO.Persistence.Entity.Facility;
import es.iessoterohernandez.ProyectoFinalAGO.Persistence.Entity.TechnicalCategory;

/**
 * Interfaz de Persistencia. Entidad: Servicios de investigación
 * 
 * @author agadelao
 *
 */
@Repository
public interface FacilityDaoI extends JpaRepository<Facility, Long> {

	/**
	 * Recupera los equipos s/n activos por su categoría técnica
	 * 
	 * @param techCat
	 * @param activo
	 * @return List<Facility>
	 */
	public List<Facility> findByIdTechCatAndActive(TechnicalCategory techCat, Boolean activo);
	
}
