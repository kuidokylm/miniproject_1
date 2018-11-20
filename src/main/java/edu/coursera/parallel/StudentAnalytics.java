package edu.coursera.parallel;

import java.util.*;

import static java.util.stream.Collectors.*;

/**
 * A simple wrapper class for various analytics methods.
 */
public final class StudentAnalytics {
    /**
     * Sequentially computes the average age of all actively enrolled students
     * using loops.
     *
     * @param studentArray Student data for the class.
     * @return Average age of enrolled students
     */
    public double averageAgeOfEnrolledStudentsImperative(
            final Student[] studentArray) {
        List<Student> activeStudents = new ArrayList<Student>();

        for (Student s : studentArray) {
            if (s.checkIsCurrent()) {
                activeStudents.add(s);
            }
        }

        double ageSum = 0.0;
        for (Student s : activeStudents) {
            ageSum += s.getAge();
        }

        return ageSum / (double) activeStudents.size();
    }

    /**
     * TODO compute the average age of all actively enrolled students using
     * parallel streams. This should mirror the functionality of
     * averageAgeOfEnrolledStudentsImperative. This method should not use any
     * loops.
     *
     * @param studentArray Student data for the class.
     * @return Average age of enrolled students
     */
    public double averageAgeOfEnrolledStudentsParallelStream(
            final Student[] studentArray) {
        return Arrays.stream(studentArray)
                .parallel()
                .filter( s -> s.checkIsCurrent())
                .mapToDouble(Student::getAge)
                .average().getAsDouble();
        //throw new UnsupportedOperationException();
    }

    /**
     * Sequentially computes the most common first name out of all students that
     * are no longer active in the class using loops.
     *
     * @param studentArray Student data for the class.
     * @return Most common first name of inactive students
     */
    public String mostCommonFirstNameOfInactiveStudentsImperative(
            final Student[] studentArray) {
        List<Student> inactiveStudents = new ArrayList<Student>();

        for (Student s : studentArray) {
            if (!s.checkIsCurrent()) {
                inactiveStudents.add(s);
            }
        }

        Map<String, Integer> nameCounts = new HashMap<String, Integer>();

        for (Student s : inactiveStudents) {
            if (nameCounts.containsKey(s.getFirstName())) {
                nameCounts.put(s.getFirstName(),
                        new Integer(nameCounts.get(s.getFirstName()) + 1));
            } else {
                nameCounts.put(s.getFirstName(), 1);
            }
        }

        String mostCommon = null;
        int mostCommonCount = -1;
        for (Map.Entry<String, Integer> entry : nameCounts.entrySet()) {
            if (mostCommon == null || entry.getValue() > mostCommonCount) {
                mostCommon = entry.getKey();
                mostCommonCount = entry.getValue();
            }
        }

        return mostCommon;
    }

    /**
     * TODO compute the most common first name out of all students that are no
     * longer active in the class using parallel streams. This should mirror the
     * functionality of mostCommonFirstNameOfInactiveStudentsImperative. This
     * method should not use any loops.
     https://marcin-chwedczuk.github.io/grouping-using-java-8-streams
     * @param studentArray Student data for the class.
     * @return Most common first name of inactive students
     */
    //
    public String mostCommonFirstNameOfInactiveStudentsParallelStream(
            final Student[] studentArray) {
        /*
        Map<String,List<Student>> stud =
        Arrays.stream(studentArray)
                .parallel()
                .filter( s -> s.checkIsCurrent() == false)
                .collect(groupingBy(Student::getFirstName,toCollection(ArrayList::new))); // toSet()
        System.out.println(stud);
        Map<String,Long> stu =
                Arrays.stream(studentArray)
                        .parallel()
                        .filter( s -> s.checkIsCurrent() == false)
                        .collect(groupingBy(Student::getFirstName,counting())); // toSet()
        System.out.println(stu);
        System.out.println(stu.getClass());

        Long maksimum = Arrays.stream(studentArray)
                .parallel()
                .filter( s -> s.checkIsCurrent() == false)
                .collect(groupingBy(Student::getFirstName,counting())).values().stream()
                .max(Comparator.comparing(a->a))
                .get();
        System.out.println("Maksimum: "+maksimum);

        List<Map.Entry<String, Long>> st = Arrays.stream(studentArray)
                .parallel()
                .filter( s -> s.checkIsCurrent() == false)
                .collect(groupingBy(Student::getFirstName,counting()))
                .entrySet().stream()
                .collect(toList());
        System.out.println("Maksimum List: "+st);
        */
        return Arrays.stream(studentArray)
                .parallel()
                .filter( s -> !s.checkIsCurrent())
                .collect(groupingBy(Student::getFirstName,counting()))
                .entrySet().stream()
                .collect(toList())
                .stream()
                .min((o1, o2) -> o2.getValue().compareTo(o1.getValue()))
                .get().getKey();
//                .sorted((o1, o2) -> o2.getValue().compareTo(o1.getValue()))  //reverse
//                .findFirst().get().getKey();
    }

    /**
     * Sequentially computes the number of students who have failed the course
     * who are also older than 20 years old. A failing grade is anything below a
     * 65. A student has only failed the course if they have a failing grade and
     * they are not currently active.
     *
     * @param studentArray Student data for the class.
     * @return Number of failed grades from students older than 20 years old.
     */
    public int countNumberOfFailedStudentsOlderThan20Imperative(
            final Student[] studentArray) {
        int count = 0;
        for (Student s : studentArray) {
            if (!s.checkIsCurrent() && s.getAge() > 20 && s.getGrade() < 65) {
                count++;
            }
        }
        return count;
    }

    /**
     * TODO compute the number of students who have failed the course who are
     * also older than 20 years old. A failing grade is anything below a 65. A
     * student has only failed the course if they have a failing grade and they
     * are not currently active. This should mirror the functionality of
     * countNumberOfFailedStudentsOlderThan20Imperative. This method should not
     * use any loops.
     *
     * @param studentArray Student data for the class.
     * @return Number of failed grades from students older than 20 years old.
     */
    public int countNumberOfFailedStudentsOlderThan20ParallelStream(
            final Student[] studentArray) {
        Long kokku=Arrays.stream(studentArray)
                .parallel()
                .filter( s -> ( !s.checkIsCurrent() && s.getGrade() < 65 ))
                .filter(s -> s.getAge() > 20)
                .count();
        return Integer.valueOf(String.valueOf(kokku));
    }
}
