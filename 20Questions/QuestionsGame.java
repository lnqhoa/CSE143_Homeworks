// This is a starter file for QuestionsGame.
//
// You should delete this comment and replace it with your class
// header comment.

import java.util.*;

public class QuestionsGame {
    private Scanner console;
    // Your code here

    // post: asks the user a question, forcing an answer of "y" or "n";
    //       returns true if the answer was yes, returns false otherwise
    public boolean yesTo(String prompt) {
        System.out.print(prompt + " (y/n)? ");
        String response = console.nextLine().trim().toLowerCase();
        while (!response.equals("y") && !response.equals("n")) {
            System.out.println("Please answer y or n.");
            System.out.print(prompt + " (y/n)? ");
            response = console.nextLine().trim().toLowerCase();
        }
        return response.equals("y");
    }

    private static class QuestionNode {
        // Your code here
    }
}
