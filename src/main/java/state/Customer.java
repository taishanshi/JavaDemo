package state;

import java.util.Scanner;

public class Customer {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        StringBuilder sb = new StringBuilder();

        Elevator elevator = new Elevator();

        // 循环读取字符，直到遇到换行
        while (scanner.hasNext()) {
            String line = scanner.nextLine();
            sb.append(line);
            // 或者逐字符处理
            for (char c : line.toCharArray()) {
                elevator.operate(Character.getNumericValue(c));
            }
        }
    }
}
