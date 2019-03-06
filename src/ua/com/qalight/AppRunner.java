package ua.com.qalight;

import java.util.logging.Logger;

import ua.com.qalight.entity.EntityObj;
import ua.com.qalight.service.FileManagerService;

public class AppRunner {

	private static Logger logger = Logger.getLogger(AppRunner.class.getName());
	
	public static void main(String[] args) {
		
		String catPath = "D:\\test\\cat.jpg";
		String catCopyPath = "D:\\test\\cat_copy.jpg";
			
		FileManagerService.copyFile(catPath, catCopyPath);
		
//		EntityObj entityObj = new EntityObj("text field", 12345, true);
//		FileManagerService.writeObjectToFile(entityObj);
//		
//		EntityObj entityObjFromFile = FileManagerService.readObjectFromFile();
//		logger.info(entityObj.toString());
//		
//		logger.info("The end!");
		
		
	}
}
