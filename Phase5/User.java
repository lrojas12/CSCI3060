/**
 * This class will contain a constructor to create User objects, as well as
 * methods to access it's private members.
 *
 * User.java
 * 
 * @author Luisa Rojas
 * @author Truyen Truong
 * @author Danesh Parthipan
 */

import java.io.*;
import java.util.*;

public class User {

  private String accHolder;
  private int accNum;
  private float accBalance;
  private char accStatus;
  private int numTran;
  private char accPlan;

  User() {
    accHolder = null;
    accNum = 0;
    accBalance = 0;
    accStatus = ' ';
    numTran = 0;
    accPlan = ' ';
  }

  User(String holder, int num, float balance, char status, int tranCount, char plan) {
		
    accHolder = holder;
    accNum = num;
    accBalance = balance;
    accStatus = status;
    numTran = tranCount;
    accPlan = plan;
  }

  /**
   * Get account holder name.
   */
  public String getName() {
    return this.accHolder;
  }

  /**
   * Get the account number.
   */
  public int getNum() {
    return this.accNum;
  }
  	
  /**
   * Get the account balance.
   */
  public float getBalance() {
    return this.accBalance;
  }
  	
  /**
   * Get the account status.
   */
  public char getStatus() {
    return this.accStatus;
  }
  	
  /**
   * Get the transcation count for this user.
   */
  public int getNumTran() {
    return this.numTran;
  }
  	
  /**
   * Get the account plan.
   */
  public char getPlan() {
    return this.accPlan;
  }

  /** 
   * Set account holder name.
   */
  public void setName(String name) {
    this.accHolder = name;
  }
  	
  /** 
   * Change the account number.
   */
  public void setNum(int num) {
    this.accNum = num;
  }
  	
  /**
   * Change the account balance.
   */
  public void setBalance(float balance) {
    this.accBalance = balance;
  }
  	
  /**
   * Change the account status.
   */
  public void setStatus(char status) {
    this.accStatus = status;
  }
	
  /**
  * Get the transcation count for this user.
  */
  public void setNumTran(int tranCount) {
    this.numTran = tranCount;
  }
  	
  /** 
   * Change the account plan.
   */
  public void setPlan(char plan) {
    this.accPlan = plan;
  }
}