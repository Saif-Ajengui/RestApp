package tn.esprit.spring.controller;

import tn.esprit.spring.config.FileUploadUtil;
import tn.esprit.spring.entity.Badge;
import tn.esprit.spring.entity.Deposit;
import tn.esprit.spring.service.*;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping("/Deposit")
public class DepositControl {
	
	@Autowired
	private DepositService depositService;
	
	// http://localhost:8082/examen/Deposit/assignDepositToTask/{idDepo}/{idTask}
    @PutMapping(value = "/assignDepositToTask/{idDepo}/{idTask}") 
	public Deposit assignDepositToTask(@PathVariable("idDepo")int idDepo, @PathVariable("idTask")int idTask) {
    	return depositService.assignDepositToTask(idDepo, idTask);
	}
    
 // http://localhost:8082/examen/Deposit/AddDeposit
 	@PostMapping(value = "/AddDeposit", consumes = { MediaType.MULTIPART_FORM_DATA_VALUE })
 	public Deposit AddDeposit(@RequestPart("img") MultipartFile file) throws IOException {
 		Deposit bd = new Deposit();

 		String fileNameRepo = StringUtils.cleanPath(file.getOriginalFilename());
 		String uploadDir = "files/";
 		System.out.println("file name =" + fileNameRepo);
 		try {

 			String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath().path("/files/")
 					.path(fileNameRepo).toUriString();
 			System.out.println("file url =====>" + fileDownloadUri);


 			bd.setFile(fileDownloadUri.getBytes());
 			FileUploadUtil.saveFile(uploadDir, fileNameRepo, file);

 		} catch (IOException e) {
 			System.out.println(e.getMessage());
 		}

 		return depositService.AddDeposit(bd);

 	}
	
	

}
