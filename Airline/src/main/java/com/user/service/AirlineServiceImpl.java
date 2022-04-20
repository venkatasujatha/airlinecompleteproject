package com.user.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import org.apache.logging.log4j.util.PropertySource.Comparator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.user.model.IndicomAirlines;
import com.user.model.Passenger;
import com.user.model.Ticket;
import com.user.repository.AirlinesRepository;
import com.user.repository.PassengerRepository;
import com.user.repository.TicketRepository;

@Service
public class AirlineServiceImpl<E> implements AirlineServiceInterface {

	@Autowired
	private AirlinesRepository airlineRepo;

	@Autowired
	private PassengerRepository prepo;

	@Autowired
	private TicketRepository ticketRepository;

	@Override
	public IndicomAirlines saveTicket(IndicomAirlines ticket) {

		return airlineRepo.save(ticket);
	}

	@Override
	public List<IndicomAirlines> getFlightPassangers(String flightName) {
		List<IndicomAirlines> list = airlineRepo.findByflightName(flightName);

		List<IndicomAirlines> l = list.stream().filter(i -> i.getFlightName().contains("INDI34E"))
				.collect(Collectors.toList());

		List<Ticket> tick = new ArrayList();

		for (IndicomAirlines ia : l) {
			tick.addAll(ia.getTickets());
		}
		return list;

	}

	@Override
	public List<IndicomAirlines> getList(String flightName) {
		List<IndicomAirlines> list1 = airlineRepo.findByflightName(flightName);

		List<IndicomAirlines> collect = list1.stream().filter(i -> i.getFlightName().contains("INDI67G"))
				.collect(Collectors.toList());
		List<Ticket> t = new ArrayList();

		for (IndicomAirlines collect1 : list1) {
			t.addAll(collect1.getTickets());
		}

		return list1;
	}

	@Override
	public List<IndicomAirlines> fList(IndicomAirlines indicomAirlines) {
		List<IndicomAirlines> list = airlineRepo.findAll();

		return list;
	}

	@Override
	public List<Passenger> sortByAge() {

		List<Passenger> list2 = prepo.findByage();

		return list2;
	}

	@Override
	public List<Passenger> servhByTicSortByAge() {
		List<Passenger> list = prepo.searchByTicSort();
		return list;
	}

	@Override
	public List<Passenger> tickets(String id) {
		List<Ticket> list = ticketRepository.findByTicketId(id);

		List<Passenger> list2 = null;
		for (Ticket ticket : list) {
			list2 = ticket.getPassengers();

		}

		Collections.sort(list2, (o1, o2) -> {
			return o1.getAge() - o2.getAge();
		});

		return list2;

	}

	@Override
	 public String canceltic(int id) {
	
	        //SimpleDateFormat obj = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	        
	              Ticket t=ticketRepository.getById((long) id);
	              Date date1=t.getArrivalDateAndTime();
	              Date date2=new Date();
	             //System.out.println("it is date2 "+date2);
	              long diffInMillies = date1.getTime()-date2.getTime();
	              long days= TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);
	              //System.out.println("days"+days);
	              int hours = (int) ((diffInMillies / (10006060)) % 24);
	              //System.out.println(hours+" hours");
	             // System.out.println("it is day "+days);
	             // ticketRepository.deleteById((long) id);
	              
	           // String s =(days>=0?(hours<=1 && days==0)?"No refund allowed":(hours<=5 && hours>1 && days>0)?"50% refund allowed":"full refund allowed");

	              //System.out.println(s);
	            
					/*
					 * if(days>=0) { if(hours<=1 && days==0) { return "No refund allowed"; } else
					 * if(hours<=5 &&hours>1 && days>0) { return "50% refund allowed"; } else {
					 * return "full refund"; }
					 * 
					 * } else { return "flight already departed"; }
					 */
  	 	            
  	              
  	              
  	              String s3=((days>=0)?(hours<=1 && days==0)?"No refund allowed":(hours<=5 && hours>1 && days>0)?"50% refund allowed":"full refund allowed":"fligth already departed");

	             return s3;
	}

	@Override
	public String deletebyId(Long id) {
		// List<Ticket> list = ticketRepository.findByTicketId(id);
		prepo.deleteById(id);
		return "";
	}

}
