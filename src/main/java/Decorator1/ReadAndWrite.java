package Decorator1;

import java.io.*;

public class ReadAndWrite {
    public static void main(String[] args) {


        try (BufferedInputStream bis = new BufferedInputStream(new FileInputStream("src/main/resources/Decorator1/input.txt"));
             BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream("src/main/resources/Decorator1/output.txt"))) {
            byte[] buffer = new byte[8192];
            int len;
            while ((len = bis.read(buffer)) != -1) {
                bos.write(buffer, 0, len);
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }
}
