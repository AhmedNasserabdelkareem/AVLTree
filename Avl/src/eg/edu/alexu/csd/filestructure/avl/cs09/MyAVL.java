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
		
		if(x==null)
			return;
		
		
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
		if(x==null)
			return;
		
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
	        root = (Node<T>) deleteNode(root, key);
	        size--;
			return true;
		}else {
			return false;
		}
	}
    int getBalance(Node node) {
        if (node == null)
            return 0;
        int l = 0;
        int r = 0;
        if (node.getLeftChild() != null) {
            l = ((Node) (node.getLeftChild())).getHeight() + 1;
        }
        if (node.getRightChild() != null) {
            r = ((Node) (node.getRightChild())).getHeight() + 1;
        }
        return l - r;
    }
	@Override
	public boolean search(T key) {
		Node<T> temp=root;
		while(temp!=null)
		{
			if(temp.getValue().compareTo(key)==0)
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
		if (root==null){
			return 0;
		}
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
	



	public int getSize() {
		// TODO Auto-generated method stub
		return size;
	}
	 private Node balanceDeletion(Node node) {

	        int l = -1;
	        int r = -1;
	        if (node.getLeftChild() != null) {
	            l = ((Node) (node.getLeftChild())).getHeight();
	        }
	        if (node.getRightChild() != null) {
	            r = ((Node) (node.getRightChild())).getHeight();
	        }
	        int balance = l - r;

	        if (balance < -1
	                && getBalance((Node) node.getRightChild()) <= 0) {
	            return rotateLeft(node);

	        } else if (balance < -1
	                && getBalance((Node) node.getRightChild()) > 0) {
	            node.setRightChild(rotateRight((Node) node.getRightChild()));
	            return rotateLeft(node);

	        } else if (balance > 1
	                && getBalance((Node) node.getLeftChild()) < 0) {
	            node.setLeftChild(rotateLeft((Node) node.getLeftChild()));
	            return rotateRight(node);

	        } else if (balance > 1
	                && getBalance((Node) node.getLeftChild()) >= 0) {
	            return rotateRight(node);
	        }
	        return null;
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
	   }
	private Node rotateRight(Node node) {
        Node x = (Node) node.getLeftChild();
        Node t = (Node) x.getRightChild();

        node.setLeftChild(t);
        x.setRightChild(node);

        node.setHeight(MaxHeight(node) + 1);
        x.setHeight(MaxHeight(x) + 1);
        return x;
    }

    private Node rotateLeft(Node node) {
        Node x = (Node) node.getRightChild();
        Node t = (Node) x.getLeftChild();

        node.setRightChild(t);
        x.setLeftChild(node);

        node.setHeight(MaxHeight(node) + 1);
        x.setHeight(MaxHeight(x) + 1);

        return x;
    }
	 private Node deleteNode(Node node, Comparable key) {
	        if (node == null) {
	            return node;
	        } else if (node.getValue().compareTo(key) > 0) {
	            node.setLeftChild(deleteNode((Node) node.getLeftChild(), key));
	        } else if (node.getValue().compareTo(key) < 0) {
	            node.setRightChild(deleteNode((Node) node.getRightChild(), key));
	        } else {
	            if (node.getLeftChild() == null && node.getRightChild() == null) {
	                node = null;
	                return node;
	            } else if (node.getLeftChild() == null) {
	                node = (Node) node.getRightChild();
	                return node;
	            } else if (node.getRightChild() == null) {
	                node = (Node) node.getLeftChild();
	                return node;
	            } else {
	                Node temp = minValueNode((Node) node.getRightChild());
	                node.setValue(temp.getValue());
	                node.setRightChild(deleteNode((Node) node.getRightChild(),
	                        temp.getValue()));
	            }
	        }
	        node.setHeight(MaxHeight(node) + 1);
	        Node temp = balanceDeletion(node);
	        if (temp != null) {
	            node = temp;
	        }
	        return node;
	    }
	    private int MaxHeight(Node node) {
	        int l = -1;
	        int r = -1;
	        if (node.getLeftChild() != null) {
	            l = ((Node) (node.getLeftChild())).getHeight();
	        }
	        if (node.getRightChild() != null) {
	            r = ((Node) (node.getRightChild())).getHeight();
	        }
	        return Math.max(l, r);
	    }
}