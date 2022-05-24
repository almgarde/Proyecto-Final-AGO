package es.iessoterohernandez.ProyectoFinalAGO.Services;

import java.util.List;
import java.util.Map;

import es.iessoterohernandez.ProyectoFinalAGO.Persistence.Entity.Publication;
import es.iessoterohernandez.ProyectoFinalAGO.Services.Dto.PublicationsYearsDto;
import es.iessoterohernandez.ProyectoFinalAGO.Services.Dto.Datatables.PublicationsDatatableDto;
import es.iessoterohernandez.ProyectoFinalAGO.Services.Dto.Form.PublicationsFormDto;

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
	
	/**
	 * Recupera todos las noticias almacenadas en BDD
	 * @return
	 * @throws Exception
	 */
	public List<PublicationsDatatableDto> getAllPublicationsData() throws Exception;
	
	/**
	 * Almacena una noticia en BDD
	 * 
	 * @param addNewsFormDto
	 * @param imageNews
	 */
	public Publication addPublications(PublicationsFormDto publicationsFormDto) throws Exception;
	
	/**
	 * Actualiza una noticia en BDD
	 * 
	 * @param addNewsFormDto
	 * @param imageNews
	 */
	public Publication updatePublications(PublicationsFormDto publicationsFormDto) throws Exception;
	
	public void deletePublications(Map<String, String> publicationData) throws Exception;

}
