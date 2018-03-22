package eg.edu.alexu.csd.filestructure.avl.cs09;

import eg.edu.alexu.csd.filestructure.avl.IAVLTree;
import eg.edu.alexu.csd.filestructure.avl.INode;

public class MyAVL<T extends Comparable<T>> implements IAVLTree<T> {
	public Node<T> root;
	private int size=0;

	@Override
	public void insert(T key) {
		if (!search(key)) {
		Node<T> temp = new Node<T>();
		//boolean heightflag=false;
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
		size++;
		}
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
		return checkBalanceAux(parent) != -1;
	}

	@Override
	public boolean delete(T key) {
		if (search(key)) {
			searchNode(key);			
			//balance(temp.getParent());
			return true;
		}else {
			return false;
		}
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

	public Node searchNode(T key) {
		Node<T> temp=root;
		while(temp!=null)
		{
			if(temp.getValue()==key) {				
				return temp;
		}
			if(temp.getValue().compareTo(key)>0)
				temp=(Node<T>)temp.getLeftChild();
			else
				temp=(Node<T>)temp.getRightChild();
		}
		return null;
		}
	@Override
	public int height() {
		return root.getHeight()+1;
	}

	@Override
	public INode<T> getTree() {
 
		return root;
	}

	public void print() {

		printInorder(root);
		System.out.println();
	}

	
	public void printInorder(Node<T> x) {
		if (x != null) {
			System.out.print(x.getValue()+"  ");
			printInorder((Node<T>) x.getLeftChild());
			printInorder((Node<T>) x.getRightChild());
		}
	}

	private int checkBalanceAux(INode<T> node) {
		if (node == null)
			return 0;
		int left = checkBalanceAux(node.getLeftChild());
		int right = checkBalanceAux(node.getRightChild());
		if (left == -1 || right == -1)
			return -1;
		if (Math.abs(left - right) > 1) {
			return -1;
		}
		return 1 + Math.max(left, right);
	}
	/* public Node deleteNode(Node root, T key)
	    {
	        if (root == null)
	            return root;
	        if (key.compareTo((T) root.getValue()) == -1)
	            root.setLeftChild(deleteNode((Node) root.getLeftChild(), key));
	        else if (key.compareTo((T) root.getValue()) == 1)
				root.setRightChild(deleteNode((Node) root.getRightChild(), key));
	        else
	        {
	 
	            if ((root.getLeftChild()== null) || (root.getRightChild() == null))
	            {
	                Node temp = null;
	                if (temp == root.getLeftChild())
	                    temp = (Node) root.getRightChild();
	                else
	                    temp = (Node) root.getLeftChild();	 
	                if (temp == null)
	                {
	                    temp = root;
	                    root = null;
	                }
	                else   
	                    root = temp; 
	            }
	            else
	            {
	                Node temp = minValueNode((Node) root.getRightChild());	 
	                root.setValue(temp.getValue());	 
	                root.setRightChild(deleteNode((Node) root.getRightChild(), (T) temp.getValue()));
	            }
	        }	 
	        if (root == null)
	            return root;	 
	        root.setHeight(Math.max(((Node) root.getLeftChild()).getHeight(),((Node) root.getRightChild()).getHeight()) + 1);
	 
	        // STEP 3: GET THE BALANCE FACTOR OF THIS NODE (to check whether
	        //  this node became unbalanced)
	        boolean balance = checkbalance(root);
	 
	        // Left Left Case
	        if (balance  && calculateBalance((Node) root.getLeftChild()) >= 0)
	            rightRotate(root);
	 
	        // Left Right Case
	        if (balance && calculateBalance((Node) root.getLeftChild()) < 0)
	        {
	            leftrightRotate(root);
	        }
	 
	        // Right Right Case
	        if (balance && calculateBalance((Node) root.getRightChild()) <= 0)
	            leftRotate(root);
	 
	        // Right Left Case
	        if (balance  && calculateBalance((Node) root.getRightChild()) > 0)
	        {
	            rightleftRotate(root);
	        }
	 
	        return root;
	    }
	   private  Node minValueNode(Node node) {
	        Node current = node;	 
	        while (current.getLeftChild() != null)
	           current = (Node) current.getLeftChild();
	        return current;
	    }
	   private int calculateBalance (Node node) {
		   if (node == null)
			   return 0;
		   return ((Node<T>) node.getLeftChild()).getHeight()-((Node<T>) node.getRightChild()).getHeight();
	   }*/

	public int getSize() {
		// TODO Auto-generated method stub
		return size;
	}
}