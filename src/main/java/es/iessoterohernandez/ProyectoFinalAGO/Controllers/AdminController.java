package es.iessoterohernandez.ProyectoFinalAGO.Controllers;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import es.iessoterohernandez.ProyectoFinalAGO.Persistence.Entity.News;
import es.iessoterohernandez.ProyectoFinalAGO.Services.NewsServiceI;
import es.iessoterohernandez.ProyectoFinalAGO.Services.Dto.Datatables.NewsDataTableDto;
import es.iessoterohernandez.ProyectoFinalAGO.Services.Dto.Form.AddNewsFormDto;

@Controller
@RequestMapping("/management")
public class AdminController {

	/** Logger */
	final static Logger LOGGER = LoggerFactory.getLogger(AdminController.class);

	@Autowired
	NewsServiceI newsService;

	@GetMapping
	public String getManagementPanel(Model model) throws Exception {

		String viewResult = "/views/private/gestionPanel";

		return viewResult;

	}

	@PostMapping("/getDataTableCategory")
	public String getDataTableCategory(Model model, String numCat) throws Exception {

		String viewResult = "/views/common/Errors";

		try {
			if (numCat != null) {
				switch (numCat) {
				case "0":

					break;

				case "1":
					model.addAttribute("addNewsFormDto", new AddNewsFormDto());
					viewResult = "/views/private/datatableNews";
					break;

				case "2":
					viewResult = "/views/private/datatableProCat";
					break;

				case "3":
					viewResult = "/views/private/datatableMembers";
					break;

				case "4":
					viewResult = "/views/private/datatableProjects";
					break;

				case "5":
					viewResult = "/views/private/datatableTechCat";
					break;

				case "6":
					viewResult = "/views/private/datatableDacilities";
					break;

				case "7":
					viewResult = "/views/private/datatableThesis";
					break;

				case "8":
					viewResult = "/views/private/datatableLinks";
					break;

				default:
					break;
				}
			} else {
				LOGGER.error("AdminController getDataTableCategory .- Error: Parámetros de entrada nulos");
			}
		} catch (Exception e) {
			LOGGER.error(
					"AdminController getDataTableCategory .- Error no controlado al redirigir a la vista seleccionada");
			throw e;
		}

		return viewResult;

	}

	@GetMapping("/getNewsData")
	@ResponseBody
	public List<NewsDataTableDto> getNewsData(Model model) throws Exception {

		LOGGER.info("AdminController getNewsData .- Inicio");

		List<NewsDataTableDto> listaNewsDataTableDto = null;

		try {

			listaNewsDataTableDto = newsService.getAllNewsData();

		} catch (Exception e) {
			LOGGER.error("AdminController getNewsData .- Error no controlado al recuperar los datos de las noticias");
			throw e;
		}

		LOGGER.info("AdminController getNewsData .- Fin");

		return listaNewsDataTableDto;

	}

	@PostMapping("/addNewsData")
	@ResponseBody
	public String addNewsData(Model model, @RequestParam Map<String, String> params, @RequestParam("file") MultipartFile imageNews)
			throws Exception {

		LOGGER.info("AdminController addNewsData .- Inicio");

		try {

			if (imageNews != null && !imageNews.isEmpty()) {				

				AddNewsFormDto addNewsFormDto = new AddNewsFormDto();
				addNewsFormDto.setTitleNews(params.get("titleNews"));
				addNewsFormDto.setAbstractNews(params.get("abstractNews"));
				addNewsFormDto.setContentNews(params.get("contentNews"));
				addNewsFormDto.setActive(params.get("active"));
					
								
				News n = newsService.addNews(addNewsFormDto, imageNews.getOriginalFilename());
				
				Path dirImages = Paths.get("src//main//resources//static//images");
				String rutaAbsoluta = dirImages.toFile().getAbsolutePath();

				byte[] bytesImg = imageNews.getBytes();
				Path rutaCompleta = Paths.get(rutaAbsoluta + "//" + n.getImageNews());
				Files.write(rutaCompleta, bytesImg);

			} else {
				LOGGER.error("AdminController addNewsData .- Error: Parámetros nulos");

			}

		} catch (Exception e) {
			LOGGER.error("AdminController addNewsData .- Error no controlado al dar de alta la noticia");
			throw e;
		}

		LOGGER.info("AdminController addNewsData .- Fin");

		return "redirect:/management";

	}

}
