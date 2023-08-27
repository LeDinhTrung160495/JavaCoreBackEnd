package ra.entity;

import java.util.List;
import java.util.Scanner;

//InterfaceGeneric có kiểu dữ liệu là T
public interface IEntity<T> {
    void inputData(Scanner scanner, List<T> list);

    void displayData();

    void callAge();

    void calAvgMark_Rank();
}
