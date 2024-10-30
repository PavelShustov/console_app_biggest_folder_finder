import java.io.File;
import java.util.concurrent.ForkJoinPool;

public class Main {
    public static void main(String[] args) {


        /*MyThread thread = new MyThread(1);
        MyThread thread2 = new MyThread(2);

        thread.start();
        thread2.start();

        */

       /* for (int i = 0; i < args.length; i++) {
            System.out.println(i + " => " + args[i]);
        }
*/
        ParametersBag bag = new ParametersBag(args);
        String folderPath = bag.getPath();
        long sizeLimit = bag.getLimit();

        System.exit(0);

        //String folderPath = "D:/Музыка";
        //String folderPath = "G:/Games/Установки";
        // String folderPath = "D:/Фильмы";
        // long sizeLimit = (long) (50 * Math.pow(1024, 2));
        File file = new File(folderPath);
        Node root = new Node(file, sizeLimit);


        System.out.println("Размер файла в байтах: " + file.length());
       /* System.out.println(); //Разделительная строка.

        Set keys = System.getProperties().keySet();
        for (Object key : keys) {
            System.out.println(key);
        }
        System.out.println(); //Разделительная строка.
        System.out.println(System.getProperties().get("user.dir"));
        */

        long start = System.currentTimeMillis();

        //System.out.println(); //Разделительная строка.
        // System.out.println(getFolderSize(file));
        long duration = System.currentTimeMillis() - start;
        System.out.println(); //Разделительная строка.
        System.out.println(duration + "ms");
        System.out.println(); //Разделительная строка.

        long start1 = System.currentTimeMillis();

        // FolderSizeCalculator calculator = new FolderSizeCalculator(file);
        FolderSizeCalculator calculator = new FolderSizeCalculator(root);
        ForkJoinPool pool = new ForkJoinPool();
       /* long size = pool.invoke(calculator);
        System.out.println(size);
        */
        pool.invoke(calculator);

        System.out.println("Размер папки в байтах: " + root.getSize());
        System.out.println(); //Разделительная строка.
        System.out.println(root);

        long duration1 = System.currentTimeMillis() - start1;
        //System.out.println(); //Разделительная строка.
        System.out.println("Время затраченное на выполнение программы: " + duration1 + "ms");
        /*System.out.println(); //Разделительная строка.
        //System.out.println(getHumanReadableSize(getFolderSize(file)));
        System.out.println(); //Разделительная строка.
        //System.out.println(getSizeFromHumanReadableSize(getHumanReadableSize(getFolderSize(file))));
    */
    }
}
