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

import es.iessoterohernandez.ProyectoFinalAGO.Services.LinksServiceI;
import es.iessoterohernandez.ProyectoFinalAGO.Services.Dto.LinksDto;

/**
 * Controlador. Entidad: Links
 * 
 * @author agadelao
 *
 */
@Controller
public class LinksController {

	final static Logger LOGGER = LoggerFactory.getLogger(LinksController.class);

	@Autowired
	LinksServiceI linksService;

	@GetMapping("/links")
	public String getLinks(Model model) throws Exception {
		LOGGER.info("LinksController getLinks .- Inicio");

		String viewResult = "/views/common/Errors";

		try {

			final List<LinksDto> listaLinkDto = linksService.getAllLinksActive();
			Locale lang = LocaleContextHolder.getLocale();

			if (listaLinkDto != null && !listaLinkDto.isEmpty()) {
				model.addAttribute("listaLinkDto", listaLinkDto);
				model.addAttribute("lang", lang);
				viewResult = "/views/public/links";
			} else {
				LOGGER.error("LinksController getLinks .- Error: No existen links activos registrados");
			}

		} catch (Exception e) {
			LOGGER.error("LinksController getLinks .- Error no controlado al redireccionar a la pantalla links");
			throw e;
		}

		LOGGER.info("LinksController getLinks .- Fin");

		return viewResult;

	}

}
