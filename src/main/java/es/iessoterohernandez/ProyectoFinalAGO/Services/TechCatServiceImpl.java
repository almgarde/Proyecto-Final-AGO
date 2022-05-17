package es.iessoterohernandez.ProyectoFinalAGO.Services;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.iessoterohernandez.ProyectoFinalAGO.Persistence.Dao.TechnicalCategoryDaoI;
import es.iessoterohernandez.ProyectoFinalAGO.Persistence.Entity.TechnicalCategory;
import es.iessoterohernandez.ProyectoFinalAGO.Services.Dto.TechCatDto;

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

}
