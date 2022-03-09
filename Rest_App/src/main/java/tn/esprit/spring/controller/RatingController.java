package tn.esprit.spring.controller;


import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import tn.esprit.spring.repository.UserRepository;
import tn.esprit.spring.service.IRatingService;



@RestController
public class RatingController {
	@Autowired
	IRatingService iEvaluationService;
	
	@Autowired
	UserRepository iUserRepository;
	
	
	
	
	
    @PostMapping("/evaluation/add-evaluation/{idevent}/{iduser}/{rate}")  
	@ResponseBody()
    public  ResponseEntity<?> EvaluateProduct(@PathVariable("idevent") int idevent,
			@PathVariable("iduser") int idUser, @PathVariable("rate") int rate
	)   
	{  
		return  iEvaluationService.evaluateEvent(idUser, idevent, rate);
	}
    
    @PutMapping("/evaluation/update-evaluation/{idevent}/{iduser}/{idrate}/{rate}")  
	@ResponseBody()
    public  String UpdateEvaluate(@PathVariable("idevent") int idevent,
			@PathVariable("iduser") int idUser, @PathVariable("idrate") int idrate,@PathVariable("rate") int rate)   
	{  
		return  iEvaluationService.updateRating(idUser, idevent, idrate,rate);
	}
	

    @GetMapping(value = "/evaluation/recommended", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getRecommendedOroductsByUserId(@RequestParam("User_id") int User_id)
            throws Exception, JSONException {
        if (!iUserRepository.existsById(User_id)) {
            throw new Exception("User not found!");
        }

        String recommendedProducts= iEvaluationService.recommendedEvents(User_id);

        return ResponseEntity.ok().body(recommendedProducts);
    }
    
	
	

	

}
