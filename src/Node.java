import java.io.File;
import java.util.ArrayList;

public class Node {

    private File folder;
    private ArrayList<Node> children;
    private long size;

    public Node(File folder) {
        this.folder = folder;
        children = new ArrayList<>();
    }

    public File getFolder() {
        return folder;
    }

    public void addChild(Node node) {
        children.add(node);
    }

    public ArrayList<Node> getChildren() {
        return children;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }


    @Override
    public String toString() {
        String folderSize = String.valueOf(SizeCalculator.getFolderSize(folder));
        String humanReadableSIze = SizeCalculator.getHumanReadableSize(getSize());
        return folderSize + "\n" +
                humanReadableSIze + "\n"/* +
                "Node{" +
                "folder=" + folder +
                ", children=" + children +
                ", size=" + size +
                '}'*/;
    }
}
