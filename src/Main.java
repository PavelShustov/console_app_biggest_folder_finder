import java.io.File;
import java.util.concurrent.ForkJoinPool;

public class Main {
    public static void main(String[] args) {

        for (String arg : args) {
            System.out.println(">>" + arg + "<<");
        }
        //String[] argums = {"-d", "D:\\Музыка", "-l", "54G"};
        ParametersBag bag = new ParametersBag(args);
        String folderPath = bag.getPath();
        long sizeLimit = bag.getLimit();



        //String folderPath = "D:/Музыка";
        //String folderPath = "G:/Games/Установки";
        // String folderPath = "D:/Фильмы";
        // long sizeLimit = (long) (50 * Math.pow(1024, 2));
        File file = new File(folderPath);
        Node root = new Node(file, sizeLimit);


        System.out.println("Размер файла в байтах: " + file.length());

        long start = System.currentTimeMillis();

        long duration = System.currentTimeMillis() - start;
        System.out.println(); //Разделительная строка.
        System.out.println(duration + "ms");
        System.out.println(); //Разделительная строка.

        long start1 = System.currentTimeMillis();

        FolderSizeCalculator calculator = new FolderSizeCalculator(root);
        ForkJoinPool pool = new ForkJoinPool();

        pool.invoke(calculator);

        System.out.println("Размер папки в байтах: " + root.getSize());
        System.out.println(); //Разделительная строка.
        System.out.println(root);

        long duration1 = System.currentTimeMillis() - start1;
        System.out.println("Время затраченное на выполнение программы: " + duration1 + "ms");
    }
}
