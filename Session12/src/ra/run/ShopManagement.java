package ra.run;

import ra.entity.Categories;
import ra.entity.Product;

import java.sql.SQLOutput;
import java.util.*;

public class ShopManagement {
    static List<Categories> categoriesList = new ArrayList<>();
    static List<Product> productList = new ArrayList<>();
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        do {
            System.out.println("***********SHOP MANAGEMENT**********");
            System.out.println("1. Quản lý danh mục sản phẩm");
            System.out.println("2. Quản lý sản phẩm");
            System.out.println("3. Thoát");
            System.out.print("Sự lựa chọn của bạn là: ");
            boolean checkChoice = true;
            int choice = 0;
            do {
                try {
                    choice = Integer.parseInt(scanner.nextLine());
                    if(choice>0&&choice<4){
                        checkChoice=false;
                    }else {
                        System.err.println("Sự lựa chọn của bạn phải từ 0 đến 3, vui lòng nhập lại");
                    }
                } catch (NumberFormatException ex) {
                    System.err.println("Sự lựa chọn phải là số nguyên, vui lòng nhập lại");
                }
            } while (checkChoice);
            switch (choice) {
                case 1:
                    ShopManagement.catalogMenu();
                    break;
                case 2:
                    ShopManagement.productMenu();
                    break;
                default:
                    System.exit(0);
            }
        } while (true);
    }

    public static void catalogMenu() {
        boolean isExit = true;
        do {
            System.out.println("**********CATALOG MANAGEMENT**********");
            System.out.println("1. Thêm mới danh mục");
            System.out.println("2. Hiển thị thông tin các danh mục");
            System.out.println("3. Cập nhật tên danh mục theo mã danh mục");
            System.out.println("4. Xóa danh mục theo mã danh mục (Danh mục chưa chứa sản phẩm)");
            System.out.println("5. Thoát (Quay lại Shop Management)");
            System.out.print("Sự lựa chọn của bạn là: ");
            boolean checkChoice = true;
            int choice = 0;
            do {
                try {
                    choice = Integer.parseInt(scanner.nextLine());
                    if(choice>0&&choice<6){
                        checkChoice=false;
                    }else {
                        System.err.println("Sự lựa chọn của bạn phải từ 0 đến 5, vui lòng nhập lại");
                    }
                } catch (NumberFormatException ex) {
                    System.err.println("Sự lựa chọn phải là số nguyên, vui lòng nhập lại");
                }
            } while (checkChoice);
            switch (choice) {
                case 1:
                    ShopManagement.inputCategory();
                    break;
                case 2:
                    ShopManagement.displayCategory();
                    break;
                case 3:
                    break;
                case 4:
                    break;
                default:
                    isExit = false;
            }
        } while (isExit);
    }

    public static void inputCategory() {
        Categories categoryNew = new Categories();
        categoryNew.inputData(scanner, categoriesList, productList);
        categoriesList.add(categoryNew);
    }

    public static void displayCategory() {
        for (Categories category : categoriesList
        ) {
            category.displayData();
        }
    }

    public static void productMenu() {
        boolean isExit = true;
        do {
            System.out.println("**********PRODUCT MANAGEMENT**********");
            System.out.println("1. Thêm mới sản phẩm (Khi thêm cho phép chọn danh mục sản phẩm mà sản phẩm thuộc về)");
            System.out.println("2. Hiển thị thông tin sản phẩm");
            System.out.println("3. Cập nhật giá sản phẩm theo mã sản phẩm");
            System.out.println("4. Xóa sản phẩm theo mã sản phẩm");
            System.out.println("5. Sắp xếp sản phẩm theo giá sản phẩm tăng dần");
            System.out.println("6. Sắp xếp sản phẩm theo tên tăng dần");
            System.out.println("7. Thống kê số lượng sản phẩm theo danh mục sản phẩm");
            System.out.println("8. Tìm kiếm sản phẩm theo tên sản phẩm");
            System.out.println("9. Thoát");
            System.out.print("Sự lựa chọn của bạn là: ");
            boolean checkChoice = true;
            int choice = 0;
            do {
                try {
                    choice = Integer.parseInt(scanner.nextLine());
                    if(choice>0&&choice<10){
                        checkChoice=false;
                    }else {
                        System.err.println("Sự lựa chọn của bạn phải từ 0 đến 9, vui lòng nhập lại");
                    }
                } catch (NumberFormatException ex) {
                    System.err.println("Sự lựa chọn phải là số nguyên, vui lòng nhập lại");
                }
            } while (checkChoice);
            switch (choice) {
                case 1:
                    ShopManagement.inputProduct();
                    break;
                case 2:
                    ShopManagement.displayProduct();
                    break;
                case 3:
                    break;
                case 4:
                    break;
                case 5:
                    ShopManagement.sortProductByPriceASC();
                    break;
                case 6:
                    ShopManagement.sortProductByNameASC();
                    break;
                case 7:
                    ShopManagement.statisticNumberOfProductByCategory();
                    break;
                case 8:
                    ShopManagement.searchProductByName();
                    break;
                default:
                    isExit = false;
            }
        } while (isExit);
    }

    public static void inputProduct() {
        //B1: Khởi tạo đối tượng product thêm mới:
        Product productNew = new Product();
        //B2: Nhập thông tin sản phẩm áo sơ mi polo
        productNew.inputData(scanner, categoriesList, productList);
        //B3: Cho chon sản phẩm thuộc danh mục nào - Hiển thị các danh mục theo MeNu để người dùng chọn
        System.out.println("**********CATALOG**********");
        for (int i = 0; i < categoriesList.size(); i++) {
            System.out.printf("%d. %s\n", i + 1, categoriesList.get(i).getCatalogName());
        }
        System.out.println("Chọn danh mục: ");
        int choiceCatalog = Integer.parseInt(scanner.nextLine());
        productNew.setCatalogId(categoriesList.get(choiceCatalog - 1).getCatalogId());
        //B4: add sản phẩm vào productList
        productList.add(productNew);
    }

    public static void displayProduct() {
        for (Product product : productList
        ) {
            product.displayData();
        }
    }

    public static void sortProductByPriceASC() {
        Collections.sort(productList, new Comparator<Product>() {
            @Override
            public int compare(Product o1, Product o2) {
                return Float.compare(o1.getPrice(), o2.getPrice());
            }
        });
        System.out.println("Đã sắp xếp sản phẩm theo giá sản phẩm tăng dần");
    }

    public static void sortProductByNameASC() {
        Collections.sort(productList, new Comparator<Product>() {
            @Override
            public int compare(Product o1, Product o2) {
                return o1.getProductName().compareTo(o2.getProductName());
            }
        });
        System.out.println("Đã sắp xếp sản phẩm theo tên tăng dần");
    }

    public static void statisticNumberOfProductByCategory() {
        int arrNumberProductByCategory[] = new int[categoriesList.size()];
        for (int i = 0; i < categoriesList.size(); i++) {
            int cntProductByCategory = 0;
            for (int j = 0; j < productList.size(); j++) {
                if (categoriesList.get(i).getCatalogId() == productList.get(j).getCatalogId()) {
                    cntProductByCategory++;
                }
            }
            arrNumberProductByCategory[i] = cntProductByCategory;
        }
        System.out.println("Thống kê số lượng sản phẩm theo danh mục sản phẩm");
        for (Categories category : categoriesList
        ) {
            System.out.printf("%20s", category.getCatalogName());
        }
        System.out.printf("\n");
        for (int cnt : arrNumberProductByCategory
        ) {
            System.out.printf("%20d", cnt);
        }
        System.out.printf("\n");
    }

    public static void searchProductByName() {
        System.out.println("Nhập vào tên sản phẩm cần tìm");
        String productNameSearch = scanner.nextLine();
        boolean isExist = false;
        System.out.println("Thông tin của sinh viên đang tìm kiếm");
        for (Product product : productList
        ) {
            if (product.getProductName().toLowerCase().contains(productNameSearch.toLowerCase())) {
                product.displayData();
                isExist = true;
            }
        }
        if (!isExist) {
            System.out.println("Tên sản phẩm đang được tìm không tồn tại");
        }
    }
}

