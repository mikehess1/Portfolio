# Guess the number

import random

# Setup variables
number = random.randint(1,100)
guessed = False
guesses = 0

# Print Instructions
print("Guess the number between 1 and 100")

# Begin the game
while not(guessed):
    guess = input()
    guess = int(guess)
    guesses += 1

    if guess < number:
        print ("The correct number is higher than {0}".format(guess))

    if guess > number:
        print ("The correct number is lower than {0}".format(guess))

    if guess == number:
        print ("Yes! The number is {0}.  You made {1} guesses.".format(number,guesses))
        guessed = True
