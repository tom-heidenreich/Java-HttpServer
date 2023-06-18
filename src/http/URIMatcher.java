package http;

import java.net.URI;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class URIMatcher<E> {

    private abstract class Node {

        private final String part;

        public Node(String part) {
            this.part = part;
        }

        public boolean matches(String part) {
            if(this.part.equals("*")) return true;
            return this.part.equals(part);
        }
    }

    private class FinalNode extends Node {

        public final E content;

        public FinalNode(String part, E content) {
            super(part);
            this.content = content;
        }
    }

    private class ContinueNode extends Node {

        public final Node child;

        public ContinueNode(String part, Node child) {
            super(part);
            this.child = child;
        }
    }

    private final LinkedList<Node> nodes = new LinkedList<>();

    public void add(URI uri, E content) {
        // split the path into parts
        String[] parts = uri.getPath().split("/");

        if(parts.length == 0) {
            this.nodes.add(new FinalNode("/", content));
            return;
        }

        // remove the first part if it is empty
        if(parts[0].trim().equals("")) parts = Arrays.copyOfRange(parts, 1, parts.length);

        if(parts.length == 1) this.nodes.add(new FinalNode(parts[0], content));
        else {
            // create nodes from the back to the front
            Node node = new FinalNode(parts[parts.length - 1], content);
            for(int i = parts.length - 2; i >= 0; i--) {
                node = new ContinueNode(parts[i], node);
            }
            this.nodes.add(node);
        }
    }

    public void add(String uri, E content) {
        this.add(URI.create(uri), content);
    }

    public List<E> matches(URI uri) {
        // split the path into parts
        String[] parts = uri.getPath().split("/");

        // remove the first part if it is empty
        if(parts.length > 0 && parts[0].trim().equals("")) parts = Arrays.copyOfRange(parts, 1, parts.length);

        @SuppressWarnings("unchecked")
        LinkedList<Node> nodes = (LinkedList<Node>) this.nodes.clone();
        return this.matches(parts, nodes);
    }

    public List<E> matches(String uri) {
        return this.matches(URI.create(uri));
    }

    private List<E> matches(String[] uriParts, LinkedList<Node> nodes) {
        // if there are no parts, return the root node
        if(uriParts.length == 0) return this.matches(new String[] { "/" }, nodes);

        // if there are parts, check if there is a node for the first part
        nodes.removeIf(e -> !e.matches(uriParts[0]));

        if(uriParts.length > 1) {
            // filter out the nodes that are final
            nodes.removeIf(e -> !(e instanceof ContinueNode));
            // map the nodes to their children
            LinkedList<Node> newNodes = nodes.stream().map(e -> ((ContinueNode) e).child).collect(Collectors.toCollection(LinkedList::new));
            return this.matches(Arrays.copyOfRange(uriParts, 1, uriParts.length), newNodes);
        }

        // filter out the nodes that are not final
        nodes.removeIf(e -> e instanceof ContinueNode);
        return nodes.stream().map(e -> ((FinalNode) e).content).collect(Collectors.toList());
    }
}