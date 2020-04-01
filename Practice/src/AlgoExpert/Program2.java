package AlgoExpert;

import java.util.ArrayList;
import java.util.List;

public class Program2 {
    public static void main(String[] args) {
        String[][] calendar = {{"09:00","10:30"},{"12:00","13:00"},{"16:00","18:00"}};
        String[] startAndEndTime = {"09:00","20:00"};
        String[][] calendar2  = {{"10:00","11:30"},{"12:30","14:30"},{"14:30","15:00"},{"16:00","17:00"}};
        String[] startAndEndTime2 = {"10:00","18:30"};
        Program2 program2 = new Program2();
        int duration = 30;

        int[] startAndEndTimeInInt = new int[2],startAndEndTimeInInt2 = new int[2];
        for(int i=0;i<startAndEndTime.length;i++)
            startAndEndTimeInInt[i] = program2.getInMinutes(startAndEndTime[i]);

        for(int i=0;i<startAndEndTime2.length;i++)
            startAndEndTimeInInt2[i] = program2.getInMinutes(startAndEndTime2[i]);

        System.out.println("StartAndEndTimes ");
        for(int sAndE:startAndEndTimeInInt)
            System.out.println(sAndE);

        System.out.println();
        for(int sAndE:startAndEndTimeInInt2)
            System.out.println(sAndE);


        List<int[]> calInInts = new ArrayList(),calInInts2 = new ArrayList();
        program2.addIntoList(calInInts,calendar,program2);
        program2.addIntoList(calInInts2,calendar2,program2);

        for(int[] sAndE:calInInts)
            System.out.println("<"+sAndE[0]+" "+sAndE[1]+">");
        System.out.println();
        for(int[] sAndE:calInInts2)
            System.out.println("<"+sAndE[0]+" "+sAndE[1]+">");

        List<int[]> freeTime = program2.getFreeTime(calInInts,startAndEndTimeInInt),
                freeTime2 = program2.getFreeTime(calInInts2,startAndEndTimeInInt2);

        for(int[] ft:freeTime)
            System.out.println(program2.revertBackIntoTime(ft[0])+","+program2.revertBackIntoTime(ft[1]));

        System.out.println();

        for(int[] ft:freeTime2)
            System.out.println(program2.revertBackIntoTime(ft[0])+","+program2.revertBackIntoTime(ft[1]));


        System.out.println("Printing common FreeTime...");
        List<int[]> commonFreeTime = program2.getCommonFreeTime(freeTime,freeTime2,duration);
        for(int[] cft:commonFreeTime)
            System.out.println(program2.revertBackIntoTime(cft[0])+","+program2.revertBackIntoTime(cft[1]));

    }

    public Object[] check(int[] t,int[] t2,int duration){

        int s = Integer.max(t[0],t2[0]),e = Integer.min(t[1],t2[1]);

        System.out.println( "\t\t i.e. "+revertBackIntoTime(s)+" to "+revertBackIntoTime(e)+" from "
                +" t "+revertBackIntoTime(t[0])+" to "+revertBackIntoTime(t[1]) + " and t2 "
                +revertBackIntoTime(t2[0])+" to "+revertBackIntoTime(t2[1]));

        if(e - s>=duration){
            System.out.println("Returning true...for "+revertBackIntoTime(s)+" "+revertBackIntoTime(e));
            return new Object[]{true,new int[]{s,e}};
        }

        return new Object[]{false};
    }

    public List<int[]> getCommonFreeTime(List<int[]> freeTime,List<int[]> freeTime2,int duration){

        List<int[]> cft = new ArrayList<>();
        int reached = 0;
        for(int[] ft:freeTime){
            System.out.println("new Ft ");
            for(int j=reached;j<freeTime2.size();j++){
                int[] ft2 = freeTime2.get(j);
                Object[] res = check(ft,ft2,duration);
                if((boolean)res[0]) {
                    cft.add((int[]) res[1]);
                    reached = j;
                }else if(ft2[1]<=ft[0])reached = j;
            }
        }
        System.out.println("Returning "+cft);

        return cft;
    }

    public List<int[]> getFreeTime(List<int[]> meetingTimes,int[] startAndEndTime){
        int start = startAndEndTime[0],end = startAndEndTime[1];
        List<int[]> freeTimes = new ArrayList();

        System.out.println("\t\tss startAndEnd Time "+start+" end "+end);
        for(int[] mt: meetingTimes){
            int[] ft = new int[2];
            System.out.print("\t\t\tss For mt "+mt[0]+" to "+mt[1]+" and start "+start+" and end "+end+" ");
            ft[0]=start;ft[1]= start+(mt[0]-start);
            start = mt[1];

            System.out.println(" Free Time is "+ft[0]+" to "+ft[1]);
            freeTimes.add(ft);
        }
        int[] ft = new int[2];
        ft[0] = start;ft[1] = start+(end - start);
        freeTimes.add(ft);

        System.out.println("At Last ");
        for(int[] ftt:freeTimes){
            System.out.print(ftt[0]+" "+ftt[1]+"\t\t\t");
        }

        return freeTimes;
    }

    public String revertBackIntoTime(int mins){
        int hr = mins/60;mins = mins%60;

        return hr+":"+mins;
    }

    public List<int[]> addIntoList(List<int[]> calInInts,String[][] calendar,Program2 program2){
        for(String[] sAndETime : calendar){
            int[] sAndE = {program2.getInMinutes(sAndETime[0]),program2.getInMinutes(sAndETime[1])};
            calInInts.add(sAndE);
        }
        return calInInts;
    }


    public int getInMinutes(String time){
        String[] splits = time.split(":");
        return Integer.parseInt(splits[0]) * 60 + Integer.parseInt(splits[1]);
    }
}
