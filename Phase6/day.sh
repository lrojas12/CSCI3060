#remove previous transaction files
rm actual-trans-files/*

#compile front end
#g++ -std=c++11 -I/usr/local/include front_end/*.cpp -o exe
echo "Front End Compiled!"

#run front end
./exe curr.cbaf < curr-input.in
echo "Front End Execution Complete!"

#merge transaction files
cat actual-trans-files/* > merged-trans.tf

#compile back end
#javac -cp back_end/ back_end/*.java
echo "Back End Compiled!"

#run the back end
java -cp back_end/ backEnd curr.mbaf merged-trans.tf
echo "Back End Execution Comple!"
