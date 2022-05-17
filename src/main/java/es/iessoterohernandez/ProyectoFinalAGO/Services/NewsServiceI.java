package es.iessoterohernandez.ProyectoFinalAGO.Services;

import java.util.List;

import es.iessoterohernandez.ProyectoFinalAGO.Persistence.Entity.News;
import es.iessoterohernandez.ProyectoFinalAGO.Services.Dto.NewsDto;
import es.iessoterohernandez.ProyectoFinalAGO.Services.Dto.Datatables.NewsDataTableDto;
import es.iessoterohernandez.ProyectoFinalAGO.Services.Dto.Form.AddNewsFormDto;

/**
 * Interfaz de Servicios. Entidad: Noticias
 * 
 * @author agadelao
 *
 */
public interface NewsServiceI {

	/** Recupera las 4 noticias activas más recientes 
	 * 
	 * @return List<NewsDto>
	 * @throws Exception
	 */
	public List<NewsDto> getFourMostRecentNewsActive() throws Exception;

	/** Recupera una noticia activa por su Id 
	 * 
	 * @param id
	 * @return NewsDto
	 * @throws Exception
	 */
	public NewsDto getNewsByIdActive(Long id) throws Exception;
	
	/**
	 * Recupera todos las noticias almacenadas en BDD
	 * @return
	 * @throws Exception
	 */
	public List<NewsDataTableDto> getAllNewsData() throws Exception;
	
	/**
	 * Almacena una noticia en BDD
	 * 
	 * @param addNewsFormDto
	 * @param imageNews
	 */
	public News addNews(AddNewsFormDto addNewsFormDto, String imageNews) throws Exception;

}
