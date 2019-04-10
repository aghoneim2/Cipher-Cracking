# Cipher-Cracking

Two simple programs which demonstrate how to programmatically crack a caesar cipher through brute force and a monoalphabetic cipher through character frequency analysis.

CrackCaesar uses brute force to go through the 26 possibilities of different letter orderings in order to break a caesar cipher, given a cipher text as well as a sample dictionary of the most popular 10,000 words in the English language, pulled from Google.

CrackCipher attempts to crack a monoalphabetic cipher by creating a frequency analysis of the characters of a cipher as well as a known version of some text. For simplicity, it is assumed that the frequency of characters in the known text will exactly correspond to the frequency of characters in the cipher text. 

For both the caesar cipher and the monoalphabetic cipher, character cases are ignored, and the output does not include any upper case letters.
