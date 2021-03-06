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
import es.iessoterohernandez.ProyectoFinalAGO.Persistence.Entity.Admin;
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

	final static Logger LOGGER = LoggerFactory.getLogger(MembersServiceImpl.class);

	@Autowired
	MemberDaoI memberDao;

	@Autowired
	AuthorsPublicationDaoI authorsPublicationDao;

	@Autowired
	ProfessionalCategoryDaoI proCatDao;

	/**
	 * Recupera todos los datos de los miembros almacenados en BDD
	 * 
	 * @return List<MembersDatatableDto>
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
				LOGGER.error("MembersServiceImpl getAllMembersData .- Error: No existen miembros registrados");
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
	 * @param membersData
	 * @param imageMembers
	 * @return Member
	 * @throws Exception
	 */
	@Override

	public Member addMembers(Map<String, String> membersData, String imageMember) throws Exception {

		LOGGER.info("MembersServiceImpl addMembers .- Inicio");

		Member memberSaved2 = null;

		try {

			if (membersData != null && !membersData.isEmpty() && !StringUtils.isEmpty(imageMember)) {
				
				List<Member> listaMembers = memberDao.findAll();
				
				if (!membersData.get("dniMember").isEmpty() && membersData.get("dniMember").length() != 9) {
					throw new Exception("dniMemberLength");
				} 
				for (Member m : listaMembers) {
					
					if (membersData.get("shortNameMember").equals(m.getShortNameMember())) {
						throw new Exception("shortNameMemberUnique");
					}
					
					if (!membersData.get("dniMember").isEmpty() && membersData.get("dniMember").equals(m.getDniMember())) {
						throw new Exception("dniMemberUnique");
					}					
					
					
					if (membersData.get("emailMember").equals(m.getEmailMember())) {
						throw new Exception("emailMemberUnique");
					}
					
					if (!membersData.get("reseachIdMember").isEmpty() && membersData.get("reseachIdMember").equals(m.getReseachIdMember())) {
						throw new Exception("reseachIdMemberUnique");
					}
					
					if (!membersData.get("scopusIdMember").isEmpty() && membersData.get("scopusIdMember").equals(m.getScopusIdMember())) {
						throw new Exception("scopusIdMemberUnique");
					}
					
					if (!membersData.get("orcIdMember").isEmpty() && membersData.get("orcIdMember").equals(m.getOrcIdMember())) {
						throw new Exception("orcIdMemberUnique");
					}
					
				}
				

				Member m = new Member();

				m.setNameMember(membersData.get("nameMember"));
				m.setShortNameMember(membersData.get("shortNameMember"));
				m.setDniMember(membersData.get("dniMember"));
				m.setEmailMember(membersData.get("emailMember"));
				m.setPhoneMember(membersData.get("phoneMember"));
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
				m.setUpdateAdmin(membersData.get("inputUser"));
				m.setUpdateDate(new Date());

				Member memberSaved = memberDao.save(m);
				m.setPhotoMember(memberSaved.getIdMember() + imageMember);
				memberSaved2 = memberDao.save(m);

				LOGGER.info("MembersServiceImpl addMembers .- Miembros almacenado correctamente");

			} else {
				LOGGER.error("MembersServiceImpl addMembers .- Error: Par??metros de entrada nulos");
			}
		} catch (Exception e) {
			LOGGER.error("MembersServiceImpl addMembers .- Error no controlado al a??adir el miembro");
			throw e;
		}

		LOGGER.info("MembersServiceImpl addMembers .- Fin");

		return memberSaved2;

	}

	/**
	 * Actualiza los datos de un miembro almacenado en BDD
	 * 
	 * @param membersData
	 * @return Member
	 * @throws Exception
	 */
	@Override
	public Member updateMembers(Map<String, String> membersData) throws Exception {

		LOGGER.info("MembersServiceImpl updateMembers .- Inicio");

		Member memberUpdated = null;

		try {

			if (membersData != null && !membersData.isEmpty()) {

				Member m = memberDao.findByIdMember(Long.parseLong(membersData.get("idMembers")));

				if (m != null) {
					
					List<Member> listaMembers = memberDao.findAll();
					
					if (!membersData.get("dniMember").isEmpty() && membersData.get("dniMember").length() != 9) {
						throw new Exception("dniMemberLength");
					}
					
					
					if (!membersData.get("shortNameMember").equals(m.getShortNameMember())) {						
						for (Member member : listaMembers) {
							if (membersData.get("shortNameMember").equals(member.getShortNameMember())) {
								throw new Exception("shortNameMemberUnique");
							}
						}
					}
					
					if (!membersData.get("dniMember").isEmpty() && !membersData.get("dniMember").equals(m.getDniMember())) {						
						for (Member member : listaMembers) {
							if (membersData.get("dniMember").equals(member.getDniMember())) {
								throw new Exception("dniMemberUnique");
							}
						}
					}
					
					
					if (!membersData.get("emailMember").equals(m.getEmailMember())) {						
						for (Member member : listaMembers) {
							if (membersData.get("emailMember").equals(member.getEmailMember())) {
								throw new Exception("emailMemberUnique");
							}
						}
					}
					
					if (!membersData.get("reseachIdMember").isEmpty() &&  !membersData.get("reseachIdMember").equals(m.getReseachIdMember())) {						
						for (Member member : listaMembers) {
							if (membersData.get("reseachIdMember").equals(member.getReseachIdMember())) {
								throw new Exception("reseachIdMemberUnique");
							}
						}
					}
					
					if (!membersData.get("scopusIdMember").isEmpty() &&  !membersData.get("scopusIdMember").equals(m.getScopusIdMember())) {						
						for (Member member : listaMembers) {
							if (membersData.get("scopusIdMember").equals(member.getScopusIdMember())) {
								throw new Exception("scopusIdMemberUnique");
							}
						}
					}
					
					if (!membersData.get("orcIdMember").isEmpty() &&  !membersData.get("orcIdMember").equals(m.getOrcIdMember())) {						
						for (Member member : listaMembers) {
							if (membersData.get("orcIdMember").equals(member.getOrcIdMember())) {
								throw new Exception("orcIdMemberUnique");
							}
						}
					}

					m.setNameMember(membersData.get("nameMember"));
					m.setShortNameMember(membersData.get("shortNameMember"));
					m.setDniMember(membersData.get("dniMember"));
					m.setEmailMember(membersData.get("emailMember"));
					m.setPhoneMember(membersData.get("phoneMember"));
					m.setIdProCat(proCatDao.findByIdProCat(Long.parseLong(membersData.get("idProCat"))));
					m.setReseachIdMember(membersData.get("reseachIdMember"));
					m.setScopusIdMember(membersData.get("scopusIdMember"));
					m.setOrcIdMember(membersData.get("orcIdMember"));
					m.setTrajectoryMember(membersData.get("trajectoryMember"));

					if (Integer.parseInt(membersData.get("active")) == 1) {
						m.setActive(true);
					} else {
						m.setActive(false);
					}

					m.setUpdateAdmin(membersData.get("inputUser"));
					m.setUpdateDate(new Date());

					memberUpdated = memberDao.save(m);

				} else {
					LOGGER.error("MembersServiceImpl updateMembers .- Miembro no encontrado");
				}
				LOGGER.info("MembersServiceImpl updateMembers .- Miembro actualizado correctamente");

			} else {
				LOGGER.error("MembersServiceImpl updateMembers .- Error: Par??metros de entrada nulos");
			}

		} catch (Exception e) {
			LOGGER.error("MembersServiceImpl updateMembers .- Error no controlado al actualizar el miembro");
			throw e;
		}

		LOGGER.info("MembersServiceImpl updateMembers .- Fin");

		return memberUpdated;

	}

	/**
	 * Actualiza la foto de un miembro almacenado en BDD
	 * 
	 * @param membersData
	 * @param photoMembers
	 * @return Member
	 * @throws Exception
	 */
	@Override
	public Member updatePhotoMembers(Map<String, String> membersData, String photoMembers) throws Exception {

		LOGGER.info("MembersServiceImpl updatePhotoMembers .- Inicio");

		Member memberPhotoUpdated = null;

		try {

			if (membersData != null && !membersData.isEmpty() && photoMembers != null) {

				Member m = memberDao.findByIdMember(Long.parseLong(membersData.get("idMembers")));

				if (m != null) {
					m.setUpdateAdmin(membersData.get("inputUser"));
					m.setPhotoMember(m.getIdMember() + photoMembers);
					memberPhotoUpdated = memberDao.save(m);
				} else {
					LOGGER.error("MembersServiceImpl updatePhotoMembers .- Miembro no encontrado");
				}

				LOGGER.info("MembersServiceImpl updatePhotoMembers .- Miembro actualizado correctamente");

			} else {
				LOGGER.error("MembersServiceImpl updatePhotoMembers .- Error: Par??metros de entrada nulos");
			}

		} catch (Exception e) {
			LOGGER.error("MembersServiceImpl updatePhotoMembers .- Error no controlado al actualizar el miembro");
			throw e;
		}

		LOGGER.info("MembersServiceImpl updatePhotoMembers .- Fin");

		return memberPhotoUpdated;

	}

	/**
	 * Elimina un miembro almacenado en BDD
	 * 
	 * @param membersData
	 * @throws Exception
	 */
	@Override
	public void deleteMembers(Map<String, String> membersData) throws Exception {

		LOGGER.info("MembersServiceImpl deleteMembers .- Inicio");

		try {

			if (membersData != null && !membersData.isEmpty()) {

				Member m = memberDao.findByIdMember(Long.parseLong(membersData.get("idMembers")));

				if (m != null) {
					memberDao.delete(m);
				}
				LOGGER.info("MembersServiceImpl deleteMembers .- Miembro eliminado correctamente");

			} else {
				LOGGER.error("MembersServiceImpl deleteMembers .- Error: Par??metros de entrada nulos");
			}

		} catch (Exception e) {
			LOGGER.error("MembersServiceImpl deleteMembers .- Error no controlado al eliminar el miembro");
			throw e;
		}

		LOGGER.info("MembersServiceImpl deleteMembers .- Fin");

	}

	/**
	 * Recupera los miembros activos
	 * 
	 * @return List<MembersDto>
	 * @throws Exception
	 */
	@Override
	public List<MembersDto> getMembersByActive() throws Exception {

		LOGGER.info("MembersServiceImpl getMembersByActive .- Inicio");

		List<MembersDto> listaMembersDto = null;

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
				LOGGER.info("MembersServiceImpl getMembersByActive .- Error: No existen miembros activos registrados");
			}

		} catch (Exception e) {
			LOGGER.error("MembersServiceImpl getMembersByActive .- Error no controlado al recuperar los miembros");
			throw e;
		}

		LOGGER.info("MembersServiceImpl getMembersByActive .- Fin");
		return listaMembersDto;
	}

	/**
	 * Recupera todos los miembros activos agrupados por categor??as profesionales
	 * 
	 * @return List<ProCatMembersDto>
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
								"MembersServiceImpl getAllMembersByProCatActive .- Error: No existen miembros activos en esta categor??a");
					}
				}

			} else {
				LOGGER.error(
						"MembersServiceImpl getAllMembersByProCatActive .- Error: No existen categor??as activas registradas");
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
	 * Recupera los miembros activos de una categor??a profesional
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
				LOGGER.info("MembersServiceImpl getMembersByProCatActive .- Categor??a sin miembros");
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
				LOGGER.error("MembersServiceImpl getMemberByIdActive .- Error: Par??metros de entrada nulos");
			}

		} catch (Exception e) {
			LOGGER.error("MembersServiceImpl getMemberByIdActive .- Error no controlado al recuperar el miembro");
			throw e;
		}

		LOGGER.info("MembersServiceImpl getMemberByIdActive .- Fin");

		return membersDto;

	}

}
