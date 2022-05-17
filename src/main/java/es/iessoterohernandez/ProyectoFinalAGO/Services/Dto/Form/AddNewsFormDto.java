package es.iessoterohernandez.ProyectoFinalAGO.Services.Dto.Form;

public class AddNewsFormDto {

	private String titleNews;

	private String abstractNews;

	private String contentNews;

	private String active;

	public String getTitleNews() {
		return titleNews;
	}

	public void setTitleNews(String titleNews) {
		this.titleNews = titleNews;
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

	public String getActive() {
		return active;
	}

	public void setActive(String active) {
		this.active = active;
	}

}
