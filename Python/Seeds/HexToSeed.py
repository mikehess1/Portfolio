#!/bin/python3

from binascii import unhexlify
from embit import bip39

if __name__ == '__main__':
    print('\nEnter Hexadecimal: ', end='')

    # These three lines of code will convert a valid Hex to BIP39 Seed:
    #
    # byte_val = unhexlify(input())
    # mnemonic = bip39.mnemonic_from_bytes(byte_val)
    # print('BIP39 Mnemonic:', mnemonic, '\n')

    # This code does the same, but with some error handling for invalid input
    try:
        # Convert user input from hex to bytes
        byte_val = unhexlify(input())

        # Raise error if input is not 16 or 32 bytes, for 12 or 24 words
        if not (len(byte_val) == 16 or len(byte_val) == 32):
            raise ValueError('Hex string should be 32 or 64 characters')

        # Convert bytes to BIP39 mnemonic and Print result
        mnemonic = bip39.mnemonic_from_bytes(byte_val)
        print('BIP39 Mnemonic: ', mnemonic, '\n')

    except Exception as e:
        # Print error if exception occurs
        print(e, '\n')
