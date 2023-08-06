import java.util.Scanner;

public class Cylinder extends Circle {
    private double height;
    private double volume;

    public Cylinder() {
    }

    public Cylinder(double radius, String color, double circleArea, double height, double volume) {
        super(radius, color, circleArea);
        this.height = height;
        this.volume = volume;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public double getVolume() {
        return volume;
    }

    public void setVolume(double volume) {
        this.volume = volume;
    }

    public void inputCylinderData(Scanner scanner) {
        super.inputCircleData(scanner);
        System.out.println("Nhập vào chiều cao của hình trụ: ");
        this.height = Double.parseDouble(scanner.nextLine());
    }

    public void callVolume() {
        this.volume = super.getCircleArea() * this.height;
    }

    public String toString() {
        return "Thông tin hình trụ:";
    }
    public void displayCylinderData(){
        super.displayDataCircle();
        System.out.printf("Chiều cao hình trụ: %.1f - Thể tích hình trụ: %.1f",this.height,this.volume);
    }

}
