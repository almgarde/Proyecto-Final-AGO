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
import org.springframework.web.bind.annotation.RequestParam;

import es.iessoterohernandez.ProyectoFinalAGO.Persistence.Dao.FacilityDaoI;
import es.iessoterohernandez.ProyectoFinalAGO.Persistence.Dao.TechnicalCategoryDaoI;
import es.iessoterohernandez.ProyectoFinalAGO.Persistence.Entity.AuthorsPublication;
import es.iessoterohernandez.ProyectoFinalAGO.Persistence.Entity.Facility;
import es.iessoterohernandez.ProyectoFinalAGO.Persistence.Entity.Member;
import es.iessoterohernandez.ProyectoFinalAGO.Persistence.Entity.Publication;
import es.iessoterohernandez.ProyectoFinalAGO.Persistence.Entity.TechnicalCategory;
import es.iessoterohernandez.ProyectoFinalAGO.Services.Dto.AuthorDto;
import es.iessoterohernandez.ProyectoFinalAGO.Services.Dto.FacilitiesDto;
import es.iessoterohernandez.ProyectoFinalAGO.Services.Dto.TechCatFacilitiesDto;
import es.iessoterohernandez.ProyectoFinalAGO.Services.Dto.Datatables.FacilitiesDatatableDto;
import es.iessoterohernandez.ProyectoFinalAGO.Services.Dto.Datatables.MembersDatatableDto;
import es.iessoterohernandez.ProyectoFinalAGO.Services.Dto.Form.PublicationsFormDto;

/**
 * Servicios. Entidad: Equipos de investigación
 * 
 * @author agadelao
 *
 */
@Service
public class FacilitiesServiceImpl implements FacilitiesServiceI {

	/** Logger */
	final static Logger LOGGER = LoggerFactory.getLogger(FacilitiesServiceImpl.class);

	@Autowired
	FacilityDaoI facilityDao;

	@Autowired
	TechnicalCategoryDaoI techCatDao;

	/**
	 * Recupera los equipos de investigación activos por categorías técnicas
	 * 
	 * @returnList<TechCatFacilitiesDto>
	 * @throws Exception
	 */
	@Override
	public List<TechCatFacilitiesDto> getAllFacilitiesByTechCatActive() throws Exception {
		LOGGER.info("FacilitiesServiceImpl getAllFacilitiesByTechCatActive .- Inicio");

		List<TechCatFacilitiesDto> listaTechCatFacilitiesDto = null;

		try {

			List<TechnicalCategory> listaTechCat = techCatDao.findByActive(Boolean.TRUE);

			if (listaTechCat != null && !listaTechCat.isEmpty()) {
				listaTechCatFacilitiesDto = new ArrayList<TechCatFacilitiesDto>();
				for (TechnicalCategory tc : listaTechCat) {
					TechCatFacilitiesDto techCatFacilitiesDto = getFacilitiesByTechCatActive(tc);
					if (techCatFacilitiesDto != null) {
						listaTechCatFacilitiesDto.add(techCatFacilitiesDto);
					} else {
						LOGGER.error(
								"FacilitiesServiceImpl getAllFacilitiesByTechCatActive .- Error: Parámetros nulos de equipos");
					}
				}

			} else {
				LOGGER.error(
						"FacilitiesServiceImpl getAllFacilitiesByTechCatActive .- Error: Parámetros nulos de categorías");
			}
		} catch (Exception e) {
			LOGGER.error(
					"FacilitiesServiceImpl getAllFacilitiesByTechCatActive .- Error no controlado al recuperar los equipos");
			throw e;
		}

		LOGGER.info("FacilitiesServiceImpl getAllFacilitiesBytechCatActive .- Fin");

		return listaTechCatFacilitiesDto;
	}

	/**
	 * Recupera los equipos de investigación activos de una categoría técnica
	 * 
	 * @param techCat
	 * @return TechCatFacilitiesDto
	 * @throws Exception
	 */
	@Override
	public TechCatFacilitiesDto getFacilitiesByTechCatActive(TechnicalCategory techCat) throws Exception {
		LOGGER.info("FacilitiesServiceImpl getFacilitiesByTechCatActive .- Inicio");

		TechCatFacilitiesDto techCatFacilitiesDto = null;

		try {

			if (techCat != null) {
				TechnicalCategory techCat2 = techCatDao.findByIdTechCatAndActive(techCat.getIdTechCat(), Boolean.TRUE);
				List<Facility> listaFacilities = facilityDao.findByIdTechCatAndActive(techCat2, Boolean.TRUE);
				if (listaFacilities != null && !listaFacilities.isEmpty()) {
					techCatFacilitiesDto = new TechCatFacilitiesDto();
					techCatFacilitiesDto.setIdTechCat(String.valueOf(techCat2.getIdTechCat()));
					techCatFacilitiesDto.setNameTechCat(techCat2.getNameTechCat());
					List<FacilitiesDto> listaFacilitiesDto = new ArrayList<FacilitiesDto>();
					for (Facility f : listaFacilities) {

						FacilitiesDto facilitiesDto = new FacilitiesDto();
						facilitiesDto.setNameFacilitie(f.getNameFacility());
						facilitiesDto.setIdTechCat(String.valueOf(techCat2.getIdTechCat()));
						facilitiesDto.setPhotoFacilitie(f.getPhotoFacility());
						facilitiesDto.setFeaturesFacilitie(f.getFeaturesFacility());

						listaFacilitiesDto.add(facilitiesDto);
					}
					techCatFacilitiesDto.setFacilitiesDto(listaFacilitiesDto);

				} else {
					LOGGER.info("FacilitiesServiceImpl getFacilitiesByTechCatActive .- Categoría sin equipos");
				}
			} else {
				LOGGER.error("FacilitiesServiceImpl getFacilitiesByTechCatActive .- Error: Parámetro introducido nulo");
			}
		} catch (Exception e) {
			LOGGER.error(
					"FacilitiesServiceImpl getFacilitiesByTechCatActive .- Error no controlado al recuperar los equipos");
			throw e;
		}

		LOGGER.info("FacilitiesServiceImpl getFacilitiesByTechCatActive .- Fin");
		return techCatFacilitiesDto;
	}

	/**
	 * Recupera todos las equipos almacenadas en BDD
	 * 
	 * @return
	 * @throws Exception
	 */
	public List<FacilitiesDatatableDto> getAllFacilitiesData() throws Exception {

		LOGGER.info("FacilitiesServiceImpl getAllFacilitiesData .- Inicio");

		List<FacilitiesDatatableDto> listaFacilitiesDatatableDto = new ArrayList<FacilitiesDatatableDto>();

		try {
			List<Facility> listaFacilities = facilityDao.findAll();

			if (listaFacilities != null && !listaFacilities.isEmpty()) {

				for (Facility f : listaFacilities) {
					FacilitiesDatatableDto facilitiesDatatableDto = new FacilitiesDatatableDto();
					facilitiesDatatableDto.setIdFacility(String.valueOf(f.getIdFacility()));
					facilitiesDatatableDto.setNameFacility(f.getNameFacility());
					facilitiesDatatableDto.setIdTechCat(String.valueOf(f.getIdTechCat().getIdTechCat()));
					facilitiesDatatableDto.setNameTechCat(f.getIdTechCat().getNameTechCat());
					facilitiesDatatableDto.setActiveTechCat(String.valueOf(f.getIdTechCat().getActive()));
					facilitiesDatatableDto.setPhotoFacility(f.getPhotoFacility());
					facilitiesDatatableDto.setFeaturesFacility(f.getFeaturesFacility());
					if (f.getActive()) {
						facilitiesDatatableDto.setActive("true");
					} else {
						facilitiesDatatableDto.setActive("false");
					}

					facilitiesDatatableDto.setAdmin(f.getUpdateAdmin());
					facilitiesDatatableDto.setDate(String.format("%1$td/%1$tm/%1$tY", f.getUpdateDate()));
					listaFacilitiesDatatableDto.add(facilitiesDatatableDto);
				}

			} else {
				LOGGER.error("FacilitiesServiceImpl getAllFacilitiesData .- Error: Parámetros nulos");
			}
		} catch (Exception e) {
			LOGGER.error("FacilitiesServiceImpl getAllFacilitiesData .- Error no controlado al recuperar los equipos");
			throw e;
		}

		LOGGER.info("FacilitiesServiceImpl getAllFacilitiesData .- Fin");

		return listaFacilitiesDatatableDto;

	}

	/**
	 * Almacena un miembro en BDD
	 * 
	 * @param addNewsFormDto
	 * @param imageNews
	 */
	@Override

	public Facility addFacilities(Map<String, String> facilitiesData, String photoFacilites) throws Exception {

		LOGGER.info("FacilitiesServiceImpl addFacilities .- Inicio");

		Facility facilitySaved2 = null;

		try {
			if (facilitiesData != null && !facilitiesData.isEmpty() && !StringUtils.isEmpty(photoFacilites)) {

				Facility f = new Facility();

				f.setNameFacility(facilitiesData.get("nameFacility"));
				f.setIdTechCat(techCatDao.findByIdTechCatAndActive(Long.parseLong(facilitiesData.get("idTechCat")),
						Boolean.TRUE));
				f.setFeaturesFacility(facilitiesData.get("featuresFacility"));
				
				if (Integer.parseInt(facilitiesData.get("active")) == 1) {
					f.setActive(true);
				} else {
					f.setActive(false);
				}

				f.setPhotoFacility(photoFacilites);
				f.setUpdateAdmin("agadelao");
				f.setUpdateDate(new Date());

				Facility facilitySaved = facilityDao.save(f);
				f.setPhotoFacility(facilitySaved.getIdFacility() + photoFacilites);
				facilitySaved2 = facilityDao.save(f);

				LOGGER.info("FacilitiesServiceImpl addFacilities .- Equipo almacenada correctamente");

			} else {
				LOGGER.error("FacilitiesServiceImpl addFacilities .- Error: Parámetros nulos");
			}
		} catch (Exception e) {
			LOGGER.error("FacilitiesServiceImpl addFacilities .- Error no controlado al añadir el equipo");
			throw e;
		}

		LOGGER.info("FacilitiesServiceImpl addFacilities .- Fin");

		return facilitySaved2;

	}
	
	/**
	 * Almacena una noticia en BDD
	 * 
	 * @param addNewsFormDto
	 * @param imageNews
	 */
	@Override
	public Facility updateFacilities(Map<String, String> facilitiesData) throws Exception {

		LOGGER.info("FacilitiesServiceImpl updateFacilities .- Inicio");

		Facility facilityUpdated = null;

		try {
			if (facilitiesData != null && !facilitiesData.isEmpty() ) {

				Facility f = facilityDao
						.findByIdFacility(Long.parseLong(facilitiesData.get("idFacility")));

				if (f != null) {
					
					f.setNameFacility(facilitiesData.get("nameFacility"));
					f.setIdTechCat(techCatDao.findByIdTechCatAndActive(Long.parseLong(facilitiesData.get("idTechCat")),
							Boolean.TRUE));
					f.setFeaturesFacility(facilitiesData.get("featuresFacility"));
					
					if (Integer.parseInt(facilitiesData.get("active")) == 1) {
						f.setActive(true);
					} else {
						f.setActive(false);
					}

					f.setUpdateAdmin("agadelao");
					f.setUpdateDate(new Date());

					facilityUpdated = facilityDao.save(f);
				} else {
					LOGGER.error("FacilitiesServiceImpl updateFacilities .- Equipo no encontrado");
				}
				LOGGER.info("FacilitiesServiceImpl updateFacilities .- Equipo actualizado correctamente");

			} else {
				LOGGER.error("FacilitiesServiceImpl updateFacilities .- Error: Parámetros nulos");
			}
		} catch (Exception e) {
			LOGGER.error("FacilitiesServiceImpl updateFacilities .- Error no controlado al actualizar la publicación");
			throw e;
		}

		LOGGER.info("FacilitiesServiceImpl updateFacilities .- Fin");

		return facilityUpdated;

	}
	
	/**
	 * Almacena una noticia en BDD
	 * 
	 * @param addNewsFormDto
	 * @param imageNews
	 */
	@Override
	public Facility updatePhotoFacilities(Map<String, String> facilitiesData, String photoFacilities) throws Exception {

		LOGGER.info("FacilitiesServiceImpl updateFacilities .- Inicio");

		Facility facilityPhotoUpdated = null;

		try {
			if (facilitiesData != null && !facilitiesData.isEmpty() && photoFacilities != null) {

				Facility f = facilityDao
						.findByIdFacility(Long.parseLong(facilitiesData.get("idFacility")));

				if (f != null) {

					f.setPhotoFacility(f.getIdFacility() + photoFacilities);
					facilityPhotoUpdated = facilityDao.save(f);
				} else {
					LOGGER.error("FacilitiesServiceImpl updateFacilities .- Equipo no encontrado");
				}
				LOGGER.info("FacilitiesServiceImpl updateFacilities .- Equipo actualizado correctamente");

			} else {
				LOGGER.error("FacilitiesServiceImpl updateFacilities .- Error: Parámetros nulos");
			}
		} catch (Exception e) {
			LOGGER.error("FacilitiesServiceImpl updateFacilities .- Error no controlado al actualizar la publicación");
			throw e;
		}

		LOGGER.info("FacilitiesServiceImpl updateFacilities .- Fin");

		return facilityPhotoUpdated;

	}
	
	@Override
	public void deleteFacilities(Map<String, String> facilitiesData) throws Exception {

		LOGGER.info("PublicationsServiceImpl deletePublications .- Inicio");


		try {
			if (facilitiesData != null && !facilitiesData.isEmpty()) {

				Facility f = facilityDao
						.findByIdFacility(Long.parseLong(facilitiesData.get("idFacility")));

				if (f != null) {

					facilityDao.delete(f);

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
