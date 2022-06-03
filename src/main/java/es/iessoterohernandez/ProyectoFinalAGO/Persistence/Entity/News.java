package es.iessoterohernandez.ProyectoFinalAGO.Persistence.Entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
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

	/** ID de Noticia */
	private Long idNews;

	/** Título */
	private String titleNews;

	/** Imagen */
	private String imageNews;

	/** Abstract */
	private String abstractNews;

	/** Contenido */
	private String contentNews;

	@Id
	@GeneratedValue(generator = "genSeqNews")
	@SequenceGenerator(name = "genSeqNews", sequenceName = "SEQ_NEWS", allocationSize = 1)
	public Long getIdNews() {
		return idNews;
	}

	public void setIdNews(Long idNews) {
		this.idNews = idNews;
	}

	@Column(name = "TITLE_NEWS", nullable = false, unique = true)
	public String getTitleNews() {
		return titleNews;
	}

	public void setTitleNews(String titleNews) {
		this.titleNews = titleNews;
	}

	@Column(name = "IMAGE_NEWS", nullable = false)
	public String getImageNews() {
		return imageNews;
	}

	public void setImageNews(String imageNews) {
		this.imageNews = imageNews;
	}

	@Column(name = "ABSTRACT_NEWS", nullable = true)
	public String getAbstractNews() {
		return abstractNews;
	}

	public void setAbstractNews(String abstractNews) {
		this.abstractNews = abstractNews;
	}

	@Column(name = "CONTENT_NEWS", nullable = true)
	public String getContentNews() {
		return contentNews;
	}

	public void setContentNews(String contentNews) {
		this.contentNews = contentNews;
	}

}
