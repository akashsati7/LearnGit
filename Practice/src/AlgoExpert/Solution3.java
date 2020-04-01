package AlgoExpert;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;


public class Solution3 {
    public static void main(String[] args) {
        int[] arr = {1,1};
        int[] res = largestRange(arr);
        System.out.println(res[0]);
    }

    public static int[] largestRange(int[] array) {
        // Write your code here.
        List<Integer> arr =  IntStream.of(array)	  // returns IntStream
                .boxed()
                .collect(Collectors.toList());
        Collections.sort(arr);
        List<Integer> start = new ArrayList(),
                end = new ArrayList();
        for(int e:arr){
            int index = end.indexOf((e-1));
            if(index!=-1){
//                System.out.print("At e "+end.get(index));
                end.set(index,e);
//                System.out.println("\t setting e "+e+" at "+index);
            }else{
                start.add(e);
                end.add(e);
            }
        }

        System.out.println(start+" "+end);

        int max=0,index=-1;
        for(int i=0;i<start.size();i++){
            int e = end.get(i),s = start.get(i),diff = e-s;
            if(diff>max){
                max = diff;
                index = i;
            }
        }

        if(index!=-1)
        return new int[]{start.get(index),end.get(index)};
        else return new int[]{start.get(0),end.get(0)};
    }
}
