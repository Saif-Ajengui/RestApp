package tn.esprit.spring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfWriter;

import tn.esprit.spring.controller.TaskControl;
import tn.esprit.spring.entity.Task;

import java.awt.Color;
import java.io.IOException;
import java.util.Calendar;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

@Service
public class PdfGeneratorServiceImpl implements PdfGeneratorService{
	
	@Autowired
	TaskControl taskControl;
	
	@Override
	public void export(HttpServletResponse response, String pName) throws DocumentException, IOException
	{
		Document document = new Document(PageSize.A4 );
		PdfWriter.getInstance(document, response.getOutputStream());
		
		document.open();
		Font fontTitle = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
		fontTitle.setSize(18);
		fontTitle.setColor(Color.RED);
		
		String ch = "Greedy Theorem";
		Paragraph paragraph = new Paragraph(ch, fontTitle);
		paragraph.setAlignment(Paragraph.ALIGN_CENTER);
		
		Font fontParagraph = FontFactory.getFont(FontFactory.HELVETICA);
		fontParagraph.setSize(12);
		
		Font fontSubTitle = FontFactory.getFont(FontFactory.HELVETICA);
		fontSubTitle.setSize(16);
		fontSubTitle.setColor(Color.BLUE);
		
		String ch1 = " I. Greedy Conclusion";
		Paragraph paragraph1 = new Paragraph(ch1, fontSubTitle);
		paragraph1.setAlignment(Paragraph.ALIGN_JUSTIFIED);
		
		
		List<Task> task = taskControl.getTask_SortDDL_ProcessingTasks_byProject(pName);
		Calendar d_task = task.get(0).getStartDateConfirm();
		//String ch2 = "This is paragraph";
		String ch2 = taskControl.getMesurement_scheduleTask_GREEDY_METHOD_ByProject(pName);
		Paragraph paragraph2 = new Paragraph(ch2, fontParagraph);
		paragraph2.setAlignment(Paragraph.ALIGN_JUSTIFIED);
		
		String ch3 = " II. Greedy Plan";
		Paragraph paragraph3 = new Paragraph(ch3, fontSubTitle);
		paragraph3.setAlignment(Paragraph.ALIGN_JUSTIFIED);
		
		String ch4 = taskControl.toStringTask_SortDDL_ProcessingTasks_byProject(pName);
		Paragraph paragraph4 = new Paragraph(ch4, fontParagraph);
		paragraph4.setAlignment(Paragraph.ALIGN_JUSTIFIED);
		
		
		
		document.add(paragraph);
		document.add(paragraph1);
		document.add(paragraph2);
		document.add(paragraph3);
		document.add(paragraph4);
		document.close();
		
	}
}
