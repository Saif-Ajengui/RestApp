package tn.esprit.spring.service;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import com.lowagie.text.DocumentException;

public interface PdfGeneratorService {

	void export(HttpServletResponse response, String pName) throws DocumentException, IOException;

}
