package es.iessoterohernandez.ProyectoFinalAGO.Persistence;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Entidad: Publicaciones. Tabla: PUBLICATIONS
 * 
 * @author agadelao
 *
 */
@Entity
@Table(name = "PUBLICATIONS")
public class Publications extends AbstractEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	/** ID de Publication */
	private Long idPublication;

	/** Título */
	private String titlePublication;

	/** Autores */
	private List<String> authorsPublication;

	/** Revista */
	private String journalPublication;

	/** DOI */
	private String doiPublication;

	public Long getIdPublication() {
		return idPublication;
	}

	public void setIdPublication(Long idPublication) {
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

}
