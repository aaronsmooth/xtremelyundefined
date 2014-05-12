package Controller;

import java.io.FileInputStream;
import java.io.ObjectInputStream;

public class ConferenceSystem {

	public void main(String[] args){
		
		ManagementSystem system;
		
		FileInputStream  file = new FileInputStream("managementsystem.ser");
		ObjectInputStream obj = new ObjectInputStream(file);
		(ManagementSystem) system = obj.readObject();
	}
 }
