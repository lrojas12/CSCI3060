/* This software is the front-end design of the Watermelon Banking System.
 * It is to be ran as a console application.
 * Input(s): Command line and file input
 * Output(s): Command line prompts and file output.
 * 
 * main.cpp
 * 
 * Authors: Denesh Parthipan, Luisa Rojas, Truyen Truong
 */

#include <stdio.h>
#include <stdlib.h>
#include <iostream>
#include <fstream>
#include <string>
#include <vector>
#include "User.h"
#include "TransactionHelper.h"
#include "Standard.h"
#include "Administrator.h"

using namespace std;

/* These class objects will be used to refence
 * the different methods as needed.
 */
Standard s;
Administrator a;

/* Second declaration of external global variables.
 * Needed in order to access and edit them from any
 * class.
 */
User curr_user;
vector<User> users;
vector<string> transaction_file;
string mode;
string acc_holder;
bool is_logged;
TransactionHelper transactions;

int main (int argc, char *argv[]) {

  transactions.LoadAccounts();
  transactions.PrintWelcomeMessage();

  string command;
  is_logged = false;

  int acc_num;
  float amount;
  int acc_num_t;
  int acc_num_f;
  string company;

  // For command line input only
  if (argc == 1) {

    // Permanently listening for commands
    while(true) {
      cin >> command;

      // If command is LOGIN
      if (transactions.to_Lower(command).compare("login") == 0)
        transactions.Login();

      // If command is LOGOUT
      else if (transactions.to_Lower(command).compare("logout") == 0)
        transactions.Logout();

      // If command is CREATE (Privileged)
      else if (transactions.to_Lower(command).compare("create") == 0)
        if (is_logged) {
          if (transactions.is_Admin()) 
            a.Create();
          else
            cerr << "\n>>> ERROR: You need to be an administrator to create a new account.\n" << endl;
        } else 
          cerr << "\n>>> ERROR: Must be logged in before invoking any other commands." << endl;

      // If command is DELETE (Privileged)
      else if (transactions.to_Lower(command).compare("delete") == 0) {
        if (transactions.is_Admin()) {
          a.Deleted();
        } else {
          cerr << "\n>>> ERROR: You need to be an administrator to delete an account.\n" << endl;
        }

      // If command is DISABLE (Privileged)
      } else if (transactions.to_Lower(command).compare("disable") == 0) {
        if (transactions.is_Admin()) {
          cout << "Enter account holder's name: ";
          cin >> acc_holder;
          cout << "Enter account number: ";
          cin >> acc_num;
          a.Disable(acc_num);
        } else {
          cerr << "\n>>> ERROR: You need to be an administrator to disable an account.\n" << endl;			
        }

      // If command is ENABLE (Privileged)
      } else if (transactions.to_Lower(command).compare("enable") == 0) {
        if (transactions.is_Admin()) {
          cout << "Enter account holder's name: ";
          cin >> acc_holder;
          cout << "Enter account number: ";
          cin >> acc_num;
          a.Enable(acc_num);
        } else {
          cerr << "\n>>> ERROR: You need to be an administrator to enable an account.\n" << endl;			
        }
      // If command is DEPOSIT
      } else if (transactions.to_Lower(command).compare("deposit") == 0) {
        if (transactions.is_Admin()) {
          cout << "Enter account holder's name: ";
          cin >> acc_holder;
          cout << "Enter account number: ";
          cin >> acc_num;
          cout << "Enter amount to deposit: ";
          cin >> amount;
          a.Deposit(acc_num, amount);
        } else {
          cout << "Enter account number: ";
          cin >> acc_num;
          cout << "Enter amount to deposit: ";
          cin >> amount;
          s.Deposit(acc_num, amount);
        }

      // If command is WITHDRAWAL
      } else if (transactions.to_Lower(command).compare("withdrawal") == 0) {
        if (transactions.is_Admin()) {
          cout << "Enter account holder's name: ";
          cin >> acc_holder;
          cout << "Enter account number: ";
          cin >> acc_num;
          cout << "Enter amount to withdraw: ";
          cin >> amount;
          a.Withdrawal(acc_num, amount);
        } else {
          cout << "Enter account number: ";
          cin >> acc_num;
          cout << "Enter amount to withdraw: ";
          cin >> amount;
          s.Withdrawal(acc_num, amount);
        }

      // If command is TRANSFER
      } else if (transactions.to_Lower(command).compare("transfer") == 0) {
        if (transactions.is_Admin()) {
          cout << "Enter origin account holder's name: ";
          cin >> acc_holder;
          cout << "Enter origin account number: ";
          cin >> acc_num_f;
          cout << "Enter destination account number: ";
          cin >> acc_num_t;
          cout << "Enter amount to transfer: ";
          cin >> amount;
          a.Transfer(acc_num_f, acc_num_t, amount);
        } else {
          cout << "Enter your account number: ";
          cin >> acc_num_f;
          cout << "Enter destination account number: ";
          cin >> acc_num_t;
          cout << "Enter amount to transfer: ";
          cin >> amount;
          s.Transfer(acc_num_f, acc_num_t, amount);
        }

      // If command is PAYBILL
      } else if (transactions.to_Lower(command).compare("paybill") == 0) {
        if (transactions.is_Admin()) {
          cout << "Enter origin account holder's name: ";
          cin >> acc_holder;
          cout << "Enter origin account number: ";
          cin >> acc_num_f;
          cout << "Enter company: ";
          cin >> company;
          cout << "Enter amount to pay: ";
          cin >> amount;
          a.Paybill(acc_num_f, company, amount);
        } else {
          cout << "Enter origin account number: ";
          cin >> acc_num_f;
          cout << "Enter company: ";
          cin >> company;
          cout << "Enter amount to pay: ";
          cin >> amount;
          s.Paybill(acc_num_f, company, amount);
        }

      // If command is CHANGEPLAN
      } else if (transactions.to_Lower(command).compare("changeplan") == 0) {
        if (transactions.is_Admin()) {
          cout << "Enter account holder's name: ";
          cin >> acc_holder;
          cout << "Enter account number: ";
          cin >> acc_num;
          a.Changeplan(acc_num);
        } else {
          cerr << "\n>>> ERROR: You need to be an administrator to change transcation payment plans.\n" << endl;			
        }

      // If command is HELP
      } else if (transactions.to_Lower(command).compare("help") == 0) {
        transactions.PrintHelp();

      // If command is QUIT
      } else if (transactions.to_Lower(command).compare("quit") == 0) {
        if (!is_logged)
          return 1;
        else
          cerr << "\n>>> ERROR: You may not quit while logged in.\n" << endl;
      } else {
        cerr << "\n>>> ERROR: Invalid command.\n" << endl;
      }
    }
  } else {
     cerr << "\n>>> ERROR: Too many arguments.\n" << endl;
     return -1;
  }
}