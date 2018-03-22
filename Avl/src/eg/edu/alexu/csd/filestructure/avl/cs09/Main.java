package eg.edu.alexu.csd.filestructure.avl.cs09;


public class Main {

	public static void main(String[] args) {
		System.out.println("hi");
		MyAVL x = new MyAVL();

		for (int i=10;i>=1;i--){
			x.insert(i);
		}

		x.print();

	}

}
