package es.iessoterohernandez.ProyectoFinalAGO.Services.Dto;

import java.util.List;

/**
 * DTO entidad: Publications
 * 
 * @author agadelao
 *
 */
public class PublicationsDto {

	private String titlePublication;

	private List<AuthorDto> authorsPublication;

	private int numAuthors;

	private String journalPublication;

	private String doiPublication;

	private Integer yearPublication;

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

	public int getNumAuthors() {
		return numAuthors;
	}

	public void setNumAuthors(int numAuthors) {
		this.numAuthors = numAuthors;
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

	public Integer getYearPublication() {
		return yearPublication;
	}

	public void setYearPublication(Integer yearPublication) {
		this.yearPublication = yearPublication;
	}

}
