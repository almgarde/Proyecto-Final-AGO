package es.iessoterohernandez.ProyectoFinalAGO.Services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.iessoterohernandez.ProyectoFinalAGO.Persistence.Dao.NewsDaoI;
import es.iessoterohernandez.ProyectoFinalAGO.Persistence.Entity.News;
import es.iessoterohernandez.ProyectoFinalAGO.Services.Dto.NewsDto;
import es.iessoterohernandez.ProyectoFinalAGO.Services.Dto.Datatables.NewsDataTableDto;
import es.iessoterohernandez.ProyectoFinalAGO.Services.Dto.Form.AddNewsFormDto;

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
	public List<NewsDto> getFourMostRecentNewsActive() throws Exception {

		LOGGER.info("NewsServiceImpl getFourMostRecentNews .- Inicio");

		List<NewsDto> listaNewsDto = new ArrayList<NewsDto>();

		try {
			List<News> listNews = newsDao.findTop4ByActiveOrderByUpdateDateDesc(Boolean.TRUE);

			if (listNews != null && !listNews.isEmpty()) {

				for (News n : listNews) {
					NewsDto newsDto = new NewsDto();
					newsDto.setIdNews(String.valueOf(n.getIdNews()));
					newsDto.setTitleNews(n.getTitleNews());
					newsDto.setAbstractNews(n.getAbstractNews());
					newsDto.setContentNews(n.getContentNews());
					newsDto.setImageNews(n.getImageNews());

					listaNewsDto.add(newsDto);
				}

			} else {
				LOGGER.error("NewsServiceImpl getFourMostRecentNews .- Error: Parámetros nulos");
			}
		} catch (Exception e) {
			LOGGER.error("NewsServiceImpl getFourMostRecentNews .- Error no controlado al recuperar las noticias");
			throw e;
		}

		LOGGER.info("NewsServiceImpl getFourMostRecentNews .- Fin");

		return listaNewsDto;
	}

	/**
	 * Recupera una noticia por su Id
	 * 
	 * @return List<NewsDto>
	 */
	@Override
	public NewsDto getNewsByIdActive(Long id) throws Exception {

		LOGGER.info("NewsServiceImpl getNewsById .- Inicio");

		NewsDto newsDto = null;

		try {
			if (id != null) {

				newsDto = new NewsDto();
				News n = newsDao.findByIdNewsAndActive(id, Boolean.TRUE);
				newsDto.setIdNews(String.valueOf(n.getIdNews()));
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

	/**
	 * Recupera todos las noticias almacenadas en BDD
	 * 
	 * @return
	 * @throws Exception
	 */
	public List<NewsDataTableDto> getAllNewsData() throws Exception {

		LOGGER.info("NewsServiceImpl getAllNewsData .- Inicio");

		List<NewsDataTableDto> listaNewsDataTableDto = new ArrayList<NewsDataTableDto>();

		try {
			List<News> listNews = newsDao.findAll();

			if (listNews != null && !listNews.isEmpty()) {

				for (News n : listNews) {
					NewsDataTableDto newsDataTableDto = new NewsDataTableDto();
					newsDataTableDto.setIdNews(String.valueOf(n.getIdNews()));
					newsDataTableDto.setTitleNews(n.getTitleNews());
					newsDataTableDto.setAbstractNews(n.getAbstractNews());
					newsDataTableDto.setContentNews(n.getContentNews());
					newsDataTableDto.setImageNews(n.getImageNews());
					if (n.getActive()) {
						newsDataTableDto.setActive("true");
					} else {
						newsDataTableDto.setActive("false");
					}

					newsDataTableDto.setAdmin(n.getUpdateAdmin());
					newsDataTableDto.setDate(String.format("%1$td-%1$tm-%1$tY", n.getUpdateDate()));
					listaNewsDataTableDto.add(newsDataTableDto);
				}

			} else {
				LOGGER.error("NewsServiceImpl getAllNewsData .- Error: Parámetros nulos");
			}
		} catch (Exception e) {
			LOGGER.error("NewsServiceImpl getAllNewsData .- Error no controlado al recuperar las noticias");
			throw e;
		}

		LOGGER.info("NewsServiceImpl getAllNewsData .- Fin");

		return listaNewsDataTableDto;

	}

	/**
	 * Almacena una noticia en BDD
	 * 
	 * @param addNewsFormDto
	 * @param imageNews
	 */
	@Override

	public News addNews(AddNewsFormDto addNewsFormDto, String imageNews) throws Exception {

		LOGGER.info("NewsServiceImpl addNews .- Inicio");
		
		News newsUpdated = null;

		try {
			if (addNewsFormDto != null && !StringUtils.isEmpty(imageNews)) {

				News n = new News();

				n.setTitleNews(addNewsFormDto.getTitleNews());
				n.setAbstractNews(addNewsFormDto.getAbstractNews());
				n.setContentNews(addNewsFormDto.getContentNews());
				if (addNewsFormDto.getActive() == "1") {
					n.setActive(true);
				} else {
					n.setActive(false);
				}

				n.setImageNews(imageNews);
				n.setUpdateAdmin("agadelao");
				n.setUpdateDate(new Date());
				
				News newsSaved = newsDao.save(n);
				newsSaved.setImageNews(newsSaved.getIdNews() + imageNews);
				newsUpdated = newsDao.save(n);

				
				LOGGER.info("NewsServiceImpl addNews .- Noticia almacenada correctamente");

			} else {
				LOGGER.error("NewsServiceImpl addNews .- Error: Parámetros nulos");
			}
		} catch (Exception e) {
			LOGGER.error("NewsServiceImpl addNews .- Error no controlado al añadir la noticia");
			throw e;
		}

		LOGGER.info("NewsServiceImpl addNews .- Fin");
		
		return newsUpdated;

	}

	

}
