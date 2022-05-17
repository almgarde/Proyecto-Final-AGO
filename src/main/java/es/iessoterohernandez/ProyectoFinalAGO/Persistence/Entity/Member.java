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
 * Entidad: Miembros. Tabla: MEMBERS
 * 
 * @author agadelao
 *
 */
@Entity
@Table(name = "MEMBERS")
public class Member extends AbstractEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	/** ID de Miembro */
	private Long idMember;

	/** Nombre */
	private String nameMember;

	/** Nombre corto */
	private String shortNameMember;

	/** DNI */
	private String dniMember;

	/** Email */
	private String emailMember;

	/** Teléfono */
	private int phoneMember;

	/** Categoría profesional */
	private ProfessionalCategory idProCat;

	/** Foto */
	private String photoMember;

	/** ResearchID */
	private String reseachIdMember;

	/** ScopusID */
	private String scopusIdMember;

	/** ORCID */
	private String orcIdMember;
	
	/** Trayectoria profesional */
	private String trajectoryMember;

	@Id
	@GeneratedValue(generator = "genSeqMembers")
	@SequenceGenerator(name = "genSeqMembers", sequenceName = "SEQ_MEMBERS", allocationSize = 1)
	public Long getIdMember() {
		return idMember;
	}

	public void setIdMember(Long idMember) {
		this.idMember = idMember;
	}

	@Column(name = "NAME_MEMBER", nullable = false, length = 9, unique = true)
	public String getNameMember() {
		return nameMember;
	}

	public void setNameMember(String nameMember) {
		this.nameMember = nameMember;
	}

	@Column(name = "SHORT_NAME_MEMBER", nullable = false, unique = true)
	public String getShortNameMember() {
		return shortNameMember;
	}

	public void setShortNameMember(String shortNameMember) {
		this.shortNameMember = shortNameMember;
	}

	@Column(name = "DNI_MEMBER", nullable = false, length = 9, unique = true)
	public String getDniMember() {
		return dniMember;
	}

	public void setDniMember(String dniMember) {
		this.dniMember = dniMember;
	}

	@Column(name = "EMAIL_MEMBER", nullable = false, unique = true)
	public String getEmailMember() {
		return emailMember;
	}

	public void setEmailMember(String emailMember) {
		this.emailMember = emailMember;
	}

	@Column(name = "PHONE_MEMBER", nullable = true, length = 9)
	public int getPhoneMember() {
		return phoneMember;
	}

	public void setPhoneMember(int phoneMember) {
		this.phoneMember = phoneMember;
	}

	@ManyToOne
	@JoinColumn(name = "ID_PRO_CAT")
	public ProfessionalCategory getIdProCat() {
		return idProCat;
	}

	public void setIdProCat(ProfessionalCategory idProCat) {
		this.idProCat = idProCat;
	}

	@Column(name = "PHOTO_MEMBER", nullable = false)
	public String getPhotoMember() {
		return photoMember;
	}

	public void setPhotoMember(String photoMember) {
		this.photoMember = photoMember;
	}

	@Column(name = "RESEARCH_ID_MEMBER", nullable = true, length = 11, unique = true)
	public String getReseachIdMember() {
		return reseachIdMember;
	}

	public void setReseachIdMember(String reseachIdMember) {
		this.reseachIdMember = reseachIdMember;
	}

	@Column(name = "SCOPUS_ID_MEMBER", nullable = true, length = 11, unique = true)
	public String getScopusIdMember() {
		return scopusIdMember;
	}

	public void setScopusIdMember(String scopusIdMember) {
		this.scopusIdMember = scopusIdMember;
	}

	@Column(name = "ORC_ID_MEMBER", nullable = true, length = 16, unique = true)
	public String getOrcIdMember() {
		return orcIdMember;
	}

	public void setOrcIdMember(String orcIdMember) {
		this.orcIdMember = orcIdMember;
	}
	@Column(name = "TRAJECTORY_MEMBER", nullable = true)
	public String getTrajectoryMember() {
		return trajectoryMember;
	}

	public void setTrajectoryMember(String trajectoryMember) {
		this.trajectoryMember = trajectoryMember;
	}
	
	

}
