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

public class ConferenceSystem {

	public static final String SAVE = "/src/managementsystem.ser";
	public static final String CSV = "/Users/mitchellalpert/Documents/TCSS360/TCSS360/src/UsersWithRolesExport.csv.txt";
	
	public static void main(String[] args) {
		
		ManagementSystem system = new ManagementSystem();
		Path serializedFile = FileSystems.getDefault().getPath(SAVE);
		Path data = FileSystems.getDefault().getPath(CSV);

		try{
			if (Files.notExists(serializedFile)) {
				system = populateSystem(data);
				FileOutputStream out = new FileOutputStream(SAVE);
				ObjectOutputStream obj = new ObjectOutputStream(out);
				obj.writeObject(system);
				obj.close();
			}
			for (User s : system.users){
				System.err.println(s);
			}
			FileInputStream  in = new FileInputStream(SAVE);
			ObjectInputStream obj = new ObjectInputStream(in);
			system = (ManagementSystem) obj.readObject();
			obj.close();
		} catch (IOException | ClassNotFoundException e) {
			System.out.println("There was an error opening data file.");
		}
		
//		LoginPanel login = new LoginPanel();
//		login.display();
	}
	
	public static ManagementSystem populateSystem(Path filePath) {
		int id;
		User currentUser;
		String firstName, lastName, email, conferenceTitle, conferenceDesc;
		
		ManagementSystem system = new ManagementSystem();

		try{
			List<String> csv = Files.readAllLines(filePath, Charset.defaultCharset());
			
			for (String s : csv) {
				System.out.println(s);
				Scanner line = new Scanner(s).useDelimiter(",");
				
				if (line.hasNextInt()) {
					id = line.nextInt();
					firstName = line.next();
					lastName = line.next();
					email = line.next();
					currentUser = new User(id, firstName, lastName, email);
					system.addUser(currentUser);
				/*	if (line.hasNext()) {//has Conference info
						line.next(); //skip conference ID
						conferenceTitle = line.next();
						conferenceDesc = line.next();
						if (!system.hasConference(conferenceTitle)) {
							system.addConference(new Conference("", "", "",null,conferenceTitle ));
						}
						line.next(); //skip RoleID
						switch (line.next()) {
						case "Program Chair" :
							system.getConference(conferenceTitle).setPC(currentUser);
							break;
						case "Reviewer" :
							system.getConference(conferenceTitle).addReviewer(currentUser);
							break;
						case "SubProgram Chair":
							system.getConference(conferenceTitle).setPC(currentUser);
							break;
						default:
							break;			
						}
					}*/
			}			
		}
			
		} catch (IOException e) {
			System.out.println("There was an error opening " + filePath.toString());
		}
		
		return system;
	}
 }
