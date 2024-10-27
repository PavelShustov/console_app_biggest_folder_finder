import java.io.File;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.RecursiveTask;

public class FolderSizeCalculator extends RecursiveTask<Long> {

    // private File folder;
    private Node node;

    public FolderSizeCalculator(Node node) {
        this.node = node;
    }

    /*  public FolderSizeCalculator(File folder) {
          this.folder = folder;
      }

      @Override
      protected Long compute() {

          if (folder.isFile()) {
              return folder.length();
          }

          long sum = 0;

          List<FolderSizeCalculator> subTasks = new LinkedList<>();

          File[] files = folder.listFiles();
          for (File file : files) {
              FolderSizeCalculator task = new FolderSizeCalculator(file);
              task.fork(); // Запустим асинхронно.
              subTasks.add(task);
          }

          for (FolderSizeCalculator task : subTasks) {
              sum += task.join(); // Дождёмся выполнения и прибавим результат.
          }

          return sum;
      }

     */
    @Override
    protected Long compute() {

        File folder = node.getFolder();
        if (folder.isFile()) {
            long length = folder.length();
            node.setSize(length);
            return folder.length();
        }

        long sum = 0;

        List<FolderSizeCalculator> subTasks = new LinkedList<>();

        File[] files = folder.listFiles();
        for (File file : files) {
            Node child = new Node(file);
            FolderSizeCalculator task = new FolderSizeCalculator(child);
            //FolderSizeCalculator task = new FolderSizeCalculator(file);
            task.fork(); // Запустим асинхронно.
            subTasks.add(task);
            node.addChild(child);
        }

        for (FolderSizeCalculator task : subTasks) {
            sum += task.join(); // Дождёмся выполнения и прибавим результат.
        }

        node.setSize(sum);
        return sum;
    }

}
