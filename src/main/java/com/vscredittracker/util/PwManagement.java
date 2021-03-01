package com.vscredittracker.util;

import org.mindrot.jbcrypt.BCrypt;

public class PwManagement 
{

	public String generatePassword(String password) {
	    
		String passwordHash = "";
		passwordHash = BCrypt.hashpw(password, BCrypt.gensalt());
		return passwordHash;
	}
	
	public boolean checkPassword(String password, String storedPasswordHash)
	{
		boolean result =false;
		
		result = BCrypt.checkpw(password, storedPasswordHash);
		return result;
	}
	
	
	public static void main(String args[])
	{
		String password ="admin";
		PwManagement pw = new PwManagement();
		System.out.println(pw.generatePassword(password));
	}
}
