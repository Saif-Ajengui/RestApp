package tn.esprit.spring.service;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


import tn.esprit.spring.entity.Rating;
import tn.esprit.spring.config.ValueComparatorInt;
import tn.esprit.spring.entity.Event;
import tn.esprit.spring.entity.User;
import tn.esprit.spring.repository.EventRepository;
import tn.esprit.spring.repository.IRatingRepository;
import tn.esprit.spring.repository.UserRepository;




@Service
public class RatingServiceImpl implements IRatingService {
    @Autowired
	UserRepository iUserRepository;
	
	@Autowired
	EventRepository iEventRepository;
	
	@Autowired
	IRatingRepository iEvaluationRepository;
	
	
    // Number of output neighbourhoods
    private static final int NUM_NEIGHBOURHOODS = 10;
    // Number of output recommendations
    private static final int NUM_RECOMMENDATIONS = 3;
	
	
	
	
	
	  
    /**
     * Map with the user id as key and its ratings as value that is a map with boo ASIN as key and its rating as value
     */
    private Map<Integer, Map<Integer, Integer>> ratings;

    /**
     * Average rating of each user where the key is the user id and the value its average rating
     */
    private Map<Integer, Double> averageRating;

    /**
     * Constructor
     */
    public RatingServiceImpl() {
        ratings = new HashMap<>();
        averageRating = new HashMap<>();
    }

    public Map<Integer, Map<Integer, Integer>> getRatings() {
        return ratings;
    }

    private void setRatings(Map<Integer, Map<Integer, Integer>> ratings) {
        this.ratings = ratings;
    }

    public Map<Integer, Double> getAverageRating() {
        return averageRating;
    }

    private void setAverageRating(Map<Integer, Double> averageRating) {
        this.averageRating = averageRating;
    }


	
	@Override
	public ResponseEntity<?> evaluateEvent(int idUser, int idEvent, int rate) {
		// TODO Auto-generated method stub
				Rating evaluate = new Rating();
				User user = iUserRepository.findById(idUser).get();
				Event event =iEventRepository.findById( idEvent).get();
				System.out.println("event === counted ="+iEvaluationRepository.countRatingUserByEvent(idUser,  idEvent));

				
				if(rate > 5) {
					return ResponseEntity.ok("Rating only below or equal 5");
				}
				else if(rate <0) {
					return ResponseEntity.ok("Rating could not be negative");
 
				}
				
				else if(iEvaluationRepository.countRatingUserByEvent(idEvent,  idUser)!=0) {
					return ResponseEntity.ok("you are already  rate this event");
				}
				else {
					evaluate.setEvent(event);
					evaluate.setUser(user);
					evaluate.setRate(rate);
					iEvaluationRepository.save(evaluate);
					return ResponseEntity.ok( "Rating is done successfully");
				}
	}


	@Override
	public String updateRating(int iduser , int idproduct, int idRate, int rate) {
		// TODO Auto
		List<Rating>evaluations = iEvaluationRepository.findEvaluationByUserAndEvent(idproduct, iduser);
		if(rate > 5) {
			return "Rating only below or equal 5"	;
		}
		if(evaluations.size()!=0) {
			for(int i  =0 ; i<evaluations.size(); i++) {
				
				if(idRate == evaluations.get(i).getIdEvalution()) {
					evaluations.get(i).setRate(rate);
					iEvaluationRepository.save(evaluations.get(i));
					return "Update Evaluation successfully";
				}
			}
			
			
		}
		return "No Evaluations Found for you to update";
		
	}
	
		@Override
	public int countRatingUserByEvent(int idp) {
		// TODO Auto-generated method stub
		
		Event p = iEventRepository.findById(idp).get();
		
		if(p != null)
			return iEvaluationRepository.countEvaluation(p.getIdEvenement());
		else
			return 0;
		
	}
	
	
	private Map<Integer, Double> getNeighbourhoods(Map<Integer, Integer> userRatings) {
        Map<Integer, Double> neighbourhoods = new HashMap<>();
        ValueComparatorInt valueComparator = new ValueComparatorInt(neighbourhoods);
        Map<Integer, Double> sortedNeighbourhoods = new TreeMap<>(valueComparator);

        double userAverage = getAverage(userRatings);
        
        

        for (int user : ratings.keySet()) {
            ArrayList<Integer> matches = new ArrayList<>();
            for (int productASIN : userRatings.keySet()) {
                if (ratings.get(user).containsKey(productASIN)) {
                    matches.add(productASIN);
                }
            }
            double matchRate;
            if (matches.size() > 0) {
                double numerator = 0, userDenominator = 0, 	
                		otherUserDenominator = 0;
              
                
                for (int productASIN  : matches) {
                    double u = userRatings.get(productASIN) - userAverage;
                    double v = ratings.get(user).get(productASIN) - averageRating.get(user);
                    
                    numerator += u * v;
                    userDenominator += u * u;
                    otherUserDenominator += v * v;
                }
                if (userDenominator == 0 || otherUserDenominator == 0) {
                    matchRate = 0;
                } else {
                    matchRate = numerator / (Math.sqrt(userDenominator) * Math.sqrt(otherUserDenominator));
                }
            } else {
                matchRate = 0;
            }

            neighbourhoods.put(user, matchRate);
        }
        sortedNeighbourhoods.putAll(neighbourhoods);

        Map<Integer, Double> output = new TreeMap<>();

        Iterator entries = sortedNeighbourhoods.entrySet().iterator();
        int i = 0;
        while (entries.hasNext() && i < NUM_NEIGHBOURHOODS) {
            Map.Entry entry = (Map.Entry) entries.next();
            if ((double) entry.getValue() > 0) {
                output.put((int) entry.getKey(), (double) entry.getValue());
                i++;
            }
        }
        return output;
    }

    /********************************Pärtie Intelligence Articficiel / Prédiction DataSet(x,y) and deep learning (Made by Chadi)****************************
     * Get predictions of each event by a user giving some ratings and its neighbourhood:
     * r(u,i) = r(u) + sum(sim(u,v) * (r(v,i) - r(v))) / sum(abs(sim(u,v)))
     * sim(u,v): similarity between u and v users
     * r(u,i): rating of the event i by the user u
     * r(u): average rating of the user u
     *
     * @param userRatings    ratings of the user
     * @param neighbourhoods nearest neighbourhoods
     * @param books          books in the database
     * @return predictions for each event
     */
    private Map<Integer, Double> getRecommendations(Map<Integer, Integer> userRatings,
                                                 Map<Integer, Double> neighbourhoods, Map<Integer, String> books) {

        Map<Integer, Double> predictedRatings = new HashMap<>();

        // r(u)
        double userAverage = getAverage(userRatings);

        for (int productASIN : books.keySet()) {
            if (!userRatings.containsKey(productASIN)) {

                // sum(sim(u,v) * (r(v,i) - r(v)))
                double numerator = 0;

                // sum(abs(sim(u,v)))
                double denominator = 0;

                for (int neighbourhood : neighbourhoods.keySet()) {
                    if (ratings.get(neighbourhood).containsKey(productASIN)) {
                        double matchRate = neighbourhoods.get(neighbourhood);
                        numerator +=
                                matchRate * (ratings.get(neighbourhood).get(productASIN) - averageRating.get(neighbourhood));
                        denominator += Math.abs(matchRate);
                    }
                }

                double predictedRating = 0;
                if (denominator > 0) {
                    predictedRating = userAverage + numerator / denominator;
                    if (predictedRating > 5) {
                        predictedRating = 5;
                    }
                }
                predictedRatings.put(productASIN, predictedRating);
            }
        }
        return predictedRatings;
    }

    /**
     * Get average of the ratings of a user
     *
     * @param userRatings ratings of a user
     * @return average or the ratings of a user
     */
    private double getAverage(Map<Integer, Integer> userRatings) {
        double userAverage = 0;
        for (Map.Entry<Integer, Integer> longIntegerEntry : userRatings.entrySet()) {
            userAverage += (int) ((Map.Entry) longIntegerEntry).getValue();
        }
        return userAverage / userRatings.size();
    }
    
    
	
	 public String recommendedEvents(int  userId) throws JSONException  {

         Map<Integer, Double> averageRating = new HashMap<>();
         Map<Integer, Map<Integer, Integer>> setRatings = new HashMap<>();

         Map<Integer, Map<Integer, Integer>> myRatesMap = new TreeMap<>();
         Map<Integer, Map<Integer, Integer>> userWithRatesMap = new TreeMap<>();


         iUserRepository.findAll().forEach(userItem -> {
             int userID = userItem.getId();
             Map<Integer, Integer> userRatings = new HashMap<>();

             userItem.getEvaluations().forEach(userEventRating -> {
                         if (userEventRating.getUser().getId()==userID ) {
                             System.out.println(userEventRating.getRate());
                             System.out.println(userEventRating.getUser().getId());
                             userRatings.put(userEventRating.getUser().getId(), userEventRating.getRate());
                         }
                     }
             );

             if (userId==userID)  {
                 myRatesMap.put(userID, userRatings);
             } else {
                 userWithRatesMap.put(userID, userRatings);

                 setRatings(userWithRatesMap);
                 averageRating.put(userID, 0.0);

                 for (Map.Entry<Integer, Integer> longIntegerEntry : userRatings.entrySet()) {

                     if (ratings.containsKey(userID)) {
                         ratings.get(userID).put(longIntegerEntry.getKey(), longIntegerEntry.getValue());
                         averageRating.put(userID, averageRating.get(userID) + (double) longIntegerEntry.getValue());
                     } else {
                         Map<Integer, Integer> bookRating = new HashMap<>();
                         bookRating.put(longIntegerEntry.getKey(), longIntegerEntry.getValue());
                         ratings.put(userID, bookRating);
                         averageRating.put(userID, (double) longIntegerEntry.getValue());
                     }
                 }
             }
         });

         for (Map.Entry<Integer, Double> longDoubleEntry : averageRating.entrySet()) {
             if (ratings.containsKey(longDoubleEntry.getKey())) {
                 longDoubleEntry.setValue(longDoubleEntry.getValue() / (double) ratings.get(longDoubleEntry.getKey()).size());
             }
         }

         setAverageRating(averageRating);

         Map<Integer, String> event = new HashMap<>();
         Map<Integer, String> evaluations = new HashMap<>();
        


   
         iEventRepository.findAll()
         .forEach(e ->
         event.put(e.getIdEvenement(),e.getTitle()));

         
         Map<Integer, Double> neighbourhoods = getNeighbourhoods(myRatesMap.get(userId));
         Map<Integer, Double> recommendations = getRecommendations(myRatesMap.get(userId), neighbourhoods, event);
         ValueComparatorInt valueComparator = new ValueComparatorInt(recommendations);

         Map<Integer, Double> sortedRecommendations = new TreeMap<>(valueComparator);
         sortedRecommendations.putAll(recommendations);

         Iterator<Map.Entry<Integer, Double>> sortedREntries = sortedRecommendations.entrySet().iterator();
         JSONArray recommendedProductsArray = new JSONArray();
         
         
         
         int i = 0;
         while (sortedREntries.hasNext() && i < NUM_RECOMMENDATIONS) {
             Map.Entry<Integer, Double> entry = sortedREntries.next();
                  JSONObject recommendedProducts = new JSONObject("{}");
                  	
                  recommendedProducts.put("Evenet Name", 	event.get(entry.getKey()));
                  System.out.println("Evaluations = "+sortedREntries.toString());
                  System.out.println("RE ="+entry.getKey());
           
                      recommendedProducts.put("Rate", iEvaluationRepository.getEvaluationByEvent(entry.getKey()));
  
                  recommendedProductsArray.put(recommendedProducts);
                  i++;

         }
         return recommendedProductsArray.toString();	
     }


	
	
	
	
	
	
}