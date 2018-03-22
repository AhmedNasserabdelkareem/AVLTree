package eg.edu.alexu.csd.filestructure.avl.cs09;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import eg.edu.alexu.csd.filestructure.avl.IDictionary;

public class MyDictionary<T extends Comparable<T>> implements IDictionary {

	MyAVL myOwn = new MyAVL<>();
	@Override
	public void load(File file) {
		try {
			int counter =0;
			String line = "";
            FileReader fileReader = 
                new FileReader(file);
            BufferedReader bufferedReader = 
                new BufferedReader(fileReader);

            
            while((line = bufferedReader.readLine()) != null) {
            	counter++;
            	if (counter ==325) {
            		System.out.println("ahmed");
            	}
            	//System.out.println(line);
            	//System.out.println(myOwn.getSize());
                myOwn.insert(line);
            }   
            bufferedReader.close();         
        }
        catch(FileNotFoundException ex) {             
        }
        catch(IOException ex) {

        }
		
	}

	@Override
	public boolean insert(String word) {
		// TODO Auto-generated method stub
		if (myOwn.search(word)) {
			return false;
		}else {
			myOwn.insert(word);
			return true;
		}
	}

	@Override
	public boolean exists(String word) {
		// TODO Auto-generated method stub
		if (myOwn.search(word)) {
			return true;
		}else {
			return false;
		}		
	}

	@Override
	public boolean delete(String word) {
		// TODO Auto-generated method stub
		return myOwn.delete(word);
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return myOwn.getSize();
	}

	@Override
	public int height() {
		// TODO Auto-generated method stub
		return myOwn.height();
	}

	
}
