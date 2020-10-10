package wtf.ungarscool1.epitechintratoken;

import java.io.File;

import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;

public class DetectText {
	public static String getImgText(String imageLocation) {
	      Tesseract instance = new Tesseract();
	      File img = new File(imageLocation);

	      instance.setDatapath(Main.config.TesseractData);
	      instance.setLanguage("fra");
	      try {
	    	  String imgText = instance.doOCR(img);
	    	  return imgText;
	      } catch (TesseractException e) {
	    	  e.printStackTrace();
	    	  return "Error while reading image";
	      }
	}
}
