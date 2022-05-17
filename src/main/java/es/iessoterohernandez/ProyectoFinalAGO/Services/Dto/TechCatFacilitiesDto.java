package es.iessoterohernandez.ProyectoFinalAGO.Services.Dto;

import java.util.List;

/**
 * DTO entidades: Equipos de investigación - Categorías técnicas
 * 
 * @author agadelao
 *
 */
public class TechCatFacilitiesDto {

	private String idTechCat;

	private String nameTechCat;

	private List<FacilitiesDto> facilitiesDto;

	public String getIdTechCat() {
		return idTechCat;
	}

	public void setIdTechCat(String idTechCat) {
		this.idTechCat = idTechCat;
	}

	public String getNameTechCat() {
		return nameTechCat;
	}

	public void setNameTechCat(String nameTechCat) {
		this.nameTechCat = nameTechCat;
	}

	public List<FacilitiesDto> getFacilitiesDto() {
		return facilitiesDto;
	}

	public void setFacilitiesDto(List<FacilitiesDto> facilitiesDto) {
		this.facilitiesDto = facilitiesDto;
	}

}
