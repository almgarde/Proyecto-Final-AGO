package es.iessoterohernandez.ProyectoFinalAGO.Services;

import java.util.List;

import es.iessoterohernandez.ProyectoFinalAGO.Services.Dto.ProCatDto;

/**
 * Interfaz de Servicios. Entidad: Categorías profesionales
 * 
 * @author agadelao
 *
 */
public interface ProCatServiceI {

	/** Recupera las categorías profesionales activas */
	public List<ProCatDto> getAllProCatActive() throws Exception;

}
