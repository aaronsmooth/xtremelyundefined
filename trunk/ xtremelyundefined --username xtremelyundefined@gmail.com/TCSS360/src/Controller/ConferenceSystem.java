package Controller;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.charset.Charset;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.util.List;
import java.util.Scanner;

import Model.Conference;
import Model.ManagementSystem;
import Model.User;
import View.LoginPanel;
import View.MainFrame;

public class ConferenceSystem {

	public static final String FILE = "src/supportingFiles/managementsystem.ser";
	
	public static void main(String[] args) {
		
		ManagementSystem system = new ManagementSystem();
		Path src = FileSystems.getDefault().getPath(FILE);	

		try{
			FileInputStream  in = new FileInputStream(FILE);
			ObjectInputStream obj = new ObjectInputStream(in);
			system = (ManagementSystem) obj.readObject();
			obj.close();
		} catch (IOException | ClassNotFoundException e) {
			System.out.println("There was an error opening data file.");
		}
		startGUI();
		
	}
	
	public static void startGUI() {
		MainFrame window = new MainFrame();
		LoginPanel login = new LoginPanel();
		login.display();
	}
 }
