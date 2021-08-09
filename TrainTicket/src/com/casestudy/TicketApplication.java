package com.casestudy;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.*;

public class TicketApplication {

	public static void main(String[] args) throws SQLException, ClassNotFoundException, IOException {
		
		
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter the train number:");
		int trainNo = Integer.parseInt(sc.nextLine());
		TrainDAO trainDAO = new TrainDAO();
		Train train = trainDAO.findTrain(trainNo);
		if(train == null) {
			return;
		}
		
		System.out.println("Enter travel date:");
		String[] arr = sc.nextLine().split("/");
		LocalDate travelDate = LocalDate.of(Integer.parseInt(arr[2]), Integer.parseInt(arr[1]), Integer.parseInt(arr[0]));
		System.out.println("Enter Number of Passengers:");
		Ticket ticket = new Ticket(travelDate,train);
		int num = Integer.parseInt(sc.nextLine());
		
		
		while(num-- > 0) {
			System.out.println("Enter Passenger Name:");
			String name = sc.nextLine();
			System.out.println("Enter Age:");
			int age = Integer.parseInt(sc.nextLine());
			System.out.println("Enter Gender(M/F):");
			char gender = sc.nextLine().charAt(0);
			ticket.addPassenger(name,age,gender);
		}
		ticket.writeTicket();
		System.out.println("Ticket booked with PNR: " + ticket.getPnr());
		
		

	}

}
