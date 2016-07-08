package datastructures.trees;

/**
 * @author Copyright (c) 2013 by Oracle, Inc. All Rights Reserved.
 */
public class TreeNode<T extends Comparable<T>>
{
    T value;
    TreeNode<T> leftChild;
    TreeNode<T> rightChild;

    TreeNode(T value)
    {
        this.value = value;
    }

    public String toString()
    {
        StringBuilder sb = new StringBuilder(" ==> Value: " + value);
       // sb.append(".  Left child: ").append(leftChild==null?"NULL":leftChild.value);
       // sb.append(".  Right child: ").append(rightChild==null?"NULL":rightChild.value);
        return sb.toString();
    }
}
