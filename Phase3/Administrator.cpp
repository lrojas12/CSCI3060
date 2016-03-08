/* 
 * Administrator.cpp
 * 
 * Authors: Denesh Parthipan, Luisa Rojas, Truyen Truong
 */

#include "Administrator.h"

void Administrator::Withdrawal() {

  string padded_acc_holder;
  string padded_acc_num;
  string padded_amount;
  string padded_new_balance;
  string acc_holder;
  string temp_acc_num;
  string temp_amount;
  stringstream stream;
  float amount;
  float new_balance = 0.0;
  int acc_num;

  cout << "\nWithdrawal transaction selected.\n" << endl;

  cout << "Enter account holder's name: ";
  cin >> acc_holder;

  if (!transactions.HolderExists(acc_holder)) {
    cerr << "\n>>> ERROR: The account holder name entered is not valid.\n" << endl;
    return;
  } else if (acc_holder.compare("END_OF_FILE") == 0) {
    cerr << "\n>>> ERROR: You may not withdraw funds from this account.\n" << endl;
    return;
  }

  cout << "Enter account number: ";
  cin >> temp_acc_num;

  regex re("[0-9]{0,5}");
  if (regex_match(temp_acc_num, re)) {
    acc_num = stoi(temp_acc_num);
  } else {
    cerr << "\n>>> ERROR: The account number entered is invalid.\n" << endl;
    return;
  }

  if (transactions.Matches(acc_holder, acc_num)) {

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

  } else {
      cerr << "\n>>> ERROR: The account number entered does not match the account holder name.\n" << endl;
  }
}

void Administrator::Transfer() {
  cout << "Funds have been successfully transfered from account " << endl;
}

void Administrator::Paybill() {
  cout << "You have successfully paid a bill of $" << endl;
}

void Administrator::Deposit() {

  string padded_acc_holder;
  string padded_acc_num;
  string padded_amount;
  string padded_new_balance;
  string acc_holder;
  string temp_acc_num;
  string temp_amount;
  stringstream stream;
  float amount;
  float new_balance = 0.0;
  int acc_num;

  cout << "\nDeposit transaction selected.\n" << endl;

  cout << "Enter account holder's name: ";
  cin >> acc_holder;

  if (!transactions.HolderExists(acc_holder)) {
    cerr << "\n>>> ERROR: The account holder name entered is not valid.\n" << endl;
    return;
  } else if (acc_holder.compare("END_OF_FILE") == 0) {
    cerr << "\n>>> ERROR: You may not withdraw funds from this account.\n" << endl;
    return;
  }

  cout << "Enter account number: ";
  cin >> temp_acc_num;

  regex re("[0-9]{0,5}");
  if (regex_match(temp_acc_num, re)) {
    acc_num = stoi(temp_acc_num);
  } else {
    cerr << "\n>>> ERROR: The account number entered is invalid.\n" << endl;
    return;
  }

  if (transactions.Matches(acc_holder, acc_num)) {

    if (transactions.is_Disabled(acc_num)) {
      cerr << "\n>>> ERROR: Disabled accounts may not deposit funds.\n" << endl;
      return;
    } else if (transactions.is_New(acc_num)) {
      cerr << "\n>>> ERROR: Newly created accounts may not deposit funds. Please try again in 24 hours.\n" << endl;
      return;
    }

    cout << "Enter amount to deposit: ";
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

        if (((amount + users.at(i).GetBalance()) < 100000.00) && (amount > 0.0)) {
          new_balance = users.at(i).GetBalance() + amount;
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
  
    cout << "\nFunds have been successfully added to the account " << padded_acc_num << "." << endl;
    cout << "New balance: $" + padded_new_balance << endl;

    string transaction_line = "01 " + padded_acc_holder + " " + padded_acc_num + " " + padded_amount + "   ";
    transaction_file.push_back(transaction_line);
    cout << "\nEnter a command.\n" << endl;

  } else {
      cerr << "\n>>> ERROR: The account number entered does not match the account holder name.\n" << endl;
  }
}

void Administrator::Create() {

  string padded_acc_num;
  string padded_init_balance;
  string padded_new_name;
  string new_name;
  string init_balance;

  cout << "\nCreate transaction selected.\n" << endl;
	
  cout << "Enter new account holder's name: ";
  //cin >> new_name;
  cin.ignore();
  getline(cin, new_name);

  /* If the new account holder name enter happens to be longer
   * than 20 characters, truncate it to fit the requirements
   */
  if (new_name.length() > 20) {
    new_name = new_name.substr(0, 20);
    cout << "The new account holder's name has been truncated to: " << new_name << endl;
  }

  padded_new_name = new_name;
  while (padded_new_name.length() < 20) {
    padded_new_name = padded_new_name + " ";
  }

  /* If the name does not exist (e.g. is unique) then 
   * check if it fits the format required
   */
  if (!(transactions.HolderExists(new_name)) && (new_name.compare("admin") != 0) && (transactions.is_Name_Valid(new_name))) {
    // Get the last element from vector users

    int last_acc_num;
    
    for (int i = 0; i < users.size(); i++) {
      if (i == users.size() - 3) {
        last_acc_num = users.at(i).GetNum();
      }
    }

    // Assigns the next number in the sequence
    int new_acc_num = last_acc_num + 1;

    if (new_acc_num > 99999) {
      cerr << "There are no more bank account numbers available." << endl;
      return;
    } else {
      // Prompt for initial balance and check it's valid
      // Then create new User and add it to the users vector
      cout << "Enter initial balance: ";
      cin >> init_balance;

      padded_init_balance = init_balance;
      while (padded_init_balance.length() < 8) {
        padded_init_balance = "0" + padded_init_balance;
      }

      padded_acc_num = to_string(new_acc_num);
      while (padded_acc_num.length() < 5) {
        padded_acc_num = "0" + padded_acc_num;
      }

      if (!(transactions.is_Amount_Valid(init_balance))) {
        cerr << ">>> ERROR: The initial balance entered is not valid." << endl;
        return;
      }

      User new_user;
      new_user.SetName(new_name);
      new_user.SetNum(new_acc_num);
      new_user.SetBalance(stof(init_balance));
      new_user.SetStatus('A');
      new_user.SetPlan('N');
      users.push_back(new_user);
      new_users.push_back(new_user);

      cout << "\nYou have successfully created a new account." << endl;
      cout << "Bank account number: " << padded_acc_num << endl;
      cout << "Balance: $" << padded_init_balance << endl;

      if (new_user.GetPlan() == 'S')
        cout << "Transaction payment plan: Student" << endl;
      else if (new_user.GetPlan() == 'N')
        cout << "Transaction payment plan: Non-student" << endl;
      else 
        cerr << ">>> ERROR: Could not get payment plan information." << endl;     
      
      if (new_user.GetStatus() == 'D')
        cout << "Status: Disabled" << endl;
      else if (new_user.GetStatus() == 'A')
        cout << "Status: Active" << endl;
      else
        cerr << ">>> ERROR: Could not get status information." << endl;

      cout << "\nEnter a command.\n" << endl;

      string transaction_line = "04 " + padded_new_name + " " + padded_acc_num + " " + padded_init_balance + "   \n";
      transaction_file.push_back(transaction_line);
    }
  } else {
    cerr << ">>> ERROR: The account name entered is already in use. Please pick a different one." << endl;
  }
}

void Administrator::Deleted() {

  string padded_acc_holder;
  string padded_acc_num;
  string acc_holder;
  string temp_acc_num;
  int acc_num;
  string choice;

  cout << "\nDelete transaction selected.\n" << endl;

  cout << "Enter account holder's name: ";
  cin >> acc_holder;

  if (!transactions.HolderExists(acc_holder)) {
    cerr << "\n>>> ERROR: The account holder name entered is not valid.\n" << endl;
    return;
  }

  cout << "Enter account number: ";
  cin >> temp_acc_num;

  regex re("[0-9]{0,5}");
  if (regex_match(temp_acc_num, re)) {
    acc_num = stoi(temp_acc_num);
  } else {
    cerr << "\n>>> ERROR: The account number entered is invalid.\n" << endl;
    return;
  }

  if (transactions.Matches(acc_holder, acc_num)) {

    padded_acc_holder = acc_holder;
    while (padded_acc_holder.length() < 20) {
      padded_acc_holder = padded_acc_holder + " ";
    }

    padded_acc_num = temp_acc_num;
    while (padded_acc_num.length() < 5) {
      padded_acc_num = "0" + padded_acc_num;
    }
    cout << "Are you sure you want to delete " << acc_holder << "'s account " << padded_acc_num << " (yes/no)? ";
    cin >> choice;

    if (transactions.to_Lower(choice).compare("yes") == 0) {
      for (int i = 0; i < users.size(); i++) {
        if (users.at(i).GetNum() == acc_num) {
          users.erase(users.begin() + i);
          break;
        }
      }
  
      cout << "\nThe account " << padded_acc_num << " has successfully been deleted." << endl;

      string transaction_line = "06 " + padded_acc_holder + " " + padded_acc_num + "            ";
      transaction_file.push_back(transaction_line);
      cout << "\nEnter a command.\n" << endl;
    }
    else if (transactions.to_Lower(choice).compare("no") == 0) {
      cout << "\nThe deletion of account " << acc_num << " has been aborted." << endl;
      cout << "\nEnter a command.\n" << endl;
    } else
      cerr << "\n>>> ERROR: This is not a valid input.\n" << endl;

  } else {
      cerr << "\n>>> ERROR: The account number entered does not match the account holder name.\n" << endl;
  }
}

void Administrator::Disable() {

  string padded_acc_holder;
  string padded_acc_num;
  string acc_holder;
  string temp_acc_num;
  int acc_num;

  cout << "\nDisable transaction selected.\n" << endl;

  cout << "Enter account holder's name: ";
  cin >> acc_holder;

  if (!transactions.HolderExists(acc_holder)) {
    cerr << "\n>>> ERROR: The account holder name entered is not valid.\n" << endl;
    return;
  }

  cout << "Enter account number: ";
  cin >> temp_acc_num;

  regex re("[0-9]{0,5}");
  if (regex_match(temp_acc_num, re)) {
    acc_num = stoi(temp_acc_num);
  } else {
    cerr << "\n>>> ERROR: The account number entered is invalid.\n" << endl;
    return;
  }

  if (transactions.Matches(acc_holder, acc_num)) {

    padded_acc_holder = acc_holder;
    while (padded_acc_holder.length() < 20) {
      padded_acc_holder = padded_acc_holder + " ";
    }

    padded_acc_num = temp_acc_num;
    while (padded_acc_num.length() < 5) {
      padded_acc_num = "0" + padded_acc_num;
    }

    for (int i = 0; i < users.size(); i++) {
      if (users.at(i).GetNum() == acc_num) {
        if (transactions.is_Disabled(acc_num)) {
          cerr << "\n>>> ERROR: This account is already disabled.\n" << endl;
          return;
        } else {
          users.at(i).SetStatus('D');
          break;
        }
      }
    }
    cout << "\nThe account " << padded_acc_num << " has successfully been disabled." << endl;

    string transaction_line = "07 " + padded_acc_holder + " " + padded_acc_num + "            ";
    transaction_file.push_back(transaction_line);
    cout << "\nEnter a command.\n" << endl;
    
  } else {
      cerr << "\n>>> ERROR: The account number entered does not match the account holder name.\n" << endl;
  }
}

void Administrator::Enable() {

  string padded_acc_holder;
  string padded_acc_num;
  string acc_holder;
  string temp_acc_num;
  int acc_num;

  cout << "\nEnable transaction selected.\n" << endl;

  cout << "Enter account holder's name: ";
  cin >> acc_holder;

  if (!transactions.HolderExists(acc_holder)) {
    cerr << "\n>>> ERROR: The account holder name entered is not valid.\n" << endl;
    return;
  }

  cout << "Enter account number: ";
  cin >> temp_acc_num;

  regex re("[0-9]{0,5}");
  if (regex_match(temp_acc_num, re)) {
    acc_num = stoi(temp_acc_num);
  } else {
    cerr << "\n>>> ERROR: The account number entered is invalid.\n" << endl;
    return;
  }

  if (transactions.Matches(acc_holder, acc_num)) {

    padded_acc_holder = acc_holder;
    while (padded_acc_holder.length() < 20) {
      padded_acc_holder = padded_acc_holder + " ";
    }

    padded_acc_num = temp_acc_num;
    while (padded_acc_num.length() < 5) {
      padded_acc_num = "0" + padded_acc_num;
    }

    for (int i = 0; i < users.size(); i++) {
      if (users.at(i).GetNum() == acc_num) {
        if (!transactions.is_Disabled(acc_num)) {
          cerr << "\n>>> ERROR: This account is already enabled.\n" << endl;
          return;
        } else {
          users.at(i).SetStatus('A');
          break;
        }
      }
    }
    cout << "\nThe account " << padded_acc_num << " has successfully been enabled." << endl;

    string transaction_line = "09 " + padded_acc_holder + " " + padded_acc_num + "            ";
    transaction_file.push_back(transaction_line);
    cout << "\nEnter a command.\n" << endl;
    
  } else {
      cerr << "\n>>> ERROR: The account number entered does not match the account holder name.\n" << endl;
  }
}

void Administrator::Changeplan() {

  string new_plan;
  string padded_acc_holder;
  string padded_acc_num;
  string acc_holder;
  string temp_acc_num;
  int acc_num;

  cout << "\nChange plan transaction selected.\n" << endl;

  cout << "Enter account holder's name: ";
  cin >> acc_holder;

  if (!transactions.HolderExists(acc_holder)) {
    cerr << "\n>>> ERROR: The account holder name entered is not valid.\n" << endl;
    return;
  }

  cout << "Enter account number: ";
  cin >> temp_acc_num;

  regex re("[0-9]{0,5}");
  if (regex_match(temp_acc_num, re)) {
    acc_num = stoi(temp_acc_num);
  } else {
    cerr << "\n>>> ERROR: The account number entered is invalid.\n" << endl;
    return;
  }

  if (transactions.Matches(acc_holder, acc_num)) {

    if (transactions.is_Disabled(acc_num)) {
      cerr << "\n>>> ERROR: Disabled accounts can not change transaction payment plans.\n" << endl;
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

    for (int i = 0; i < users.size(); i++) {
      if (users.at(i).GetNum() == acc_num) {
        if (users.at(i).GetPlan() == 'S') {
          users.at(i).SetPlan('N');
          new_plan = "Non-student";
        } else {
          users.at(i).SetPlan('S');
          new_plan = "Student";
        }
      }
    }

    cout << "\nThe transaction payment plan for account " << padded_acc_num << " has successfully changed to " << new_plan << "."<< endl;

    string transaction_line = "08 " + padded_acc_holder + " " + padded_acc_num + "            ";
    transaction_file.push_back(transaction_line);
    cout << "\nEnter a command.\n" << endl;
    
  } else {
      cerr << "\n>>> ERROR: The account number entered does not match the account holder name.\n" << endl;
  }
}