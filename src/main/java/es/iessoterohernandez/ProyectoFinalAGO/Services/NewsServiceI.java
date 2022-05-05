package es.iessoterohernandez.ProyectoFinalAGO.Services;

import java.util.List;

import es.iessoterohernandez.ProyectoFinalAGO.Services.Dto.NewsDto;

/**
 * Interfaz de Servicios. Entidad: Noticias
 * 
 * @author agadelao
 *
 */
public interface NewsServiceI {
	
	/** Obtiene las 4 noticias más recientes */
	public List<NewsDto> getFourMostRecentNews();
	
	/** Encuentra una noticia por su Id */
	public NewsDto getNewsById(Long id);

}
