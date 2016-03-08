/* 
 * Standard.h
 * 
 * Authors: Denesh Parthipan, Luisa Rojas, Truyen Truong
 */

#ifndef STANDARD_H
#define STANDARD_H
#include <stdio.h>
#include <stdlib.h>
#include <iostream>
#include <fstream>
#include <string.h>
#include <vector>
#include <regex>
#include "IAccount.h"
#include "TransactionHelper.h"

using namespace std;

/*
 * Standard is for standard accounts. Has variables for the current
 * standard account. Has functions to access the current standard account's
 * data. Has implementations of the transaction functions.
 */
class Standard: public virtual IAccount {
 private:
  int acc_num_;
  float acc_balance_;
  char acc_status_;
  char acc_plan_;

 public:
  Standard() {};
  //	Standard(int acc_num, float acc_balance, char acc_status, char acc_plan);
  ~Standard() {} // MAYBE THIS COULD BE PART OF THE DELETE FUNCTION - SAME JOB
		
  // Get the account number.
  int GetNum() {return acc_num_;}
  // Get the account balance.
  float GetBalance() {return acc_balance_;}
  // Get the account status.
  char GetStatus() {return acc_status_;}
  // Get the account plan.
  char GetPlan() {return acc_plan_;}

  // Change the account number.
  void SetNum(int num) {acc_num_ = num;}
  // Change the account balance.
  void SetBalance(float balance) {acc_balance_ = balance;}
  // Change the account status.
  void SetStatus(char status) {acc_status_ = status;}
  // Change the account plan.
  void SetPlan(char plan) {acc_plan_ = plan;}

  //	void login(string acc_holder);
  // Withdraw from the current user account.
  void Withdrawal();
  // Transfer from the current user account to another user account.
  void Transfer();
  // Pay a bill to a company from the current user account.
  void Paybill();
  // Deposit money into the current user account.
  void Deposit();
  //	bool logout();
};

#endif