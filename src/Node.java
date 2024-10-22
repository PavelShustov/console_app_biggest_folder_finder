import java.io.File;
import java.util.ArrayList;

public class Node {

    private File folder;
    private ArrayList<Node> children;

    public Node(File folder) {
        this.folder = folder;
        children = new ArrayList<>();
    }

    public File getFolder() {
        return folder;
    }
}
