package com.vscredittracker.dao;


import java.text.SimpleDateFormat;

import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Component;

import com.vscredittracker.model.CreditCard;
import com.vscredittracker.model.User;
import com.vscredittracker.util.PwManagement;
import com.vscredittracker.hibernate.HibernateUtil;

@Component
public class UserDAO {

	private Logger logger = Logger.getLogger(UserDAO.class);

	
	public boolean registerUser(User objUser) {
		// TODO Auto-generated method stub
		logger.info("Inside registerUser method of UserDAO");
		
		objUser.setCreatedOn(new SimpleDateFormat("dd-MM-yyyy").format(new Date()));
        Transaction transaction = null;
        try 
        {
        	Session session = HibernateUtil.getSessionFactory().openSession();
            // start a transaction
            transaction = session.beginTransaction();
            PwManagement pw =new PwManagement();
            objUser.setPassword(pw.generatePassword(objUser.getPassword()));
            
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
        logger.info("End of registerUser method of UserDAO");
    	return false;
		
	}
	
	
	public boolean checkUserNameExists(User lUser)
    {
		 logger.info("Inside checkUserNameExists method of UserDAO");
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
    	 
    	    logger.info("End of checkUserNameExists method of UserDAO");
			return false;
    	
    }
	
	public boolean validateLogin(User objUser) {
   	 
		logger.info("Inside validateLogin method of UserDAO");
   	 	boolean result = false;
       try{ 
			
			Session session = HibernateUtil.getSessionFactory().openSession() ;
			

           // get an student object
           String hql = " FROM User l WHERE l.userid = :userId";
           
           Query query = session.createQuery(hql);
           query.setParameter("userId", objUser.getUserid());
           List<User> results = query.getResultList();
           PwManagement pw =new PwManagement();
           if (results != null && !results.isEmpty()) 
           {
           	System.out.println(results.get(0).getPassword());
           	System.out.println(objUser.getPassword());
           	String actual= results.get(0).getPassword();
           	String entered = objUser.getPassword();
           	if(pw.checkPassword(entered, actual))
           	{
           		logger.info("User credentials are valid.!!!");
           		result = true;
           		return result;
           	}
           	
              
           }
           // commit transaction
         
       } catch (Exception e) {
       	 e.printStackTrace();
           }
       
        logger.info("End of validateLogin method of UserDAO");
		return result;
          
       
		
   }
	
	public int fetchUserId(User objUser) {
		// TODO Auto-generated method stub
		logger.info("Inside fetchUserId method of UserDAO");
		 int id = 0;
    	 try{ 
				
				Session session = HibernateUtil.getSessionFactory().openSession() ;
				

	            // get an student object
	            String hql = "select id FROM User l WHERE l.userid = :userId";
	            
	            Query query = session.createQuery(hql);
	            query.setParameter("userId", objUser.getUserid());
	            id =(int) query.getResultList().get(0);

	           
	            // commit transaction
	          
	        } catch (Exception e) {
	        	 e.printStackTrace();
	            }
    	    logger.info("End of fetchUserId method of UserDAO");
			return id;
		
	}


	public boolean addCreditCard(CreditCard objCreditCard) 
	{
		logger.info("Inside addCreditCard method of UserDAO");
		
		
        Transaction transaction = null;
        boolean result = false;
        try {
        	Session session = HibernateUtil.getSessionFactory().openSession();
            // start a transaction
            transaction = session.beginTransaction();
            
            session.save(objCreditCard);
           
           	// commit transaction
            transaction.commit();
            result = true;
           
            
        
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
                return false;
            }
            e.printStackTrace();
        }
        logger.info("End of addCreditCard method of UserDAO");
    	return result;
	}
	
	 public List<CreditCard> getCreditCardAfterUpdation(CreditCard objCreditCard) {
			
		 logger.info("Inside getCreditCardAfterUpdation method of UserDAO");
			// TODO Auto-generated method stub
			 List <CreditCard> creditCardList = null;
			 Query query = null;
			
			try{ 
				
				 	Session session = HibernateUtil.getSessionFactory().openSession() ;
				    String hql = " FROM CreditCard td WHERE td.id = :id";
		            
				    query = session.createQuery(hql);
		            query.setParameter("id", objCreditCard.getId());
		            creditCardList = query.getResultList();
		            System.out.println("List size="+creditCardList.size());
		        } 
			catch (Exception e) {
				
		        	 e.printStackTrace();
		            }
	           
			logger.info("End of getCreditCardAfterUpdation method of UserDAO");
			return creditCardList;
		}
	 
	 public List<CreditCard> getAllCreditCardAfterUpdation() {
			
		 logger.info("Inside getAllCreditCardAfterUpdation method of UserDAO");
			// TODO Auto-generated method stub
			 List <CreditCard> creditCardList = null;
			 Query query = null;
			
			try{ 
				
				 	Session session = HibernateUtil.getSessionFactory().openSession() ;
				    String hql = " FROM CreditCard ";
		            
				    query = session.createQuery(hql);
		            
		            creditCardList = query.getResultList();
		            System.out.println("List size="+creditCardList.size());
		        } 
			catch (Exception e) {
				
		        	 e.printStackTrace();
		            }
	           
			logger.info("End of getAllCreditCardAfterUpdation method of UserDAO");
			return creditCardList;
		}
	 
	 public int update(CreditCard objCreditCard) {
			// TODO Auto-generated method stub
			
		 logger.info("Inside update method of UserDAO");
			int result = 0;
			 Transaction transaction = null;
				try
				{ 
				
				Session session = HibernateUtil.getSessionFactory().openSession() ;
				 transaction = session.beginTransaction();
	            String query = "update CreditCard td set td.expiryDate=:expiryDate where td.creditCardNumber=:creditCardNumber";
	            Query query1 = session.createQuery(query);
	            query1.setParameter("expiryDate", objCreditCard.getExpiryDate());
	            query1.setParameter("creditCardNumber", objCreditCard.getCreditCardNumber());
	            
	            result = query1.executeUpdate();
	           
	            transaction.commit();
	        } catch (Exception e) {
	        	 if (transaction != null) {
		                transaction.rollback();
		            }
		            e.printStackTrace();
	            }
				logger.info("End of update method of UserDAO");
			return result;
		}
}
