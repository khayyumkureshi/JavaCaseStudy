package com.casestudy;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class TrainDAO {
	public Train findTrain(int trainNo) throws ClassNotFoundException, SQLException {
		Class.forName("oracle.jdbc.OracleDriver");
		Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:XE", "system", "kk");
		Statement statement = connection.createStatement();
		
		String query = "SELECT * FROM TRAINS WHERE TRAIN_NO = " + trainNo;
		ResultSet trainSet = statement.executeQuery(query);
		boolean status = trainSet.next();
		try {
			if(!status) {
				throw new NoSuchTrainException();
			}
		}catch(NoSuchTrainException e) {
			System.out.println(e.getMessage());
			return null;
		}
		
		if(status) {
			String trainName = trainSet.getString(2);
			String source = trainSet.getString(3);
			String destination = trainSet.getString(4);
			double ticketPrice = trainSet.getDouble(5);
			return new Train(trainNo, trainName, source, destination, ticketPrice);
		}
		connection.close();
		return null;
		

		
	}

}
