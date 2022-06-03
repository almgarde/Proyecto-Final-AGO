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
	 * Recupera todos los datos de las publicaciones almacenadas en BDD
	 * 
	 * @return List<PublicationsDatatableDto>
	 * @throws Exception
	 */
	public List<PublicationsDatatableDto> getAllPublicationsData() throws Exception;

	/**
	 * Almacena una publicación en BDD
	 * 
	 * @param publicationsFormDto
	 * @return Publication
	 * @throws Exception
	 */
	public Publication addPublications(PublicationsFormDto publicationsFormDto) throws Exception;

	/**
	 * Actualiza los datos de una publicacion almacenada en BDD
	 * 
	 * @param publicationsFormDto
	 * @return Publication
	 * @throws Exception
	 */
	public Publication updatePublications(PublicationsFormDto publicationsFormDto) throws Exception;

	/**
	 * Elimina una publicacion almacenada en BDD
	 * 
	 * @param publicationData
	 * @throws Exception
	 */
	public void deletePublications(Map<String, String> publicationData) throws Exception;

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
