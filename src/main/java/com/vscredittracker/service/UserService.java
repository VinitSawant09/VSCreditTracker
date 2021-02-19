package com.vscredittracker.service;

import com.vscredittracker.dao.UserDAO;
import com.vscredittracker.model.User;

public class UserService {

	public boolean registerUser(User objUser) {
		// TODO Auto-generated method stub
		System.out.println("Inside registerUser method of UserService");
		UserDAO objUserDAO = new UserDAO();
		boolean result = false;
		try
		{
			
			result = objUserDAO.registerUser(objUser);
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
		System.out.println("End of registerUser method of UserService");
		return result;
	}

	public boolean validateLogin(User objUser) 
	{
		// TODO Auto-generated method stub
		System.out.println("Inside registerUser method of UserService");
		UserDAO objUserDAO = new UserDAO();
		boolean result = false;
		try
		{
			result = objUserDAO.validateLogin(objUser);
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
		System.out.println("End of registerUser method of UserService");
		return result;
	}
	
	public int fetchUserId(User objUser) 
	{
		// TODO Auto-generated method stub
		System.out.println("Inside fetchUserId method of UserService");
		UserDAO objUserDAO = new UserDAO();
		int id = 0;
		try
		{
			id = objUserDAO.fetchUserId(objUser);
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
		
		System.out.println("End of fetchUserId method of UserService");
		return id;
	}

}
