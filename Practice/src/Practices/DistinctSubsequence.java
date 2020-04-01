package Practices;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class DistinctSubsequence {
    public static void main(String[] args) {
        DistinctSubsequence ds = new DistinctSubsequence();
        String S = "rabbbit", T = "rabbit";

        S ="daacaedaceacabbaabdccdaaeaebacddadcaeaacadbceaecddecdeedcebcdacdaebccdeebcbdeaccabcecbeeaadbccbaeccbbdaeadecabbbedceaddcdeabbcdaeadcddedddcececbeeabcbecaeadddeddccbdbcdcbceabcacddbbcedebbcaccac"
        ;T = "ceadbaa";
        System.out.println(ds.numDistinct(S,T));
    }

    AtomicInteger atomicInteger = new AtomicInteger();
    Map<String,Integer> parsedMap = new HashMap();
    public int numDistinct(String s, String t) {
        findDistinct(s,t,0,0,0);
        return atomicInteger.get();
    }



    //with memoization...
    public void findDistinct(String s,String t,int tPointer,int sPointer,int matched){
        String ss = sPointer+" "+tPointer;
        boolean contains = parsedMap.containsKey(ss);
        if(contains || matched>=t.length()) {
            if(contains) atomicInteger.addAndGet(parsedMap.get(ss));else atomicInteger.addAndGet(1);
            return;
        }
        if((matched + s.substring(sPointer).length())<t.substring(tPointer).length() ||  tPointer>=t.length() || sPointer>=s.length()) {
            return;
        }
        int prev = atomicInteger.get();

        findDistinct(s,t,tPointer,sPointer+1,matched);
        if(s.charAt(sPointer)==t.charAt(tPointer)){
            matched++;
            findDistinct(s,t,tPointer+1,sPointer+1,matched);
        }
        parsedMap.put(ss,atomicInteger.get()-prev);

    }

    /*

    // without memoization...
    public void findDistinct(String s,String t,int tPointer,int sPointer,int matched){
        if(matched>=t.length()) {
            atomicInteger.addAndGet(1);
            return;
        }
        if((matched + s.substring(sPointer).length())<t.substring(tPointer).length() ||  tPointer>=t.length() || sPointer>=s.length()) {
            return;
        }

        findDistinct(s,t,tPointer,sPointer+1,matched);
        if(s.charAt(sPointer)==t.charAt(tPointer)){
            matched++;
            findDistinct(s,t,tPointer+1,sPointer+1,matched);
        }
    }*/
}
