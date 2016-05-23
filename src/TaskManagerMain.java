import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
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
import net.fortuna.ical4j.model.component.*;

public class TaskManagerMain {
	private static ArrayList<VToDo> tasks = new ArrayList<VToDo>();
	private static ArrayList<Component> otherComponents = new ArrayList<Component>();
	private static Calendar mainCalendar;
	private static String filePath;

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
		//Strip out otherComponents into .taskconfig file?
		if(filePath.equals("EMPTY")){
			JFileChooser open = new JFileChooser();
			File f = null;
			int result = open.showOpenDialog(null);
			if (result == JFileChooser.APPROVE_OPTION) {
				openMasterFile(open.getSelectedFile().getAbsolutePath());
			}
		}
		else{
			openMasterFile();
		}
		
		
		NewEventGui neg = new NewEventGui(); 
		
	}
	
	private static void readConfig(){
		try {
			BufferedReader configReader = new BufferedReader(new FileReader (new File(".taskconfig")));
			String temp = configReader.readLine();
			while(temp != null){
				String[] tokens = temp.split(":");
				if(tokens[0].equals("FILEPATH")){
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
	
	//for use when a new master file was opened
	private static void openMasterFile(String newPath)
	{
		filePath = newPath;
		dumpToConfigFile();
		openMasterFile();
	}
	
	private static void openMasterFile()
	{
		// have the gui open a jfilechooser then call this
		mainCalendar = FileData.readFile(new File(filePath));
		for (Iterator i = mainCalendar.getComponents().iterator(); i.hasNext();) {
				Component component = (Component) i.next();
				//System.out.println("Component [" + component.getName() + "]");
				if(component.getName().equals("VToDo")){
				    tasks.add((VToDo)component);
				}
				else{
				    otherComponents.add(component);
				}
				for (Iterator j = component.getProperties().iterator(); j.hasNext();) {
				    Property property = (Property) j.next();
			        //System.out.println("Property [" + property.getName() + ", " + property.getValue() + "]");
				}
		}
	}
	
	private static void dumpToConfigFile()
	{
		try {
			BufferedWriter confWriter = new BufferedWriter(new FileWriter(new File(".taskconfig")));
			confWriter.write("FILEPATH:"+filePath);
			confWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private static void importIntoMaster(String inFilePath){
		if(!(new File(filePath).exists())){
			JOptionPane.showMessageDialog(null, "No current master exists to import into", "Error", JOptionPane.ERROR_MESSAGE);
			return;
		}
		//add to task list, maybe otherComponents?
		Calendar tempCalendar = FileData.readFile(new File(inFilePath));
		for (Iterator i = tempCalendar.getComponents().iterator(); i.hasNext();) {
		    Component component = (Component) i.next();
		    if(component.getName().equals("VToDo")){
		    	tasks.add((VToDo)component);
		    }
		    //else{
		    //	otherComponents.add(component);
			//}
		}
		mainCalendar.getComponents().clear();
		for(Component c:otherComponents){
			if(c.getName().equals("VAvailability")){
		    	mainCalendar.getComponents().add((VAvailability)c);
		    }
			else if(c.getName().equals("VAlarm")){
		    	mainCalendar.getComponents().add((VAlarm)c);
		    }
			else if(c.getName().equals("VEvent")){
		    	mainCalendar.getComponents().add((VEvent)c);
		    }
			else if(c.getName().equals("VFreeBusy")){
		    	mainCalendar.getComponents().add((VFreeBusy)c);
		    }
			else if(c.getName().equals("VJournal")){
		    	mainCalendar.getComponents().add((VJournal)c);
		    }
			else if(c.getName().equals("VTimeZone")){
		    	mainCalendar.getComponents().add((VTimeZone)c);
		    }
			else if(c.getName().equals("VVenue")){
		    	mainCalendar.getComponents().add((VVenue)c);
		    }
		}
		mainCalendar.getComponents().addAll(tasks);
		FileData.writeFile(mainCalendar, new File(filePath));
	}

}
