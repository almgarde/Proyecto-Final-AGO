package es.iessoterohernandez.ProyectoFinalAGO.Persistence.Dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import es.iessoterohernandez.ProyectoFinalAGO.Persistence.Entity.Facility;
import es.iessoterohernandez.ProyectoFinalAGO.Persistence.Entity.News;
import es.iessoterohernandez.ProyectoFinalAGO.Persistence.Entity.ProfessionalCategory;

/**
 * Interfaz de Persistencia. Entidad: Categorías profesionales
 * 
 * @author agadelao
 *
 */
@Repository
public interface ProfessionalCategoryDaoI extends JpaRepository<ProfessionalCategory, Long> {

	/**
	 * Recupera todas las categorías profesionales s/n activas
	 * 
	 * @param activo
	 * @return List<ProfessionalCategory>
	 */
	public List<ProfessionalCategory> findByActive(Boolean activo);

	/**
	 * Recupera una categoría profesional s/n activa por su Id
	 * 
	 * @param id
	 * @param activo
	 * @return ProfessionalCategory
	 */
	public ProfessionalCategory findByIdProCatAndActive(Long id, Boolean activo);
	
	/**
	 * Recupera una categoría profesional por su Id
	 * 
	 * @param techCat
	 * @param activo
	 * @return List<Facility>
	 */
	public ProfessionalCategory findByIdProCat(Long Id);

}
