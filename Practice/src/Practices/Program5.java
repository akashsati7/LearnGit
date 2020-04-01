package Practices;

public class Program5 {
    public static void main(String[] args) {
        Cost cost = new Cost();
        buildString(4,5,"aabaacaba",cost);
//        scabcjpsd a edsas edsa scabcjpsd ds daedsasedsa
//            8    1   5     3       3     2      3

//       S   aabaacaba 4 5
//       SC  aabaacaba
//       cost 4+4+4+5+4+5
        System.out.println(cost.cost+" "+cost.info);
    }

    static class Cost{
        int cost;
        String info="";
    }

    static int buildString(int a, int b, String s,Cost cost) {
        /*
         * Write your code here.
         */
        cost.cost+=a;
        String sb=s.charAt(0)+"";
        cost.info += "{ tempString : " + sb + " , cost " + a + " } , ";

        for(int iter = 1;iter<s.length();) {
            String tempString = s.charAt(iter) + "";
            if (sb.contains(tempString)) {
                int  individualCost;
                while (sb.contains(tempString)) {
                    iter++;
                    if (iter >= s.length()) break;
                    tempString += s.charAt(iter) + "";
                }
                if (iter < s.length())
                    tempString = tempString.substring(0, tempString.length() - 1);
                individualCost = tempString.length() * a;
                sb += tempString;
                cost.cost += Integer.min(b, individualCost);
                cost.info += "{ tempString : " + tempString + " , cost " + Integer.min(b, individualCost) + " } , ";
            } else {
                sb += tempString;
                cost.cost += a;
                cost.info += "{ tempString : " + tempString + " , cost " + a + " } , ";
                iter++;
            }
        }

        return cost.cost;
    }

    static int buildString(int a, int b, String s) {
        // try to match substring whose length >= k will save cost
        int k = b > a ? b / a + 1 : 1;

        int cost = 0;
        int i = 0, len = s.length();
        while (i < len) {
            if (i + k > len) {
                cost += a * (len - i);
                break;
            }

            // try to match and skip as many as possible chars, i.e, k + sl
            int sl = 0;
            String ls = i > 0 ? s.substring(0, i) : "";
            while (i + k + sl <= len) {
                String ks = s.substring(i, i + k + sl);
                if (!ls.contains(ks))
                    break;
                sl++;
            }
            if (sl > 0) {
                cost += b;
                i += k + sl - 1;
                continue;
            }

            // sadly no match k chars, move to the next by one step
            cost += a;
            i++;
        }
        return cost;
    }
}
