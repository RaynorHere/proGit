#!/bin/sh

# Read a file in; manifest being the array name
IFS=$'\n' read -d '' -r -a manifest < [SOURCEPATH]

# Timestamp at the very top of the execution
absBegin=$(date +%s)

# BASH arr len meth: ${#array_name[@]}
length=${#manifest[@]}

# Non-contiguous variable decl/assign
let counter=1

for (( i = 0; i < $length; i++));
do

    # Current time in seconds, beginning of iteration
    comTime=$(date +%s)

    # Once every item compared, no need to check again; can work 'down' list
    # Therefore, j can always be one step ahead of i
    for (( j=$(($i + 1)); j < $length; j++));
        do
            # I don't need the output of the cmp program
            cmp ${manifest[i]} ${manifest[j]} >> /dev/null
            # echo " ${manifest[i]} and ${manifest[j]} \n" >> ./test

            # $? should be the exit status of the last run command
            # cmp man page says ret 0 if no diff, ret 1 if diff, ret 2 if crash
            # -eq 0 should be pretty obvious
            if [ $? -eq 0 ];
            then
                # Log any files that return nonzero codes from cmp
                echo " ${manifest[i]} and ${manifest[j]} are the same \n " > ./report
            fi
    done

    # Notify
    echo "Total progress: $counter out of ${#manifest[@]}"
    counter=$(($counter + 1))

    # Time spent on iteration
    endTime=$(date +%s)
    let totTime=$(($endTime - $comTime))
    echo "That took $totTime seconds"
done

# Total program time elapsed
absEnd=$(date +%s)
let absTotal=$(($absEnd - $absBegin))
echo "Overall, $absTotal seconds."
