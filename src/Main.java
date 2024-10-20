import java.io.File;
import java.util.Set;
import java.util.concurrent.ForkJoinPool;

public class Main {
    public static void main(String[] args) {

        /*MyThread thread = new MyThread(1);
        MyThread thread2 = new MyThread(2);

        thread.start();
        thread2.start();

        */

        String folderPath = "D:/Музыка";
        File file = new File(folderPath);

        System.out.println(file.length());
       /* System.out.println(); //Разделительная строка.

        Set keys = System.getProperties().keySet();
        for (Object key : keys) {
            System.out.println(key);
        }
        System.out.println(); //Разделительная строка.
        System.out.println(System.getProperties().get("user.dir"));
        */

        long start = System.currentTimeMillis();

        System.out.println(); //Разделительная строка.
        System.out.println(getFolderSize(file));
        long duration = System.currentTimeMillis() - start;
        System.out.println(); //Разделительная строка.
        System.out.println(duration + "ms");

        long start1 = System.currentTimeMillis();

        FolderSizeCalculator calculator = new FolderSizeCalculator(file);
        ForkJoinPool pool = new ForkJoinPool();
        long size = pool.invoke(calculator);
        System.out.println(size);
        long duration1 = System.currentTimeMillis() - start1;
        System.out.println(); //Разделительная строка.
        System.out.println(duration1 + "ms");
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
