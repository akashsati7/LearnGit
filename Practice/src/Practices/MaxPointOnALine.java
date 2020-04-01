package Practices;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MaxPointOnALine {
    public static void main(String[] args) {
        MaxPointOnALine mpl = new MaxPointOnALine();
//        int[][] points = {{1,5},{2,5},{3,5},{4,5},{4,4},{5,5},{4,4},{4,3},{4,2},{4,1},{4,0},{4,6},{5,4},{3,7},{1,8},{2,7},{3,6},{4,7},{6,6},{7,7},{8,8}};
        int[][] points = {{0,0},{1,0}};
        System.out.println(mpl.maxPoints(points));
    }

    public int maxPoints(int[][] points) {
        int maxRow = 0,maxCol = 0,minRow = 0,minCol = 0;
        Map<String,Integer> pointsMap = new HashMap();

        for(int[] point:points){
            int x = point[0],y = point[1];
            maxRow = Integer.max(maxRow,x);
            maxCol = Integer.max(maxCol,y);
            minRow = Integer.min(minRow,x);
            minCol = Integer.min(minCol,y);
            String s = y+" "+x;
            int val = 1;
            if(x==0 && y==0)val=2;
            if(!pointsMap.containsKey(s))pointsMap.put(s,val);
            else pointsMap.replace(s,pointsMap.get(s)+1);
        }

        if(pointsMap.size()==2){
            int i=0;
            int m = 0;
            for(String k:pointsMap.keySet()){
                if(pointsMap.get(k)!=1)i+=pointsMap.get(k);
            }
            m+=i;

            return m;
        }

//        System.out.println("MinRow "+minRow+" minCol "+minCol);
        if(minRow!=0 || minCol!=0){
            if(minRow!=0){
                minRow = (minRow*-1);
                maxRow+=minRow;
            }
            if(minCol!=0){
                minCol = (minCol*-1);
                maxCol+=minCol;
            }
            Map<String,Integer> mapCopy = new HashMap<>();
            for(String k:pointsMap.keySet()){
                int y = Integer.parseInt(k.split(" ")[0]),
                        x = Integer.parseInt(k.split(" ")[1]);
                y += minCol;
                x += minRow;
                mapCopy.put(y+" "+x,pointsMap.get(k));
            }
            pointsMap = mapCopy;
        }

//        System.out.println(pointsMap+" maxRow "+maxRow+" maxCol "+maxCol);

//        System.out.println(pointsList);

        Dot[][] dots = new Dot[maxCol+1][maxRow+1];
//        print(dots,maxRow,maxCol);
//        System.exit(0);
        int max = 0;

        for(int i = maxCol;i>=0;i--){
            for(int j = 0;j<=maxRow;j++){
                int a = 0,b = 0,c = 0,d = 0;
                boolean iPlusOne = i+1<=maxCol,jMinusOne = j-1>=0,jPlusOne = j+1<=maxRow;
                if(iPlusOne){
                    if(dots[i+1][j]!=null)c = dots[i+1][j].c;
                    if(jMinusOne)
                        if(dots[i+1][j-1]!=null)b= dots[i+1][j-1].b;
                }
                if(jMinusOne){
                    if(dots[i][j-1]!=null)a = dots[i][j-1].a;
                }
                if(jPlusOne && iPlusOne){
                    if(dots[i+1][j+1]!=null)d = dots[i+1][j+1].d;
                }

//                System.out.println("\t\t\t\ta "+a+" b "+b+" c "+c+" d "+d);print(dots,maxRow,maxCol);

                Dot dot = new Dot();
                if(pointsMap.containsKey(i+" "+j)){
                    dot.addVal(pointsMap.get(i+" "+j));
                }
                dot.add(a,b,c,d);
                dots[i][j] = dot;
                max = Integer.max(max,dot.getMax());
//                System.out.println(i+" "+j+" "+dot);
            }
        }
        return max;
    }

    public void print(Dot[][] dots,int maxRow,int maxCol){
        for(int i = maxCol-1;i>=0;i--) {
            String s = "",atPos = "";
            for (int j = 0; j <= maxRow; j++) {
                if(dots[i][j]!=null && dots[i][j].getMax()!=0){s += "* ,";atPos+= i+" "+j+" ,";}
                else s+= "  ,";
            }
            System.out.println(s+" atPos "+atPos);
        }
    }

    class Dot {
        int a,b,c,d;
        void add(int a,int b,int c,int d){
            this.a += a;
            this.b += b;
            this.c += c;
            this.d += d;
        }
        void addVal(int val){
            this.a+=val;
            this.b+=val;
            this.c+=val;
            this.d+=val;
        }

        int getMax(){
            return Integer.max(Integer.max(Integer.max(a,b),c),d);
        }

        public String toString(){
            return "{ a "+a+" b "+b+" c "+c+" d "+d+" },";
        }
    }
}
