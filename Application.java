import java.util.Scanner;
import java.util.Arrays;
import java.io.File;
import java.io.IOException;

public class Main {

    static Scanner sc = new Scanner(System.in);

    public static void listAllAscending(String directoryPath) {

        /*
         * Utility function to display files from a directory in ascending order
         * 
         * Parameters: directoryPath : location of a directory
         * 
         * Return: Void
         */

        // Creating File object for the given directory
        File directory = new File(directoryPath);

        // If files exist in the directory
        try {
            // Fetching the list of all the files in the directory
            String[] allFiles = directory.list();
            int len = allFiles.length;

            // Sorting file names in ascending order
            Arrays.sort(allFiles);

            // Traversing and Printing File Names to the console
            System.out.println("List of all the files : ");
            for (String file : allFiles) {
                System.out.println(file);
            }
        } catch (NullPointerException nullExcpetion) {
            System.out.println("Directory is empty");
        }
    }

    public static void createFile(String directoryPath){

        /*
         * Utility function to create new file
         * 
         * Parameters: directoryPath : location of a directory
         * 
         * Return: Void
         */

        System.out.print("Enter name of the file : ");
        // Taking input of name of the file from the user
        String fileName = sc.nextLine();
        
        // Initializing object of the file
        File newFile = new File(directoryPath,fileName);

        // Creating directory if doesn't exist
        newFile.getParentFile().mkdirs();

        try{
            

            // Creating a new file
            if(newFile.createNewFile()){
                System.out.println("File created successfully");
            }
            else{
                System.out.println("File already exist");
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    public static void deleteFile(String directoryPath){

        /*
         * Utility function to delete the specific file
         * 
         * Parameters: directoryPath : location of a directory
         * 
         * Return: Void
         */

        // Taking input from the user the name of the file to delete
        System.out.println("Enter name of file to delete : ");
        String fileName = sc.nextLine();

        // Searching whether the file exist or not
        if(searchFile(directoryPath, fileName)){
            try{
                // Initializing object of the file
                File file = new File(directoryPath,fileName);

                // Deleting file from the Application
                if(file.delete()){
                    System.out.println("File Deleted Successfully");
                }
                else{
                    System.out.println("Failed to delete file");
                }
            }
            catch(Exception e){
                e.printStackTrace();
            }
        }
        else{
            System.out.println("File not present in the directory");
        }
    }
    
    public static Boolean searchFile(String directoryPath, String fileName){

        /*
         * Utility function to search for the given file in the directory
         * 
         * Parameters: 
         * directoryPath : location of a directory
         * fileName : name of the file to search in the given directory
         * 
         * Return: Boolean
         */

        File directory = new File(directoryPath);
        try {
            // Fetching the list of all the files in the directory
            String[] allFiles = directory.list();
            
            if(allFiles != null){

                // Traversing and Searching File Names in the directory
                for (String file : allFiles) {
                    if(fileName.equalsIgnoreCase(file)){
                        return true;
                    }
                }
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public static void main(String []args){
        int j=0;
        String menu = "Select Operation\n\n1. Retrieving file names\n2. Business Operations\n3. Close";
        String menu1 = "Select Operation\n\n1. Add file\n2. Delete file\n3. Search file\n4. Return to main menu";
        
        do{
            System.out.println(menu);
            int opt = sc.nextInt();
            sc.nextLine();
            String directoryPath =  System.getProperty("user.dir") + File.separator + "files";
            switch(opt){
                case 1:
                    listAllAscending(directoryPath);
                    break;
                
                case 2:
                    int k = 0;
                    do{
                        System.out.println(menu1);
                        int opt1 = sc.nextInt();
                        sc.nextLine();
                        switch(opt1){
                            case 1:
                                createFile(directoryPath);
                                break;
                            
                            case 2:
                                deleteFile(directoryPath);
                                break;
                            
                            case 3:
                                System.out.println("Enter name of file to search : ");
                                String fileName = sc.nextLine();
                                if(searchFile(directoryPath, fileName)){
                                    System.out.println("File exist in the directory");
                                }
                                else{
                                    System.out.println("File do not exist in the directory");
                                }
                                break;
                            
                            
                            case 4:
                                k=1;
                                continue;
                            
                            
                            default: continue;
                        }
                    }while(k==0);
                    break;
                
                
                case 3:
                    j=1;
                    break;
            
                default: break;
                
        
            }
        }
        while(j==0);
    }
}
