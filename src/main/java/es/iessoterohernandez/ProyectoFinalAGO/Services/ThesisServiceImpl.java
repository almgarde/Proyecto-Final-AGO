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
import es.iessoterohernandez.ProyectoFinalAGO.Services.Dto.ThesisDto;
import es.iessoterohernandez.ProyectoFinalAGO.Services.Dto.Datatables.ThesisDatatableDto;

/**
 * Servicios. Entidad: Proyectos
 * 
 * @author agadelao
 *
 */
@Service
public class ThesisServiceImpl implements ThesisServiceI {

	final static Logger LOGGER = LoggerFactory.getLogger(ThesisServiceImpl.class);

	SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

	@Autowired
	ThesisDaoI thesisDao;

	/**
	 * Recupera todos los datos de las tesis almacenados en BDD
	 * 
	 * @return List<ThesisDatatableDto>
	 * @throws Exception
	 */
	@Override
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
				LOGGER.error("ThesisServiceImpl getAllThesisData .- Error: No existen tesis registradas");
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
	 * @param thesisData
	 * @param imageThesis
	 * @return Thesis
	 * @throws Exception
	 */
	@Override
	public Thesis addThesis(Map<String, String> thesisData, String imageThesis) throws Exception {

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
				t.setUpdateAdmin(thesisData.get("inputUser"));
				t.setUpdateDate(new Date());

				Thesis thesisSaved = thesisDao.save(t);
				t.setCoverPageThesis(thesisSaved.getIdThesis() + imageThesis);
				thesisSaved2 = thesisDao.save(t);

				LOGGER.info("ThesisServiceImpl addThesis .- Tesis almacenada correctamente");

			} else {
				LOGGER.error("ThesisServiceImpl addThesis .- Error: Parámetros de entrada nulos");
			}

		} catch (Exception e) {
			LOGGER.error("ThesisServiceImpl addThesis .- Error no controlado al añadir la tesis");
			throw e;
		}

		LOGGER.info("ThesisServiceImpl addThesis .- Fin");

		return thesisSaved2;

	}

	/**
	 * Actualiza los datos de una tesis almacenada en BDD
	 * 
	 * @param thesisData
	 * @return Thesis
	 * @throws Exception
	 */
	@Override
	public Thesis updateThesis(Map<String, String> thesisData) throws Exception {

		LOGGER.info("ThesisServiceImpl updateThesis .- Inicio");

		Thesis thesisUpdated = null;

		try {

			if (thesisData != null && !thesisData.isEmpty()) {

				Thesis t = thesisDao.findByIdThesis(Long.parseLong(thesisData.get("idThesis")));

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

					t.setUpdateAdmin(thesisData.get("inputUser"));
					t.setUpdateDate(new Date());

					thesisUpdated = thesisDao.save(t);

				} else {
					LOGGER.error("ThesisServiceImpl updateThesis .- Tesis no encontrada");
				}

				LOGGER.info("ThesisServiceImpl updateThesis .- Tesis actualizada correctamente");

			} else {
				LOGGER.error("ThesisServiceImpl updateThesis .- Error: Parámetros de entrada nulos");
			}

		} catch (Exception e) {
			LOGGER.error("ThesisServiceImpl updateThesis .- Error no controlado al actualizar la tesis");
			throw e;
		}

		LOGGER.info("ThesisServiceImpl updateThesis .- Fin");

		return thesisUpdated;

	}

	/**
	 * Actualiza la portada de una tesis almacenada en BDD
	 * 
	 * @param thesisData
	 * @param photoThesis
	 * @return Thesis
	 * @throws Exception
	 */
	@Override
	public Thesis updateCoverPageThesis(Map<String, String> thesisData, String coverPageThesis) throws Exception {

		LOGGER.info("ThesisServiceImpl updateCoverPageThesis .- Inicio");

		Thesis thesisPhotoUpdated = null;

		try {

			if (thesisData != null && !thesisData.isEmpty() && coverPageThesis != null) {

				Thesis t = thesisDao.findByIdThesis(Long.parseLong(thesisData.get("idThesis")));

				if (t != null) {
					t.setUpdateAdmin(thesisData.get("inputUser"));
					t.setCoverPageThesis(t.getIdThesis() + coverPageThesis);
					thesisPhotoUpdated = thesisDao.save(t);
				} else {
					LOGGER.error("ThesisServiceImpl updateCoverPageThesis .- Tesis no encontrada");
				}

				LOGGER.info("ThesisServiceImpl updateCoverPageThesis .- Tesis actualizada correctamente");

			} else {
				LOGGER.error("ThesisServiceImpl updateCoverPageThesis .- Error: Parámetros nulos");
			}

		} catch (Exception e) {
			LOGGER.error("ThesisServiceImpl updateCoverPageThesis .- Error no controlado al actualizar la tesis");
			throw e;
		}

		LOGGER.info("ThesisServiceImpl updateThesis .- Fin");

		return thesisPhotoUpdated;

	}

	/**
	 * Elimina una tesis almacenada en BDD
	 * 
	 * @param thesisData
	 * @throws Exception
	 */
	@Override
	public void deleteThesis(Map<String, String> thesisData) throws Exception {

		LOGGER.info("ThesisServiceImpl deleteThesis .- Inicio");

		try {

			if (thesisData != null && !thesisData.isEmpty()) {

				Thesis f = thesisDao.findByIdThesis(Long.parseLong(thesisData.get("idThesis")));

				if (f != null) {
					thesisDao.delete(f);
				}

				LOGGER.info("ThesisServiceImpl deleteThesis .- Tesis eliminada correctamente");

			} else {
				LOGGER.error("ThesisServiceImpl deleteThesis .- Error: Parámetros de entrada nulos");
			}

		} catch (Exception e) {
			LOGGER.error("ThesisServiceImpl deleteThesis .- Error no controlado al eliminar la tesis");
			throw e;
		}

		LOGGER.info("ThesisServiceImpl deletePublications .- Fin");

	}

	/**
	 * Recupera las tesis activas
	 * 
	 * @return List<ThesisDto>
	 * @throws Exception
	 */
	@Override
	public List<ThesisDto> getAllThesisActive() throws Exception {

		LOGGER.info("ThesisServiceImpl getAllThesisActive .- Inicio");

		List<ThesisDto> listaThesisDto = null;

		try {

			List<Thesis> listThesis = thesisDao.findByActive(Boolean.TRUE);

			if (listThesis != null && !listThesis.isEmpty()) {

				listaThesisDto = new ArrayList<ThesisDto>();

				for (Thesis t : listThesis) {

					ThesisDto thesisDto = new ThesisDto();
					thesisDto.setDoctorThesis(t.getDoctorThesis());
					thesisDto.setTitleThesis(t.getTitleThesis());
					thesisDto.setCoverPageThesis(t.getCoverPageThesis());
					thesisDto.setDateDefenseThesis(t.getDateDefenseThesis());
					thesisDto.setDirectorThesis(t.getDirectorThesis());
					thesisDto.setCoDirectorThesis(t.getCoDirectorThesis());
					thesisDto.setUrlThesis(t.getUrlThesis());

					listaThesisDto.add(thesisDto);
				}

			} else {
				LOGGER.error("ThesisServiceImpl getAllThesisActive .- Error: No existen tesis activas registrados");
			}

		} catch (Exception e) {
			LOGGER.error("ThesisServiceImpl getAllThesisActive .- Error no controlado al recuperar las tesis");
			throw e;
		}

		LOGGER.info("ThesisServiceImpl getAllThesisActive .- Fin");

		return listaThesisDto;

	}

}
