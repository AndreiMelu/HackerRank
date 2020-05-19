
import java.util.Scanner;

class Person {
    protected String firstName;
    protected String lastName;
    protected int idNumber;

    // Constructor
    Person(String firstName, String lastName, int identification) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.idNumber = identification;
    }

    // Print person data
    public void printPerson() {
        System.out.println(
                "Name: " + lastName + ", " + firstName
                        + "\nID: " + idNumber);
    }

}

class Student extends Person {
    private int[] testScores;
    private String firstName;
    private String lastName;
    private int idNumber;

    public Student(String firstName, String lastName, int identification, int[] testScores) {
        super(firstName, lastName, identification);
        this.testScores = testScores;
        this.firstName = firstName;
        this.lastName = lastName;
        this.idNumber = identification;
    }

    protected String calculate() {
        int m = 0;
        for (int i = 0; i < testScores.length; i++) {
            m += testScores[i];
        }
        m = m / testScores.length;
        return m < 40 ? "T" : (m < 55) ? "D" : (m < 70) ? "P" : (m < 80) ? "A" : (m < 90) ? "E" : "O";
    }

}

public class Inheritance {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        String firstName = scan.next();
        String lastName = scan.next();
        int id = scan.nextInt();
        int numScores = scan.nextInt();
        int[] testScores = new int[numScores];
        for (int i = 0; i < numScores; i++) {
            testScores[i] = scan.nextInt();
        }
        scan.close();

        Student s = new Student(firstName, lastName, id, testScores);
        s.printPerson();
        System.out.println("Grade: " + s.calculate());
    }
}