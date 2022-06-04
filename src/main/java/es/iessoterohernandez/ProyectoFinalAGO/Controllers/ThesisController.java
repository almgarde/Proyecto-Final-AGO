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

	final static Logger LOGGER = LoggerFactory.getLogger(ThesisController.class);

	@Autowired
	ThesisServiceI thesisService;

	@GetMapping("/thesis")
	public String getThesis(Model model) throws Exception {

		LOGGER.info("ThesisController getThesis .- Inicio");

		String viewResult = "/views/common/Errors";

		try {

			final List<ThesisDto> listaThesisDto = thesisService.getAllThesisActive();
			Locale lang = LocaleContextHolder.getLocale();

			if (listaThesisDto != null && !listaThesisDto.isEmpty()) {
				model.addAttribute("listaThesisDto", listaThesisDto);
				model.addAttribute("lang", lang);
				viewResult = "/views/public/thesis";
			} else {
				LOGGER.error("ThesisController getThesis .- Error: No existen tesis activas registradas");
			}

		} catch (Exception e) {
			LOGGER.error("ThesisController getThesis .- Error no controlado al redireccionar a la pantalla thesis");
			throw e;
		}

		LOGGER.info("ThesisController getThesis .- Fin");

		return viewResult;

	}

}
