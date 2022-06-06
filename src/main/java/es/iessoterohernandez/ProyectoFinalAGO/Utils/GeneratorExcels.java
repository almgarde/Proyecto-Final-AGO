package es.iessoterohernandez.ProyectoFinalAGO.Utils;

import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;

import es.iessoterohernandez.ProyectoFinalAGO.Services.ProCatServiceI;
import es.iessoterohernandez.ProyectoFinalAGO.Services.Dto.AuthorDto;
import es.iessoterohernandez.ProyectoFinalAGO.Services.Dto.Datatables.AdminsDatatableDto;
import es.iessoterohernandez.ProyectoFinalAGO.Services.Dto.Datatables.FacilitiesDatatableDto;
import es.iessoterohernandez.ProyectoFinalAGO.Services.Dto.Datatables.LinksDatatableDto;
import es.iessoterohernandez.ProyectoFinalAGO.Services.Dto.Datatables.MembersDatatableDto;
import es.iessoterohernandez.ProyectoFinalAGO.Services.Dto.Datatables.NewsDatatableDto;
import es.iessoterohernandez.ProyectoFinalAGO.Services.Dto.Datatables.ProCatDatatableDto;
import es.iessoterohernandez.ProyectoFinalAGO.Services.Dto.Datatables.ProjectsDatatableDto;
import es.iessoterohernandez.ProyectoFinalAGO.Services.Dto.Datatables.PublicationsDatatableDto;
import es.iessoterohernandez.ProyectoFinalAGO.Services.Dto.Datatables.TechCatDatatableDto;
import es.iessoterohernandez.ProyectoFinalAGO.Services.Dto.Datatables.ThesisDatatableDto;

public class GeneratorExcels {

	@Autowired
	ProCatServiceI proCatService;

	private XSSFWorkbook workbook;
	private XSSFSheet hoja;

	public GeneratorExcels() {
		workbook = new XSSFWorkbook();
	}

	public void exportExcelNews(HttpServletResponse response, List<NewsDatatableDto> listaNews) throws Exception {
		hoja = workbook.createSheet("News");

		Row filaTitulo = hoja.createRow(0);

		Cell celda = filaTitulo.createCell(0);
		celda.setCellValue("DATOS DE NOTICIAS");

		Row filaData = hoja.createRow(2);

		String[] columnas = { "ID", "Título", "Imagen", "Abstract", "Admin", "Fecha de registro", "Activo",
				"Contenido" };

		for (int i = 0; i < columnas.length; i++) {

			celda = filaData.createCell(i);
			celda.setCellValue(columnas[i]);
			hoja.autoSizeColumn(i);
		}

		int numFila = 4;
		for (NewsDatatableDto news : listaNews) {
			filaData = hoja.createRow(numFila);

			filaData.createCell(0).setCellValue(news.getIdNews());
			filaData.createCell(1).setCellValue(news.getTitleNews());
			filaData.createCell(2).setCellValue(news.getImageNews());
			filaData.createCell(3).setCellValue(news.getAbstractNews());
			filaData.createCell(4).setCellValue(news.getAdmin());
			filaData.createCell(5).setCellValue(news.getDate());
			if (news.getActive() == "true") {
				filaData.createCell(6).setCellValue("Si");
			} else {
				filaData.createCell(6).setCellValue("No");
			}
			filaData.createCell(7).setCellValue(news.getContentNews());

			numFila++;
		}

		ServletOutputStream outputStream = response.getOutputStream();
		workbook.write(outputStream);
		workbook.close();

		outputStream.close();
	}

	public void exportExcelProCat(HttpServletResponse response, List<ProCatDatatableDto> listaProCat) throws Exception {
		hoja = workbook.createSheet("ProCat");

		Row filaTitulo = hoja.createRow(0);

		Cell celda = filaTitulo.createCell(0);
		celda.setCellValue("DATOS DE CATEGORÍAS PROFESIONALES");

		Row filaData = hoja.createRow(2);

		String[] columnas = { "ID", "Categoría profesional", "Admin", "Fecha de registro", "Activo" };

		for (int i = 0; i < columnas.length; i++) {

			celda = filaData.createCell(i);
			celda.setCellValue(columnas[i]);
			hoja.autoSizeColumn(i);
		}

		int numFila = 4;
		for (ProCatDatatableDto proCat : listaProCat) {
			filaData = hoja.createRow(numFila);

			filaData.createCell(0).setCellValue(proCat.getIdProCat());
			filaData.createCell(1).setCellValue(proCat.getNameProCat());
			filaData.createCell(2).setCellValue(proCat.getAdmin());
			filaData.createCell(3).setCellValue(proCat.getDate());
			if (proCat.getActive() == "true") {
				filaData.createCell(4).setCellValue("Si");
			} else {
				filaData.createCell(4).setCellValue("No");
			}

			numFila++;
		}

		ServletOutputStream outputStream = response.getOutputStream();
		workbook.write(outputStream);
		workbook.close();

		outputStream.close();
	}

	public void exportExcelTechCat(HttpServletResponse response, List<TechCatDatatableDto> listaTechCat)
			throws Exception {
		hoja = workbook.createSheet("TechCat");

		Row filaTitulo = hoja.createRow(0);

		Cell celda = filaTitulo.createCell(0);
		celda.setCellValue("DATOS DE CATEGORÍAS TÉCNICAS");

		Row filaData = hoja.createRow(2);

		String[] columnas = { "ID", "Categoría técnica", "Admin", "Fecha de registro", "Activo" };

		for (int i = 0; i < columnas.length; i++) {

			celda = filaData.createCell(i);
			celda.setCellValue(columnas[i]);
			hoja.autoSizeColumn(i);
		}

		int numFila = 4;
		for (TechCatDatatableDto techCat : listaTechCat) {
			filaData = hoja.createRow(numFila);

			filaData.createCell(0).setCellValue(techCat.getIdTechCat());
			filaData.createCell(1).setCellValue(techCat.getNameTechCat());
			filaData.createCell(2).setCellValue(techCat.getAdmin());
			filaData.createCell(3).setCellValue(techCat.getDate());
			if (techCat.getActive() == "true") {
				filaData.createCell(4).setCellValue("Si");
			} else {
				filaData.createCell(4).setCellValue("No");
			}

			numFila++;
		}

		ServletOutputStream outputStream = response.getOutputStream();
		workbook.write(outputStream);
		workbook.close();

		outputStream.close();
	}

	public void exportExcelFacilities(HttpServletResponse response, List<FacilitiesDatatableDto> listaFacilities)
			throws Exception {
		hoja = workbook.createSheet("Servicios de investigación");

		Row filaTitulo = hoja.createRow(0);

		Cell celda = filaTitulo.createCell(0);
		celda.setCellValue("DATOS DE SERVICIOS DE INVESTIGACIÓN");

		Row filaData = hoja.createRow(2);

		String[] columnas = { "ID", "Nombre", "Categoría técnica", "Foto", "Admin", "Fecha de registro", "Activo",
				"Características" };

		for (int i = 0; i < columnas.length; i++) {

			celda = filaData.createCell(i);
			celda.setCellValue(columnas[i]);
			hoja.autoSizeColumn(i);
		}

		int numFila = 4;
		for (FacilitiesDatatableDto facility : listaFacilities) {
			filaData = hoja.createRow(numFila);

			filaData.createCell(0).setCellValue(facility.getIdFacility());
			filaData.createCell(1).setCellValue(facility.getNameFacility());
			filaData.createCell(2).setCellValue(facility.getNameTechCat());
			filaData.createCell(3).setCellValue(facility.getPhotoFacility());
			filaData.createCell(4).setCellValue(facility.getAdmin());
			filaData.createCell(5).setCellValue(facility.getDate());
			if (facility.getActive() == "true") {
				filaData.createCell(6).setCellValue("Si");
			} else {
				filaData.createCell(6).setCellValue("No");
			}

			filaData.createCell(7).setCellValue(facility.getFeaturesFacility());
			numFila++;
		}

		ServletOutputStream outputStream = response.getOutputStream();
		workbook.write(outputStream);
		workbook.close();

		outputStream.close();
	}

	public void exportExcelMembers(HttpServletResponse response, List<MembersDatatableDto> listaMembers)
			throws Exception {
		hoja = workbook.createSheet("Miembros");

		Row filaTitulo = hoja.createRow(0);

		Cell celda = filaTitulo.createCell(0);
		celda.setCellValue("DATOS DE MIEMBROS");

		Row filaData = hoja.createRow(2);

		String[] columnas = { "ID", "Nombre", "Nombre abreviado", "DNI", "Email", "Teléfono", "Categoría Profesional",
				"Foto", "ResearchID", "ScopusID", "OrcID", "Admin", "Fecha de registro", "Activo", "Trayectoria" };

		for (int i = 0; i < columnas.length; i++) {

			celda = filaData.createCell(i);
			celda.setCellValue(columnas[i]);
			hoja.autoSizeColumn(i);
		}

		int numFila = 4;
		for (MembersDatatableDto member : listaMembers) {
			filaData = hoja.createRow(numFila);

			filaData.createCell(0).setCellValue(member.getIdMember());
			filaData.createCell(1).setCellValue(member.getNameMember());
			filaData.createCell(2).setCellValue(member.getShortNameMember());
			filaData.createCell(3).setCellValue(member.getDniMember());
			filaData.createCell(4).setCellValue(member.getEmailMember());
			filaData.createCell(5).setCellValue(member.getPhoneMember());
			filaData.createCell(6).setCellValue(member.getNameProCat());
			filaData.createCell(7).setCellValue(member.getPhotoMember());
			filaData.createCell(8).setCellValue(member.getReseachIdMember());
			filaData.createCell(9).setCellValue(member.getScopusIdMember());
			filaData.createCell(10).setCellValue(member.getOrcIdMember());
			filaData.createCell(11).setCellValue(member.getAdmin());
			filaData.createCell(12).setCellValue(member.getDate());
			if (member.getActive() == "true") {
				filaData.createCell(13).setCellValue("Si");
			} else {
				filaData.createCell(13).setCellValue("No");
			}
			filaData.createCell(14).setCellValue(member.getTrajectoryMember());
			numFila++;
		}

		ServletOutputStream outputStream = response.getOutputStream();
		workbook.write(outputStream);
		workbook.close();

		outputStream.close();
	}

	public void exportExcelProjects(HttpServletResponse response, List<ProjectsDatatableDto> listaProjects)
			throws Exception {
		hoja = workbook.createSheet("Proyectos");

		Row filaTitulo = hoja.createRow(0);

		Cell celda = filaTitulo.createCell(0);
		celda.setCellValue("DATOS DE PROYECTOS");

		Row filaData = hoja.createRow(2);

		String[] columnas = { "ID", "Título", "Imagen", "Admin", "Fecha de registro", "Activo", "Descripción" };

		for (int i = 0; i < columnas.length; i++) {

			celda = filaData.createCell(i);
			celda.setCellValue(columnas[i]);
			hoja.autoSizeColumn(i);
		}

		int numFila = 4;
		for (ProjectsDatatableDto project : listaProjects) {
			filaData = hoja.createRow(numFila);

			filaData.createCell(0).setCellValue(project.getIdProject());
			filaData.createCell(1).setCellValue(project.getTitleProject());
			filaData.createCell(2).setCellValue(project.getImageProject());
			filaData.createCell(3).setCellValue(project.getAdmin());
			filaData.createCell(4).setCellValue(project.getDate());
			if (project.getActive() == "true") {
				filaData.createCell(5).setCellValue("Si");
			} else {
				filaData.createCell(5).setCellValue("No");
			}
			filaData.createCell(6).setCellValue(project.getDescriptionProject());
			numFila++;
		}

		ServletOutputStream outputStream = response.getOutputStream();
		workbook.write(outputStream);
		workbook.close();

		outputStream.close();
	}

	public void exportExcelThesis(HttpServletResponse response, List<ThesisDatatableDto> listaThesis) throws Exception {
		hoja = workbook.createSheet("Tesis");

		Row filaTitulo = hoja.createRow(0);

		Cell celda = filaTitulo.createCell(0);
		celda.setCellValue("DATOS DE TESIS");

		Row filaData = hoja.createRow(2);

		String[] columnas = { "ID", "Doctor", "Título", "Portada", "Fecha de defensa", "Director", "Co-director", "URL",
				"Admin", "Fecha de registro", "Activo" };

		for (int i = 0; i < columnas.length; i++) {

			celda = filaData.createCell(i);
			celda.setCellValue(columnas[i]);
			hoja.autoSizeColumn(i);
		}

		int numFila = 4;
		for (ThesisDatatableDto thesis : listaThesis) {
			filaData = hoja.createRow(numFila);

			filaData.createCell(0).setCellValue(thesis.getIdThesis());
			filaData.createCell(1).setCellValue(thesis.getDoctorThesis());
			filaData.createCell(2).setCellValue(thesis.getTitleThesis());
			filaData.createCell(3).setCellValue(thesis.getCoverPageThesis());
			filaData.createCell(4).setCellValue(thesis.getDateDefenseThesis());
			filaData.createCell(5).setCellValue(thesis.getDirectorThesis());
			filaData.createCell(6).setCellValue(thesis.getCoDirectorThesis());
			filaData.createCell(7).setCellValue(thesis.getUrlThesis());
			filaData.createCell(8).setCellValue(thesis.getAdmin());
			filaData.createCell(39).setCellValue(thesis.getDate());
			if (thesis.getActive() == "true") {
				filaData.createCell(10).setCellValue("Si");
			} else {
				filaData.createCell(10).setCellValue("No");
			}

			numFila++;
		}

		ServletOutputStream outputStream = response.getOutputStream();
		workbook.write(outputStream);
		workbook.close();

		outputStream.close();
	}

	public void exportExcelLinks(HttpServletResponse response, List<LinksDatatableDto> listaLinks) throws Exception {
		hoja = workbook.createSheet("Links");

		Row filaTitulo = hoja.createRow(0);

		Cell celda = filaTitulo.createCell(0);
		celda.setCellValue("DATOS DE LINKS");

		Row filaData = hoja.createRow(2);

		String[] columnas = { "ID", "Título", "Imagen", "URL", "Admin", "Fecha de registro", "Activo" };

		for (int i = 0; i < columnas.length; i++) {

			celda = filaData.createCell(i);
			celda.setCellValue(columnas[i]);
			hoja.autoSizeColumn(i);
		}

		int numFila = 4;
		for (LinksDatatableDto link : listaLinks) {
			filaData = hoja.createRow(numFila);

			filaData.createCell(0).setCellValue(link.getIdLink());
			filaData.createCell(1).setCellValue(link.getTitleLink());
			filaData.createCell(2).setCellValue(link.getImageLink());
			filaData.createCell(3).setCellValue(link.getUrlLink());
			filaData.createCell(4).setCellValue(link.getAdmin());
			filaData.createCell(5).setCellValue(link.getDate());
			if (link.getActive() == "true") {
				filaData.createCell(6).setCellValue("Si");
			} else {
				filaData.createCell(6).setCellValue("No");
			}

			numFila++;
		}

		ServletOutputStream outputStream = response.getOutputStream();
		workbook.write(outputStream);
		workbook.close();

		outputStream.close();
	}

	public void exportExcelPublications(HttpServletResponse response, List<PublicationsDatatableDto> listaPublications)
			throws Exception {
		hoja = workbook.createSheet("Publicaciones");

		Row filaTitulo = hoja.createRow(0);

		Cell celda = filaTitulo.createCell(0);
		celda.setCellValue("DATOS DE PUBLICACIONES");

		Row filaData = hoja.createRow(2);

		String[] columnas = { "ID", "Título", "Autores", "Revista", "DOI", "Año de publicación", "Admin",
				"Fecha de registro", "Activo" };

		for (int i = 0; i < columnas.length; i++) {

			celda = filaData.createCell(i);
			celda.setCellValue(columnas[i]);
			hoja.autoSizeColumn(i);
		}

		int numFila = 4;
		for (PublicationsDatatableDto publication : listaPublications) {
			filaData = hoja.createRow(numFila);

			filaData.createCell(0).setCellValue(publication.getIdPublication());
			filaData.createCell(1).setCellValue(publication.getTitlePublication());

			String autores = "";
			int numAutores = 0;

			for (AuthorDto autor : publication.getAuthorsPublication()) {
				autores = autores.concat(autor.getNameAuthor());
				numAutores++;
				if (numAutores < publication.getAuthorsPublication().size()) {
					autores = autores.concat(", ");
				}
			}

			filaData.createCell(2).setCellValue(autores);
			filaData.createCell(3).setCellValue(publication.getJournalPublication());
			filaData.createCell(4).setCellValue(publication.getDoiPublication());
			filaData.createCell(5).setCellValue(publication.getYearPublication());
			filaData.createCell(6).setCellValue(publication.getAdmin());
			filaData.createCell(7).setCellValue(publication.getDate());
			if (publication.getActive() == "true") {
				filaData.createCell(8).setCellValue("Si");
			} else {
				filaData.createCell(8).setCellValue("No");
			}

			numFila++;
		}

		ServletOutputStream outputStream = response.getOutputStream();
		workbook.write(outputStream);
		workbook.close();

		outputStream.close();
	}

	public void exportExcelAdmins(HttpServletResponse response, List<AdminsDatatableDto> listaAdmins) throws Exception {
		hoja = workbook.createSheet("Admins");

		Row filaTitulo = hoja.createRow(0);

		Cell celda = filaTitulo.createCell(0);
		celda.setCellValue("DATOS DE ADMINISTRADORES");

		Row filaData = hoja.createRow(2);

		String[] columnas = { "ID", "Nombre y apellidos", "Email", "Nombre de usuario" };

		for (int i = 0; i < columnas.length; i++) {

			celda = filaData.createCell(i);
			celda.setCellValue(columnas[i]);
			hoja.autoSizeColumn(i);
		}

		int numFila = 4;
		for (AdminsDatatableDto admin : listaAdmins) {
			filaData = hoja.createRow(numFila);

			filaData.createCell(0).setCellValue(admin.getIdAdmin());
			filaData.createCell(1).setCellValue(admin.getNameAdmin());
			filaData.createCell(2).setCellValue(admin.getEmailAdmin());
			filaData.createCell(3).setCellValue(admin.getUsernameAdmin());

			numFila++;
		}

		ServletOutputStream outputStream = response.getOutputStream();
		workbook.write(outputStream);
		workbook.close();

		outputStream.close();
	}

}
