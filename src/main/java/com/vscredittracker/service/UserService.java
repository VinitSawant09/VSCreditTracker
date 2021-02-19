package com.vscredittracker.service;

import java.util.ArrayList;
import java.util.List;

import com.vscredittracker.dao.UserDAO;
import com.vscredittracker.model.CreditCard;
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
	
	public boolean addCreditCard(CreditCard objCreditCard) {
		System.out.println("Inside addCreditCard method of UserService");
		
		boolean result = false;
		UserDAO userDAO = new  UserDAO ();
     	try
     	{
     		
     		result = userDAO.addCreditCard(objCreditCard);
     	}
     	catch(Exception e)
     	{
     		
     		e.printStackTrace();
     	}
     	System.out.println("End of addCreditCard method of UserService");
         return result;
	}

	public List<CreditCard> getCreditCardAfterUpdation(CreditCard objCreditCard) {
		
		System.out.println("Inside getCreditCardAfterUpdation method of UserService");
		List<CreditCard> todolist = new ArrayList<CreditCard>();
     	try
     	{
     		 UserDAO userDAO = new  UserDAO ();
     		 todolist = userDAO.getCreditCardAfterUpdation(objCreditCard);
     	}
     	catch(Exception e)
     	{
     		
     		e.printStackTrace();
     	}
     	System.out.println("End of getCreditCardAfterUpdation method of UserService");
         return todolist;
	}
	
public List<CreditCard> getAllCreditCardAfterUpdation() {
		
		System.out.println("Inside getAllCreditCardAfterUpdation method of UserService");
		List<CreditCard> todolist = new ArrayList<CreditCard>();
     	try
     	{
     		 UserDAO userDAO = new  UserDAO ();
     		 todolist = userDAO.getAllCreditCardAfterUpdation();
     	}
     	catch(Exception e)
     	{
     		
     		e.printStackTrace();
     	}
     	System.out.println("End of getAllCreditCardAfterUpdation method of UserService");
         return todolist;
	}

}
