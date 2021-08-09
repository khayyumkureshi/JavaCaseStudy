package com.spring.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="trains")
public class Train {
	@Id
	private int TrainNo;
	
	@Column(name="TrainName")
	private String TrainName;
	private String source;
	private String destination;
	private double ticketPrice;
	
	Train(){}

	public Train(int TrainNo, String TrainName, String source, String destination, double ticketPrice) {
		super();
		this.TrainNo = TrainNo;
		this.TrainName = TrainName;
		this.source = source;
		this.destination = destination;
		this.ticketPrice = ticketPrice;
	}

	public int getTrainNo() {
		return TrainNo;
	}

	public void setTrainNo(int TrainNo) {
		this.TrainNo = TrainNo;
	}

	public String getTrainName() {
		return TrainName;
	}

	public void setTrainName(String TrainName) {
		this.TrainName = TrainName;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getDestination() {
		return destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}

	public double getTicketPrice() {
		return ticketPrice;
	}

	public void setTicketPrice(double ticketPrice) {
		this.ticketPrice = ticketPrice;
	};
	
	
	
}

