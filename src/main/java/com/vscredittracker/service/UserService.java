package com.vscredittracker.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.vscredittracker.dao.UserDAO;
import com.vscredittracker.model.CreditCard;
import com.vscredittracker.model.User;

@Component
public class UserService {

	@Autowired
	UserDAO userDao;
	
	private Logger logger = Logger.getLogger(UserService.class);
	
	public boolean registerUser(User objUser) {
		// TODO Auto-generated method stub
		logger.info("Inside registerUser method of UserService");
		
		boolean result = false;
		try
		{
			result = userDao.registerUser(objUser);
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
		logger.info("End of registerUser method of UserService");
		return result;
	}

	public boolean validateLogin(User objUser) 
	{
		// TODO Auto-generated method stub
		logger.info("Inside registerUser method of UserService");
		
		boolean result = false;
		try
		{
			result = userDao.validateLogin(objUser);
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
		logger.info("End of registerUser method of UserService");
		return result;
	}
	
	public int fetchUserId(User objUser) 
	{
		// TODO Auto-generated method stub
		logger.info("Inside fetchUserId method of UserService");
		
		int id = 0;
		try
		{
			id = userDao.fetchUserId(objUser);
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
		
		logger.info("End of fetchUserId method of UserService");
		return id;
	}
	
	public boolean addCreditCard(CreditCard objCreditCard) {
		logger.info("Inside addCreditCard method of UserService");
		
		boolean result = false;
		
     	try
     	{
     		
     		result = userDao.addCreditCard(objCreditCard);
     	}
     	catch(Exception e)
     	{
     		
     		e.printStackTrace();
     	}
     	logger.info("End of addCreditCard method of UserService");
         return result;
	}

	public List<CreditCard> getCreditCardAfterUpdation(CreditCard objCreditCard) {
		
		logger.info("Inside getCreditCardAfterUpdation method of UserService");
		List<CreditCard> todolist = new ArrayList<CreditCard>();
     	try
     	{
     		 
     		 todolist = userDao.getCreditCardAfterUpdation(objCreditCard);
     	}
     	catch(Exception e)
     	{
     		
     		e.printStackTrace();
     	}
     	logger.info("End of getCreditCardAfterUpdation method of UserService");
         return todolist;
	}
	
	public List<CreditCard> getAllCreditCardAfterUpdation() {
			
		logger.info("Inside getAllCreditCardAfterUpdation method of UserService");
			List<CreditCard> todolist = new ArrayList<CreditCard>();
	     	try
	     	{
	     		 
	     		 todolist = userDao.getAllCreditCardAfterUpdation();
	     	}
	     	catch(Exception e)
	     	{
	     		
	     		e.printStackTrace();
	     	}
	     	logger.info("End of getAllCreditCardAfterUpdation method of UserService");
	         return todolist;
		}
	
public int update(CreditCard objCreditCard) {
		
	logger.info("Inside update method of UserService");
		
		int result =0;
     	try
     	{
     		 
     		result = userDao.update(objCreditCard);
     	}
     	catch(Exception e)
     	{
     		
     		e.printStackTrace();
     	}
     	logger.info("End of update method of UserService");
         return result;
	}

}
