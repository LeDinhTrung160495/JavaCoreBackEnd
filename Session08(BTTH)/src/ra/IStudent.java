package ra;

import ra.impl.Student;

import java.util.Scanner;

public interface IStudent {
    public static final float MARK_PASS = 5;
    public abstract void inputData(Scanner scanner, Student[] arrStudents, int numberStudent);
    public abstract void displayData();
    public abstract void callAvgMark();
}
