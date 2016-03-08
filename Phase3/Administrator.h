/* 
 * Administrator.h
 * 
 * Authors: Denesh Parthipan, Luisa Rojas, Truyen Truong
 */

#ifndef ADMINISTRATOR_H
#define ADMINISTRATOR_H
#include <stdio.h>
#include <stdlib.h>
#include <iostream>
#include <fstream>
#include <string>
#include "IAccount.h"
#include "User.h"
#include "Standard.h"
#include "TransactionHelper.h"

using namespace std;

/*
 * Administrator class contains all the transaction functions that
 * the administrators can do. These functions are used when the
 * account is an administrator.
 */
class Administrator: public virtual IAccount {
 public:
  Administrator() {};
  ~Administrator() {}

  // This is the create function to create a new user account.
  void Create();
  // This is the delete function to delete an existing user account.
  void Deleted();
  // This is the disable function to disable an user account that is currently enabled.
  void Disable(int acc_num);
  // This is the enable function to enable an user account that is currently disabled.
  void Enable(int acc_num);
  // This is the change plan function used to change from between non-student and student plans.
  void Changeplan(int acc_num);

  // Administrator implementation of withdrawal to withdraw from an user account.
  void Withdrawal(int acc_num, float amount);
  // Administrator implementation of transfer to transfer from an user account to another user account.
  void Transfer(int acc_num_f, int acc_num_t, float amount);
  // Administrator implementation of paybill to pay a bill to a company from an user account.
  void Paybill(int acc_num, string company, float amount);
  // Administrator implementation of deposit to deposit money into an user account.
  void Deposit(int acc_num, float amount);
  // bool logout();
};

#endif