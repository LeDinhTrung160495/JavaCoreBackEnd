package ra.entity;

import java.util.List;
import java.util.Scanner;

public class Category implements IEntity<Category, Book> {
    private int catalogId;
    private String catalogName;
    private boolean catalogStatus;

    public Category() {
    }

    public Category(int catalogId, String catalogName, boolean catalogStatus) {
        this.catalogId = catalogId;
        this.catalogName = catalogName;
        this.catalogStatus = catalogStatus;
    }

    public int getCatalogId() {
        return catalogId;
    }

    public void setCatalogId(int catalogId) {
        this.catalogId = catalogId;
    }

    public String getCatalogName() {
        return catalogName;
    }

    public void setCatalogName(String catalogName) {
        this.catalogName = catalogName;
    }

    public boolean isCatalogStatus() {
        return catalogStatus;
    }

    public void setCatalogStatus(boolean catalogStatus) {
        this.catalogStatus = catalogStatus;
    }

    @Override
    public void input(Scanner scanner, List<Category> categoryList, List<Book> bookList) {
        this.catalogId = validateCatalogId(scanner, categoryList);
        this.catalogName = validateCatalogName(scanner, categoryList);
        this.catalogStatus = validateCatalogStatus(scanner);
    }

    public static int validateCatalogId(Scanner scanner, List<Category> categoryList) {
        System.out.println("Nhập vào mã thể loại sách: ");
        do {
            String srtCatalogId = scanner.nextLine();
            // Kiểm tra không được để trống
            if (srtCatalogId != null || srtCatalogId.trim().length() != 0) {
                // Kiểm tra dữ liệu nhập vào là duy nhất
                int intCatalogId = Integer.parseInt(srtCatalogId);
                boolean isExistCatalogId = false;
                for (Category category : categoryList
                ) {
                    if (category.catalogId == intCatalogId) {
                        isExistCatalogId = true;
                        break;
                    }
                }
                if (!isExistCatalogId) {
                    //Kiểm tra kiểu dữ liệu
                    try {
                        if (intCatalogId > 0) {
                            return intCatalogId;
                        } else {
                            System.err.println("Mã thể loại sách có giá trị lớn hơn 0, vui lòng nhập lại");
                        }
                    } catch (NumberFormatException ex1) {
                        System.out.println("Mã thể loại sách không phải định dạng số nguyên, vui lòng nhập lại");
                    } catch (Exception ex) {
                        System.err.println("Lỗi không xác định, vui lòng liên hệ hệ thống");
                    }
                } else {
                    System.err.println("Mã thể loại sách đã trùng vui lòng nhập lại");
                }

            } else {
                System.err.println("Mã thể loại sách bị bỏ trống, vui lòng nhập lại");
            }
        } while (true);
    }

    public static String validateCatalogName(Scanner scanner, List<Category> categoryList) {
        System.out.println("Nhập vào tên thể loại sách: ");
        do {
            String catalogName = scanner.nextLine();
            // Kiểm tra không được để trống
            if (catalogName != null || catalogName.trim().length() != 0) {
                boolean isExistCatalogName = false;
                for (Category category : categoryList
                ) {
                    if (category.catalogName.equals(catalogName)) {
                        isExistCatalogName = true;
                        break;
                    }
                }
                if (!isExistCatalogName) {
                    if (catalogName.length() >= 6 && catalogName.length() <= 30) {
                        return catalogName;
                    } else {
                        System.err.println("Tên thể loại sách có từ 6-30 ký tự, vui lòng nhập lại");
                    }
                } else {
                    System.err.println("Tên thể loại sách đã trùng vui lòng nhập lại");
                }
            } else {
                System.err.println("Tên thể loại sách bị bỏ trống, vui lòng nhập lại");
            }
        } while (true);
    }

    public static boolean validateCatalogStatus(Scanner scanner) {
        System.out.println("Nhập vào trạng thái thể loại");
        do {
            String catalogStatus = scanner.nextLine();
            // Kiểm tra không được để trống
            if (catalogStatus != null || catalogStatus.trim().length() != 0) {
                if (catalogStatus.equalsIgnoreCase("true") || catalogStatus.equalsIgnoreCase("false")) {
                    return Boolean.parseBoolean(catalogStatus);
                } else {
                    System.err.println("Trạng thái thể loại chỉ nhận giá trị true | false, vui lòng nhập lại");
                }
            } else {
                System.err.println("Trạng thái thể loại bị bỏ trống, vui lòng nhập lại");
            }
        } while (true);
    }

    @Override
    public void output(List<Category> categoryList) {
        String strCatalogStatus = this.catalogStatus ? "Hoạt động" : "Không hoạt động";
        System.out.printf("Mã thể loại sách: %d - Tên thể loại sách: %s - Trạng thái thể loại: %b\n", this.catalogId, this.catalogName, strCatalogStatus);
    }
}
