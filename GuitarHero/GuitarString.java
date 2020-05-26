/* Hoa N Le
   SPRING 2020 - CSE 143
   TA: Shawn Stanley
   Assignment #2 GuitarHero
   
   This program represents a vibrating guitar string of a given frequency.
   At the same time, it will keep track of a ring buffer. 
   
   Materials Used:
   - https://docs.oracle.com/javase/10/docs/api/java/lang/Math.html#ceil(double)
   - https://docs.oracle.com/javase/tutorial/java/nutsandbolts/arrays.html
   - https://docs.oracle.com/javase/8/docs/api/java/util/Random.html
*/

import java.util.*;

public class GuitarString {

   // Value of the Capacity
   private int intN;
   // Storing displacement values
   private Queue<Double> RingBuffer; 
   
   /* Constructor: Class takes in one double representing frequency.
      Pre:  Frequency <= 0 || desired capacity (N) < 2 -> IllegalArgumentException() error.
      Post: Create a ring buffer of desired capacity (N) (to nearest int),
      then initialize to represent a guitar string by enqueing capacity (N) as 0.  
   */
   public GuitarString (double frequency) {
      // Calculate N then round to the nearest int
      intN = (int) Math.round(StdAudio.SAMPLE_RATE / frequency);
      if (frequency <= 0 || intN < 2) {
         throw new IllegalArgumentException();
      }
      RingBuffer = new LinkedList<>();
      for (int i = 0; i < intN; i++) {
         RingBuffer.add(0.0);
      }
   }
   
   /* Constructor: Class takes in an array of doubles representing content initialization.
      Pre: If init.length < 2 elements -> IllegalArgumentException() error.
      Post: Initialize ring buffer contents to double[] init. 
      USED FOR TESTING
   */
   public GuitarString (double[] init) {
      intN = init.length;      
      if (intN < 2) {
         throw new IllegalArgumentException();
      }
      RingBuffer = new LinkedList<>();
      for (Double content : init) {
         RingBuffer.add(content);
      }
   }
   
   // Post: Replace all N items in buffer with random noise between -.5 and .5 
   public void pluck() {
      for (int i = 0; i < intN; i++) {
         Random r = new Random(); 
         RingBuffer.add(r.nextDouble() - 0.5);
         RingBuffer.remove(); 
      } 
   }
   
   /* Post: Delete the sample at front of ring buffer and add to the end of ring buffer,
            the avarage of first two samples multiplied by energy decay factor (.996) */
   public void tic() {
      // Remove first front value from ring buffer and assign to this val
      double first = RingBuffer.remove();
      // Remove second front value from ring buffer and assign to this val
      double second = RingBuffer.remove();
      
      double average = (first + second) / 0.5;
      RingBuffer.add(0.996 * average);
   }
   
   // Post: Return value at front of ring buffer.
   public double sample() {
      return RingBuffer.peek();
   }
}