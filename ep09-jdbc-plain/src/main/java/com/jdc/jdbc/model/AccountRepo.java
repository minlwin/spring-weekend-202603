package com.jdc.jdbc.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class AccountRepo {
	
	@Autowired
	private DataSource dataSource;

	public List<Account> getAll() {
		var result = new ArrayList<Account>();
		Connection connection = null;
		PreparedStatement stmt = null;
		
		try {
			
			connection = dataSource.getConnection();
			stmt = connection.prepareStatement("select * from account");
			
			var resultSet = stmt.executeQuery();
			
			while(resultSet.next()) {
				result.add(new Account(
					resultSet.getInt("id"), 
					resultSet.getString("name"), 
					resultSet.getString("phone"), 
					resultSet.getString("email")));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(null != stmt) {
				try {
					stmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			if(null != connection) {
				try {
					connection.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
		return result;
	}

	public Account findById(int id) {
		try(var connection = dataSource.getConnection();
				var stmt = connection.prepareStatement("select * from account where id = ?")) {
			
			stmt.setInt(1, id);
			var resultSet = stmt.executeQuery();
			
			while(resultSet.next()) {
				return new Account(
						resultSet.getInt("id"), 
						resultSet.getString("name"), 
						resultSet.getString("phone"), 
						resultSet.getString("email"));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}
	
	public int create(String name, String phone, String email) {
		try(var connection = dataSource.getConnection();
				var stmt = connection.prepareStatement(
						"insert into account (name, phone, email) values (?, ?, ?)", 
						Statement.RETURN_GENERATED_KEYS)) {
			
			stmt.setString(1, name);
			stmt.setString(2, phone);
			stmt.setString(3, email);
			stmt.executeUpdate();
			
			var rs = stmt.getGeneratedKeys();
			if(rs.next()) {
				return rs.getInt(1);
			}		
		} catch (Exception e) {
			e.printStackTrace();
		}

		return 0;
	}

	public Integer update(int id, String name, String phone, String email) {
		
		try(var conn = dataSource.getConnection();
				var stmt = conn.prepareStatement("update account set name = ?, phone = ?, email = ? where id = ?")) {
			
			stmt.setString(1, name);
			stmt.setString(2, phone);
			stmt.setString(3, email);
			stmt.setInt(4, id);
			
			return stmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}

	public Integer deleteById(int id) {
		try(var conn = dataSource.getConnection();
				var stmt = conn.prepareStatement("delete from account where id = ?")) {
			
			stmt.setInt(1, id);
			return stmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}
}
