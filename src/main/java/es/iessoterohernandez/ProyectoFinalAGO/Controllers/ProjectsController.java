package es.iessoterohernandez.ProyectoFinalAGO.Controllers;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import es.iessoterohernandez.ProyectoFinalAGO.Services.ProjectsServiceI;
import es.iessoterohernandez.ProyectoFinalAGO.Services.Dto.ProCatFormDto;
import es.iessoterohernandez.ProyectoFinalAGO.Services.Dto.ProCatMembersDto;
import es.iessoterohernandez.ProyectoFinalAGO.Services.Dto.ProjectsDto;

/**
 * Controlador. Entidad: Proyectos
 * 
 * @author agadelao
 *
 */
@Controller
public class ProjectsController {
	
	/** Logger */
	final static Logger LOGGER = LoggerFactory.getLogger(ProjectsController.class);

	@Autowired
	ProjectsServiceI projectsService;


	@GetMapping("/projects")
	public String getMembers(Model model) throws Exception {

		LOGGER.info("MembersController getMembers .- Inicio");

		String viewResult = "/views/common/Errors";

		try {
			// Para la lista del filtro - Se muestran entonces todas las categorías,
			// incluidas las que no tienen miembros
			final List<ProjectsDto> listaProjectsDto = projectsService.getAllProjectsActive();

			
			if (listaProjectsDto != null && !listaProjectsDto.isEmpty()) {
				model.addAttribute("listaProjectsDto", listaProjectsDto);

				viewResult = "/views/public/projects";
			} else {
				LOGGER.error("MembersController getMembers .- Error: Parámetros nulos");
			}
		} catch (Exception e) {
			LOGGER.error("MembersController getMembers .- Error no controlado al redireccionar a la pantalla members");
			throw e;
		}

		LOGGER.info("MembersController getMembers .- Fin");

		return viewResult;

	}

}
