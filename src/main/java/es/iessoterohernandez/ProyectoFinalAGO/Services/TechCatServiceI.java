package es.iessoterohernandez.ProyectoFinalAGO.Services;

import java.util.List;
import java.util.Map;

import es.iessoterohernandez.ProyectoFinalAGO.Persistence.Entity.TechnicalCategory;
import es.iessoterohernandez.ProyectoFinalAGO.Services.Dto.TechCatDto;
import es.iessoterohernandez.ProyectoFinalAGO.Services.Dto.Datatables.TechCatDatatableDto;

/**
 * Interfaz de Servicios. Entidad: Categorías técnicas
 * 
 * @author agadelao
 *
 */
public interface TechCatServiceI {

	/**
	 * Recupera todos los datos de las categorías técnicas almacenadas en BDD
	 * 
	 * @return List<TechCatDatatableDto>
	 * @throws Exception
	 */
	public List<TechCatDatatableDto> getAllTechCatData() throws Exception;

	/**
	 * Almacena una categoría técnica en BDD
	 * 
	 * @param techCatData
	 * @return TechnicalCategory
	 * @throws Exception
	 */
	public TechnicalCategory addTechCat(Map<String, String> techCatData) throws Exception;

	/**
	 * Actualiza los datos de una categoría técnica almacenada en BDD
	 * 
	 * @param techCatData
	 * @return TechnicalCategory
	 * @throws Exception
	 */
	public TechnicalCategory updateTechCat(Map<String, String> techCatData) throws Exception;

	/**
	 * Elimina una categoría técnica almacenada en BDD
	 * 
	 * @param techCatData
	 * @throws Exception
	 */
	public void deleteTechCat(Map<String, String> techCatData) throws Exception;

	/**
	 * Recupera las categorías técnicas activas
	 * 
	 * @return List<TechCatDto>
	 * @throws Exception
	 */
	public List<TechCatDto> getAllTechCatActive() throws Exception;

	/**
	 * Recupera todas las categorías técnicas
	 * 
	 * @return List<TechCatDto>
	 * @throws Exception
	 */
	public List<TechCatDto> getAllTechCat() throws Exception;

}
