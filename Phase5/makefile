front:
	@make -f makefile.front

back:
	@make -f makefile.back

tests: back
	@javac -cp junit-4.10.jar:. UtilitiesTest.java
	@java -cp junit-4.10.jar:. org.junit.runner.JUnitCore UtilitiesTest

clean:
	@rm *.class