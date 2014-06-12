package Controller;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.nio.charset.Charset;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import Model.Conference;
import Model.ManagementSystem;
import Model.User;

/**
 * This class takes the specially formatted CSV file and initially populates
 * a ManagementSystem with conferences and Users and then serializes it.
 * 
 * @author Mitchell Alpert
 * @author Modified by - Aaron Nelson
 *
 */
public class Startup {

	/**
	 * Path for source file
	 */
	public static final String src = "src/supportingFiles/UsersWithRolesExport.csv.txt";
	
	/**
	 * Path for dest file
	 */
	public static final String dest = "src/supportingFiles/managementsystem.ser";

	public static void main(String[] args) {

		Path data = FileSystems.getDefault().getPath(src);

		ManagementSystem system = populateSystem(data);
		
		try{
			FileOutputStream out = new FileOutputStream(dest);
			ObjectOutputStream obj = new ObjectOutputStream(out);
			obj.writeObject(system);
			obj.close();
		} catch (IOException e) {
			System.out.println(e);
		}
	}
	
	public static ManagementSystem populateSystem(Path filePath) {
		int id;
		User currentUser;
		String firstName, lastName, email, confTitle, confDesc;
		Conference conf = null;
		
		ManagementSystem system = new ManagementSystem();

		try{
			List<String> csv = Files.readAllLines(filePath, Charset.defaultCharset());

			for (String str : csv) {
				//thanks SO 1757065
				String[] line = str.split(",(?=([^\"]*\"[^\"]*\")*[^\"]*$)");
				for (int i = 0; i < line.length; i++) {
					line[i] = line[i].replaceAll("\"", "");
				}
				try{
					id = Integer.valueOf(line[0]);
					if (!system.hasUser(id)) {
						firstName = line[1];
						lastName = line[2];
						email = line[3];
						currentUser = new User(id, firstName, lastName, email);
						system.addUser(currentUser);
					} else {
						currentUser = system.getUser(id);
					}
					if (line.length > 4) { //has a conference association
						confTitle = line[5];

						if (!system.hasConference(confTitle)){
							conf = new Conference("","","",null, confTitle);
							system.addConference(conf);
						} else {
							conf = system.getConference(confTitle);
						}
						switch (line[8]) {
						case "Program Chair" :
							conf.setPC(currentUser);
							break;
						case "Reviewer" :
							conf.addReviewer(currentUser);
							break;
						case "SubProgram Chair":
							conf.addReviewer(currentUser); //business rule must be reviewer to be spc
							conf.addSPC(currentUser);
							break;
						default:
							break;			
						}
					}
				} catch (NumberFormatException e) {
					//do nothing and go to next line
				}
			}			
		} catch (IOException e) {
			System.out.println("There was an error opening " + filePath.toString());
		}

		return system;
	}
}

