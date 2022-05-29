package es.iessoterohernandez.ProyectoFinalAGO.Services;

import java.util.List;
import java.util.Map;

import es.iessoterohernandez.ProyectoFinalAGO.Persistence.Entity.ProfessionalCategory;
import es.iessoterohernandez.ProyectoFinalAGO.Persistence.Entity.Project;
import es.iessoterohernandez.ProyectoFinalAGO.Persistence.Entity.Project;
import es.iessoterohernandez.ProyectoFinalAGO.Services.Dto.ProjectsDto;
import es.iessoterohernandez.ProyectoFinalAGO.Services.Dto.Datatables.ProjectsDatatableDto;

public interface ProjectsServiceI {
	
	/**
	 * Recupera todos los proyectos almacenados en BDD
	 * @return
	 * @throws Exception
	 */
	public List<ProjectsDatatableDto> getAllProjectsData() throws Exception;
	
	/**
	 * Almacena un proyecto en BDD
	 * 
	 * @param addNewsFormDto
	 * @param imageNews
	 */
	public Project addProjects(Map<String, String> projectsData, String imageProjects) throws Exception;
	
	public Project updateProjects(Map<String, String> projectsData) throws Exception;

	public Project updateImageProjects(Map<String, String> projectData, String photoProjects) throws Exception;
	public void deleteProjects(Map<String, String> projectsData) throws Exception;
	
	/**
	 * Recupera los proyectos activos
	 * 
	 * @param proCat
	 * @return ProCatMembersDto
	 * @throws Exception
	 */
	
	public List<ProjectsDto> getAllProjectsActive() throws Exception;

}
