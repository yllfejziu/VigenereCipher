import java.util.Scanner;

public class Main {

    private static final String ALPHABET = "abcdefghijklmnopqrstuvwxyz";

    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);
        System.out.println("Enter the plain text: ");
        String plainText = scn.next();

        System.out.println("Enter a key: ");
        String givenKey = scn.next();

        String key = generateKey(plainText, givenKey);
        String cipherText = encrypt(plainText, key);
        String originalText = decrypt(cipherText, key);

        System.out.println();
        System.out.println("Plain text: " + plainText);
        System.out.println("Key entered: " + givenKey);

        if(!key.equals(givenKey))
            System.out.println("Generated key: " + key);

        System.out.println();
        System.out.println("Encrypted text (cipher text): " + cipherText);
        System.out.println("Decrypted text (original text): " + originalText);
    }

    private static String generateKey(String plainText, String givenKey) {

        StringBuilder key = new StringBuilder(givenKey);

        for(int i = 0; i < plainText.length(); i++){
            if(i == givenKey.length())
                i = 0;

            if(key.length() == plainText.length())
                break;

            key.append(givenKey.charAt(i));
        }
        return key.toString();
    }

    private static String encrypt(String plainText, String key) {

        plainText = plainText.toLowerCase();
        StringBuilder cipherText = new StringBuilder();

        for(int i = 0; i < plainText.length(); i++){
            int x = (ALPHABET.indexOf(plainText.charAt(i)) + ALPHABET.indexOf(key.charAt(i))) % 26;

            cipherText.append(ALPHABET.charAt(x));
        }

        return cipherText.toString();
    }

    private static String decrypt(String cipherText, String key) {

        cipherText = cipherText.toLowerCase();
        StringBuilder plainText = new StringBuilder();

        for(int i = 0; i < cipherText.length(); i++){
            int x = (ALPHABET.indexOf(cipherText.charAt(i)) - ALPHABET.indexOf(key.charAt(i)) + 26) % 26;

            plainText.append(ALPHABET.charAt(x));
        }

        return plainText.toString();
    }
}
