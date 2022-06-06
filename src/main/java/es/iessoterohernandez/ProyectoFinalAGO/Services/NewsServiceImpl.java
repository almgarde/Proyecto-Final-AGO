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

import es.iessoterohernandez.ProyectoFinalAGO.Persistence.Dao.NewsDaoI;
import es.iessoterohernandez.ProyectoFinalAGO.Persistence.Entity.Admin;
import es.iessoterohernandez.ProyectoFinalAGO.Persistence.Entity.News;
import es.iessoterohernandez.ProyectoFinalAGO.Services.Dto.NewsDto;
import es.iessoterohernandez.ProyectoFinalAGO.Services.Dto.Datatables.NewsDatatableDto;

/**
 * Servicios. Entidad: Noticias
 * 
 * @author agadelao
 *
 */
@Service
public class NewsServiceImpl implements NewsServiceI {

	final static Logger LOGGER = LoggerFactory.getLogger(NewsServiceImpl.class);

	@Autowired
	NewsDaoI newsDao;

	/**
	 * Recupera todos los datos de las noticias almacenadas en BDD
	 * 
	 * @return
	 * @throws Exception
	 */
	public List<NewsDatatableDto> getAllNewsData() throws Exception {

		LOGGER.info("NewsServiceImpl getAllNewsData .- Inicio");

		List<NewsDatatableDto> listaNewsDatatableDto = new ArrayList<NewsDatatableDto>();

		try {

			List<News> listNews = newsDao.findAll();

			if (listNews != null && !listNews.isEmpty()) {

				for (News n : listNews) {

					NewsDatatableDto newsDatatableDto = new NewsDatatableDto();
					newsDatatableDto.setIdNews(String.valueOf(n.getIdNews()));
					newsDatatableDto.setTitleNews(n.getTitleNews());
					newsDatatableDto.setAbstractNews(n.getAbstractNews());
					newsDatatableDto.setContentNews(n.getContentNews());
					newsDatatableDto.setImageNews(n.getImageNews());

					if (n.getActive()) {
						newsDatatableDto.setActive("true");
					} else {
						newsDatatableDto.setActive("false");
					}

					newsDatatableDto.setAdmin(n.getUpdateAdmin());
					newsDatatableDto.setDate(String.format("%1$td/%1$tm/%1$tY", n.getUpdateDate()));
					listaNewsDatatableDto.add(newsDatatableDto);
				}

			} else {
				LOGGER.error("NewsServiceImpl getAllNewsData .- Error: Parámetros de entrada nulos");
			}

		} catch (Exception e) {
			LOGGER.error("NewsServiceImpl getAllNewsData .- Error no controlado al recuperar las noticias");
			throw e;
		}

		LOGGER.info("NewsServiceImpl getAllNewsData .- Fin");

		return listaNewsDatatableDto;

	}

	/**
	 * Almacena una noticia en BDD
	 * 
	 * @param newsData
	 * @param imageNews
	 * @return
	 * @throws Exception
	 */
	@Override
	public News addNews(Map<String, String> newsData, String imageNews) throws Exception {

		LOGGER.info("NewsServiceImpl addNews .- Inicio");

		News newsSaved2 = null;

		try {

			if (newsData != null && !newsData.isEmpty() && !StringUtils.isEmpty(imageNews)) {
				
				if (newsData.get("contentNews").length() <350) {
					throw new Exception("contentNewsLength");
				} 				
				List<News> listaNews = newsDao.findAll();
				
				for (News news : listaNews) {
					
					if (newsData.get("titleNews").equals(news.getTitleNews())) {
						throw new Exception("titleNewsUnique");					}

					
				}


				News n = new News();

				n.setTitleNews(newsData.get("titleNews"));
				n.setContentNews(newsData.get("contentNews"));

				if (Integer.parseInt(newsData.get("active")) == 1) {
					n.setActive(true);
				} else {
					n.setActive(false);
				}

				n.setImageNews(imageNews);
				n.setUpdateAdmin(newsData.get("inputUser"));
				n.setUpdateDate(new Date());

				News newsSaved = newsDao.save(n);
				n.setImageNews(newsSaved.getIdNews() + imageNews);
				newsSaved2 = newsDao.save(n);

				LOGGER.info("NewsServiceImpl addNews .- Noticia almacenada correctamente");

			} else {
				LOGGER.error("NewsServiceImpl addNews .- Error: Parámetros de entrada nulos");
			}

		} catch (Exception e) {
			LOGGER.error("NewsServiceImpl addNews .- Error no controlado al añadir la noticia");
			throw e;
		}

		LOGGER.info("NewsServiceImpl addNews .- Fin");

		return newsSaved2;

	}

	/**
	 * Actualiza los datos de una noticia almacenada en BDD
	 * 
	 * @param newsData
	 * @return
	 * @throws Exception
	 */
	@Override
	public News updateNews(Map<String, String> newsData) throws Exception {

		LOGGER.info("NewsServiceImpl updateNews .- Inicio");

		News newsUpdated = null;

		try {

			if (newsData != null && !newsData.isEmpty()) {

				News n = newsDao.findByIdNews(Long.parseLong(newsData.get("idNews")));

				if (n != null) {
					
					if (newsData.get("contentNews").length() <350) {
						throw new Exception("contentNewsLength");
					}
					
					if (!newsData.get("titleNews").equals(n.getTitleNews())) {
						List<News> listaNews = newsDao.findAll();
						for (News news : listaNews) {
							if (newsData.get("titleNews").equals(news.getTitleNews())) {
								throw new Exception("titleNewsUnique");
							}
						}
					}

					n.setTitleNews(newsData.get("titleNews"));
					n.setAbstractNews(newsData.get("abstractNews"));
					n.setContentNews(newsData.get("contentNews"));

					if (Integer.parseInt(newsData.get("active")) == 1) {
						n.setActive(true);
					} else {
						n.setActive(false);
					}

					n.setUpdateAdmin(newsData.get("inputUser"));
					n.setUpdateDate(new Date());

					newsUpdated = newsDao.save(n);

				} else {
					LOGGER.error("NewsServiceImpl updateNews .- Noticia no encontrada");
				}

				LOGGER.info("NewsServiceImpl updateNews .- Noticia actualizada correctamente");

			} else {
				LOGGER.error("NewsServiceImpl updateNews .- Error: Parámetros de entrada nulos");
			}

		} catch (Exception e) {
			LOGGER.error("NewsServiceImpl updateNews .- Error no controlado al actualizar la noticia");
			throw e;
		}

		LOGGER.info("NewsServiceImpl updateNews .- Fin");

		return newsUpdated;

	}

	/**
	 * Actualiza la imagen de una noticia almacenada en BDD
	 * 
	 * @param newsData
	 * @param photoNews
	 * @return
	 * @throws Exception
	 */
	@Override
	public News updateImageNews(Map<String, String> newsData, String imageNews) throws Exception {

		LOGGER.info("NewsServiceImpl updateImageNews .- Inicio");

		News newsImageUpdated = null;

		try {

			if (newsData != null && !newsData.isEmpty() && imageNews != null) {

				News n = newsDao.findByIdNews(Long.parseLong(newsData.get("idNews")));

				if (n != null) {
					n.setUpdateAdmin(newsData.get("inputUser"));
					n.setImageNews(n.getIdNews() + imageNews);
					newsImageUpdated = newsDao.save(n);
				} else {
					LOGGER.error("NewsServiceImpl updateImageNews .- Noticia no encontrada");
				}

				LOGGER.info("NewsServiceImpl updateImageNews .- Noticia actualizada correctamente");

			} else {
				LOGGER.error("NewsServiceImpl updateImageNews .- Error: Parámetros nulos");
			}

		} catch (Exception e) {
			LOGGER.error("NewsServiceImpl updateImageNews .- Error no controlado al actualizar la noticia");
			throw e;
		}

		LOGGER.info("NewsServiceImpl updateImageNews .- Fin");

		return newsImageUpdated;

	}

	/**
	 * Elimina una noticia almacenada en BDD
	 * 
	 * @param newsData
	 * @throws Exception
	 */
	@Override
	public void deleteNews(Map<String, String> newsData) throws Exception {

		LOGGER.info("NewsServiceImpl deleteNews .- Inicio");

		try {

			if (newsData != null && !newsData.isEmpty()) {

				News n = newsDao.findByIdNews(Long.parseLong(newsData.get("idNews")));

				if (n != null) {
					newsDao.delete(n);
				}

				LOGGER.info("NewsServiceImpl deleteNews .- Noticia eliminada correctamente");

			} else {
				LOGGER.error("NewsServiceImpl deleteNews .- Error: Parámetros nulos");
			}

		} catch (Exception e) {
			LOGGER.error("NewsServiceImpl deleteNews .- Error no controlado al eliminar la noticia");
			throw e;
		}

		LOGGER.info("NewsServiceImpl deleteNews .- Fin");

	}

	/**
	 * Recupera las seis noticias s/n activas más recientes ordenadas por fecha
	 * descendente
	 * 
	 * @return
	 * @throws Exception
	 */
	@Override
	public List<NewsDto> getSixMostRecentNewsActive() throws Exception {

		LOGGER.info("NewsServiceImpl getSixMostRecentNewsActive .- Inicio");

		List<NewsDto> listaNewsDto = new ArrayList<NewsDto>();

		try {

			List<News> listNews = newsDao.findTop6ByActiveOrderByUpdateDateDesc(Boolean.TRUE);

			if (listNews != null && !listNews.isEmpty()) {

				for (News n : listNews) {

					NewsDto newsDto = new NewsDto();
					newsDto.setIdNews(String.valueOf(n.getIdNews()));
					newsDto.setTitleNews(n.getTitleNews());
					newsDto.setAbstractNews(n.getAbstractNews());
					String subStringNews = n.getContentNews().substring(0, 300);
					subStringNews = subStringNews + "...";
					newsDto.setContentNews(subStringNews);
					newsDto.setImageNews(n.getImageNews());

					listaNewsDto.add(newsDto);
				}

			} else {
				LOGGER.error(
						"NewsServiceImpl getSixMostRecentNewsActive .- Error: No existen noticias activas registradas");
			}

		} catch (Exception e) {
			LOGGER.error("NewsServiceImpl getSixMostRecentNewsActive .- Error no controlado al recuperar las noticias");
			throw e;
		}

		LOGGER.info("NewsServiceImpl getSixMostRecentNewsActive .- Fin");

		return listaNewsDto;
	}

	/**
	 * Recupera una noticia activa por su Id
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@Override
	public NewsDto getNewsByIdActive(Long id) throws Exception {

		LOGGER.info("NewsServiceImpl getNewsByIdActive .- Inicio");

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
				LOGGER.error("NewsServiceImpl getNewsByIdActive .- Error: Parámetros de entrada nulos");
			}

		} catch (Exception e) {
			LOGGER.error("NewsServiceImpl getNewsByIdActive .- Error no controlado al recuperar la noticia");
			throw e;
		}

		LOGGER.info("NewsServiceImpl getNewsByIdActive .- Fin");

		return newsDto;
	}

}
