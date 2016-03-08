/* 
 * TransactionHelper.cpp
 * 
 * Authors: Denesh Parthipan, Luisa Rojas, Truyen Truong
 */

#include "TransactionHelper.h"
#include "User.h"

/* To compare user input - should accept variations
 * (e.g. "Standard", "standard", "STANDARD", etc)
 */
string TransactionHelper::to_Lower(string str) {

  for (int i = 0; i < str.length(); i++) {
    str[i] = tolower(str[i]);
  }
  return str;
}

// Check if the curret user is an administrator
bool TransactionHelper::is_Admin() {
  if (mode.compare("admin") == 0)
    return true;
  return false;
}

// Check if the account holder name inputed exists in the "database"
bool TransactionHelper::HolderExists(string name) {

  bool ret = false;

  for (int i = 0; i < users.size(); i++) {
    if (((users.at(i).GetName()).compare(name)) == 0) {			
      ret = true;
      break;
    }
  }
  return ret;
}

/* Check that the new account holder name entered follows the
 * required constraints: non-empty.
 */
bool TransactionHelper::is_Name_Valid(string name) {

	if (!name.empty())
    return true;
  else
    return false;
}

// Checks that both arguments correspond to eachother
bool TransactionHelper::Matches(string name, int acc_num) {

  bool ret = false;

  for (int i = 0; i < users.size(); i++) {
    if (((users.at(i).GetName()).compare(name)) == 0) {     
      if (users.at(i).GetNum() == acc_num) {
        ret = true;
        break;
      }
      break;
    }
  }
  return ret;
}

/* Checks that the amound of funds entered is valid
 * (e.g. formatting). Done using regex.
 */
bool TransactionHelper::is_Amount_Valid(string amount) {
  
  //Formats to be accepted: 5.50, 0.50, .50. Decimals are mandatory.
  regex re("[0-9]{0,5}\\.[0-9]{2}");

  if (regex_match(amount, re))
    return true;
  else
    return false;
}

// Checks if the account provided is active or disabled
bool TransactionHelper::is_Disabled(int acc_num) {
  
  bool ret = false;

  for (int i = 0; i < users.size(); i++) {
    if (((users.at(i).GetNum()) == acc_num)) {
      string str;    
      if (users.at(i).GetStatus() == 'D') {
        ret = true;
        break;
      }
      break;
    }
  }
  return ret;
}

/* Checks if the user's transaction payment plan
 * (student or non-student)
 */
bool TransactionHelper::is_Student(int acc_num) {
  
  bool ret = false;

  for (int i = 0; i < users.size(); i++) {
    if (((users.at(i).GetNum()) == acc_num)) {  
      string str;   
      if (users.at(i).GetPlan() == 'S') {
        ret = true;
        break;
      }
      break;
    }
  }
  return ret;
}

// Outputs the transaction_file vector into a file
void TransactionHelper::WriteTransactionFile() {
  
  string file_name = "transaction_file.txt";

  ofstream outfile(file_name);

  if (outfile.is_open()) {
    for (int i = 0; i < transaction_file.size(); i++) {
      outfile << transaction_file.at(i);
    }
    outfile.close();
  } else {
    cerr << ">>> ERROR: Unable to open transaction file" << endl;
  }
}

/* Loads all accounts' information from the
 * current_bank_accounts_file.txt file (provided by the back end)
 */
void TransactionHelper::LoadAccounts() {

  string file_name = "current_bank_accounts_file.txt";

  ifstream infile(file_name);

  string acc_holder;
  char acc_status, acc_plan;
  int acc_num;
  float acc_balance;

  if (infile) {
    while (true) {

      infile >> acc_num >> acc_holder >> acc_status >> acc_balance >> acc_plan;
      User u;

      if(infile.eof())
        break;
      //	User u(acc_holder, acc_num, acc_balance, acc_status, acc_plan);
      u.SetName(acc_holder);
      u.SetNum(acc_num);
      u.SetBalance(acc_balance);
      u.SetStatus(acc_status);
      u.SetPlan(acc_plan);
      users.push_back(u);
    }

    infile.close();
  } else {
    cerr << "ERROR: File \"" << file_name << "\" was not found." << endl;
  exit(-1);
  }

  /*
  for (int i = 0; i < users.size(); i++) {
    cout << users.at(i).GetName() << " " <<users.at(i).GetNum() << " " << users.at(i).GetBalance() <<endl;
  }
  */
}

void TransactionHelper::PrintWelcomeMessage() {

cout << " __    __      _                           _ " << endl;           
cout << "/ / /\\ \\ \\__ _| |_ ___ _ __ _ __ ___   ___| | ___  _ __ " << endl;  
cout << "\\ \\/  \\/ / _` | __/ _ \\ '__| '_ ` _ \\ / _ \\ |/ _ \\| '_ \\" << endl;  
cout << " \\  /\\  / (_| | ||  __/ |  | | | | | |  __/ | (_) | | | |" << endl;  
cout << "  \\/  \\/ \\__,_|\\__\\___|_|  |_| |_| |_|\\___|_|\\___/|_| |_|" << endl;  
                                                              
  cout << "\nWelcome to Watermelon Banking System" << endl;
  cout << "Please log in to begin or type in \"help\" for more information.\n" << endl;
}

// Allows the users to log in as a STANDARD user
void TransactionHelper::Login() {

  if (!is_logged) {
    cout << "\nEnter mode in which you wish to log in as: ";
    cin >> mode;

    if (transactions.to_Lower(mode).compare("admin") == 0) {
      mode = "admin";
      is_logged = true;
      cout << "\nYou are currently logged in as an administrator." << endl;
      cout << "\nEnter a command.\n" << endl;
    } else if (transactions.to_Lower(mode).compare("standard") == 0) {
      mode = "standard";

      // Save the account holder's name
      cout << "Enter account holder's name: ";
      cin.ignore();
      getline(cin, acc_holder);

      /* If the name stored is found in the users vector, then
       * update the curr_user User object with the new curr_user
       * information for future transactions
       */
      if (HolderExists(acc_holder) && acc_holder != "END_OF_FILE") {

        is_logged = true;

        for (int i = 0; i < users.size(); i++)
          if (users.at(i).GetName().compare(acc_holder) == 0) {
            curr_user = users.at(i);
          }

        // Output the accounts' information in a user-friendly/readable way
        cout << "\nYou are currently logged in as " << curr_user.GetName() << "." << endl;
        cout << "Bank account number: " << curr_user.GetNum() << endl;
        cout << "Balance: " << curr_user.GetBalance() << endl;
        if (curr_user.GetPlan() == 'S')
          cout << "Transaction payment plan: Student" << endl;
        else if (curr_user.GetPlan() == 'N')
          cout << "Transaction payment plan: Non-student" << endl;
        else 
          cerr << "ERROR: Could not get payment plan information." << endl;     
        
        if (curr_user.GetStatus() == 'D')
          cout << "Status: Disabled" << endl;
        else if (curr_user.GetStatus() == 'A')
          cout << "Status: Active" << endl;
        else
          cerr << ">>> ERROR: Could not get status information." << endl;

        cout << "\nEnter a command." << endl;  

      } else {
        // The name is not found in the "database"
        cerr << ">>> ERROR: The account holder entered is invalid." << endl;
      }
    } else {
      cerr << ">>> ERROR: Invalid account mode." << endl;
    }
  } else {
    cerr << ">>> ERROR: There is a session running. Please log out and try again." << endl;
  }
}

// Logs user out from either account - administrator or standard
void TransactionHelper::Logout() {
  if (is_logged) {
    cout << "\nYou have successfully logged out of your account.\n\nLogin or enter \"help\" for more information.\n" << endl;

    // Resets all variables for next user
    is_logged = false;
    mode = "";
    acc_holder = "";

    WriteTransactionFile();

  } else {
    // There is no running session to be logged out of
    cerr << "ERROR: You are not currently logged into an account." << endl;
  }
}

void TransactionHelper::PrintHelp() {

  string file_name = "help.txt";
  string line;

  ifstream infile(file_name);

  if (infile) {
    while (getline (infile,line)) {
      cout << line << endl;
    }
    infile.close();
  } else {
    cerr << "ERROR: File \"" << file_name << "\" was not found." << endl;
    exit(-1);
  }
}