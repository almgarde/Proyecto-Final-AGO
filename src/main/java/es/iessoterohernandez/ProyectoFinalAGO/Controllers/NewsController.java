package es.iessoterohernandez.ProyectoFinalAGO.Controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import es.iessoterohernandez.ProyectoFinalAGO.Services.NewsServiceI;
import es.iessoterohernandez.ProyectoFinalAGO.Services.Dto.NewsDto;

/**
 * Controlador. Entidad: Noticias
 * 
 * @author agadelao
 *
 */
@Controller
@RequestMapping("/news")
public class NewsController {

	final static Logger LOGGER = LoggerFactory.getLogger(NewsController.class);

	@Autowired
	NewsServiceI newsService;

	@GetMapping("/newsComplete/{idNews}")
	public String getNews(@PathVariable String idNews, Model model) throws Exception {

		LOGGER.info("NewsController getNews .- Inicio");

		String viewResult = "/views/common/Errors";

		try {

			final NewsDto newsDto = newsService.getNewsByIdActive(Long.parseLong(idNews));

			if (newsDto != null) {
				model.addAttribute("news", newsDto);
				viewResult = "/views/public/newsComplete";
			} else {
				LOGGER.error("NewsController getNews .- Error: No existen noticias activas registradas");
			}

		} catch (Exception e) {
			LOGGER.error("NewsController getNews .- Error no controlado al redireccionar a la pantalla newsComplete");
			throw e;
		}

		LOGGER.info("NewsController getNews .- Fin");

		return viewResult;

	}

}
