package eg.edu.alexu.csd.filestructure.avl.cs09;

import eg.edu.alexu.csd.filestructure.avl.IAVLTree;
import eg.edu.alexu.csd.filestructure.avl.INode;

public class MyAVL<T extends Comparable<T>> implements IAVLTree<T> {
	public Node<T> root;

	@Override
	public void insert(T key) {
		Node<T> temp = new Node<T>();
		//boolean heightflag=false;
		temp.setValue(key);
		if (root == null) {
			root = temp;
			return;
		} else {
			Node<T> index = root;
			Node<T> currentParent;
			while (true) {
				currentParent = index;
				if ( key.compareTo(index.getValue())==-1 ) {
					index = (Node<T>) index.getLeftChild();
					if (index == null) {
						currentParent.setLeftChild(temp);
						temp.setParent(currentParent);
						/*if(currentParent.getRightChild()==null)
							heightflag=true;*/
						break;
					}
				} else {
					index = (Node<T>) index.getRightChild();
					if (index == null) {
						currentParent.setRightChild(temp);
						temp.setParent(currentParent);
						/*if(currentParent.getLeftChild()==null)
							heightflag=true;*/
						break;
					}
				}
			}
		}

		
		adjustHeight(temp.getParent());

		balance(temp);

		adjustHeight(temp);
		adjustHeight((Node<T>)temp.getLeftChild());
		adjustHeight((Node<T>)temp.getRightChild());

		// balance method here

	}

	private void adjustHeight(Node<T> node) {
		if(node!=null){
			node.setHeight(getmaxheight((Node<T>)node.getLeftChild(), (Node<T>)node.getRightChild())+1);
			adjustHeight(node.getParent());
		}
		
	}

	
	private void balance(Node<T> temp) {
		if(temp==null)
			return;
		Node<T> parent = temp.getParent();
		if(checkbalance(parent))//balanced
		{
		  balance(parent);	
		}
		else//unbalanced
		{
			int yz=0;
			int xy=0;
			if(temp.getLeftChild()==null)
				xy=2;
			else if(temp.getRightChild()==null)
				xy=1;
			else if(((Node<T>)temp.getLeftChild()).getHeight()>((Node<T>)temp.getRightChild()).getHeight())
			{
				xy=1;//x is to the left of y
			}
			else
				xy=2;//x is to the right of y
			if(parent.getLeftChild()!=null&&parent.getLeftChild().equals(temp))
			{
				yz=1; //y is to the left of z
			}
			else
			{
				yz=2; //y is to the right of z
			}
			//System.out.println("yz="+yz+" xy="+xy);
			rotate(temp,xy,yz);
		}

	}

	

	private void rotate(Node<T> temp, int xy, int yz) {

		if(xy==1&&yz==1)
			rightRotate(temp);
		if(xy==2&&yz==2)
			leftRotate(temp);
		if(xy==2&&yz==1)
			leftrightRotate(temp);
		if(xy==1&&yz==2)
			rightleftRotate(temp);
		
		
	}

	private void rightleftRotate(Node<T> y) {
		Node<T> x=(Node<T>)y.getLeftChild();
		Node<T> t2=(Node<T>)x.getLeftChild();
		Node<T> t3=(Node<T>)x.getRightChild();
		Node<T> t4=(Node<T>)y.getRightChild();
		
		Node<T> temp= new Node<T>();
		temp.setLeftChild(t3);
		temp.setRightChild(t4);
		temp.setValue(y.getValue());
		temp.setParent(y);
		if(t3!=null)t3.setParent(temp);
		if(t4!=null)t4.setParent(temp);
		
		y.setRightChild(temp);
		y.setValue(x.getValue());
		y.setLeftChild(t2);
		if(t2!=null)t2.setParent(y);
		leftRotate(y);

	}

	private void leftrightRotate(Node<T> y) {
		Node<T> x=(Node<T>)y.getRightChild();
		Node<T> t1=(Node<T>)y.getLeftChild();
		Node<T> t2=(Node<T>)x.getLeftChild();
		Node<T> t3=(Node<T>)x.getRightChild();
		
		Node<T> temp= new Node<T>();
		temp.setLeftChild(t1);
		temp.setRightChild(t2);
		temp.setValue(y.getValue());
		temp.setParent(y);
		if(t1!=null)t1.setParent(temp);
		if(t2!=null)t2.setParent(temp);
		
		y.setLeftChild(temp);
		y.setValue(x.getValue());
		y.setRightChild(t3);
		if(t3!=null)t3.setParent(y);
		
		rightRotate(y);
		
	}

	private void rightRotate(Node<T> y) {
		Node<T> z= y.getParent();
		Node<T> x=(Node<T>)y.getLeftChild();
		
		
		Node<T> t1=(Node<T>)x.getLeftChild();
		Node<T> t2=(Node<T>)x.getRightChild();
		Node<T> t3=(Node<T>)y.getRightChild();
		Node<T> t4=(Node<T>)z.getRightChild();
		
		Node<T> temp= new Node<T>();
		temp.setRightChild(t4);
		temp.setLeftChild(t3);
		if(t4!=null)t4.setParent(temp);
		if(t3!=null)t3.setParent(temp);
		temp.setParent(z);
		z.setRightChild(temp);
		
		temp.setValue(z.getValue());
		z.setValue(y.getValue());
		y.setValue(x.getValue());
		y.setLeftChild(t1);
		y.setRightChild(t2);
		if(t1!=null)t1.setParent(y);
		if(t2!=null)t2.setParent(y);
		
		/*y.setHeight(getmaxheight(t3,t4)+1);
		temp.setHeight(getmaxheight(t1, t2)+1);
		int zheight=z.getHeight();
		z.setHeight(getmaxheight(y, temp));
		int difference=z.getHeight()-zheight;
		Node<T> parent=z.getParent();
		while(parent!=null)
		{
			parent.setHeight(parent.getHeight()+difference);
			parent=parent.getParent();
		}*/
		
	}
	
	
	private void leftRotate(Node<T> y) {
		
		Node<T> z= y.getParent();
		Node<T> x=(Node<T>)y.getRightChild();
		
		
		Node<T> t1=(Node<T>)z.getLeftChild();
		Node<T> t2=(Node<T>)y.getLeftChild();
		Node<T> t3=(Node<T>)x.getLeftChild();
		Node<T> t4=(Node<T>)x.getRightChild();
		//System.out.println();
		Node<T> temp= new Node<T>();
		
		temp.setValue(z.getValue());
		temp.setLeftChild(t1);
		temp.setRightChild(t2);
		if(t1!=null) t1.setParent(temp);
		if(t2!=null) t2.setParent(temp);
		z.setLeftChild(temp);
		
		z.setValue(y.getValue());
		y.setValue(x.getValue());
		y.setLeftChild(t3);
		y.setRightChild(t4);
		if(t3!=null)t3.setParent(y);
		if(t4!=null)t4.setParent(y);
		//print();
		/*y.setHeight(getmaxheight(t1,t2)+1);
		temp.setHeight(getmaxheight(t3, t4)+1);
		int zheight=z.getHeight();
		z.setHeight(getmaxheight(y, temp));
		int difference=z.getHeight()-zheight;
		Node<T> parent=z.getParent();
		while(parent!=null)
		{
			parent.setHeight(parent.getHeight()+difference);
			parent=parent.getParent();
		}*/
		
		
		
	}
	private int getmaxheight(Node<T> t1, Node<T> t2) {
		if(t1==null&&t2==null)
			return -1;
		if(t1==null)
			return t2.getHeight();
		if(t2==null)
			return t1.getHeight();
		if(t1.getHeight()>t2.getHeight())
			return t1.getHeight();
		else
			return t2.getHeight();
		
	}

	private boolean checkbalance(Node<T> parent) {
		if (parent == null)
			return true;//reached the root and balanced
		int l=0,r=0;
		Node<T> left=(Node<T>)parent.getLeftChild();
		Node<T> right=(Node<T>)parent.getRightChild();
		
		if(left!=null)
			l=left.getHeight()+1;
		if(right!=null)
			r=right.getHeight()+1;
		
		if (Math.abs(l-r)>1)//the left is unbalanced
			{
				//System.out.println(parent.getValue()+"   "+l+" "+r);
			//System.out.println(((Node<Integer>)root.getLeftChild().getRightChild()).getValue());
				return false;
			}
		return true;//balanced

	}

	@Override
	public boolean delete(T key) {
	return false;
	}

	@Override
	public boolean search(T key) {
		Node<T> temp=root;
		while(temp!=null)
		{
			if(temp.getValue()==key)
				return true;
			if(temp.getValue().compareTo(key)>0)
				temp=(Node<T>)temp.getLeftChild();
			else
				temp=(Node<T>)temp.getRightChild();
		}
		return false;
		}

	@Override
	public int height() {
 
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
 
		return root;
	}

	public void print() {
		System.out.println("\nIn Order ");
		printInorder(root);
		System.out.println();
	/*	System.out.println("Pre Order ");
		printPreorder(root);
		System.out.println();
		System.out.println("Post Order ");
		printPostorder(root);*/
		
	}

	
	public void printPreorder(Node<T> x) {
		if (x != null) {
			printPreorder((Node<T>) x.getLeftChild());
			System.out.print(x.getValue()+" ");
			printPreorder((Node<T>) x.getLeftChild());
		}
	}

	public void printInorder(Node<T> x) {
		if (x != null) {
			System.out.print(x.getValue()+"  ");
			printInorder((Node<T>) x.getLeftChild());
			printInorder((Node<T>) x.getRightChild());
		}
	}

	public void printPostorder(Node<T> x) {
		if (x != null) {
			printPostorder((Node<T>) x.getLeftChild());
			printPostorder((Node<T>) x.getRightChild());
			System.out.print(x.getValue()+" ");

		}
	}

}
