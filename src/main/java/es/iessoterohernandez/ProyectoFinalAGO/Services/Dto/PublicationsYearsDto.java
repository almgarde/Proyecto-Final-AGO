package es.iessoterohernandez.ProyectoFinalAGO.Services.Dto;

import java.util.List;

/**
 * DTO entidad: Publicaciones - Años
 * 
 * @author agadelao
 *
 */
public class PublicationsYearsDto {

	private int yearPublication;

	private List<PublicationsDto> listaPublicationsDto;

	public int getYearPublication() {
		return yearPublication;
	}

	public void setYearPublication(int yearPublication) {
		this.yearPublication = yearPublication;
	}

	public List<PublicationsDto> getListaPublicationsDto() {
		return listaPublicationsDto;
	}

	public void setListaPublicationsDto(List<PublicationsDto> listaPublicationsDto) {
		this.listaPublicationsDto = listaPublicationsDto;
	}

}
