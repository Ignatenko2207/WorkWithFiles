package ua.com.qalight.service;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import ua.com.qalight.entity.ConnectionLog;
import ua.com.qalight.util.Randomizer;

class FileManagerServiceTest {

	private static List<ConnectionLog> connectionLogsInFile;
	
	@Test
	void testWriteObjectToFile() {
		
	}

	@Test
	void testReadObjectFromFile() {
		
	}

	@BeforeAll
	public static void saveDataFromFile() {
		connectionLogsInFile = FileManagerService.readConnectionLogsFromFile();
	}
	
	@Test
	void testWriteAndReadConnectionLogInFile() {
		long time = System.currentTimeMillis();
		int sessionId = Randomizer.getNumberInRange(100_000_000, 999_999_999);
		String ip = 
				Randomizer.getNumberInRange(1, 255) + "." +
				Randomizer.getNumberInRange(1, 255) + "." +
				Randomizer.getNumberInRange(1, 255) + "." +
				Randomizer.getNumberInRange(1, 255);
		
		ConnectionLog connectionLog = new ConnectionLog(time, sessionId, ip);
		
		FileManagerService.writeConnectionLogToFile(connectionLog, false);
		
		List<ConnectionLog> connectionLogs = FileManagerService.readConnectionLogsFromFile();
		
		assertTrue(connectionLogs != null);
		assertTrue(connectionLogs.size() == 1);
		
		assertEquals(ip, connectionLogs.get(0).getIp());
	}
	
	@Test
	void testFilterByTime() {
		int sessionId = Randomizer.getNumberInRange(100_000_000, 999_999_999);
		String ip = 
				Randomizer.getNumberInRange(1, 255) + "." +
				Randomizer.getNumberInRange(1, 255) + "." +
				Randomizer.getNumberInRange(1, 255) + "." +
				Randomizer.getNumberInRange(1, 255);
		
		long timeVeryOld = System.currentTimeMillis() - (1000*60*60*24*5);
		ConnectionLog connectionLogVeryOld = new ConnectionLog(timeVeryOld, sessionId, ip);
		
		long timeOld = System.currentTimeMillis() - (1000*60*60*24*3);
		ConnectionLog connectionLogOld = new ConnectionLog(timeOld, sessionId, ip);
		
		long timeNow = System.currentTimeMillis();
		ConnectionLog connectionLogNow = new ConnectionLog(timeNow, sessionId, ip);
		
		FileManagerService.writeConnectionLogToFile(connectionLogVeryOld, false);
		FileManagerService.writeConnectionLogToFile(connectionLogOld, true);
		FileManagerService.writeConnectionLogToFile(connectionLogNow, true);

		List<ConnectionLog> connectionLogs = FileManagerService.readConnectionLogsFromFile();
		assertEquals(3, connectionLogs.size());
		
		FileManagerService.filterConnectionLogFileByTime(4);
		connectionLogs = FileManagerService.readConnectionLogsFromFile();
		assertEquals(2, connectionLogs.size());
		for (ConnectionLog connectionLog : connectionLogs) {
			assertTrue(connectionLog.getTime() != timeVeryOld);
		}
		
		FileManagerService.filterConnectionLogFileByTime(2);
		connectionLogs = FileManagerService.readConnectionLogsFromFile();
		assertEquals(1, connectionLogs.size());
		for (ConnectionLog connectionLog : connectionLogs) {
			assertTrue(connectionLog.getTime() != timeOld);
			assertTrue(connectionLog.getTime() == timeNow);
		}
	}
	
	@AfterAll
	public static void saveDataToFile() {
		if(!connectionLogsInFile.isEmpty()) {
			for (int i = 0; i < connectionLogsInFile.size(); i++) {
				if(i == 0) {
					FileManagerService.writeConnectionLogToFile(connectionLogsInFile.get(i), false);
				}
				else {
					FileManagerService.writeConnectionLogToFile(connectionLogsInFile.get(i), true);
				}
			}
		}
	}
}
