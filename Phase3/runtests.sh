#!/bin/bash

cd test_case_files

for dir in */; do
	mkdir -- "$dir/real_output" 2>/dev/null
	mkdir -- "$dir/real_transaction" 2>/dev/null;
done

cd ..

# quit testing
for j in "quit"; do
	for k in 01a 02a 02b;do

        infile="";
        infile+="$j";
        infile+="$k";
        outfile=infile;
        outfile+=".out";
        infile+=".in";
        
		./exe current_bank_accounts_file.txt < ./test_case_files/quit/input/"$j$k.in" > ./test_case_files/quit/real_output/"$j$k.out" 2>/dev/null
        
        echo "$j$k.in"
		echo ""
		echo ""
		echo "================================================================================"
		echo ""
		echo "Test Case: $j$k"
		echo ""
		echo "================================================================================"
		echo ""
		echo "Output Difference: "
		diff ./test_case_files/quit/expected_output/"$j$k.out" ./test_case_files/quit/real_output/"$j$k.out"
		echo ""
		echo "Transaction File Difference: "
		diff ./test_case_files/quit/expected_transaction/"$j$k.tra" "transaction_file.tra"
        
        read

	done
done

# help testing
for j in "help"; do
	for k in 01a 01b 01c;do

		./exe current_bank_accounts_file.txt < ./test_case_files/help/input/"$j$k.in" > ./test_case_files/help/real_output/"$j$k.out" 2>/dev/null

		echo ""
		echo ""
		echo "================================================================================"
		echo ""
		echo "Test Case: $j$k"
		echo ""
		echo "================================================================================"
		echo ""
		echo "Output Difference: "
		diff ./test_case_files/help/expected_output/"$j$k.out" ./test_case_files/help/real_output/"$j$k.out"
		echo ""
		echo "Transaction File Difference: "
		diff ./test_case_files/help/expected_transaction/"$j$k.tra" "transaction_file.tra"
        
        read

	done
done

# login testing
for j in "login"; do
	for k in 01a 01b 01c 02a 02b 03a 04a 04b 05a 05b;do

		./exe current_bank_accounts_file.txt < ./test_case_files/login/input/"$j$k.in" > ./test_case_files/login/real_output/"$j$k.out" 2>/dev/null

		echo ""
		echo ""
		echo "================================================================================"
		echo ""
		echo "Test Case: $j$k"
		echo ""
		echo "================================================================================"
		echo ""
		echo "Output Difference: "
		diff ./test_case_files/login/expected_output/"$j$k.out" ./test_case_files/login/real_output/"$j$k.out"
		echo ""
		echo "Transaction File Difference: "
		diff ./test_case_files/login/expected_transaction/"$j$k.tra" "transaction_file.tra"

        read
    
	done
done

# logout testing
for j in "logout"; do
	for k in 01a;do

		./exe current_bank_accounts_file.txt < ./test_case_files/logout/input/"$j$k.in" > ./test_case_files/logout/real_output/"$j$k.out" 2>/dev/null

		echo ""
		echo ""
		echo "================================================================================"
		echo ""
		echo "Test Case: $j$k"
		echo ""
		echo "================================================================================"
		echo ""
		echo "Output Difference: "
		diff ./test_case_files/logout/expected_output/"$j$k.out" ./test_case_files/logout/real_output/"$j$k.out"
		echo ""
		echo "Transaction File Difference: "
		diff ./test_case_files/logout/expected_transaction/"$j$k.tra" "transaction_file.tra"
        
        read

	done
done

# create testing
for j in "create"; do
	for k in 01a 02a 03a 04a 04b 04c; do

		./exe current_bank_accounts_file.txt < ./test_case_files/create/input/"$j$k.in" > ./test_case_files/create/real_output/"$j$k.out" 2>/dev/null

		echo ""
		echo ""
		echo "================================================================================"
		echo ""
		echo "Test Case: $j$k"
		echo ""
		echo "================================================================================"
		echo ""
		echo "Output Difference: "
		diff ./test_case_files/create/expected_output/"$j$k.out" ./test_case_files/create/real_output/"$j$k.out"
		echo ""
		echo "Transaction File Difference: "
		diff ./test_case_files/create/expected_transaction/"$j$k.tra" "transaction_file.tra"
        
        read
        
	done
done

# delete testing
for j in "delete"; do
	for k in 01a 01b 02a 03a 04a 05a 06a; do
    
		./exe current_bank_accounts_file.txt < ./test_case_files/delete/input/"$j$k.in" > ./test_case_files/delete/real_output/"$j$k.out" 2>/dev/null

		echo ""
		echo ""
		echo "================================================================================"
		echo ""
		echo "Test Case: $j$k"
		echo ""
		echo "================================================================================"
		echo ""
		echo "Output Difference: "
		diff ./test_case_files/delete/expected_output/"$j$k.out" ./test_case_files/delete/real_output/"$j$k.out"
		echo ""
		echo "Transaction File Difference: "
		diff ./test_case_files/delete/expected_transaction/"$j$k.tra" "transaction_file.tra"
        
        read
        
	done
done

# disable testing
for j in "disable"; do
	for k in 01a 02a 03a 04a 05a 06a 06b 06c 06d 06e 06f;do

		./exe current_bank_accounts_file.txt < ./test_case_files/disable/input/"$j$k.in" > ./test_case_files/disable/real_output/"$j$k.out" 2>/dev/null

		echo ""
		echo ""
		echo "================================================================================"
		echo ""
		echo "Test Case: $j$k"
		echo ""
		echo "================================================================================"
		echo ""
		echo "Output Difference: "
		diff ./test_case_files/disable/expected_output/"$j$k.out" ./test_case_files/disable/real_output/"$j$k.out"
		echo ""
		echo "Transaction File Difference: "
		diff ./test_case_files/disable/expected_transaction/"$j$k.tra" "transaction_file.tra"
        
        read
        
	done
done

# enable testing
for j in "enable"; do
	for k in 01a 02a 03a 04a 05a 06a 07a 07b 07c 07d 07e;do

		./exe current_bank_accounts_file.txt < ./test_case_files/enable/input/"$j$k.in" > ./test_case_files/enable/real_output/"$j$k.out" 2>/dev/null

		echo ""
		echo ""
		echo "================================================================================"
		echo ""
		echo "Test Case: $j$k"
		echo ""
		echo "================================================================================"
		echo ""
		echo "Output Difference: "
		diff ./test_case_files/enable/expected_output/"$j$k.out" ./test_case_files/enable/real_output/"$j$k.out"
		echo ""
		echo "Transaction File Difference: "
		diff ./test_case_files/enable/expected_transaction/"$j$k.tra" "transaction_file.tra"

        read

	done
done

# changeplan testing
for j in "changeplan"; do
	for k in 01a 01b 02a 03a 04a 05a 06a;do

		./exe current_bank_accounts_file.txt < ./test_case_files/changeplan/input/"$j$k.in" > ./test_case_files/changeplan/real_output/"$j$k.out" 2>/dev/null

		echo ""
		echo ""
		echo "================================================================================"
		echo ""
		echo "Test Case: $j$k"
		echo ""
		echo "================================================================================"
		echo ""
		echo "Output Difference: "
		diff ./test_case_files/changeplan/expected_output/"$j$k.out" ./test_case_files/changeplan/real_output/"$j$k.out"
		echo ""
		echo "Transaction File Difference: "
		diff ./test_case_files/changeplan/expected_transaction/"$j$k.tra" "transaction_file.tra"

        read

	done
done

# deposit testing
for j in "deposit"; do
	for k in 01a 01b 02a 02b 03a 03b 04a 04b 05a 05b 05c 06a 06b 06c 06d 07a 07b 07c 07d 07e 07f 07g 07h;do

		./exe current_bank_accounts_file.txt < ./test_case_files/deposit/input/"$j$k.in" > ./test_case_files/deposit/real_output/"$j$k.out" 2>/dev/null

		echo ""
		echo ""
		echo "================================================================================"
		echo ""
		echo "Test Case: $j$k"
		echo ""
		echo "================================================================================"
		echo ""
		echo "Output Difference: "
		diff ./test_case_files/deposit/expected_output/"$j$k.out" ./test_case_files/deposit/real_output/"$j$k.out"
		echo ""
		echo "Transaction File Difference: "
		diff ./test_case_files/deposit/expected_transaction/"$j$k.tra" "transaction_file.tra"

        read

	done
done

# withdrawal testing
for j in "withdrawal"; do
	for k in 01a 01b 02a 03a 03b 04a 04b 05a 05b 05c 06a 06b 06c 06d 06e 06f 07a 07b 08a 08b;do

		./exe current_bank_accounts_file.txt < ./test_case_files/withdrawal/input/"$j$k.in" > ./test_case_files/withdrawal/real_output/"$j$k.out" 2>/dev/null

		echo ""
		echo ""
		echo "================================================================================"
		echo ""
		echo "Test Case: $j$k"
		echo ""
		echo "================================================================================"
		echo ""
		echo "Output Difference: "
		diff ./test_case_files/withdrawal/expected_output/"$j$k.out" ./test_case_files/withdrawal/real_output/"$j$k.out"
		echo ""
		echo "Transaction File Difference: "
		diff ./test_case_files/withdrawal/expected_transaction/"$j$k.tra" "transaction_file.tra"
        
        read

	done
done

# transfer testing
for j in "transfer"; do
	for k in 01a 01b 02a 03a 03b 04a 04b 05a 05b 05c 06a 06b 07a 07b 08a 08b 08c 09a 09b 09c 09d 09e 09f 09g 09h 10a 10b 10c 10d 10e 10f 11a;do

		./exe current_bank_accounts_file.txt < ./test_case_files/transfer/input/"$j$k.in" > ./test_case_files/transfer/real_output/"$j$k.out" 2>/dev/null

		echo ""
		echo ""
		echo "================================================================================"
		echo ""
		echo "Test Case: $j$k"
		echo ""
		echo "================================================================================"
		echo ""
		echo "Output Difference: "
		diff ./test_case_files/transfer/expected_output/"$j$k.out" ./test_case_files/transfer/real_output/"$j$k.out"
		echo ""
		echo "Transaction File Difference: "
		diff ./test_case_files/transfer/expected_transaction/"$j$k.tra" "transaction_file.tra"
        
        read

	done
done

# paybill testing
for j in "paybill"; do
	for k in 01a 01b 01c 01d 01e 01f 02a 02b 03a 03b 04a 04b 05a 05b 05c 06a 06b 07a 07b 07c 07d 07e 07f 07g 07h 08a 08b 08c 08d; do

		./exe current_bank_accounts_file.txt < ./test_case_files/paybill/input/"$j$k.in" > ./test_case_files/paybill/real_output/"$j$k.out" 2>/dev/null

		echo ""
		echo ""
		echo "================================================================================"
		echo ""
		echo "Test Case: $j$k"
		echo ""
		echo "================================================================================"
		echo ""
		echo "Output Difference: "
		diff ./test_case_files/paybill/expected_output/"$j$k.out" ./test_case_files/paybill/real_output/"$j$k.out"
		echo ""
		echo "Transaction File Difference: "
		diff ./test_case_files/paybill/expected_transaction/"$j$k.tra" "transaction_file.tra"

        read

	done
done