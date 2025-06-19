public class waitnofity {
    private static boolean completed = false;

    public static void main(String[] args){
        Object lock = new Object();

        Thread t1 = new Thread(() -> {
          synchronized(lock){
              System.out.println("producing");
              completed = true;
              lock.notifyAll();
              System.out.println("after notify");
          }
        });
        Thread t3 = new Thread(() -> {
           synchronized (lock){
               while(!completed){
                   try{
                       System.out.println("consumed2 bef");
                       lock.wait();
                       Thread.sleep(1000);
                       System.out.println("consumed2 aft");

                   }
                   catch(InterruptedException e){

                   }
               }
               System.out.println("baita 2");
           }
        });
        Thread t2 = new Thread(() -> {
            synchronized(lock){
                while(!completed){
                    try {
                        System.out.println("consumed1 bef");
                        lock.wait();
                        Thread.sleep(1000);
                        System.out.println("consumed1 afte");
                    }
                    catch(InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println("baita 2");
            }
        });

        t2.start();
        t3.start();
        try{Thread.sleep(2000);} catch (InterruptedException e){};
        t1.start();
    }
}
