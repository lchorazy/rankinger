package chorazy;

import java.util.Scanner;

public class Test {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String essay = scanner.nextLine();

        while (essay.split("[.]").length < 2) {
            System.out.println("Incorrect input! Your essay must contain at least 2 sentences, please try again: ");
            essay = scanner.nextLine();
        }
        System.out.println("Your essay contains " + essay.split("[.]").length + " sentences");
    }
}
