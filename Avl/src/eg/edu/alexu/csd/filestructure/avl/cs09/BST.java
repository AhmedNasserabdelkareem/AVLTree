package eg.edu.alexu.csd.filestructure.avl.cs09;

import eg.edu.alexu.csd.filestructure.avl.IAVLTree;
import eg.edu.alexu.csd.filestructure.avl.INode;

public class BST<T extends Comparable<T>> implements IAVLTree<T> {
	public Node<T> root;
	private int size = 0;

	@Override
	public void insert(T key) {
		if (search(key))
			return;
		Node<T> temp = new Node<T>();
		temp.setValue(key);
		if (root == null) {
			root = temp;
			size++;
			return;
		} else {
			Node<T> index = root;
			Node<T> currentParent;
			while (true) {
				currentParent = index;
				if (key.compareTo(index.getValue()) == -1) {
					index = (Node<T>) index.getLeftChild();
					if (index == null) {
						currentParent.setLeftChild(temp);
						temp.setParent(currentParent);
						if(currentParent.getRightChild()==null)
							increaseHeight(currentParent);
							
						break;
					}
				} else {
					index = (Node<T>) index.getRightChild();
					if (index == null) {
						currentParent.setRightChild(temp);
						temp.setParent(currentParent);
						if(currentParent.getLeftChild()==null)
							increaseHeight(currentParent);
						break;
					}
				}
			}
		}
		size++;
	}

	private void increaseHeight(Node<T> node) {
		if(node==null)
			return;
		node.setHeight(node.getHeight()+1);
		increaseHeight(node.getParent());
		
		
	}

	@Override
	public boolean delete(T key) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean search(T key) {
		INode<T> temp = root;
		while (temp != null) {
			if (temp.getValue().compareTo(key) == 0)
				return true;
			if (temp.getValue().compareTo(key) > 0)
				temp = (Node<T>) temp.getLeftChild();
			else
				temp = (Node<T>) temp.getRightChild();
		}
		return false;
	}

	@Override
	public int height() {
		if(root==null)
		return 0;
		else
			return root.getHeight()+1;
	}

	@Override
	public INode<T> getTree() {
		// TODO Auto-generated method stub
		return null;
	}

}
