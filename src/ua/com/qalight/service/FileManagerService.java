package ua.com.qalight.service;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;

import ua.com.qalight.entity.EntityObj;

public class FileManagerService {

	private static final String OBJ_FILE_PATH = 
			System.getProperty("user.dir") + 
			System.getProperty("file.separator") + 
			"files" + 
			System.getProperty("file.separator") + 
			"entity.obj";
	
	public static void writeObjectToFile(EntityObj entityObj) {
		
		try (
				FileOutputStream fileOutputStream = new FileOutputStream(OBJ_FILE_PATH);
				ObjectOutputStream objOutputStream = new ObjectOutputStream(fileOutputStream);
			){
			
			objOutputStream.writeObject(entityObj);
			objOutputStream.flush();

		} catch (Exception e) {
			e.printStackTrace();
		} 
	}
	
	public static EntityObj readObjectFromFile() {
		EntityObj entityObj = null;
		try (
				FileInputStream fileInputStream = new FileInputStream(OBJ_FILE_PATH);
				ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
			){
			entityObj = (EntityObj) objectInputStream.readObject();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return entityObj;
	}
	
}
