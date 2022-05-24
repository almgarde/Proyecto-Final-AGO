package es.iessoterohernandez.ProyectoFinalAGO.Services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.iessoterohernandez.ProyectoFinalAGO.Persistence.Dao.AuthorsPublicationDaoI;
import es.iessoterohernandez.ProyectoFinalAGO.Persistence.Dao.MemberDaoI;
import es.iessoterohernandez.ProyectoFinalAGO.Persistence.Dao.ProfessionalCategoryDaoI;
import es.iessoterohernandez.ProyectoFinalAGO.Persistence.Entity.AuthorsPublication;
import es.iessoterohernandez.ProyectoFinalAGO.Persistence.Entity.Facility;
import es.iessoterohernandez.ProyectoFinalAGO.Persistence.Entity.Member;
import es.iessoterohernandez.ProyectoFinalAGO.Persistence.Entity.ProfessionalCategory;
import es.iessoterohernandez.ProyectoFinalAGO.Services.Dto.MembersDto;
import es.iessoterohernandez.ProyectoFinalAGO.Services.Dto.ProCatMembersDto;
import es.iessoterohernandez.ProyectoFinalAGO.Services.Dto.Datatables.MembersDatatableDto;

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
	AuthorsPublicationDaoI authorsPublicationDao;

	@Autowired
	ProfessionalCategoryDaoI proCatDao;
	
	/**
	 * Recupera los miembros activos
	 * 
	 * @param proCat
	 * @return ProCatMembersDto
	 * @throws Exception
	 */
	@Override
	public List<MembersDto> getMembersByActive() throws Exception {
		
		List<MembersDto> listaMembersDto = null;

		LOGGER.info("MembersServiceImpl getMembersByActive .- Inicio");
		try {
		

			List<Member> listaMembers = memberDao.findByActive(Boolean.TRUE);

			if (listaMembers != null && !listaMembers.isEmpty()) {
				listaMembersDto = new ArrayList<MembersDto>();
				for (Member m : listaMembers) {

					MembersDto membersDto = new MembersDto();
					membersDto.setIdMember(String.valueOf(m.getIdMember()));
					membersDto.setNameMember(m.getNameMember());
					membersDto.setShortNameMember(m.getShortNameMember());
					membersDto.setEmailMember(m.getEmailMember());
					membersDto.setPhoneMember(m.getPhoneMember());
					membersDto.setNameProCat(String.valueOf(m.getIdProCat().getIdProCat()));
					membersDto.setPhotoMember(m.getPhotoMember());
					membersDto.setReseachIdMember(m.getReseachIdMember());
					membersDto.setScopusIdMember(m.getScopusIdMember());
					membersDto.setOrcIdMember(m.getOrcIdMember());
					membersDto.setTrajectoryMember(m.getTrajectoryMember());

					listaMembersDto.add(membersDto);
				}
				

			} else {
				LOGGER.info("MembersServiceImpl getMembersByActive .- Parámetros nulos");
			}
		} catch (Exception e) {
			LOGGER.error(
					"MembersServiceImpl getMembersByActive .- Error no controlado al recuperar los miembros");
			throw e;
		}

		LOGGER.info("MembersServiceImpl getMembersByActive .- Fin");
		return listaMembersDto;
	}
	

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

	/**
	 * Recupera todos las noticias almacenadas en BDD
	 * 
	 * @return
	 * @throws Exception
	 */
	public List<MembersDatatableDto> getAllMembersData() throws Exception {

		LOGGER.info("MembersServiceImpl getAllMembersData .- Inicio");

		List<MembersDatatableDto> listaMembersDatatableDto = new ArrayList<MembersDatatableDto>();

		try {
			List<Member> listMembers = memberDao.findAll();

			if (listMembers != null && !listMembers.isEmpty()) {

				for (Member m : listMembers) {
					MembersDatatableDto membersDatatableDto = new MembersDatatableDto();
					membersDatatableDto.setIdMember(String.valueOf(m.getIdMember()));
					membersDatatableDto.setNameMember(m.getNameMember());
					membersDatatableDto.setShortNameMember(m.getShortNameMember());
					membersDatatableDto.setDniMember(m.getDniMember());
					membersDatatableDto.setEmailMember(m.getEmailMember());
					membersDatatableDto.setPhoneMember(m.getPhoneMember());
					membersDatatableDto.setIdProCat(String.valueOf(m.getIdProCat().getIdProCat()));
					membersDatatableDto.setNameProCat(m.getIdProCat().getNameProCat());
					membersDatatableDto.setActiveProCat(String.valueOf(m.getIdProCat().getActive()));
					membersDatatableDto.setPhotoMember(m.getPhotoMember());
					membersDatatableDto.setReseachIdMember(m.getReseachIdMember());
					membersDatatableDto.setScopusIdMember(m.getScopusIdMember());
					membersDatatableDto.setOrcIdMember(m.getOrcIdMember());
					membersDatatableDto.setTrajectoryMember(m.getTrajectoryMember());
					if (m.getActive()) {
						membersDatatableDto.setActive("true");
					} else {
						membersDatatableDto.setActive("false");
					}

					membersDatatableDto.setAdmin(m.getUpdateAdmin());
					membersDatatableDto.setDate(String.format("%1$td/%1$tm/%1$tY", m.getUpdateDate()));
					listaMembersDatatableDto.add(membersDatatableDto);
				}

			} else {
				LOGGER.error("MembersServiceImpl getAllMembersData .- Error: Parámetros nulos");
			}
		} catch (Exception e) {
			LOGGER.error("MembersServiceImpl getAllMembersData .- Error no controlado al recuperar los miembros");
			throw e;
		}

		LOGGER.info("MembersServiceImpl getAllMembersData .- Fin");

		return listaMembersDatatableDto;

	}

	/**
	 * Almacena un miembro en BDD
	 * 
	 * @param addNewsFormDto
	 * @param imageNews
	 */
	@Override

	public Member addMembers(Map<String, String> membersData, String imageMember) throws Exception {

		LOGGER.info("MembersServiceImpl addMembers .- Inicio");

		Member memberSaved2 = null;

		try {
			if (membersData != null && !membersData.isEmpty() && !StringUtils.isEmpty(imageMember)) {

				Member m = new Member();

				m.setNameMember(membersData.get("nameMember"));
				m.setShortNameMember(membersData.get("shortNameMember"));
				m.setDniMember(membersData.get("dniMember"));
				m.setEmailMember(membersData.get("emailMember"));
				m.setPhoneMember(Integer.parseInt(membersData.get("phoneMember")));
				m.setIdProCat(
						proCatDao.findByIdProCatAndActive(Long.parseLong(membersData.get("idProCat")), Boolean.TRUE));
				m.setReseachIdMember(membersData.get("reseachIdMember"));
				m.setScopusIdMember(membersData.get("scopusIdMember"));
				m.setOrcIdMember(membersData.get("orcIdMember"));
				m.setTrajectoryMember(membersData.get("trajectoryMember"));
				if (Integer.parseInt(membersData.get("active")) == 1) {
					m.setActive(true);
				} else {
					m.setActive(false);
				}

				m.setPhotoMember(imageMember);
				m.setUpdateAdmin("agadelao");
				m.setUpdateDate(new Date());
				

				Member memberSaved = memberDao.save(m);
				m.setPhotoMember(memberSaved.getIdMember() + imageMember);
				memberSaved2 = memberDao.save(m);

				LOGGER.info("MembersServiceImpl addMembers .- Noticia almacenada correctamente");

			} else {
				LOGGER.error("MembersServiceImpl addMembers .- Error: Parámetros nulos");
			}
		} catch (Exception e) {
			LOGGER.error("MembersServiceImpl addMembers .- Error no controlado al añadir el miembro");
			throw e;
		}

		LOGGER.info("MembersServiceImpl addMembers .- Fin");

		return memberSaved2;

	}
	
	/**
	 * Almacena una noticia en BDD
	 * 
	 * @param addNewsFormDto
	 * @param imageNews
	 */
	@Override
	public Member updateMembers(Map<String, String> membersData) throws Exception {

		LOGGER.info("MembersServiceImpl updateMembers .- Inicio");

		Member memberUpdated = null;

		try {
			if (membersData != null && !membersData.isEmpty() ) {

				Member m = memberDao
						.findByIdMember(Long.parseLong(membersData.get("idMembers")));

				if (m != null) {
					
					m.setNameMember(membersData.get("nameMember"));
					m.setShortNameMember(membersData.get("shortNameMember"));
					m.setDniMember(membersData.get("dniMember"));
					m.setEmailMember(membersData.get("emailMember"));
					m.setPhoneMember(Integer.parseInt(membersData.get("phoneMember")));
					m.setIdProCat(
							proCatDao.findByIdProCat(Long.parseLong(membersData.get("idProCat"))));
					m.setReseachIdMember(membersData.get("reseachIdMember"));
					m.setScopusIdMember(membersData.get("scopusIdMember"));
					m.setOrcIdMember(membersData.get("orcIdMember"));
					m.setTrajectoryMember(membersData.get("trajectoryMember"));
					if (Integer.parseInt(membersData.get("active")) == 1) {
						m.setActive(true);
					} else {
						m.setActive(false);
					}

					m.setUpdateAdmin("agadelao");
					m.setUpdateDate(new Date());
					

					memberUpdated = memberDao.save(m);

				} else {
					LOGGER.error("MembersServiceImpl updateMembers .- Miembro no encontrado");
				}
				LOGGER.info("MembersServiceImpl updateMembers .- Miembro actualizado correctamente");

			} else {
				LOGGER.error("MembersServiceImpl updateMembers .- Error: Parámetros nulos");
			}
		} catch (Exception e) {
			LOGGER.error("MembersServiceImpl updateMembers .- Error no controlado al actualizar el miembro");
			throw e;
		}

		LOGGER.info("MembersServiceImpl updateMembers .- Fin");

		return memberUpdated;

	}
	
	/**
	 * Almacena una noticia en BDD
	 * 
	 * @param addNewsFormDto
	 * @param imageNews
	 */
	@Override
	public Member updatePhotoMembers(Map<String, String> membersData, String photoMembers) throws Exception {

		LOGGER.info("MembersServiceImpl updateFacilities .- Inicio");

		Member memberPhotoUpdated = null;

		try {
			if (membersData != null && !membersData.isEmpty() && photoMembers != null) {

				Member m = memberDao
						.findByIdMember(Long.parseLong(membersData.get("idMembers")));

				if (m != null) {

					m.setPhotoMember(m.getIdMember() + photoMembers);
					memberPhotoUpdated = memberDao.save(m);
				} else {
					LOGGER.error("MembersServiceImpl updateFacilities .- Equipo no encontrado");
				}
				LOGGER.info("MembersServiceImpl updateFacilities .- Equipo actualizado correctamente");

			} else {
				LOGGER.error("MembersServiceImpl updateFacilities .- Error: Parámetros nulos");
			}
		} catch (Exception e) {
			LOGGER.error("MembersServiceImpl updateFacilities .- Error no controlado al actualizar la publicación");
			throw e;
		}

		LOGGER.info("MembersServiceImpl updateFacilities .- Fin");

		return memberPhotoUpdated;

	}
	
	@Override
	public void deleteMembers(Map<String, String> membersData) throws Exception {

		LOGGER.info("MembersServiceImpl deleteMembers .- Inicio");


		try {
			if (membersData != null && !membersData.isEmpty()) {

				Member m = memberDao
						.findByIdMember(Long.parseLong(membersData.get("idMembers")));

				if (m != null) {

					memberDao.delete(m);

				}
				LOGGER.info("MembersServiceImpl deleteMembers .- Publicación eliminada correctamente");

			} else {
				LOGGER.error("MembersServiceImpl deleteMembers .- Error: Parámetros nulos");
			}
		} catch (Exception e) {
			LOGGER.error("MembersServiceImpl deleteMembers .- Error no controlado al eliminar la publicación");
			throw e;
		}

		LOGGER.info("MembersServiceImpl deleteMembers .- Fin");


	}

}
