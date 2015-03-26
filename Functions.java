import java.io.File;
import java.util.ArrayList;
import java.util.*;

/** Class containing all the methods except for one creating md5 hash */
public class Functions {
	
	private static Map<String, String> dictionary;
	private static ArrayList<String> uniques;
	private static ArrayList<String> duplicates;

	/** Creates an ArrayList<String> containing all the paths to files in directory and subdirectories */
	public static ArrayList<String> getFiles(String directory) {
		ArrayList<String> fileList = new ArrayList<String>();
		File folder = new File(directory);
		File[] files = folder.listFiles();
		for (File file : files) {
			if(file.isFile()) {
				fileList.add(file.getAbsolutePath());
			}
			else if(file.isDirectory()) {
				fileList.addAll(getFiles(file.getAbsolutePath()));
			}		
		}
	
				
		return fileList;
		}
	
	/** Returns Maps where K is path and V is md5 hash */
	public static Map<String, String> fileHash(ArrayList<String> fileList) throws Exception {
		dictionary = new HashMap<String,String>();
		
		for (String path : fileList) {
			dictionary.put(path, MD5Checksum.getMD5Checksum(path));
			}
		return dictionary;	
		}
	
	/** Looks for duplicates in given Map, returns ArrayList<String> with path to duplicate files */
	public static ArrayList<String> FindDuplicate(Map<String, String> dictionary) {
		uniques = new ArrayList<String>();
		duplicates = new ArrayList<String>();
			
			for (String key : dictionary.keySet()) {
				String hashValue=dictionary.get(key);
				if (uniques.contains(hashValue)) {
					duplicates.add(key);
				}
				else
					uniques.add(hashValue);
				}	
		return duplicates;
	}
	
	
}

