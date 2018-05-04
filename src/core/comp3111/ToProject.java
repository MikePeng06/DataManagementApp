package core.comp3111;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

/**
 * The ToProject class of this GUI application
 * It is used for save the whole project (including charts and datasets)
 * They can be saved as a whole file called (*.comp3111) 
 * 
 * After loading a *comp3111 file, you can see the charts and datasets
 * 
 * @author Zeyang Bao
 *
 */


public class ToProject {
//Save Comp3311 Project
	
	public static void SaveProject(DataPack dp, String directory) {
        System.out.print("监听到窗口关闭"); 
        try
        {
           FileOutputStream fileOut = 
           new FileOutputStream(directory);
           ObjectOutputStream out = new ObjectOutputStream(fileOut); 
           out.writeObject(dp);
           out.close(); 
           fileOut.close();
           System.out.printf("Serialized data is saved in /tmp/employee.ser");
           System.out.println(dp.dataTableList);
        }catch(IOException i) 
        {
            i.printStackTrace();
        }
	}

//Load Comp3311 Project
	public static DataPack LoadProject(String str) throws ClassNotFoundException {
		DataPack dp = null;
		try 
	      {
	         FileInputStream fileIn = new FileInputStream(str);
	         ObjectInputStream in = new ObjectInputStream(fileIn);
	         dp = (DataPack) in.readObject(); 
	         in.close();
	         fileIn.close(); 
	      }catch(IOException i)
	      {
	         i.printStackTrace();
	         return null;
	      }
		return dp;
	}
	
}
