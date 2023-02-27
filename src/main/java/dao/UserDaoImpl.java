package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;

import model.Records;
import model.User;
import model.Model;

public class UserDaoImpl implements UserDao {
	private final String TABLE_NAME_1 = "Patients";
	private final String TABLE_NAME_2 = "Records";
	private Model model;
	public UserDaoImpl() {
	}

	@Override
	public void setup() throws SQLException, ClassNotFoundException {
		Class.forName("org.sqlite.JDBC");
		try (Connection connection = Database.getConnection();
				Statement stmt = connection.createStatement();) {
			String sql_1 = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME_1 + " (first_name VARCHAR(10) NOT NULL,last_name VARCHAR(10) NOT NULL,username VARCHAR(10) NOT NULL,"
					+ "password VARCHAR(8) NOT NULL," + "PRIMARY KEY (username))";
			String sql_2 = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME_2 + " (username VARCHAR(10) NOT NULL,date VARCHAR(10) NOT NULL,weight INTEGER NOT NULL,temperature INTEGER NOT NULL,low INTEGER NOT NULL,high INTEGER NOT NULL,"
					+ "notes VARCHAR(500) NOT NULL)";
			stmt.executeUpdate(sql_1);
			stmt.executeUpdate(sql_2);
		} 
	}

	@Override
	public User getUser(String username, String password) throws SQLException {
		String sql = "SELECT * FROM " + TABLE_NAME_1 + " WHERE username = ? AND password = ?";
		try (Connection connection = Database.getConnection(); 
				PreparedStatement stmt = connection.prepareStatement(sql);) {

			stmt.setString(1, username);
			stmt.setString(2, password);
			
			try (ResultSet rs = stmt.executeQuery()) {
				if (rs.next()) {
					User user = new User();
					user.setFirst_name(rs.getString("first_name"));
					user.setLast_name(rs.getString("last_name"));
					user.setUsername(rs.getString("username"));
					user.setPassword(rs.getString("password"));
					return user;
				}
				return null;
			} 
		}
	}

	@Override
	public User createUser(String first_name,String last_name,String username, String password) throws SQLException {
		String sql = "INSERT INTO " + TABLE_NAME_1 + " VALUES (?,?,?, ?)";
		try (Connection connection = Database.getConnection();
				PreparedStatement stmt = connection.prepareStatement(sql);) {
			stmt.setString(1, first_name);
			stmt.setString(2, last_name);
			stmt.setString(3, username);
			stmt.setString(4, password);

			stmt.executeUpdate();
			return new User(first_name,last_name,username, password);
		} 
	}




	@Override
	public Records createRecord(String username, String  date, String  weight, String  temperature, String  low, String  high, String notes) throws SQLException {
		String sql = "INSERT INTO " + TABLE_NAME_2 + " VALUES (?,?,?, ?,?,?, ?)";
		try (Connection connection = Database.getConnection();
			 PreparedStatement stmt = connection.prepareStatement(sql);) {
			stmt.setString(1, username);
			stmt.setString(2, String.valueOf(date));
			stmt.setString(3, String.valueOf(weight));
			stmt.setString(4, String.valueOf(temperature));
			stmt.setString(5, String.valueOf(low));
			stmt.setString(6, String.valueOf(high));
			stmt.setString(7, notes);
			stmt.executeUpdate();
			return new Records(weight,temperature,low,high,notes,date);
		}
	}

	@Override
	public void UpdateUser(String first, String last,String username) throws SQLException {
		String sql = "UPDATE " + TABLE_NAME_1 + " SET first_name = "+"'"+first+"'"+" WHERE username = "+"'"+username+"'";
		try (Connection connection = Database.getConnection();
			 PreparedStatement stmt = connection.prepareStatement(sql);) {

			stmt.executeUpdate();

		}
	}

	@Override
	public ArrayList<Records> getRecords(String username) throws SQLException {
		ArrayList<Records> re=new ArrayList<Records>();
		String sql = "SELECT * FROM " + TABLE_NAME_2 + " WHERE username ="+"'"+username+"'" ;
		try (Connection connection = Database.getConnection();
			 PreparedStatement stmt = connection.prepareStatement(sql);) {



			try (ResultSet rs = stmt.executeQuery()) {

				while (rs.next()) {


					Records rec =new Records(rs.getString("weight"),rs.getString("temperature"),rs.getString("low"),rs.getString("high"),rs.getString("notes"),rs.getString("date"));
					re.add(rec);

				}
				return re;
			}

		}
	}

	@Override
	public Boolean deleteRecords(Records username,String user) throws SQLException {
		System.out.print(username.getDate());
		System.out.print(username.getNotes());
		System.out.print(username.getLow_bp());
		System.out.print(username.getHigh_bp());
		System.out.print(username.getTemperature());
		System.out.print(username.getWeight());


		String sql = "DELETE FROM " + TABLE_NAME_2 + " WHERE username ="+"'"+user+"' AND"+ "  date ="+"'"+username.getDate()+"' AND"+ " temperature ="+"'"+username.getTemperature()+"' AND"+ " weight ="+"'"+username.getWeight()+"' AND"+ " high ="+"'"+username.getHigh_bp()+"' AND"+ " low ="+"'"+username.getLow_bp()+"' AND"+ " notes ="+"'"+username.getNotes()+"' " ;
		try (Connection connection = Database.getConnection();
			 PreparedStatement stmt = connection.prepareStatement(sql);) {



			try (ResultSet rs = stmt.executeQuery()) {

				return true;

				}

			}

		}

	}

