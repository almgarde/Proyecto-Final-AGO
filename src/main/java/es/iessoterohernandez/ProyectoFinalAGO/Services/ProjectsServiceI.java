package es.iessoterohernandez.ProyectoFinalAGO.Services;

import java.util.List;
import java.util.Map;

import es.iessoterohernandez.ProyectoFinalAGO.Persistence.Entity.Project;
import es.iessoterohernandez.ProyectoFinalAGO.Services.Dto.ProjectsDto;
import es.iessoterohernandez.ProyectoFinalAGO.Services.Dto.Datatables.ProjectsDatatableDto;

public interface ProjectsServiceI {

	/**
	 * Recupera todos los datos de los projectos almacenados en BDD
	 * 
	 * @return List<ProjectsDatatableDto>
	 * @throws Exception
	 */
	public List<ProjectsDatatableDto> getAllProjectsData() throws Exception;

	/**
	 * Almacena un proyecto en BDD
	 * 
	 * @param projectsData
	 * @param imageProjects
	 * @return Project
	 * @throws Exception
	 */
	public Project addProjects(Map<String, String> projectsData, String imageProjects) throws Exception;

	/**
	 * Actualiza los datos de un proyecto almacenado en BDD
	 * 
	 * @param projectsData
	 * @return Project
	 * @throws Exception
	 */
	public Project updateProjects(Map<String, String> projectsData) throws Exception;

	/**
	 * Actualiza la imagen de un proyecto almacenado en BDD
	 * 
	 * @param projectData
	 * @param photoProjects
	 * @return Project
	 * @throws Exception
	 */
	public Project updateImageProjects(Map<String, String> projectData, String photoProjects) throws Exception;

	/**
	 * Elimina un proyecto almacenado en BDD
	 * 
	 * @param projectsData
	 * @throws Exception
	 */
	public void deleteProjects(Map<String, String> projectsData) throws Exception;

	/**
	 * Recupera los proyectos activos
	 * 
	 * @return List<ProjectsDto>
	 * @throws Exception
	 */
	public List<ProjectsDto> getAllProjectsActive() throws Exception;
}
