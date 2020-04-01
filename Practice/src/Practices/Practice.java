package Practices;

import java.lang.annotation.Annotation;

public class Practice {
    public static void main(String[] args) {
        String s = "bbasasds";
        int l = s.length();
        for(int i=0;i<s.length();i++){
            Count c = new Count(),c1 = new Count();
            int a = check(i,i+1,s,c,s.charAt(i)).count;
            char e = '\0';
            if(i-1>=0)e=s.charAt(i-1);else if(i+1<s.length())e=s.charAt(i+1);
            int b = check(i-1,i+1,s,c1,e).count;
            l+=(a+b);
            if(c.t!=null) System.out.println(c.t);
            if(c1.t!=null) System.out.println(c1.t);
        }

        System.out.println(l);
    }

    static class Count{
        public int count;
        public String t;
    }

    public static Count check(int i,int j,String s,Count count,char e){
        if(i>=0 && j<s.length() && s.charAt(i)==s.charAt(j) && s.charAt(i)==e){
            count.count++;
            count.t = s.substring(i,j+1);
            check(i-1,j+1,s,count,e);
        }

        return count;
    }
}
