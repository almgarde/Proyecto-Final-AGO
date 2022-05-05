package es.iessoterohernandez.ProyectoFinalAGO.Services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.iessoterohernandez.ProyectoFinalAGO.Persistence.Dao.NewsDaoI;
import es.iessoterohernandez.ProyectoFinalAGO.Persistence.Entity.News;
import es.iessoterohernandez.ProyectoFinalAGO.Services.Dto.NewsDto;

/**
 * Servicios. Entidad: Noticias
 * 
 * @author agadelao
 *
 */
@Service
public class NewsServiceImpl implements NewsServiceI {

	/** Logger */
	final static Logger LOGGER = LoggerFactory.getLogger(NewsServiceImpl.class);

	@Autowired
	NewsDaoI newsDao;

	/**
	 * Muestra las cuatro noticias más recientes
	 * 
	 * @return List<NewsDto>
	 */
	@Override
	public List<NewsDto> getFourMostRecentNews() {

		LOGGER.info("NewsServiceImpl getFourMostRecentNews .- Inicio");

		List<NewsDto> listaNewsDto = new ArrayList<NewsDto>();

		List<News> listNews = newsDao.findTop4ByActiveOrderByUpdateDateDesc(Boolean.TRUE);

		if (listNews != null && !listNews.isEmpty()) {

			for (News n : listNews) {
				NewsDto newsDto = new NewsDto();
				newsDto.setIdNews(n.getIdNews());
				newsDto.setTitleNews(n.getTitleNews());
				newsDto.setAbstractNews(n.getAbstractNews());
				newsDto.setContentNews(n.getContentNews());
				newsDto.setImageNews(n.getImageNews());

				listaNewsDto.add(newsDto);
			}

		} else {
			LOGGER.error("NewsServiceImpl getFourMostRecentNews .- Parámetros nulos");
		}

		LOGGER.info("NewsServiceImpl getFourMostRecentNews .- Fin");

		return listaNewsDto;
	}

	/**
	 * Muestra las cuatro noticias más recientes
	 * 
	 * @return List<NewsDto>
	 */
	@Override
	public NewsDto getNewsById(Long id) {

		LOGGER.info("NewsServiceImpl getNewsById .- Inicio");

		NewsDto newsDto = null;

		try {
			if (id != null) {
				
				newsDto = new NewsDto();
				News n = newsDao.findByIdNews(id);
				newsDto.setIdNews(n.getIdNews());
				newsDto.setTitleNews(n.getTitleNews());
				newsDto.setAbstractNews(n.getAbstractNews());
				newsDto.setContentNews(n.getContentNews());
				newsDto.setImageNews(n.getImageNews());

			} else {
				LOGGER.error("NewsServiceImpl getNewsById .- Error: Parámetros nulos");
			}
		} catch (Exception e) {
			LOGGER.error("NewsServiceImpl getNewsById .- Error no controlado al recuperar la noticia");
			throw e;
		}

		LOGGER.info("NewsServiceImpl getNewsById .- Fin");

		return newsDto;
	}

}
