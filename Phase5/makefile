front:
	@make -f makefile.front

back:
	@make -f makefile.back

testUtilities: back
	@javac -cp junit-4.10.jar:. UtilitiesTest.java
	@java -cp junit-4.10.jar:. org.junit.runner.JUnitCore UtilitiesTest

testMain: back
	@javac -cp junit-4.10.jar:. MainTest.java
	@java -cp junit-4.10.jar:. org.junit.runner.JUnitCore MainTest

testUser: back
	@javac -cp junit-4.10.jar:. UserTest.java
	@java -cp junit-4.10.jar:. org.junit.runner.JUnitCore UserTest

testUpdateMaster: back
	@javac -cp junit-4.10.jar:. UpdateMasterTest.java
	@java -cp junit-4.10.jar:. org.junit.runner.JUnitCore UpdateMasterTest

clean:
	@rm *.class