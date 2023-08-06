import java.util.Scanner;

public class Student {
    private String studentId;
    private String studentName;
    private int age;
    private boolean sex;
    private float htmlScore;
    private float cssScore;
    private float javascriptScore;
    private float avgScore;

    public Student() {
    }

    public Student(String studentId, String studentName, int age, boolean sex, float htmlScore, float cssScore, float javascriptScore, float avgScore) {
        this.studentId = studentId;
        this.studentName = studentName;
        this.age = age;
        this.sex = sex;
        this.htmlScore = htmlScore;
        this.cssScore = cssScore;
        this.javascriptScore = javascriptScore;
        this.avgScore = avgScore;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public boolean isSex() {
        return sex;
    }

    public void setSex(boolean sex) {
        this.sex = sex;
    }

    public float getHtmlScore() {
        return htmlScore;
    }

    public void setHtmlScore(float htmlScore) {
        this.htmlScore = htmlScore;
    }

    public float getCssScore() {
        return cssScore;
    }

    public void setCssScore(float cssScore) {
        this.cssScore = cssScore;
    }

    public float getJavascriptScore() {
        return javascriptScore;
    }

    public void setJavascriptScore(float javascriptScore) {
        this.javascriptScore = javascriptScore;
    }

    public float getAvgScore() {
        return avgScore;
    }

    public void setAvgScore(float avgScore) {
        this.avgScore = avgScore;
    }

    public void inputData(Scanner scanner) {
        System.out.println("Nhập mã số sinh viên: ");
        this.studentId = scanner.nextLine();
        System.out.println("Nhập tên sinh viên: ");
        this.studentName = scanner.nextLine();
        System.out.println("Nhập tuổi sinh viên: ");
        this.age = Integer.parseInt(scanner.nextLine());
        System.out.println("Nhập giới tính sinh viên: ");
        this.sex = Boolean.parseBoolean(scanner.nextLine());
        System.out.println("Nhập điểm html: ");
        this.htmlScore = Float.parseFloat(scanner.nextLine());
        System.out.println("Nhập điểm css: ");
        this.cssScore = Float.parseFloat(scanner.nextLine());
        System.out.println("Nhập điểm javascript: ");
        this.javascriptScore = Float.parseFloat(scanner.nextLine());
    }

    public void calAvgScore() {
        this.avgScore = (this.htmlScore + this.cssScore + this.javascriptScore) / 3;
    }

    public void displayData() {
        String sexDisplay = this.sex ? "Nam" : "Nữ";
        System.out.printf("Mã sinh viên: %s - Tên sinh viên: %s - Tuổi: %d - Giới tính: %s\n", this.studentId, this.studentName, this.age, sexDisplay);
        System.out.printf("Điểm html: %.1f - Điểm css: %.1f - Điểm javascript: %.1f - AvgScore: %.1f\n",this.htmlScore, this.cssScore, this.javascriptScore,this.avgScore);
    }
}
