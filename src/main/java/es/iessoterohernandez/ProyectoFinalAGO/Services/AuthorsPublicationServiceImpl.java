package es.iessoterohernandez.ProyectoFinalAGO.Services;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.iessoterohernandez.ProyectoFinalAGO.Persistence.Dao.AuthorsPublicationDaoI;
import es.iessoterohernandez.ProyectoFinalAGO.Persistence.Dao.MemberDaoI;
import es.iessoterohernandez.ProyectoFinalAGO.Persistence.Entity.AuthorsPublication;
import es.iessoterohernandez.ProyectoFinalAGO.Persistence.Entity.Member;
import es.iessoterohernandez.ProyectoFinalAGO.Services.Dto.AuthorDto;

/**
 * Servicios. Entidad: Authors-Publication
 * 
 * @author agadelao
 *
 */
@Service
public class AuthorsPublicationServiceImpl implements AuthorsPublicationServiceI {
	
	/** Logger */
	final static Logger LOGGER = LoggerFactory.getLogger(AuthorsPublicationServiceImpl.class);

	@Autowired
	AuthorsPublicationDaoI authorsPublicationsDao;
	
	@Autowired
	MemberDaoI memberDao;
//
//	
//	/**
//	 * Recupera todos los autores almacenadas en BDD
//	 * 
//	 * @return
//	 * @throws Exception
//	 */
//	public List<AuthorDto> getAllAuthorsDataOrderByName() throws Exception {
//
//		LOGGER.info("AuthorsPublicationServiceImpl getAllAuthorsDataOrderByName .- Inicio");
//
//		List<AuthorDto> listaAuthorDto = new ArrayList<AuthorDto>();
//
//		try {
//			List<AuthorsPublication> listaAuthors = authorsPublicationsDao.findOrderByNameAuthorAsc();
//
//			if (listaAuthors != null && !listaAuthors.isEmpty()) {
//
//				for (AuthorsPublication a : listaAuthors) {
//					AuthorDto authorDto = new AuthorDto();
//					
//					
//					authorDto.setNameAuthor(a.getNameAuthor());
//					authorDto.setShortNameAuthor(a.getShortNameAuthor());
//					
//					List<Member> miembros = memberDao.findByShortNameMemberAndActive(a.getShortNameAuthor(),
//							Boolean.TRUE);
//					if (miembros != null && miembros.size() == 1) {
//						authorDto.setIsMember(Boolean.TRUE);
//						authorDto.setIdMember(String.valueOf(miembros.get(0).getIdMember()));
//					} else {
//						authorDto.setIsMember(Boolean.FALSE);
//					}
//					
//					listaAuthorDto.add(authorDto);
//				}
//
//			} else {
//				LOGGER.error("AuthorsPublicationServiceImpl getAllAuthorsDataOrderByName .- Error: Parámetros nulos");
//			}
//		} catch (Exception e) {
//			LOGGER.error("AuthorsPublicationServiceImpl getAllAuthorsDataOrderByName .- Error no controlado al recuperar los autores");
//			throw e;
//		}
//
//		LOGGER.info("AuthorsPublicationServiceImpl getAllAuthorsDataOrderByName .- Fin");
//
//		return listaAuthorDto;
//
//}
	
}
