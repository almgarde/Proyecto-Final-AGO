package es.iessoterohernandez.ProyectoFinalAGO.Services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.iessoterohernandez.ProyectoFinalAGO.Persistence.Dao.MemberDaoI;
import es.iessoterohernandez.ProyectoFinalAGO.Persistence.Dao.ProfessionalCategoryDaoI;
import es.iessoterohernandez.ProyectoFinalAGO.Persistence.Entity.Member;
import es.iessoterohernandez.ProyectoFinalAGO.Persistence.Entity.ProfessionalCategory;
import es.iessoterohernandez.ProyectoFinalAGO.Services.Dto.ProCatDto;
import es.iessoterohernandez.ProyectoFinalAGO.Services.Dto.Datatables.ProCatDatatableDto;

/**
 * Servicios. Entidad: Categorías profesionales
 * 
 * @author agadelao
 *
 */
@Service
public class ProCatServiceImpl implements ProCatServiceI {

	final static Logger LOGGER = LoggerFactory.getLogger(ProCatServiceImpl.class);

	@Autowired
	ProfessionalCategoryDaoI proCatDao;

	@Autowired
	MemberDaoI memberDao;

	/**
	 * Recupera todos los datos de las categorías profesionales almacenadas en BDD
	 * 
	 * @return List<ProCatDatatableDto>
	 * @throws Exception
	 */
	public List<ProCatDatatableDto> getAllProCatData() throws Exception {

		LOGGER.info("ProCatServiceImpl getAllProCatData .- Inicio");

		List<ProCatDatatableDto> listaProCatDatatableDto = new ArrayList<ProCatDatatableDto>();

		try {

			List<ProfessionalCategory> listProCat = proCatDao.findAll();

			if (listProCat != null && !listProCat.isEmpty()) {

				for (ProfessionalCategory pc : listProCat) {

					ProCatDatatableDto proCatDatatableDto = new ProCatDatatableDto();
					proCatDatatableDto.setIdProCat(String.valueOf(pc.getIdProCat()));
					proCatDatatableDto.setNameProCat(pc.getNameProCat());

					if (pc.getActive()) {
						proCatDatatableDto.setActive("true");
					} else {
						proCatDatatableDto.setActive("false");
					}

					proCatDatatableDto.setAdmin(pc.getUpdateAdmin());
					proCatDatatableDto.setDate(String.format("%1$td/%1$tm/%1$tY", pc.getUpdateDate()));
					listaProCatDatatableDto.add(proCatDatatableDto);
				}

			} else {
				LOGGER.error(
						"ProCatServiceImpl getAllProCatData .- Error: No existen categorías profesionales registradas");
			}

		} catch (Exception e) {
			LOGGER.error(
					"ProCatServiceImpl getAllProCatData .- Error no controlado al recuperar las categorías profesionales");
			throw e;
		}

		LOGGER.info("ProCatServiceImpl getAllProCatData .- Fin");

		return listaProCatDatatableDto;

	}

	/**
	 * Almacena una categoría profesional en BDD
	 * 
	 * @param proCatData
	 * @return ProfessionalCategory
	 * @throws Exception
	 */
	@Override
	public ProfessionalCategory addProCat(Map<String, String> proCatData) throws Exception {

		LOGGER.info("ProCatServiceImpl addProCat .- Inicio");

		ProfessionalCategory proCatSaved = null;

		try {

			if (proCatData != null && !proCatData.isEmpty()) {

				ProfessionalCategory pc = new ProfessionalCategory();

				pc.setNameProCat(proCatData.get("nameProCat"));

				if (Integer.parseInt(proCatData.get("active")) == 1) {
					pc.setActive(true);
				} else {
					pc.setActive(false);
				}

				pc.setUpdateAdmin("agadelao");
				pc.setUpdateDate(new Date());

				proCatSaved = proCatDao.save(pc);

				LOGGER.info("ProCatServiceImpl addProCat .- Categoría profesional almacenada correctamente");

			} else {
				LOGGER.error("ProCatServiceImpl addProCat .- Error: Parámetros de entrada nulos");
			}

		} catch (Exception e) {
			LOGGER.error("ProCatServiceImpl addProCat .- Error no controlado al añadir la categoría profesional");
			throw e;
		}

		LOGGER.info("ProCatServiceImpl addProCat .- Fin");

		return proCatSaved;

	}

	/**
	 * Actualiza los datos de una categoría profesional almacenada en BDD
	 * 
	 * @param proCatData
	 * @return ProfessionalCategory
	 * @throws Exception
	 */
	@Override
	public ProfessionalCategory updateProCat(Map<String, String> proCatData) throws Exception {

		LOGGER.info("ProCatServiceImpl updateProCat .- Inicio");

		ProfessionalCategory proCatUpdated = null;

		try {

			if (proCatData != null && !proCatData.isEmpty()) {

				ProfessionalCategory pc = proCatDao.findByIdProCat(Long.parseLong(proCatData.get("idProCat")));

				if (pc != null) {

					pc.setNameProCat(proCatData.get("nameProCat"));
					if (Integer.parseInt(proCatData.get("active")) == 1) {
						pc.setActive(true);
					} else {
						List<Member> listaMembers = memberDao.findByIdProCat(pc);

						for (Member member : listaMembers) {
							member.setActive(false);
							memberDao.save(member);
							pc.setActive(false);
						}
					}

					pc.setUpdateAdmin("agadelao");
					pc.setUpdateDate(new Date());

					proCatUpdated = proCatDao.save(pc);

				} else {
					LOGGER.error("ProCatServiceImpl updateProCat .- Categoría profesional no encontrada");
				}

				LOGGER.info("ProCatServiceImpl updateProCat .- Categoría profesional actualizada correctamente");

			} else {
				LOGGER.error("ProCatServiceImpl updateProCat .- Error: Parámetros de entrada nulos");
			}

		} catch (Exception e) {
			LOGGER.error(
					"ProCatServiceImpl updateProCat .- Error no controlado al actualizar la categoría profesional");
			throw e;
		}

		LOGGER.info("ProCatServiceImpl updateProCat .- Fin");

		return proCatUpdated;

	}

	/**
	 * Elimina una categoría profesional almacenada en BDD
	 * 
	 * @param proCatData
	 * @throws Exception
	 */
	@Override
	public void deleteProCat(Map<String, String> proCatData) throws Exception {

		LOGGER.info("ProCatServiceImpl deleteProCat .- Inicio");

		try {

			if (proCatData != null && !proCatData.isEmpty()) {

				ProfessionalCategory pc = proCatDao.findByIdProCat(Long.parseLong(proCatData.get("idProCat")));

				if (pc != null) {

					proCatDao.delete(pc);

					LOGGER.info("ProCatServiceImpl deleteProCat .- Categoría profesional eliminada correctamente");

				} else {
					LOGGER.error("ProCatServiceImpl deleteProCat .- Error: Parámetros de entrada nulos");
				}
			}

		} catch (Exception e) {
			LOGGER.error("ProCatServiceImpl deleteProCat .- Error no controlado al eliminar la Categoría profesional");
			throw e;
		}

		LOGGER.info("ProCatServiceImpl deleteProCat .- Fin");

	}

	/**
	 * Recupera las categorías profesionales activas
	 * 
	 * @return List<ProCatDto>
	 * @throws Exception
	 */
	@Override
	public List<ProCatDto> getAllProCatActive() throws Exception {

		LOGGER.info("ProCatServiceImpl getAllProCatActive .- Inicio");

		List<ProCatDto> listaProCatDto = null;

		try {

			List<ProfessionalCategory> listaProCat = proCatDao.findByActive(Boolean.TRUE);

			if (listaProCat != null && !listaProCat.isEmpty()) {
				listaProCatDto = new ArrayList<ProCatDto>();

				for (ProfessionalCategory pc : listaProCat) {

					ProCatDto proCatDto = new ProCatDto();
					proCatDto.setIdProCat(String.valueOf(pc.getIdProCat()));
					proCatDto.setNameProCat(pc.getNameProCat());

					listaProCatDto.add(proCatDto);
				}

			} else {
				LOGGER.error(
						"ProCatServiceImpl getAllProCatActive .- Error: No existen categorías proifesionales activas registradas");
			}

		} catch (Exception e) {
			LOGGER.error(
					"ProCatServiceImpl getAllProCatActive .- Error no controlado al recuperar las categorías profesionales activas");
			throw e;
		}

		LOGGER.info("ProCatServiceImpl getAllProCatActive .- Fin");

		return listaProCatDto;

	}

	/**
	 * Recupera todas las categorías profesionales almacenadas en BDD
	 * 
	 * @return List<ProCatDto>
	 * @throws Exception
	 */
	@Override
	public List<ProCatDto> getAllProCat() throws Exception {

		LOGGER.info("ProCatServiceImpl getAllProCat .- Inicio");

		List<ProCatDto> listaProCatDto = null;

		try {

			List<ProfessionalCategory> listaProCat = proCatDao.findAll();

			if (listaProCat != null && !listaProCat.isEmpty()) {
				listaProCatDto = new ArrayList<ProCatDto>();

				for (ProfessionalCategory pc : listaProCat) {

					ProCatDto proCatDto = new ProCatDto();
					proCatDto.setIdProCat(String.valueOf(pc.getIdProCat()));
					proCatDto.setNameProCat(pc.getNameProCat());

					listaProCatDto.add(proCatDto);
				}
			} else {
				LOGGER.error(
						"ProCatServiceImpl getAllProCat .- Error: No existen categorías profesionales registradas");
			}

		} catch (Exception e) {
			LOGGER.error(
					"ProCatServiceImpl getAllProCat .- Error no controlado al recuperar las categorías profesionales");
			throw e;
		}

		LOGGER.info("ProCatServiceImpl getAllProCat .- Fin");

		return listaProCatDto;

	}

}
