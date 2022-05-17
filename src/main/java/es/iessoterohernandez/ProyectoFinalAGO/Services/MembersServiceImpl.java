package es.iessoterohernandez.ProyectoFinalAGO.Services;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.iessoterohernandez.ProyectoFinalAGO.Persistence.Dao.MemberDaoI;
import es.iessoterohernandez.ProyectoFinalAGO.Persistence.Dao.ProfessionalCategoryDaoI;
import es.iessoterohernandez.ProyectoFinalAGO.Persistence.Entity.Member;
import es.iessoterohernandez.ProyectoFinalAGO.Persistence.Entity.News;
import es.iessoterohernandez.ProyectoFinalAGO.Persistence.Entity.ProfessionalCategory;
import es.iessoterohernandez.ProyectoFinalAGO.Services.Dto.MembersDto;
import es.iessoterohernandez.ProyectoFinalAGO.Services.Dto.NewsDto;
import es.iessoterohernandez.ProyectoFinalAGO.Services.Dto.ProCatMembersDto;

/**
 * Servicios. Entidad: Miembros
 * 
 * @author agadelao
 *
 */
@Service
public class MembersServiceImpl implements MembersServiceI {

	/** Logger */
	final static Logger LOGGER = LoggerFactory.getLogger(MembersServiceImpl.class);

	@Autowired
	MemberDaoI memberDao;

	@Autowired
	ProfessionalCategoryDaoI proCatDao;

	/**
	 * Recupera los miembros activos por categorías profesionales
	 * 
	 * @returnList<ProCatMembersDto>
	 * @throws Exception
	 */
	@Override
	public List<ProCatMembersDto> getAllMembersByProCatActive() throws Exception {
		LOGGER.info("MembersServiceImpl getAllMembersByProCatActive .- Inicio");

		List<ProCatMembersDto> listaProCatMembersDto = null;

		try {

			List<ProfessionalCategory> listaProCat = proCatDao.findByActive(Boolean.TRUE);

			if (listaProCat != null && !listaProCat.isEmpty()) {

				listaProCatMembersDto = new ArrayList<ProCatMembersDto>();
				
				for (ProfessionalCategory pc : listaProCat) {
					ProCatMembersDto proCatMembersDto = getMembersByProCatActive(pc);
					if (proCatMembersDto != null) {
						listaProCatMembersDto.add(proCatMembersDto);
					} else {
						LOGGER.error(
								"MembersServiceImpl getAllMembersByProCatActive .- Error: Parámetros nulos de miembros");
					}
				}

			} else {
				LOGGER.error("MembersServiceImpl getAllMembersByProCatActive .- Error: Parámetros nulos de categorías");
			}
		} catch (Exception e) {
			LOGGER.error(
					"MembersServiceImpl getAllMembersByProCatActive .- Error no controlado al recuperar los miembros");
			throw e;
		}

		LOGGER.info("MembersServiceImpl getAllMembersByProCatActive .- Fin");

		return listaProCatMembersDto;
	}

	/**
	 * Recupera los miembros activos de una categoría profesional
	 * 
	 * @param proCat
	 * @return ProCatMembersDto
	 * @throws Exception
	 */
	@Override
	public ProCatMembersDto getMembersByProCatActive(ProfessionalCategory proCat) throws Exception {

		LOGGER.info("MembersServiceImpl getMembersByProCatActive .- Inicio");

		ProCatMembersDto proCatMembersDto = null;

		try {

			ProfessionalCategory proCat2 = proCatDao.findByIdProCatAndActive(proCat.getIdProCat(), Boolean.TRUE);

			List<Member> listaMembers = memberDao.findByIdProCatAndActive(proCat2, Boolean.TRUE);

			if (listaMembers != null && !listaMembers.isEmpty()) {
				proCatMembersDto = new ProCatMembersDto();
				proCatMembersDto.setNameProCat(proCat2.getNameProCat());
				proCatMembersDto.setIdProCat(String.valueOf(proCat2.getIdProCat()));
				List<MembersDto> listaMembersDto = new ArrayList<MembersDto>();
				for (Member m : listaMembers) {

					MembersDto membersDto = new MembersDto();
					membersDto.setIdMember(String.valueOf(m.getIdMember()));
					membersDto.setNameMember(m.getNameMember());
					membersDto.setShortNameMember(m.getShortNameMember());
					membersDto.setEmailMember(m.getEmailMember());
					membersDto.setPhoneMember(m.getPhoneMember());
					membersDto.setNameProCat(proCat2.getNameProCat());
					membersDto.setPhotoMember(m.getPhotoMember());
					membersDto.setReseachIdMember(m.getReseachIdMember());
					membersDto.setScopusIdMember(m.getScopusIdMember());
					membersDto.setOrcIdMember(m.getOrcIdMember());
					membersDto.setTrajectoryMember(m.getTrajectoryMember());

					listaMembersDto.add(membersDto);
				}
				proCatMembersDto.setMembersDto(listaMembersDto);

			} else {
				LOGGER.info("MembersServiceImpl getMembersByProCatActive .- Categoría sin miembros");
			}
		} catch (Exception e) {
			LOGGER.error(
					"MembersServiceImpl getMembersByProCatActive .- Error no controlado al recuperar los miembros");
			throw e;
		}

		LOGGER.info("MembersServiceImpl getMembersByProCatActive .- Fin");
		return proCatMembersDto;
	}
	
	/**
	 * Recupera un miembro activo por su Id
	 * 
	 * @param id
	 * @return MembersDto
	 * @throws Exception
	 */
	@Override
	public MembersDto getMemberByIdActive(Long id) throws Exception {
		
		LOGGER.info("MembersServiceImpl getMemberByIdActive .- Inicio");

		MembersDto membersDto = null;

		try {
			if (id != null) {

				membersDto = new MembersDto();
				Member m = memberDao.findByIdMemberAndActive(id, Boolean.TRUE);
				membersDto.setIdMember(String.valueOf(m.getIdMember()));
				membersDto.setNameMember(m.getNameMember());
				membersDto.setShortNameMember(m.getShortNameMember());
				membersDto.setEmailMember(m.getEmailMember());
				membersDto.setPhoneMember(m.getPhoneMember());
				membersDto.setNameProCat(m.getIdProCat().getNameProCat());
				membersDto.setPhotoMember(m.getPhotoMember());
				membersDto.setReseachIdMember(m.getReseachIdMember());
				membersDto.setScopusIdMember(m.getScopusIdMember());
				membersDto.setOrcIdMember(m.getOrcIdMember());
				membersDto.setTrajectoryMember(m.getTrajectoryMember());
				
				
				

			} else {
				LOGGER.error("MembersServiceImpl getMemberByIdActive .- Error: Parámetros nulos");
			}
		} catch (Exception e) {
			LOGGER.error("MembersServiceImpl getMemberByIdActive .- Error no controlado al recuperar el miembro");
			throw e;
		}

		LOGGER.info("MembersServiceImpl getMemberByIdActive .- Fin");

		return membersDto;
		
	}

}
