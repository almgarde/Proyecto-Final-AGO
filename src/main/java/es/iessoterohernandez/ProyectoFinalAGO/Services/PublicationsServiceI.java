package es.iessoterohernandez.ProyectoFinalAGO.Services;

import java.util.List;

import es.iessoterohernandez.ProyectoFinalAGO.Services.Dto.PublicationsDto;
import es.iessoterohernandez.ProyectoFinalAGO.Services.Dto.PublicationsYearsDto;

/**
 * Interfaz de Servicios. Entidad: Publicaciones
 * 
 * @author agadelao
 *
 */
public interface PublicationsServiceI {

	/**
	 * Recupera todas las publicaciones activas ordenadas por año de publicación y
	 * agrupadas por año
	 * 
	 * @param ascendente
	 * @return List<PublicationsYearsDto>
	 * @throws Exception
	 */
	public List<PublicationsYearsDto> getAllPublicationsActiveOrdered(Boolean ascendente) throws Exception;

}
