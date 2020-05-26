/* Hoa N Le
   SPRING 2020 - CSE 143
   TA: Shawn Stanley
   Assignment #5 Grammar Solver
   
   This program analyzes a BNF grammar text file and 
   generates random + valid sentences given a set of rules. 
      
   Material Used:
      - https://docs.oracle.com/javase/7/docs/api/java/lang/String.html
      - https://docs.oracle.com/javase/8/docs/api/java/util/List.html
      - https://docs.oracle.com/javase/8/docs/api/java/util/TreeMap.html
      - https://docs.oracle.com/javase/8/docs/api/java/util/Random.html
*/

import java.util.*;

public class GrammarSolver {
      
      // List of non-terminals and terminals
      private SortedMap<String, String[]> gRules;
      
      /* Constructor takes in a list of BNF grammar rules but will not modify it. 
         Pre:  If list is empty or 2+ entries in the grammar for the same non-terminal
               --> IllegalArgumentException() error.
         Post: Break apart rules and store into a SortedMap (K = Non-terminals; V = Terminals). */
      public GrammarSolver (List<String> rules) {
         if (rules.isEmpty()) {
            throw new IllegalArgumentException();
         }
         gRules =  new TreeMap<String, String[]>();
         for (String bnf : rules) {
            // Splitting strings
            String[] grammar = bnf.split("::=");
            String nonTR = grammar[0];
            if (gRules.containsKey(nonTR)) {
               throw new IllegalArgumentException();
            }
            String[] terminal = grammar[1].trim().split("\\|");
            gRules.put(nonTR, terminal);
         }
      }
      
      /* Pre: Take in a symbol string as parem.
         Post: If symbol is non-terminal --> true. Otherwise --> false. */
      public boolean grammarContains(String symbol) {
         return gRules.containsKey(symbol);
      }
      
      // Post: Return a represenation of the various non-terminal symbols.
      public String getSymbols() {
         return gRules.keySet().toString();
      }
      
      /* Pre: Take in a symbol string and an integer as parem.
              If times < 0 || symbol is not non-terminal --> IllegalArgumentException() error.
         Post: Return times random occurences of the given symbom in String[].
               There should be one space between each teriminal and no leading or trailing spaces. */
      public String[] generate (String symbol, int times)  {
         if (times < 0 || grammarContains(symbol) == false) {
            throw new IllegalArgumentException();
         } 
         String[] newString = new String[times];
         for (int i = 0; i < times; i++) {
            newString[i] = generateString(symbol).trim();
         }
         return newString;
      }        
      
      private String generateString (String symbol) {
         Random r = new Random();
         if (grammarContains(symbol) == false) {
            return symbol;
         } else {
            String result = "";
            String[] terminal = gRules.get(symbol);
            String[] random = terminal[(r.nextInt(terminal.length))].split("\\s+");
            for (String s : random) {
               result += generateString(s) + " ";
            }
            return result;
         }
      }
} 