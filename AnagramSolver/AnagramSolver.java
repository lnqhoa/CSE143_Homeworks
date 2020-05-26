/* Hoa N Le
   SPRING 2020 - CSE 143
   TA: Shawn Stanley
   Assignment #6 Anagram Solver 
   
   This program takes in an user input of a phrase and prints all anagrams 
   of that given string in a limited/unlimited number of phrases. */

import java.util.*;

public class AnagramSolver {

   // List of words in the dictionary
   private List<String> dictionary; 
   // Map of each word and its letters
   private HashMap<String, LetterInventory> dictionaryLetters;
   // Letter Inventory for the user input's text
   private LetterInventory textLetters; 
   
   /* Pre: Constructor takes in a string list representing the dictionary as parem,
           assuming the list is non-empty and does not contain duplicates.
      Post: Precompute all letter inventories in advance and store them. 
            Do not change the list in anyway. */
   public AnagramSolver (List<String> dictionary) {
      this.dictionary = dictionary;
      letterInventory(this.dictionary);
   }
   
   /* Pre: Helper method takes in a string list representing the dictionary as parem.
      Post: Precompute all letter inventories in advance and store them. */
   private void letterInventory(List<String> dictionary) {
      dictionaryLetters = new HashMap<String, LetterInventory>();
      for (String word : this.dictionary) {
         LetterInventory values = new LetterInventory(word);
         dictionaryLetters.put(word, values);
      }
   }
   
   /* Pre: Helper method takes in a user input's text as parem.
      Post: Return a string list that are listed in dictioanry and 
            can be spelled using letters from text's letters. */
   private List<String> getAnagrams (String text, int max) {
      List<String> matches  = new ArrayList<String>();
      textLetters = new LetterInventory(text);     
      for (String word : this.dictionary) {
         if (textLetters.subtract(dictionaryLetters.get(word)) != null) {
            matches.add(word);
         }  
      }
      return matches;
   }
      
   /* Method takes a text string and a max number of anagrams as parems. 
      Pre: If max < 0 --> IllegalArgumentException() error.
      Post: Print all anagrams include the max words (or unlimited if max == 0). */   
   public void print (String text, int max) {
      if (max < 0) {
         throw new IllegalArgumentException();
      }
      textLetters = new LetterInventory(text);
      List<String> matches = getAnagrams(text, max); 
      Stack<String> choices = new Stack<String>();
      printAnagrams(textLetters, max, matches, choices); 
   }
   
   /* Pre: Helper method takes in a max number of anagrams, a text's letter inventory ,
      a list of anagrams that spelt out by text's letters, and a list that stores possible anagrams.
      Post: Recursively prints out all of the anagrams. */
   private void printAnagrams(LetterInventory textLetters, int max, 
                              List<String> matches, Stack<String> choices) {
      if (textLetters.isEmpty() && (max == 0 || choices.size() <= max)) {
         System.out.println(choices);
      } else {
         for (String word : matches) {
            LetterInventory wordLetters = new LetterInventory(dictionaryLetters.get(word).toString());
            LetterInventory leftOver = textLetters.subtract(wordLetters);
            if (leftOver != null) {
               choices.push(word);
               printAnagrams(leftOver, max, matches, choices);
               choices.pop();
            }
         }
      }
   }
}