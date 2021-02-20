package com.vscredittracker.unit;


import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import com.vscredittracker.controller.CreditTrackerController;
import com.vscredittracker.model.CreditCard;
import com.vscredittracker.model.User;
import com.vscredittracker.service.UserService;


public class UnitTesting {

	@Test
    public void checkIfUserIdBlank() {
        
		CreditTrackerController credObj = new CreditTrackerController();
        // assert statements
        assertEquals(true, credObj.validateUserId("abc"), "Blank or null User name");
           
    }
	
	
	
	@Test
    public void checkIfPasswordBlank() {
         
		CreditTrackerController contObj = new CreditTrackerController();
        // assert statements
        assertEquals(true, contObj.validatePassword("sduvuk"), "Blank or null Password");
           
    }
	
	@Test
    public void registerUser() {
        
		UserService servObj = new UserService();
		User objUser = new User ();
		objUser.setUserid("q");
		objUser.setPassword("v");
        
        assertEquals(true, servObj.registerUser(objUser), "Success");
           
    }
	
	@Test
    public void validateUser() {
        
		UserService servObj = new UserService();
		User objUser = new User ();
		objUser.setUserid("q");
		objUser.setPassword("v");
        // assert statements
        assertEquals(true, servObj.validateLogin(objUser), "Valid");
           
    }
	
	
	@Test
    public void addToDo() {
        
		UserService servObj = new UserService();
		CreditCard objCreditCard = new CreditCard ();
		objCreditCard.setCardMerchant("ICICI");
		objCreditCard.setExpiryDate("2021-12");
		objCreditCard.setMaxLimit("20000");
		Long l= 8979461288855841278L;
		objCreditCard.setCreditCardNumber(l);
		objCreditCard.setId(2);
        // assert statements
        assertEquals(true, servObj.addCreditCard(objCreditCard), "Success");
           
    }
	
	@Test
    public void updateToDo() {
         
		UserService servObj = new UserService();
		CreditCard objCreditCard = new CreditCard ();
		objCreditCard.setCreditCardNumber(8979461288855841278L);
		objCreditCard.setExpiryDate("2021-10");
        // assert statements
        assertEquals(1, servObj.update(objCreditCard), "Success");
           
    }
	
	
	
	
	
	
	
}
