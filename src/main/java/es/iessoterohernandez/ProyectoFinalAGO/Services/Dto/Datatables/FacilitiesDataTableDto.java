package es.iessoterohernandez.ProyectoFinalAGO.Services.Dto.Datatables;

import es.iessoterohernandez.ProyectoFinalAGO.Persistence.Entity.TechnicalCategory;

public class FacilitiesDataTableDto {

	private String idFacilitie;

	private String nameFacilitie;

	private TechnicalCategory idTechCat;

	private String imageFacilitie;

	private String featuresFacilitie;

	public String getIdFacilitie() {
		return idFacilitie;
	}

	public void setIdFacilitie(String idFacilitie) {
		this.idFacilitie = idFacilitie;
	}

	public String getNameFacilitie() {
		return nameFacilitie;
	}

	public void setNameFacilitie(String nameFacilitie) {
		this.nameFacilitie = nameFacilitie;
	}

	public TechnicalCategory getIdTechCat() {
		return idTechCat;
	}

	public void setIdTechCat(TechnicalCategory idTechCat) {
		this.idTechCat = idTechCat;
	}

	public String getImageFacilitie() {
		return imageFacilitie;
	}

	public void setImageFacilitie(String imageFacilitie) {
		this.imageFacilitie = imageFacilitie;
	}

	public String getFeaturesFacilitie() {
		return featuresFacilitie;
	}

	public void setFeaturesFacilitie(String featuresFacilitie) {
		this.featuresFacilitie = featuresFacilitie;
	}

}
