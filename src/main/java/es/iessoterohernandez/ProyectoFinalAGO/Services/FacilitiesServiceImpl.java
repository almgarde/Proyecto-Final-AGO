package es.iessoterohernandez.ProyectoFinalAGO.Services;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.iessoterohernandez.ProyectoFinalAGO.Persistence.Dao.FacilityDaoI;
import es.iessoterohernandez.ProyectoFinalAGO.Persistence.Dao.TechnicalCategoryDaoI;
import es.iessoterohernandez.ProyectoFinalAGO.Persistence.Entity.Facility;
import es.iessoterohernandez.ProyectoFinalAGO.Persistence.Entity.TechnicalCategory;
import es.iessoterohernandez.ProyectoFinalAGO.Services.Dto.FacilitiesDto;
import es.iessoterohernandez.ProyectoFinalAGO.Services.Dto.TechCatFacilitiesDto;

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
						facilitiesDto.setNameFacilitie(f.getNameFacilitie());
						facilitiesDto.setIdTechCat(String.valueOf(techCat2.getIdTechCat()));
						facilitiesDto.setImageFacilitie(f.getImageFacilitie());
						facilitiesDto.setFeaturesFacilitie(f.getFeaturesFacilitie());

						listaFacilitiesDto.add(facilitiesDto);
					}
					techCatFacilitiesDto.setFacilitiesDto(listaFacilitiesDto);

				} else {
					LOGGER.info("FacilitiesServiceImpl getFacilitiesByTechCatActive .- Categoría sin equipos");
				} 
			} else {
				LOGGER.error(
						"FacilitiesServiceImpl getFacilitiesByTechCatActive .- Error: Parámetro introducido nulo");
			}
		} catch (Exception e) {
			LOGGER.error(
					"FacilitiesServiceImpl getFacilitiesByTechCatActive .- Error no controlado al recuperar los equipos");
			throw e;
		}

		LOGGER.info("FacilitiesServiceImpl getFacilitiesByTechCatActive .- Fin");
		return techCatFacilitiesDto;
	}

}
