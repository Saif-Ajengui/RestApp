package tn.esprit.spring.controller;


import tn.esprit.spring.config.FileUploadUtil;
import tn.esprit.spring.entity.Badge;
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
@RequestMapping("/Badge")
public class BadgeControl {

	@Autowired
	private BadgeService badgeService;

	// http://localhost:8082/examen/Badge/AjoutBadge
	@PostMapping(value = "/AjoutBadge", consumes = { MediaType.MULTIPART_FORM_DATA_VALUE })
	public Badge AjoutBadge(@RequestParam("nbVoteYes") int nbVoteYes, @RequestParam("nbVoteNo") int nbVoteNo,
			@RequestParam("nbVoteABS") int nbVoteABS, @RequestPart("img") MultipartFile file) throws IOException {
		Badge bd = new Badge();

		String fileNameRepo = StringUtils.cleanPath(file.getOriginalFilename());
		String uploadDir = "uploads/";
		System.out.println("image name =" + fileNameRepo);
		try {
			// ev= objectMapper.readValue(evJson, Event.class);
			String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath().path("/uploads/")
					.path(fileNameRepo).toUriString();
			System.out.println("file url =====>" + fileDownloadUri);

			bd.setNbVoteYes(nbVoteYes);
			bd.setNbVoteNo(nbVoteNo);
			bd.setNbVoteABS(nbVoteABS);
			bd.setImg(fileDownloadUri.getBytes());
			FileUploadUtil.saveFile(uploadDir, fileNameRepo, file);

		} catch (IOException e) {
			System.out.println(e.getMessage());
		}

		return badgeService.ajouterBadge(bd);

	}

	// http://localhost:8082/examen/Badge/Badges
	@GetMapping("/Badges")
	@ResponseBody
	public List<Badge> findAllBadges() {
		return badgeService.getBadges();
	}

	// http://localhost:8082/examen/Badge/{id}
	@GetMapping("/{id}")
	@ResponseBody
	public Badge findBadgeById(@PathVariable("id") int id) {
		return badgeService.getBadgeById(id);
	}

	// http://localhost:8082/examen/Badge/update
	@PutMapping("/update")
	@ResponseBody
	public Badge updateBadge(@RequestBody Badge badge) {
		return badgeService.updateBadge(badge);
	}

	// http://localhost:8082/examen/Badge/delete/{id}
	@DeleteMapping("/delete/{id}")
	@ResponseBody
	public String deleteBadge(@PathVariable("id") int id) {
		return badgeService.deleteBadge(id);
	}
	
	// http://localhost:8082/examen/Badge/voteBadge/id/vote
		@PutMapping("/voteBadge/{id}/{vote}")
		@ResponseBody
		public Badge voteBadge(@PathVariable("id") int id, @PathVariable("vote") String vote) {
			return badgeService.voteBadge(id,vote);
		}

}
