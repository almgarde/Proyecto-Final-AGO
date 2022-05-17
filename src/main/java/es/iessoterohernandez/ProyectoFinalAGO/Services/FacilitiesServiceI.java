package es.iessoterohernandez.ProyectoFinalAGO.Services;

import java.util.List;

import es.iessoterohernandez.ProyectoFinalAGO.Persistence.Entity.TechnicalCategory;
import es.iessoterohernandez.ProyectoFinalAGO.Services.Dto.TechCatFacilitiesDto;

/**
 * Interfaz de Servicios. Entidad: Equipos de investigación
 * 
 * @author agadelao
 *
 */
public interface FacilitiesServiceI {

	/**
	 * Recupera todos los equipos de investigación activos agrupados por categorías técnicas
	 * 
	 * @return List<TechCatFacilitiesDto>
	 * @throws Exception
	 */
	public List<TechCatFacilitiesDto> getAllFacilitiesByTechCatActive() throws Exception;

	/** 
	 * Recupera los equipos de investigación activos de una categoría técnica 
	 * 
	 * @param techCat
	 * @return TechCatFacilitiesDto
	 * @throws Exception
	 */
	public TechCatFacilitiesDto getFacilitiesByTechCatActive(TechnicalCategory techCat) throws Exception;

}
