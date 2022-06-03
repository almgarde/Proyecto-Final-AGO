package es.iessoterohernandez.ProyectoFinalAGO.Services;

import java.util.List;
import java.util.Map;

import es.iessoterohernandez.ProyectoFinalAGO.Persistence.Entity.Thesis;
import es.iessoterohernandez.ProyectoFinalAGO.Services.Dto.ThesisDto;
import es.iessoterohernandez.ProyectoFinalAGO.Services.Dto.Datatables.ThesisDatatableDto;

public interface ThesisServiceI {

	/**
	 * Recupera todos los datos de las tesis almacenados en BDD
	 * 
	 * @return List<ThesisDatatableDto>
	 * @throws Exception
	 */
	public List<ThesisDatatableDto> getAllThesisData() throws Exception;

	/**
	 * Almacena una tesis en BDD
	 * 
	 * @param thesisData
	 * @param imageThesis
	 * @return Thesis
	 * @throws Exception
	 */
	public Thesis addThesis(Map<String, String> thesisData, String imageThesis) throws Exception;

	/**
	 * Actualiza los datos de una tesis almacenada en BDD
	 * 
	 * @param thesisData
	 * @return Thesis
	 * @throws Exception
	 */
	public Thesis updateThesis(Map<String, String> thesisData) throws Exception;

	/**
	 * Actualiza la portada de una tesis almacenada en BDD
	 * 
	 * @param thesisData
	 * @param photoThesis
	 * @return Thesis
	 * @throws Exception
	 */
	public Thesis updateCoverPageThesis(Map<String, String> thesisData, String photoThesis) throws Exception;

	/**
	 * Elimina una tesis almacenada en BDD
	 * 
	 * @param thesisData
	 * @throws Exception
	 */
	public void deleteThesis(Map<String, String> thesisData) throws Exception;

	/**
	 * Recupera las tesis activas
	 * 
	 * @return List<ThesisDto>
	 * @throws Exception
	 */
	public List<ThesisDto> getAllThesisActive() throws Exception;

}
