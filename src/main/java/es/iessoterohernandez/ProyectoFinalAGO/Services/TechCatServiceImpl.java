package es.iessoterohernandez.ProyectoFinalAGO.Services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.iessoterohernandez.ProyectoFinalAGO.Persistence.Dao.FacilityDaoI;
import es.iessoterohernandez.ProyectoFinalAGO.Persistence.Dao.TechnicalCategoryDaoI;
import es.iessoterohernandez.ProyectoFinalAGO.Persistence.Entity.Facility;
import es.iessoterohernandez.ProyectoFinalAGO.Persistence.Entity.News;
import es.iessoterohernandez.ProyectoFinalAGO.Persistence.Entity.TechnicalCategory;
import es.iessoterohernandez.ProyectoFinalAGO.Services.Dto.TechCatDto;
import es.iessoterohernandez.ProyectoFinalAGO.Services.Dto.Datatables.TechCatDatatableDto;

/**
 * Servicios. Entidad: Categorías técnicas
 * 
 * @author agadelao
 *
 */
@Service
public class TechCatServiceImpl implements TechCatServiceI {

	final static Logger LOGGER = LoggerFactory.getLogger(TechCatServiceImpl.class);

	@Autowired
	TechnicalCategoryDaoI techCatDao;

	@Autowired
	FacilityDaoI facilityDao;

	/**
	 * Recupera todos los datos de las categorías técnicas almacenadas en BDD
	 * 
	 * @return List<TechCatDatatableDto>
	 * @throws Exception
	 */
	public List<TechCatDatatableDto> getAllTechCatData() throws Exception {

		LOGGER.info("TechCatServiceImpl getAllTechCatData .- Inicio");

		List<TechCatDatatableDto> listaTechCatDatatableDto = new ArrayList<TechCatDatatableDto>();

		try {

			List<TechnicalCategory> listTechCat = techCatDao.findAll();

			if (listTechCat != null && !listTechCat.isEmpty()) {

				for (TechnicalCategory tc : listTechCat) {

					TechCatDatatableDto techCatDatatableDto = new TechCatDatatableDto();
					techCatDatatableDto.setIdTechCat(String.valueOf(tc.getIdTechCat()));
					techCatDatatableDto.setNameTechCat(tc.getNameTechCat());

					if (tc.getActive()) {
						techCatDatatableDto.setActive("true");
					} else {
						techCatDatatableDto.setActive("false");
					}

					techCatDatatableDto.setAdmin(tc.getUpdateAdmin());
					techCatDatatableDto.setDate(String.format("%1$td/%1$tm/%1$tY", tc.getUpdateDate()));
					listaTechCatDatatableDto.add(techCatDatatableDto);
				}

			} else {
				LOGGER.error(
						"TechCatServiceImpl getAllTechCatData .- Error: No existen categorías técnicas registradas");
			}

		} catch (Exception e) {
			LOGGER.error(
					"TechCatServiceImpl getAllTechCatData .- Error no controlado al recuperar las categorías técnicas");
			throw e;
		}

		LOGGER.info("TechCatServiceImpl getAllTechCatData .- Fin");

		return listaTechCatDatatableDto;

	}

	/**
	 * Almacena una categoría técnica en BDD
	 * 
	 * @param techCatData
	 * @return TechnicalCategory
	 * @throws Exception
	 */
	@Override
	public TechnicalCategory addTechCat(Map<String, String> techCatData) throws Exception {

		LOGGER.info("TechCatServiceImpl addTechCat .- Inicio");

		TechnicalCategory techCatSaved = null;

		try {

			if (techCatData != null && !techCatData.isEmpty()) {

				List<TechnicalCategory> listaTechCat = techCatDao.findAll();
				for (TechnicalCategory techCat : listaTechCat) {

					if (techCatData.get("nameTechCat").equals(techCat.getNameTechCat())) {
						throw new Exception("nameTechCatUnique");
					}

				}

				TechnicalCategory tc = new TechnicalCategory();

				tc.setNameTechCat(techCatData.get("nameTechCat"));

				if (Integer.parseInt(techCatData.get("active")) == 1) {
					tc.setActive(true);
				} else {
					tc.setActive(false);
				}

				tc.setUpdateAdmin(techCatData.get("inputUser"));
				tc.setUpdateDate(new Date());

				techCatSaved = techCatDao.save(tc);

				LOGGER.info("TechCatServiceImpl addTechCat .- Categoría técnica almacenada correctamente");

			} else {
				LOGGER.error("TechCatServiceImpl addTechCat .- Error: Parámetros de entrada nulos");
			}

		} catch (Exception e) {
			LOGGER.error("TechCatServiceImpl addTechCat .- Error no controlado al añadir la categoría técnica");
			throw e;
		}

		LOGGER.info("TechCatServiceImpl addTechCat .- Fin");

		return techCatSaved;

	}

	/**
	 * Actualiza los datos de una categoría técnica almacenada en BDD
	 * 
	 * @param techCatData
	 * @return TechnicalCategory
	 * @throws Exception
	 */
	@Override
	public TechnicalCategory updateTechCat(Map<String, String> techCatData) throws Exception {

		LOGGER.info("TechCatServiceImpl updateTechCat .- Inicio");

		TechnicalCategory techCatUpdated = null;

		try {

			if (techCatData != null && !techCatData.isEmpty()) {

				TechnicalCategory tc = techCatDao.findByIdTechCat(Long.parseLong(techCatData.get("idTechCat")));

				if (tc != null) {
					
					if (!techCatData.get("nameTechCat").equals(tc.getNameTechCat())) {
						List<TechnicalCategory> listaTechCat = techCatDao.findAll();
						for (TechnicalCategory techCat : listaTechCat) {
							if (techCatData.get("nameTechCat").equals(techCat.getNameTechCat())) {
								throw new Exception("nameTechCatUnique");
							}
						}
					}

					tc.setNameTechCat(techCatData.get("nameTechCat"));

					if (Integer.parseInt(techCatData.get("active")) == 1) {
						tc.setActive(true);
					} else {
						List<Facility> listaFacilities = facilityDao.findByIdTechCat(tc);

						for (Facility facility : listaFacilities) {
							facility.setActive(false);
							facilityDao.save(facility);
						}

						tc.setActive(false);
					}

					tc.setUpdateAdmin(techCatData.get("inputUser"));
					tc.setUpdateDate(new Date());

					techCatUpdated = techCatDao.save(tc);

				} else {
					LOGGER.error("TechCatServiceImpl updateTechCat .- Categoría técnica no encontrada");
				}

				LOGGER.info("TechCatServiceImpl updateTechCat .- Categoría técnica actualizada correctamente");

			} else {
				LOGGER.error("TechCatServiceImpl updateTechCat .- Error: Parámetros de entrada nulos");
			}

		} catch (Exception e) {
			LOGGER.error("TechCatServiceImpl updateTechCat .- Error no controlado al actualizar la categoría técnica");
			throw e;
		}

		LOGGER.info("TechCatServiceImpl updateTechCat .- Fin");

		return techCatUpdated;

	}

	/**
	 * Elimina una categoría técnica almacenada en BDD
	 * 
	 * @param techCatData
	 * @throws Exception
	 */
	@Override
	public void deleteTechCat(Map<String, String> techCatData) throws Exception {

		LOGGER.info("TechCatServiceImpl deleteTechCat .- Inicio");

		try {

			if (techCatData != null && !techCatData.isEmpty()) {

				TechnicalCategory tc = techCatDao.findByIdTechCat(Long.parseLong(techCatData.get("idTechCat")));

				if (tc != null) {
					techCatDao.delete(tc);
				}

				LOGGER.info("TechCatServiceImpl deleteTechCat .- Categoría técnica eliminada correctamente");

			} else {
				LOGGER.error("TechCatServiceImpl deleteTechCat .- Error: Parámetros de entrada nulos");
			}

		} catch (Exception e) {
			LOGGER.error("TechCatServiceImpl deleteTechCat .- Error no controlado al eliminar la categoría técnica");
			throw e;
		}

		LOGGER.info("TechCatServiceImpl deleteTechCat .- Fin");

	}

	/**
	 * Recupera las categorías técnicas activas
	 * 
	 * @return List<TechCatDto>
	 * @throws Exception
	 */
	@Override
	public List<TechCatDto> getAllTechCatActive() throws Exception {

		LOGGER.info("TechCatServiceImpl getAllTechCatActive .- Inicio");

		List<TechCatDto> listaTechCatDto = null;

		try {

			List<TechnicalCategory> listaTechCat = techCatDao.findByActive(Boolean.TRUE);

			if (listaTechCat != null && !listaTechCat.isEmpty()) {

				listaTechCatDto = new ArrayList<TechCatDto>();

				for (TechnicalCategory tc : listaTechCat) {

					TechCatDto techCatDto = new TechCatDto();
					techCatDto.setIdTechCat(String.valueOf(tc.getIdTechCat()));
					techCatDto.setNameTechCat(tc.getNameTechCat());

					listaTechCatDto.add(techCatDto);
				}

			} else {
				LOGGER.error(
						"TechCatServiceImpl getAllTechCatActive .- Error: No existen categorías técnicas activos registradas");
			}

		} catch (Exception e) {
			LOGGER.error(
					"TechCatServiceImpl getAllTechCatActive .- Error no controlado al recuperar las categorías técnicas activas");
			throw e;
		}

		LOGGER.info("TechCatServiceImpl getAllTechCatActive .- Fin");

		return listaTechCatDto;

	}

	/**
	 * Recupera todas las categorías técnicas
	 * 
	 * @return List<TechCatDto>
	 * @throws Exception
	 */
	@Override
	public List<TechCatDto> getAllTechCat() throws Exception {

		LOGGER.info("TechCatServiceImpl getAllTechCat .- Inicio");

		List<TechCatDto> listaTechCatDto = null;

		try {

			List<TechnicalCategory> listaTechCat = techCatDao.findAll();

			if (listaTechCat != null && !listaTechCat.isEmpty()) {

				listaTechCatDto = new ArrayList<TechCatDto>();

				for (TechnicalCategory tc : listaTechCat) {

					TechCatDto techCatDto = new TechCatDto();
					techCatDto.setIdTechCat(String.valueOf(tc.getIdTechCat()));
					techCatDto.setNameTechCat(tc.getNameTechCat());

					listaTechCatDto.add(techCatDto);
				}

			} else {
				LOGGER.error("TechCatServiceImpl getAllTechCat .- Error: No existen categorías técnicas registradas");
			}

		} catch (Exception e) {
			LOGGER.error(
					"TechCatServiceImpl getAllTechCat .- Error no controlado al recuperar las categorías técnicas");
			throw e;
		}

		LOGGER.info("TechCatServiceImpl getAllTechCat .- Fin");

		return listaTechCatDto;

	}

}
