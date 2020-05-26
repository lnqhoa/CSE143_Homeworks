/* Hoa N Le
   SPRING 2020 - CSE 143
   TA: Shawn Stanley
   Assignment #2 GuitarHero
   
   This program represents a 37-note implementation for Guitar.java interface.
   The notes are on the chromatic scale of 110Hz to 880Hz so that clients can
   play guitar using computers' keyboards.
   
   Materials Used:
   - https://docs.oracle.com/javase/8/docs/api/java/lang/Math.html
   - https://stackoverflow.com/questions/506105/how-can-i-check-if-a-single-character-appears-in-a-strings
   - https://docs.oracle.com/javase/7/docs/api/java/lang/String.html
*/

public class Guitar37 implements Guitar {

    // Keyboard Layout
    public static final String KEYBOARD = "q2we4r5ty7u8i9op-[=zxdcfvgbnjmk,.;/' ";
    // Size of the Keyboard
    private int SIZE = KEYBOARD.length();
    // Array representing each note' frequency
    private GuitarString[] pianoKeyboard;
    // Number of times tic() function has been called
    private int time; 

    /* Constructor: Create a string array of 37 values that scale from 110Hz to 880Hz.
       Post: Array values will conrespond to a specific frequency. */
    public Guitar37() {
      pianoKeyboard = new GuitarString[SIZE];
      for (int i = 0; i < SIZE; i++) {
         double frequency = 440 * Math.pow(2, (i - 24) / 12.0);
         pianoKeyboard[i] = new GuitarString(frequency);
      }
    }
    
    /* Take in a char representing pitch.
       Pre: If pitch is unsupported, program will ignore (don't throw error). 
       Post: If possible, play a specify note given by client (thru pitch). */
    public void playNote(int pitch) {
      int index = pitch + 24;
      if (index >= 0 && index < SIZE) {
         pianoKeyboard[index].pluck();
      }
    }
    
    /* Take in a char string supposedly representing a note.
       Post: Return whether the string exists inside the KEYBOARD. */
    public boolean hasString(char string) { 
        // If character doesn't exist in KEYBOARD, returns -1 instead of index value.
        return (KEYBOARD.indexOf(string) != -1);
    }

    /* Take in a char supposedly representing a note.
       Pre: If char is not in KEYBOARD -> IllegalArgumentException() error.
       Post: If possible, play a specify note given by client (thru a char representing the note). */
    public void pluck(char string) {
       if (!hasString(string)) {
         throw new IllegalArgumentException();
       }
       int index = KEYBOARD.indexOf(string);
       pianoKeyboard[index].pluck();
    }
    
    // Post: Return sum of numbers returned from sample methods of all notes
    public double sample() {
      double sum = 0;
      for (GuitarString note : pianoKeyboard) {
         sum += note.sample();
      }
      return sum;
    }
    
    // Post: Advance the time forward one tic
    public void tic() {
      for (GuitarString note : pianoKeyboard) {
        note.tic();
      }
      time++;
    }
    
    // Post: Return the number of times tic() has been used. 
    public int time() {
        return time;
    }    
}
