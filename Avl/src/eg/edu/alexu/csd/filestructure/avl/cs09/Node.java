package eg.edu.alexu.csd.filestructure.avl.cs09;

import eg.edu.alexu.csd.filestructure.avl.INode;

/**
 * @author ahmednasser
 *
 * @param <T>
 */
public class Node<T extends Comparable<T>> implements INode<T> {
	private T nodeValue;
	private Node<T> left;
	private Node<T> right;
	private Node<T> parent;
	private int index;
	private int height;
	@Override
	public INode<T> getLeftChild() {
		// TODO Auto-generated method stub
		return left;
	}

	@Override
	public INode<T> getRightChild() {
		// TODO Auto-generated method stub
		return right;
	}

	@Override
	public T getValue() {
		// TODO Auto-generated method stub
		return nodeValue;
	}

	@Override
	public void setValue(T value) {
		// TODO Auto-generated method stub
		this.nodeValue=value;
		
	}
	
	public void setHeight(int height)
	{
		this.height=height;
	}
	public int getHeight()
	{
		return height;
	}
	public int getIndex() {
		return index;
	}

	public void setIndex(int nodeIndex) {
		this.index = nodeIndex;

	}

	public void setLeftChild(Node<T> left) {
		this.left = left;
	}

	public void setRightChild(Node<T> right) {
		this.right = right;
	}

	public void setParent(Node<T> parent) {
		this.parent = parent;
	}
	/*public Node<T> getleft()
	{
		return left;
	}
	public Node<T> getright()
	{
		return right;
	}*/

	public int getLeftIndex() {
		return (index * 2) + 1;
	}

	public int getRightIndex() {
		return (index * 2) + 2;

	}
	public Node<T> getParent()
	{
		return parent;
	}
	public int getParentIndex() {
		if ((index-1)<0) {
			return -1;
		}
		return ((index - 1) / 2);
	}


}
