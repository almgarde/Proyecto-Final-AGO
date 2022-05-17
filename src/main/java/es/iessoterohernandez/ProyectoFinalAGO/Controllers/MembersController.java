package es.iessoterohernandez.ProyectoFinalAGO.Controllers;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import es.iessoterohernandez.ProyectoFinalAGO.Persistence.Entity.ProfessionalCategory;
import es.iessoterohernandez.ProyectoFinalAGO.Services.MembersServiceI;
import es.iessoterohernandez.ProyectoFinalAGO.Services.ProCatServiceI;
import es.iessoterohernandez.ProyectoFinalAGO.Services.Dto.MembersDto;
import es.iessoterohernandez.ProyectoFinalAGO.Services.Dto.ProCatDto;
import es.iessoterohernandez.ProyectoFinalAGO.Services.Dto.ProCatFormDto;
import es.iessoterohernandez.ProyectoFinalAGO.Services.Dto.ProCatMembersDto;

/**
 * Controlador. Entidad: Miembros
 * 
 * @author agadelao
 *
 */
@Controller
public class MembersController {

	/** Logger */
	final static Logger LOGGER = LoggerFactory.getLogger(MembersController.class);

	@Autowired
	MembersServiceI membersService;

	@Autowired
	ProCatServiceI proCatService;

	@GetMapping("/members")
	public String getMembers(Model model) throws Exception {

		LOGGER.info("MembersController getMembers .- Inicio");

		String viewResult = "/views/common/Errors";

		try {
			// Para la lista del filtro - Se muestran entonces todas las categorías,
			// incluidas las que no tienen miembros
			final List<ProCatDto> listaProCatDto = proCatService.getAllProCatActive();

			// Muestra los miembros de aquellas categorías con miembros
			final List<ProCatMembersDto> listaProCatMemberDto = membersService.getAllMembersByProCatActive();

			// Para formulario
			ProCatFormDto proCatFormDto = new ProCatFormDto();

			if (listaProCatMemberDto != null && !listaProCatMemberDto.isEmpty()) {
				model.addAttribute("listaProCatDto", listaProCatDto);
				model.addAttribute("listaProCatMemberDto", listaProCatMemberDto);
				model.addAttribute("proCatFormDto", proCatFormDto);
				viewResult = "/views/public/members";
			} else {
				LOGGER.error("MembersController getMembers .- Error: Parámetros nulos");
			}
		} catch (Exception e) {
			LOGGER.error("MembersController getMembers .- Error no controlado al redireccionar a la pantalla members");
			throw e;
		}

		LOGGER.info("MembersController getMembers .- Fin");

		return viewResult;

	}

	@PostMapping("/filterMembers")
	public String getFilterMembers(Model model, ProCatFormDto proCatFormDto) throws Exception {

		LOGGER.info("MembersController getFilterMembers .- Inicio");

		String viewResult = "/views/common/Errors";
		List<ProCatMembersDto> listaProCatMemberDto = null;

		try {
			if (Long.parseLong(proCatFormDto.getIdProCat()) == 0) {
				listaProCatMemberDto = membersService.getAllMembersByProCatActive();
			} else {
				ProfessionalCategory proCat = new ProfessionalCategory();
				proCat.setIdProCat(Long.parseLong(proCatFormDto.getIdProCat()));

				final ProCatMembersDto proCatMemberDto = membersService.getMembersByProCatActive(proCat);

				if (proCatMemberDto != null) {
					listaProCatMemberDto = new ArrayList<ProCatMembersDto>();
					listaProCatMemberDto.add(proCatMemberDto);

				} else {
					LOGGER.error("MembersController getMembers .- Error: Parámetros nulos de proCatMemberDto");
				}

			}

			if (listaProCatMemberDto != null && !listaProCatMemberDto.isEmpty()) {
				model.addAttribute("listaProCatMemberDto", listaProCatMemberDto);
				viewResult = "/views/public/filterMembers";

			} else {
				LOGGER.error("MembersController getFilterMembers .- Error: Parámetros nulos de lista proCatMemberDto");
			}
		} catch (Exception e) {
			LOGGER.error(
					"MembersController getFilterMembers .- Error no controlado al redireccionar a la pantalla members");
			throw e;
		}

		LOGGER.info("MembersController getFilterMembers .- Fin");

		return viewResult;
	}

	@GetMapping("/memberComplete/{idMember}")
	public String getMember(@PathVariable String idMember, Model model) throws Exception {
		LOGGER.info("MembersController getMember .- Inicio");

		String viewResult = "/views/common/Errors";

		try {

			if (idMember != null) {
				
				final MembersDto membersDto = membersService.getMemberByIdActive(Long.parseLong(idMember));
				
				if (membersDto != null) {
					model.addAttribute("member", membersDto);
					viewResult = "/views/public/home";
				} else {
					LOGGER.error("NewsController getMember .- Parámetros nulos");
				}
			} else {
				LOGGER.error("MembersController getMember .- Error: Parámetro de entrada nulo");
			}
		} catch (Exception e) {
			LOGGER.error("MembersController getMember .- Error no controlado al redireccionar a la pantalla memberComplete");
			throw e;
		}

		LOGGER.info("MembersController getMember .- Fin");

		return viewResult;

	}

}
