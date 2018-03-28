package eg.edu.alexu.csd.filestructure.avl.cs09;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class Main {

	public static void main(String[] args) {
		MyAVL avl = new MyAVL();
		System.out.println(avl.height());
		//assertEquals("Invalid height", 0, avl.height());
		avl.insert(123);
		System.out.println(avl.height());

		//assertEquals("Invalid height", 1, avl.height());
		avl.insert(456);
		System.out.println(avl.height());

		//assertEquals("Invalid height", 2, avl.height());
		avl.delete(456);
		System.out.println(avl.height());

		//assertEquals("Invalid height", 1, avl.height());
		avl.delete(123);
		System.out.println(avl.height());

		//assertEquals("Invalid height", 0, avl.height());
		for (int i = 0; i < 1000; i++){
			avl.insert(55);
		}
		System.out.println(avl.height());

		//assertTrue("Invalid height after adding duplicates", avl.height() > 1);
//		int[] input = { 13, 8, 5, 9, 4, 6, 12, 2, 1, 3 };
//
//		for (int i = 0; i < input.length; ++i){
//			x.insert(input[i]);
//			System.out.println(x.getSize());
//			//x.print();
//		}
//		// try deleting non-existing elements
//		for (int i = -1; i >= -5; --i) {
//			assertFalse(x.delete(i));
//		}
//		// check that the tree structure is not affected
//		// delete all existing elements
//		int[] deleteOrder = { 8, 4, 2, 12, 9, 13, 5, 3, 1, 6 };
//		for (int element : deleteOrder) {
//			assertTrue(x.delete(element));
//			System.out.println(x.getSize());
//			//x.print();
//		}
		

		/*	for (int i=10;i>=1;i--){
			x.insert(i);
		}
		x.print();
		System.out.println(x.height());
		System.out.println(x.root.getValue());*/

	}

}