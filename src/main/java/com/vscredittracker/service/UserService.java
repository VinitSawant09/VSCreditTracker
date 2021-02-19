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

	public boolean validateLogin(User objUser) {
		// TODO Auto-generated method stub
		System.out.println("Inside registerUser method of UserService");
		UserDAO objUserDAO = new UserDAO();
		boolean result = false;
		
		
		System.out.println("End of registerUser method of UserService");
		return result;
	}

}
