package eg.edu.alexu.csd.filestructure.avl.cs09;

import eg.edu.alexu.csd.filestructure.avl.IAVLTree;
import eg.edu.alexu.csd.filestructure.avl.INode;

public class MyAVL<T extends Comparable<T>> implements IAVLTree<T> {
	public Node<T> root;

	@Override
	public void insert(T key) {
		// TODO Auto-generated method stub
		Node<T> temp = new Node<T>();
		temp.setValue(key);
		int childPosition;
		if (root == null) {
			root = temp;
			return;
		} else {
			Node<T> index = root;
			Node<T> currentParent;
			while (true) {
				currentParent = index;
				index.setHeight(index.getHeight() + 1);
				if ((int) key < (int) index.getValue()) {
					index = (Node<T>) index.getLeftChild();
					if (index == null) {
						currentParent.setLeftChild(temp);
						temp.setParent(currentParent);
						childPosition=1;
						break;
					}
				} else {
					index = (Node<T>) index.getRightChild();
					if (index == null) {
						currentParent.setRightChild(temp);
						temp.setParent(currentParent);
						childPosition=2;
						break;
					}
				}
			}
		}
		balance(temp,childPosition);
		// balance method here

	}

	private void balance(Node<T> temp,int position) {
		// TODO Auto-generated method stub
		Node<T> parent = temp.getParent();
		int p;
		if(parent==null)
			return;
		if(parent.getleft().equals(temp))
			p=1;
		else
			p=2;
		if(checkbalance(parent))//balanced
		{
		  balance(parent.getParent(),p);	
		}
		else//unbalanced
		{
			if(position==1&&p==1)// x->left y && y->left z
			{
				
			}
			else if(position==2&&p==2)// x->right y && y->right z 
			{
				
			}
			else if(position==1&&p==2)//x-> left y && y-> right z
			{
				
			}
			else if(position==2&&p==1)//x->right y&& y->left z
			{
				
			}
		}

	}

	private boolean checkbalance(Node<T> parent) {
		// TODO Auto-generated method stub
		if (parent == null)
			return true;//reached the root and balanced
		int l = parent.getleft().getHeight();
		int r = parent.getright().getHeight();
		if (Math.abs(l-r)>1)//the left is unbalanced
			return false;
		return true;//balanced

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
		/*
		 * int rcounter = 0; int lcounter = 0; Node temp = root; while
		 * (temp.getRightChild() != null) { temp = (Node) temp.getRightChild();
		 * rcounter++; } while (temp.getLeftChild() != null) { temp = (Node)
		 * temp.getLeftChild(); lcounter++; } if (rcounter>lcounter) { return
		 * rcounter+1; }else { return lcounter+1; }
		 */
		return root.getHeight();
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
