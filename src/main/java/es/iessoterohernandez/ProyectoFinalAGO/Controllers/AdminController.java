package es.iessoterohernandez.ProyectoFinalAGO.Controllers;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import es.iessoterohernandez.ProyectoFinalAGO.Persistence.Entity.Facility;
import es.iessoterohernandez.ProyectoFinalAGO.Persistence.Entity.Link;
import es.iessoterohernandez.ProyectoFinalAGO.Persistence.Entity.Member;
import es.iessoterohernandez.ProyectoFinalAGO.Persistence.Entity.News;
import es.iessoterohernandez.ProyectoFinalAGO.Persistence.Entity.Project;
import es.iessoterohernandez.ProyectoFinalAGO.Persistence.Entity.Publication;
import es.iessoterohernandez.ProyectoFinalAGO.Persistence.Entity.Thesis;
import es.iessoterohernandez.ProyectoFinalAGO.Services.FacilitiesServiceI;
import es.iessoterohernandez.ProyectoFinalAGO.Services.LinksServiceI;
import es.iessoterohernandez.ProyectoFinalAGO.Services.MembersServiceI;
import es.iessoterohernandez.ProyectoFinalAGO.Services.NewsServiceI;
import es.iessoterohernandez.ProyectoFinalAGO.Services.ProCatServiceI;
import es.iessoterohernandez.ProyectoFinalAGO.Services.ProjectsServiceI;
import es.iessoterohernandez.ProyectoFinalAGO.Services.PublicationsServiceI;
import es.iessoterohernandez.ProyectoFinalAGO.Services.TechCatServiceI;
import es.iessoterohernandez.ProyectoFinalAGO.Services.ThesisServiceI;
import es.iessoterohernandez.ProyectoFinalAGO.Services.Dto.MembersDto;
import es.iessoterohernandez.ProyectoFinalAGO.Services.Dto.ProCatDto;
import es.iessoterohernandez.ProyectoFinalAGO.Services.Dto.TechCatDto;
import es.iessoterohernandez.ProyectoFinalAGO.Services.Dto.Datatables.FacilitiesDatatableDto;
import es.iessoterohernandez.ProyectoFinalAGO.Services.Dto.Datatables.LinksDatatableDto;
import es.iessoterohernandez.ProyectoFinalAGO.Services.Dto.Datatables.MembersDatatableDto;
import es.iessoterohernandez.ProyectoFinalAGO.Services.Dto.Datatables.NewsDatatableDto;
import es.iessoterohernandez.ProyectoFinalAGO.Services.Dto.Datatables.ProCatDatatableDto;
import es.iessoterohernandez.ProyectoFinalAGO.Services.Dto.Datatables.ProjectsDatatableDto;
import es.iessoterohernandez.ProyectoFinalAGO.Services.Dto.Datatables.PublicationsDatatableDto;
import es.iessoterohernandez.ProyectoFinalAGO.Services.Dto.Datatables.TechCatDatatableDto;
import es.iessoterohernandez.ProyectoFinalAGO.Services.Dto.Datatables.ThesisDatatableDto;
import es.iessoterohernandez.ProyectoFinalAGO.Services.Dto.Form.PublicationsFormDto;
import es.iessoterohernandez.ProyectoFinalAGO.Utils.GeneratorExcels;

@Controller
@RequestMapping("/management")
public class AdminController {

	/** Logger */
	final static Logger LOGGER = LoggerFactory.getLogger(AdminController.class);

	@Autowired
	NewsServiceI newsService;

	@Autowired
	ProCatServiceI proCatService;

	@Autowired
	TechCatServiceI techCatService;

	@Autowired
	MembersServiceI membersService;

	@Autowired
	LinksServiceI linksService;

	@Autowired
	ProjectsServiceI projectsService;

	@Autowired
	ThesisServiceI thesisService;

	@Autowired
	FacilitiesServiceI facilitiesService;

	@Autowired
	PublicationsServiceI publicationsService;

	@GetMapping
	public String getManagementPanel(Model model) throws Exception {

		String viewResult = "/views/private/gestionPanel";

		return viewResult;

	}

	@PostMapping("/getDatatableCategory/")
	public String getDatatableCategory(Model model, String numCat) throws Exception {

		if (numCat == null) {
			numCat = (String) model.getAttribute("catPublication");
		}
		String viewResult = "/views/common/Errors";

		try {
			if (numCat != null) {
				switch (numCat) {
				case "0":

					break;

				case "1":
					viewResult = "/views/private/datatableNews";
					break;

				case "2":
					viewResult = "/views/private/datatableProCat";
					break;

				case "3":
					List<ProCatDto> listaProCatDto = proCatService.getAllProCat();
					List<ProCatDto> listaProCatDtoActivas = proCatService.getAllProCatActive();

					if (listaProCatDto != null && !listaProCatDto.isEmpty() && listaProCatDtoActivas != null
							&& !listaProCatDtoActivas.isEmpty()) {
						model.addAttribute("listaProCatDto", listaProCatDto);
						model.addAttribute("listaProCatDtoActivas", listaProCatDtoActivas);
					}
					viewResult = "/views/private/datatableMembers";
					break;

				case "4":
					viewResult = "/views/private/datatableProjects";
					break;

				case "5":
					viewResult = "/views/private/datatableTechCat";
					break;

				case "6":
					final List<TechCatDto> listaTechCatDto = techCatService.getAllTechCat();
					final List<TechCatDto> listaTechCatDtoActivas = techCatService.getAllTechCatActive();

					if (listaTechCatDto != null && !listaTechCatDto.isEmpty() && listaTechCatDtoActivas != null
							&& !listaTechCatDtoActivas.isEmpty()) {
						model.addAttribute("listaTechCatDto", listaTechCatDto);
						model.addAttribute("listaTechCatDtoActivas", listaTechCatDtoActivas);
					}

					viewResult = "/views/private/datatableFacilities";
					break;

				case "7":
					List<MembersDto> listaMembersDto = membersService.getMembersByActive();

					if (listaMembersDto != null && !listaMembersDto.isEmpty()) {
						model.addAttribute("listaMembersDto", listaMembersDto);
						model.addAttribute("publicationsFormDto", new PublicationsFormDto());
					}

					viewResult = "/views/private/datatablePublications";
					break;

				case "8":
					viewResult = "/views/private/datatableThesis";
					break;

				case "9":
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

	// NOTICIAS

	@GetMapping("/getNewsData")
	@ResponseBody
	public List<NewsDatatableDto> getNewsData(Model model) throws Exception {

		LOGGER.info("AdminController getNewsData .- Inicio");

		List<NewsDatatableDto> listaNewsDataTableDto = null;

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
	public void addNewsData(Model model, @RequestParam Map<String, String> params,
			@RequestParam("file") MultipartFile imageNews) throws Exception {

		LOGGER.info("AdminController addNewsData .- Inicio");

		try {

			if (imageNews != null && !imageNews.isEmpty() && params != null && !params.isEmpty()) {

				News n = newsService.addNews(params, imageNews.getOriginalFilename());

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

	}

	@PostMapping("/updateNewsData")
	@ResponseBody
	public void updateNewsData(Model model, @RequestParam Map<String, String> params) throws Exception {

		LOGGER.info("AdminController addMembersData .- Inicio");

		try {

			if (params != null && !params.isEmpty()) {

				newsService.updateNews(params);

			} else {
				LOGGER.error("AdminController addMembersData .- Error: Parámetros nulos");

			}

		} catch (Exception e) {
			LOGGER.error("AdminController addMembersData .- Error no controlado al dar de alta el equipo");
			throw e;
		}

		LOGGER.info("AdminController addMembersData .- Fin");
	}

	@PostMapping("/updateImageNewsData")
	@ResponseBody
	public void updateImageNewsData(Model model, @RequestParam Map<String, String> params,
			@RequestParam("file") MultipartFile imageNews) throws Exception {

		LOGGER.info("AdminController addMembersData .- Inicio");

		try {

			if (imageNews != null && !imageNews.isEmpty() && params != null && !params.isEmpty()) {

				News n = newsService.updateImageNews(params, imageNews.getOriginalFilename());

				Path dirImages = Paths.get("src//main//resources//static//images");
				String rutaAbsoluta = dirImages.toFile().getAbsolutePath();

				byte[] bytesImg = imageNews.getBytes();
				Path rutaCompleta = Paths.get(rutaAbsoluta + "//" + n.getImageNews());
				Files.write(rutaCompleta, bytesImg);

			} else {
				LOGGER.error("AdminController addMembersData .- Error: Parámetros nulos");

			}

		} catch (Exception e) {
			LOGGER.error("AdminController addMembersData .- Error no controlado al dar de alta el equipo");
			throw e;
		}

		LOGGER.info("AdminController addMembersData .- Fin");
	}

	@PostMapping("/deleteNewsData")
	@ResponseBody
	public void deleteNewsData(Model model, @RequestParam Map<String, String> params) throws Exception {

		LOGGER.info("AdminController deleteMembersData .- Inicio");

		try {

			if (params != null && !params.isEmpty()) {

				newsService.deleteNews(params);

			} else {
				LOGGER.error("AdminController deleteMembersData .- Error: Parámetros nulos");

			}

		} catch (Exception e) {
			LOGGER.error("AdminController deleteMembersData .- Error no controlado al dar de alta el equipo");
			throw e;
		}

		LOGGER.info("AdminController deleteMembersData .- Fin");
	}
	
	@GetMapping("/generateExcel/news")
	public void exportToExcelNews(HttpServletResponse response) throws Exception {
		List<NewsDatatableDto> listaNews = null;
		try {
			response.setHeader("Content-Disposition", "attachment; filename=\"dataNews.xlsx\"");

			listaNews = newsService.getAllNewsData();

			if (listaNews != null && !listaNews.isEmpty()) {
				GeneratorExcels generatorExcel = new GeneratorExcels();

				generatorExcel.exportExcelNews(response, listaNews);
			} else {
				LOGGER.error("AdminController getTechCatData .- Lista vacía");

			}

		} catch (Exception e) {
			LOGGER.error("AdminController getTechCatData .- Lista vacía");

			throw e;
		}
	}

	// CATEGORÍAS PROFESIONALES

	@GetMapping("/getProCatData")
	@ResponseBody
	public List<ProCatDatatableDto> getProCatData(Model model) throws Exception {

		LOGGER.info("AdminController getProCatData .- Inicio");

		List<ProCatDatatableDto> listaProCatDataTableDto = null;

		try {

			listaProCatDataTableDto = proCatService.getAllProCatData();

		} catch (Exception e) {
			LOGGER.error(
					"AdminController getProCatData .- Error no controlado al recuperar los datos de las categorías profesionales");
			throw e;
		}

		LOGGER.info("AdminController getProCatData .- Fin");

		return listaProCatDataTableDto;

	}

	@PostMapping("/addProCatData")
	@ResponseBody
	public void addProCatData(Model model, @RequestParam Map<String, String> params) throws Exception {

		LOGGER.info("AdminController addProCatData .- Inicio");

		try {

			if (params != null && !params.isEmpty()) {

				proCatService.addProCat(params);

			} else {
				LOGGER.error("AdminController addProCatData .- Error: Parámetros nulos");

			}

		} catch (Exception e) {
			LOGGER.error(
					"AdminController addProCatData .- Error no controlado al dar de alta la categoría profesional");
			throw e;
		}

		LOGGER.info("AdminController addProCatData .- Fin");

	}

	@PostMapping("/updateProCatData")
	@ResponseBody
	public void updateProCatData(Model model, @RequestParam Map<String, String> params) throws Exception {

		LOGGER.info("AdminController addMembersData .- Inicio");

		try {

			if (params != null && !params.isEmpty()) {

				proCatService.updateProCat(params);

			} else {
				LOGGER.error("AdminController addMembersData .- Error: Parámetros nulos");

			}

		} catch (Exception e) {
			LOGGER.error("AdminController addMembersData .- Error no controlado al dar de alta el equipo");
			throw e;
		}

		LOGGER.info("AdminController addMembersData .- Fin");
	}

	@PostMapping("/deleteProCatData")
	@ResponseBody
	public void deleteProCatData(Model model, @RequestParam Map<String, String> params) throws Exception {

		LOGGER.info("AdminController deleteMembersData .- Inicio");

		try {

			if (params != null && !params.isEmpty()) {

				proCatService.deleteProCat(params);

			} else {
				LOGGER.error("AdminController deleteMembersData .- Error: Parámetros nulos");

			}

		} catch (Exception e) {
			LOGGER.error("AdminController deleteMembersData .- Error no controlado al dar de alta el equipo");
			throw e;
		}

		LOGGER.info("AdminController deleteMembersData .- Fin");
	}

	@GetMapping("/generateExcel/proCat")
	public void exportToExcelProCat(HttpServletResponse response) throws Exception {
		List<ProCatDatatableDto> listaProCat = null;
		try {
			response.setHeader("Content-Disposition", "attachment; filename=\"dataProCat.xlsx\"");

			listaProCat = proCatService.getAllProCatData();

			if (listaProCat != null && !listaProCat.isEmpty()) {
				GeneratorExcels generatorExcelProCat = new GeneratorExcels();

				generatorExcelProCat.exportExcelProCat(response, listaProCat);
			} else {
				LOGGER.error("AdminController getTechCatData .- Lista vacía");

			}

		} catch (Exception e) {
			LOGGER.error("AdminController getTechCatData .- Lista vacía");

			throw e;
		}
	}

	// CATEGORÍAS TÉCNICAS

	@GetMapping("/getTechCatData")
	@ResponseBody
	public List<TechCatDatatableDto> getTechCatData(Model model) throws Exception {

		LOGGER.info("AdminController getTechCatData .- Inicio");

		List<TechCatDatatableDto> listaTechCatDataTableDto = null;

		try {

			listaTechCatDataTableDto = techCatService.getAllTechCatData();

		} catch (Exception e) {
			LOGGER.error(
					"AdminController getTechCatData .- Error no controlado al recuperar los datos de las categorías técnicas");
			throw e;
		}

		LOGGER.info("AdminController getTechCatData .- Fin");

		return listaTechCatDataTableDto;

	}

	@PostMapping("/addTechCatData")
	@ResponseBody
	public void addTechCatData(Model model, @RequestParam Map<String, String> params) throws Exception {

		LOGGER.info("AdminController addTechCatData .- Inicio");

		try {

			if (params != null && !params.isEmpty()) {

				techCatService.addTechCat(params);

			} else {
				LOGGER.error("AdminController addTechCatData .- Error: Parámetros nulos");

			}

		} catch (Exception e) {
			LOGGER.error("AdminController addTechCatData .- Error no controlado al dar de alta la categoría técnica");
			throw e;
		}

		LOGGER.info("AdminController addTechCatData .- Fin");

	}

	@PostMapping("/updateTechCatData")
	@ResponseBody
	public void updateTchCatData(Model model, @RequestParam Map<String, String> params) throws Exception {

		LOGGER.info("AdminController addMembersData .- Inicio");

		try {

			if (params != null && !params.isEmpty()) {

				techCatService.updateTechCat(params);

			} else {
				LOGGER.error("AdminController addMembersData .- Error: Parámetros nulos");

			}

		} catch (Exception e) {
			LOGGER.error("AdminController addMembersData .- Error no controlado al dar de alta el equipo");
			throw e;
		}

		LOGGER.info("AdminController addMembersData .- Fin");
	}

	@PostMapping("/deleteTechCatData")
	@ResponseBody
	public void deleteTechCatData(Model model, @RequestParam Map<String, String> params) throws Exception {

		LOGGER.info("AdminController deleteMembersData .- Inicio");

		try {

			if (params != null && !params.isEmpty()) {

				techCatService.deleteTechCat(params);

			} else {
				LOGGER.error("AdminController deleteMembersData .- Error: Parámetros nulos");

			}

		} catch (Exception e) {
			LOGGER.error("AdminController deleteMembersData .- Error no controlado al dar de alta el equipo");
			throw e;
		}

		LOGGER.info("AdminController deleteMembersData .- Fin");
	}
	
	@GetMapping("/generateExcel/techCat")
	public void exportToExcelTechCat(HttpServletResponse response) throws Exception {
		List<TechCatDatatableDto> listaTechCat = null;
		try {
			response.setHeader("Content-Disposition", "attachment; filename=\"dataTechCat.xlsx\"");

			listaTechCat = techCatService.getAllTechCatData();

			if (listaTechCat != null && !listaTechCat.isEmpty()) {
				GeneratorExcels generatorExcel = new GeneratorExcels();

				generatorExcel.exportExcelTechCat(response, listaTechCat);
			} else {
				LOGGER.error("AdminController getTechCatData .- Lista vacía");

			}

		} catch (Exception e) {
			LOGGER.error("AdminController getTechCatData .- Lista vacía");

			throw e;
		}
	}

	// MIEMBROS

	@GetMapping("/getMembersData")
	@ResponseBody
	public List<MembersDatatableDto> getMembersData(Model model) throws Exception {

		LOGGER.info("AdminController getMembersData .- Inicio");

		List<MembersDatatableDto> listaMembersDataTableDto = null;

		try {

			listaMembersDataTableDto = membersService.getAllMembersData();

		} catch (Exception e) {
			LOGGER.error(
					"AdminController getMembersData .- Error no controlado al recuperar los datos de los miembros");
			throw e;
		}

		LOGGER.info("AdminController getMembersData .- Fin");

		return listaMembersDataTableDto;

	}

	@PostMapping("/addMembersData")
	@ResponseBody
	public void addMembersData(Model model, @RequestParam Map<String, String> params,
			@RequestParam("file") MultipartFile photoMember) throws Exception {

		LOGGER.info("AdminController addMembersData .- Inicio");

		try {

			if (photoMember != null && !photoMember.isEmpty() && params != null && !params.isEmpty()) {

				Member m = membersService.addMembers(params, photoMember.getOriginalFilename());

				Path dirImages = Paths.get("src//main//resources//static//images");
				String rutaAbsoluta = dirImages.toFile().getAbsolutePath();

				byte[] bytesImg = photoMember.getBytes();
				Path rutaCompleta = Paths.get(rutaAbsoluta + "//" + m.getPhotoMember());
				Files.write(rutaCompleta, bytesImg);

			} else {
				LOGGER.error("AdminController addMembersData .- Error: Parámetros nulos");

			}

		} catch (Exception e) {
			LOGGER.error("AdminController addMembersData .- Error no controlado al dar de alta el miembro");
			throw e;
		}

		LOGGER.info("AdminController addMembersData .- Fin");

	}

	@PostMapping("/updateMembersData")
	@ResponseBody
	public void updateMembersData(Model model, @RequestParam Map<String, String> params) throws Exception {

		LOGGER.info("AdminController addMembersData .- Inicio");

		try {

			if (params != null && !params.isEmpty()) {

				Member m = membersService.updateMembers(params);

			} else {
				LOGGER.error("AdminController addMembersData .- Error: Parámetros nulos");

			}

		} catch (Exception e) {
			LOGGER.error("AdminController addMembersData .- Error no controlado al dar de alta el equipo");
			throw e;
		}

		LOGGER.info("AdminController addMembersData .- Fin");
	}

	@PostMapping("/updatePhotoMembersData")
	@ResponseBody
	public void updatePhotoMembersData(Model model, @RequestParam Map<String, String> params,
			@RequestParam("file") MultipartFile photoFacility) throws Exception {

		LOGGER.info("AdminController addMembersData .- Inicio");

		try {

			if (photoFacility != null && !photoFacility.isEmpty() && params != null && !params.isEmpty()) {

				Member m = membersService.updatePhotoMembers(params, photoFacility.getOriginalFilename());

				Path dirImages = Paths.get("src//main//resources//static//images");
				String rutaAbsoluta = dirImages.toFile().getAbsolutePath();

				byte[] bytesImg = photoFacility.getBytes();
				Path rutaCompleta = Paths.get(rutaAbsoluta + "//" + m.getPhotoMember());
				Files.write(rutaCompleta, bytesImg);

			} else {
				LOGGER.error("AdminController addMembersData .- Error: Parámetros nulos");

			}

		} catch (Exception e) {
			LOGGER.error("AdminController addMembersData .- Error no controlado al dar de alta el equipo");
			throw e;
		}

		LOGGER.info("AdminController addMembersData .- Fin");
	}

	@PostMapping("/deleteMembersData")
	@ResponseBody
	public void deleteMembersData(Model model, @RequestParam Map<String, String> params) throws Exception {

		LOGGER.info("AdminController deleteMembersData .- Inicio");

		try {

			if (params != null && !params.isEmpty()) {

				membersService.deleteMembers(params);

			} else {
				LOGGER.error("AdminController deleteMembersData .- Error: Parámetros nulos");

			}

		} catch (Exception e) {
			LOGGER.error("AdminController deleteMembersData .- Error no controlado al dar de alta el equipo");
			throw e;
		}

		LOGGER.info("AdminController deleteMembersData .- Fin");
	}
	
	@GetMapping("/generateExcel/members")
	public void exportToExcelMembers(HttpServletResponse response) throws Exception {
		List<MembersDatatableDto> listaMembers = null;
		try {
			response.setHeader("Content-Disposition", "attachment; filename=\"dataMembers.xlsx\"");

			listaMembers = membersService.getAllMembersData();

			if (listaMembers != null && !listaMembers.isEmpty()) {
				GeneratorExcels generatorExcel = new GeneratorExcels();

				generatorExcel.exportExcelMembers(response, listaMembers);
			} else {
				LOGGER.error("AdminController getTechCatData .- Lista vacía");

			}

		} catch (Exception e) {
			LOGGER.error("AdminController getTechCatData .- Lista vacía");

			throw e;
		}
	}

	// EQUIPOS DE INVESTIGACIÓN

	@GetMapping("/getFacilitiesData")
	@ResponseBody
	public List<FacilitiesDatatableDto> getFacilitiesData(Model model) throws Exception {

		LOGGER.info("AdminController getFacilitiesData .- Inicio");

		List<FacilitiesDatatableDto> listaFacilitiesDataTableDto = null;

		try {

			listaFacilitiesDataTableDto = facilitiesService.getAllFacilitiesData();

		} catch (Exception e) {
			LOGGER.error(
					"AdminController getFacilitiesData .- Error no controlado al recuperar los datos de los equipos");
			throw e;
		}

		LOGGER.info("AdminController getFacilitiesData .- Fin");

		return listaFacilitiesDataTableDto;

	}

	@PostMapping("/addFacilitiesData")
	@ResponseBody
	public void addFacilitiesData(Model model, @RequestParam Map<String, String> params,
			@RequestParam("file") MultipartFile photoFacility) throws Exception {

		LOGGER.info("AdminController addFacilitiesData .- Inicio");

		try {

			if (photoFacility != null && !photoFacility.isEmpty() && params != null && !params.isEmpty()) {

				Facility f = facilitiesService.addFacilities(params, photoFacility.getOriginalFilename());

				Path dirImages = Paths.get("src//main//resources//static//images");
				String rutaAbsoluta = dirImages.toFile().getAbsolutePath();

				byte[] bytesImg = photoFacility.getBytes();
				Path rutaCompleta = Paths.get(rutaAbsoluta + "//" + f.getPhotoFacility());
				Files.write(rutaCompleta, bytesImg);

			} else {
				LOGGER.error("AdminController addFacilitiesData .- Error: Parámetros nulos");

			}

		} catch (Exception e) {
			LOGGER.error("AdminController addFacilitiesData .- Error no controlado al dar de alta el equipo");
			throw e;
		}

		LOGGER.info("AdminController addFacilitiesData .- Fin");

	}

	@PostMapping("/updateFacilitiesData")
	@ResponseBody
	public void updateFacilitiesData(Model model, @RequestParam Map<String, String> params) throws Exception {

		LOGGER.info("AdminController addFacilitiesData .- Inicio");

		try {

			if (params != null && !params.isEmpty()) {

				Facility f = facilitiesService.updateFacilities(params);

			} else {
				LOGGER.error("AdminController addFacilitiesData .- Error: Parámetros nulos");

			}

		} catch (Exception e) {
			LOGGER.error("AdminController addFacilitiesData .- Error no controlado al dar de alta el equipo");
			throw e;
		}

		LOGGER.info("AdminController addFacilitiesData .- Fin");
	}

	@PostMapping("/updatePhotoFacilitiesData")
	@ResponseBody
	public void updatePhotoFacilitiesData(Model model, @RequestParam Map<String, String> params,
			@RequestParam("file") MultipartFile photoFacility) throws Exception {

		LOGGER.info("AdminController addFacilitiesData .- Inicio");

		try {

			if (photoFacility != null && !photoFacility.isEmpty() && params != null && !params.isEmpty()) {

				Facility f = facilitiesService.updatePhotoFacilities(params, photoFacility.getOriginalFilename());

				Path dirImages = Paths.get("src//main//resources//static//images");
				String rutaAbsoluta = dirImages.toFile().getAbsolutePath();

				byte[] bytesImg = photoFacility.getBytes();
				Path rutaCompleta = Paths.get(rutaAbsoluta + "//" + f.getPhotoFacility());
				Files.write(rutaCompleta, bytesImg);

			} else {
				LOGGER.error("AdminController addFacilitiesData .- Error: Parámetros nulos");

			}

		} catch (Exception e) {
			LOGGER.error("AdminController addFacilitiesData .- Error no controlado al dar de alta el equipo");
			throw e;
		}

		LOGGER.info("AdminController addFacilitiesData .- Fin");
	}

	@PostMapping("/deleteFacilitiesData")
	@ResponseBody
	public void deleteFacilitiesData(Model model, @RequestParam Map<String, String> params) throws Exception {

		LOGGER.info("AdminController addFacilitiesData .- Inicio");

		try {

			if (params != null && !params.isEmpty()) {

				facilitiesService.deleteFacilities(params);

			} else {
				LOGGER.error("AdminController addFacilitiesData .- Error: Parámetros nulos");

			}

		} catch (Exception e) {
			LOGGER.error("AdminController addFacilitiesData .- Error no controlado al dar de alta el equipo");
			throw e;
		}

		LOGGER.info("AdminController addFacilitiesData .- Fin");
	}
	
	@GetMapping("/generateExcel/facilities")
	public void exportToExcelFacilities(HttpServletResponse response) throws Exception {
		List<FacilitiesDatatableDto> listaFacilities = null;
		try {
			response.setHeader("Content-Disposition", "attachment; filename=\"dataFacilities.xlsx\"");

			listaFacilities = facilitiesService.getAllFacilitiesData();

			if (listaFacilities != null && !listaFacilities.isEmpty()) {
				GeneratorExcels generatorExcel = new GeneratorExcels();

				generatorExcel.exportExcelFacilities(response, listaFacilities);
			} else {
				LOGGER.error("AdminController getTechCatData .- Lista vacía");

			}

		} catch (Exception e) {
			LOGGER.error("AdminController getTechCatData .- Lista vacía");

			throw e;
		}
	}

	// PUBLICACIONES

	@GetMapping("/getPublicationsData")
	@ResponseBody
	public List<PublicationsDatatableDto> getPublicationsData(Model model) throws Exception {

		LOGGER.info("AdminController getPublicationsData .- Inicio");

		List<PublicationsDatatableDto> listaPublicationsDatatableDto = null;

		try {

			listaPublicationsDatatableDto = publicationsService.getAllPublicationsData();

		} catch (Exception e) {
			LOGGER.error(
					"AdminController getPublicationsData .- Error no controlado al recuperar los datos de las publicaciones");
			throw e;
		}

		LOGGER.info("AdminController getPublicationsData .- Fin");

		return listaPublicationsDatatableDto;

	}

	@PostMapping("/addPublicationsData")
	public String addPublicationsData(Model model, PublicationsFormDto publicationsFormDto,
			RedirectAttributes redirectAttributes) throws Exception {

		LOGGER.info("AdminController addPublicationsData .- Inicio");

		try {

			if (publicationsFormDto != null) {

				Publication p = publicationsService.addPublications(publicationsFormDto);

				redirectAttributes.addFlashAttribute("catPublication", true);

			} else {
				LOGGER.error("AdminController addPublicationsData .- Error: Parámetros nulos");

			}

		} catch (Exception e) {
			LOGGER.error("AdminController addPublicationsData .- Error no controlado al dar de alta la publicación");
			throw e;
		}

		LOGGER.info("AdminController addPublicationsData .- Fin");

		return "redirect:/management";
	}

	@PostMapping("/updatePublicationsData")
	public String updatePublicationsData(Model model, PublicationsFormDto publicationsFormDto,
			RedirectAttributes redirectAttributes) throws Exception {

		LOGGER.info("AdminController updatePublicationsData .- Inicio");

		try {

			if (publicationsFormDto != null) {

				Publication p = publicationsService.updatePublications(publicationsFormDto);

				redirectAttributes.addFlashAttribute("catPublication", true);
			} else {
				LOGGER.error("AdminController updatePublicationsData .- Error: Parámetros nulos");

			}

		} catch (Exception e) {
			LOGGER.error("AdminController updatePublicationsData .- Error no controlado al actualizar la publicación");
			throw e;
		}

		LOGGER.info("AdminController updatePublicationsData .- Fin");

		return "redirect:/management";
	}

	@PostMapping("/deletePublicationsData")
	@ResponseBody
	public void deletePublicationsData(Model model, @RequestParam Map<String, String> params) throws Exception {

		LOGGER.info("AdminController addFacilitiesData .- Inicio");

		try {

			if (params != null && !params.isEmpty()) {

				publicationsService.deletePublications(params);

			} else {
				LOGGER.error("AdminController addFacilitiesData .- Error: Parámetros nulos");

			}

		} catch (Exception e) {
			LOGGER.error("AdminController addFacilitiesData .- Error no controlado al dar de alta el equipo");
			throw e;
		}

		LOGGER.info("AdminController addFacilitiesData .- Fin");

	}
	
	@GetMapping("/generateExcel/publications")
	public void exportToExcelPublications(HttpServletResponse response) throws Exception {
		List<PublicationsDatatableDto> listaPublications = null;
		try {
			response.setHeader("Content-Disposition", "attachment; filename=\"dataPublications.xlsx\"");

			listaPublications = publicationsService.getAllPublicationsData();

			if (listaPublications != null && !listaPublications.isEmpty()) {
				GeneratorExcels generatorExcel = new GeneratorExcels();

				generatorExcel.exportExcelPublications(response, listaPublications);
			} else {
				LOGGER.error("AdminController getTechCatData .- Lista vacía");

			}

		} catch (Exception e) {
			LOGGER.error("AdminController getTechCatData .- Lista vacía");

			throw e;
		}
	}

	// PROYECTOS

	@GetMapping("/getProjectsData")
	@ResponseBody
	public List<ProjectsDatatableDto> getProjectsData(Model model) throws Exception {

		LOGGER.info("AdminController getProjectsData .- Inicio");

		List<ProjectsDatatableDto> listaProjectsDataTableDto = null;

		try {

			listaProjectsDataTableDto = projectsService.getAllProjectsData();

		} catch (Exception e) {
			LOGGER.error(
					"AdminController getProjectsData .- Error no controlado al recuperar los datos de los proyectos");
			throw e;
		}

		LOGGER.info("AdminController getProjectsData .- Fin");

		return listaProjectsDataTableDto;

	}

	@PostMapping("/addProjectsData")
	@ResponseBody
	public void addProjectsData(Model model, @RequestParam Map<String, String> params,
			@RequestParam("file") MultipartFile imageProjects) throws Exception {

		LOGGER.info("AdminController addProjectsData .- Inicio");

		try {

			if (imageProjects != null && !imageProjects.isEmpty() && params != null && !params.isEmpty()) {

				Project p = projectsService.addProjects(params, imageProjects.getOriginalFilename());

				Path dirImages = Paths.get("src//main//resources//static//images");
				String rutaAbsoluta = dirImages.toFile().getAbsolutePath();

				byte[] bytesImg = imageProjects.getBytes();
				Path rutaCompleta = Paths.get(rutaAbsoluta + "//" + p.getImageProject());
				Files.write(rutaCompleta, bytesImg);

			} else {
				LOGGER.error("AdminController addProjectsData .- Error: Parámetros nulos");

			}

		} catch (Exception e) {
			LOGGER.error("AdminController addProjectsData .- Error no controlado al dar de alta el proyecto");
			throw e;
		}

		LOGGER.info("AdminController addProjectsData .- Fin");

	}

	@PostMapping("/updateProjectsData")
	@ResponseBody
	public void updateProjectsData(Model model, @RequestParam Map<String, String> params) throws Exception {

		LOGGER.info("AdminController addMembersData .- Inicio");

		try {

			if (params != null && !params.isEmpty()) {

				projectsService.updateProjects(params);

			} else {
				LOGGER.error("AdminController addMembersData .- Error: Parámetros nulos");

			}

		} catch (Exception e) {
			LOGGER.error("AdminController addMembersData .- Error no controlado al dar de alta el equipo");
			throw e;
		}

		LOGGER.info("AdminController addMembersData .- Fin");
	}

	@PostMapping("/updateImageProjectsData")
	@ResponseBody
	public void updateImageProjectsData(Model model, @RequestParam Map<String, String> params,
			@RequestParam("file") MultipartFile imageProjects) throws Exception {

		LOGGER.info("AdminController addMembersData .- Inicio");

		try {

			if (imageProjects != null && !imageProjects.isEmpty() && params != null && !params.isEmpty()) {

				Project p = projectsService.updateImageProjects(params, imageProjects.getOriginalFilename());

				Path dirImages = Paths.get("src//main//resources//static//images");
				String rutaAbsoluta = dirImages.toFile().getAbsolutePath();

				byte[] bytesImg = imageProjects.getBytes();
				Path rutaCompleta = Paths.get(rutaAbsoluta + "//" + p.getImageProject());
				Files.write(rutaCompleta, bytesImg);

			} else {
				LOGGER.error("AdminController addMembersData .- Error: Parámetros nulos");

			}

		} catch (Exception e) {
			LOGGER.error("AdminController addMembersData .- Error no controlado al dar de alta el equipo");
			throw e;
		}

		LOGGER.info("AdminController addMembersData .- Fin");
	}

	@PostMapping("/deleteProjectsData")
	@ResponseBody
	public void deleteProjectsData(Model model, @RequestParam Map<String, String> params) throws Exception {

		LOGGER.info("AdminController deleteMembersData .- Inicio");

		try {

			if (params != null && !params.isEmpty()) {

				projectsService.deleteProjects(params);

			} else {
				LOGGER.error("AdminController deleteMembersData .- Error: Parámetros nulos");

			}

		} catch (Exception e) {
			LOGGER.error("AdminController deleteMembersData .- Error no controlado al dar de alta el equipo");
			throw e;
		}

		LOGGER.info("AdminController deleteMembersData .- Fin");
	}
	
	@GetMapping("/generateExcel/projects")
	public void exportToExcelProjects(HttpServletResponse response) throws Exception {
		List<ProjectsDatatableDto> listaProjects = null;
		try {
			response.setHeader("Content-Disposition", "attachment; filename=\"dataProjects.xlsx\"");

			listaProjects = projectsService.getAllProjectsData();

			if (listaProjects != null && !listaProjects.isEmpty()) {
				GeneratorExcels generatorExcel = new GeneratorExcels();

				generatorExcel.exportExcelProjects(response, listaProjects);
			} else {
				LOGGER.error("AdminController getTechCatData .- Lista vacía");

			}

		} catch (Exception e) {
			LOGGER.error("AdminController getTechCatData .- Lista vacía");

			throw e;
		}
	}

	// TESIS

	@GetMapping("/getThesisData")
	@ResponseBody
	public List<ThesisDatatableDto> getThesisData(Model model) throws Exception {

		LOGGER.info("AdminController getThesisData .- Inicio");

		List<ThesisDatatableDto> listaThesisDataTableDto = null;

		try {

			listaThesisDataTableDto = thesisService.getAllThesisData();

		} catch (Exception e) {
			LOGGER.error("AdminController getThesisData .- Error no controlado al recuperar los datos de las tesis");
			throw e;
		}

		LOGGER.info("AdminController getThesisData .- Fin");

		return listaThesisDataTableDto;

	}

	@PostMapping("/addThesisData")
	@ResponseBody
	public void addThesisData(Model model, @RequestParam Map<String, String> params,
			@RequestParam("file") MultipartFile coverPageThesis) throws Exception {

		LOGGER.info("AdminController addThesisData .- Inicio");

		try {

			if (coverPageThesis != null && !coverPageThesis.isEmpty() && params != null && !params.isEmpty()) {

				Thesis t = thesisService.addThesis(params, coverPageThesis.getOriginalFilename());

				Path dirImages = Paths.get("src//main//resources//static//images");
				String rutaAbsoluta = dirImages.toFile().getAbsolutePath();

				byte[] bytesImg = coverPageThesis.getBytes();
				Path rutaCompleta = Paths.get(rutaAbsoluta + "//" + t.getCoverPageThesis());
				Files.write(rutaCompleta, bytesImg);

			} else {
				LOGGER.error("AdminController addThesisData .- Error: Parámetros nulos");

			}

		} catch (Exception e) {
			LOGGER.error("AdminController addThesisData .- Error no controlado al dar de alta la tesis");
			throw e;
		}

		LOGGER.info("AdminController addThesisData .- Fin");

	}

	@PostMapping("/updateThesisData")
	@ResponseBody
	public void updateThesisData(Model model, @RequestParam Map<String, String> params) throws Exception {

		LOGGER.info("AdminController addMembersData .- Inicio");

		try {

			if (params != null && !params.isEmpty()) {

				thesisService.updateThesis(params);

			} else {
				LOGGER.error("AdminController addMembersData .- Error: Parámetros nulos");

			}

		} catch (Exception e) {
			LOGGER.error("AdminController addMembersData .- Error no controlado al dar de alta el equipo");
			throw e;
		}

		LOGGER.info("AdminController addMembersData .- Fin");
	}

	@PostMapping("/updateCoverPageThesisData")
	@ResponseBody
	public void updateCoverPageThesisData(Model model, @RequestParam Map<String, String> params,
			@RequestParam("file") MultipartFile coverPageThesis) throws Exception {

		LOGGER.info("AdminController addMembersData .- Inicio");

		try {

			if (coverPageThesis != null && !coverPageThesis.isEmpty() && params != null && !params.isEmpty()) {

				Thesis t = thesisService.updateCoverPageThesis(params, coverPageThesis.getOriginalFilename());

				Path dirImages = Paths.get("src//main//resources//static//images");
				String rutaAbsoluta = dirImages.toFile().getAbsolutePath();

				byte[] bytesImg = coverPageThesis.getBytes();
				Path rutaCompleta = Paths.get(rutaAbsoluta + "//" + t.getCoverPageThesis());
				Files.write(rutaCompleta, bytesImg);

			} else {
				LOGGER.error("AdminController addMembersData .- Error: Parámetros nulos");

			}

		} catch (Exception e) {
			LOGGER.error("AdminController addMembersData .- Error no controlado al dar de alta el equipo");
			throw e;
		}

		LOGGER.info("AdminController addMembersData .- Fin");
	}

	@PostMapping("/deleteThesisData")
	@ResponseBody
	public void deleteThesisData(Model model, @RequestParam Map<String, String> params) throws Exception {

		LOGGER.info("AdminController deleteMembersData .- Inicio");

		try {

			if (params != null && !params.isEmpty()) {

				thesisService.deleteThesis(params);

			} else {
				LOGGER.error("AdminController deleteMembersData .- Error: Parámetros nulos");

			}

		} catch (Exception e) {
			LOGGER.error("AdminController deleteMembersData .- Error no controlado al dar de alta el equipo");
			throw e;
		}

		LOGGER.info("AdminController deleteMembersData .- Fin");
	}
	
	@GetMapping("/generateExcel/thesis")
	public void exportToExcelThesis(HttpServletResponse response) throws Exception {
		List<ThesisDatatableDto> listaThesis = null;
		try {
			response.setHeader("Content-Disposition", "attachment; filename=\"dataThesis.xlsx\"");

			listaThesis = thesisService.getAllThesisData();

			if (listaThesis != null && !listaThesis.isEmpty()) {
				GeneratorExcels generatorExcel = new GeneratorExcels();

				generatorExcel.exportExcelThesis(response, listaThesis);
			} else {
				LOGGER.error("AdminController getTechCatData .- Lista vacía");

			}

		} catch (Exception e) {
			LOGGER.error("AdminController getTechCatData .- Lista vacía");

			throw e;
		}
	}

	// LINKS

	@GetMapping("/getLinksData")
	@ResponseBody
	public List<LinksDatatableDto> getLinksData(Model model) throws Exception {

		LOGGER.info("AdminController getLinksData .- Inicio");

		List<LinksDatatableDto> listaLinksDataTableDto = null;

		try {

			listaLinksDataTableDto = linksService.getAllLinksData();

		} catch (Exception e) {
			LOGGER.error("AdminController getLinksData .- Error no controlado al recuperar los datos de los links");
			throw e;
		}

		LOGGER.info("AdminController getLinksData .- Fin");

		return listaLinksDataTableDto;

	}

	@PostMapping("/addLinksData")
	@ResponseBody
	public void addLinksData(Model model, @RequestParam Map<String, String> params,
			@RequestParam("file") MultipartFile imageLinks) throws Exception {

		LOGGER.info("AdminController addLinksData .- Inicio");

		try {

			if (imageLinks != null && !imageLinks.isEmpty() && params != null && !params.isEmpty()) {

				Link l = linksService.addLinks(params, imageLinks.getOriginalFilename());

				Path dirImages = Paths.get("src//main//resources//static//images");
				String rutaAbsoluta = dirImages.toFile().getAbsolutePath();

				byte[] bytesImg = imageLinks.getBytes();
				Path rutaCompleta = Paths.get(rutaAbsoluta + "//" + l.getImageLink());
				Files.write(rutaCompleta, bytesImg);

			} else {
				LOGGER.error("AdminController addLinksData .- Error: Parámetros nulos");

			}

		} catch (Exception e) {
			LOGGER.error("AdminController addLinksData .- Error no controlado al dar de alta el link");
			throw e;
		}

		LOGGER.info("AdminController addLinksData .- Fin");

	}

	@PostMapping("/updateLinksData")
	@ResponseBody
	public void updateLinksData(Model model, @RequestParam Map<String, String> params) throws Exception {

		LOGGER.info("AdminController addMembersData .- Inicio");

		try {

			if (params != null && !params.isEmpty()) {

				linksService.updateLinks(params);

			} else {
				LOGGER.error("AdminController addMembersData .- Error: Parámetros nulos");

			}

		} catch (Exception e) {
			LOGGER.error("AdminController addMembersData .- Error no controlado al dar de alta el equipo");
			throw e;
		}

		LOGGER.info("AdminController addMembersData .- Fin");
	}

	@PostMapping("/updateImageLinksData")
	@ResponseBody
	public void updateImageLinksData(Model model, @RequestParam Map<String, String> params,
			@RequestParam("file") MultipartFile imageLinks) throws Exception {

		LOGGER.info("AdminController addMembersData .- Inicio");

		try {

			if (imageLinks != null && !imageLinks.isEmpty() && params != null && !params.isEmpty()) {

				Link l = linksService.updateImageLinks(params, imageLinks.getOriginalFilename());

				Path dirImages = Paths.get("src//main//resources//static//images");
				String rutaAbsoluta = dirImages.toFile().getAbsolutePath();

				byte[] bytesImg = imageLinks.getBytes();
				Path rutaCompleta = Paths.get(rutaAbsoluta + "//" + l.getImageLink());
				Files.write(rutaCompleta, bytesImg);

			} else {
				LOGGER.error("AdminController addMembersData .- Error: Parámetros nulos");

			}

		} catch (Exception e) {
			LOGGER.error("AdminController addMembersData .- Error no controlado al dar de alta el equipo");
			throw e;
		}

		LOGGER.info("AdminController addMembersData .- Fin");
	}

	@PostMapping("/deleteLinksData")
	@ResponseBody
	public void deleteLinksData(Model model, @RequestParam Map<String, String> params) throws Exception {

		LOGGER.info("AdminController deleteMembersData .- Inicio");

		try {

			if (params != null && !params.isEmpty()) {

				linksService.deleteLinks(params);

			} else {
				LOGGER.error("AdminController deleteMembersData .- Error: Parámetros nulos");

			}

		} catch (Exception e) {
			LOGGER.error("AdminController deleteMembersData .- Error no controlado al dar de alta el equipo");
			throw e;
		}

		LOGGER.info("AdminController deleteMembersData .- Fin");
	}
	
	@GetMapping("/generateExcel/links")
	public void exportToExcelLinks(HttpServletResponse response) throws Exception {
		List<LinksDatatableDto> listaLinks = null;
		try {
			response.setHeader("Content-Disposition", "attachment; filename=\"dataLinks.xlsx\"");

			listaLinks = linksService.getAllLinksData();

			if (listaLinks != null && !listaLinks.isEmpty()) {
				GeneratorExcels generatorExcel = new GeneratorExcels();

				generatorExcel.exportExcelLinks(response, listaLinks);
			} else {
				LOGGER.error("AdminController getTechCatData .- Lista vacía");

			}

		} catch (Exception e) {
			LOGGER.error("AdminController getTechCatData .- Lista vacía");

			throw e;
		}
	}

}
