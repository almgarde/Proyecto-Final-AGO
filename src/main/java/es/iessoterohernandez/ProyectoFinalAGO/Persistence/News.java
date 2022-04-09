package es.iessoterohernandez.ProyectoFinalAGO.Persistence;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Entidad: Noticias. Tabla: NEWS
 * 
 * @author agadelao
 *
 */
@Entity
@Table(name = "NEWS")
public class News extends AbstractEntity implements Serializable {

	private static final long serialVersionUID = 1L;
	
	/** ID de Noticias */
	private String idNews;
	
	/** Título de Noticias */
	private String titleNews;
	
	/** Imagen de Noticias */
	private String imageNews;
	
	/** Contenido Noticias */
	private String contentNews;

	public String getIdNews() {
		return idNews;
	}

	public void setIdNews(String idNews) {
		this.idNews = idNews;
	}

	public String getTitleNews() {
		return titleNews;
	}

	public void setTitleNews(String titleNews) {
		this.titleNews = titleNews;
	}

	public String getImageNews() {
		return imageNews;
	}

	public void setImageNews(String imageNews) {
		this.imageNews = imageNews;
	}

	public String getContentNews() {
		return contentNews;
	}

	public void setContentNews(String contentNews) {
		this.contentNews = contentNews;
	}
	
	

}
