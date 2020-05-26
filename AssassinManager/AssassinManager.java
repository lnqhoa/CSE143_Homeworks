/* Hoa N Le
   SPRING 2020 - CSE 143
   TA: Shawn Stanley
   Assignment #3 Assassin Manager
   
   This program represents the game "Assassin". Each player has a particular target that
   they try to assassinate. Each player only knows who their target is but do not know who 
   is trying to assassinate them nor whom the other players are trying to assasinate. 
   
   Material Used:
   - https://docs.oracle.com/javase/7/docs/api/java/lang/String.html#equalsIgnoreCase(java.lang.String)
   - https://docs.oracle.com/javase/tutorial/java/nutsandbolts/while.html
*/

import java.util.*;

public class AssassinManager {

   // Front Node of Kill Ring
   private AssassinNode frontKillRing; 
   // Front Node of Graveyard
   private AssassinNode frontGraveyard;
   
   /* Constructor takes in a list of names but don't save list nor modify it. 
      Pre:  If list is empty -> IllegalArgumentException() error.
      Post: Build a new ListNodes that have the names in same order. */
   public AssassinManager (List<String> names) {
      if (names.isEmpty()) {
         throw new IllegalArgumentException();
      }
      AssassinNode last = new AssassinNode(names.get(names.size() - 1)); 
      frontKillRing = last;
      for (int i = names.size() - 2; i >= 0; i--) {
         String name = names.get(i);
         frontKillRing = new AssassinNode(name, frontKillRing);
      }
      last.next = frontKillRing;
   }  
   
   // Post: Print names of people in kill ring per line. 
   public void printKillRing() {
      AssassinNode player = frontKillRing; 
      String name = frontKillRing.name;
      do {
         System.out.println("    " + player.name + " is stalking " + player.next.name);
         player = player.next;
      } while (!player.name.equalsIgnoreCase(name));
   }    

   // Post: Print names of poeple in graveyard per line (sorted by most recently killed).
   public void printGraveyard() {    
      AssassinNode victim = frontGraveyard;
      while (victim != null) {      
         System.out.println("    " + victim.name + " was killed by " + victim.killer);
         victim = victim.next; 
      }
   }
   
   /* Pre: Take in a string name as parem. 
      Post: Return whether the  name is in the current kill ring (case-insensitive). */
      public boolean killRingContains (String name) {
         AssassinNode player = frontKillRing;
         String frontName = frontKillRing.name;
         do {
            if (player.name.equalsIgnoreCase(name)) {
               return true;
            }
            player = player.next;
         } while (!player.name.equalsIgnoreCase(frontName));
         return false; 
      }  

   /* Pre: Take in a string name as parem. 
      Post: Return whether the name is in the current graveyard (case-insensitive). */
   public boolean graveyardContains(String name) {
      AssassinNode victim = frontGraveyard;
      while (victim != null) {
         if (victim.name.equalsIgnoreCase(name)) {
            return true;
         }
         victim = victim.next;
      }
      return false;
   }
   
   // Post: Return true if kill ring has one person, false otherwise. 
   public boolean gameOver() {
      return (frontKillRing.next == frontKillRing); 
   }
   
   // Post: Return name of the winner or null if game is not over. 
   public String winner() {
      return frontKillRing.name; 
   }
   
   /* Pre: Take in a string name as parem. 
           If game is over (\& give name is not in kill ring) ->  IllegalStateException() error.
           If given name is not in the kill ring -> IllegalArgumentException() error.
      Post: Remove the name from kill ring list and transfer to graveyard list. 
            Links of who is killing whom stay the same (case-insensitive). */
   public void kill (String name) {
      if (gameOver()) {
       throw new IllegalStateException();
      } else if (gameOver() && !killRingContains(name)) {
         throw new IllegalStateException();
      } else if (!killRingContains(name)) {
         throw new IllegalArgumentException();
      } else {
      // Remove name from frontKillRing
         AssassinNode victim = null;
         AssassinNode current = frontKillRing;
         // If name is front of frontKillRing 
         while (!current.next.name.equalsIgnoreCase(name)) {
            current = current.next;
         }
         victim = current.next;
         current.next = victim.next;
         victim.killer = current.name;
         
         // Put name in frontGraveyard
         if (frontGraveyard != null) {
            victim.next = null;
            frontGraveyard = victim;
         } else {
            victim.next = frontGraveyard;
            frontGraveyard = victim;
         }  
      }
   }
}