package es.iessoterohernandez.ProyectoFinalAGO.Services.Dto.Form;

import java.util.List;

import es.iessoterohernandez.ProyectoFinalAGO.Services.Dto.AuthorDto;

public class PublicationsFormDto {

	private String idPublication;

	private String titlePublication;

	private List<AuthorDto> authorsPublication;

	private String journalPublication;

	private String doiPublication;

	private String yearPublication;

	private String active;

	private String admin;

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

	public List<AuthorDto> getAuthorsPublication() {
		return authorsPublication;
	}

	public void setAuthorsPublication(List<AuthorDto> authorsPublication) {
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

	public String getActive() {
		return active;
	}

	public void setActive(String active) {
		this.active = active;
	}

	public String getAdmin() {
		return admin;
	}

	public void setAdmin(String admin) {
		this.admin = admin;
	}

}
