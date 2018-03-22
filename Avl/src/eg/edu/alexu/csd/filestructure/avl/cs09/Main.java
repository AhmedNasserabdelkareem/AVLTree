package eg.edu.alexu.csd.filestructure.avl.cs09;

import static org.junit.Assert.assertEquals;

public class Main {

	public static void main(String[] args) {
		MyAVL x = new MyAVL();
		x.insert(123);
		System.out.println(x.height()+"");
		x.print();
		x.insert(456);
		System.out.println(x.height()+"");
		x.print();

		x.delete(456);
		System.out.println(x.height()+"");
		x.print();

		x.delete(123);
		System.out.println(x.height()+"");
		x.print();

		for (int i = 0; i < 1000; i++)
			x.insert(55);
		System.out.println(x.height()+"");

		x.print();

		/*	for (int i=10;i>=1;i--){
			x.insert(i);
		}

		x.print();
		System.out.println(x.height());
		System.out.println(x.root.getValue());*/

	}

}
