package es.iessoterohernandez.ProyectoFinalAGO.Services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.iessoterohernandez.ProyectoFinalAGO.Persistence.Dao.ProjectDaoI;
import es.iessoterohernandez.ProyectoFinalAGO.Persistence.Entity.Member;
import es.iessoterohernandez.ProyectoFinalAGO.Persistence.Entity.Project;
import es.iessoterohernandez.ProyectoFinalAGO.Services.Dto.MembersDto;
import es.iessoterohernandez.ProyectoFinalAGO.Services.Dto.ProjectsDto;
import es.iessoterohernandez.ProyectoFinalAGO.Services.Dto.Datatables.ProjectsDatatableDto;

/**
 * Servicios. Entidad: Proyectos
 * 
 * @author agadelao
 *
 */
@Service
public class ProjectsServiceImpl implements ProjectsServiceI {
	
	/** Logger */
	final static Logger LOGGER = LoggerFactory.getLogger(ProjectsServiceImpl.class);

	@Autowired
	ProjectDaoI projectDao;
	
	/**
	 * Recupera todos los proyectos almacenados en BDD
	 * 
	 * @return
	 * @throws Exception
	 */
	public List<ProjectsDatatableDto> getAllProjectsData() throws Exception {

		LOGGER.info("ProjectsServiceImpl getAllProjectsData .- Inicio");

		List<ProjectsDatatableDto> listaProjectsDatatableDto = new ArrayList<ProjectsDatatableDto>();

		try {
			List<Project> listProjects = projectDao.findAll();

			if (listProjects != null && !listProjects.isEmpty()) {

				for (Project p : listProjects) {
					ProjectsDatatableDto projectsDatatableDto = new ProjectsDatatableDto();
					
					projectsDatatableDto.setIdProject(String.valueOf(p.getIdProject()));
					projectsDatatableDto.setTitleProject(p.getTitleProject());
					projectsDatatableDto.setImageProject(p.getImageProject());
					projectsDatatableDto.setDescriptionProject(p.getDescriptionProject());
					if (p.getActive()) {
						projectsDatatableDto.setActive("true");
					} else {
						projectsDatatableDto.setActive("false");
					}

					projectsDatatableDto.setAdmin(p.getUpdateAdmin());
					projectsDatatableDto.setDate(String.format("%1$td/%1$tm/%1$tY", p.getUpdateDate()));
					listaProjectsDatatableDto.add(projectsDatatableDto);
				}

			} else {
				LOGGER.error("ProjectsServiceImpl getAllProjectsData .- Error: Parámetros nulos");
			}
		} catch (Exception e) {
			LOGGER.error("ProjectsServiceImpl getAllProjectsData .- Error no controlado al recuperar los proyectos");
			throw e;
		}

		LOGGER.info("ProjectsServiceImpl getAllProjectsData .- Fin");

		return listaProjectsDatatableDto;

	}

	/**
	 * Almacena un proyecto en BDD
	 * 
	 * @param addNewsFormDto
	 * @param imageNews
	 */
	@Override

	public Project addProjects (Map<String, String> projectsData , String imageProjects) throws Exception {

		LOGGER.info("ProjectsServiceImpl addProjects .- Inicio");
		
		Project projectsSaved2 = null;

		try {
			if (projectsData != null && !projectsData.isEmpty() && !StringUtils.isEmpty(imageProjects)) {

				Project p = new Project();

				p.setTitleProject(projectsData.get("titleProjects"));
				p.setDescriptionProject(projectsData.get("descProjects"));
				if (Integer.parseInt(projectsData.get("active")) == 1) {
					p.setActive(true);
				} else {
					p.setActive(false);
				}

				p.setImageProject(imageProjects);
				p.setUpdateAdmin("agadelao");
				p.setUpdateDate(new Date());
				
				Project projectsSaved = projectDao.save(p);
				p.setImageProject(projectsSaved.getIdProject() + imageProjects);
				projectsSaved2 = projectDao.save(p);

				
				LOGGER.info("ProjectsServiceImpl addProjects .- Proyecto almacenado correctamente");

			} else {
				LOGGER.error("ProjectsServiceImpl addProjects .- Error: Parámetros nulos");
			}
		} catch (Exception e) {
			LOGGER.error("ProjectsServiceImpl addProjects .- Error no controlado al añadir el proyecto");
			throw e;
		}

		LOGGER.info("ProjectsServiceImpl addProjects .- Fin");
		
		return projectsSaved2;

	}
	
	
	/**
	 * Almacena una noticia en BDD
	 * 
	 * @param addNewsFormDto
	 * @param imageNews
	 */
	@Override
	public Project updateProjects(Map<String, String> projectsData) throws Exception {

		LOGGER.info("ProjectsServiceImpl updateProjects .- Inicio");

		Project projectUpdated = null;

		try {
			if (projectsData != null && !projectsData.isEmpty() ) {

				Project p = projectDao
						.findByIdProject(Long.parseLong(projectsData.get("idProject")));

				if (p != null) {
					
					p.setTitleProject(projectsData.get("titleProject"));
					p.setDescriptionProject(projectsData.get("descProject"));
					if (Integer.parseInt(projectsData.get("active")) == 1) {
						p.setActive(true);
					} else {
						p.setActive(false);
					}

					p.setUpdateAdmin("agadelao");
					p.setUpdateDate(new Date());

					projectUpdated = projectDao.save(p);
				} else {
					LOGGER.error("ProjectsServiceImpl updateProjects .- Equipo no encontrado");
				}
				LOGGER.info("ProjectsServiceImpl updateProjects .- Equipo actualizado correctamente");

			} else {
				LOGGER.error("ProjectsServiceImpl updateProjects .- Error: Parámetros nulos");
			}
		} catch (Exception e) {
			LOGGER.error("ProjectsServiceImpl updateProjects .- Error no controlado al actualizar la publicación");
			throw e;
		}

		LOGGER.info("ProjectsServiceImpl updateProjects .- Fin");

		return projectUpdated;

	}
	
	/**
	 * Almacena una noticia en BDD
	 * 
	 * @param addNewsFormDto
	 * @param imageNews
	 */
	@Override
	public Project updateImageProjects(Map<String, String> projectsData, String imageProjects) throws Exception {

		LOGGER.info("ProjectsServiceImpl updateProjects .- Inicio");

		Project projectImageUpdated = null;

		try {
			if (projectsData != null && !projectsData.isEmpty() && imageProjects != null) {

				Project p = projectDao
						.findByIdProject(Long.parseLong(projectsData.get("idProject")));

				if (p != null) {

					p.setImageProject(p.getIdProject() + imageProjects);
					projectImageUpdated = projectDao.save(p);
				} else {
					LOGGER.error("ProjectsServiceImpl updateProjects .- Equipo no encontrado");
				}
				LOGGER.info("ProjectsServiceImpl updateProjects .- Equipo actualizado correctamente");

			} else {
				LOGGER.error("ProjectsServiceImpl updateProjects .- Error: Parámetros nulos");
			}
		} catch (Exception e) {
			LOGGER.error("ProjectsServiceImpl updateProjects .- Error no controlado al actualizar la publicación");
			throw e;
		}

		LOGGER.info("ProjectsServiceImpl updateProjects .- Fin");

		return projectImageUpdated;

	}
	
	@Override
	public void deleteProjects(Map<String, String> projectsData) throws Exception {

		LOGGER.info("PublicationsServiceImpl deletePublications .- Inicio");


		try {
			if (projectsData != null && !projectsData.isEmpty()) {

				Project f = projectDao
						.findByIdProject(Long.parseLong(projectsData.get("idProject")));

				if (f != null) {

					projectDao.delete(f);

				}
				LOGGER.info("PublicationsServiceImpl deletePublications .- Publicación eliminada correctamente");

			} else {
				LOGGER.error("PublicationsServiceImpl deletePublications .- Error: Parámetros nulos");
			}
		} catch (Exception e) {
			LOGGER.error("PublicationsServiceImpl deletePublications .- Error no controlado al eliminar la publicación");
			throw e;
		}

		LOGGER.info("PublicationsServiceImpl deletePublications .- Fin");


	}
	
	/**
	 * Recupera los proyectos activos
	 * 
	 * @param proCat
	 * @return ProCatMembersDto
	 * @throws Exception
	 */
	@Override
	public List<ProjectsDto> getAllProjectsActive() throws Exception {
		
		List<ProjectsDto> listaProjectsDto = null;

		LOGGER.info("MembersServiceImpl getMembersByActive .- Inicio");
		try {
		

			List<Project> listaProjects = projectDao.findByActive(Boolean.TRUE);

			if (listaProjects != null && !listaProjects.isEmpty()) {
				listaProjectsDto = new ArrayList<ProjectsDto>();
				for (Project p : listaProjects) {

					ProjectsDto projectsDto = new ProjectsDto();
					projectsDto.setTitleProject(p.getTitleProject());
					projectsDto.setImageProject(p.getImageProject());
					projectsDto.setDescriptionProject(p.getDescriptionProject());

					listaProjectsDto.add(projectsDto);
				}
				

			} else {
				LOGGER.info("MembersServiceImpl getMembersByActive .- Parámetros nulos");
			}
		} catch (Exception e) {
			LOGGER.error(
					"MembersServiceImpl getMembersByActive .- Error no controlado al recuperar los miembros");
			throw e;
		}

		LOGGER.info("MembersServiceImpl getMembersByActive .- Fin");
		return listaProjectsDto;
	}

}



