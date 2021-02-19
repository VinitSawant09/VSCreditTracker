package com.vscredittracker.dao;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.vscredittracker.model.User;
import com.vscredittracker.hibernate.HibernateUtil;


public class UserDAO {

	
	
	public boolean registerUser(User objUser) {
		// TODO Auto-generated method stub
		System.out.println("Inside registerUser method of UserDAO");
		
		objUser.setCreatedOn(new SimpleDateFormat("dd-MM-yyyy").format(new Date()));
        Transaction transaction = null;
        try 
        {
        	Session session = HibernateUtil.getSessionFactory().openSession();
            // start a transaction
            transaction = session.beginTransaction();
            
            if(!checkUserNameExists(objUser))
            {
	            // save the user objects
	            session.save(objUser);
	            transaction.commit();
	            return true;
            }
            
           	// commit transaction
           
       } 
        catch (Exception e) 
        {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        System.out.println("End of registerUser method of UserDAO");
    	return false;
		
	}
	
	
	public boolean checkUserNameExists(User lUser)
    {
    	 System.out.println("Inside checkUserNameExists method of UserDAO");
    	 try{ 
				
				Session session = HibernateUtil.getSessionFactory().openSession() ;
				
	            
	            String hql = " FROM User l WHERE l.userid = :userId";
	            
	            Query query = session.createQuery(hql);
	            query.setParameter("userId", lUser.getUserid());
	            List<User> results = query.getResultList();

	            if (results != null && !results.isEmpty())
	            	
	            	{
	            		System.out.println("User Exists.!");
	            		return true;
	            	}
	           
	          
	        } catch (Exception e) {
	        	 e.printStackTrace();
	            }
    	 
    	    System.out.println("End of checkUserNameExists method of UserDAO");
			return false;
    	
    }
	
	public boolean validateLogin(User objUser) {
   	 
   	 	System.out.println("Inside validateLogin method of UserDAO");
   	 	boolean result = false;
       try{ 
			
			Session session = HibernateUtil.getSessionFactory().openSession() ;
			

           // get an student object
           String hql = " FROM User l WHERE l.userid = :userId";
           
           Query query = session.createQuery(hql);
           query.setParameter("userId", objUser.getUserid());
           List<User> results = query.getResultList();

           if (results != null && !results.isEmpty()) 
           {
           	System.out.println(results.get(0).getPassword());
           	System.out.println(objUser.getPassword());
           	String actual= results.get(0).getPassword();
           	String entered = objUser.getPassword();
           	if(actual.equals(entered))
           	{
           		System.out.println("User credentials are valid.!!!");
           		result = true;
           		return result;
           	}
           	
              
           }
           // commit transaction
         
       } catch (Exception e) {
       	 e.printStackTrace();
           }
       
   		System.out.println("End of validateLogin method of UserDAO");
		return result;
          
       
		
   }
}