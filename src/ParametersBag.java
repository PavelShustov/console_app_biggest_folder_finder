import java.io.File;

public class ParametersBag {

    private long limit;
    private String path;

    public ParametersBag(String[] args) {

        if (args.length != 4) {
            throw new IllegalArgumentException("Укажите два параметра:" +
                    " -l (лимит по объему) и -d (путь к папке)");
        }

        limit = 0;
        path = "";
        for (int i = 0; i < 4; i = i + 2) {
            if (args[i].equals("-l")) {
                limit = SizeCalculator.getSizeFromHumanReadableSize(args[i + 1]);
            } else if (args[i].equals("-d")) {
                path = args[i + 1];
            }
        }

        if (limit <= 0) {
            throw new IllegalArgumentException("Лимит не указан или указан неверно");
        }
        File folder = new File(path);
        if (!folder.exists() || !folder.isDirectory()) {
            throw new IllegalArgumentException("Не указан путь к папке или указан неверно");
        }
    }

    public long getLimit() {
        return limit;
    }

    public String getPath() {
        return path;
    }
}
