package com.shopme.admin.user.export;

import java.awt.Color;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import com.lowagie.text.Document;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import com.shopme.common.entity.User;

public class UserPDFExporter extends AbstractExporter {
	public void export(List<User> listUser, HttpServletResponse response) throws IOException {
		super.setResponseHeader(response, "application/pdf", ".pdf");
		
		Document document= new Document(PageSize.A4);
		PdfWriter.getInstance(document, response.getOutputStream());
		
		document.open();
		
		Font font=FontFactory.getFont(FontFactory.HELVETICA_BOLD);
		font.setSize(16);
		font.setColor(Color.BLUE);
		
		Paragraph paragraph= new Paragraph("List Of Users",font);
		paragraph.setAlignment(Paragraph.ALIGN_CENTER);
		
		document.add(paragraph);
		PdfPTable table= new PdfPTable(6);
		table.setWidthPercentage(100f);
		table.setSpacingBefore(10);
		table.setWidths(new float[] {1.5f,3.5f,3.0f,3.0f,3.0f,1.5f});
		writeTableHEader(table);
		writeTableData(table,listUser);
		
		document.add(table);
		
		
		
		document.close();
		
		
	}

	private void writeTableData(PdfPTable table, List<User> listUser) {
		for(User user: listUser) {
			table.addCell(String.valueOf(user.getId()));
			table.addCell(String.valueOf(user.getEmail()));
			table.addCell(String.valueOf(user.getFirstName()));
			table.addCell(String.valueOf(user.getLastName()));
			table.addCell(String.valueOf(user.getRoles()));
			table.addCell(String.valueOf(user.isEnabled()));
		}
		
	}

	private void writeTableHEader(PdfPTable table) {
		PdfPCell cell= new PdfPCell();
		cell.setBackgroundColor(Color.BLUE);
		cell.setPadding(5);
		
		Font font=FontFactory.getFont(FontFactory.HELVETICA);
		font.setSize(16);
		font.setColor(Color.WHITE);
		
		cell.setPhrase(new Phrase("User-ID",font));
		table.addCell(cell);
		
		cell.setPhrase(new Phrase("Email",font));
		table.addCell(cell);
		
		cell.setPhrase(new Phrase("First-Name",font));
		table.addCell(cell);
		
		cell.setPhrase(new Phrase("Last-Name",font));
		table.addCell(cell);
		
		cell.setPhrase(new Phrase("Roles",font));
		table.addCell(cell);
		
		cell.setPhrase(new Phrase("Status",font));
		table.addCell(cell);
		
	}

}
