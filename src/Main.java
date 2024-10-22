import java.io.File;
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
        System.out.println(); //Разделительная строка.
        System.out.println(getHumanReadableSize(getFolderSize(file)));
        System.out.println(); //Разделительная строка.
        System.out.println(getSizeFromHumanReadableSize(getHumanReadableSize(getFolderSize(file))));
    }

    public static long getFolderSize(File folder) {
        if (folder.isFile()) {
            return folder.length();
        }

        long sum = 0;
        File[] files = folder.listFiles();
        if (files != null) {
            for (File file : files) {
                sum += getFolderSize(file);

            }
        }
        return sum;
    }

    //TODO: 24B, 234Kb, 36Mb, 34Gb, 42Tb
    public static String getHumanReadableSize(long size) {

        if (size > 1024 && size < Math.pow(1024, 2)) {
            size = size / 1024;
            return size + "Kb";
        } else if (size > Math.pow(1024, 2) && size < Math.pow(1024, 3) && size < Math.pow(1024, 4)) {
            size = size / (long) Math.pow(1024, 2);
            return size + "Mb";
        } else if (size > Math.pow(1024, 3) && size < Math.pow(1024, 4)) {
            size = size / (long) Math.pow(1024, 3);
            return size + "Gb";
        } else if (size > Math.pow(1024, 4)) {
            size = size / (long) Math.pow(1024, 4);
            return size + "Tb";
        }
        return size + "B";
    }

    //TODO: 24B, 234Kb, 36Mb, 34Gb, 42Tb
    //  24B, 234K, 36M, 34G, 42T
    //  234 => 239616
    public static long getSizeFromHumanReadableSize(String size) {
        long valueOfSize = Long.parseLong(size.replaceAll("[^0-9]+", ""));
        if (size.contains("K")) {
            return valueOfSize * 1024;
        } else if (size.contains("M")) {
            return valueOfSize * (long) Math.pow(1024, 2);
        } else if (size.contains("G")) {
            return valueOfSize * (long) Math.pow(1024, 3);
        } else if (size.contains("T")) {
            return valueOfSize * (long) Math.pow(1024, 4);
        }
        return valueOfSize;
    }
}
