package datastructures.trees;

import java.util.*;

/**
 * @author Copyright (c) 2013 by Oracle, Inc. All Rights Reserved.
 */
public class BinarySearchTree<T extends Comparable<T>>
{
    // Initialize the tree
    TreeNode<T> root = null;

    void createTree(List<T> values)
    {
        // Loop over the nodes and insert into tree
        for (T value : values)
        {
            insert(root, value);
        }

        inorder(root);
    }

    public void insert(TreeNode<T> node, T value)
    {
        if (root == null)
        {
            System.err.println("Inserted root " + node + " with value " + value);
            root = new TreeNode(value);
            return;
        }
        //if (value <= node.value)
        if (value.compareTo(node.value) <= 0)
        {
            if (node.leftChild != null)
            {
                insert(node.leftChild, value);
            } else
            {
                System.err.println("  Inserted " + value + " to left of Node " + node.value);
                node.leftChild = new TreeNode<>(value);
            }
        }
        else if (value.compareTo(node.value) > 0)
        {
            if (node.rightChild != null)
            {
                insert(node.rightChild, value);
            } else {
                System.err.println("  Inserted " + value + " to right of Node " + node.value);
                node.rightChild = new TreeNode<>(value);
            }
        }
    }

    void preorder(TreeNode<T> node)
    {
        if (node == null)
        {
            return;
        }
        System.err.println("Visiting " + node);
        preorder(node.leftChild);
        preorder(node.rightChild);
    }

    void postorder(TreeNode<T> node)
    {
        if (node == null)
        {
            return;
        }
        postorder(node.leftChild);
        postorder(node.rightChild);
        System.err.println("Visiting " + node);
    }

    void inorder(TreeNode<T> node)
    {
        if (node == null)
        {
            return;
        }
        inorder(node.leftChild);
        System.err.println("Visiting " + node);
        inorder(node.rightChild);
    }

    // Breadth-first search
    void breadthFirst(TreeNode<T> root)
    {
        Queue<TreeNode<T>> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty())
        {
            TreeNode<T> node = queue.remove();
            System.err.println(node.value);
            if (node.leftChild != null)
            {
                queue.add(node.leftChild);
            }
            if (node.rightChild != null)
            {
                queue.add(node.rightChild);
            }
        }
    }

    public static void main (String [] args)
    {
        BinarySearchTree tree = new BinarySearchTree<Integer>();
        List<Integer> list = new ArrayList<>();
        Random r = new Random();
        for (int i=0; i<10; i++)
        {
            list.add(r.nextInt(100));
        }

        tree.createTree(list);

        System.err.println("\nBreadth First");
        tree.breadthFirst(tree.root);

        System.err.println("\nInorder");
        tree.inorder(tree.root);

        System.err.println("\nPreorder");
        tree.preorder(tree.root);

        System.err.println("\nPostorder");
        tree.postorder(tree.root);
    }

}
