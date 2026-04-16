public class CustomAlphabetCaesarCipher {
    private String customAlphabet;
    private int shift;

    public CustomAlphabetCaesarCipher(String alphabet, int shift) {
        this.customAlphabet = alphabet.toUpperCase();
        this.shift = shift;
    }

    public String encode(String input) {
        StringBuilder result = new StringBuilder();
        for (char c : input.toCharArray()) {
            if (Character.isLetter(c)) {
                int index = customAlphabet.indexOf(Character.toUpperCase(c));
                if (index != -1) {
                    int newIndex = (index + shift) % customAlphabet.length();
                    char shifted = customAlphabet.charAt(newIndex);
                    result.append(Character.isUpperCase(c) ? shifted : Character.toLowerCase(shifted));
                } else {
                    result.append(c); // Fallback if letter isn't in custom alphabet
                }
            } else {
                result.append(c); // Non-letters unchanged
            }
        }
        return result.toString();
    }

    public String decode(String input) {
        StringBuilder result = new StringBuilder();
        for (char c : input.toCharArray()) {
            if (Character.isLetter(c)) {
                int index = customAlphabet.indexOf(Character.toUpperCase(c));
                if (index != -1) {
                    int originalIndex = (index - shift + customAlphabet.length()) % customAlphabet.length();
                    char decoded = customAlphabet.charAt(originalIndex);
                    result.append(Character.isUpperCase(c) ? decoded : Character.toLowerCase(decoded));
                } else {
                    result.append(c);
                }
            } else {
                result.append(c);
            }
        }
        return result.toString();
    }
}
