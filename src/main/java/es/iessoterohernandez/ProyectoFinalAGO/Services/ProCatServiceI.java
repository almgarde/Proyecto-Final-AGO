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

	/** Recupera las categorías profesionales activas */
	public List<ProCatDto> getAllProCatActive() throws Exception;
	
	/**
	 * Recupera las categorías profesionales 
	 * 
	 * @return List<ProCatDto>
	 */
	
	public List<ProCatDto> getAllProCat() throws Exception;
	
	/**
	 * Recupera todos las categorías profesionales almacenadas en BDD
	 * 
	 * @return
	 * @throws Exception
	 */
	public List<ProCatDatatableDto> getAllProCatData() throws Exception;
	
	/** 
	 * Almacena una categoría profesional en BDD 
	 * 
	 * @param proCatData
	 * @return
	 * @throws Exception
	 */
	public ProfessionalCategory addProCat(Map<String, String> proCatData) throws Exception;
	
	public ProfessionalCategory updateProCat(Map<String, String> proCatData) throws Exception;


	
	public void deleteProCat(Map<String, String> proCatData) throws Exception;

}
