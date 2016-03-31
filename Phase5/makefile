front:
	@make -f makefile.front

back:
	@make -f makefile.back

testMain: back
	@echo " ============== TESTING MAIN ============== "
	@javac -cp junit-4.10.jar:. MainTest.java
	@java -cp junit-4.10.jar:. org.junit.runner.JUnitCore MainTest
	@rm *.class
	@rm current_bank_accounts_file.txt
	@echo " ========================================== "

testUpdateMaster: back
	@echo " ========== TESTING UPDATEMASTER ========== "
	@javac -cp junit-4.10.jar:. UpdateMasterTest.java
	@java -cp junit-4.10.jar:. org.junit.runner.JUnitCore UpdateMasterTest
	@rm *.class
	@rm master_bank_accounts_file_test.txt
	@rm current_bank_accounts_file.txt
	@echo " ========================================== "

testUser: back
	@echo " ============== TESTING USER ============== "
	@javac -cp junit-4.10.jar:. UserTest.java
	@java -cp junit-4.10.jar:. org.junit.runner.JUnitCore UserTest
	@rm *.class
	@rm current_bank_accounts_file.txt
	@echo " ========================================== "

testUtilities: back
	@echo " ============ TESTING UTILITIES =========== "
	@javac -cp junit-4.10.jar:. UtilitiesTest.java
	@java -cp junit-4.10.jar:. org.junit.runner.JUnitCore UtilitiesTest
	@rm *.class
	@rm master_bank_accounts_file_test.txt
	@rm current_bank_accounts_file.txt
	@echo " ========================================== "

testAll: back testMain testUpdateMaster testUser testUtilities

clean:
	@rm *.class
	@rm master_bank_accounts_file_test.txt