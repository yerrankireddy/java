import java.io.*;
import java.util.*;

public class Solution {

    enum Color {
        RED, GREEN
    }

    static abstract class Tree {
        private int value;
        private Color color;
        private int depth;

        Tree(int value, Color color, int depth) {
            this.value = value;
            this.color = color;
            this.depth = depth;
        }

        public int getValue() {
            return value;
        }

        public Color getColor() {
            return color;
        }

        public int getDepth() {
            return depth;
        }

        public abstract void accept(TreeVis visitor);
    }

    static class TreeNode extends Tree {
        private ArrayList<Tree> children = new ArrayList<>();

        TreeNode(int value, Color color, int depth) {
            super(value, color, depth);
        }

        public void accept(TreeVis visitor) {
            visitor.visitNode(this);

            for (Tree child : children) {
                child.accept(visitor);
            }
        }

        public void addChild(Tree child) {
            children.add(child);
        }
    }

    static class TreeLeaf extends Tree {
        TreeLeaf(int value, Color color, int depth) {
            super(value, color, depth);
        }

        public void accept(TreeVis visitor) {
            visitor.visitLeaf(this);
        }
    }

    static abstract class TreeVis {

        public abstract int getResult();

        public abstract void visitNode(TreeNode node);

        public abstract void visitLeaf(TreeLeaf leaf);
    }

    static class SumInLeavesVisitor extends TreeVis {

        private int sum = 0;

        public int getResult() {
            return sum;
        }

        public void visitNode(TreeNode node) {
            // Nothing to do
        }

        public void visitLeaf(TreeLeaf leaf) {
            sum += leaf.getValue();
        }
    }

    static class ProductOfRedNodesVisitor extends TreeVis {

        private long product = 1;
        private static final long MOD = 1000000007L;

        public int getResult() {
            return (int) product;
        }

        public void visitNode(TreeNode node) {
            if (node.getColor() == Color.RED) {
                product = (product * node.getValue()) % MOD;
            }
        }

        public void visitLeaf(TreeLeaf leaf) {
            if (leaf.getColor() == Color.RED) {
                product = (product * leaf.getValue()) % MOD;
            }
        }
    }

    static class FancyVisitor extends TreeVis {

        private int evenDepthSum = 0;
        private int greenLeafSum = 0;

        public int getResult() {
            return Math.abs(evenDepthSum - greenLeafSum);
        }

        public void visitNode(TreeNode node) {
            if (node.getDepth() % 2 == 0) {
                evenDepthSum += node.getValue();
            }
        }

        public void visitLeaf(TreeLeaf leaf) {
            if (leaf.getColor() == Color.GREEN) {
                greenLeafSum += leaf.getValue();
            }
        }
    }

    public static Tree solve() {

        Scanner scanner = new Scanner(System.in);

        int n = scanner.nextInt();

        int[] values = new int[n];
        for (int i = 0; i < n; i++) {
            values[i] = scanner.nextInt();
        }

        int[] colors = new int[n];
        for (int i = 0; i < n; i++) {
            colors[i] = scanner.nextInt();
        }

        ArrayList<Integer>[] graph = new ArrayList[n];

        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < n - 1; i++) {
            int u = scanner.nextInt() - 1;
            int v = scanner.nextInt() - 1;

            graph[u].add(v);
            graph[v].add(u);
        }

        scanner.close();

        return buildTree(0, -1, 0, values, colors, graph);
    }

    static Tree buildTree(
            int current,
            int parent,
            int depth,
            int[] values,
            int[] colors,
            ArrayList<Integer>[] graph) {

        Color color = colors[current] == 0
                ? Color.RED
                : Color.GREEN;

        ArrayList<Integer> children = new ArrayList<>();

        for (int next : graph[current]) {
            if (next != parent) {
                children.add(next);
            }
        }

        if (children.isEmpty()) {
            return new TreeLeaf(
                    values[current],
                    color,
                    depth
            );
        }

        TreeNode node = new TreeNode(
                values[current],
                color,
                depth
        );

        for (int child : children) {
            node.addChild(
                    buildTree(
                            child,
                            current,
                            depth + 1,
                            values,
                            colors,
                            graph
                    )
            );
        }

        return node;
    }

    public static void main(String[] args) {

        Tree root = solve();

        SumInLeavesVisitor vis1 = new SumInLeavesVisitor();
        ProductOfRedNodesVisitor vis2 = new ProductOfRedNodesVisitor();
        FancyVisitor vis3 = new FancyVisitor();

        root.accept(vis1);
        root.accept(vis2);
        root.accept(vis3);

        System.out.println(vis1.getResult());
        System.out.println(vis2.getResult());
        System.out.println(vis3.getResult());
    }
}
