package tn.esprit.spring.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import tn.esprit.spring.config.FileUpload;
import tn.esprit.spring.entity.TChat;
import tn.esprit.spring.response.RetourResponse;
import tn.esprit.spring.service.ITChatService;



@RestController
@RequestMapping("/TCHAT")
public class TChatController {
	
	@Autowired
	ITChatService tchatService;
	
	@PostMapping("sendTChat/{idReceiver}")
	public RetourResponse<TChat> sendTChat(@PathVariable("idReceiver") int idReceiver, @RequestBody TChat chat) throws Exception {

		return tchatService.sendTChat(idReceiver, chat);
	}
	
	@PostMapping(value = "sendFile/{idReceiver}", consumes = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.MULTIPART_FORM_DATA_VALUE })
	public RetourResponse<TChat> sendFile(@PathVariable("idReceiver") int idReceiver, @RequestPart("file") MultipartFile file)
			throws Exception {

		TChat chat = new TChat();
		String fileNameRepo = StringUtils.cleanPath(file.getOriginalFilename());
		String uploadDir = "uploads/";
		try {
			String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath().path("/uploads/")
					.path(fileNameRepo).toUriString();
			
			chat.setFile(fileDownloadUri.getBytes());
			FileUpload.saveFile(uploadDir, fileNameRepo, file);
			System.out.println("file url =====>" + fileDownloadUri);
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
		return tchatService.sendTChat(idReceiver, chat);
	}
	
	@GetMapping("receiverTChat/{idReceiver}")
	public List<TChat> receiverTChat(@PathVariable("idReceiver") int idReceiver) throws Exception {
		return tchatService.receiverTChat(idReceiver);
	}
}
