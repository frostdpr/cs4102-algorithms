import java.util.*;

public class Cryptarithms {
    
    static boolean[] usedNums = new boolean[10];
    static HashMap<Character, Integer> mapping = new HashMap<Character, Integer>();
    static HashSet<Character> unique = new HashSet<Character>();
    /**
     * @param equation a list of strings that make up the equation, last element is the sum, all other elements are addends
     * @return a map from characters to integers representing your final solution
     */
    public static Map<Character, Integer> solve(List<String> equation) {
        if(equation == null){return null;}
        mapping.clear();
        usedNums = new boolean[10];
        unique.clear();
        //put input in a usable format
        ArrayList<ArrayList<Character>> format = new ArrayList<ArrayList<Character>>();

        for(int i = 0; i < equation.size(); i++ ){
            format.add(new ArrayList<Character>());
            String word = equation.get(i);
            ArrayList<Character> temp = new ArrayList<Character>();
            unique.add(word.charAt(0));
            for(char c : word.toCharArray()){
                temp.add(c);
            }
            format.set(i,temp);
        }


        boolean check = solve(format, 0, 0, 0, 0);
        
        return check ? mapping : null;
    }

    public static boolean solve(ArrayList<ArrayList<Character>> equation, int row, int place, int curSum, int carry){
        ArrayList<Character>  curWord = equation.get(row);
   
        if(row == equation.size() - 1 ){ //If we are currently trying to assign a char in the sum
            //String curWord = equation[row];
            if(place >= curWord.size()){ //beyond leftmost digit
                return carry == 0 ? true : false;
            }
            char curChar = curWord.get(curWord.size() - 1 -place);
            //System.out.println(curChar);
            int newCarry = curSum+carry > 9 ? ((curSum + carry)/10) : 0; //gives 10ths place digit
            int correctSum = (curSum + carry) % 10;
            //System.out.println("NEWCARRY: " + newCarry);
            if(mapping.get(curChar) != null && correctSum == mapping.get(curChar)){ //If char assigned & matches correct,
                if(solve(equation, 0, place + 1, 0, newCarry)){ //recur on next column to the left with carry, if success return true,
                    return true;
                }
            }
            else if (mapping.get(curChar) != null && correctSum != mapping.get(curChar)) { //If char assigned & doesn’t match, return false
                return false;
            }
            else if (mapping.get(curChar) == null && usedNums[correctSum] == true ) { //If char unassigned & correct digit already used, return false
                return false;
            }
            else if (mapping.get(curChar) == null && usedNums[correctSum] == false ) { //If char unassigned & correct digit unused,
                mapping.put(curChar, correctSum);
                usedNums[correctSum] = true; 
                if(solve(equation, 0, place + 1, 0, newCarry)){ //recur on next column to the left with carry, if success return true,
                    return true;
                }else{
                    mapping.put(curChar, null);
                    usedNums[correctSum] = false;
                }
            }else{return false;}

        }
        else{ //assigning char in addends

            if(curWord.size() - 1 - place >= 0){
                char curChar = curWord.get(curWord.size() - 1 - place);
                //System.out.println(curChar);

                if(mapping.get(curChar) == null){

                    for(int i = 0; i < usedNums.length; i ++){
                        if(curWord.size() -1 == place && i == 0){continue;}
                        if(unique.contains(curChar) && i == 0){continue;}
                        if(!usedNums[i]){
                            mapping.put(curChar, i);
                            usedNums[i] = true;
                            if(solve(equation, row + 1, place , curSum + i, carry)){
                                return true;
                            }
                            else{
                                mapping.put(curChar, null);
                                usedNums[i] = false;
                            }
                        }
                    }
                    return false; //no assignment worked
                }
                else{
                    return solve(equation, row + 1, place , curSum + mapping.get(curChar), carry);
                }
            }
            else{
                return solve(equation, row + 1, place , curSum, carry);

            }
        }
        return false;
    }


    /**
     * Add for compilation and incremental testing purposes
     * @param args
     */
    public static void main(String args[]) {
        System.out.println("Welcome to Cryptarithms!");
       
    }
}
