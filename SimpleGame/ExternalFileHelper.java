import java.util.Scanner;
import java.io.IOException;
import java.io.File;

/**
 * ExternalFileHelper lets other classes write to a file.
 * 
 * @author Jason Yuen
 * @version a0.1
 */
public class ExternalFileHelper
{
    
    //private final String folderPath = System.getProperty("user.home")+"\\Desktop\\IsaacSave\\";
    private final String folderPath;
    
    /**
     * Constructor for objects of class ExternalFileHelper
     */
    public ExternalFileHelper()
    {
        // initialize folderPath
        File dir1 = new File(System.getProperty("user.home"));
        File dir2 = new File(dir1, "Desktop");
        dir2.mkdir();
        File dir3 = new File(dir2, "IsaacSave");
        dir3.mkdir();
        folderPath = dir3.getPath();
    }
    
    public Scanner getScanner(String fileName)
    {
        try {
            return new Scanner(folderPath + fileName);
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
