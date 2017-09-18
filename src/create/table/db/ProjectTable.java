package create.table.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class ProjectTable {
	
	/**
	 * Creating tables
	 */
	public static void main(String[] args) throws InterruptedException {
		boolean create;
		create = true ; 
		if (create){
		String url = "jdbc:derby://localhost:1527/dbCars;create=true";
		try(Connection con = DriverManager.getConnection(url);) {
			System.out.println("connect");
			Statement st = con.createStatement();
			String sql1 = "CREATE TABLE Cars(ID BIGINT NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),licensePlate VARCHAR(30),make VARCHAR(30),colors VARCHAR(30),yearMunuf INT,capacity INT,ownersNum INT,PRIMARY KEY(ID))";
			st.executeUpdate(sql1);
			System.out.println("Cars table created");
			Thread.sleep(500);
//			String sql2 = "CREATE TABLE customer(ID BIGINT NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),CUST_NAME VARCHAR(15) UNIQUE,PASSWORD VARCHAR(15),PRIMARY KEY(ID))";
//			st.executeUpdate(sql2);
//			System.out.println(" customer table created");
//			Thread.sleep(500);
//			String sql3 = "CREATE TABLE coupon(ID BIGINT NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),TITLE VARCHAR(15) UNIQUE,START_DATE DATE,END_DATE DATE,AMOUNT INT,TYPE VARCHAR(15),MESSAGE VARCHAR(20),PRICE DOUBLE,IMAGE VARCHAR(30),PRIMARY KEY(ID))";
//			st.executeUpdate(sql3);
//			System.out.println(" coupon table created");
//			Thread.sleep(500);
//			String sql4 = "CREATE TABLE customer_coupon(CUST_ID BIGINT,COUPON_ID BIGINT,PRIMARY KEY(CUST_ID,COUPON_ID))";
//			st.executeUpdate(sql4);
//			System.out.println(" customer_coupon table created");
//			Thread.sleep(500);
//			String sql5 = "CREATE TABLE company_coupon(COMP_ID BIGINT,COUPON_ID BIGINT,PRIMARY KEY(COMP_ID,COUPON_ID))";
//			st.executeUpdate(sql5);
//			System.out.println(" company_coupon table created");
//			Thread.sleep(500);
		} catch (SQLException e) {
			e.printStackTrace();
			//System.out.println("Connection failed. Contact us to fix");
		}
		System.out.println("disconnect");
		}
	}
}