#!/bin/python3

import os
import sys
import requests
from time import sleep, time
from datetime import timedelta
from playsound import playsound

api_url = 'https://mempool.space/api/'
block = 'block/'
hash = 'blocks/tip/hash'
height = 'blocks/tip/height'

sound = 'sound.wav'

red = '\033[31m'
wht = '\033[0m'


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


if __name__ == '__main__':
    # Clear terminal
    os.system('clear')

    # Get and print current block height
    thisBlock = getHeight()
    print('CURRENT BLOCK:   ' + str(thisBlock))
    playsound('/Users/mike/Desktop/' + sound)

    # Get current seconds since last block
    seconds = getTimeSinceBlock()

    while True:
        # Pause, increment 1 second and print
        sleep(1)
        seconds += 1
        printTime(seconds)

        # Check for new block every 15 seconds
        if seconds % 15 == 0:
            response = getHeight()

            # If new block is found
            if response > thisBlock:
                # Calculate and print elapsed block time
                elapsed = seconds
                seconds = getTimeSinceBlock()
                printTime(elapsed-seconds)

                # Update and print new block height
                thisBlock = response
                print('\nNEW BLOCK MINED: ' + str(thisBlock))
                playsound('/Users/mike/Desktop/' + sound)
