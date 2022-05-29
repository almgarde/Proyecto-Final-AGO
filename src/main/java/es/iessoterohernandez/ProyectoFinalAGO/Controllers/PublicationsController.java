package es.iessoterohernandez.ProyectoFinalAGO.Controllers;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import es.iessoterohernandez.ProyectoFinalAGO.Persistence.Entity.ProfessionalCategory;
import es.iessoterohernandez.ProyectoFinalAGO.Services.PublicationsServiceI;
import es.iessoterohernandez.ProyectoFinalAGO.Services.Dto.ProCatFormDto;
import es.iessoterohernandez.ProyectoFinalAGO.Services.Dto.ProCatMembersDto;
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

	/** Logger */
	final static Logger LOGGER = LoggerFactory.getLogger(LinksController.class);

	@Autowired
	PublicationsServiceI publicationsService;

	@GetMapping("/publications")
	public String getPublications(Model model) throws Exception {
		LOGGER.info("PublicationsController getPublications .- Inicio");

		String viewResult = "/views/common/Errors";

		try {

			final List<PublicationsYearsDto> listaPublicationsYearsDto = publicationsService
					.getAllPublicationsActiveOrdered(Boolean.FALSE);

			if (listaPublicationsYearsDto != null && !listaPublicationsYearsDto.isEmpty()) {
				model.addAttribute("listaPublicationsYearsDto", listaPublicationsYearsDto);
				
				model.addAttribute("publicationsOrderFormDto", new PublicationsOrderFormDto());
				viewResult = "/views/public/publications";
			} else {
				LOGGER.error("PublicationsController getPublications .- Parámetros nulos");
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
	public String orderedPublications(Model model, PublicationsOrderFormDto publicationsOrderFormDto) throws Exception {

		LOGGER.info("PublicationsController orderPublication .- Inicio");

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
							"PublicationsController orderPublication .- Error: Parámetros nulos de listaPublicationsYearsDto");
				}

			} else {
				LOGGER.error(
						"PublicationsController orderPublication .- Error: Parámetros nulos de publicationsOrderFormDto");
			}

		} catch (Exception e) {
			LOGGER.error(
					"PublicationsController orderPublication .- Error no controlado al redireccionar a la pantalla publications");
			throw e;
		}

		LOGGER.info("PublicationsController orderPublication .- Fin");

		return viewResult;
	}

}
