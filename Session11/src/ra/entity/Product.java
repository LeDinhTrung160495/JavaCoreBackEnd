package ra.entity;

import ra.IShop;

import java.util.List;
import java.util.Scanner;

public class Product implements IShop {
    private String productId;
    private String productName;
    private float price;
    private String title;
    private int catalogId;
    private boolean statusProduct;

    public Product() {
    }

    public Product(String productId, String productName, float price, String title, int catalogId, boolean statusProduct) {
        this.productId = productId;
        this.productName = productName;
        this.price = price;
        this.title = title;
        this.catalogId = catalogId;
        this.statusProduct = statusProduct;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getCatalogId() {
        return catalogId;
    }

    public void setCatalogId(int catalogId) {
        this.catalogId = catalogId;
    }

    public boolean isStatusProduct() {
        return statusProduct;
    }

    public void setStatusProduct(boolean statusProduct) {
        this.statusProduct = statusProduct;
    }

    @Override
    public void inputData(Scanner scanner, List<Categories> categoriesList, List<Product> productList) {
        System.out.println("Nhập vào mã sản phẩm: ");
        boolean checkProductId = true;
        do {
            this.productId = scanner.nextLine();
            boolean isExistProductId = false;
            for (Product product : productList
            ) {
                if (product.productId.equals(this.productId)) {
                    isExistProductId = true;
                    break;
                }
            }
            if (!isExistProductId) {
                if (this.productId.length() == 5) {
                    if (this.productId.startsWith("P")) {
                        break;
                    } else {
                        System.err.println("Vui lòng nhập mã sản phẩm với ký tự bắt đầu là P");
                    }
                } else {
                    System.err.println("Vui lòng nhập mã sản phẩm gồm 5 ký tự");
                }
            } else {
                System.err.println("Mã sản phẩm đã trùng vui lòng nhập lại");
            }
        } while (checkProductId);
        System.out.println("Nhập vào tên sản phẩm");
        boolean checkProductName = true;
        do {
            this.productName = scanner.nextLine();
            boolean isExistProductName = false;
            for (Product product : productList
            ) {
                if (product.productName.equals(this.productName)) {
                    isExistProductName = true;
                    break;
                }
            }
            if (!isExistProductName) {
                break;
            } else {
                System.err.println("Tên sản phẩm đã trùng vui lòng nhập lại");
            }
        } while (checkProductName);
        System.out.println("Nhập vào giá sản phẩm");
        this.price = Float.parseFloat(scanner.nextLine());
        System.out.println("Nhập vào tiêu đề sản phẩm");
        this.title = scanner.nextLine();
        System.out.println("Nhập vào trạng thái sản phẩm");
        this.statusProduct = Boolean.parseBoolean(scanner.nextLine());
    }

    @Override
    public void displayData() {
        System.out.printf("Mã sản phẩm: %s - Tên sản phẩm: %s - Giá sản phẩm: %f\n", this.productId, this.productName, this.price);
        System.out.printf("Tiêu đề sản phẩm: %s - Mã danh mục mà sản phẩm thuộc về: %d - Trạng thái sản phẩm: %b\n", this.title, this.catalogId, this.statusProduct);
    }
}
