package com.vscredittracker.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "CreditCard")
public class CreditCard {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "cid")
	private int cid;
	
	public CreditCard() {
		super();
		// TODO Auto-generated constructor stub
	}
	public int getCid() {
		return cid;
	}
	public void setCid(int cid) {
		this.cid = cid;
	}
	public String getCardMerchant() {
		return cardMerchant;
	}
	public void setCardMerchant(String cardMerchant) {
		this.cardMerchant = cardMerchant;
	}
	public Long getCreditCardNumber() {
		return creditCardNumber;
	}
	public void setCreditCardNumber(Long creditCardNumber) {
		this.creditCardNumber = creditCardNumber;
	}
	public String getMaxLimit() {
		return maxLimit;
	}
	public void setMaxLimit(String maxLimit) {
		this.maxLimit = maxLimit;
	}
	public String getExpiryDate() {
		return expiryDate;
	}
	public void setExpiryDate(String expiryDate) {
		this.expiryDate = expiryDate;
	}
	@Column(name = "cardMerchant")
	private String cardMerchant;
	
	@Column(name = "creditCardNumber",unique=true)
	private Long creditCardNumber;
	@Column(name = "maxLimit")
	private String maxLimit;
	@Column(name = "expiryDate")
	private String expiryDate;
	@Column(name = "userid")
	private int id;
	
	public CreditCard(int cid, String cardMerchant, Long creditCardNumber, String maxLimit, String expiryDate,
			int id) {
		super();
		this.cid = cid;
		this.cardMerchant = cardMerchant;
		this.creditCardNumber = creditCardNumber;
		this.maxLimit = maxLimit;
		this.expiryDate = expiryDate;
		this.id = id;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
}
