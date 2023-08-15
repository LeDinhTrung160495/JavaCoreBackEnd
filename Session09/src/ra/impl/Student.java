package ra.impl;

import com.sun.xml.internal.ws.api.model.wsdl.WSDLOutput;
import ra.IStudent;

import java.util.Scanner;

public class Student implements IStudent {
    private String studentId;
    private String studentName;
    private int age;
    private float htmlScore;
    private float cssScore;
    private float javascriptScore;
    private float avgScore;
    private boolean sex;
    private String classification;
    private String status;

    public Student() {
    }

    public Student(String studentId, String studentName, int age, float htmlScore, float cssScore, float javascriptScore, float avgScore, boolean sex, String classification, String status) {
        this.studentId = studentId;
        this.studentName = studentName;
        this.age = age;
        this.htmlScore = htmlScore;
        this.cssScore = cssScore;
        this.javascriptScore = javascriptScore;
        this.avgScore = avgScore;
        this.sex = sex;
        this.classification = classification;
        this.status = status;
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

    public boolean isSex() {
        return sex;
    }

    public void setSex(boolean sex) {
        this.sex = sex;
    }

    public String getClassification() {
        return classification;
    }

    public void setClassification(String classification) {
        this.classification = classification;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public void inputData(Scanner scanner, Student[] arrStudents, int numberStudent) {
        //1.Mã sinh viên là duy nhất
        System.out.println("Nhập vào mã sinh viên: ");
        boolean checkStudentId = true;
        do {
            this.studentId = scanner.nextLine();
            boolean isExistStudentId = false;
            for (int i = 0; i < numberStudent; i++) {
                if (arrStudents[i].studentId.equals(this.studentId)) {
                    isExistStudentId = true;
                    break;
                }
            }
            if (!isExistStudentId) {
                if (this.studentId.length() == 4) {
                    if (this.studentId.startsWith("SV")) {
                        break;
                    } else {
                        System.err.println("Vui lòng nhập mã số sinh viên với ký tự bắt đầu là SV");
                    }
                } else {
                    System.err.println("Vui lòng nhập mã số sinh viên gồm 4 ký tự");
                }
            } else {
                System.err.println("Mã sinh viên đã trùng vui lòng nhập lại");
            }
        } while (checkStudentId);
        System.out.println("Nhập vào tên sinh viên: ");
        boolean checkStudentName = true;
        do {
            this.studentName = scanner.nextLine();
            if (this.studentName.length() >= 6 && this.studentName.length() <= 50) {
                break;
            } else {
                System.err.println("Tên sinh viên từ 6-50 ký tự, vui lòng nhập lại");
            }
        } while (checkStudentName);
        System.out.println("Nhập vào tuổi sinh viên: ");
        boolean checkAge = true;
        do {
            this.age = Integer.parseInt(scanner.nextLine());
            if (this.age >= 18) {
                break;
            } else {
                System.err.println("Tuổi có giá trị lớn hơn hoặc bằng 18, vui lòng nhập lại");
            }
        } while (checkAge);
        System.out.println("Nhập vào điểm html: ");
        boolean checkHtml = true;
        do {
            this.htmlScore = Float.parseFloat(scanner.nextLine());
            if (this.htmlScore >= 0 && this.htmlScore <= 10) {
                break;
            } else {
                System.err.println("Điểm html trong khoảng 0-10, vui lòng nhập lại");
            }
        } while (checkHtml);
        System.out.println("Nhập vào điểm css: ");
        boolean checkCss = true;
        do {
            this.cssScore = Float.parseFloat(scanner.nextLine());
            if (this.cssScore >= 0 && this.cssScore <= 10) {
                break;
            } else {
                System.err.println("Điểm css trong khoảng 0-10, vui lòng nhập lại");
            }
        } while (checkCss);
        System.out.println("Nhập vào điểm javascript: ");
        boolean checkJavascript = true;
        do {
            this.javascriptScore = Float.parseFloat(scanner.nextLine());
            if (this.javascriptScore >= 0 && this.javascriptScore <= 10) {
                break;
            } else {
                System.err.println("Điểm javascript trong khoảng 0-10, vui lòng nhập lại");
            }
        } while (checkJavascript);
        System.out.println("Nhập vào giới tính của sinh viên");
        this.sex=Boolean.parseBoolean(scanner.nextLine());
    }
    @Override
    public void displayData() {
        String displaySex = this.sex ? "Nam" : "Nữ";
        System.out.printf("Mã sinh viên: %s - Tên sinh viên: %s - Tuổi: %d - Giới tính: %s\n", this.studentId, this.studentName, this.age, displaySex);
        System.out.printf("Điểm html: %.1f - Điểm css: %.1f - Điểm javascript: %.1f - Điểm trung bình: %.1f\n", this.htmlScore, this.cssScore, this.javascriptScore, this.avgScore);
        System.out.printf("Xếp loại sinh viên: %s - Trạng thái sinh viên: %s\n", this.classification, this.status);
    }

    @Override
    public void callAvgMark() {
        this.avgScore = (this.htmlScore + this.cssScore + this.javascriptScore)/3;
    }

    public void callClassificationStudent() {
        if (this.avgScore < 5) {
            this.classification = "Xếp loại yếu";
        } else if (this.avgScore < 7) {
            this.classification = "Xếp loại trung bình";
        } else if (this.avgScore < 8) {
            this.classification = "Xếp loại khá";
        } else if (this.avgScore < 9) {
            this.classification = "Xếp loại giỏi";
        } else {
            this.classification = "Xếp loại xuất sắc";
        }
    }

    public void callStatusStudent() {
        if (this.avgScore >= MARK_PASS) {
            this.status = "PASS";
        } else {
            this.status = "FAIL";
        }
    }
}
