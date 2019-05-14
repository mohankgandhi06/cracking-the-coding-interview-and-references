package r.chapter.hardDifficulty;

import java.util.*;

public class UWordTransformer {

    public HashMap<String, String> dictionary;
    public char[] alphabets = new char[]{'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};

    public UWordTransformer() {
        this.dictionary = new HashMap<>();
    }

    public static void main(String[] args) {
        UWordTransformer game = new UWordTransformer();
        buildDictionary(game);

        String from = "DAMP";
        String to = "LIKE";
        System.out.println("From: " + from + " TO: " + to);
        game.oneAtATime(from, to);

        from = "DAME";
        to = "LIVE";
        System.out.println("\nFrom: " + from + " TO: " + to);
        game.oneAtATime(from, to);

        from = "HIKE";
        to = "LAMB";
        System.out.println("\nFrom: " + from + " TO: " + to);
        game.oneAtATime(from, to);
    }

    private void oneAtATime(String from, String to) {
        /* Bi-directional Search */
        /* Take FROM variable and then have wildcard searches for each character
         * Don't repeat the characters
         * If specific position is altered in the current one don't alter the same
         * in immediate next search. ensure that carefully to avoid repeat
         *
         * Take TO variable and after the modification check if the values match */
        HashMap<String, GraphNode> setLeft = new HashMap<>();
        HashMap<String, GraphNode> setRight = new HashMap<>();

        Queue<Criteria> queueLeft = new LinkedList<>();
        Queue<Criteria> queueRight = new LinkedList<>();

        GraphNode leftNode = new GraphNode(from, null);
        GraphNode rightNode = new GraphNode(to, null);

        setLeft.put(from, leftNode);
        queueLeft.offer(new Criteria(leftNode, -1));
        setRight.put(to, rightNode);
        queueRight.offer(new Criteria(rightNode, -1));

        ResultList result = new ResultList();
        Graph leftGraph = new Graph(leftNode);
        Graph rightGraph = new Graph(rightNode);

        while (!queueRight.isEmpty() && !queueLeft.isEmpty()) {
            if (!queueLeft.isEmpty()) {
                Criteria leftCriteria = queueLeft.poll();
                solve(leftCriteria, setLeft, queueLeft, setRight, result, leftGraph);
                if (result.isSequenceFound()) {
                    LinkedList<GraphNode> backward = new LinkedList<>();
                    LinkedList<GraphNode> forward = new LinkedList<>();
                    showBackwards(result.getFrom(), backward);
                    showForwards(result.getIntermediate(), forward);
                    for (GraphNode graphNodes : backward) {
                        forward.add(graphNodes);
                    }
                    Collections.reverse(forward);
                    show(forward);
                    return;
                }
            }
            if (!queueRight.isEmpty()) {
                Criteria rightCriteria = queueRight.poll();
                solve(rightCriteria, setRight, queueRight, setLeft, result, rightGraph);
                if (result.isSequenceFound()) {
                    int z = 0;
                    LinkedList<GraphNode> backward = new LinkedList<>();
                    LinkedList<GraphNode> forward = new LinkedList<>();
                    showBackwards(result.getFrom(), backward);
                    showForwards(result.getIntermediate(), forward);
                    for (GraphNode graphNodes : backward) {
                        forward.add(graphNodes);
                    }
                    show(forward);
                    return;
                }
            }
        }
    }

    private void show(LinkedList<GraphNode> forward) {
        LinkedList<GraphNode> result = (LinkedList<GraphNode>) forward.clone();
        System.out.println("Path: ");
        for (GraphNode node : result) {
            System.out.println(node.data + " ");
        }
    }

    private void solve(Criteria criteria, HashMap<String, GraphNode> set, Queue<Criteria> queue, HashMap<String, GraphNode> otherSet, ResultList result, Graph leftGraph) {
        /* Create a wildcard search criteria and search for all the elements that are already not there */
        for (int i = 0; i < criteria.getWord().data.length(); i++) {
            if (i != criteria.getExcludeIndex()) {
                search(criteria, i, set, queue, otherSet, result);/* Word, Index which needs to be altered */
                addToGraph(criteria.getWord(), result.getList());
                if (result.isSequenceFound()) {
                    return;
                }
            }
        }
    }

    private void addToGraph(GraphNode node, List<GraphNode> list) {
        GraphNode tempNode = node;
        if (tempNode.map == null) {
            tempNode.map = new HashMap<>();
        }
        for (GraphNode s : list) {
            tempNode.map.put(s.data, s);
        }
        list.clear();
    }

    private void search(Criteria criteria, int wildcardIndex, HashMap<String, GraphNode> set, Queue<Criteria> queue, HashMap<String, GraphNode> otherSet, ResultList result) {
        StringBuilder stringBuilder = new StringBuilder(criteria.getWord().data);
        for (char c : this.alphabets) {
            stringBuilder.setCharAt(wildcardIndex, c);
            if (this.dictionary.containsKey(stringBuilder.toString())) {
                if (!set.containsKey(stringBuilder.toString())) {
                    if (otherSet.containsKey(stringBuilder.toString())) {
                        result.setFrom(criteria.getWord());
                        result.setIntermediate(otherSet.get(stringBuilder.toString()));
                        result.setSequenceFound(true);
                        return;
                    }
                    GraphNode newNode = new GraphNode(stringBuilder.toString(), criteria.getWord());
                    set.put(stringBuilder.toString(), newNode);
                    queue.offer(new Criteria(newNode, criteria.getExcludeIndex()));
                    result.getList().add(newNode);
                }
            }
        }
    }

    private void showBackwards(GraphNode node, LinkedList<GraphNode> backward) {
        /* IMPLEMENT DFS for the word and then start showing their parent and their parent until the root node */
        GraphNode graphNode = node;
        while (graphNode != null) {
            backward.offer(graphNode);
            graphNode = graphNode.parent;
        }
    }

    private void showForwards(GraphNode node, LinkedList<GraphNode> forward) {
        /* IMPLEMENT DFS for the word and then start showing from the root node */
        GraphNode graphNode = node;
        while (graphNode != null) {
            forward.addFirst(graphNode);
            graphNode = graphNode.parent;
        }
    }

    private static void buildDictionary(UWordTransformer game) {
        game.dictionary.put("LIKE", "LIKE");
        game.dictionary.put("BIKE", "BIKE");
        game.dictionary.put("HIKE", "HIKE");
        game.dictionary.put("PIKE", "PIKE");
        game.dictionary.put("LAKE", "LAKE");
        game.dictionary.put("LICE", "LICE");
        game.dictionary.put("LIFE", "LIFE");
        /*game.dictionary.put("LIME", "LIME");*/
        game.dictionary.put("LINE", "LINE");
        game.dictionary.put("LIVE", "LIVE");
        game.dictionary.put("BAKE", "BAKE");
        game.dictionary.put("BILE", "BILE");
        game.dictionary.put("BITE", "BITE");
        game.dictionary.put("HIVE", "HIVE");
        game.dictionary.put("HIDE", "HIDE");
        game.dictionary.put("HIRE", "HIRE");
        game.dictionary.put("PUKE", "PUKE");
        game.dictionary.put("POKE", "POKE");
        game.dictionary.put("PILE", "PILE");
        game.dictionary.put("PIPE", "PIPE");
        game.dictionary.put("CAKE", "CAKE");
        game.dictionary.put("FAKE", "FAKE");
        game.dictionary.put("MAKE", "MAKE");
        game.dictionary.put("RAKE", "RAKE");
        game.dictionary.put("SAKE", "SAKE");
        game.dictionary.put("TAKE", "TAKE");
        game.dictionary.put("WAKE", "WAKE");
        game.dictionary.put("LACE", "LACE");
        /*game.dictionary.put("LAME", "LAME");*/
        game.dictionary.put("LANE", "LANE");
        game.dictionary.put("LATE", "LATE");
        game.dictionary.put("DAMP", "DAMP");
        game.dictionary.put("CAMP", "CAMP");
        game.dictionary.put("LAMP", "LAMP");
        game.dictionary.put("RAMP", "RAMP");
        game.dictionary.put("DUMP", "DUMP");
        game.dictionary.put("DAME", "DAME");
        game.dictionary.put("DAMN", "DAMN");
        game.dictionary.put("CAME", "CAME");
        game.dictionary.put("LIMP", "LIMP");
        game.dictionary.put("LUMP", "LUMP");
        game.dictionary.put("LAMB", "LAMB");
        game.dictionary.put("ROMP", "ROMP");
    }
}

class Criteria {
    private GraphNode word;
    private int excludeIndex;

    public Criteria(GraphNode word, int excludeIndex) {
        this.word = word;
        this.excludeIndex = excludeIndex;
    }

    public GraphNode getWord() {
        return word;
    }

    public int getExcludeIndex() {
        return excludeIndex;
    }
}

class ResultList {
    private List<GraphNode> list;
    private boolean sequenceFound;
    private GraphNode from;
    private GraphNode intermediate;

    public ResultList() {
        this.list = new LinkedList<>();
    }

    public List<GraphNode> getList() {
        return list;
    }

    public boolean isSequenceFound() {
        return sequenceFound;
    }

    public void setSequenceFound(boolean sequenceFound) {
        this.sequenceFound = sequenceFound;
    }

    public GraphNode getFrom() {
        return from;
    }

    public void setFrom(GraphNode from) {
        this.from = from;
    }

    public GraphNode getIntermediate() {
        return intermediate;
    }

    public void setIntermediate(GraphNode intermediate) {
        this.intermediate = intermediate;
    }
}

class Graph {
    public GraphNode node;

    public Graph(GraphNode node) {
        this.node = node;
    }
}

class GraphNode {
    public String data;
    public GraphNode parent;
    public HashMap<String, GraphNode> map;

    public GraphNode(String data, GraphNode parent) {
        this.data = data;
        this.parent = parent;
    }

    public GraphNode(GraphNode node) {
        this.data = node.data;
    }
}