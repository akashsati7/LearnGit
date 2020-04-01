package Practices;

import java.util.*;

public class WordLadder2 {
    List<List<String>> result = new ArrayList<>();

    String beginWord, endWord;
    Map<Integer,TreeSet<Character>> indexMap = new HashMap();
    List<String> wordList;
    public static void main(String[] args) {
        WordLadder2 wl2 = new WordLadder2();
        wl2.findLadders("hit","cog", Arrays.asList("hot","dot","dog","lot","log","cog"));

    }

    public void findLadders(String beginWord, String endWord, List<String> wordList) {
        this.beginWord = beginWord;
        this.endWord = endWord;
        this.wordList = wordList;
        for(String word:wordList)
            for(int i=0;i<word.length();i++){
                if(!indexMap.containsKey(i)){
                    TreeSet<Character> charSet = new TreeSet();
                    charSet.add(word.charAt(i));
                    indexMap.put(i,charSet);
                }else{
                    indexMap.get(i).add(word.charAt(i));
                }
            }

        System.out.println(indexMap);

        List<String> li = new ArrayList<>();
        li.add(beginWord);
        laddersFinder(beginWord, li);
        System.out.println(result);
    }

    public boolean laddersFinder(String s,List<String> li){
        if(s.equals(endWord)) {
//            result.add(li);
            return true;
        }
        List<List<String>> results = new ArrayList();

        for(int i=0;i<s.length();i++){
            Set<Character> charSet = indexMap.get(i);
            char sc = s.charAt(i);
            for(Character c:charSet){
                if(sc==c)continue;
                System.out.println("B4 "+s);
                String t = s.substring(0, i) + c + s.substring(i+1);
                System.out.println("After "+t);
                if(wordList.contains(t) && !li.contains(t)) {
                    System.out.println("\t\tmatched "+t+" li "+li);
                    List<String> li2 = new ArrayList<>(li);
                    li2.add(t);
                    if(laddersFinder(t,li2)){
                        results.add(li2);
                    }
                    break;
                }
            }
        }

        System.out.println("Results "+results);
        int min = Integer.MAX_VALUE;
        List<List<String>> included = new ArrayList();
        for(List<String> liRes:results)
            if(liRes.size()<=min){
                if(liRes.size()<min){
                    included.clear();
                }
                included.add(liRes);
                min = liRes.size();
            }
        for(List<String> includedLists:included)
            result.add(includedLists);

            return false;
    }

    public boolean ifAlreadyIncluded(String t,int i){
        System.out.println("checking "+t+" i "+i+" t "+t);
        for(List<String> li:result)
            if(li.get(i).equals(t))return true;
        return false;
    }
    /*public void laddersFinder(int i,String s,List<String> li){
        if(s.equals(endWord)) {
            result.add(li);
            System.out.println(result);
            return;
        }
        if(i>=s.length())return;

        String t;
        for(Character c:allCharacters) {
            System.out.println("B4 "+s);
            t = s.substring(0, i) + c + s.substring(i+1);
            System.out.println("After "+t);
            List<String> li2 = new ArrayList<>(li);
            if(wordList.contains(t) && !t.equals(s) && !li2.contains(t)){
                System.out.println("\t\tContains "+t);
                li2.add(t);
                break;
            }
            laddersFinder(i+1,t,li2);
        }
    }*/
}
