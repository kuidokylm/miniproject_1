package edu.coursera.parallel;

import java.util.Random;

public class Coursera {

    public static void main(String[] args)
    {
        System.out.println("Tere Coursera");
        //System.setProperty("java.util.concurrent.ForkJoinPool.common.parallelism","3");


        /*
        double x;
        Random r = new Random();
        double[] X = new double[100000000];
        for (int i=0; i < X.length;i++) {
            //x=r.nextDouble();
            x=45678;
            X[i]=x;
        }
        for ( int numRun=0; numRun<4; numRun++) {
            System.out.println("RUN:"+numRun);
            ReciprocalArraySum.seqArraySum(X);
            System.out.println("");
            ReciprocalArraySum.parArraySum(X);
            System.out.println("");
            ReciprocalArraySum.parManyTaskArraySum(X, 4);
            System.out.println("");
        }
        */

        Student[] students = new Student[7];
        Student stu = new Student("Sanjay","Chatterjee",26, 54, true);
        students[0]=stu;
        stu = new Student("Yunming","Chang",23, 31, true);
        students[1]=stu;
        stu = new Student("John","Smith",23, 84, false);
        students[2]=stu;
        stu = new Student("Shams","imam",20, 73, true);
        students[3]=stu;
        stu = new Student("Max","Grossman",24, 42, false);
        students[4]=stu;
        stu = new Student("John","Simmons",22, 22, false);
        students[5]=stu;
        stu = new Student("Gerhard","Sutter",21, 91, false);
        students[6]=stu;
        StudentAnalytics stud = new StudentAnalytics();
        double kesk = stud.averageAgeOfEnrolledStudentsImperative(students);
        System.out.println("Keskmine Imperative: "+kesk);
        kesk=stud.averageAgeOfEnrolledStudentsParallelStream(students);
        System.out.println("Keskmine ParallelStream: "+kesk);

        String nimi =  stud.mostCommonFirstNameOfInactiveStudentsImperative(students);
        System.out.println("Levinuim mitteaktiivsete eesnimi: "+nimi);

        String maks=stud.mostCommonFirstNameOfInactiveStudentsParallelStream(students);
        System.out.println("PAR Levinuim mitteaktiivsete eesnimi: "+maks);

        int failinuid = stud.countNumberOfFailedStudentsOlderThan20Imperative(students);
        System.out.println("Failinuid: "+failinuid);

        failinuid = stud.countNumberOfFailedStudentsOlderThan20ParallelStream(students);
        System.out.println("PAR Failinuid: "+failinuid);

    }
}
