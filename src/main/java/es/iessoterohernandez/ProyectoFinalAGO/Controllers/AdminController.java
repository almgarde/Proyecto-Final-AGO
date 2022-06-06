package es.iessoterohernandez.ProyectoFinalAGO.Controllers;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.ResponseEntity;
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
import es.iessoterohernandez.ProyectoFinalAGO.Persistence.Entity.Thesis;
import es.iessoterohernandez.ProyectoFinalAGO.Services.AdminServiceI;
import es.iessoterohernandez.ProyectoFinalAGO.Services.FacilitiesServiceI;
import es.iessoterohernandez.ProyectoFinalAGO.Services.LinksServiceI;
import es.iessoterohernandez.ProyectoFinalAGO.Services.MembersServiceI;
import es.iessoterohernandez.ProyectoFinalAGO.Services.NewsServiceI;
import es.iessoterohernandez.ProyectoFinalAGO.Services.ProCatServiceI;
import es.iessoterohernandez.ProyectoFinalAGO.Services.ProjectsServiceI;
import es.iessoterohernandez.ProyectoFinalAGO.Services.PublicationsServiceI;
import es.iessoterohernandez.ProyectoFinalAGO.Services.TechCatServiceI;
import es.iessoterohernandez.ProyectoFinalAGO.Services.ThesisServiceI;
import es.iessoterohernandez.ProyectoFinalAGO.Services.Dto.AdminDto;
import es.iessoterohernandez.ProyectoFinalAGO.Services.Dto.MembersDto;
import es.iessoterohernandez.ProyectoFinalAGO.Services.Dto.ProCatDto;
import es.iessoterohernandez.ProyectoFinalAGO.Services.Dto.TechCatDto;
import es.iessoterohernandez.ProyectoFinalAGO.Services.Dto.Datatables.AdminsDatatableDto;
import es.iessoterohernandez.ProyectoFinalAGO.Services.Dto.Datatables.FacilitiesDatatableDto;
import es.iessoterohernandez.ProyectoFinalAGO.Services.Dto.Datatables.LinksDatatableDto;
import es.iessoterohernandez.ProyectoFinalAGO.Services.Dto.Datatables.MembersDatatableDto;
import es.iessoterohernandez.ProyectoFinalAGO.Services.Dto.Datatables.NewsDatatableDto;
import es.iessoterohernandez.ProyectoFinalAGO.Services.Dto.Datatables.ProCatDatatableDto;
import es.iessoterohernandez.ProyectoFinalAGO.Services.Dto.Datatables.ProjectsDatatableDto;
import es.iessoterohernandez.ProyectoFinalAGO.Services.Dto.Datatables.PublicationsDatatableDto;
import es.iessoterohernandez.ProyectoFinalAGO.Services.Dto.Datatables.TechCatDatatableDto;
import es.iessoterohernandez.ProyectoFinalAGO.Services.Dto.Datatables.ThesisDatatableDto;
import es.iessoterohernandez.ProyectoFinalAGO.Services.Dto.Form.AdminsFormDto;
import es.iessoterohernandez.ProyectoFinalAGO.Services.Dto.Form.PublicationsFormDto;
import es.iessoterohernandez.ProyectoFinalAGO.Utils.GeneratorExcels;

@Controller
@RequestMapping("/management")
public class AdminController {

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

	@Autowired
	AdminServiceI adminService;

	@GetMapping
	public String getManagementPanel(Model model) throws Exception {

//		String username = auth.getName();
//		
//		if (session.getAttribute("admin") == null) {
//			Admin admin = adminService.getAdminByUsername(username);
//			admin.setPwdAdmin(null);
//			session.setAttribute("admin", admin);
//		}
		Locale lang = LocaleContextHolder.getLocale();
		model.addAttribute("lang", lang);
		String viewResult = "/views/private/gestionPanel";
		return viewResult;
	}

	@PostMapping("/updateCarouselImages")
	@ResponseBody
	public String updateCarouselImages(Model model, @RequestParam Map<String, String> params,
			@RequestParam("file") MultipartFile imageEditCarousel) throws Exception {

		LOGGER.info("AdminController updateCarouselImages .- Inicio");

		String viewResult = "/views/common/ErrorAjax";

		try {

			if (imageEditCarousel != null && !imageEditCarousel.isEmpty() && params != null && !params.isEmpty()) {

				Path dirImages = Paths.get("src//main//resources//static//images");
				String rutaAbsoluta = dirImages.toFile().getAbsolutePath();

				byte[] bytesImg = imageEditCarousel.getBytes();
				int numImage = Integer.parseInt(params.get("numImageCarousel"));

				if (numImage == 1) {
					Path rutaCompleta = Paths.get(rutaAbsoluta + "//image1Carousel.png");
					Files.write(rutaCompleta, bytesImg);
				} else if (numImage == 2) {
					Path rutaCompleta = Paths.get(rutaAbsoluta + "//image2Carousel.png");
					Files.write(rutaCompleta, bytesImg);
				} else {
					Path rutaCompleta = Paths.get(rutaAbsoluta + "//image3Carousel.png");
					Files.write(rutaCompleta, bytesImg);
				}

				viewResult = "/views/private/gestionHome";

			} else {
				LOGGER.error("AdminController updateCarouselImages .- Error: Parámetros de entrada nulos");
			}

		} catch (Exception e) {
			LOGGER.error("AdminController updateCarouselImages .- Error no controlado al guardar la image");
			throw e;
		}

		LOGGER.info("AdminController updateCarouselImages .- Fin");

		return viewResult;
	}

	@PostMapping("/getDatatableCategory")
	public String getDatatableCategory(Model model, String numCat, String inputUser) throws Exception {

		LOGGER.info("AdminController getDatatableCategory .- Inicio");

		String viewResult = "/views/common/ErrorAjax";

		if (numCat == null) {
			numCat = (String) model.getAttribute("catPublication");
		}

		try {

			if (numCat != null) {
				switch (numCat) {

				case "0":
					viewResult = "/views/private/gestionHome";
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

				case "10":
					AdminDto adminDto = adminService.getAdminDataByUsername(inputUser);
					model.addAttribute("adminsFormDto", new AdminsFormDto());
					model.addAttribute("adminDto", adminDto);
					viewResult = "/views/private/datatableAdmins";
					break;

				default:
					viewResult = "/views/common/ErrorAjax";
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

		LOGGER.info("AdminController getDatatableCategory .- Fin");

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
	public ResponseEntity addNewsData(Model model, @RequestParam Map<String, String> params,
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
				LOGGER.error("AdminController addNewsData .- Error: Parámetros de entrada nulos");
			}

		} catch (Exception e) {
			LOGGER.error("AdminController addNewsData .- Error no controlado al dar de alta la noticia");
			return ResponseEntity.badRequest().body(e.getMessage());
		}

		LOGGER.info("AdminController addNewsData .- Fin");
		return ResponseEntity.ok("success");

	}

	@PostMapping("/updateNewsData")
	@ResponseBody
	public ResponseEntity updateNewsData(Model model, @RequestParam Map<String, String> params) throws Exception {

		LOGGER.info("AdminController updateNewsData .- Inicio");

		try {

			if (params != null && !params.isEmpty()) {
				newsService.updateNews(params);
			} else {
				LOGGER.error("AdminController updateNewsData .- Error: Parámetros de entrada nulos");
			}

		} catch (Exception e) {
			LOGGER.error("AdminController updateNewsData .- Error no controlado al actualizar la noticia");
			return ResponseEntity.badRequest().body(e.getMessage());
		}

		LOGGER.info("AdminController updateNewsData .- Fin");
		return ResponseEntity.ok("success");
	}

	@PostMapping("/updateImageNewsData")
	@ResponseBody
	public void updateImageNewsData(Model model, @RequestParam Map<String, String> params,
			@RequestParam("file") MultipartFile imageNews) throws Exception {

		LOGGER.info("AdminController updateImageNewsData .- Inicio");

		try {

			if (imageNews != null && !imageNews.isEmpty() && params != null && !params.isEmpty()) {

				News n = newsService.updateImageNews(params, imageNews.getOriginalFilename());

				Path dirImages = Paths.get("src//main//resources//static//images");
				String rutaAbsoluta = dirImages.toFile().getAbsolutePath();

				byte[] bytesImg = imageNews.getBytes();
				Path rutaCompleta = Paths.get(rutaAbsoluta + "//" + n.getImageNews());
				Files.write(rutaCompleta, bytesImg);

			} else {
				LOGGER.error("AdminController updateImageNewsData .- Error: Parámetros de entrada nulos");

			}

		} catch (Exception e) {
			LOGGER.error("AdminController updateImageNewsData .- Error no controlado al actualizar la noticia");
			throw e;
		}

		LOGGER.info("AdminController updateImageNewsData .- Fin");
	}

	@PostMapping("/deleteNewsData")
	@ResponseBody
	public void deleteNewsData(Model model, @RequestParam Map<String, String> params) throws Exception {

		LOGGER.info("AdminController deleteNewsData .- Inicio");

		try {

			if (params != null && !params.isEmpty()) {
				newsService.deleteNews(params);
			} else {
				LOGGER.error("AdminController deleteNewsData .- Error: Parámetros nulos");
			}

		} catch (Exception e) {
			LOGGER.error("AdminController deleteNewsData .- Error no controlado al eliminar la noticia");
			throw e;
		}

		LOGGER.info("AdminController deleteNewsData .- Fin");
	}

	@GetMapping("/generateExcel/news")
	public void exportToExcelNews(HttpServletResponse response) throws Exception {

		LOGGER.info("AdminController exportToExcelNews .- Inicio");

		List<NewsDatatableDto> listaNews = null;

		try {

			response.setHeader("Content-Disposition", "attachment; filename=\"dataNews.xlsx\"");

			listaNews = newsService.getAllNewsData();

			if (listaNews != null && !listaNews.isEmpty()) {
				GeneratorExcels generatorExcel = new GeneratorExcels();
				generatorExcel.exportExcelNews(response, listaNews);
			} else {
				LOGGER.error("AdminController exportToExcelNews .- Error: No existen noticias registradas");
			}

		} catch (Exception e) {
			LOGGER.error("AdminController exportToExcelNews .- Error no controlado al realizar la exportación a Excel");
			throw e;
		}

		LOGGER.info("AdminController exportToExcelNews .- Fin");
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
	public ResponseEntity addProCatData(Model model, @RequestParam Map<String, String> params) throws Exception {

		LOGGER.info("AdminController addProCatData .- Inicio");

		try {

			if (params != null && !params.isEmpty()) {
				proCatService.addProCat(params);
			} else {
				LOGGER.error("AdminController addProCatData .- Error: Parámetros de entrada nulos");
			}

		} catch (Exception e) {
			LOGGER.error(
					"AdminController addProCatData .- Error no controlado al dar de alta la categoría profesional");
			return ResponseEntity.badRequest().body(e.getMessage());
		}

		LOGGER.info("AdminController addProCatData .- Fin");
		return ResponseEntity.ok("success");

	}

	@PostMapping("/updateProCatData")
	@ResponseBody
	public ResponseEntity updateProCatData(Model model, @RequestParam Map<String, String> params) throws Exception {

		LOGGER.info("AdminController updateProCatData .- Inicio");

		try {

			if (params != null && !params.isEmpty()) {
				proCatService.updateProCat(params);
			} else {
				LOGGER.error("AdminController updateProCatData .- Error: Parámetros de entrada nulos");
			}

		} catch (Exception e) {
			LOGGER.error(
					"AdminController updateProCatData .- Error no controlado al actualizar la categoría profesional");
			return ResponseEntity.badRequest().body(e.getMessage());
		}

		LOGGER.info("AdminController updateProCatData .- Fin");
		return ResponseEntity.ok("success");
	}

	@PostMapping("/deleteProCatData")
	@ResponseBody
	public void deleteProCatData(Model model, @RequestParam Map<String, String> params) throws Exception {

		LOGGER.info("AdminController deleteProCatData .- Inicio");

		try {

			if (params != null && !params.isEmpty()) {
				proCatService.deleteProCat(params);
			} else {
				LOGGER.error("AdminController deleteProCatData .- Error: Parámetros de entrada nulos");
			}

		} catch (Exception e) {
			LOGGER.error(
					"AdminController deleteProCatData .- Error no controlado al eliminar la categoría profesional");
			throw e;
		}

		LOGGER.info("AdminController deleteProCatData .- Fin");
	}

	@GetMapping("/generateExcel/proCat")
	public void exportToExcelProCat(HttpServletResponse response) throws Exception {

		LOGGER.info("AdminController exportToExcelProCat .- Inicio");

		List<ProCatDatatableDto> listaProCat = null;

		try {

			response.setHeader("Content-Disposition", "attachment; filename=\"dataProCat.xlsx\"");

			listaProCat = proCatService.getAllProCatData();

			if (listaProCat != null && !listaProCat.isEmpty()) {
				GeneratorExcels generatorExcelProCat = new GeneratorExcels();
				generatorExcelProCat.exportExcelProCat(response, listaProCat);
			} else {
				LOGGER.error(
						"AdminController exportToExcelProCat .- Error: No existen categorías profesionales registradas");
			}

		} catch (Exception e) {
			LOGGER.error(
					"AdminController exportToExcelProCat .- Error no controlado al realizar la exportación a Excel");

			throw e;
		}

		LOGGER.info("AdminController exportToExcelProCat .- Fin");
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
	public ResponseEntity addTechCatData(Model model, @RequestParam Map<String, String> params) throws Exception {

		LOGGER.info("AdminController addTechCatData .- Inicio");

		try {

			if (params != null && !params.isEmpty()) {
				techCatService.addTechCat(params);
			} else {
				LOGGER.error("AdminController addTechCatData .- Error: Parámetros de entrada nulos");
			}

		} catch (Exception e) {
			LOGGER.error("AdminController addTechCatData .- Error no controlado al dar de alta la categoría técnica");
			return ResponseEntity.badRequest().body(e.getMessage());
		}

		LOGGER.info("AdminController addTechCatData .- Fin");
		return ResponseEntity.ok("success");

	}

	@PostMapping("/updateTechCatData")
	@ResponseBody
	public ResponseEntity updateTchCatData(Model model, @RequestParam Map<String, String> params) throws Exception {

		LOGGER.info("AdminController updateTechCatData .- Inicio");

		try {

			if (params != null && !params.isEmpty()) {
				techCatService.updateTechCat(params);
			} else {
				LOGGER.error("AdminController updateTechCatData .- Error: Parámetros de entrada nulos");
			}

		} catch (Exception e) {
			LOGGER.error("AdminController updateTechCatData .- Error no controlado al actualizar la categoría técnica");
			return ResponseEntity.badRequest().body(e.getMessage());
		}

		LOGGER.info("AdminController updateTechCatData .- Fin");
		return ResponseEntity.ok("success");
	}

	@PostMapping("/deleteTechCatData")
	@ResponseBody
	public void deleteTechCatData(Model model, @RequestParam Map<String, String> params) throws Exception {

		LOGGER.info("AdminController deleteTechCatData .- Inicio");

		try {

			if (params != null && !params.isEmpty()) {

				techCatService.deleteTechCat(params);

			} else {
				LOGGER.error("AdminController deleteTechCatData .- Error: Parámetros de entrada nulos");

			}

		} catch (Exception e) {
			LOGGER.error("AdminController deleteTechCatData .- Error no controlado al eliminar la categoría técnica");
			throw e;
		}

		LOGGER.info("AdminController deleteTechCatData .- Fin");
	}

	@GetMapping("/generateExcel/techCat")
	public void exportToExcelTechCat(HttpServletResponse response) throws Exception {

		LOGGER.info("AdminController exportToExcelTechCat .- Inicio");

		List<TechCatDatatableDto> listaTechCat = null;

		try {

			response.setHeader("Content-Disposition", "attachment; filename=\"dataTechCat.xlsx\"");
			listaTechCat = techCatService.getAllTechCatData();

			if (listaTechCat != null && !listaTechCat.isEmpty()) {
				GeneratorExcels generatorExcel = new GeneratorExcels();
				generatorExcel.exportExcelTechCat(response, listaTechCat);
			} else {
				LOGGER.error(
						"AdminController exportToExcelTechCat .- Error: No existen categorías técnicas registradas");
			}

		} catch (Exception e) {
			LOGGER.error("AdminController exportToExcelTechCat .- Error no controlado al hacer la exportación a Excel");
			throw e;
		}

		LOGGER.info("AdminController exportToExcelTechCat .- Fin");
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
	public ResponseEntity addMembersData(Model model, @RequestParam Map<String, String> params,
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
				LOGGER.error("AdminController addMembersData .- Error: Parámetros de entrada nulos");

			}

		} catch (Exception e) {
			LOGGER.error("AdminController addMembersData .- Error no controlado al dar de alta el miembro");
			return ResponseEntity.badRequest().body(e.getMessage());
		}

		LOGGER.info("AdminController addMembersData .- Fin");
		return ResponseEntity.ok("success");

	}

	@PostMapping("/updateMembersData")
	@ResponseBody
	public ResponseEntity updateMembersData(Model model, @RequestParam Map<String, String> params) throws Exception {

		LOGGER.info("AdminController updateMembersData .- Inicio");

		try {

			if (params != null && !params.isEmpty()) {
				membersService.updateMembers(params);
			} else {
				LOGGER.error("AdminController updateMembersData .- Error: Parámetros nulos");
			}

		} catch (Exception e) {
			LOGGER.error("AdminController updateMembersData .- Error no controlado al actualizar el miembro");
			return ResponseEntity.badRequest().body(e.getMessage());
		}

		LOGGER.info("AdminController updateMembersData .- Fin");
		return ResponseEntity.ok("success");
	}

	@PostMapping("/updatePhotoMembersData")
	@ResponseBody
	public void updatePhotoMembersData(Model model, @RequestParam Map<String, String> params,
			@RequestParam("file") MultipartFile photoFacility) throws Exception {

		LOGGER.info("AdminController updatePhotoMembersData .- Inicio");

		try {

			if (photoFacility != null && !photoFacility.isEmpty() && params != null && !params.isEmpty()) {

				Member m = membersService.updatePhotoMembers(params, photoFacility.getOriginalFilename());

				Path dirImages = Paths.get("src//main//resources//static//images");
				String rutaAbsoluta = dirImages.toFile().getAbsolutePath();

				byte[] bytesImg = photoFacility.getBytes();
				Path rutaCompleta = Paths.get(rutaAbsoluta + "//" + m.getPhotoMember());
				Files.write(rutaCompleta, bytesImg);

			} else {
				LOGGER.error("AdminController updatePhotoMembersData .- Error: Parámetros de entrada nulos");
			}

		} catch (Exception e) {
			LOGGER.error("AdminController updatePhotoMembersData .- Error no controlado al actualizar el miembro");
			throw e;
		}

		LOGGER.info("AdminController updatePhotoMembersData .- Fin");
	}

	@PostMapping("/deleteMembersData")
	@ResponseBody
	public void deleteMembersData(Model model, @RequestParam Map<String, String> params) throws Exception {

		LOGGER.info("AdminController deleteMembersData .- Inicio");

		try {

			if (params != null && !params.isEmpty()) {
				membersService.deleteMembers(params);
			} else {
				LOGGER.error("AdminController deleteMembersData .- Error: Parámetros de entrada nulos");
			}

		} catch (Exception e) {
			LOGGER.error("AdminController deleteMembersData .- Error no controlado al eliminar el miembro");
			throw e;
		}

		LOGGER.info("AdminController deleteMembersData .- Fin");
	}

	@GetMapping("/generateExcel/members")
	public void exportToExcelMembers(HttpServletResponse response) throws Exception {

		LOGGER.info("AdminController exportToExcelMembers .- Inicio");

		List<MembersDatatableDto> listaMembers = null;
		try {
			response.setHeader("Content-Disposition", "attachment; filename=\"dataMembers.xlsx\"");

			listaMembers = membersService.getAllMembersData();

			if (listaMembers != null && !listaMembers.isEmpty()) {
				GeneratorExcels generatorExcel = new GeneratorExcels();

				generatorExcel.exportExcelMembers(response, listaMembers);
			} else {
				LOGGER.error("AdminController exportToExcelMembers .- Error: No existen miembros registrados");

			}

		} catch (Exception e) {
			LOGGER.error(
					"AdminController exportToExcelMembers .- Error no controlado al realizar la exportación a Excel");
			throw e;
		}

		LOGGER.info("AdminController exportToExcelMembers .- Fin");
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
				LOGGER.error("AdminController addFacilitiesData .- Error: Parámetros de entrada nulos");
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

		LOGGER.info("AdminController updateFacilitiesData .- Inicio");

		try {

			if (params != null && !params.isEmpty()) {
				facilitiesService.updateFacilities(params);
			} else {
				LOGGER.error("AdminController updateFacilitiesData .- Error: Parámetros nulos");
			}

		} catch (Exception e) {
			LOGGER.error("AdminController updateFacilitiesData .- Error no controlado al actualizar el equipo");
			throw e;
		}

		LOGGER.info("AdminController updateFacilitiesData .- Fin");
	}

	@PostMapping("/updatePhotoFacilitiesData")
	@ResponseBody
	public void updatePhotoFacilitiesData(Model model, @RequestParam Map<String, String> params,
			@RequestParam("file") MultipartFile photoFacility) throws Exception {

		LOGGER.info("AdminController updatePhotoFacilitiesData .- Inicio");

		try {

			if (photoFacility != null && !photoFacility.isEmpty() && params != null && !params.isEmpty()) {

				Facility f = facilitiesService.updatePhotoFacilities(params, photoFacility.getOriginalFilename());

				Path dirImages = Paths.get("src//main//resources//static//images");
				String rutaAbsoluta = dirImages.toFile().getAbsolutePath();

				byte[] bytesImg = photoFacility.getBytes();
				Path rutaCompleta = Paths.get(rutaAbsoluta + "//" + f.getPhotoFacility());
				Files.write(rutaCompleta, bytesImg);

			} else {
				LOGGER.error("AdminController updatePhotoFacilitiesData .- Error: Parámetros de entrada nulos");
			}

		} catch (Exception e) {
			LOGGER.error("AdminController updatePhotoFacilitiesData .- Error no controlado al actualizar el equipo");
			throw e;
		}

		LOGGER.info("AdminController updatePhotoFacilitiesData .- Fin");
	}

	@PostMapping("/deleteFacilitiesData")
	@ResponseBody
	public void deleteFacilitiesData(Model model, @RequestParam Map<String, String> params) throws Exception {

		LOGGER.info("AdminController deleteFacilitiesData .- Inicio");

		try {

			if (params != null && !params.isEmpty()) {
				facilitiesService.deleteFacilities(params);
			} else {
				LOGGER.error("AdminController deleteFacilitiesData .- Error: Parámetros de entrada nulos");
			}

		} catch (Exception e) {
			LOGGER.error("AdminController deleteFacilitiesData .- Error no controlado al eliminar el equipo");
			throw e;
		}

		LOGGER.info("AdminController deleteFacilitiesData .- Fin");
	}

	@GetMapping("/generateExcel/facilities")
	public void exportToExcelFacilities(HttpServletResponse response) throws Exception {

		LOGGER.info("AdminController exportToExcelFacilities .- Inicio");

		List<FacilitiesDatatableDto> listaFacilities = null;
		try {
			response.setHeader("Content-Disposition", "attachment; filename=\"dataFacilities.xlsx\"");

			listaFacilities = facilitiesService.getAllFacilitiesData();

			if (listaFacilities != null && !listaFacilities.isEmpty()) {
				GeneratorExcels generatorExcel = new GeneratorExcels();
				generatorExcel.exportExcelFacilities(response, listaFacilities);
			} else {
				LOGGER.error("AdminController exportToExcelFacilities .- Error: No existen equipos registrados");
			}

		} catch (Exception e) {
			LOGGER.error(
					"AdminController exportToExcelFacilities .- Error no controlado al realizar la exportación a Excel");
			throw e;
		}

		LOGGER.info("AdminController exportToExcelFacilities .- Fin");
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
				publicationsService.addPublications(publicationsFormDto);
				redirectAttributes.addFlashAttribute("catPublication", true);

			} else {
				LOGGER.error("AdminController addPublicationsData .- Error: Parámetros de entrada nulos");
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
				publicationsService.updatePublications(publicationsFormDto);
				redirectAttributes.addFlashAttribute("catPublication", true);
			} else {
				LOGGER.error("AdminController updatePublicationsData .- Error: Parámetros de entrada nulos");
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

		LOGGER.info("AdminController deletePublicationsData .- Inicio");

		try {

			if (params != null && !params.isEmpty()) {
				publicationsService.deletePublications(params);
			} else {
				LOGGER.error("AdminController deletePublicationsData .- Error: Parámetros de entrada nulos");
			}

		} catch (Exception e) {
			LOGGER.error("AdminController deletePublicationsData .- Error no controlado al eliminar la publicación");
			throw e;
		}

		LOGGER.info("AdminController deletePublicationsData .- Fin");

	}

	@GetMapping("/generateExcel/publications")
	public void exportToExcelPublications(HttpServletResponse response) throws Exception {

		LOGGER.info("AdminController exportToExcelPublications .- Fin");

		List<PublicationsDatatableDto> listaPublications = null;

		try {

			response.setHeader("Content-Disposition", "attachment; filename=\"dataPublications.xlsx\"");
			listaPublications = publicationsService.getAllPublicationsData();

			if (listaPublications != null && !listaPublications.isEmpty()) {
				GeneratorExcels generatorExcel = new GeneratorExcels();
				generatorExcel.exportExcelPublications(response, listaPublications);
			} else {
				LOGGER.error(
						"AdminController exportToExcelPublications .- Error: No existen publicaciones registradas");
			}

		} catch (Exception e) {
			LOGGER.error(
					"AdminController exportToExcelPublications .- Error no controlado al realizar la exportación a Excel");
			throw e;
		}

		LOGGER.info("AdminController exportToExcelPublications .- Fin");
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
	public ResponseEntity addProjectsData(Model model, @RequestParam Map<String, String> params,
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
				LOGGER.error("AdminController addProjectsData .- Error: Parámetros de entrada nulos");
			}

		} catch (Exception e) {
			LOGGER.error("AdminController addProjectsData .- Error no controlado al dar de alta el proyecto");
			return ResponseEntity.badRequest().body(e.getMessage());
		}

		LOGGER.info("AdminController addProjectsData .- Fin");
		return ResponseEntity.ok("success");
	}

	@PostMapping("/updateProjectsData")
	@ResponseBody
	public ResponseEntity updateProjectsData(Model model, @RequestParam Map<String, String> params) throws Exception {

		LOGGER.info("AdminController updateProjectsData .- Inicio");

		try {

			if (params != null && !params.isEmpty()) {
				projectsService.updateProjects(params);
			} else {
				LOGGER.error("AdminController updateProjectsData .- Error: Parámetros de entrada nulos");
			}

		} catch (Exception e) {
			LOGGER.error("AdminController updateProjectsData .- Error no controlado al actualizar el proyecto");
			return ResponseEntity.badRequest().body(e.getMessage());
		}

		LOGGER.info("AdminController updateProjectsData .- Fin");
		return ResponseEntity.ok("success");
	}

	@PostMapping("/updateImageProjectsData")
	@ResponseBody
	public void updateImageProjectsData(Model model, @RequestParam Map<String, String> params,
			@RequestParam("file") MultipartFile imageProjects) throws Exception {

		LOGGER.info("AdminController updateImageProjectsData .- Inicio");

		try {

			if (imageProjects != null && !imageProjects.isEmpty() && params != null && !params.isEmpty()) {

				Project p = projectsService.updateImageProjects(params, imageProjects.getOriginalFilename());

				Path dirImages = Paths.get("src//main//resources//static//images");
				String rutaAbsoluta = dirImages.toFile().getAbsolutePath();

				byte[] bytesImg = imageProjects.getBytes();
				Path rutaCompleta = Paths.get(rutaAbsoluta + "//" + p.getImageProject());
				Files.write(rutaCompleta, bytesImg);

			} else {
				LOGGER.error("AdminController updateImageProjectsData .- Error: Parámetros nulos");
			}

		} catch (Exception e) {
			LOGGER.error("AdminController updateImageProjectsData .- Error no controlado al actualizar el proyecto");
			throw e;
		}

		LOGGER.info("AdminController updateImageProjectsData .- Fin");
	}

	@PostMapping("/deleteProjectsData")
	@ResponseBody
	public void deleteProjectsData(Model model, @RequestParam Map<String, String> params) throws Exception {

		LOGGER.info("AdminController deleteProjectsData .- Inicio");

		try {

			if (params != null && !params.isEmpty()) {
				projectsService.deleteProjects(params);
			} else {
				LOGGER.error("AdminController deleteProjectsData .- Error: Parámetros nulos");
			}

		} catch (Exception e) {
			LOGGER.error("AdminController deleteProjectsData .- Error no controlado al eliminar el proyecto");
			throw e;
		}

		LOGGER.info("AdminController deleteProjectsData .- Fin");
	}

	@GetMapping("/generateExcel/projects")
	public void exportToExcelProjects(HttpServletResponse response) throws Exception {

		LOGGER.info("AdminController exportToExcelProjects .- Fin");

		List<ProjectsDatatableDto> listaProjects = null;

		try {

			response.setHeader("Content-Disposition", "attachment; filename=\"dataProjects.xlsx\"");
			listaProjects = projectsService.getAllProjectsData();

			if (listaProjects != null && !listaProjects.isEmpty()) {
				GeneratorExcels generatorExcel = new GeneratorExcels();
				generatorExcel.exportExcelProjects(response, listaProjects);
			} else {
				LOGGER.error("AdminController exportToExcelProjects .- Error: No existen proyectos registrados");
			}

		} catch (Exception e) {
			LOGGER.error(
					"AdminController exportToExcelProjects .- Error no controlado al realizar la exportación a Excel");
			throw e;
		}

		LOGGER.info("AdminController exportToExcelProjects .- Fin");
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
				LOGGER.error("AdminController addThesisData .- Error: Parámetros de entrada nulos");
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

		LOGGER.info("AdminController updateThesisData .- Inicio");

		try {

			if (params != null && !params.isEmpty()) {
				thesisService.updateThesis(params);
			} else {
				LOGGER.error("AdminController updateThesisData .- Error: Parámetros de entrada nulos");
			}

		} catch (Exception e) {
			LOGGER.error("AdminController updateThesisData .- Error no controlado al actualizar la tesis");
			throw e;
		}

		LOGGER.info("AdminController updateThesisData .- Fin");
	}

	@PostMapping("/updateCoverPageThesisData")
	@ResponseBody
	public void updateCoverPageThesisData(Model model, @RequestParam Map<String, String> params,
			@RequestParam("file") MultipartFile coverPageThesis) throws Exception {

		LOGGER.info("AdminController updateCoverPageThesisData .- Inicio");

		try {

			if (coverPageThesis != null && !coverPageThesis.isEmpty() && params != null && !params.isEmpty()) {

				Thesis t = thesisService.updateCoverPageThesis(params, coverPageThesis.getOriginalFilename());

				Path dirImages = Paths.get("src//main//resources//static//images");
				String rutaAbsoluta = dirImages.toFile().getAbsolutePath();

				byte[] bytesImg = coverPageThesis.getBytes();
				Path rutaCompleta = Paths.get(rutaAbsoluta + "//" + t.getCoverPageThesis());
				Files.write(rutaCompleta, bytesImg);

			} else {
				LOGGER.error("AdminController updateCoverPageThesisData .- Error: Parámetros nulos");
			}

		} catch (Exception e) {
			LOGGER.error("AdminController updateCoverPageThesisData .- Error no controlado al actualizar la tesis");
			throw e;
		}

		LOGGER.info("AdminController updateCoverPageThesisData .- Fin");
	}

	@PostMapping("/deleteThesisData")
	@ResponseBody
	public void deleteThesisData(Model model, @RequestParam Map<String, String> params) throws Exception {

		LOGGER.info("AdminController deleteThesisData .- Inicio");

		try {

			if (params != null && !params.isEmpty()) {
				thesisService.deleteThesis(params);
			} else {
				LOGGER.error("AdminController deleteThesisData .- Error: Parámetros de entrada nulos");
			}

		} catch (Exception e) {
			LOGGER.error("AdminController deleteThesisData .- Error no controlado al eliminar la tesis");
			throw e;
		}

		LOGGER.info("AdminController deleteThesisData .- Fin");
	}

	@GetMapping("/generateExcel/thesis")
	public void exportToExcelThesis(HttpServletResponse response) throws Exception {

		LOGGER.info("AdminController exportToExcelThesis .- Fin");

		List<ThesisDatatableDto> listaThesis = null;

		try {

			response.setHeader("Content-Disposition", "attachment; filename=\"dataThesis.xlsx\"");
			listaThesis = thesisService.getAllThesisData();

			if (listaThesis != null && !listaThesis.isEmpty()) {
				GeneratorExcels generatorExcel = new GeneratorExcels();
				generatorExcel.exportExcelThesis(response, listaThesis);
			} else {
				LOGGER.error("AdminController exportToExcelThesis .- Error: No existen tesis registradas");
			}

		} catch (Exception e) {
			LOGGER.error(
					"AdminController exportToExcelThesis .- Error no controlado al realizar la exportación a Excel");
			throw e;
		}

		LOGGER.info("AdminController exportToExcelThesis .- Fin");
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
	public ResponseEntity addLinksData(Model model, @RequestParam Map<String, String> params,
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
				LOGGER.error("AdminController addLinksData .- Error: Parámetros de entrada nulos");

			}

		} catch (Exception e) {
			LOGGER.error("AdminController addLinksData .- Error no controlado al dar de alta el link");
			return ResponseEntity.badRequest().body(e.getMessage());
		}

		LOGGER.info("AdminController addLinksData .- Fin");
		return ResponseEntity.ok("success");

	}

	@PostMapping("/updateLinksData")
	@ResponseBody
	public ResponseEntity updateLinksData(Model model, @RequestParam Map<String, String> params) throws Exception {

		LOGGER.info("AdminController updateLinksData .- Inicio");

		try {

			if (params != null && !params.isEmpty()) {
				linksService.updateLinks(params);
			} else {
				LOGGER.error("AdminController updateLinksData .- Error: Parámetros de entrada nulos");
			}

		} catch (Exception e) {
			LOGGER.error("AdminController updateLinksData .- Error no controlado al actualizar el link");
			return ResponseEntity.badRequest().body(e.getMessage());
		}

		LOGGER.info("AdminController updateLinksData .- Fin");
		return ResponseEntity.ok("success");
	}

	@PostMapping("/updateImageLinksData")
	@ResponseBody
	public void updateImageLinksData(Model model, @RequestParam Map<String, String> params,
			@RequestParam("file") MultipartFile imageLinks) throws Exception {

		LOGGER.info("AdminController updateImageLinksData .- Inicio");

		try {

			if (imageLinks != null && !imageLinks.isEmpty() && params != null && !params.isEmpty()) {

				Link l = linksService.updateImageLinks(params, imageLinks.getOriginalFilename());

				Path dirImages = Paths.get("src//main//resources//static//images");
				String rutaAbsoluta = dirImages.toFile().getAbsolutePath();

				byte[] bytesImg = imageLinks.getBytes();
				Path rutaCompleta = Paths.get(rutaAbsoluta + "//" + l.getImageLink());
				Files.write(rutaCompleta, bytesImg);

			} else {
				LOGGER.error("AdminController updateImageLinksData .- Error: Parámetros de entrada nulos");

			}

		} catch (Exception e) {
			LOGGER.error("AdminController updateImageLinksData .- Error no controlado al actualizar el link");
			throw e;
		}

		LOGGER.info("AdminController updateImageLinksData .- Fin");
	}

	@PostMapping("/deleteLinksData")
	@ResponseBody
	public void deleteLinksData(Model model, @RequestParam Map<String, String> params) throws Exception {

		LOGGER.info("AdminController deleteLinksData .- Inicio");

		try {

			if (params != null && !params.isEmpty()) {
				linksService.deleteLinks(params);
			} else {
				LOGGER.error("AdminController deleteLinksData .- Error: Parámetros de entrada nulos");
			}

		} catch (Exception e) {
			LOGGER.error("AdminController deleteLinksData .- Error no controlado al eliminar el link");
			throw e;
		}

		LOGGER.info("AdminController deleteLinksData .- Fin");
	}

	@GetMapping("/generateExcel/links")
	public void exportToExcelLinks(HttpServletResponse response) throws Exception {

		LOGGER.info("AdminController exportToExcelLinks .- Inicio");

		List<LinksDatatableDto> listaLinks = null;
		try {
			response.setHeader("Content-Disposition", "attachment; filename=\"dataLinks.xlsx\"");

			listaLinks = linksService.getAllLinksData();

			if (listaLinks != null && !listaLinks.isEmpty()) {
				GeneratorExcels generatorExcel = new GeneratorExcels();

				generatorExcel.exportExcelLinks(response, listaLinks);
			} else {
				LOGGER.error("AdminController exportToExcelLinks .- Error: No existen links registrados");

			}

		} catch (Exception e) {
			LOGGER.error(
					"AdminController exportToExcelLinks .- Error no controlado al realizar la Exportación a Excel");
			throw e;
		}

		LOGGER.info("AdminController exportToExcelLinks .- Fin");
	}

	@GetMapping("/getAdminsData")
	@ResponseBody
	public List<AdminsDatatableDto> getAdminsData(Model model) throws Exception {

		LOGGER.info("AdminController getAdminsData .- Inicio");

		List<AdminsDatatableDto> listaAdminsDatatableDto = null;

		try {

			listaAdminsDatatableDto = adminService.getAllAdminsData();

		} catch (Exception e) {
			LOGGER.error(
					"AdminController getAdminsData .- Error no controlado al recuperar los datos de los administradores");
			throw e;
		}

		LOGGER.info("AdminController getAdminsData .- Fin");

		return listaAdminsDatatableDto;

	}

	@PostMapping("/addAdminsData")
	@ResponseBody
	public ResponseEntity addAdminsData(Model model, @RequestParam Map<String, String> params) throws Exception {

		LOGGER.info("AdminController addAdminsData .- Inicio");

		try {

			if (params != null && !params.isEmpty()) {
				adminService.addAdmin(params);
			} else {
				LOGGER.error("AdminController addAdminsData .- Error: Parámetros de entrada nulos");
			}

		} catch (Exception e) {
			LOGGER.error("AdminController addAdminsData .- Error no controlado al dar de alta el administrador");
			return ResponseEntity.badRequest().body(e.getMessage());
		}

		LOGGER.info("AdminController addAdminsData .- Fin");
		return ResponseEntity.ok("success");

	}

	@PostMapping("/updateDataAdmins")
	@ResponseBody
	public ResponseEntity updateDataAdmins(Model model, @RequestParam Map<String, String> params) throws Exception {

		LOGGER.info("AdminController updateDataAdmins .- Inicio");

		try {

			if (params != null && !params.isEmpty()) {
				adminService.updateDataAdmin(params);
			} else {
				LOGGER.error("AdminController updateDataAdmins .- Error: Parámetros de entrada nulos");
			}

		} catch (Exception e) {
			LOGGER.error(
					"AdminController updateDataAdmins .- Error no controlado al actualizar los datos personales del administrador");

			return ResponseEntity.badRequest().body(e.getMessage());
		}

		LOGGER.info("AdminController updateDataAdmins .- Fin");

		return ResponseEntity.ok("success");

	}

	@PostMapping("/changePwdAdmins")
	@ResponseBody
	public ResponseEntity changePwdAdmins(Model model, @RequestParam Map<String, String> params) throws Exception {

		LOGGER.info("AdminController changePwdAdmins .- Inicio");

		try {

			if (params != null && !params.isEmpty()) {
				adminService.changePwdAdmins(params);
			} else {
				LOGGER.error("AdminController changePwdAdmins .- Error: Parámetros de entrada nulos");
			}

		} catch (Exception e) {
			LOGGER.error(
					"AdminController changePwdAdmins .- Error no controlado al actualizar la contraseña del administrador");

			return ResponseEntity.badRequest().body(e.getMessage());

		}

		LOGGER.info("AdminController changePwdAdmins .- Fin");
		return ResponseEntity.ok("success");
	}
	
	@PostMapping("/deleteAdminsData")
	public String deleteAdminsData(Model model, @RequestParam Map<String, String> params) throws Exception {

		LOGGER.info("AdminController deleteAdminsData .- Inicio");

		try {

			if (params != null && !params.isEmpty()) {
				adminService.deleteAdmins(params);
			} else {
				LOGGER.error("AdminController deleteAdminsData .- Error: Parámetros de entrada nulos");
			}

		} catch (Exception e) {
			LOGGER.error("AdminController deleteAdminsData .- Error no controlado al eliminar el link");
			throw e;
		}

		LOGGER.info("AdminController deleteAdminsData .- Fin");
		return "redirect:/logout";
	}
	
	@GetMapping("/generateExcel/admins")
	public void exportToExcelAdmins(HttpServletResponse response) throws Exception {

		LOGGER.info("AdminController exportToExcelAdmins .- Inicio");

		List<AdminsDatatableDto> listaAdmins = null;
		try {
			response.setHeader("Content-Disposition", "attachment; filename=\"dataAdmins.xlsx\"");

			listaAdmins = adminService.getAllAdminsData();

			if (listaAdmins != null && !listaAdmins.isEmpty()) {
				GeneratorExcels generatorExcel = new GeneratorExcels();

				generatorExcel.exportExcelAdmins(response, listaAdmins);
			} else {
				LOGGER.error("AdminController exportToExcelAdmins .- Error: No existen administradores registrados");

			}

		} catch (Exception e) {
			LOGGER.error(
					"AdminController exportToExcelAdmins .- Error no controlado al realizar la Exportación a Excel");
			throw e;
		}

		LOGGER.info("AdminController exportToExcelAdmins .- Fin");
	}

}
