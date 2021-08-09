package com.spring.model;



import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.*;


public class Ticket {

	private int counter=100;
	private String pnr;
	private LocalDate travelDate ;
	private Train train;
	private Map<Passenger, Double > passengers = new TreeMap<>();
	
	public Ticket(){};


	public Ticket( LocalDate travelDate, Train train) {
		super();

		this.travelDate = travelDate;
		this.train = train;

	}


	public  int getCounter() {
		return counter;
	}

	public  void setCounter(int counter) {
		this.counter = counter;
	}
	public String getPnr() {
		return pnr;
	}
	public void setPnr(String pnr) {
		this.pnr = pnr;
	}
	public LocalDate getTravelDate() {
		return travelDate;
	}
	public void setTravelDate(LocalDate travelDate) {
		this.travelDate = travelDate;
	}
	public Train getTrain() {
		return train;
	}
	public void setTrain(Train train) {
		this.train = train;
	}
	public Map<Passenger, Double> getPassengers() {
		return passengers;
	}
	public void setPassengers(Map<Passenger, Double> passengers) {
		this.passengers = passengers;
	}

	private int setCounterValue()
	{
		int i,counterValue=0;    
        try {
        	StringBuilder s= new StringBuilder();
        	FileReader fr=new FileReader("D:\\CounterValue.txt"); 
			while((i=fr.read())!=-1)   
			s.append((char)i);
			int c =Integer.parseInt(s.toString());
			counterValue=c;
			c++;
			FileWriter fWriter;
			fWriter = new FileWriter("D:\\CounterValue.txt");
			fWriter.write(Integer.toString(c));
			fWriter.close();
			fr.close(); 
		} 
        catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return counterValue;
		
	}
	
	private String generatePNR()

	{	
		counter = this.setCounterValue();
		String newString =travelDate.format(DateTimeFormatter.ofPattern("yyyyMMdd"));
		pnr = train.getSource().charAt(0)+""+train.getDestination().charAt(0)+"_"+newString+"_"+counter;
		return pnr;
	}

	private double calcPassengerFare(Passenger p1)
	{
		
		if (p1.getAge()<=12)
			return (train.getTicketPrice()*0.5);
		else if (p1.getAge()>=60)
			return (train.getTicketPrice()*0.6);
		else if (p1.getGender()=='F')
			return (train.getTicketPrice()*0.75);
		else 
			return (train.getTicketPrice());
	}

	public void addPassenger(String PassengerName, int PassengerAge, char PassengerGender)
	{
		Passenger p1 =new Passenger();
		p1.setName(PassengerName);
		p1.setAge(PassengerAge);
		p1.setGender(PassengerGender);
		//passengers.put(p1,calcPassengerFare(p1) );
		passengers.put(p1, calcPassengerFare(p1) );

	}
	
	private double totalTicketPrice ()
	{
		return passengers.entrySet().stream().mapToDouble(Map.Entry::getValue).sum();

	}

	private StringBuilder  generateTicket()
	{
		String DateString =travelDate.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
		String gfg2 =   "PNR                 :"+ this.pnr+"\n"+ 
						"Train no            :"+ train.getTrainNo()+"\n"+
						"Train Name          :" +train.getTrainName()+"\n"+
						"From                :"+ train.getSource()+"\n"+
						"To                  :"+ train.getDestination()+"\n"+
						"TravelDate          :"+DateString +"\n\n"+
						"Passengers: "+"\n"+
						"Name\t"+"Age\t"+"Gender\t"+"Fare\t"+"\n"
						;
		StringBuilder str = new StringBuilder(gfg2);
		for (Map.Entry<Passenger, Double> entry : passengers.entrySet())
		{
			Passenger p1 =entry.getKey();
			str.append(p1.getName()+"\t"+p1.getAge()+"\t"+p1.getGender()+"\t"+entry.getValue()+"\n");
			
		}
		str.append("Total price: "+this.totalTicketPrice()+"\n\n");
		
		
		return str;
 
		
		
	}

	public String writeTicket() 
	{
		String passengerPNR = this.generatePNR();
		
		try {
			FileWriter fWriter;
			fWriter = new FileWriter("D:\\"+this.pnr+".txt",true);
			fWriter.write(this.generateTicket().toString());
			
			fWriter.close();
		} catch (Exception e) {
			System.out.println("exception occured");
			e.printStackTrace();
		}
		
		return passengerPNR;
	}
}
