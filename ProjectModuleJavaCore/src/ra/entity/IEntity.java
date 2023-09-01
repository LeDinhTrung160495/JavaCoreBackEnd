package ra.entity;

import java.util.List;
import java.util.Scanner;

public interface IEntity<T,U> {
    void input(Scanner scanner, List<T> categoryList, List<U> bookList);
    void output(List<T> categoryList);
}
