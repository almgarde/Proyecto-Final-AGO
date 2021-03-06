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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import es.iessoterohernandez.ProyectoFinalAGO.Services.PublicationsServiceI;
import es.iessoterohernandez.ProyectoFinalAGO.Services.Dto.PublicationsYearsDto;
import es.iessoterohernandez.ProyectoFinalAGO.Services.Dto.Form.PublicationsOrderFormDto;

/**
 * Controlador. Entidad: Publicaciones
 * 
 * @author agadelao
 *
 */
@Controller
public class PublicationsController {

	
	final static Logger LOGGER = LoggerFactory.getLogger(PublicationsController.class);

	@Autowired
	PublicationsServiceI publicationsService;

	@GetMapping("/publications")
	public String getPublications(Model model) throws Exception {

		LOGGER.info("PublicationsController getPublications .- Inicio");

		String viewResult = "/views/common/Errors";

		try {

			final List<PublicationsYearsDto> listaPublicationsYearsDto = publicationsService
					.getAllPublicationsActiveOrdered(Boolean.FALSE);
			Locale lang = LocaleContextHolder.getLocale();

			if (listaPublicationsYearsDto != null && !listaPublicationsYearsDto.isEmpty()) {
				model.addAttribute("listaPublicationsYearsDto", listaPublicationsYearsDto);
				model.addAttribute("publicationsOrderFormDto", new PublicationsOrderFormDto());
				model.addAttribute("lang", lang);
				viewResult = "/views/public/publications";
			} else {
				LOGGER.error(
						"PublicationsController getPublications .- Error: No existen publicaciones activas registradas");
			}

		} catch (Exception e) {
			LOGGER.error(
					"PublicationsController getPublications .- Error no controlado al redireccionar a la pantalla publications");
			throw e;
		}

		LOGGER.info("PublicationsController getPublications .- Fin");

		return viewResult;

	}

	@PostMapping("/orderedPublications")
	@ResponseBody
	public String orderedPublications(Model model, PublicationsOrderFormDto publicationsOrderFormDto) throws Exception {

		LOGGER.info("PublicationsController orderedPublications .- Inicio");

		String viewResult = "/views/common/ErrorAjax";

		try {

			if (publicationsOrderFormDto != null) {

				final List<PublicationsYearsDto> listaPublicationsYearsDto = publicationsService
						.getAllPublicationsActiveOrdered(publicationsOrderFormDto.getAscendente());

				if (listaPublicationsYearsDto != null && !listaPublicationsYearsDto.isEmpty()) {
					model.addAttribute("listaPublicationsYearsDto", listaPublicationsYearsDto);
					viewResult = "/views/public/orderedPublications";
				} else {
					LOGGER.error(
							"PublicationsController orderedPublications .- Error: No existen publicaciones activas registradas");
				}

			} else {
				LOGGER.error("PublicationsController orderedPublications .- Error: Par??metros de entrada nulos");
			}

		} catch (Exception e) {
			LOGGER.error(
					"PublicationsController orderedPublications .- Error no controlado al redireccionar a la pantalla publications");
			throw e;
		}

		LOGGER.info("PublicationsController orderedPublications .- Fin");

		return viewResult;
	}

}
