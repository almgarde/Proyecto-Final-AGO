package es.iessoterohernandez.ProyectoFinalAGO.Persistence.Dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import es.iessoterohernandez.ProyectoFinalAGO.Persistence.Entity.Facility;
import es.iessoterohernandez.ProyectoFinalAGO.Persistence.Entity.TechnicalCategory;

/**
 * Interfaz de Persistencia. Entidad: Categorías técnicas
 * 
 * @author agadelao
 *
 */
@Repository
public interface TechnicalCategoryDaoI extends JpaRepository<TechnicalCategory, Long> {

	/**
	 * Recupera todas las categorías técnicas s/n activas
	 * 
	 * @param activo
	 * @return List<TechnicalCategory>
	 */
	public List<TechnicalCategory> findByActive(Boolean activo);

	/**
	 * Recupera una categoría técnica s/n activa por su Id
	 * 
	 * @param id
	 * @param activo
	 * @return TechnicalCategory
	 */
	public TechnicalCategory findByIdTechCatAndActive(Long id, Boolean activo);
	
	/**
	 * Recupera una categoría técnica por su Id
	 * 
	 * @param techCat
	 * @param activo
	 * @return List<Facility>
	 */
	public TechnicalCategory findByIdTechCat(Long Id);

}
