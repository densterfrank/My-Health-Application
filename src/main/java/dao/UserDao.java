package dao;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

import model.Records;
import model.User;

/**
 * A data access object (DAO) is a pattern that provides an abstract interface 
 * to a database or other persistence mechanism. 
 * the DAO maps application calls to the persistence layer and provides some specific data operations 
 * without exposing details of the database. 
 */
public interface UserDao {
	void setup() throws SQLException, ClassNotFoundException;

	User getUser(String username, String password) throws SQLException;

	User createUser(String first_name, String last_name, String username, String password) throws SQLException;

	Records createRecord(String first_name, String  date, String  weight, String  temperature, String  low, String  high, String notes) throws SQLException;

	void UpdateUser(String first, String last,String username) throws SQLException;

	ArrayList<Records> getRecords(String username) throws SQLException;

	Boolean deleteRecords(Records username,String user) throws SQLException;
}


