import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

public class Sort {

    static void quickSort(int[] arr, int low, int high){
        if (low < high) { //See if our 2 indexes fo the array got past eachother

            int pi = partition(arr, low, high);// crate a partition based on the indexes

            quickSort(arr, low, pi - 1); //solve recursively the first partision of the array 
            quickSort(arr, pi + 1, high); //solve recursively the second partision of the array 
        }
    }

    static void swap(int[] arr, int i, int j) //makes a swap of 2 elements in a given array
    {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    static int partition(int[] arr, int low, int high)
    {
        int pivot = arr[high]; //chooses the pivot as the element more to the right
        int i = (low - 1); //sets the starting point of the partisioned array
  
        for (int j = low; j <= high - 1; j++) { //for every element in the partioned array
            if (arr[j] > pivot) { //if its bigger than the pivot
                i++; //sets the next element as the far left one
                swap(arr, i, j); //it swaps the element from the far left with the element being ckecked
            }
        }
        swap(arr, i + 1, high); //Swaps pivot with left end of the array
        return (i + 1); //Returns the index of the far left element of the partisioned array
    }

    static void printArray(int[] arr, int size) //Prints the array
    {
        for (int i = 0; i < size; i++)
            System.out.print(arr[i] + " ");
  
        System.out.println();
    }

    static int readNumOfLines(String path){//Reads the number of line hence the number of folders
        int lines = 0;
        BufferedReader reader = null;
        String l;
        try{
            reader = new BufferedReader(new FileReader(new File(path)));
            l = reader.readLine();
            while (l != null) {
                if(l.equals("")){ //in case there are blank lines in the txt file with the folders
                    l = reader.readLine(); //Skip and read the next line
                    continue;
                }
                l = reader.readLine();
                lines++;
            }
        }catch (IOException e) {
            //Inform the user that the File is not readable
            System.out.println("\nError reading file...\n");
        }
        return lines;
        
    }

    static void cleanFile(String path){//cleans the file in the path given
        PrintWriter writer;
        try {
            writer = new PrintWriter(path);
            writer.print("");
            writer.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    static void writeFile(String path, int [] arr){//Writes the array vertically in the document
        try{
            PrintWriter pr = new PrintWriter(path);

            for (int i=0; i<arr.length ; i++)
            {
                pr.print(arr[i] + "\n");
            }
            pr.close();
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("No such file exists.");
        }
    }

    public static void main(String args[]){
        BufferedReader reader = null;
        String l;
        int n = 0;
        try{
            String path = args[0];
            reader = new BufferedReader(new FileReader(new File(path)));
            l = reader.readLine();
            
            int i = 0;
            n = readNumOfLines(path); //gets the number of lines on the document
            int[] nums = new int[n]; //Creates an array the same size as the number of folders

            while (l != null){
                
                nums[i] = Integer.parseInt(l);//Puts the capasity of the folders in the array
                i++;
                l = reader.readLine();
            }
            //printArray(nums, n);
            quickSort(nums, 0, n-1); //Sorts the array
            //printArray(nums, n);
            cleanFile(path); //Cleans the document and prepears it for writing

            writeFile(path, nums); ////Writes the array vertically in the document

        }catch (IOException e) {
            //Inform the user that the File is not readable
            System.out.println("\nError reading file...\n");
        }

        //return n;
    }
}
