package eg.edu.alexu.csd.filestructure.avl.cs09;


public class Main {

	public static void main(String[] args) {
		MyAVL x = new MyAVL();
		for (int i = 1; i < 1000; ++i)
			x.insert(i);
		System.out.println(x.getTree().getValue());
	/*	for (int i=10;i>=1;i--){
			x.insert(i);
		}

		x.print();
		System.out.println(x.height());
		System.out.println(x.root.getValue());*/

	}

}
