package com.spring.controller;


import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.spring.model.Passenger;
import com.spring.model.Ticket;
import com.spring.model.Train;

import com.spring.repository.TrainRepository;




@RestController
@RequestMapping("/api")
public class ticketController 
{
	Train passengerTrain;
	@Autowired
	TrainRepository trainRepository;
	
	//
	
	@GetMapping("/traindata")
	public List<Train> getAllMagazine()
	{
		return trainRepository.findAll();
		
	}

	@PostMapping("/traindata")
	public Train addtrain(@RequestBody Train train)
	{
		return trainRepository.save(train);
	}
	
	@DeleteMapping("/traindata")
	public String deleteTrain(@RequestBody Train train)
	{
		Optional<Train> trainData = trainRepository.findById(train.getTrainNo());

		if (trainData.isPresent())
		{
			trainRepository.delete(trainData.get());
			return "train deleted successfully";
		}
		else
			return "train not found";
	}
	

	
	@PostMapping("/train")
	public String getticket(@RequestParam String date, int id , @RequestBody List<Passenger> passengerList)
	{
		Optional<Train> trainData = trainRepository.findById(id);

		if (!trainData.isPresent())
		{	
			return "Train is not available";
		}
		
		passengerTrain = trainData.get();
		String travelDate =date;
		
		
		//Date verification
		DateTimeFormatter formatter_1 = DateTimeFormatter.ofPattern("d/MM/yyyy");
		LocalDate date1 = LocalDate.parse(travelDate, formatter_1);
		LocalDate todayDate = LocalDate.now();
		if (date1.isBefore(todayDate))
		{	
			
			return ("Travel date is before current date");
			
		}
			
		//Generate Ticket
		Ticket passengerTicket =new Ticket(date1, passengerTrain);
		
		
		for (Passenger p:passengerList)
		{
			passengerTicket.addPassenger(p.getName(), p.getAge(), p.getGender());
		}
		
		
		
		return "Ticket generation successful : "+passengerTicket.writeTicket();
	}

}
