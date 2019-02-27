import java.util.Scanner;

public class Main {

    private static final String ALPHABET = "abcdefghijklmnopqrstuvwxyz";

    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);
        System.out.println("Enter the plain text: ");
        String plainText = scn.nextLine();

        System.out.println("Enter a key: ");
        String givenKey = scn.nextLine();

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
        plainText = plainText.replaceAll("\\s","");

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
        int offset = 0;

        for(int i = 0; i < plainText.length(); i++){
            if(ALPHABET.indexOf(plainText.charAt(i)) >= 0){
                int x = (ALPHABET.indexOf(plainText.charAt(i)) + ALPHABET.indexOf(key.charAt(i - offset))) % 26;
                cipherText.append(ALPHABET.charAt(x));
            }else if(plainText.charAt(i) == ' '){
                cipherText.append(' ');
                offset++;
            }
        }

        return cipherText.toString();
    }

    private static String decrypt(String cipherText, String key) {

        cipherText = cipherText.toLowerCase();
        StringBuilder plainText = new StringBuilder();
        int offset = 0;

        for(int i = 0; i < cipherText.length(); i++){
            if(ALPHABET.indexOf(cipherText.charAt(i)) >= 0) {
                int x = (ALPHABET.indexOf(cipherText.charAt(i)) - ALPHABET.indexOf(key.charAt(i - offset)) + 26) % 26;
                plainText.append(ALPHABET.charAt(x));
            }else if(cipherText.charAt(i) == ' '){
                plainText.append(' ');
                offset++;
            }
        }

        return plainText.toString();
    }
}
