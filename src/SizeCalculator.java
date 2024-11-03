import java.io.File;

public class SizeCalculator {

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
        double value = size;
        if (size > 1024 && size < Math.pow(1024, 2)) {
            value = size / 1024.;
            return Math.round(value * 100) / 100. + "Kb";
        } else if (size > Math.pow(1024, 2) && size < Math.pow(1024, 3) && size < Math.pow(1024, 4)) {
            value = size / Math.pow(1024., 2);
            return Math.round(value * 100) / 100. + "Mb";
        } else if (size > Math.pow(1024, 3) && size < Math.pow(1024, 4)) {
            value = size / Math.pow(1024., 3);
            return Math.round(value * 100) / 100. + "Gb";
        } else if (size > Math.pow(1024, 4)) {
            value = size / Math.pow(1024., 4);
            return Math.round(value * 100) / 100. + "Tb";
        }
        return Math.round(value * 100) / 100. + "B";
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
