# Hangman Game

import random

#Setup list of words, randomly choose one
wordArray = ["apple", "bicycle", "cards", "dogs", "element"]
word = wordArray[random.randint(0,4)]

#Create empty puzzle string, assign dashes for each letter and print
puzzle = ""
for x in word:
    puzzle = puzzle + "-"

print("\nThe Puzzle is: \n")
print(puzzle)

#Define variable to track number of incorrect guesses
misses = 0

#Function passes user input and checks validity
#def checkInput(userInput):

#Function passes user guess and returns new string with correctly guessed letters
def checkPuzzle(char, puzzle):
    i = 0
    newpuzzle = ""
    for x in word:
        if x == char:
            newpuzzle = newpuzzle + char
        elif puzzle[i] != "-":
            newpuzzle = newpuzzle + puzzle[i]
        else:
            newpuzzle = newpuzzle + "-"
        i += 1
    
    return newpuzzle

#Function passes number of guesses and prints out appropriate result
def printPenalty():
    print ("\nIncorrect Guess")
    if misses == 1:
        print("You got a head!")
    if misses == 2:
        print("You got a body!")
    if misses == 3:
        print ("You got a leg!")
    if misses == 4:
        print ("You got another leg!")
    if misses == 5:
        print ("You got an arm! One guess left!")
    if misses == 6:
        print ("You got another arm! Hangman, you lose!\n")

#Begin the game
unresolved = True
while unresolved:
    if not(puzzle.isalpha()):
        letter = input("\nGuess a letter: ")

        if not(letter.isalpha()):
            print("No numbers, spaces or special characters.")
            print("Please enter only one letter:")
        elif len(letter) != 1:
            print("Please enter only one letter:")
        else:
            if word.find(letter) < 0:
                misses += 1
                printPenalty()
                if misses == 6:
                    unresolved = False
            else:
                puzzle = checkPuzzle(letter, puzzle)
                print("\n{0}".format(puzzle))
    else:
        unresolved = False
        print("You solved the puzzle!")