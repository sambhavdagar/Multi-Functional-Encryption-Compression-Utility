# Multi-Functional-Encryption-Compression-Utility
This project implements a Multi-Functional Encryption Compression Utility in Java. It is designed as an educational toolkit showcasing algorithmic design, file I/O, bit-level output handling, and object-oriented design principles. The application operates via a central console menu that orchestrates user authentication and feature delegation.


🚀 Features
Custom Alphabet Caesar Cipher: Encodes and decodes strings using a configurable alphabet (default A-Z). It intelligently preserves character casing and leaves non-letter characters unchanged.


Huffman Text Compression: Implements Huffman coding for text file compression, mapping characters to variable-length bit strings based on frequency.


Bit-Level File I/O: Utilizes a custom BitOutputStream to buffer individual bits into 8-bit bytes before writing to a FileOutputStream for compact storage.

Password Strength Validator: Evaluates passwords using a rule-based heuristic to identify common security weaknesses.


🛠️ Technical Stack
Language: Java 

Design Pattern: Modular, object-oriented design with clear separation of concerns.

🧠 System Architecture & Algorithms
The application is structured into specific, testable modules:

CustomAlphabetCaesarCipher: Computes character indices, applies the shift modulo the alphabet length, and operates with an O(n) time complexity.


HuffmanNode & Compression: 1. Computes a frequency table from input file bytes.
2. Builds a priority queue of HuffmanNode objects keyed by frequency.
3. Combines the lowest-frequency nodes to build the Huffman tree and generates bit-string codes.


PasswordValidator: Executes rule-based checks ensuring a minimum length of 8 characters, and the presence of uppercase letters, lowercase letters, digits, and special characters.

⚠️ Limitations & Future Scope
Compression Overhead: This implementation focuses on the encoder and does not currently write header/meta-information (like the serialized tree or code lengths) to the file, which is required for a production-ready decoder.

Security: The utility demonstrates classical ciphers and should not be used in place of modern, cryptographically secure encryption (like AES or RSA) for sensitive data.
