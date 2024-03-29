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

import javax.swing.JComboBox;
import javax.swing.JOptionPane;

import Model.Conference;
import Model.ManagementSystem;
import Model.Paper;
import Model.User;
import View.LoginPanel;
import View.MainFrame;
import View.SelectBox;
import View.SubmitRecommendation;
import View.SubmitReview;

/**
 * This is an application for maintaining and organizing papers for educational
 * conferences. Tracks members of the conference as well as their responsibilities
 * as far as completing reviews, recommendations, etc. 
 * 
 * This class loads the serialized information from the binary file and starts the GUI.
 * @author Mitchell Alpert
 * @version 5/20/14
 *
 */
public class ConferenceSystem implements Runnable{
        
        public static final String FILE = "src/supportingFiles/managementsystem.ser";
        
       /**
        * Starts the GUI.
        * 
        * @param args not used
        */
        public static void main(String[] args) {
                
                ManagementSystem system = deSeriealize();
                Thread thd = new Thread(new MainFrame(system));
                thd.start();   
        }
        
        /**
         * Loads the serialized data from the given file path
         * 
         * @return ManagementSystem the saved system
         */
        public static ManagementSystem deSeriealize() {
        	ManagementSystem system = null;

        	try{
        		FileInputStream  in = new FileInputStream(FILE);
        		ObjectInputStream obj = new ObjectInputStream(in);
        		system = (ManagementSystem) obj.readObject();
        		obj.close();
        	} catch (IOException | ClassNotFoundException e) {
        		System.out.println(e);
        	}
        	return system;
        }
        
        /**
         * {@inheritDoc}
         */
		@Override
		public void run() {
			new Thread(new ConferenceSystem()).start();
	
		}
 }