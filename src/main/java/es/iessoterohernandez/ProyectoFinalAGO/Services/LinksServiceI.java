package es.iessoterohernandez.ProyectoFinalAGO.Services;

import java.util.List;
import java.util.Map;

import es.iessoterohernandez.ProyectoFinalAGO.Persistence.Entity.Link;
import es.iessoterohernandez.ProyectoFinalAGO.Persistence.Entity.Link;
import es.iessoterohernandez.ProyectoFinalAGO.Services.Dto.LinksDto;
import es.iessoterohernandez.ProyectoFinalAGO.Services.Dto.Datatables.LinksDatatableDto;

public interface LinksServiceI {
	
	/** Recupera los links activos
	 * 
	 * @return List<LinksDto>
	 * @throws Exception
	 */
	public List<LinksDto> getAllLinksActive() throws Exception;
	
	/**
	 * Recupera todos los links almacenadas en BDD
	 * @return
	 * @throws Exception
	 */
	public List<LinksDatatableDto> getAllLinksData() throws Exception;
	
	/**
	 * Almacena un link en BDD
	 * 
	 * @param addNewsFormDto
	 * @param imageNews
	 */
	public Link addLinks(Map<String, String> linksData, String imageLinks) throws Exception;
	
	public Link updateLinks(Map<String, String> linksData) throws Exception;

	public Link updateImageLinks(Map<String, String> linksData, String photoLinks) throws Exception;
	
	public void deleteLinks(Map<String, String> linksData) throws Exception;

}
