# Dice Rolling

import random


print("How many dice would you like to roll?")
rolls = input()
rolls = int(rolls)

i = 0

# Roll Dice
while i < rolls:
    print("You rolled a {0}".format(random.randint(1,6)))
    i += 1