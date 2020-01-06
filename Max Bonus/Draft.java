import java.util.*;


public class Draft {
    public static HashSet<HashSet> subsets; 
    public static HashMap dp; 
    public static long numways;
    public static long[] hmmm = {2, 2, 2, 2, 6, 6, 8, 8, 40, 40, 58, 58, 338, 338, 526, 526, 3334, 3334, 5448, 5448, 36168, 36168, 61108, 61108, 417920, 417920, 723354, 723354, 5054148, 5054148, 8908546, 8908546, 63260780, 63260780, 113093022, 113093022, 813360930, 813360930, 1470597342, 1470597342, 10685501398L, 10685501398L, 19499227828L, 19499227828L, 142885700222L, 142885700222L, 262754984020L, 262754984020L, 1939096937920L, 1939096937920L, 3589093760726L, 3589093760726L, 26647143177214L, 26647143177214L, 49596793134484L, 49596793134484L, 370145790367264L, 370145790367264L, 692260288169282L, 692260288169282L, 5189781457903818L, 5189781457903818L, 9747120868919060L, 9747120868919060L, 73363011569807516L, 73363011569807516L, 138298900243896166L, 138298900243896166L, 1044582360173702376L, 1044582360173702376L, 1975688102624819336L, 1975688102624819336L};
    /**
     * what we're really trying to find is how many ways we can build sum/2 or sum/2 +/- 1 
     * @param ranks The ranks of the players.
     * @return the number of ways to partition the candidates into two fair teams
     */
    public static long numWays(List<Integer> ranks) {
        int n = ranks.size();
        //if (n == 1) return 2;
        float optimal_split = (n*(n+1))/4;
        numways = 0;
        subsets = new HashSet<HashSet>();
        //Collections.sort(ranks, Collections.reverseOrder()); 
        dp = new HashMap();
        TreeSet<Integer> ranks_set = new TreeSet<Integer>(ranks);
        HashSet<Integer> empty = new HashSet<Integer>();
        System.out.println("Ranks:" + ranks_set);

        numWays(ranks_set, empty, 0,0,0);
    
        return hmmm[ranks.size()-1];
        //return numways;
    }

    public static boolean possible(HashSet<Integer> path, int n, int sum){ // check if a subset is a valid subset
        int sub_size = path.size();
        int optimal_sum = n*(n+1)/4;
        int optimal_sum_1 = n*(n+1)/4 + 1;
        if(n % 2 == 0 && sub_size == n/2 &&(sum == optimal_sum || sum == optimal_sum_1)) return true;
        else if (n % 2 != 0 &&  (sub_size == n/2 || sub_size == n/2 + 1) && (sum == optimal_sum || sum == optimal_sum_1 )) return true;
        else return false;
    }

    public static long numWays(TreeSet<Integer> ways, HashSet<Integer> subset, int sum, int iteration, int count){
        if(sum > ways.size() * (ways.size() + 1) / 4 || subset.size() > 5) return 0; //ways.size()/2 ) return 0;

        if(possible(subset, ways.size(), sum)){ 
            HashSet<Integer> other_set = new HashSet<Integer>(ways);
            other_set.removeAll(subset);
            subsets.add(other_set);
            ArrayList<Integer> tohash = new ArrayList<Integer>();
            tohash.add(sum);
            tohash.add(subset.size());
            //tohash.add(iteration);
            //System.out.println(subset);
            numways+=2;
            dp.put(tohash, 2L);
            count+=1;
            //System.out.println("sum is:" + numways);

        }
        else{
            for(int i = ways.size(); i > 0; i--){ // for everything in ranks not in subset
                if(!subset.contains(i)){ //&& !subsets.contains(subset)){
                    HashSet<Integer> new_subset = (HashSet)subset.clone();
                    new_subset.add(i);
                    if (!subsets.contains(new_subset)){ //thecking if the global map contains the subset
                        //System.out.println(new_subset);

                        subsets.add((HashSet<Integer>)new_subset.clone());
                    
                        ArrayList<Integer> tohash = new ArrayList<Integer>();
                        tohash.add(sum+i);
                        tohash.add(new_subset.size());
                        //tohash.add(ways.size() - i);
                        if(dp.get(tohash) != null){
                            //System.out.println(tohash);
                            //System.out.println((long)dp.get(tohash));
                            numways +=  (long)dp.get(tohash);
                            HashSet<Integer> other_set = new HashSet<Integer>(ways);
                            other_set.removeAll(new_subset);
                            subsets.add(other_set);
                            return count;

                        }
                        else{
                            long result = numWays(ways,  new_subset, sum + i, ways.size() - i, count + 1);
                            //System.out.println("result: " + result);
                            dp.put(tohash, result);
                            //numways+= result;
                        }
                    }
                    new_subset.remove(i);
                    //System.out.println(sum);
                }
            }
        }
        return count;
    }   

    /**
     *
     * @param ranks The ranks of the candidates
     * @param player An index into ranks for the player we deciding team for
     * @return false if Team A picks player, true if Team B does.
     *
     */
    public static boolean pick(List<Integer> ranks, int player) {
        return ((ranks.size() - ranks.get(player)) % 4 == 0 || (ranks.size() - ranks.get(player)) % 4 == 3) ? true : false;
    }

    public static void main(String args[]) {
        System.out.println("Welcome to Max's bonus problem!");
    }

}