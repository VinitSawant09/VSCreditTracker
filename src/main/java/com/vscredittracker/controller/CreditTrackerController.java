package com.vscredittracker.controller;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@CrossOrigin
@Component
@Controller
@Scope("session")
public class CreditTrackerController {

	@RequestMapping(value = "/",method = RequestMethod.GET)  
	
	 public String landing()
	 {  
	    System.out.println("Inside landing method of CreditTrackerController");
	    System.out.println("Redirecting to login page.!!");
       return "index";  
    }  
	
	 @RequestMapping(value = "/signup",method = RequestMethod.GET)  
		
	 public String signup()
	 {  
	    System.out.println("Inside signup method of CreditTrackerController");
	    System.out.println("Redirecting to Sign up page.!!");
        return "signup";  
     }  
}
