import java.util.Scanner;

/**
 * @ClassName TreadDemo1
 * @Description TODO
 * @Auther danni
 * @Date 2019/11/4 9:29]
 * @Version 1.0
 **/

public class ThreadDemo1{
    private static final long CONT=10_00;
    public static void main(String[] args) throws InterruptedException {
        //method1();
        //method2();
        /*while(true){
            System.out.println("我在main中打印");
            //进程会暂停运行1秒
            Thread.sleep(1000);
        }*/
        Scanner scanner=new Scanner(System.in);
        /*while (true){
        System.out.println("请输入要计算的第几项的菲波那切数列");
        int num=scanner.nextInt();
        Thread thread=new FibonacciThread(num);
        thread.start();*/

        /*while(true){
            System.out.println("请输入：");
            int num=scanner.nextInt();
            Thread thread=new FibonacciThread2(num);
            System.out.print("计算中");
            thread.start();
            while(thread.isAlive()){
                System.out.print('.');
                Thread.sleep(1000);
            }
        }*/
        serial();
        System.out.println();
        concurrent();
    }

    private static void serial() {
        long begin=System.nanoTime();
        int a=0;
        for (long i = 0; i <CONT ; i++) {
            a++;
        }
        int b=0;
        for (long i = 0; i <CONT ; i++) {
            b++;
        }
        int c=0;
        for (long i = 0; i <CONT ; i++) {
            c++;
        }
        long end=System.nanoTime();
        double time=((end-begin)*1.0/1000/1000/1000);
        System.out.printf("串行模式共花费%f秒",time);
    }
    public static class CalcThread extends Thread{
        @Override
        public void run() {
            int a=0;
            for (int i = 0; i <CONT ; i++) {
                a++;
            }
        }
    }
    private static void concurrent() throws InterruptedException {
        long begin=System.nanoTime();
        Thread thread1=new CalcThread();
        thread1.start();
        Thread thread2=new CalcThread();
        thread2.start();
        int a=0;
        for (int i = 0; i <CONT ; i++) {
            a++;
        }
        thread1.join();
        thread2.join();
        long end=System.nanoTime();
        double time=((end-begin)*1.0/1000/1000/1000);
        System.out.printf("并行模式共花费%f秒",time);
    }

    public static class MyThread extends Thread{
        @Override
        public void run() {
           while(true){
               System.out.println("我在method2中打印");
               try {
                   Thread.sleep(1000);
               } catch (InterruptedException e) {
                   e.printStackTrace();
               }
           }
        }
    }
    private static void method2() {
        Thread thread=new MyThread();
        thread.start();
    }

    private static void method1() throws InterruptedException {
        while(true){
            System.out.println("我在method1中打印");
            //进程会暂停运行1秒
            Thread.sleep(1000);
        }
    }
}

class Fibonacci{
    public static long calc(int n){
        if(n==0||n==1){
            return 1;
        }else{
            return calc(n-1)+calc(n-2);
        }
    }
}

class FibonacciThread extends Thread{
    private int n;
    FibonacciThread(int n){
        this.n=n;
    }
    @Override
    public void run() {
        long result=Fibonacci.calc(n);
        System.out.printf("第%d项斐波那契数为：%d\n",n,result);
    }
}

class FibonacciThread2 extends Thread{
    private int n;
    FibonacciThread2(int n){
        this.n=n;
    }
    @Override
    public void run() {
        long result=Fibonacci.calc(n);
        System.out.printf("%dfib(%d)\n",n,result);
    }
}
