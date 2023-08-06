import java.util.Scanner;

public class Circle {
    private double radius;
    private String color;
    private double circleArea;

    public Circle() {
    }

    public Circle(double radius, String color, double circleArea) {
        this.radius = radius;
        this.color = color;
        this.circleArea = circleArea;
    }

    public double getRadius() {
        return radius;
    }

    public void setRadius(double radius) {
        this.radius = radius;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public double getCircleArea() {
        return circleArea;
    }

    public void setCircleArea(double circleArea) {
        this.circleArea = circleArea;
    }

    public void inputCircleData(Scanner scanner) {
        System.out.println("Nhập vào bán kính của hình tròn: ");
        this.radius = Double.parseDouble(scanner.nextLine());
        System.out.println("Nhập vào màu sắc của hình tròn: ");
        this.color = scanner.nextLine();
    }

    public void callCircleArea() {
        this.circleArea = Math.PI * Math.pow(this.radius, 2);
    }

    public String toString() {
        return "Thông tin hình tròn";
    }

    public void displayDataCircle() {
        System.out.printf("Bán kính hình tròn: %.1f - Màu sắc hình tròn: %s - Diện tích hình tròn: %.1f\n", this.radius, this.color, this.circleArea);
    }
}
