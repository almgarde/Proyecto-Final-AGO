package es.iessoterohernandez.ProyectoFinalAGO.Services.Dto;

/**
 * DTO entidad: Authors - Publications
 * 
 * @author agadelao
 *
 */
public class AuthorDto {

	private String idAuthor;

	private String nameAuthor;

	private String shortNameAuthor;

	private Boolean isMember;

	private String idMember;

	public String getIdAuthor() {
		return idAuthor;
	}

	public void setIdAuthor(String idAuthor) {
		this.idAuthor = idAuthor;
	}

	public String getNameAuthor() {
		return nameAuthor;
	}

	public void setNameAuthor(String nameAuthor) {
		this.nameAuthor = nameAuthor;
	}

	public String getShortNameAuthor() {
		return shortNameAuthor;
	}

	public void setShortNameAuthor(String shortNameAuthor) {
		this.shortNameAuthor = shortNameAuthor;
	}

	public Boolean getIsMember() {
		return isMember;
	}

	public void setIsMember(Boolean isMember) {
		this.isMember = isMember;
	}

	public String getIdMember() {
		return idMember;
	}

	public void setIdMember(String idMember) {
		this.idMember = idMember;
	}

}
