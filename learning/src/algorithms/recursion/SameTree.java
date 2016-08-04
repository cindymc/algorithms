package algorithms.recursion;

import datastructures.trees.TreeNode;

import java.util.*;

/**
 * Check whether two binary trees are the same.  They are same if:
 * - every corresponding node has same value
 * - the structure of the tree at every corresponding node is same
 *
 * Algorithm
 *  - compare node values at every level
 *  - check that nodes are present for every corresponding node in both trees
 *  - repeat until the entire tree has been traversed
 *
 * Base case:
 *  - a null current node in one tree should have a corresponding null in the other tree (otherwise, break out)
 *  - node values should be same at every node (otherwise, break out)
 *
 * Recursive case:
 *  - check that the subtree rooted at every node is the same
 *
 * We have to visit every node in both trees, so O(2N) = O(N)
 *
 * Created by cindymc on 8/4/16.
 */
public class SameTree
{
    public static void main(String [] args)
    {

    }

    // Insert Node into tree
    Node buildTree()
    {
        Node root = new Node(1);
        Node two = new Node(2);
        Node three = new Node(3);
        Node four = new Node(4);
        Node five = new Node(5);
        Node six = new Node(6);
        Node seven = new Node(7);
        Node eight = new Node(8);

        root.addChildren(two, three);
        two.addChildren(six, null);
        three.addChildren(four, five);
        four.addChildren(seven, null);
        five.addChildren(null, eight);

        return root;
    }


    // Breadth-first search
    void breadthFirst(Node root)
    {
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty())
        {
            Node node = queue.remove();
            if (node.getLeft() != null)
            {
                queue.add(node.getLeft());
            }
            if (node.getRight() != null)
            {
                queue.add(node.getRight());
            }
        }
    }

    private static class Node
    {
        private int id;
        // Either child can be null
        private Node left;
        private Node right;

        public Node (int id){this.id = id;}

        public Node getRight() { return right; }
        public int getId() { return id; }
        public Node getLeft() {  return left;}

        public void addChildren(Node left, Node right){ this.left=left; this.right=right;}
    }

    static boolean sameTree(Node head1, Node head2)
    {
        // Both trees could have null nodes
        if (head1==null && head2==null) return true;

        // We already tested whether both nodes are null, so now see if either is
        if (head1==null) return false;   // because we know head2 is not null
        if (head2==null) return false;   // because we know head1 is not null

        // Recurse.  If both trees have left and right nodes, then test the ID
        if(sameTree(head1.getLeft(), head2.getLeft()) && (sameTree(head1.getRight(), head2.getRight())))
        {
            // Check values on current node
            return head1.getId() == head2.getId();
        }

        // Otherwise, not
        return false;
    }
}
