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

import es.iessoterohernandez.ProyectoFinalAGO.Persistence.Entity.TechnicalCategory;
import es.iessoterohernandez.ProyectoFinalAGO.Services.FacilitiesServiceI;
import es.iessoterohernandez.ProyectoFinalAGO.Services.TechCatServiceI;
import es.iessoterohernandez.ProyectoFinalAGO.Services.Dto.TechCatDto;
import es.iessoterohernandez.ProyectoFinalAGO.Services.Dto.TechCatFacilitiesDto;
import es.iessoterohernandez.ProyectoFinalAGO.Services.Dto.TechCatFormDto;

/**
 * Controlador. Entidad: Equipos de investigación
 * 
 * @author agadelao
 *
 */
@Controller
public class FacilitiesController {

	/** Logger */
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
			// Para la lista del filtro - Se muestran entonces todas las categorías,
			// incluidas las que no tienen miembros
			final List<TechCatDto> listaTechCatDto = techCatService.getAllTechCatActive();

			// Muestra los miembros de aquellas categorías con miembros
			final List<TechCatFacilitiesDto> listaTechCatFacilitiesDto = facilitiesService
					.getAllFacilitiesByTechCatActive();

			// Para formulario
			TechCatFormDto techCatFormDto = new TechCatFormDto();

			if (listaTechCatFacilitiesDto != null && !listaTechCatFacilitiesDto.isEmpty()) {
				model.addAttribute("listaTechCatDto", listaTechCatDto);
				model.addAttribute("listaTechCatFacilitiesDto", listaTechCatFacilitiesDto);
				model.addAttribute("techCatFormDto", techCatFormDto);
				viewResult = "/views/public/facilities";
			} else {
				LOGGER.error("FacilitiesController getFacilities .- Error: Parámetros nulos");
			}
		} catch (Exception e) {
			LOGGER.error(
					"FacilitiesController getFacilities .- Error no controlado al redireccionar a la pantalla facilities");
			throw e;
		}

		LOGGER.info("FacilitiesController getFacilities .- Fin");

		return viewResult;

	}

	@PostMapping("/filterFacilities")
	public String getFilterfacilities(Model model, TechCatFormDto techCatFormDto) throws Exception {

		LOGGER.info("FacilitiesController getFilterFacilities .- Inicio");

		String viewResult = "/views/common/Errors";
		List<TechCatFacilitiesDto> listaTechCatFacilitiesDto = null;

		try {
			if (Long.parseLong(techCatFormDto.getIdTechCat()) == 0) {
				listaTechCatFacilitiesDto = facilitiesService.getAllFacilitiesByTechCatActive();
			} else {
				TechnicalCategory techCat = new TechnicalCategory();
				techCat.setIdTechCat(Long.parseLong(techCatFormDto.getIdTechCat()));

				final TechCatFacilitiesDto techCatFacilitiesDto = facilitiesService
						.getFacilitiesByTechCatActive(techCat);

				if (techCatFacilitiesDto != null) {
					listaTechCatFacilitiesDto = new ArrayList<TechCatFacilitiesDto>();
					listaTechCatFacilitiesDto.add(techCatFacilitiesDto);

				} else {
					LOGGER.error(
							"FacilitiesController getFilterFacilities .- Error: Parámetros nulos de techCatFacilitiesDto");
				}

			}

			if (listaTechCatFacilitiesDto != null && !listaTechCatFacilitiesDto.isEmpty()) {
				model.addAttribute("listaTechCatFacilitiesDto", listaTechCatFacilitiesDto);
				viewResult = "/views/public/filterFacilities";

			} else {
				LOGGER.error(
						"FacilitiesController getFilterFacilities .- Error: Parámetros nulos de lista techCatFacilitiesDto");
			}
		} catch (Exception e) {
			LOGGER.error(
					"FacilitiesController getFilterFacilities .- Error no controlado al redireccionar a la pantalla facilities");
			throw e;
		}

		LOGGER.info("facilitiesController getFilterfacilities .- Fin");

		return viewResult;
	}
}
