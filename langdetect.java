/**
 * 
 */
/**
 * @author Saumya Shandilya
 *
 */
package test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.cybozu.labs.langdetect.Detector;
import com.cybozu.labs.langdetect.DetectorFactory;
import com.cybozu.labs.langdetect.LangDetectException;
import com.cybozu.labs.langdetect.Language;

@Path("/lang") 
public class langdetect {
	
	public void init(String profileDirectory) throws LangDetectException {
        DetectorFactory.loadProfile(profileDirectory);
    }
    public String detect(String text) throws LangDetectException {
        Detector detector = DetectorFactory.create();
        detector.append(text);
        return detector.detect();
    }
    public ArrayList<Language> detectLangs(String text) throws LangDetectException {
        Detector detector = DetectorFactory.create();
        detector.append(text);
        return detector.getProbabilities();
    }
    
    
    
	public String readtextFile()
    {
    	File file = new File("C:\\Users\\sashandi\\eclipse-workspace\\languagedetectionproject\\src\\test\\input_text_file\\langdetect_text.txt");
        FileInputStream fis = null;
        InputStreamReader is = null;
        BufferedReader in = null;
        String inputText="";
     
        try {
          fis = new FileInputStream(file);
     
          is = new InputStreamReader(fis,"UTF8");
          in = new BufferedReader(is);
  
          inputText+=in.readLine();
        
          
        } catch (FileNotFoundException e) {
          e.printStackTrace();
        } catch (IOException e) {
          e.printStackTrace();
        }	
        return inputText;
    }
    
	
	
    @GET
    @Produces({MediaType.TEXT_HTML,MediaType.TEXT_XML,MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
    public String checkLang() throws LangDetectException, IOException
    {
    	init("C:\\Users\\sashandi\\Desktop\\profiles");
    	String input=readtextFile();
    	String response=detect(input);
		String result="<html><head><title>Language detection from text</title></head><body><h1><center>Language detection from text<br><br><br><i>The language detected is : "+response+"</i></h1></center></body></html>";
    	return result;
    }
}
