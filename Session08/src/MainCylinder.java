import java.util.Scanner;

public class MainCylinder {
    public static void main(String[] args) {
        Cylinder myCylinder = new Cylinder();
        Scanner scanner = new Scanner(System.in);
        myCylinder.inputCylinderData(scanner);
        myCylinder.callCircleArea();
        myCylinder.callVolume();
        System.out.println(myCylinder.toString());
        myCylinder.displayCylinderData();
    }
}
