#setup initial state

rm "curr.cbaf" "curr.mbaf" "curr-input.in" 2> /dev/null
rm archive/day1/* archive/day2/* archive/day3/* archive/day4/* archive/day5/* 2> /dev/null

cp init/* .

#itterate 5 days
for i in {1..5}
do
  #remove the precious days input and copy from the input folder the current
  #days input
  rm curr-input.in
  cp "input/day$i.in" "curr-input.in"

  #run the daily script
  ./day.sh

  #archive the data
  mv "curr-input.in" "curr.cbaf" "curr.mbaf" "merged-trans.tf" "archive/day$i"

  cp "new.cbaf" "curr.cbaf"
  cp "new.mbaf" "curr.mbaf"

  mv "new.cbaf" "new.mbaf" "archive/day$i"

  #wait for user input at the end of fay(for error checking)
  echo "End of Day$i"
  read
done
