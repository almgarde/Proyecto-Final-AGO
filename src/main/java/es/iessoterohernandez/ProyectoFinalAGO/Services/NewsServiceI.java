package es.iessoterohernandez.ProyectoFinalAGO.Services;

import java.util.List;
import java.util.Map;

import es.iessoterohernandez.ProyectoFinalAGO.Persistence.Entity.News;
import es.iessoterohernandez.ProyectoFinalAGO.Persistence.Entity.News;
import es.iessoterohernandez.ProyectoFinalAGO.Services.Dto.NewsDto;
import es.iessoterohernandez.ProyectoFinalAGO.Services.Dto.Datatables.NewsDatatableDto;

/**
 * Interfaz de Servicios. Entidad: Noticias
 * 
 * @author agadelao
 *
 */
public interface NewsServiceI {

	/**
	 * Recupera todos los datos de las noticias almacenadas en BDD
	 * 
	 * @return List<NewsDatatableDto>
	 * @throws Exception
	 */
	public List<NewsDatatableDto> getAllNewsData() throws Exception;

	/**
	 * Almacena una noticia en BDD
	 * 
	 * @param newsData
	 * @param imageNews
	 * @return News
	 * @throws Exception
	 */
	public News addNews(Map<String, String> newsData, String imageNews) throws Exception;

	/**
	 * Actualiza los datos de una noticia almacenada en BDD
	 * 
	 * @param newsData
	 * @return News
	 * @throws Exception
	 */
	public News updateNews(Map<String, String> newsData) throws Exception;

	/**
	 * Actualiza la imagen de una noticia almacenada en BDD
	 * 
	 * @param newsData
	 * @param photoNews
	 * @return News
	 * @throws Exception
	 */
	public News updateImageNews(Map<String, String> newsData, String photoNews) throws Exception;

	/**
	 * Elimina una noticia almacenada en BDD
	 * 
	 * @param newsData
	 * @throws Exception
	 */
	public void deleteNews(Map<String, String> newsData) throws Exception;

	/**
	 * Recupera las seis noticias s/n activas más recientes ordenadas por fecha
	 * descendente
	 * 
	 * @return List<NewsDto>
	 * @throws Exception
	 */
	public List<NewsDto> getSixMostRecentNewsActive() throws Exception;

	/**
	 * Recupera una noticia activa por su Id
	 * 
	 * @param id
	 * @return NewsDto
	 * @throws Exception
	 */
	public NewsDto getNewsByIdActive(Long id) throws Exception;

}
