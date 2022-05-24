package es.iessoterohernandez.ProyectoFinalAGO.Services;

import java.util.List;
import java.util.Map;

import es.iessoterohernandez.ProyectoFinalAGO.Persistence.Entity.Thesis;
import es.iessoterohernandez.ProyectoFinalAGO.Persistence.Entity.Thesis;
import es.iessoterohernandez.ProyectoFinalAGO.Services.Dto.Datatables.ThesisDatatableDto;

public interface ThesisServiceI {
	
	/**
	 * Recupera todos las tesis almacenadas en BDD
	 * @return
	 * @throws Exception
	 */
	public List<ThesisDatatableDto> getAllThesisData() throws Exception;
	
	/**
	 * Almacena una tesis en BDD
	 * 
	 * @param addNewsFormDto
	 * @param imageNews
	 */
	public Thesis addThesis(Map<String, String> thesisData, String imageThesis) throws Exception;
	
	public Thesis updateThesis(Map<String, String> thesisData) throws Exception;

	public Thesis updateCoverPageThesis(Map<String, String> thesisData, String photoThesis) throws Exception;
	
	public void deleteThesis(Map<String, String> thesisData) throws Exception;

}
