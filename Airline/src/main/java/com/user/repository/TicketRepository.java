package com.user.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.user.model.IndicomAirlines;
import com.user.model.Ticket;
import com.user.response.TicketResponseEntity;
@Repository
public interface TicketRepository extends JpaRepository<Ticket, Long>{
	//@Query("select ")
	//public List<TicketResponseEntity> findByticketId(@Param("ticketId") String ticketId);
	@Query("SELECT t from tickets t  ORDER BY ticket_id ASC")
	public List<Ticket> findAlltickets();
	
	public List<Ticket> findByTicketId(String ticketid);
	
	



}
