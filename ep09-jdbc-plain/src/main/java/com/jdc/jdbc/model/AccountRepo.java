package com.jdc.jdbc.model;

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
		try(var connection = dataSource.getConnection();
				var stmt = connection.prepareStatement("select * from account")) {
			
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
		}
		
		return result;
	}

	public Account findById(int id) {
		// TODO Auto-generated method stub
		return null;
	}
}
