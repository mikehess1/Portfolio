#!/bin/python3

import os
import requests
from time import sleep, time
from datetime import timedelta
from playsound import playsound

api_url = 'https://mempool.space/api/'
block = 'block/'
hash = 'blocks/tip/hash'
height = 'blocks/tip/height'

sound = '/sound.wav'

red = '\033[31m'
wht = '\033[0m'


# Initialize environment
def initialize():
    # Get file path for sound
    global sound
    dir = os.path.dirname(os.path.realpath(__file__))
    sound = dir + sound

    # Clear terminal window
    os.system('clear')


# Returns int: Current highest block number
def getHeight():
    call = api_url + height
    return requests.get(call).json()


# Returns int: Seconds passed since last block mined
def getTimeSinceBlock():
    call = api_url + hash
    thisHash = requests.get(call).text
    call = api_url + block + thisHash
    seconds = requests.get(call).json()

    return int(time()) - seconds['timestamp']


# Accepts int Seconds, formats and prints seconds elapsed
def printTime(seconds):
    time = str(timedelta(seconds=seconds))
    output = ''
    if seconds < 600:
        output = time
    else:
        output = red + time + wht

    print('\r\033[K' + output, end='')


# Attempts to play sound or throws exception
def play(file):
    try:
        playsound(file)
    except:
        print('Failed to play ' + sound)


if __name__ == '__main__':
    # Initialize sound path and clear terminal
    initialize()

    # Get and print current block height
    thisBlock = getHeight()
    print('CURRENT BLOCK:   ' + str(thisBlock))
    play(sound)

    # Get seconds elapsed since last block
    elapsed = getTimeSinceBlock()

    while True:
        # Pause, increment 1 second and print elapsed time
        sleep(1)
        elapsed += 1
        printTime(elapsed)

        # Check for new block every 15 seconds
        if elapsed % 15 == 0:
            response = getHeight()

            # If new block is found
            if response > thisBlock:
                # Calculate and print elapsed block time
                seconds = elapsed
                elapsed = getTimeSinceBlock()
                printTime(seconds-elapsed)

                # Update and print new block height
                thisBlock = response
                print('\nNEW BLOCK MINED: ' + str(thisBlock))
                play(sound)
