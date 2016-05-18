import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import com.sun.jmx.snmp.tasks.TaskServer;

import net.fortuna.ical4j.model.Calendar;
import net.fortuna.ical4j.model.Component;
import net.fortuna.ical4j.model.Property;
import net.fortuna.ical4j.model.component.VToDo;

public class TaskManagerMain {
	private static ArrayList<VToDo> tasks = new ArrayList<VToDo>();
	private static ArrayList<Component> otherComponents = new ArrayList<Component>();
	private static Calendar mainCalendar;
	private static String filePath;
	private static String username;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
	    try {
		            // Set System L&F
		        UIManager.setLookAndFeel(
		            UIManager.getSystemLookAndFeelClassName());
		    } 
		    catch (UnsupportedLookAndFeelException e) {
		       // handle exception
		    }
		    catch (ClassNotFoundException e) {
		       // handle exception
		    }
		    catch (InstantiationException e) {
		       // handle exception
		    }
		    catch (IllegalAccessException e) {
		       // handle exception
		    }
		readConfig();
		System.out.println(username);
		System.out.println(filePath);
		//Strip out otherComponents into .taskconfig file?
		JFileChooser open = new JFileChooser();
		File f = null;
		int result = open.showOpenDialog(null);
		if (result == JFileChooser.APPROVE_OPTION) {
		    f = open.getSelectedFile();
		    
		}

		mainCalendar = FileData.readFile(f);
		for (Iterator i = mainCalendar.getComponents().iterator(); i.hasNext();) {
		    Component component = (Component) i.next();
		    System.out.println("Component [" + component.getName() + "]");
		    if(component.getName().equals("VToDo")){
		    	tasks.add((VToDo)component);
		    }
		    else{
		    	otherComponents.add(component);
		    }
		    for (Iterator j = component.getProperties().iterator(); j.hasNext();) {
		        Property property = (Property) j.next();
		        System.out.println("Property [" + property.getName() + ", " + property.getValue() + "]");
		    }
		}
		
		NewEventGui neg = new NewEventGui(); 
		
	}
	
	private static void readConfig(){
		try {
			BufferedReader configReader = new BufferedReader(new FileReader (new File(".taskconfig")));
			String temp = configReader.readLine();
			while(temp != null){
				String[] tokens = temp.split(":");
				if(tokens[0].equals("USERNAME")){
					username = tokens[1];
				}
				else if(tokens[0].equals("FILEPATH")){
					filePath = tokens[1];
				}
				temp = configReader.readLine();
			}
			configReader.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
