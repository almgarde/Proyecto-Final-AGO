package es.iessoterohernandez.ProyectoFinalAGO.Services;

import java.util.List;
import java.util.Map;

import es.iessoterohernandez.ProyectoFinalAGO.Persistence.Entity.TechnicalCategory;
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
	
	/** Recupera las categorías técnicas activas */
	public List<TechCatDto> getAllTechCatActive() throws Exception;
	
	/**
	 * Recupera las categorías técnicas 
	 * 
	 * @return List<ProCatDto>
	 */
	public List<TechCatDto> getAllTechCat() throws Exception;
	
	/**
	 * Recupera todos las categorías técnicas almacenadas en BDD
	 * 
	 * @return
	 * @throws Exception
	 */
	public List<TechCatDatatableDto> getAllTechCatData() throws Exception;
	
	/**
	 * Almacena una categoría técnica en BDD
	 * 
	 * @param techCatData
	 * @return
	 * @throws Exception
	 */
	public TechnicalCategory addTechCat(Map<String, String> techCatData) throws Exception;
	
	public TechnicalCategory updateTechCat(Map<String, String> techCatData) throws Exception;

	
	public void deleteTechCat(Map<String, String> techCatData) throws Exception;

}
