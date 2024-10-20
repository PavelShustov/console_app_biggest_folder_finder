import java.io.File;
import java.util.Set;

public class Main {
    public static void main(String[] args) {

        MyThread thread = new MyThread(1);
        MyThread thread2 = new MyThread(2);

        thread.start();
        thread2.start();

        String folderPath = "D:/Музыка";
        File file = new File(folderPath);

        System.out.println(file.length());
        System.out.println(); //Разделительная строка.

      /*  Set keys = System.getProperties().keySet();
        for (Object key : keys) {
            System.out.println(key);
        }
        System.out.println(); //Разделительная строка.
        System.out.println(System.getProperties().get("user.dir"));
        */
        System.out.println(); //Разделительная строка.
        System.out.println(getFolderSize(file));
    }

    public static long getFolderSize(File folder) {
        if (folder.isFile()) {
            return folder.length();
        }

        long sum = 0;
        File[] files = folder.listFiles();
        for (File file : files) {
            sum += getFolderSize(file);
        }
        return sum;
    }
}
