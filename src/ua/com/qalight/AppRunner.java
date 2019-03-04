package ua.com.qalight;

import java.util.logging.Logger;

import ua.com.qalight.entity.EntityObj;
import ua.com.qalight.service.FileManagerService;

public class AppRunner {

	private static Logger logger = Logger.getLogger(AppRunner.class.getName());
	
	public static void main(String[] args) {
		
		EntityObj entityObj = new EntityObj("text field", 12345, true);
		FileManagerService.writeObjectToFile(entityObj);
		
		EntityObj entityObjFromFile = FileManagerService.readObjectFromFile();
		logger.info(entityObj.toString());
		
		logger.info("The end!");
	}
}
