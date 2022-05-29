package es.iessoterohernandez.ProyectoFinalAGO.Services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.iessoterohernandez.ProyectoFinalAGO.Persistence.Dao.AuthorsPublicationDaoI;
import es.iessoterohernandez.ProyectoFinalAGO.Persistence.Dao.MemberDaoI;
import es.iessoterohernandez.ProyectoFinalAGO.Persistence.Dao.PublicationDaoI;
import es.iessoterohernandez.ProyectoFinalAGO.Persistence.Entity.AuthorsPublication;
import es.iessoterohernandez.ProyectoFinalAGO.Persistence.Entity.Member;
import es.iessoterohernandez.ProyectoFinalAGO.Persistence.Entity.Publication;
import es.iessoterohernandez.ProyectoFinalAGO.Services.Dto.AuthorDto;
import es.iessoterohernandez.ProyectoFinalAGO.Services.Dto.PublicationsDto;
import es.iessoterohernandez.ProyectoFinalAGO.Services.Dto.PublicationsYearsDto;
import es.iessoterohernandez.ProyectoFinalAGO.Services.Dto.Datatables.PublicationsDatatableDto;
import es.iessoterohernandez.ProyectoFinalAGO.Services.Dto.Form.PublicationsFormDto;

/**
 * Servicios. Entidad: Publicaciones
 * 
 * @author agadelao
 *
 */
@Service
public class PublicationsServiceImpl implements PublicationsServiceI {

	/** Logger */
	final static Logger LOGGER = LoggerFactory.getLogger(PublicationsServiceImpl.class);

	@Autowired
	PublicationDaoI publicationDao;

	@Autowired
	MemberDaoI memberDao;

	@Autowired
	AuthorsPublicationDaoI authorsPublicationDao;

	/**
	 * Recupera todas las publicaciones activas ordenadas por fecha de publicación
	 * 
	 * @param ascendente
	 * @return List<PublicationsDto>
	 * @throws Exception
	 */
	@Override
	public List<PublicationsYearsDto> getAllPublicationsActiveOrdered(Boolean ascendente) throws Exception {

		LOGGER.info("PublicationsServiceImpl getAllPublicationsActiveOrdered .- Inicio");

		List<PublicationsYearsDto> listaPublicationsYearsDto = null;

		try {

			List<Integer> listaYears = null;

			if (ascendente != null) {
				listaYears = publicationDao.getYearsPublicationsActiveOrdered(Boolean.TRUE, ascendente);

				if (listaYears != null && !listaYears.isEmpty()) {
					listaPublicationsYearsDto = new ArrayList<PublicationsYearsDto>();

					for (Integer year : listaYears) {
						PublicationsYearsDto publicationsYearsDto = new PublicationsYearsDto();
						publicationsYearsDto.setYearPublication(year);

						List<Publication> listaPublications = publicationDao.findByYearPublicationAndActive(year,
								Boolean.TRUE);

						if (listaPublications != null && !listaPublications.isEmpty()) {

							List<PublicationsDto> listaPublicationsDto = new ArrayList<PublicationsDto>();
							for (Publication p : listaPublications) {
								PublicationsDto publicationsDto = new PublicationsDto();
								publicationsDto.setYearPublication(p.getYearPublication());
								publicationsDto.setTitlePublication(p.getTitlePublication());
								publicationsDto.setJournalPublication(p.getJournalPublication());
								publicationsDto.setDoiPublication(p.getDoiPublication());

								List<AuthorDto> listaAuthorDto = new ArrayList<AuthorDto>();
								for (AuthorsPublication authorPublication : p.getAuthorsPublication()) {
									AuthorDto authorDto = new AuthorDto();
									authorDto.setNameAuthor(authorPublication.getNameAuthor());
									authorDto.setShortNameAuthor(authorPublication.getShortNameAuthor());
									Member member = memberDao.findByIdMemberAndActive(authorPublication.getIdMember(),
											Boolean.TRUE);
									if (member != null) {
										authorDto.setIsMember(Boolean.TRUE);
										authorDto.setIdMember(String.valueOf(member.getIdMember()));
									} else {
										authorDto.setIsMember(Boolean.FALSE);
									}
									listaAuthorDto.add(authorDto);
								}
								publicationsDto.setAuthorsPublication(listaAuthorDto);
								publicationsDto.setNumAuthors(listaAuthorDto.size());

								listaPublicationsDto.add(publicationsDto);

							}
							publicationsYearsDto.setListaPublicationsDto(listaPublicationsDto);
							listaPublicationsYearsDto.add(publicationsYearsDto);

						} else {
							LOGGER.error(
									"PublicationsServiceImpl getAllPublicationsActiveOrdered .- Error: Parámetros nulos de publicaciones");
						}
					}

				} else {
					LOGGER.error(
							"PublicationsServiceImpl getAllPublicationsActiveOrdered .- Error: Parámetros nulos de años");
				}
			} else {
				LOGGER.error(
						"PublicationsServiceImpl getAllPublicationsActiveOrdered .- Error: Parámetros nulos de modo de ordenación");
			}

		} catch (Exception e) {
			LOGGER.error(
					"PublicationsServiceImpl getAllPublicationsActiveOrdered .- Error no controlado al recuperar las publicaciones");
			throw e;
		}

		LOGGER.info("PublicationsServiceImpl getAllPublicationsActiveOrdered .- Fin");

		return listaPublicationsYearsDto;
	}

	/**
	 * Recupera todos las noticias almacenadas en BDD
	 * 
	 * @return
	 * @throws Exception
	 */
	public List<PublicationsDatatableDto> getAllPublicationsData() throws Exception {

		LOGGER.info("PublicationsServiceImpl getAllPublicationsData .- Inicio");

		List<PublicationsDatatableDto> listaPublicationsDatatableDto = new ArrayList<PublicationsDatatableDto>();

		try {
			List<Publication> listPublications = publicationDao.findAll();

			if (listPublications != null && !listPublications.isEmpty()) {

				for (Publication p : listPublications) {
					PublicationsDatatableDto publicationsDatatableDto = new PublicationsDatatableDto();
					publicationsDatatableDto.setIdPublication(String.valueOf(p.getIdPublication()));
					publicationsDatatableDto.setTitlePublication(p.getTitlePublication());

					List<AuthorDto> listaAuthorDto = new ArrayList<AuthorDto>();

					for (AuthorsPublication authorPublication : p.getAuthorsPublication()) {
						AuthorDto authorDto = new AuthorDto();
						authorDto.setNameAuthor(authorPublication.getNameAuthor());
						authorDto.setShortNameAuthor(authorPublication.getShortNameAuthor());
						Member member = memberDao.findByIdMemberAndActive(authorPublication.getIdMember(),
								Boolean.TRUE);
						if (member != null) {
							authorDto.setIsMember(Boolean.TRUE);
							authorDto.setIdMember(String.valueOf(member.getIdMember()));
						} else {
							authorDto.setIsMember(Boolean.FALSE);
						}
						listaAuthorDto.add(authorDto);
					}
					publicationsDatatableDto.setAuthorsPublication(listaAuthorDto);
					publicationsDatatableDto.setJournalPublication(p.getJournalPublication());
					publicationsDatatableDto.setDoiPublication(p.getDoiPublication());
					publicationsDatatableDto.setYearPublication(String.valueOf(p.getYearPublication()));

					if (p.getActive()) {
						publicationsDatatableDto.setActive("true");
					} else {
						publicationsDatatableDto.setActive("false");
					}

					publicationsDatatableDto.setAdmin(p.getUpdateAdmin());
					publicationsDatatableDto.setDate(String.format("%1$td/%1$tm/%1$tY", p.getUpdateDate()));
					listaPublicationsDatatableDto.add(publicationsDatatableDto);
				}

			} else {
				LOGGER.error("PublicationsServiceImpl getAllPublicationsData .- Error: Parámetros nulos");
			}
		} catch (Exception e) {
			LOGGER.error(
					"PublicationsServiceImpl getAllPublicationsData .- Error no controlado al recuperar las publicaciones");
			throw e;
		}

		LOGGER.info("PublicationsServiceImpl getAllPublicationsData .- Fin");

		return listaPublicationsDatatableDto;

	}

	/**
	 * Almacena una noticia en BDD
	 * 
	 * @param addNewsFormDto
	 * @param imageNews
	 */
	@Override

	public Publication addPublications(PublicationsFormDto publicationsFormDto) throws Exception {

		LOGGER.info("PublicationsServiceImpl addPublications .- Inicio");

		Publication publicationSaved = null;

		try {
			if (publicationsFormDto != null) {

				Publication p = new Publication();

				p.setTitlePublication(publicationsFormDto.getTitlePublication());
				p.setJournalPublication(publicationsFormDto.getJournalPublication());
				p.setDoiPublication(publicationsFormDto.getDoiPublication());
				p.setYearPublication(Integer.parseInt(publicationsFormDto.getYearPublication()));

				if (Integer.parseInt(publicationsFormDto.getActive()) == 1) {
					p.setActive(true);
				} else {
					p.setActive(false);
				}

				p.setUpdateAdmin("agadelao");
				p.setUpdateDate(new Date());

				List<AuthorDto> listaAuthorsDto = publicationsFormDto.getAuthorsPublication();
				if (listaAuthorsDto != null && !listaAuthorsDto.isEmpty()) {

					publicationSaved = publicationDao.save(p);
					for (AuthorDto authorDto : listaAuthorsDto) {
						AuthorsPublication authorsPublication = new AuthorsPublication();
						authorsPublication.setIdPublication(publicationSaved);
						if (authorDto.getIdMember() != null) {
							authorsPublication.setIdMember(Long.parseLong(authorDto.getIdMember()));
						}
						authorsPublication.setNameAuthor(authorDto.getNameAuthor());
						authorsPublication.setShortNameAuthor(authorDto.getShortNameAuthor());
						authorsPublicationDao.save(authorsPublication);
					}

				}

				LOGGER.info("PublicationsServiceImpl addPublications .- Publicación almacenada correctamente");

			} else {
				LOGGER.error("PublicationsServiceImpl addPublications .- Error: Parámetros nulos");
			}
		} catch (Exception e) {
			LOGGER.error("PublicationsServiceImpl addPublications .- Error no controlado al añadir la publicación");
			throw e;
		}

		LOGGER.info("PublicationsServiceImpl addPublications .- Fin");

		return publicationSaved;

	}

	/**
	 * Almacena una noticia en BDD
	 * 
	 * @param addNewsFormDto
	 * @param imageNews
	 */
	@Override
	public Publication updatePublications(PublicationsFormDto publicationsFormDto) throws Exception {

		LOGGER.info("PublicationsServiceImpl updatePublications .- Inicio");

		Publication publicationUpdated = null;

		try {
			if (publicationsFormDto != null) {

				Publication p = publicationDao
						.findByIdPublication(Long.parseLong(publicationsFormDto.getIdPublication()));

				if (p != null) {
					p.setTitlePublication(publicationsFormDto.getTitlePublication());
					p.setJournalPublication(publicationsFormDto.getJournalPublication());
					p.setDoiPublication(publicationsFormDto.getDoiPublication());
					p.setYearPublication(Integer.parseInt(publicationsFormDto.getYearPublication()));
					if (Integer.parseInt(publicationsFormDto.getActive()) == 1) {
						p.setActive(true);
					} else {
						p.setActive(false);
					}
					p.setUpdateAdmin("agadelao");
					p.setUpdateDate(new Date());

					List<AuthorsPublication> listaAutores = authorsPublicationDao.findByIdPublication(p);
					if (listaAutores != null && !listaAutores.isEmpty()) {
						for (AuthorsPublication authorsPublication : listaAutores) {
							authorsPublicationDao.delete(authorsPublication);
						}
					}
					List<AuthorDto> listaAuthorsDto = publicationsFormDto.getAuthorsPublication();

					if (listaAuthorsDto != null && !listaAuthorsDto.isEmpty()) {
						publicationUpdated = publicationDao.save(p);
						for (AuthorDto authorDto : listaAuthorsDto) {
							AuthorsPublication authorsPublication = new AuthorsPublication();
							authorsPublication.setIdPublication(publicationUpdated);
							if (authorDto.getIdMember() != null) {
								authorsPublication.setIdMember(Long.parseLong(authorDto.getIdMember()));
							}

							authorsPublication.setNameAuthor(authorDto.getNameAuthor());
							authorsPublication.setShortNameAuthor(authorDto.getShortNameAuthor());
							authorsPublicationDao.save(authorsPublication);
						}
					}

				}
				LOGGER.info("PublicationsServiceImpl updatePublications .- Publicación almacenada correctamente");

			} else {
				LOGGER.error("PublicationsServiceImpl updatePublications .- Error: Parámetros nulos");
			}
		} catch (Exception e) {
			LOGGER.error(
					"PublicationsServiceImpl updatePublications .- Error no controlado al actualizar la publicación");
			throw e;
		}

		LOGGER.info("PublicationsServiceImpl updatePublications .- Fin");

		return publicationUpdated;

	}

	@Override
	public void deletePublications(Map<String, String> publicationData) throws Exception {

		LOGGER.info("PublicationsServiceImpl deletePublications .- Inicio");

		try {
			if (publicationData != null && !publicationData.isEmpty()) {

				Publication p = publicationDao
						.findByIdPublication(Long.parseLong(publicationData.get("idPublication")));

				if (p != null) {

					List<AuthorsPublication> listaAutores = authorsPublicationDao.findByIdPublication(p);
					if (listaAutores != null && !listaAutores.isEmpty()) {
						for (AuthorsPublication authorsPublication : listaAutores) {
							authorsPublicationDao.delete(authorsPublication);
						}
					}
					publicationDao.delete(p);

				}
				LOGGER.info("PublicationsServiceImpl deletePublications .- Publicación eliminada correctamente");

			} else {
				LOGGER.error("PublicationsServiceImpl deletePublications .- Error: Parámetros nulos");
			}
		} catch (Exception e) {
			LOGGER.error(
					"PublicationsServiceImpl deletePublications .- Error no controlado al eliminar la publicación");
			throw e;
		}

		LOGGER.info("PublicationsServiceImpl deletePublications .- Fin");

	}

}
