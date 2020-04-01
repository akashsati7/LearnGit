package AlgoExpert;

import java.util.ArrayList;
import java.util.List;

public class Solution {
    static String path;

    public static void main(String[] args) {
//        System.out.println(numDistinct("rabbbiit","rabbit",0,0));
        Solution sol = new Solution();
        System.out.println(sol.numDistinct("adbdadeecadeadeccaeaabdabdbcdabddddabcaaadbabaaedee"
                ,"bcdd"));
    }

    class Tracker{
        public char c;
        public Tracker(char c){
            this.c = c;
        }
        public List<Integer> getAvailableIndexes(){
            return availableIndexes;
        }

        public List<Integer> getGreaterIndexes(List<Integer> indexesList,List<Integer> result){
            List<Integer> indexes = new ArrayList<>();
            result.clear();
            for(int from:indexesList) {
                for (int i = 0; i < availableIndexes.size(); i++) {
                    int val = availableIndexes.get(i);
                    if (val > from) {
                        indexes.add(val);
                        result.add(1);
                    }
                }
            }
            return indexes;
        }

        private List<Integer> availableIndexes = new ArrayList();
    }

    public int numDistinct(String s, String t){
        if(t.length()==1){
            int count=0;
            for(int i=0;i<s.length();i++)
                if(s.charAt(i)==t.charAt(0))
                    count++;
            return count;
        }

        List<Tracker> trackers = new ArrayList<>(t.length());

        for(int i=0;i<t.length();i++){
            char tt=t.charAt(i);
                Tracker tracker = new Tracker(tt);
                trackers.add(tracker);
        }

        for(Tracker tracker:trackers){
            char c = tracker.c;
            for(int i=0;i<s.length();i++){
                if(c==s.charAt(i))tracker.getAvailableIndexes().add(i);
            }
        }


        int a,count=0;
        for(int i=0;i<trackers.get(0).getAvailableIndexes().size();i++){
            a = trackers.get(0).getAvailableIndexes().get(i);
            List<Integer> indexes = new ArrayList<>();
            indexes.add(a);
            path = indexes.toString()+" ";
            List<Integer> res = recursion(new ArrayList<>(),indexes,1,trackers);
            if(res.size()!=0)count+=res.size();

            System.out.println(a+" "+path+" Res \t\t"+res);

        }
        return count;
    }

    private List<Integer> recursion(List<Integer> result,List<Integer> indexesList,int index,List<Tracker> trackers){
        if(trackers.size()==index)return result;
        List<Integer> indexes = trackers.get(index).getGreaterIndexes(indexesList,result);
        if(indexes.size()==0){return result;}

        path+=indexes.toString()+" ";

        return recursion(result,indexes,index+1,trackers);
    }
}