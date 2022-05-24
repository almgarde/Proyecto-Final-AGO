package es.iessoterohernandez.ProyectoFinalAGO.Persistence.Entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * Entidad: Autores - Publicaciones. Tabla: AUTHORS_PUBLICATIONS
 * 
 * @author agadelao
 *
 */
@Entity
@Table(name = "AUTHORS_PUBLICATIONS")
public class AuthorsPublication implements Serializable {

	private static final long serialVersionUID = 1L;

	/** ID de Autor */
	private Long idAuthor;

	/** Nombre */
	private String nameAuthor;

	/** Nombre corto */
	private String shortNameAuthor;

	private Publication idPublication;

	@Id
	@GeneratedValue(generator = "genSeqAuthorsPublcation")
	@SequenceGenerator(name = "genSeqAuthorsPublcation", sequenceName = "SEQ_AUTHORS_PUBLICATIONS", allocationSize = 1)
	public Long getIdAuthor() {
		return idAuthor;
	}

	public void setIdAuthor(Long idAuthor) {
		this.idAuthor = idAuthor;
	}

	@Column(name = "NAME_AUTHOR", nullable = false)
	public String getNameAuthor() {
		return nameAuthor;
	}

	public void setNameAuthor(String nameAuthor) {
		this.nameAuthor = nameAuthor;
	}

	@Column(name = "SHORT_NAME_AUTHOR", nullable = false, unique = true)
	public String getShortNameAuthor() {
		return shortNameAuthor;
	}

	public void setShortNameAuthor(String shortNameAuthor) {
		this.shortNameAuthor = shortNameAuthor;
	}

	@ManyToOne
	@JoinColumn(name = "ID_PUBLICATION")
	public Publication getIdPublication() {
		return idPublication;
	}

	public void setIdPublication(Publication idPublication) {
		this.idPublication = idPublication;
	}

}
