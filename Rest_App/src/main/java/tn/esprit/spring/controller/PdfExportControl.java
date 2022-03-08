package tn.esprit.spring.controller;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletResponse;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lowagie.text.DocumentException;

import tn.esprit.spring.service.PdfGeneratorService;

@RestController
@RequestMapping("/PdfExportControl")
public class PdfExportControl {
	
	

	private final PdfGeneratorService pdfGeneratorService;

	public PdfExportControl(PdfGeneratorService pdfGeneratorService) {
		super();
		this.pdfGeneratorService = pdfGeneratorService;
	}
	
	// http://localhost:8082/examen/PdfExportControl/pdf-generate/pName
		@GetMapping("/pdf-generate/{pName}")
		public void generatePDF(HttpServletResponse response,@PathVariable("pName") String pName) throws DocumentException, IOException {
			
			response.setContentType("application/pdf");

			DateFormat dateFormatter = new SimpleDateFormat();
			String currentDateTime = dateFormatter.format(new Date());
			
			String headerKey = "Content-Disposition";
			String headerValue = "attachment; filename=pdf_"+ pName+"_Greedy_Solution"+ ".pdf";
			response.setHeader(headerKey, headerValue);
			
			this.pdfGeneratorService.export(response,pName);
		}

		
		

	
}
