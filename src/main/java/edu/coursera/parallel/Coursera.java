package edu.coursera.parallel;

import java.util.Random;

public class Coursera {

    public static void main(String[] args)
    {
        System.out.println("Tere Coursera");
        //System.setProperty("java.util.concurrent.ForkJoinPool.common.parallelism","3");
        double x;
        Random r = new Random();
        double[] X = new double[100000000];
        for (int i=0; i < X.length;i++) {
            //x=r.nextDouble();
            x=45678;
            X[i]=x;
        }
        //System.out.println("Summa: ");
        for ( int numRun=0; numRun<4; numRun++) {
            System.out.println("RUN:"+numRun);
            ReciprocalArraySum.seqArraySum(X);
            System.out.println("");
            ReciprocalArraySum.parArraySum(X);
            System.out.println("");
            ReciprocalArraySum.parManyTaskArraySum(X, 4);
            System.out.println("");
        }
    }
}
