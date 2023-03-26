#!/bin/sh

# Read a file in
IFS=$'\n' read -d '' -r -a manifest < /home/raynor/Desktop/cmpTest/manifest

length=${#manifest[@]}
let counter=1

for (( i = 0; i < $length; i++));
do

# Current time in seconds
comTime=$(date +%s)

for (( j = $i; j < $length; j++));
    do
        # I don't need the output of the cmp program
        # cmp ${manifest[i]} ${manifest[j]} >> /dev/null
        echo " ${manifest[i]} and ${manifest[j]} " > ./test

        # $? should be the exit status of the last run command
        # cmp man page says ret 0 if no diff, ret 1 if diff, ret 2 if crash
        # -ne 0 should be pretty obvious
        if [ $? -eq 0 ];
        then
            echo " ${manifest[i]} and ${manifest[j]} are the same \n " > ./report
        fi
done

echo "One iter down"
echo "Total progress: $counter out of ${#manifest[@]}"
counter=$(($counter + 1))
endTime=$(date +%s)
let totTime=$(($endTime - $comTime))
echo "That took $totTime seconds"
done




####        STAGE ONE COMPLETE        ####
# Will properly read a file, put it in an array called 'manifest', and print each element
# IFS=$'\n ' read -d '' -r -a manifest < /home/raynor/Desktop/cmpTest/manifest

# for (( i = 0; i < ${#manifest[@]} ; i++));
# do
# echo ${manifest[i]}
# done
