/* Hoa N Le
   SPRING 2020 - CSE 143
   TA: Shawn Stanley
   Assignment #4 Evil Hangman   
   
   This program represents a different version of "Hangman". It delays in picking
   the final answer until there is only one possible words in its dictionary to 
   maximize number of wrong guesses. */

import java.util.*;

public class HangmanManager {
   // Tracking guesses player made. 
   private int count;
   private int length;
   // Tracking possible answers
   private Set<String> wordOptions = new TreeSet<String>();
   // Guesses that player made
   private Set<Character> guesses = new TreeSet<Character>();
   // Tracking what is displayed
   private String currentPattern;
   
   /* Constructor takes in a word collection (case-insensitive), a word's length 
      and max number of wrong guesses as parems.
      Pre: If length < 1 or max < 0 -> IllegalArgumentException() error.
      Post: Initializing game's state. */
   public HangmanManager (Collection<String> dictionary, int length, int max) {
      if (max < 0 || length < 1) {
         throw new IllegalArgumentException();
      }
      count = max; 
      this.length = length; 
      for (String word : dictionary) {
         if (word.length() == length) {
            wordOptions.add(word);
         }
      }
   }
   
   // Post: Return current set of possible answers. 
   public Set<String> words() {
      return wordOptions; 
   }
   
   // Post: Return number(s) of remaining available wrong guesses.
   public int guessLeft() {
      return count;
   }
   
   // Post: Return current sets of letters that player guessed. 
   public Set<Character> guesses() {
      return guesses;
   }
   
   /* Pre: If set of words is empty -> IllegalArgumentException() error.
      Post: Return current pattern with spaces between each letter and 
            for letters have not been guessed, display dash. */
   public String pattern() {
      if (wordOptions.isEmpty()) {
         throw new IllegalArgumentException(); 
      }
      for (int i = 0; i < length; i++) {
         if (guesses.isEmpty()) {
            currentPattern += "-";
         }
      }

      return currentPattern; 
   }
   
   /* Method takes in a character as parem. 
      Pre: If number of guesses left < 1 or set of words is empty -> IllegalStateException() error. 
           If character was guessed previously -> IllegalArgumentException() error.     
      Post: Return number of occurences of char guess in new pattern.
            Update set of possible answers and number of remaining guesses. */
   public int record (char guess) { 
      int occ = 0;
      return occ;
   }
   
   // Method to find patterns from the wordOptions
   private String findPatterns() {
      return "";
   }
   
}