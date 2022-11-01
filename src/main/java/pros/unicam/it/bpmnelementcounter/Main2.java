package pros.unicam.it.bpmnelementcounter;

import org.w3c.dom.*;
import org.xml.sax.*;
import javax.xml.parsers.*;
import javax.xml.xpath.*;
import java.io.*;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;


public class Main2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String userDir = System.getProperty("user.home");
  		JFileChooser fileChooser = new JFileChooser(userDir +"/Desktop");
		int result = fileChooser.showOpenDialog(fileChooser);
		if (result == JFileChooser.APPROVE_OPTION) {
			fileChooser.setMultiSelectionEnabled(true);
		    File selectedFile = fileChooser.getCurrentDirectory();
		    ElementCounter.countElements(selectedFile);
		}
		
	}

}
