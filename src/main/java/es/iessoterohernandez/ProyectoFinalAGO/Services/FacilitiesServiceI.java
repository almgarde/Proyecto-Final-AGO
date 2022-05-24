package es.iessoterohernandez.ProyectoFinalAGO.Services;

import java.util.List;
import java.util.Map;

import es.iessoterohernandez.ProyectoFinalAGO.Persistence.Entity.Facility;
import es.iessoterohernandez.ProyectoFinalAGO.Persistence.Entity.TechnicalCategory;
import es.iessoterohernandez.ProyectoFinalAGO.Services.Dto.TechCatFacilitiesDto;
import es.iessoterohernandez.ProyectoFinalAGO.Services.Dto.Datatables.FacilitiesDatatableDto;

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
	
	/**
	 * Recupera todos los equipos almacenadas en BDD
	 * 
	 * @return
	 * @throws Exception
	 */
	public List<FacilitiesDatatableDto> getAllFacilitiesData() throws Exception;
	
	/**
	 * Almacena un equipo en BDD
	 * 
	 * @param membersData
	 * @param imageMembers
	 * @return
	 * @throws Exception
	 */
	public Facility addFacilities (Map<String, String> facilitiesData, String photoFacilities) throws Exception;
	
	public Facility updateFacilities(Map<String, String> facilitiesData) throws Exception;

	public Facility updatePhotoFacilities(Map<String, String> facilitiesData, String photoFacilities) throws Exception;
	
	public void deleteFacilities(Map<String, String> facilitiesData) throws Exception;

}
