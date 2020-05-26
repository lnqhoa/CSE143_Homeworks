/* Hoa N Le
   SPRING 2020 - CSE 143
   TA: Shawn Stanley
   Assignment #1 LetterInventory
   
   This program keeps track of letter inventory,
   which takes in a string and computes number of alphabet letter. 
   
   Material used: http://www.asciitable.com/ 
*/

import java.util.*;

public class LetterInventory {
   // Size of Alphabet 
   private static final int FINAL_SIZE = 26;
   // Array of each letter's count
   private int [] count; 
   // Number of letters in the string
   private int size = 0;
   
   /* Class constructor: Class takes in a string.
      Post: Count number of (case-insensitive) alphabetic letters,
            while ignoring non-alphabetic, storing in the count[] array. */
   public LetterInventory (String data) {
      count = new int [FINAL_SIZE];
      data = data.toLowerCase();
      for (int i = 0; i < data.length(); i++) {
         char c = data.charAt(i);
         if (c >= 'a' && c <= 'z') {
            count [c - 'a']++;
            this.size++;
         }
      }
   }
      
   // Post: Return sum of all counts in inventory
   public int size() {
      return this.size;
   }
      
   // Post: Return if the inventory is empty or not. 
   public boolean isEmpty() {
      return this.size == 0;
   }
   
   /* Take in a character
      Pre:  If character is nonalphabetic -> IllegalArgumentException() error.
      Post: Return the letter's count in inventory. */
   public int get (char letter) {
      letter = Character.toLowerCase(letter); 
      if (!Character.isLetter(letter)) {
         throw new IllegalArgumentException();
      }
      return count[letter - 'a'];
   }
   
   /* Take in a character and an interger value
      Pre:  If character is nonalphabetic or int value < 0 -> IllegalArgumentException() error.
      Post: Set a new value in the count[] array. */
   public void set (char letter, int value) {
      letter = Character.toLowerCase(letter);
      if (!Character.isLetter(letter) || value < 0) {
         throw new IllegalArgumentException();
      }
      this.size += value - count[letter - 'a']; // Re-update the size
      count [letter - 'a'] = value;
   }
   
   /* Post: Return a string with all (lowercased) letter in alphabetic order,
            result will be in bracket and match the number of its count. */
   public String toString() {
      String result = "[";
      for (int i = 0; i < FINAL_SIZE; i++) {
         for (int j = 0; j < count[i]; j++) {
            char letter = (char) (i + 'a');
            result += letter;
         }
      }
      return result + "]";
   }
   
   /* Take in a LetterInventory object
      Post: Return a new LetterInventory obeject with the sum counts of each letter
            from both LetterInventory objects (original "this" inventory and "other" inventory). */
   public LetterInventory add (LetterInventory other) {
      LetterInventory combine = new LetterInventory("");
      for (int i = 0; i < FINAL_SIZE; i++) {
         combine.count[i] = this.count[i] + other.count[i];
      }
      combine.size = this.size + other.size;
      return combine;
   }  
   
   /* Take in a LetterInventory object
      Post: Return a new LetterInventory obeject with the result of 
            subtract counts of each letter of "other" inventory from "this" inventory.
            Return null, if any count is negative. */
   public LetterInventory subtract(LetterInventory other) {
      LetterInventory subtract = new LetterInventory("");
      for (int i = 0; i < FINAL_SIZE; i++) {
         subtract.count[i] = this.count[i] - other.count[i];
         if (subtract.count[i] < 0) {
            return null;
         }
      }
      subtract.size = this.size - other.size;
      return subtract;
   }
}