package es.iessoterohernandez.ProyectoFinalAGO.Services.Dto;

import es.iessoterohernandez.ProyectoFinalAGO.Persistence.Entity.ProfessionalCategory;

/**
 * DTO entidad: Miembros
 * 
 * @author agadelao
 *
 */
public class MembersDto {

	private String idMember;

	private String nameMember;

	private String shortNameMember;

	private String emailMember;

	private int phoneMember;

	private String nameProCat;

	private String photoMember;

	private String reseachIdMember;

	private String scopusIdMember;

	private String orcIdMember;

	private String trajectoryMember;

	public String getIdMember() {
		return idMember;
	}

	public void setIdMember(String idMember) {
		this.idMember = idMember;
	}

	public String getNameMember() {
		return nameMember;
	}

	public void setNameMember(String nameMember) {
		this.nameMember = nameMember;
	}

	public String getShortNameMember() {
		return shortNameMember;
	}

	public void setShortNameMember(String shortNameMember) {
		this.shortNameMember = shortNameMember;
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

	public String getNameProCat() {
		return nameProCat;
	}

	public void setNameProCat(String nameProCat) {
		this.nameProCat = nameProCat;
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

	public String getTrajectoryMember() {
		return trajectoryMember;
	}

	public void setTrajectoryMember(String trajectoryMember) {
		this.trajectoryMember = trajectoryMember;
	}

}
