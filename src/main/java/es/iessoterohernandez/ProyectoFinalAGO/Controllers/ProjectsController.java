package es.iessoterohernandez.ProyectoFinalAGO.Controllers;

import java.util.List;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import es.iessoterohernandez.ProyectoFinalAGO.Services.ProjectsServiceI;
import es.iessoterohernandez.ProyectoFinalAGO.Services.Dto.ProjectsDto;

/**
 * Controlador. Entidad: Proyectos
 * 
 * @author agadelao
 *
 */
@Controller
public class ProjectsController {

	final static Logger LOGGER = LoggerFactory.getLogger(ProjectsController.class);

	@Autowired
	ProjectsServiceI projectsService;

	@GetMapping("/projects")
	public String getProjects(Model model) throws Exception {

		LOGGER.info("ProjectsController getProjects .- Inicio");

		String viewResult = "/views/common/Errors";

		try {

			final List<ProjectsDto> listaProjectsDto = projectsService.getAllProjectsActive();
			Locale lang = LocaleContextHolder.getLocale();

			if (listaProjectsDto != null && !listaProjectsDto.isEmpty()) {
				model.addAttribute("listaProjectsDto", listaProjectsDto);
				model.addAttribute("lang", lang);
				viewResult = "/views/public/projects";
			} else {
				LOGGER.error("ProjectsController getProjects .- Error: No existen proyectos activos registrados");
			}

		} catch (Exception e) {
			LOGGER.error(
					"ProjectsController getProjects .- Error no controlado al redireccionar a la pantalla projects");
			throw e;
		}

		LOGGER.info("ProjectsController getProjects .- Fin");

		return viewResult;

	}

}
