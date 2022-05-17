package es.iessoterohernandez.ProyectoFinalAGO.Services;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.iessoterohernandez.ProyectoFinalAGO.Persistence.Dao.LinkDaoI;
import es.iessoterohernandez.ProyectoFinalAGO.Persistence.Entity.Link;
import es.iessoterohernandez.ProyectoFinalAGO.Persistence.Entity.News;
import es.iessoterohernandez.ProyectoFinalAGO.Services.Dto.LinksDto;
import es.iessoterohernandez.ProyectoFinalAGO.Services.Dto.NewsDto;

/**
 * Servicios. Entidad: Links
 * 
 * @author agadelao
 *
 */
@Service
public class LinksServiceImpl implements LinksServiceI {
	/** Logger */
	final static Logger LOGGER = LoggerFactory.getLogger(LinksServiceImpl.class);

	@Autowired
	LinkDaoI linkDao;

	/**
	 * Muestra las cuatro noticias más recientes
	 * 
	 * @return List<NewsDto>
	 */
	public List<LinksDto> getAllLinksActive() throws Exception {

		LOGGER.info("LinksServiceImpl getAllLinksActive .- Inicio");

		List<LinksDto> listaLinksDto = null;

		try {
			List<Link> listLinks = linkDao.findByActive(Boolean.TRUE);

			if (listLinks != null && !listLinks.isEmpty()) {
				listaLinksDto = new ArrayList<LinksDto>();
				for (Link l : listLinks) {
					LinksDto newsDto = new LinksDto();
					newsDto.setTitleLink(l.getTitleLink());
					newsDto.setImageLink(l.getImageLink());
					newsDto.setUrlLink(l.getUrlLink());

					listaLinksDto.add(newsDto);
				}

			} else {
				LOGGER.error("LinksServiceImpl getAllLinksActive .- Error: Parámetros nulos");
			}
		} catch (Exception e) {
			LOGGER.error("LinksServiceImpl getAllLinksActive .- Error no controlado al recuperar las noticias");
			throw e;
		}

		LOGGER.info("NewsServiceImpl getFourMostRecentNews .- Fin");

		return listaLinksDto;

	}

}
