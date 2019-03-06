package ua.com.qalight.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

import ua.com.qalight.entity.ConnectionLog;
import ua.com.qalight.entity.EntityObj;

public class FileManagerService {

	private static final String OBJ_FILE_PATH = 
			System.getProperty("user.dir") + 
			System.getProperty("file.separator") + 
			"files" + 
			System.getProperty("file.separator") + 
			"entity.obj";
	
	private static final String TEXT_FILE_PATH = 
			System.getProperty("user.dir") + 
			System.getProperty("file.separator") + 
			"files" + 
			System.getProperty("file.separator") + 
			"test.txt";
	
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

	public static void writeConnectionLogToFile(ConnectionLog connectionLog, boolean append) {
		try (FileWriter fileWriter = new FileWriter(TEXT_FILE_PATH, append)){
			fileWriter.write(connectionLog.toString() + "\n");
			fileWriter.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static List<ConnectionLog> readConnectionLogsFromFile() {
		List<ConnectionLog> connectionLogs = new ArrayList<ConnectionLog>();

		try (
				FileReader fileReader = new FileReader(TEXT_FILE_PATH);
				BufferedReader bufferedReader = new BufferedReader(fileReader);
		){
			String line = "";
			while((line = bufferedReader.readLine()) != null) {
				String[] words = line.split(" ");
				long time = Long.parseLong(words[0]);
				int sessionId = Integer.parseInt(words[1]);
				String ip = words[2];
				ConnectionLog connectionLog = new ConnectionLog(time, sessionId, ip);
				connectionLogs.add(connectionLog);
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return connectionLogs;
	}

	public static void writeDataToFile(String filePath, byte[] bytes){
		FileOutputStream fos = null;
		try {
			fos = new FileOutputStream(filePath);
			fos.write(bytes);
			fos.flush();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				fos.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public static byte[] readDataFromFile(String filePath) {
		byte[] bytes = null;
		File file = new File(filePath);
		try {
			bytes = Files.readAllBytes(file.toPath());
		} catch (IOException e) {
			e.printStackTrace();
		}
		return bytes;
	}

	public static void copyFile(String fromPath, String toPath) {
		byte[] bytes = readDataFromFile(fromPath);
		writeDataToFile(toPath, bytes);		
	}
	
	public static void filterConnectionLogFileByTime(int days) {
		long marginTime = System.currentTimeMillis() - (1000*60*60*24*days);
		List<ConnectionLog> connectionLogs = readConnectionLogsFromFile();
		boolean append = false;
		for (ConnectionLog connectionLog : connectionLogs) {
			if(connectionLog.getTime() > marginTime) {
				FileManagerService.writeConnectionLogToFile(connectionLog, append);
				append = true;
			}
		}
	}
}
