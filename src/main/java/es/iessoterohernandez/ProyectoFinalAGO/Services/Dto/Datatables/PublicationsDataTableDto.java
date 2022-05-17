package es.iessoterohernandez.ProyectoFinalAGO.Services.Dto.Datatables;

import java.util.List;

public class PublicationsDataTableDto {

	private String idPublication;

	/** Título */
	private String titlePublication;

	/** Autores */
	private List<String> authorsPublication;

	/** Revista */
	private String journalPublication;

	/** DOI */
	private String doiPublication;

	/** Año de publicación */
	private String yearPublication;

	public String getIdPublication() {
		return idPublication;
	}

	public void setIdPublication(String idPublication) {
		this.idPublication = idPublication;
	}

	public String getTitlePublication() {
		return titlePublication;
	}

	public void setTitlePublication(String titlePublication) {
		this.titlePublication = titlePublication;
	}

	public List<String> getAuthorsPublication() {
		return authorsPublication;
	}

	public void setAuthorsPublication(List<String> authorsPublication) {
		this.authorsPublication = authorsPublication;
	}

	public String getJournalPublication() {
		return journalPublication;
	}

	public void setJournalPublication(String journalPublication) {
		this.journalPublication = journalPublication;
	}

	public String getDoiPublication() {
		return doiPublication;
	}

	public void setDoiPublication(String doiPublication) {
		this.doiPublication = doiPublication;
	}

	public String getYearPublication() {
		return yearPublication;
	}

	public void setYearPublication(String yearPublication) {
		this.yearPublication = yearPublication;
	}

}
