package AlgoExpert;

public class Program4 {
    public static void main(String[] args) {
        System.out.println(buildString(8,9,"abcdcefghiabcefghi"));
    }

    static int buildString(int a, int b, String s) {
        /*
         * Write your code here.
         */
        Cost cost = new Cost();
        recursion(a,b,s,0,"",cost);
        return cost.cost;
    }

    static class Cost{
        int cost;
    }

    static int recursion(int a, int b, String s,int iter,String sb,Cost cost) {
        /*
         * Write your code here.
         */
        if(iter>=s.length())return 0;
        String tempString=s.charAt(iter)+"";
        if(sb.contains(tempString)){
            int totalCost,individualCost;
            while(sb.contains(tempString)){
                iter++;
                if(iter>=s.length())break;
                tempString += s.charAt(iter)+"";
            }
            tempString = tempString.substring(0,tempString.length()-1);
            individualCost = tempString.length() * a;
            totalCost = b;
            sb+=tempString;
            cost.cost += Integer.min(totalCost,individualCost);
        }else{
            sb += tempString;
            cost.cost += a;
            iter++;
        }

        return recursion(a,b,s,iter,sb,cost);
    }
}


