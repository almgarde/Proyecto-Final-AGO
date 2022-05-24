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

	/** Logger */
	final static Logger LOGGER = LoggerFactory.getLogger(TechCatServiceImpl.class);

	@Autowired
	TechnicalCategoryDaoI techCatDao;
	
	@Autowired
	FacilityDaoI facilityDao;

	/**
	 * Recupera las categorías técnicas activas
	 * 
	 * @return List<TechCatDto>
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
				LOGGER.error("TechCatServiceImpl getAllTechCatActive .- Error: Parámetros nulos");
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
	 * Recupera las categorías técnicas 
	 * 
	 * @return List<TechCatDto>
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
				LOGGER.error("TechCatServiceImpl getAllTechCat .- Error: Parámetros nulos");
			}
		} catch (Exception e) {
			LOGGER.error(
					"TechCatServiceImpl getAllTechCat .- Error no controlado al recuperar las categorías técnicas");
			throw e;
		}

		LOGGER.info("TechCatServiceImpl getAllTechCat .- Fin");

		return listaTechCatDto;

	}
	
	/**
	 * Recupera todos las categorías técnicas almacenadas en BDD
	 * 
	 * @return
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
				LOGGER.error("TechCatServiceImpl getAllTechCatData .- Error: Parámetros nulos");
			}
		} catch (Exception e) {
			LOGGER.error("TechCatServiceImpl getAllTechCatData .- Error no controlado al recuperar las categorías técnicas");
			throw e;
		}

		LOGGER.info("TechCatServiceImpl getAllTechCatData .- Fin");

		return listaTechCatDatatableDto;

	}

	/**
	 * Almacena una categoría técnica en BDD
	 * 
	 * @param techCatData
	 */
	@Override
	public TechnicalCategory addTechCat(Map<String, String> techCatData) throws Exception {

		LOGGER.info("TechCatServiceImpl addTechCat .- Inicio");
		
		TechnicalCategory techCatSaved = null;

		try {
			if (techCatData != null && !techCatData.isEmpty()) {

				TechnicalCategory tc = new TechnicalCategory();

				tc.setNameTechCat(techCatData.get("nameTechCat"));
				if (Integer.parseInt(techCatData.get("active")) == 1) {
					tc.setActive(true);
				} else {
					tc.setActive(false);
				}

				tc.setUpdateAdmin("agadelao");
				tc.setUpdateDate(new Date());
				
				techCatSaved = techCatDao.save(tc);
				
				LOGGER.info("TechCatServiceImpl addTechCat .- Categoría técnica almacenada correctamente");

			} else {
				LOGGER.error("TechCatServiceImpl addTechCat .- Error: Parámetros nulos");
			}
		} catch (Exception e) {
			LOGGER.error("TechCatServiceImpl addTechCat .- Error no controlado al añadir la categoría técnica");
			throw e;
		}

		LOGGER.info("TechCatServiceImpl addTechCat .- Fin");
		
		return techCatSaved;

	}
	
	/**
	 * Almacena una noticia en BDD
	 * 
	 * @param addNewsFormDto
	 * @param imageNews
	 */
	@Override
	public TechnicalCategory updateTechCat(Map<String, String> techCatData) throws Exception {

		LOGGER.info("TechCatServiceImpl updateTechCat .- Inicio");

		TechnicalCategory techCatUpdated = null;

		try {
			if (techCatData != null && !techCatData.isEmpty() ) {

				TechnicalCategory tc = techCatDao
						.findByIdTechCat(Long.parseLong(techCatData.get("idTechCat")));

				if (tc != null) {
					
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

					tc.setUpdateAdmin("agadelao");
					tc.setUpdateDate(new Date());

					techCatUpdated = techCatDao.save(tc);
				} else {
					LOGGER.error("TechCatServiceImpl updateTechCat .- Equipo no encontrado");
				}
				LOGGER.info("TechCatServiceImpl updateTechCat .- Equipo actualizado correctamente");

			} else {
				LOGGER.error("TechCatServiceImpl updateTechCat .- Error: Parámetros nulos");
			}
		} catch (Exception e) {
			LOGGER.error("TechCatServiceImpl updateTechCat .- Error no controlado al actualizar la publicación");
			throw e;
		}

		LOGGER.info("TechCatServiceImpl updateTechCat .- Fin");

		return techCatUpdated;

	}
	
	
	@Override
	public void deleteTechCat(Map<String, String> techCatData) throws Exception {

		LOGGER.info("PublicationsServiceImpl deletePublications .- Inicio");


		try {
			if (techCatData != null && !techCatData.isEmpty()) {

				TechnicalCategory tc = techCatDao
						.findByIdTechCat(Long.parseLong(techCatData.get("idTechCat")));

				if (tc != null) {

					techCatDao.delete(tc);

				}
				LOGGER.info("PublicationsServiceImpl deletePublications .- Publicación eliminada correctamente");

			} else {
				LOGGER.error("PublicationsServiceImpl deletePublications .- Error: Parámetros nulos");
			}
		} catch (Exception e) {
			LOGGER.error("PublicationsServiceImpl deletePublications .- Error no controlado al eliminar la publicación");
			throw e;
		}

		LOGGER.info("PublicationsServiceImpl deletePublications .- Fin");


	}

}
