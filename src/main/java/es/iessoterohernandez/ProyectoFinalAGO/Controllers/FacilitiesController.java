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

import es.iessoterohernandez.ProyectoFinalAGO.Services.FacilitiesServiceI;
import es.iessoterohernandez.ProyectoFinalAGO.Services.TechCatServiceI;
import es.iessoterohernandez.ProyectoFinalAGO.Services.Dto.TechCatFacilitiesDto;

/**
 * Controlador. Entidad: Equipos de investigación
 * 
 * @author agadelao
 *
 */
@Controller
public class FacilitiesController {

	final static Logger LOGGER = LoggerFactory.getLogger(FacilitiesController.class);

	@Autowired
	FacilitiesServiceI facilitiesService;

	@Autowired
	TechCatServiceI techCatService;

	@GetMapping("/facilities")
	public String getFacilities(Model model) throws Exception {

		LOGGER.info("FacilitiesController getFacilities .- Inicio");

		String viewResult = "/views/common/Errors";

		try {

			final List<TechCatFacilitiesDto> listaTechCatFacilitiesDto = facilitiesService
					.getAllFacilitiesByTechCatActive();
			
			Locale lang = LocaleContextHolder.getLocale();

			if (listaTechCatFacilitiesDto != null && !listaTechCatFacilitiesDto.isEmpty()) {
				model.addAttribute("listaTechCatFacilitiesDto", listaTechCatFacilitiesDto);
				model.addAttribute("lang", lang);
				viewResult = "/views/public/facilities";
			} else {
				LOGGER.error("FacilitiesController getFacilities .- Error: No existen equipos activos registrados");
			}

		} catch (Exception e) {
			LOGGER.error(
					"FacilitiesController getFacilities .- Error no controlado al redireccionar a la pantalla facilities");
			throw e;
		}

		LOGGER.info("FacilitiesController getFacilities .- Fin");

		return viewResult;

	}

}
