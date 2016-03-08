 /* 
 * Standard.cpp
 * 
 * Authors: Denesh Parthipan, Luisa Rojas, Truyen Truong
 */

#include "Standard.h"

void Standard::Withdrawal() {

  string padded_acc_holder;
  string padded_acc_num;
  string padded_amount;
  string padded_new_balance;
  string temp_acc_num;
  string temp_amount;
  stringstream stream;
  float amount;
  float new_balance = 0.0;
  int acc_num;

  cout << "\nWithdrawal transaction selected.\n" << endl;

  cout << "Enter account number: ";
  cin >> temp_acc_num;

  regex re("[0-9]{0,5}");
  if (regex_match(temp_acc_num, re)) {
    acc_num = stoi(temp_acc_num);
  } else {
    cerr << "\n>>> ERROR: The account number entered is invalid.\n" << endl;
    return;
  }

  if (!curr_user.GetNum() != acc_num) {
    cerr << "\n>>> ERROR: The account number does not match the account number.\n" << endl;
    return;
  }

  if (transactions.is_Disabled(curr_user.GetNum())) {
      cerr << "\n>>> ERROR: Disabled accounts may not withdraw funds.\n" << endl;
      return;
  }

  if (transactions.is_New(curr_user.GetNum())) {
    cerr << "\n>>> ERROR: Newly created accounts may not withdraw funds. Please try again in 24 hours.\n" << endl;
    return;
  }

    if (transactions.is_Disabled(acc_num)) {
      cerr << "\n>>> ERROR: Disabled accounts may not withdraw funds.\n" << endl;
      return;
    } else if (transactions.is_New(acc_num)) {
      cerr << "\n>>> ERROR: Newly created accounts may not withdraw funds. Please try again in 24 hours.\n" << endl;
      return;
    }

    cout << "Enter amount to withdraw: ";
    cin >> temp_amount;

    if (transactions.is_Amount_Valid(temp_amount)) {
      amount = stof(temp_amount);
    } else {
      cerr << "\n>>> ERROR: The amount entered is invalid.\n" << endl;
      return;
    }

    padded_acc_holder = acc_holder;
    while (padded_acc_holder.length() < 20) {
      padded_acc_holder = padded_acc_holder + " ";
    }

    padded_acc_num = temp_acc_num;
    while (padded_acc_num.length() < 5) {
      padded_acc_num = "0" + padded_acc_num;
    }

    padded_amount = temp_amount;
    while (padded_amount.length() < 8) {
      padded_amount = "0" + padded_amount;
    }

    for (int i = 0; i < users.size(); i++) {
      if (users.at(i).GetNum() == acc_num) {

        if ((users.at(i).GetBalance() - users.at(i).GetDeposited()) < amount) {
          cerr << "\n>>> ERROR: You may not withdraw recently deposited funds.\n" << endl;
          return;
        }

        if (amount <= users.at(i).GetBalance() && (amount > 0.0) && fmod(amount, 5.0) == 0) {
          new_balance = users.at(i).GetBalance() - amount;
          users.at(i).SetBalance(new_balance);
          break;
        } else {
          cerr << "\n>>> ERROR: The amount entered is invalid.\n" << endl;
          return;
        }
      }
    }

    stream << fixed << setprecision(2) << new_balance;
    padded_new_balance = stream.str();
    while (padded_new_balance.length() < 8) {
      padded_new_balance = "0" + padded_new_balance;
    }
  
    cout << "\nFunds have been successfully withdrawn from the account " << padded_acc_num << "." << endl;
    cout << "New balance: $" + padded_new_balance << endl;

    string transaction_line = "01 " + padded_acc_holder + " " + padded_acc_num + " " + padded_amount + "   ";
    transaction_file.push_back(transaction_line);
    cout << "\nEnter a command.\n" << endl;
}

void Standard::Transfer() {
  cout << "Funds have been successfully transfered from your account to account " << endl;
}

void Standard::Paybill() {
  cout << "You have successfully paid a bill of $" << endl;
}

void Standard::Deposit() {
  cout << "Funds have been successfully added to your account." << endl;
}