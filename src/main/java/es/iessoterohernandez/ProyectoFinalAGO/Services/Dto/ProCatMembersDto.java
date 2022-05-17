package es.iessoterohernandez.ProyectoFinalAGO.Services.Dto;

import java.util.List;

/**
 * DTO entidades: Miembros - Categorías profesionales
 * 
 * @author agadelao
 *
 */
public class ProCatMembersDto {

	private String idProCat;

	private String nameProCat;

	private List<MembersDto> membersDto;

	public String getIdProCat() {
		return idProCat;
	}

	public void setIdProCat(String idProCat) {
		this.idProCat = idProCat;
	}

	public String getNameProCat() {
		return nameProCat;
	}

	public void setNameProCat(String nameProCat) {
		this.nameProCat = nameProCat;
	}

	public List<MembersDto> getMembersDto() {
		return membersDto;
	}

	public void setMembersDto(List<MembersDto> membersDto) {
		this.membersDto = membersDto;
	}

}
