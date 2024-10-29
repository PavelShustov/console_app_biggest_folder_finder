import java.io.File;
import java.util.ArrayList;

public class Node {

    private File folder;
    private ArrayList<Node> children;
    private long size;
    private int level;
    private long limit;

    public Node(File folder) {
        this.folder = folder;
        children = new ArrayList<>();
    }

    public Node(File folder, long sizeLimit) {
        this.folder = folder;
        children = new ArrayList<>();
        this.limit = sizeLimit;
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

    public long getLimit() {
        return limit;
    }

    @Override
    public String toString() {
        // String folderSize = String.valueOf(SizeCalculator.getFolderSize(folder));
        String humanReadableSIze = SizeCalculator.getHumanReadableSize(getSize());

        StringBuilder builder = new StringBuilder();
        //if (getSize() > limit) {
        builder.append(folder.getName() + " - " + humanReadableSIze + "\n");

        StringBuilder addSpace = new StringBuilder();
        /*for (int i = 0; i < getLevel() + 1; i++) {
            addSpace.append("  ");
        }*/
        addSpace.append("  ".repeat(Math.max(0, getLevel() + 1)));

        for (Node child : children) {
            if (child.getSize() > limit) {
                builder.append(addSpace + child.toString().stripIndent());
                //builder.append("  ".repeat(level + 1) + child.toString());
            }
        }
        return builder.toString();
    }
}
