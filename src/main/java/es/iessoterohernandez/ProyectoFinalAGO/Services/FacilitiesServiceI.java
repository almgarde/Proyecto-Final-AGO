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
	 * Recupera todos los datos de los equipos de investigación almacenados en BDD
	 * 
	 * @return List<FacilitiesDatatableDto>
	 * @throws Exception
	 */
	public List<FacilitiesDatatableDto> getAllFacilitiesData() throws Exception;

	/**
	 * Almacena un equipo de investigación en BDD
	 * 
	 * @param facilitiesData
	 * @param photoFacilities
	 * @return
	 * @throws Exception
	 */
	public Facility addFacilities(Map<String, String> facilitiesData, String photoFacilities) throws Exception;

	/**
	 * Actualiza los datos de un equipo de investigación almacenado en BDD
	 * 
	 * @param facilitiesData
	 * @return Facility
	 * @throws Exception
	 */
	public Facility updateFacilities(Map<String, String> facilitiesData) throws Exception;

	/**
	 * Actualiza la foto un equipo de investigación almacenado en BDD
	 * 
	 * @param facilitiesData
	 * @param photoFacilities
	 * @return Facility
	 * @throws Exception
	 */
	public Facility updatePhotoFacilities(Map<String, String> facilitiesData, String photoFacilities) throws Exception;

	/**
	 * Elimina un equipo de investigación almacenado en BDD
	 * 
	 * @param facilitiesData
	 * @throws Exception
	 */
	public void deleteFacilities(Map<String, String> facilitiesData) throws Exception;

	/**
	 * Recupera todos los equipos de investigación activos agrupados por categorías
	 * técnicas
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
