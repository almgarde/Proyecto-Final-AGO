package es.iessoterohernandez.ProyectoFinalAGO.Persistence;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Entidad: Miembros. Tabla: MEMBERS
 * 
 * @author agadelao
 *
 */
@Entity
@Table(name = "MEMBERS")
public class Members extends AbstractEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	/** ID de Miembro */
	private Long idMember;

	/** Nombre */
	private String nameMember;

	/** DNI */
	private String dniMember;

	/** Email */
	private String emailMember;

	/** Teléfono */
	private int phoneMember;

	/** Categoría profesional */
	private String proCatMember;

	/** Foto */
	private String photoMember;

	/** ResearchID */
	private String reseachIdMember;

	/** ScopusID */
	private String scopusIdMember;

	/** ORCID */
	private String orcIdMember;

	/** Nombre corto */
	private String shortNameMember;

	public Long getIdMember() {
		return idMember;
	}

	public void setIdMember(Long idMember) {
		this.idMember = idMember;
	}

	public String getNameMember() {
		return nameMember;
	}

	public void setNameMember(String nameMember) {
		this.nameMember = nameMember;
	}

	public String getDniMember() {
		return dniMember;
	}

	public void setDniMember(String dniMember) {
		this.dniMember = dniMember;
	}

	public String getEmailMember() {
		return emailMember;
	}

	public void setEmailMember(String emailMember) {
		this.emailMember = emailMember;
	}

	public int getPhoneMember() {
		return phoneMember;
	}

	public void setPhoneMember(int phoneMember) {
		this.phoneMember = phoneMember;
	}

	public String getProCatMember() {
		return proCatMember;
	}

	public void setProCatMember(String proCatMember) {
		this.proCatMember = proCatMember;
	}

	public String getPhotoMember() {
		return photoMember;
	}

	public void setPhotoMember(String photoMember) {
		this.photoMember = photoMember;
	}

	public String getReseachIdMember() {
		return reseachIdMember;
	}

	public void setReseachIdMember(String reseachIdMember) {
		this.reseachIdMember = reseachIdMember;
	}

	public String getScopusIdMember() {
		return scopusIdMember;
	}

	public void setScopusIdMember(String scopusIdMember) {
		this.scopusIdMember = scopusIdMember;
	}

	public String getOrcIdMember() {
		return orcIdMember;
	}

	public void setOrcIdMember(String orcIdMember) {
		this.orcIdMember = orcIdMember;
	}

	public String getShortNameMember() {
		return shortNameMember;
	}

	public void setShortNameMember(String shortNameMember) {
		this.shortNameMember = shortNameMember;
	}

}
