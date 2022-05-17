package es.iessoterohernandez.ProyectoFinalAGO.Services.Dto;

import java.sql.Clob;

/**
 * DTO entidad: Noticias
 * 
 * @author agadelao
 *
 */
public class NewsDto {

	private String idNews;

	private String titleNews;

	private String imageNews;

	private String abstractNews;

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

	public String getAbstractNews() {
		return abstractNews;
	}

	public void setAbstractNews(String abstractNews) {
		this.abstractNews = abstractNews;
	}

	public String getContentNews() {
		return contentNews;
	}

	public void setContentNews(String contentNews) {
		this.contentNews = contentNews;
	}

}
