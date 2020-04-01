package Practices;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class WordBreak {
    public static void main(String[] args) {
        WordBreak wb = new WordBreak();
        List<String> tracker = new ArrayList<String>();
        System.out.println(wb.wordBreak(tracker));
        System.out.println(tracker);
    }

    public boolean wordBreak(List<String> tracker) {
        return check("pineapplepenapple"
                , Arrays.asList("apple","pen","applepen","pine","pineapple"),new HashMap<String,Boolean>(),"","",tracker);
    }

    public boolean check(String s,List<String> wordDict,HashMap<String,Boolean> parsedOnes,String track,String lastAdded,List<String> tracker){
        if(s.isEmpty()) {
            tracker.add(track.substring(0,track.length()-1));
            return true;
        }
        else if (parsedOnes.containsKey(s + " " + lastAdded) && !wordDict.contains(s)) return parsedOnes.get(s+" "+lastAdded);

        boolean included = false;
        for(String wd:wordDict){
            boolean including = false;
            if(s.startsWith(wd))
                including = check(s.substring(wd.length()),wordDict,parsedOnes,track+wd+" ",wd,tracker);
            if(including && !included)included = true;
        }

        parsedOnes.put(s+" "+lastAdded,included);

        return included;
    }
}
