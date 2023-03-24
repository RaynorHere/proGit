import operator
import sys
# Author: Annika McCain
# CS 354 Fall 2021 - Dr. Casey Kennington


def word_count(s):
    symbols = ''',.;:/'/"&!?*-_\n'''
    words = dict()
    # read in the file
    with open(s, mode='r', encoding='utf-8') as f:
        for line in f:
            for ele in line:
                # getting rid of the symbols
                if ele in symbols:
                    line = line.replace(ele, "")
            # breaking the line into individual words
            for word in line.split(" "):
                # removing the empty elements
                if word == "":
                    continue
                # adding the words in lowercase
                words[word.lower()] = words.get(word.lower(), 0) + 1
    # sorting into descending order
    sorted_words = sorted(words.items(), key=operator.itemgetter(1), reverse=True)
    # printing out sorted list in dictionary format
    for key, value in sorted_words:
        print(key, ":", value)


# default file
s = "docs/Gettysburg-Address.txt"
# if a file was supplied
if len(sys.argv) == 2:
    s = str(sys.argv[1])
word_count(s)
