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
import org.springframework.web.bind.annotation.PathVariable;

import es.iessoterohernandez.ProyectoFinalAGO.Services.MembersServiceI;
import es.iessoterohernandez.ProyectoFinalAGO.Services.ProCatServiceI;
import es.iessoterohernandez.ProyectoFinalAGO.Services.Dto.MembersDto;
import es.iessoterohernandez.ProyectoFinalAGO.Services.Dto.ProCatMembersDto;

/**
 * Controlador. Entidad: Miembros
 * 
 * @author agadelao
 *
 */
@Controller
public class MembersController {

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

			final List<ProCatMembersDto> listaProCatMemberDto = membersService.getAllMembersByProCatActive();
			Locale lang = LocaleContextHolder.getLocale();

			if (listaProCatMemberDto != null && !listaProCatMemberDto.isEmpty()) {
				model.addAttribute("listaProCatMemberDto", listaProCatMemberDto);
				model.addAttribute("lang", lang);
				viewResult = "/views/public/members";
			} else {
				LOGGER.error("MembersController getMembers .- Error: No existen miembros activos registrados");
			}

		} catch (Exception e) {
			LOGGER.error("MembersController getMembers .- Error no controlado al redireccionar a la pantalla members");
			throw e;
		}

		LOGGER.info("MembersController getMembers .- Fin");

		return viewResult;

	}

	@GetMapping("/memberComplete/{idMember}")
	public String getMemberInfo(@PathVariable String idMember, Model model) throws Exception {

		LOGGER.info("MembersController getMemberInfo .- Inicio");

		String viewResult = "/views/common/Errors";

		try {

			if (idMember != null) {

				final MembersDto membersDto = membersService.getMemberByIdActive(Long.parseLong(idMember));
				Locale lang = LocaleContextHolder.getLocale();

				if (membersDto != null) {
					model.addAttribute("member", membersDto);
					model.addAttribute("lang", lang);
					viewResult = "/views/public/memberComplete";
				} else {
					LOGGER.error("NewsController getMemberInfo .- Error: No existen miembros activos registrados");
				}

			} else {
				LOGGER.error("MembersController getMemberInfo .- Error: Parámetros de entrada nulos");
			}

		} catch (Exception e) {
			LOGGER.error(
					"MembersController getMemberInfo .- Error no controlado al redireccionar a la pantalla memberComplete");
			throw e;
		}

		LOGGER.info("MembersController getMemberInfo .- Fin");

		return viewResult;

	}

}
