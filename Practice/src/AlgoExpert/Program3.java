package AlgoExpert;

public class Program3 {
    public static void main(String[] args) {
        Cost cost = new Cost();
        buildString(4,5,"abcdcefghiabcefghi",0,"",cost);
        System.out.println(cost.cost+" "+cost.info);
    }

    static class Cost{
        int cost;
        String info="";
    }

    static int buildString(int a, int b, String s,int iter,String sb,Cost cost) {
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
            cost.info += "{ tempString : "+tempString+" , cost "+Integer.min(totalCost,individualCost)+" } , ";
        }else{
            sb += tempString;
            cost.cost += a;
            cost.info += "{ tempString : "+tempString+" , cost "+a+" } , ";
            iter++;
        }

        return buildString(a,b,s,iter,sb,cost);
    }
}
