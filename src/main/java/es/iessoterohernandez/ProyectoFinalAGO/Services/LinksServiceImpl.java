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

import es.iessoterohernandez.ProyectoFinalAGO.Persistence.Dao.LinkDaoI;
import es.iessoterohernandez.ProyectoFinalAGO.Persistence.Entity.Admin;
import es.iessoterohernandez.ProyectoFinalAGO.Persistence.Entity.Facility;
import es.iessoterohernandez.ProyectoFinalAGO.Persistence.Entity.Link;
import es.iessoterohernandez.ProyectoFinalAGO.Services.Dto.LinksDto;
import es.iessoterohernandez.ProyectoFinalAGO.Services.Dto.Datatables.LinksDatatableDto;

/**
 * Servicios. Entidad: Links
 * 
 * @author agadelao
 *
 */
@Service
public class LinksServiceImpl implements LinksServiceI {

	final static Logger LOGGER = LoggerFactory.getLogger(LinksServiceImpl.class);

	@Autowired
	LinkDaoI linkDao;

	/**
	 * Recupera todos los datos de los links almacenados en BDD
	 * 
	 * @return List<LinksDatatableDto>
	 * @throws Exception
	 */
	public List<LinksDatatableDto> getAllLinksData() throws Exception {

		LOGGER.info("LinksServiceImpl getAllLinksData .- Inicio");

		List<LinksDatatableDto> listaLinksDatatableDto = new ArrayList<LinksDatatableDto>();

		try {
			List<Link> listLinks = linkDao.findAll();

			if (listLinks != null && !listLinks.isEmpty()) {

				for (Link l : listLinks) {
					LinksDatatableDto linksDatatableDto = new LinksDatatableDto();

					linksDatatableDto.setIdLink(String.valueOf(l.getIdLink()));
					linksDatatableDto.setTitleLink(l.getTitleLink());
					linksDatatableDto.setImageLink(l.getImageLink());
					linksDatatableDto.setUrlLink(l.getUrlLink());
					if (l.getActive()) {
						linksDatatableDto.setActive("true");
					} else {
						linksDatatableDto.setActive("false");
					}

					linksDatatableDto.setAdmin(l.getUpdateAdmin());
					linksDatatableDto.setDate(String.format("%1$td/%1$tm/%1$tY", l.getUpdateDate()));
					listaLinksDatatableDto.add(linksDatatableDto);
				}

			} else {
				LOGGER.error("LinksServiceImpl getAllLinksData .- Error: No existen links registrados");
			}

		} catch (Exception e) {
			LOGGER.error("LinksServiceImpl getAllLinksData .- Error no controlado al recuperar los links");
			throw e;
		}

		LOGGER.info("LinksServiceImpl getAllLinksData .- Fin");

		return listaLinksDatatableDto;

	}

	/**
	 * Almacena un link en BDD
	 * 
	 * @param linksData
	 * @param imageLinks
	 * @return Link
	 * @throws Exception
	 */
	@Override
	public Link addLinks(Map<String, String> linksData, String imageLinks) throws Exception {

		LOGGER.info("LinksServiceImpl addLinks .- Inicio");

		Link linksSaved2 = null;

		try {
			if (linksData != null && !linksData.isEmpty() && !StringUtils.isEmpty(imageLinks)) {

				List<Link> listaLinks = linkDao.findAll();

				for (Link link : listaLinks) {

					if (linksData.get("titleLinks").equals(link.getTitleLink())) {
						throw new Exception("titleLinkUnique");
					}

				}

				Link l = new Link();

				l.setTitleLink(linksData.get("titleLinks"));
				l.setUrlLink(linksData.get("urlLinks"));
				if (Integer.parseInt(linksData.get("active")) == 1) {
					l.setActive(true);
				} else {
					l.setActive(false);
				}

				l.setImageLink(imageLinks);
				l.setUpdateAdmin(linksData.get("inputUser"));
				l.setUpdateDate(new Date());

				Link linksSaved = linkDao.save(l);
				l.setImageLink(linksSaved.getIdLink() + imageLinks);
				linksSaved2 = linkDao.save(l);

				LOGGER.info("LinksServiceImpl addLinks .- Link almacenado correctamente");

			} else {
				LOGGER.error("LinksServiceImpl addLinks .- Error: Parámetros de entrada nulos");
			}

		} catch (Exception e) {
			LOGGER.error("LinksServiceImpl addLinks .- Error no controlado al añadir el link");
			throw e;
		}

		LOGGER.info("LinksServiceImpl addLinks .- Fin");

		return linksSaved2;

	}

	/**
	 * Actualiza los datos de link almacenado en BDD
	 * 
	 * @param linksData
	 * @return Link
	 * @throws Exception
	 */
	@Override
	public Link updateLinks(Map<String, String> linksData) throws Exception {

		LOGGER.info("LinksServiceImpl updateLinks .- Inicio");

		Link linkUpdated = null;

		try {
			if (linksData != null && !linksData.isEmpty()) {

				Link l = linkDao.findByIdLink(Long.parseLong(linksData.get("idLink")));

				if (l != null) {
					
					if (!linksData.get("titleLink").equals(l.getTitleLink())) {
						List<Link> listaLinks = linkDao.findAll();
						for (Link link : listaLinks) {
							if (linksData.get("titleLink").equals(link.getTitleLink())) {
								throw new Exception("titleLinkUnique");
							}
						}
					}

					l.setTitleLink(linksData.get("titleLink"));
					l.setUrlLink(linksData.get("urlLink"));
					if (Integer.parseInt(linksData.get("active")) == 1) {
						l.setActive(true);
					} else {
						l.setActive(false);
					}

					l.setUpdateAdmin(linksData.get("inputUser"));
					l.setUpdateDate(new Date());

					linkUpdated = linkDao.save(l);

				} else {
					LOGGER.error("LinksServiceImpl updateLinks .- Link no encontrado");
				}

				LOGGER.info("LinksServiceImpl updateLinks .- Link actualizado correctamente");

			} else {
				LOGGER.error("LinksServiceImpl updateLinks .- Error: Parámetros de entrada nulos");
			}

		} catch (Exception e) {
			LOGGER.error("LinksServiceImpl updateLinks .- Error no controlado al actualizar el link");
			throw e;
		}

		LOGGER.info("LinksServiceImpl updateLinks .- Fin");

		return linkUpdated;

	}

	/**
	 * Actualiza la imagen de un link almacenado en BDD
	 * 
	 * @param linksData
	 * @param photoLinks
	 * @return Link
	 * @throws Exception
	 */
	@Override
	public Link updateImageLinks(Map<String, String> linksData, String photoLinks) throws Exception {

		LOGGER.info("LinksServiceImpl updateImageLinks .- Inicio");

		Link linkPhotoUpdated = null;

		try {

			if (linksData != null && !linksData.isEmpty() && photoLinks != null) {

				Link l = linkDao.findByIdLink(Long.parseLong(linksData.get("idLink")));

				if (l != null) {
					l.setUpdateAdmin(linksData.get("inputUser"));
					l.setImageLink(l.getIdLink() + photoLinks);
					linkPhotoUpdated = linkDao.save(l);
				} else {
					LOGGER.error("LinksServiceImpl updateImageLinks .- Link no encontrado");
				}

				LOGGER.info("LinksServiceImpl updateImageLinks .- Link actualizado correctamente");

			} else {
				LOGGER.error("LinksServiceImpl updateImageLinks .- Error: Parámetros de entrada nulos");
			}

		} catch (Exception e) {
			LOGGER.error("LinksServiceImpl updateImageLinks .- Error no controlado al actualizar el link");
			throw e;
		}

		LOGGER.info("LinksServiceImpl updateImageLinks .- Fin");

		return linkPhotoUpdated;

	}

	/**
	 * Elimina un link almacenado en BDD
	 * 
	 * @param linksData
	 * @throws Exception
	 */
	@Override
	public void deleteLinks(Map<String, String> linksData) throws Exception {

		LOGGER.info("LinksServiceImpl deleteLinks .- Inicio");

		try {

			if (linksData != null && !linksData.isEmpty()) {

				Link f = linkDao.findByIdLink(Long.parseLong(linksData.get("idLink")));

				if (f != null) {
					linkDao.delete(f);
				}

				LOGGER.info("LinksServiceImpl deleteLinks .- Link eliminado correctamente");

			} else {
				LOGGER.error("LinksServiceImpl deleteLinks .- Error: Parámetros de entrada nulos");
			}

		} catch (Exception e) {
			LOGGER.error("LinksServiceImpl deleteLinks .- Error no controlado al eliminar el link");
			throw e;
		}

		LOGGER.info("LinksServiceImpl deleteLinks .- Fin");

	}

	/**
	 * Recupera los links activos
	 * 
	 * @return List<LinksDto>
	 * @throws Exception
	 */
	@Override
	public List<LinksDto> getAllLinksActive() throws Exception {

		LOGGER.info("LinksServiceImpl getAllLinksActive .- Inicio");

		List<LinksDto> listaLinksDto = null;

		try {

			List<Link> listLinks = linkDao.findByActive(Boolean.TRUE);

			if (listLinks != null && !listLinks.isEmpty()) {
				listaLinksDto = new ArrayList<LinksDto>();

				for (Link l : listLinks) {

					LinksDto linksDto = new LinksDto();
					linksDto.setTitleLink(l.getTitleLink());
					linksDto.setImageLink(l.getImageLink());
					linksDto.setUrlLink(l.getUrlLink());

					listaLinksDto.add(linksDto);
				}

			} else {
				LOGGER.error("LinksServiceImpl getAllLinksActive .- Error: No existen links activos registrados");
			}

		} catch (Exception e) {
			LOGGER.error("LinksServiceImpl getAllLinksActive .- Error no controlado al recuperar los links");
			throw e;
		}

		LOGGER.info("LinksServiceImpl getAllLinksActive .- Fin");

		return listaLinksDto;

	}

}
