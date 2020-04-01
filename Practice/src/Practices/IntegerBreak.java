package Practices;

import java.util.Arrays;

public class IntegerBreak {
    public static void main(String[] args) {
        IntegerBreak ib = new IntegerBreak();
        System.out.println(ib.integerBreak(10));
    }

    public int integerBreak(int n) {
        int[] dp = new int[n+1];
        dp[1] = 1;
        dp[2] = 2;
        for(int i=3;i<=n;i++){
            for(int j=1,k=i-j;j<=k;j++,k--){
                int jval = Integer.max(dp[j],j),kval = Integer.max(dp[k],k);
                dp[i] = Integer.max(dp[i],jval*kval);
            }
        }

        return dp[n];
    }
}
