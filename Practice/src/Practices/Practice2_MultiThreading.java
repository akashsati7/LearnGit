package Practices;

public class Practice2_MultiThreading {
    public static void main(String[] args) {
        Var var = new Var();
        new Producer(var);
        new Consumer(var);
    }
}

class Consumer implements Runnable{
    private Thread thread;
    private Var var;
    public Consumer(Var var){
        this.var = var;
        thread = new Thread(this,"Consumer");
        thread.start();
    }

    public void run(){
        while(true){
            var.get();
            try{Thread.sleep(2500);}catch (Exception e){}
        }
    }
}

class Producer implements Runnable{
    private Thread thread;
    private Var var;
    public Producer(Var var){
        this.var = var;
        thread = new Thread(this,"Producer");
        thread.start();
    }

    public void run(){
        int i=0;
        while(true){
            var.set(i);
            i++;
            try{Thread.sleep(2000);}catch (Exception e){}
        }
    }
}

class Var{
    int var;
    private boolean setted;
    public synchronized void set(int var){
        if(setted){
            try{wait();}catch (Exception e){}
        }
        notify();
        System.out.println("Setting "+var);
        this.var = var;
        setted = true;
    }

    public synchronized  void get(){
        if(!setted){
            try{wait();}catch (Exception e){}
        }
        notify();

        System.out.println("Getting "+var);
        setted = false;

    }
}
