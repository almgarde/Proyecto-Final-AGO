package es.iessoterohernandez.ProyectoFinalAGO.Services;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.iessoterohernandez.ProyectoFinalAGO.Persistence.Dao.ProfessionalCategoryDaoI;
import es.iessoterohernandez.ProyectoFinalAGO.Persistence.Entity.ProfessionalCategory;
import es.iessoterohernandez.ProyectoFinalAGO.Services.Dto.ProCatDto;

/**
 * Servicios. Entidad: Categorías profesionales
 * 
 * @author agadelao
 *
 */
@Service
public class ProCatServiceImpl implements ProCatServiceI {

	/** Logger */
	final static Logger LOGGER = LoggerFactory.getLogger(ProCatServiceImpl.class);

	@Autowired
	ProfessionalCategoryDaoI proCatDao;

	/**
	 * Recupera las categorías profesionales activas
	 * 
	 * @return List<ProCatDto>
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
				LOGGER.error("ProCatServiceImpl getAllProCatActive .- Error: Parámetros nulos");
			}
		} catch (Exception e) {
			LOGGER.error(
					"ProCatServiceImpl getAllProCatActive .- Error no controlado al recuperar las categorías profesionales activas");
			throw e;
		}

		LOGGER.info("ProCatServiceImpl getAllProCatActive .- Fin");

		return listaProCatDto;

	}
	
	

}
