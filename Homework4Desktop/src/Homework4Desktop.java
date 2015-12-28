import java.awt.AWTException;
import java.awt.Desktop;
import java.awt.MouseInfo;
import java.awt.PointerInfo;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Date;

import javax.imageio.ImageIO;

/**
 * 
 */

/**
 * @author pzoli
 *
 */
public class Homework4Desktop {

    /**
     * @param args
     * http://docs.oracle.com/javase/tutorial/2d/printing/index.html
     * @throws AWTException 
     * @throws IOException 
     * 
     */
    public static void main(String[] args) throws AWTException, IOException {
        Rectangle screenRect = new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());
        Robot root = new Robot();
        root.mouseMove(screenRect.height / 2 , screenRect.width / 2);        
        root.mouseWheel(5);
        PointerInfo poinfo = MouseInfo.getPointerInfo();        
        log("pointer info: "+poinfo.getLocation());
        String destinationFileName = "c:/temp/persons.xml";
        if (Desktop.isDesktopSupported()) {
            try {
                //Desktop.getDesktop().print(new File(destinationFileName));
                Desktop.getDesktop().open(new File(destinationFileName));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        StringSelection selection = new StringSelection(destinationFileName);
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        clipboard.setContents(selection, selection);
        for(DataFlavor dataFlavor : clipboard.getAvailableDataFlavors()){
            log(dataFlavor.toString());            
        }
                
        BufferedImage capture = root.createScreenCapture(screenRect);
        ImageIO.write(capture, "jpg", new File("c:/temp/screenshot.jpg"));
    }
    
    private static void log(String m) {
        System.out.println(m);
    }

}
