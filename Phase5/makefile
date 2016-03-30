front:
	@make -f makefile.front

back:
	@make -f makefile.back

testMain: back
	@javac -cp junit-4.10.jar:. MainTest.java
	@java -cp junit-4.10.jar:. org.junit.runner.JUnitCore MainTest
	@rm *.class

testUpdateMaster: back
	@javac -cp junit-4.10.jar:. UpdateMasterTest.java
	@java -cp junit-4.10.jar:. org.junit.runner.JUnitCore UpdateMasterTest
	@rm *.class
	@rm master_bank_accounts_file_test.txt

testUser: back
	@javac -cp junit-4.10.jar:. UserTest.java
	@java -cp junit-4.10.jar:. org.junit.runner.JUnitCore UserTest
	@rm *.class

testUtilities: back
	@javac -cp junit-4.10.jar:. UtilitiesTest.java
	@java -cp junit-4.10.jar:. org.junit.runner.JUnitCore UtilitiesTest
	@rm *.class
	@rm master_bank_accounts_file_test.txt

testAll: back testMain testUpdateMaster testUser testUtilities

clean:
	@rm *.class
	@rm master_bank_accounts_file_test.txt