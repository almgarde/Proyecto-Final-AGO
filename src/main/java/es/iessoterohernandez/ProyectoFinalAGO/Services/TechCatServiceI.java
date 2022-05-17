package es.iessoterohernandez.ProyectoFinalAGO.Services;

import java.util.List;

import es.iessoterohernandez.ProyectoFinalAGO.Services.Dto.TechCatDto;

/**
 * Interfaz de Servicios. Entidad: Categorías técnicas
 * 
 * @author agadelao
 *
 */
public interface TechCatServiceI {
	
	/** Recupera las categorías técnicas activas */
	public List<TechCatDto> getAllTechCatActive() throws Exception;

}
