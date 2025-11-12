package com.example.demo.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.example.demo.entity.StudentPojo;

@Repository
public class StudentDao {


	private final DataSource dataSource;

	@Autowired
	 public StudentDao(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	public StudentPojo getStudentDetailsbyId(int id) throws ClassNotFoundException {
		ResultSet result = null;
		String GET_STUDENT_DETAILS = "SELECT * FROM studentdetails where id=?";
		StudentPojo student = new StudentPojo();
		try (Connection connection = DataSourceUtils.getConnection(dataSource);
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
		String ADD_STUDENT_DETAILS = "INSERT INTO studentdetails(id,studentname,Studentclass,Section,Bloodtype) VALUES (?,?,?,?,?)";
		try (Connection connection = DataSourceUtils.getConnection(dataSource);
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
		String EDIT_STUDENT_DETAILS = "UPDATE studentdetails SET studentname = ?, Studentclass = ?, Section=?, Bloodtype=? WHERE id = ?";
		try (Connection connection = DataSourceUtils.getConnection(dataSource);
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
		int result=0;
		String DELETE_STUDENT_DETAILS = "DELETE FROM studentdetails WHERE id = ?";
		try (Connection connection = DataSourceUtils.getConnection(dataSource);
	             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_STUDENT_DETAILS)) {

			preparedStatement.setLong(1, id);

			result = preparedStatement.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public List<StudentPojo> getAllStudentDetails() {
		ResultSet result = null;
		List<StudentPojo> studentList=new ArrayList<StudentPojo>();
		String GET_ALL_STUDENT_DETAILS = "SELECT * FROM studentdetails";
		try (Connection connection = DataSourceUtils.getConnection(dataSource);
	             PreparedStatement preparedStatement = connection.prepareStatement(GET_ALL_STUDENT_DETAILS)) {

			result = preparedStatement.executeQuery();
			while (result.next()) {
				StudentPojo student = new StudentPojo();
				student.setStudentId(result.getInt("id"));
				student.setStudentName(result.getString("studentname"));
				student.setStudentClass(result.getString("Studentclass"));
				student.setStudentSection(result.getString("Section"));
				student.setStudentBloodGroup(result.getString("Bloodtype"));
				studentList.add(student);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return studentList;
	}

}
