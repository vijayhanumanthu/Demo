package com.example.demo.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.entity.StudentPojo;

@Service
public class StudentDao {

	Connection connection = null;

	public StudentDao() throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.cj.jdbc.Driver");
		connection = DriverManager.getConnection(
				"jdbc:mysql://localhost:3306/vijay?allowPublicKeyRetrieval=true&useSSL=false", "root", "admin");
	}

	public StudentPojo getStudentDetailsbyId(int id) throws ClassNotFoundException {
		ResultSet result = null;
		String GET_STUDENT_DETAILS = "SELECT * FROM studentdetails where id=?";
		StudentPojo student = new StudentPojo();
		try (

				PreparedStatement preparedStatement = connection.prepareStatement(GET_STUDENT_DETAILS)) {

			preparedStatement.setLong(1, id);

			result = preparedStatement.executeQuery();
			while (result.next()) {

				student.setStudentId(result.getInt("id"));
				student.setStudentName(result.getString("studentname"));
				student.setStudentClass(result.getString("Studentclass"));
				student.setStudentSection(result.getString("Section"));
				student.setStudentBloodGroup(result.getString("Bloodtype"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return student;
	}

	public int addStudentDetails(StudentPojo studentdetails) {
		int result=0;
		// TODO Auto-generated method stub
		String ADD_STUDENT_DETAILS = "INSERT INTO studentdetails(id,studentname,Studentclass,Section,Bloodtype) VALUES (?,?,?,?,?)";
		try (

				PreparedStatement preparedStatement = connection.prepareStatement(ADD_STUDENT_DETAILS)) {

			preparedStatement.setLong(1, studentdetails.getStudentId());
			preparedStatement.setString(2, studentdetails.getStudentName());
			preparedStatement.setString(3, studentdetails.getStudentClass());
			preparedStatement.setString(4, studentdetails.getStudentSection());
			preparedStatement.setString(5, studentdetails.getStudentBloodGroup());

			result = preparedStatement.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	public int editStudentDetails(StudentPojo studentdetails) {
		int result=0;
		// TODO Auto-generated method stub
		String EDIT_STUDENT_DETAILS = "UPDATE studentdetails SET studentname = ?, Studentclass = ?, Section=?, Bloodtype=? WHERE id = ?";
		try (

				PreparedStatement preparedStatement = connection.prepareStatement(EDIT_STUDENT_DETAILS)) {

			preparedStatement.setLong(5, studentdetails.getStudentId());
			preparedStatement.setString(1, studentdetails.getStudentName());
			preparedStatement.setString(2, studentdetails.getStudentClass());
			preparedStatement.setString(3, studentdetails.getStudentSection());
			preparedStatement.setString(4, studentdetails.getStudentBloodGroup());

			result = preparedStatement.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public int deleteStudentDetails(int id) {
		// TODO Auto-generated method stub
		int result=0;
		// TODO Auto-generated method stub
		String DELETE_STUDENT_DETAILS = "DELETE FROM studentdetails WHERE id = ?";
		try (

				PreparedStatement preparedStatement = connection.prepareStatement(DELETE_STUDENT_DETAILS)) {

			preparedStatement.setLong(1, id);

			result = preparedStatement.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

}
