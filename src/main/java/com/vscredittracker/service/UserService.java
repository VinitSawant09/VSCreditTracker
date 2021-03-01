package com.vscredittracker.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.vscredittracker.dao.UserDAO;
import com.vscredittracker.model.CreditCard;
import com.vscredittracker.model.User;

@Component
public class UserService {

	@Autowired
	UserDAO userDao;
	
	public boolean registerUser(User objUser) {
		// TODO Auto-generated method stub
		System.out.println("Inside registerUser method of UserService");
		
		boolean result = false;
		try
		{
			result = userDao.registerUser(objUser);
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
		
		boolean result = false;
		try
		{
			result = userDao.validateLogin(objUser);
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
		
		int id = 0;
		try
		{
			id = userDao.fetchUserId(objUser);
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
		
     	try
     	{
     		
     		result = userDao.addCreditCard(objCreditCard);
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
     		 
     		 todolist = userDao.getCreditCardAfterUpdation(objCreditCard);
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
	     		 
	     		 todolist = userDao.getAllCreditCardAfterUpdation();
	     	}
	     	catch(Exception e)
	     	{
	     		
	     		e.printStackTrace();
	     	}
	     	System.out.println("End of getAllCreditCardAfterUpdation method of UserService");
	         return todolist;
		}
	
public int update(CreditCard objCreditCard) {
		
		System.out.println("Inside update method of UserService");
		
		int result =0;
     	try
     	{
     		 
     		result = userDao.update(objCreditCard);
     	}
     	catch(Exception e)
     	{
     		
     		e.printStackTrace();
     	}
     	System.out.println("End of update method of UserService");
         return result;
	}

}
