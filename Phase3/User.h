/* 
 * User.h
 * 
 * Authors: Denesh Parthipan, Luisa Rojas, Truyen Truong
 */

#ifndef USER_H
#define USER_H
#include <stdio.h>
#include <stdlib.h>
#include <iostream>
#include <fstream>
#include <string.h>
#include <vector>
#include <regex>
#include "Standard.h"

using namespace std;

/*
 * User class holds the account holder name. Has functions to change
 * the data of the user and has transaction functions.
 */
class User {
 private:
  string acc_holder_;

 protected:
  Standard std_acc_;

 public:
  User(){};
  //	User(string acc_holder, int acc_num, float acc_balance, char acc_status, char acc_plan);
  ~User(){};
		
  // Get account holder name.
  string GetName() {return acc_holder_;}
  // Get account number.
  int GetNum() {return std_acc_.GetNum();}
  // Get account balance.
  float GetBalance() {return std_acc_.GetBalance();}
  // Get account status.
  char GetStatus() {return std_acc_.GetStatus();}
  // Get account plan.
  char GetPlan() {return std_acc_.GetPlan();}

  // Set account holder name.
  void SetName(string name) {acc_holder_ = name;}
  // Set account number.
  void SetNum(int num) {std_acc_.SetNum(num);}
  // Set account balance.
  void SetBalance(float balance) {std_acc_.SetBalance(balance);}
  // Set account status.
  void SetStatus(char status) {std_acc_.SetStatus(status);}
  // Set account plan.
  void SetPlan(char plan) {std_acc_.SetPlan(plan);}

  // Withdraw from the current user account.
  void Withdrawal() {std_acc_.Withdrawal();}
  // Transfer from the current user account to another user account.
  void Transfer() {std_acc_.Transfer();}
  // Pay a bill from the current user account to a company.
  void Paybill() {std_acc_.Paybill();}
  // Deposit money into the current user account.
  void Deposit() {std_acc_.Deposit();}
};

#endif