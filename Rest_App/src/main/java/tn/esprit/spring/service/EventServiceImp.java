package tn.esprit.spring.service;

import java.sql.Time;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.spring.entity.Donation;
import tn.esprit.spring.entity.Event;
import tn.esprit.spring.entity.EventCategory;
import tn.esprit.spring.entity.Jackpot;
import tn.esprit.spring.entity.Notification;
import tn.esprit.spring.entity.Participation;
import tn.esprit.spring.entity.User;
import tn.esprit.spring.repository.EventRepository;
import tn.esprit.spring.repository.IDonationRepository;
import tn.esprit.spring.repository.IJackPotRepository;
import tn.esprit.spring.repository.NotificationRepository;
import tn.esprit.spring.repository.ParticipationRepository;
import tn.esprit.spring.repository.UserRepository;

@Service
public class EventServiceImp implements IEventService{
	@Autowired 
	private EventRepository iEventRepository;
	@Autowired
	private ParticipationRepository iParticipantRepository;
	
	@Autowired
	private NotificationRepository iNotificationRepository;
	
	@Autowired
	private IDonationRepository iDonationRepository;
	
	@Autowired
	private UserRepository iUserRepository;

	@Autowired
	private IJackPotRepository jackPotRepository;
	
	/**********************************Admin**********************************/

	//creating add method that insert  event into database
	@Override
	
	public void addEvent(Event e) {
		
		Jackpot jackpot = new Jackpot();
		jackpot.setSum(0);
		e.setJackpot(jackpot);
	
		Notification n = new Notification();
		n.setUser(null);
		n.setDate(new Date());
		n.setTitle("event added welcome and never forget to join us");
		n.setEvent(e);
		jackPotRepository.save(jackpot);

		iEventRepository.save(e);

		iNotificationRepository.save(n);

		
	}
	//creating deleting method that remove   event by id  from database
		@Override
		public void deleteEvent(int id) {
			Event e = iEventRepository.findById(id).get();
			iEventRepository.delete(e);
			
		}

		@Override
		public Event findEventById(int id) {
			return  iEventRepository.findById(id).get();
		} 

		//creating getAll method that retrieve all events from database
		@Override
		public List<Event> getAllEvents() {
			
			List<Event>events = new ArrayList<Event>();
			iEventRepository.findAll().forEach(e ->events.add(e));
			return events;
		}
		@Override
		public int updateEvent(Event e, int id) {
			 
				return iEventRepository.updateEvent(e.getTitle(),e.getDate() , e.getHour(), 
							e.getAddress(), e.getDescription(), e.getPlacesNbr(),e.getCategory(),e.getTicketPrice(),e.isStatus(),e.getImage(),e.getIdEvenement());
			}
		
		
		
		
		
		
		
		
		public String affecterCategoryEvent(String category, int idevent) {
			Event event = iEventRepository.findById(idevent).get();
			String msg=" ";
			
			//bch n7awl men string l enum => CategoryEvent.valueOf(category)
			try {
			for(EventCategory c : EventCategory.values()) {
				if(c == EventCategory.valueOf(category)) {
					event.setCategory(EventCategory.valueOf(category));
					iEventRepository.save(event);
					return msg ="Category of Event Affected successfully to event!";
				}
			}
		}catch(Exception e) {
				 msg="Failed to affected Category";
			}
			return msg;
		}
		@Override
		public List<Event> upcomeEvents() {
			List<Event> list= iEventRepository.upcomingEvents();
			
			return list;	
				}

		

		@Override
		public Map<Integer, Integer> getEventsByViews() {
			List<Integer>listId = new ArrayList<>();
			List<Integer>listViews = new ArrayList<>();
			Map<Integer,Integer> h = new HashMap<>();

			
			for(Event e : iEventRepository.findAll()) {
				
				listId.add(e.getIdEvenement());
				listViews.add(e.getViews());
				
			}
			
			List<Integer>sortedList = new ArrayList<>(listViews);
			Collections.sort(sortedList);
			
			for(int i = 0 ; i <2 ; i++) {
				int max = sortedList.get(sortedList.size()-1);
				int ind = listId.get(listViews.indexOf(max));// prend nbre de vue et retourne id d'event corresspondant
				h.put(ind, max);

				System.out.println(ind +" "+ max);
				sortedList.remove(sortedList.size()-1);
				listViews.set(listViews.indexOf(max), -1);
			}
			return h;
		}
		
		//creating displayBestEventsByViews that display most 3 views event
		@Override
		public List<String> displayBestEventsByViews() {
			
			List<String>list = new ArrayList<>();
			String s ="";
			List<Integer>listId = new ArrayList<>();
			List<Integer>listViews = new ArrayList<>();
			
			List<Event> listEvent = (List<Event>)iEventRepository.findAll();
			
			for(Event e : listEvent) {
				listId.add(e.getIdEvenement());
				listViews.add(e.getViews());
			
			}
			
			List<Integer> sortedList = new ArrayList<>(listViews);
			
			Collections.sort(sortedList);
			
			for(int i = 0 ; i<2 ; i++) {
				int max = sortedList.get(sortedList.size()-1);
				int ind = listId.get(listViews.indexOf(max));
				s = (i+1)+"--Event: "+iEventRepository.findById(ind).get().getTitle()+"  = with"+max+" views";
				list.add(s);
				sortedList.remove(sortedList.size()-1);
				listViews.set(listViews.indexOf(max), -1);
				}
			
			
			return list;
		}
		@Override
		public Event getEventById(int id) {
			int countView;
			Event e = iEventRepository.findById(id).get();
			if(e == null) return null;
			
			e.setViews(e.getViews()+1);
		    countView = iEventRepository.updateViewCountEvent(e.getViews()-1,e.getIdEvenement());
				countView++;
				
			return e ;  

		}
		

		@Override
		public void refundUsers(int eid) {
			DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
			java.util.Date date = new java.util.Date();
			
			Event ev = iEventRepository.findById(eid).get();
			User u = new User();
			
			
		

			List<Participation> participationsOfEvent = iParticipantRepository.Participations(ev);
			
			
			System.out.println("hiii==");
			Notification n = new Notification();

			if(!ev.getParticipations().isEmpty()) {
			for(Participation p : participationsOfEvent) {
				//int u = new User();//user id
				u = iUserRepository.findById(p.getUser().getId()).get();
				float refundedAmount = p.getPrice();
				ev.setCollAmount(ev.getCollAmount()-refundedAmount);
				//iUserRepository.save(u);
				System.out.println("hiiiiii==");

				iParticipantRepository.delete(p);
				
				
				iEventRepository.save(ev);
				

				n.setEvent(ev);
				n.setUser(u);
			
				
				LocalTime localTime = LocalTime.now();

				n.setTitle("Canceled Event");
				n.setDescription("Dear "+u.getFname()+" "+u.getLname()+""
						+ ",we regret to announce that the event "+ev.getTitle()+" you want to attend has been canceled for some reason."
						+ " That's why, we have refunded your ticket price. If there is a problem, do not hesitate to contact us."
						+ " Thank you.");
				n.setDate(date);
				

				
				iNotificationRepository.save(n);
			
			}
			
		}else {
			System.out.println("event without participations");
		}
			
			List<Donation>donationsResult =  iDonationRepository.DonationOfEvent(ev);
			
			for(Donation d : donationsResult) {
				User user = d.getUser();
				
				float refundedAmount = d.getAmount();// flous ta3 donation
				System.out.println("donation="+refundedAmount);
				System.out.println("jackpot before=="+ev.getJackpot());
				ev.getJackpot().setSum(ev.getJackpot().getSum()- refundedAmount);//fhmtha?? brbi haw jit njib 9ahwa
				System.out.println("jackpot after retrieve money=="+ev.getJackpot().getSum());
				System.out.println("collAmount before=="+ev.getCollAmount());
				ev.setCollAmount(ev.getCollAmount()-refundedAmount);
				
				System.out.println("jackpot=="+ev.getCollAmount());

			//	System.out.println("Accurance balance before="+u.getAccBalance());
				//u.setAccBalance(u.getAccBalance()+refundedAmount);

				//System.out.println("accuranceBalance="+u.getAccBalance());
				
				//************Notification*******************//
				Notification notification = new Notification();
				notification.setTitle("Remoboursement");
				notification.setUser(user);
				notification.setDescription("Dear "+user.getFname()+" We annonce that the event"+ev.getTitle()+
						"was canceled for some reasons that's why we refunded your donation . for more informations do not hesitate to contact us"
						+ "thank you");
				notification.setDate(new Date());
				iDonationRepository.deleteById(d.getId());
				iEventRepository.save(ev);
				iUserRepository.save(u);

			}
			
		}
		
}
