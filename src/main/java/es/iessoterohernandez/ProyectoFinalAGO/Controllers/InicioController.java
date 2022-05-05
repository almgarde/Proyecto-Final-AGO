package es.iessoterohernandez.ProyectoFinalAGO.Controllers;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import es.iessoterohernandez.ProyectoFinalAGO.Services.NewsServiceI;
import es.iessoterohernandez.ProyectoFinalAGO.Services.Dto.NewsDto;

/**
 * Controlador Inicio
 * 
 * @author agadelao
 *
 */
@Controller
public class InicioController {

	/** Logger */
	final static Logger LOGGER = LoggerFactory.getLogger(InicioController.class);

	@Autowired
	NewsServiceI newsService;

	@GetMapping("/inicio")
	public String inicio(Model model) {

		LOGGER.info("InicioController inicio .- Inicio");

		String viewResult = "/views/common/Errors";

		try {
			final List<NewsDto> listaNewsDto = newsService.getFourMostRecentNews();

			if (listaNewsDto != null && !listaNewsDto.isEmpty()) {
				model.addAttribute("listaNews", listaNewsDto);
				viewResult = "/views/public/inicio";
			} else {
				LOGGER.error("InicioController inicio .- Error: Parámetros nulos");
			}
		} catch (Exception e) {
			LOGGER.error("InicioController inicio .- Error no controlado al redireccionar a la pantalla inicio");
			throw e;
		}

		LOGGER.info("InicioController inicio .- Fin");

		return viewResult;

	}
}
