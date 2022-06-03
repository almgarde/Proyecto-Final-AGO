package es.iessoterohernandez.ProyectoFinalAGO.Services;

import java.util.List;
import java.util.Map;

import es.iessoterohernandez.ProyectoFinalAGO.Persistence.Entity.Link;
import es.iessoterohernandez.ProyectoFinalAGO.Services.Dto.LinksDto;
import es.iessoterohernandez.ProyectoFinalAGO.Services.Dto.Datatables.LinksDatatableDto;

public interface LinksServiceI {

	/**
	 * Recupera todos los datos de los links almacenados en BDD
	 * 
	 * @return List<LinksDatatableDto>
	 * @throws Exception
	 */
	public List<LinksDatatableDto> getAllLinksData() throws Exception;

	/**
	 * Almacena un link en BDD
	 * 
	 * @param linksData
	 * @param imageLinks
	 * @return Link
	 * @throws Exception
	 */
	public Link addLinks(Map<String, String> linksData, String imageLinks) throws Exception;

	/**
	 * Actualiza los datos de un link almacenado en BDD
	 * 
	 * @param linksData
	 * @return Link
	 * @throws Exception
	 */
	public Link updateLinks(Map<String, String> linksData) throws Exception;

	/**
	 * Actualiza la imagen de un link almacenado en BDD
	 * 
	 * @param linksData
	 * @param photoLinks
	 * @return Link
	 * @throws Exception
	 */
	public Link updateImageLinks(Map<String, String> linksData, String photoLinks) throws Exception;

	/**
	 * Elimina un link almacenado en BDD
	 * 
	 * @param linksData
	 * @throws Exception
	 */
	public void deleteLinks(Map<String, String> linksData) throws Exception;

	/**
	 * Recupera los links activos
	 * 
	 * @return List<LinksDto>
	 * @throws Exception
	 */
	public List<LinksDto> getAllLinksActive() throws Exception;

}
