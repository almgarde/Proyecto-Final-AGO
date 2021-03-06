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
import org.springframework.web.bind.annotation.RequestMapping;

import es.iessoterohernandez.ProyectoFinalAGO.Services.NewsServiceI;
import es.iessoterohernandez.ProyectoFinalAGO.Services.Dto.NewsDto;

/**
 * Controlador. Inicio
 * 
 * @author agadelao
 *
 */
@Controller
//@RequestMapping("/home")
public class HomeController {

	final static Logger LOGGER = LoggerFactory.getLogger(HomeController.class);

	@Autowired
	NewsServiceI newsService;

	@GetMapping({"/", "/home"})
	public String inicio(Model model) throws Exception {

		LOGGER.info("InicioController inicio .- Inicio");

		String viewResult = "/views/common/Errors";

		try {

			final List<NewsDto> listaNewsDto = newsService.getSixMostRecentNewsActive();
			Locale lang = LocaleContextHolder.getLocale();
			
			if (listaNewsDto != null && !listaNewsDto.isEmpty()) {
				model.addAttribute("listaNews", listaNewsDto);
				model.addAttribute("lang", lang);
				viewResult = "/views/public/home";
			} else {
				LOGGER.error("InicioController inicio .- Error: No existen noticias activas registradas");
			}

		} catch (Exception e) {
			LOGGER.error("InicioController inicio .- Error no controlado al redireccionar a la pantalla inicio");
			throw e;
		}

		LOGGER.info("InicioController inicio .- Fin");

		return viewResult;

	}
	
	@GetMapping({"/login"})
	public String index() {
		return "/views/public/login";
	}
	
	
}
