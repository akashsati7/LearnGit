package Practices;

public class Check {
    public static void main(String[] args) {
        int i = 2;
        String ii = "av";
        changeValue(i);
        changeValues(ii);
        System.out.println(i);
        System.out.println(ii);

        FinalizeDemo fd = new FinalizeDemo();
        fd = null;
        System.gc();
    }
    public static void changeValue(int i){
        i++;
    }

    public static void changeValues(String s){
        s+=5;
    }
}

class FinalizeDemo{
    @Override
    protected void finalize() throws Throwable {
        System.out.println("Finalizing...");
    }
}
