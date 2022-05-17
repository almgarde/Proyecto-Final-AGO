package es.iessoterohernandez.ProyectoFinalAGO.Services;

import java.util.List;

import es.iessoterohernandez.ProyectoFinalAGO.Services.Dto.LinksDto;

public interface LinksServiceI {
	
	/** Recupera los links activos
	 * 
	 * @return List<LinksDto>
	 * @throws Exception
	 */
	public List<LinksDto> getAllLinksActive() throws Exception;

}
