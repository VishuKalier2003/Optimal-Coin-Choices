/* There are n piles of coins on a table... Each pile consists of a positive number of coins of assorted denominations... In one move, you can choose any coin on top of any pile, remove it, and add it to your wallet... Given a list piles, where piles[i] is a list of integers denoting the composition of the ith pile from top to bottom, and a positive integer k, return the maximum total value of coins you can have in your wallet if you choose exactly k coins optimally...
 * Eg 1: piles = [[1,100,3],[7,8,9]]                                 k = 2   Output = 100+1 = 101
 * Eg 2: piles = [[100,100,100,100,100,100],[1,1,1,1,1,1,700]]       k = 7   Output = 700+1+1+1+1+1+1 = 706
 * Eg 3: piles = [[10,5,100],[7,50],[12,30,15,10]]                   k = 5   Output = 10+5+100+7+50 = 172 
 */
import java.util.*;
public class KCoins
{
    public int MaximizedOptimalChoice(List<List<Integer>> coins, int k)
    {
        int total = 0, sum = 0;     // Sum and the total number of coins in the piles...
        int[] dp = new int[coins.size()];    // Creating a DP Array to represent each pile...
        for(int i = 0; i < dp.length; i++)
        {
            dp[i] = coins.get(i).size() - 1;  // Updating the DP Array with the size of each pile to get the lowest coin of every pile...
            total = total + coins.get(i).size();
        }
        for(int i = 0; i < coins.size(); i++)
        {
            for(int j = 0; j < coins.get(i).size(); j++)
                sum = sum + coins.get(i).get(j);    // Summing up the value of all coins...
        }
        for(int count = 0; count < (total - k); count++)
        {   // Now we will remove n - k coins to get exactly k coins left...
            int last = 1000000, index = 0;
            for(int i = 0; i < dp.length; i++)
            {
                if((dp[i] >= 0) && (coins.get(i).get(dp[i]) < last))
                {   // If the last coin in current pile is lower...
                    index = i;     // Update the index of the pile, the current index of the DP Array will give the last element of that pile...
                    last = coins.get(i).get(dp[i]);
                }
            }
            sum = sum - coins.get(index).get(dp[index]);   // We remove that coin from the pile and reduce the sum respectively...
            dp[index]--;       // Reducing the pile...
        }
        return sum;    // Returning the evaluated sum...
    }
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        int x, k, n;
        System.out.print("Enter the number of piles of coins : ");
        x = sc.nextInt();
        List<List<Integer>> coins = new ArrayList<List<Integer>>();     // A List of Lists for Input...
        for(int i = 0; i < x; i++)
        {
            System.out.print("Enter the number of Coins in "+(i+1)+" th Pile : ");
            k = sc.nextInt();
            coins.add(i, new ArrayList<Integer>());
            for(int j = 0; j < k; j++)
            {
                System.out.print("Enter the value of "+(j+1)+" th coin in "+(i+1)+" th Pile : ");
                n = sc.nextInt();
                coins.get(i).add(j, n);
            }
        }
        System.out.print("Enter the number of Coins to choose : ");
        k = sc.nextInt();
        KCoins Kcoins = new KCoins();         // Object creation...
        System.out.println("The Maximum value of Coins : "+Kcoins.MaximizedOptimalChoice(coins, k));
        sc.close();
    }
}

// Time Complexity  - O(n^2) time...
// Space Complexity - O(n) space...

/* DEDUCTIONS :- 
 * 1. Since the piles may have different number of coins, we can use Top Down Approach with Tabulation technique and take all coins from all piles and get the sum...
 * 2. Now to have exactly k coins we need to remove exactly n - k coins from onu hand...
 * 3. We can use a pointer to mark the end of each pile which will tell that for which pile we need to return what amount of coins...
 * 4. The minimum valued coin is removed at each iteration and the index of the lowest coin in each pile is stored in the Dynamic Programming Array...
*/