package eg.edu.alexu.csd.filestructure.avl.cs09;


import java.util.ArrayList;

import eg.edu.alexu.csd.filestructure.avl.IAVLTree;
import eg.edu.alexu.csd.filestructure.avl.INode;

public class MyAVL<T extends Comparable<T>> implements IAVLTree<T> {
	public Node root;


	@Override
	public void insert(T key) {
		// TODO Auto-generated method stub
		Node temp = new Node();
		temp.setValue(key);
		if (root == null) {
			root = temp;
		} else {
			Node index = root;
			Node currentParent;
			while (true) {
				currentParent = index;
				if ((int) key < (int) index.getValue()) {
					index = (Node) index.getLeftChild();
					if (index == null) {
						currentParent.setLeftChild(temp);
						return;
					}
				} else {
					index = (Node) index.getRightChild();
					if (index == null) {
						currentParent.setRightChild(temp);
						return;
					}
				}
			}
		}
		// balance method here

		
	}

	@Override
	public boolean delete(T key) {
		// TODO Auto-generated method stub
return false;
	}

	@Override
	public boolean search(T key) {
		// TODO Auto-generated method stub

		return false;
	}

	@Override
	public int height() {
		// TODO Auto-generated method stub
		int rcounter = 0;
		int lcounter = 0;
		Node temp = root;
		while (temp.getRightChild() != null) {
			temp = (Node) temp.getRightChild();
			rcounter++;
		}
		while (temp.getLeftChild() != null) {
			temp = (Node) temp.getLeftChild();
			lcounter++;
		}
		if (rcounter>lcounter) {
			return rcounter+1;
		}else {
			return lcounter+1;
		}

	}

	@Override
	public INode<T> getTree() {
		// TODO Auto-generated method stub
		return root;
	}

	public void print() {
		System.out.println("In Order ");
		printInorder(root);
		System.out.println();
		System.out.println("Pre Order ");
		printPreorder(root);
		System.out.println();
		System.out.println("Post Order ");
		printPostorder(root);
	}
	public void printPreorder(Node x) {
		if (x != null) {
			printPreorder((Node) x.getLeftChild());
			System.out.print(x.getValue());
			printPreorder((Node) x.getLeftChild());
		}
	}

	public void printInorder(Node x) {
		if (x != null) {
			System.out.print(x.getValue());
			printPreorder((Node) x.getLeftChild());
			printPreorder((Node) x.getRightChild());
		}
	}

	public void printPostorder(Node x) {
		if (x != null) {
			printPostorder((Node) x.getLeftChild());
			printPostorder((Node) x.getRightChild());
			System.out.print(x.getValue());

		}
	}

}
