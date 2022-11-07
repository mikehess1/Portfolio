# Blocks

This Python program uses the `requests` module to make API calls to `https://mempool.space` and retrieve information about the current block height and time it was found.

The current block height is printed to CLI and a counter will start counting up from the time the current block was found. Every second, the counter will update and print to CLI. This is done with the help of the `time` and `datetime` modules.

Since the average target block time is 10 minutes, if the counter exceeds 10 minutes it will turn red and display the elapsed time in H:MM:SS format.

When a new block is found, the counter will print the total elapsed time since the last block, then the new block height on the next line. A new counter will begin at 0:00:00. Whenever a block height is found and printed to CLI, an audio sound is played to alert the user. The correct path of the sound file is found using the `os` module and the sound is played using the `playsound` module.

This Python program will run indefinitely, outputting the next block height, the time elapsed for the block to be found and a sound to indicate a new block height was found. See `Blocks.png` for a screenshot.
