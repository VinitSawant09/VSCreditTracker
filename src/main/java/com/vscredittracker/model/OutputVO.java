package com.vscredittracker.model;

import java.util.List;

public class OutputVO {
	private String status;
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getStatusCode() {
		return statusCode;
	}
	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}
	private String statusCode;
	private List<CreditCard> creditCardList;
	public List<CreditCard> getCreditCardList() {
		return creditCardList;
	}
	public void setCreditCardList(List<CreditCard> creditCardList) {
		this.creditCardList = creditCardList;
	}
	
}
