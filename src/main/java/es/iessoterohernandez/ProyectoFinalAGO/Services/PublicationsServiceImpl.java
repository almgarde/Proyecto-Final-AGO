package es.iessoterohernandez.ProyectoFinalAGO.Services;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.iessoterohernandez.ProyectoFinalAGO.Persistence.Dao.MemberDaoI;
import es.iessoterohernandez.ProyectoFinalAGO.Persistence.Dao.PublicationDaoI;
import es.iessoterohernandez.ProyectoFinalAGO.Persistence.Entity.Member;
import es.iessoterohernandez.ProyectoFinalAGO.Persistence.Entity.Publication;
import es.iessoterohernandez.ProyectoFinalAGO.Services.Dto.AuthorDto;
import es.iessoterohernandez.ProyectoFinalAGO.Services.Dto.PublicationsDto;
import es.iessoterohernandez.ProyectoFinalAGO.Services.Dto.PublicationsYearsDto;

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
			} else {
				LOGGER.error(
						"PublicationsServiceImpl getAllPublicationsActiveOrdered .- Error: Parámetros nulos de modo de ordenación");
			}

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
							for (String shortNameAuthor : p.getAuthorsPublication()) {
								AuthorDto authorDto = new AuthorDto();
								authorDto.setNameAuthor(shortNameAuthor);
								List<Member> miembros = memberDao.findByShortNameMemberAndActive(shortNameAuthor, Boolean.TRUE);
								if (miembros != null && miembros.size()==1) {
									authorDto.setIsMember(Boolean.TRUE);
									authorDto.setIdMember(String.valueOf(miembros.get(0).getIdMember()));
								} else {
									authorDto.setIsMember(Boolean.FALSE);
								}
								listaAuthorDto.add(authorDto);
							}
							publicationsDto.setAuthorsPublication(listaAuthorDto);
							
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

		} catch (Exception e) {
			LOGGER.error(
					"PublicationsServiceImpl getAllPublicationsActiveOrdered .- Error no controlado al recuperar las publicaciones");
			throw e;
		}

		LOGGER.info("PublicationsServiceImpl getAllPublicationsActiveOrdered .- Fin");

		return listaPublicationsYearsDto;
	}

}
