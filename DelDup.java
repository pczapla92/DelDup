import java.util.ArrayList;
import java.util.Map;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.Files;

/** Main class */
public class DelDup {
	
	/** Deletes files after user acceptation */
	public static int deleteDuplicates(ArrayList<String> duplicates) {
		int counter=0;
		System.out.println("delete files? y/n");
		String input = System.console().readLine();
		if(input.equals("y")) {		
			for(String duplicate : duplicates) {
				Path path = Paths.get(duplicate);
				try { 
					Files.delete(path);
					counter+=1;
				} 
				catch (Exception e) {
					System.out.println("Cannot delete file: "+ duplicate);
					continue;}
			}
		}	
		else
			return 0;
		System.out.println("Deleted "+counter+" files");
		return 1;
	}
	
	public static void main(String[] args) throws Exception{
		ArrayList<String> fileList=Functions.getFiles(args[0]);
		
		if (fileList.isEmpty())
			System.exit(0);

		Map<String, String> HashValues=Functions.fileHash(fileList);
		
		ArrayList<String> duplicates=Functions.FindDuplicate(HashValues);	

		deleteDuplicates(duplicates);

	}
}
