package es.iessoterohernandez.ProyectoFinalAGO.Persistence.Entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * Entidad: Publicaciones. Tabla: PUBLICATIONS
 * 
 * @author agadelao
 *
 */
@Entity
@Table(name = "PUBLICATIONS")
public class Publication extends AbstractEntity implements Serializable {

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

	@Id
	@GeneratedValue(generator = "genSeqPublications")
	@SequenceGenerator(name = "genSeqPublications", sequenceName = "SEQ_PUBLICATIONS", allocationSize = 1)
	public Long getIdPublication() {
		return idPublication;
	}

	public void setIdPublication(Long idPublication) {
		this.idPublication = idPublication;
	}

	@Column(name = "TITLE_PUBLICATION", nullable = false, unique = true)
	public String getTitlePublication() {
		return titlePublication;
	}

	public void setTitlePublication(String titlePublication) {
		this.titlePublication = titlePublication;
	}

	@ElementCollection
    @CollectionTable(name = "AUTHORS_PUBLICATIONS", joinColumns = @JoinColumn(name = "idPublication"))
	@Column(name = "AUTHOR_PUBLICATION", nullable = false)
	public List<String> getAuthorsPublication() {
		return authorsPublication;
	}

	public void setAuthorsPublication(List<String> authorsPublication) {
		this.authorsPublication = authorsPublication;
	}

	@Column(name = "JOURNAL_PUBLICATION", nullable = false)
	public String getJournalPublication() {
		return journalPublication;
	}

	public void setJournalPublication(String journalPublication) {
		this.journalPublication = journalPublication;
	}

	@Column(name = "DOI_PUBLICATION", nullable = false, length = 255, unique = true)
	public String getDoiPublication() {
		return doiPublication;
	}

	public void setDoiPublication(String doiPublication) {
		this.doiPublication = doiPublication;
	}

}
