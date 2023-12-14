package Y2022.days.Day07;

public class Tree {
    protected Node root;
    protected Node currentNode;

    public Tree() {
        this.root = new Node("/");
        this.currentNode = root;
    }

    public void insertNode(Node node) {
        this.currentNode.addChild(node);
    }

    public void stepIn(String name) {
        this.currentNode.getChildren()
                .stream()
                .filter(node -> node.getName().equals(name))
                .findFirst().ifPresent(node -> this.currentNode = node);
    }

    public void stepOut() {
        this.currentNode = currentNode.getParent();
    }
}
