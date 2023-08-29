package ra.entity;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class Student implements IEntity<Student>, Serializable {
    private String studentId;
    private String studentName;
    private Date birthday;
    private int age;
    private boolean sex;
    private float htmlScore;
    private float cssScore;
    private float javascriptScore;
    private float avgScore;
    private String classification;

    public Student() {
    }

    public Student(String studentId, String studentName, Date birthday, int age, boolean sex, float htmlScore, float cssScore, float javascriptScore, float avgScore, String classification) {
        this.studentId = studentId;
        this.studentName = studentName;
        this.birthday = birthday;
        this.age = age;
        this.sex = sex;
        this.htmlScore = htmlScore;
        this.cssScore = cssScore;
        this.javascriptScore = javascriptScore;
        this.avgScore = avgScore;
        this.classification = classification;
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

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
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

    public String getClassification() {
        return classification;
    }

    public void setClassification(String classification) {
        this.classification = classification;
    }

    @Override
    public void inputData(Scanner scanner, List<Student> list) {
        this.studentId = validateStudentId(scanner, list);
        this.studentName = validateStudentName(scanner);
        this.birthday = validateBirthDay(scanner);
        this.sex = validateSex(scanner);
        this.htmlScore = validateScoreHTML(scanner);
        this.cssScore = validateScoreCss(scanner);
        this.javascriptScore = validateScoreJavascript(scanner);
    }

    public  String validateStudentId(Scanner scanner, List<Student> list) {
        System.out.println("Nhập vào mã sinh viên");
        do {
            String studentId = scanner.nextLine();
            boolean isExistStudentId = false;
            for (Student student : list
            ) {
                if (student.studentId.equals(studentId)) {
                    isExistStudentId = true;
                    break;
                }
            }
            if (!isExistStudentId) {
                if (studentId.length() == 4) {
                    if (studentId.startsWith("S")) {
                        return studentId;
                    } else {
                        System.err.println("Vui lòng nhập mã số sinh viên với ký tự bắt đầu là S");
                    }
                } else {
                    System.err.println("Vui lòng nhập mã số sinh viên gồm 4 ký tự");
                }
            } else {
                System.err.println("Mã sinh viên đã trùng vui lòng nhập lại");
            }
        } while (true);
    }

    public static String validateStudentName(Scanner scanner) {
        System.out.println("Nhập vào tên sinh viên: ");
        do {
            String studentName = scanner.nextLine();
            if (studentName.length() >= 10 && studentName.length() <= 50) {
                return studentName;
            } else {
                System.err.println("Tên sinh viên có từ 10-50 ký tự, vui lòng nhập lại");
            }
        } while (true);
    }

    public static Date validateBirthDay(Scanner scanner) {
        System.out.println("Nhập vào ngày sinh của sinh viên:");
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        do {
            try {
                Date birthDay = sdf.parse(scanner.nextLine());
                Calendar cal = Calendar.getInstance();
                cal.setTime(birthDay);
                if (cal.get(Calendar.YEAR) < 2005) {
                    return birthDay;
                } else {
                    System.err.println("Năm sinh phải trước năm 2005, vui lòng nhập lại");
                }
            } catch (ParseException ex1) {
                System.err.println("Dữ liệu không đúng định dạng dd/MM/yyyy, vui lòng nhập lại");
            } catch (Exception ex) {
                System.err.println("Xảy ra lỗi không xác định, vui lòng liên hệ với hệ thống");
            }
        } while (true);
    }

    public static boolean validateSex(Scanner scanner) {
        System.out.println("Nhập vào giới tính của sinh viên");
        do {
            String sex = scanner.nextLine();
            if (sex.equalsIgnoreCase("true") || sex.equalsIgnoreCase("false")) {
                return Boolean.parseBoolean(sex);
            } else {
                System.err.println("Giới tính chỉ nhận giá trị true | false, vui lòng nhập lại");
            }
        } while (true);
    }

    public static float validateScoreHTML(Scanner scanner) {
        System.out.println("Nhập vào điểm HTML");
        do {
            try {
                float scoreHTML = Float.parseFloat(scanner.nextLine());
                if (scoreHTML >= 0 && scoreHTML <= 10) {
                    return scoreHTML;
                } else {
                    System.err.println("Điểm HTML có giá trị từ 0 đến 10, vui lòng nhập lại");
                }
            } catch (NumberFormatException ex1) {
                System.err.println("Điểm HTML không phải định dạng số thực, vui lòng nhập lại");
            } catch (Exception ex) {
                System.err.println("Lỗi không xác định, vui lòng liên hệ hệ thống");
            }
        } while (true);
    }

    public static float validateScoreCss(Scanner scanner) {
        System.out.println("Nhập vào điểm CSS");
        do {
            try {
                float scoreCSS = Float.parseFloat(scanner.nextLine());
                if (scoreCSS >= 0 && scoreCSS <= 10) {
                    return scoreCSS;
                } else {
                    System.err.println("Điểm CSS có giá trị từ 0 đến 10, vui lòng nhập lại");
                }
            } catch (NumberFormatException ex1) {
                System.err.println("Điểm CSS không phải định dạng số thực, vui lòng nhập lại");
            } catch (Exception ex) {
                System.err.println("Lỗi không xác định, vui lòng liên hệ hệ thống");
            }
        } while (true);
    }

    public static float validateScoreJavascript(Scanner scanner) {
        System.out.println("Nhập vào điểm Javascript");
        do {
            try {
                float scoreJavascript = Float.parseFloat(scanner.nextLine());
                if (scoreJavascript >= 0 && scoreJavascript <= 10) {
                    return scoreJavascript;
                } else {
                    System.err.println("Điểm Javascript có gí trị từ 0 đến 10, vui lòng nhập lại");
                }
            } catch (NumberFormatException ex1) {
                System.err.println("Điểm Javascript không phải định dạng số thực, vui lòng nhập lại");
            } catch (Exception ex) {
                System.err.println("Lỗi không xác định, vui lòng liên hệ hệ thống");
            }
        } while (true);
    }

    @Override
    public void displayData() {
        String strSex = this.sex ? "Nam" : "Nữ";
        System.out.printf("StudentId: %s - StudentName: %s - BirthDay: %td-%tb-%tY - Age: %d\n", this.studentId, this.studentName, this.birthday,this.birthday,this.birthday, this.age);
        System.out.printf("Sex: %s - HTML: %f - CSS: %f - Javascript: %f - AvgScore: %f - Rank: %s\n", strSex, this.htmlScore, this.cssScore, this.javascriptScore, this.avgScore, this.classification);
    }

    @Override
    public void callAge() {
        Date now = new Date();
        this.age = now.getYear() - this.birthday.getYear();
    }

    @Override
    public void calAvgMark_Rank() {
        this.avgScore = (this.htmlScore + this.cssScore + this.javascriptScore) / 3;
        if (this.avgScore < 5) {
            this.classification = "Yếu";
        } else if (this.avgScore < 7) {
            this.classification = "Trung bình";
        } else if (this.avgScore < 8) {
            this.classification = "Khá";
        } else if (this.avgScore < 9) {
            this.classification = "Giỏi";
        } else {
            this.classification = "Xuất sắc";
        }
    }
}

