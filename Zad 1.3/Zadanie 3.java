import java.util.Scanner;

public class Solution {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String string = scanner.nextLine();
        scanner.close();
        if (string == null || string.isEmpty()) {
            System.out.println("");
            return;
        }
        StringBuilder stringBuilder = new StringBuilder();
        int charCount = 1;
        for (int i = 1; i < string.length(); i++) {
            if(string.charAt(i - 1) == string.charAt(i)) {
                charCount++;
            } else {
                stringBuilder.append(string.charAt(i - 1));
                stringBuilder.append(charCount);
                charCount = 1;
            }
        }
        stringBuilder.append(string.charAt(string.length() - 1));
        stringBuilder.append(charCount);
        String serializedString = stringBuilder.toString();
        if(string.length() > serializedString.length()){
            System.out.println(serializedString);
            return;
        }
        System.out.println(string);
    }
}