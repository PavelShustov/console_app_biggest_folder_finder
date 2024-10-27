import java.io.File;
import java.util.ArrayList;

public class Node {

    private File folder;
    private ArrayList<Node> children;
    private long size;
    private int level;

    public Node(File folder) {
        this.folder = folder;
        children = new ArrayList<>();
    }

    public File getFolder() {
        return folder;
    }

    public void addChild(Node node) {
        node.setLevel(level + 1);
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

    private void setLevel(int level) {
        this.level = level;
    }

    public int getLevel() {
        return level;
    }

    @Override
    public String toString() {
        String folderSize = String.valueOf(SizeCalculator.getFolderSize(folder));
        String humanReadableSIze = SizeCalculator.getHumanReadableSize(getSize());

        StringBuilder builder = new StringBuilder();
        builder.append(folder.getName() + " - " + humanReadableSIze + "\n");

        StringBuilder addSpace = new StringBuilder();
        /*for (int i = 0; i < getLevel() + 1; i++) {
            addSpace.append("  ");
        }*/
        addSpace.append("  ".repeat(Math.max(0, getLevel() + 1)));
        for (Node child : children) {
            builder.append(addSpace + child.toString());
            //builder.append("  ".repeat(level + 1) + child.toString());
        }

        return builder.toString();

              /*  folderSize + "\n" +
                humanReadableSIze + "\n" +
                "Node{" +
                "folder=" + folder +
                ", children=" + children +
                ", size=" + size +
                '}';*/
    }
}
