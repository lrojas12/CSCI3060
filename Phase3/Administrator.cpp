/* 
 * Administrator.cpp
 * 
 * Authors: Denesh Parthipan, Luisa Rojas, Truyen Truong
 */

#include "Administrator.h"

void Administrator::Withdrawal(int acc_num, float amount) {
  cout << "Funds have successfully been withdrawn from the account " <<
           acc_num << "." << endl;
  cout << "New balance: " << endl;
}

void Administrator::Transfer(int acc_num_f, int acc_num_t, float amount) {
  cout << "Funds have been successfully transfered from account " <<
           acc_num_f <<  " to account " << acc_num_t << "." << endl;
  cout << "New balance: " << endl;
}

void Administrator::Paybill(int acc_num, string company, float amount) {
  cout << "You have successfully paid a bill of $" << amount <<
          " from account " << acc_num << " to " << company << "." << endl;
  cout << "New balance: " << endl;
}

void Administrator::Deposit(int acc_num, float amount) {
  cout << "Funds have been successfully added to the account " <<
           acc_num << "." <<endl;
  cout << "New balance: " << endl;
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

      string transaction_line = "05 " + padded_new_name + " " + padded_acc_num + " " + padded_init_balance + "   \n";
      transaction_file.push_back(transaction_line);
    }
  } else {
    cerr << ">>> ERROR: The account name entered is already in use. Please pick a different one." << endl;
  }
}

void Administrator::Deleted() {

  cout << "BEFORE:" << endl;
  for (int i = 0; i < users.size(); i++) {
    cout << i+1 << ") " << users.at(i).GetName() << endl;
  }

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
          cout << "i: " << i << endl;
          users.erase(users.begin() + i);
          break;
        }
        break;
      }
  
      cout << "The account " << padded_acc_num << " has successfully been deleted." << endl;
  
      cout << "AFTER:" << endl;
      for (int i = 0; i < users.size(); i++) {
        cout << i+1 << ") " << users.at(i).GetName() << endl;
      }

      string transaction_line = "06 " + padded_acc_holder + " " + padded_acc_num + "            ";
      transaction_file.push_back(transaction_line);
      cout << "\nEnter a command.\n" << endl;
    }
    else if (transactions.to_Lower(choice).compare("no") == 0)
      cout << "The deletion of account " << acc_num << " has been aborted." << endl;
    else
      cerr << "\n>>> ERROR: This is not a valid input.\n" << endl;

  } else {
      cerr << "\n>>> ERROR: The account number entered does not match the account holder name.\n" << endl;
  }
}

void Administrator::Disable(int acc_num) {
  cout << "The account " << acc_num <<  " has been disabled successfully." << endl;
}

void Administrator::Enable(int acc_num) {
  cout << "The account " << acc_num <<  " has been enabled successfully." << endl;
}

void Administrator::Changeplan(int acc_num) {
  string plan = "Student";
  cout << "The transaction payment plan for account " << acc_num << 
          " has been successfully changed to " << plan << endl;
}