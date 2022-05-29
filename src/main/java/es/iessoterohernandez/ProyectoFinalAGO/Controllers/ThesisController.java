package es.iessoterohernandez.ProyectoFinalAGO.Controllers;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import es.iessoterohernandez.ProyectoFinalAGO.Services.ThesisServiceI;
import es.iessoterohernandez.ProyectoFinalAGO.Services.Dto.ThesisDto;

/**
 * Controlador. Entidad: Tesis
 * 
 * @author agadelao
 *
 */
@Controller
public class ThesisController {
	
	/** Logger */
	final static Logger LOGGER = LoggerFactory.getLogger(LinksController.class);

	@Autowired
	ThesisServiceI thesisService;

	@GetMapping("/thesis")
	public String getThesis(Model model) throws Exception {
		LOGGER.info("LinksController getLinks .- Inicio");

		String viewResult = "/views/common/Errors";

		try {

			final List<ThesisDto> listaThesisDto = thesisService.getAllThesisActive();

			if (listaThesisDto != null && !listaThesisDto.isEmpty()) {
				model.addAttribute("listaThesisDto", listaThesisDto);
				viewResult = "/views/public/thesis";
			} else {
				LOGGER.error("LinksController getLinks .- Parámetros nulos");
			}
		} catch (Exception e) {
			LOGGER.error("LinksController getLinks .- Error no controlado al redireccionar a la pantalla links");
			throw e;
		}

		LOGGER.info("LinksController getLinks .- Fin");

		return viewResult;

	}

}
