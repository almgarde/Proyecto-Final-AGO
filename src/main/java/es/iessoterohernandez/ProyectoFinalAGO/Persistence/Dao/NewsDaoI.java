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
	 * Lista de las cuatro noticias más recientes ordenadas por fecha
	 * 
	 * @return List<News>
	 */
	public List<News> findTop4ByActiveOrderByUpdateDateDesc(Boolean activo);
	
	/**
	 * Encuentra una noticia por su Id
	 * 
	 * @param id
	 * @return News
	 */
	public News findByIdNews (Long id);
}
