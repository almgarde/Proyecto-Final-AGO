package es.iessoterohernandez.ProyectoFinalAGO.Persistence.Dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import es.iessoterohernandez.ProyectoFinalAGO.Persistence.Entity.News;

/**
 * Interfaz de Persistencia. Entidad: Noticias
 * 
 * @author agadelao
 *
 */
@Repository
public interface NewsDaoI extends JpaRepository<News, Long> {

	/**
	 * Recupera las seis noticias s/n activas más recientes ordenadas por fecha
	 * descendente
	 * 
	 * @param activo
	 * @return List<News>
	 */
	public List<News> findTop6ByActiveOrderByUpdateDateDesc(Boolean activo);

	/**
	 * Recupera una noticia s/n activa por su Id
	 * 
	 * @param id
	 * @param activo
	 * @return News
	 */
	public News findByIdNewsAndActive(Long id, Boolean activo);

	/**
	 * Recupera las noticias por su Id
	 * 
	 * @param Id
	 * @return News
	 */
	public News findByIdNews(Long Id);
}
