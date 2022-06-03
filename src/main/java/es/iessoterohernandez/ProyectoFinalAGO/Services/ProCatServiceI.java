package es.iessoterohernandez.ProyectoFinalAGO.Services;

import java.util.List;
import java.util.Map;

import es.iessoterohernandez.ProyectoFinalAGO.Persistence.Entity.ProfessionalCategory;
import es.iessoterohernandez.ProyectoFinalAGO.Services.Dto.ProCatDto;
import es.iessoterohernandez.ProyectoFinalAGO.Services.Dto.Datatables.ProCatDatatableDto;

/**
 * Interfaz de Servicios. Entidad: Categorías profesionales
 * 
 * @author agadelao
 *
 */
public interface ProCatServiceI {

	/**
	 * Recupera todos los datos de las categorías profesionales almacenadas en BDD
	 * 
	 * @return List<ProCatDatatableDto>
	 * @throws Exception
	 */
	public List<ProCatDatatableDto> getAllProCatData() throws Exception;

	/**
	 * Almacena una categoría profesional en BDD
	 * 
	 * @param proCatData
	 * @return ProfessionalCategory
	 * @throws Exception
	 */
	public ProfessionalCategory addProCat(Map<String, String> proCatData) throws Exception;

	/**
	 * Actualiza los datos de una categoría profesional almacenada en BDD
	 * 
	 * @param proCatData
	 * @return ProfessionalCategory
	 * @throws Exception
	 */
	public ProfessionalCategory updateProCat(Map<String, String> proCatData) throws Exception;

	/**
	 * Elimina una categoría profesional almacenada en BDD
	 * 
	 * @param proCatData
	 * @throws Exception
	 */
	public void deleteProCat(Map<String, String> proCatData) throws Exception;

	/**
	 * Recupera las categorías profesionales activas
	 * 
	 * @return List<ProCatDto>
	 * @throws Exception
	 */
	public List<ProCatDto> getAllProCatActive() throws Exception;

	/**
	 * Recupera todas las categorías profesionales almacenadas en BDD
	 * 
	 * @return List<ProCatDto>
	 * @throws Exception
	 */
	public List<ProCatDto> getAllProCat() throws Exception;

}
