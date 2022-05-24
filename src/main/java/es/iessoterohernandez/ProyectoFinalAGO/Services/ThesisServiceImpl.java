package es.iessoterohernandez.ProyectoFinalAGO.Services;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.iessoterohernandez.ProyectoFinalAGO.Persistence.Dao.ThesisDaoI;
import es.iessoterohernandez.ProyectoFinalAGO.Persistence.Entity.Thesis;
import es.iessoterohernandez.ProyectoFinalAGO.Services.Dto.Datatables.ThesisDatatableDto;

/**
 * Servicios. Entidad: Proyectos
 * 
 * @author agadelao
 *
 */
@Service
public class ThesisServiceImpl implements ThesisServiceI {
	
	/** Logger */
	final static Logger LOGGER = LoggerFactory.getLogger(ThesisServiceImpl.class);
	
	SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

	@Autowired
	ThesisDaoI thesisDao;
	
	/**
	 * Recupera todos las tesis almacenadas en BDD
	 * 
	 * @return
	 * @throws Exception
	 */
	public List<ThesisDatatableDto> getAllThesisData() throws Exception {

		LOGGER.info("ThesisServiceImpl getAllThesisData .- Inicio");

		List<ThesisDatatableDto> listaThesisDatatableDto = new ArrayList<ThesisDatatableDto>();

		try {
			List<Thesis> listThesis = thesisDao.findAll();

			if (listThesis != null && !listThesis.isEmpty()) {

				for (Thesis t : listThesis) {
					ThesisDatatableDto thesisDatatableDto = new ThesisDatatableDto();
					
					thesisDatatableDto.setIdThesis(String.valueOf(t.getIdThesis()));
					thesisDatatableDto.setDoctorThesis(t.getDoctorThesis());
					thesisDatatableDto.setTitleThesis(t.getTitleThesis());
					thesisDatatableDto.setCoverPageThesis(t.getCoverPageThesis());
					thesisDatatableDto.setDateDefenseThesis(dateFormat.format(t.getDateDefenseThesis()));
					thesisDatatableDto.setDirectorThesis(t.getDirectorThesis());
					thesisDatatableDto.setCoDirectorThesis(t.getCoDirectorThesis());
					thesisDatatableDto.setUrlThesis(t.getUrlThesis());
					if (t.getActive()) {
						thesisDatatableDto.setActive("true");
					} else {
						thesisDatatableDto.setActive("false");
					}

					thesisDatatableDto.setAdmin(t.getUpdateAdmin());
					thesisDatatableDto.setDate(String.format("%1$td/%1$tm/%1$tY", t.getUpdateDate()));
					listaThesisDatatableDto.add(thesisDatatableDto);
				}

			} else {
				LOGGER.error("ThesisServiceImpl getAllThesisData .- Error: Parámetros nulos");
			}
		} catch (Exception e) {
			LOGGER.error("ThesisServiceImpl getAllThesisData .- Error no controlado al recuperar las tesis");
			throw e;
		}

		LOGGER.info("ThesisServiceImpl getAllThesisData .- Fin");

		return listaThesisDatatableDto;

	}

	/**
	 * Almacena una tesis en BDD
	 * 
	 * @param addNewsFormDto
	 * @param imageNews
	 */
	@Override

	public Thesis addThesis(Map<String, String> thesisData , String imageThesis) throws Exception {

		LOGGER.info("ThesisServiceImpl addThesis .- Inicio");
		
		Thesis thesisSaved2 = null;

		try {
			if (thesisData != null && !thesisData.isEmpty() && !StringUtils.isEmpty(imageThesis)) {

				Thesis t = new Thesis();

				t.setDoctorThesis(thesisData.get("doctorThesis"));
				t.setTitleThesis(thesisData.get("titleThesis"));
				t.setDateDefenseThesis(dateFormat.parse(thesisData.get("dateDefenseThesis")));
				t.setDirectorThesis(thesisData.get("directorThesis"));
				t.setCoDirectorThesis(thesisData.get("coDirectorThesis"));
				t.setUrlThesis(thesisData.get("urlThesis"));
				if (Integer.parseInt(thesisData.get("active")) == 1) {
					t.setActive(true);
				} else {
					t.setActive(false);
				}

				t.setCoverPageThesis(imageThesis);
				t.setUpdateAdmin("agadelao");
				t.setUpdateDate(new Date());
				
				Thesis thesisSaved = thesisDao.save(t);
				t.setCoverPageThesis(thesisSaved.getIdThesis() + imageThesis);
				thesisSaved2 = thesisDao.save(t);

				
				LOGGER.info("ThesisServiceImpl addThesis .- Tesis almacenado correctamente");

			} else {
				LOGGER.error("ThesisServiceImpl addThesis .- Error: Parámetros nulos");
			}
		} catch (Exception e) {
			LOGGER.error("ThesisServiceImpl addThesis .- Error no controlado al añadir la tesis");
			throw e;
		}

		LOGGER.info("ThesisServiceImpl addThesis .- Fin");
		
		return thesisSaved2;

	}
	
	/**
	 * Almacena una noticia en BDD
	 * 
	 * @param addNewsFormDto
	 * @param imageNews
	 */
	@Override
	public Thesis updateThesis(Map<String, String> thesisData) throws Exception {

		LOGGER.info("ThesisServiceImpl updateThesis .- Inicio");

		Thesis thesisUpdated = null;

		try {
			if (thesisData != null && !thesisData.isEmpty() ) {

				Thesis t = thesisDao
						.findByIdThesis(Long.parseLong(thesisData.get("idThesis")));

				if (t != null) {
					
					t.setDoctorThesis(thesisData.get("doctorThesis"));
					t.setTitleThesis(thesisData.get("titleThesis"));
					t.setDateDefenseThesis(dateFormat.parse(thesisData.get("dateDefenseThesis")));
					t.setDirectorThesis(thesisData.get("directorThesis"));
					t.setCoDirectorThesis(thesisData.get("coDirectorThesis"));
					t.setUrlThesis(thesisData.get("urlThesis"));
					if (Integer.parseInt(thesisData.get("active")) == 1) {
						t.setActive(true);
					} else {
						t.setActive(false);
					}
					
					t.setUpdateAdmin("agadelao");
					t.setUpdateDate(new Date());

					thesisUpdated = thesisDao.save(t);
				} else {
					LOGGER.error("ThesisServiceImpl updateThesis .- Equipo no encontrado");
				}
				LOGGER.info("ThesisServiceImpl updateThesis .- Equipo actualizado correctamente");

			} else {
				LOGGER.error("ThesisServiceImpl updateThesis .- Error: Parámetros nulos");
			}
		} catch (Exception e) {
			LOGGER.error("ThesisServiceImpl updateThesis .- Error no controlado al actualizar la publicación");
			throw e;
		}

		LOGGER.info("ThesisServiceImpl updateThesis .- Fin");

		return thesisUpdated;

	}
	
	/**
	 * Almacena una noticia en BDD
	 * 
	 * @param addNewsFormDto
	 * @param imageNews
	 */
	@Override
	public Thesis updateCoverPageThesis(Map<String, String> thesisData, String coverPageThesis) throws Exception {

		LOGGER.info("ThesisServiceImpl updateThesis .- Inicio");

		Thesis thesisPhotoUpdated = null;

		try {
			if (thesisData != null && !thesisData.isEmpty() && coverPageThesis != null) {

				Thesis t = thesisDao
						.findByIdThesis(Long.parseLong(thesisData.get("idThesis")));

				if (t != null) {

					t.setCoverPageThesis(t.getIdThesis() + coverPageThesis);
					thesisPhotoUpdated = thesisDao.save(t);
				} else {
					LOGGER.error("ThesisServiceImpl updateThesis .- Equipo no encontrado");
				}
				LOGGER.info("ThesisServiceImpl updateThesis .- Equipo actualizado correctamente");

			} else {
				LOGGER.error("ThesisServiceImpl updateThesis .- Error: Parámetros nulos");
			}
		} catch (Exception e) {
			LOGGER.error("ThesisServiceImpl updateThesis .- Error no controlado al actualizar la publicación");
			throw e;
		}

		LOGGER.info("ThesisServiceImpl updateThesis .- Fin");

		return thesisPhotoUpdated;

	}
	
	@Override
	public void deleteThesis(Map<String, String> thesisData) throws Exception {

		LOGGER.info("PublicationsServiceImpl deletePublications .- Inicio");


		try {
			if (thesisData != null && !thesisData.isEmpty()) {

				Thesis f = thesisDao
						.findByIdThesis(Long.parseLong(thesisData.get("idThesis")));

				if (f != null) {

					thesisDao.delete(f);

				}
				LOGGER.info("PublicationsServiceImpl deletePublications .- Publicación eliminada correctamente");

			} else {
				LOGGER.error("PublicationsServiceImpl deletePublications .- Error: Parámetros nulos");
			}
		} catch (Exception e) {
			LOGGER.error("PublicationsServiceImpl deletePublications .- Error no controlado al eliminar la publicación");
			throw e;
		}

		LOGGER.info("PublicationsServiceImpl deletePublications .- Fin");


	}

}
