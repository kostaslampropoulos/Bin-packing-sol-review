import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;

public class Differentiate {

    static void cleanFile(String path){ //Cleans the file
        PrintWriter writer;
        try {
            writer = new PrintWriter(path);
            writer.print("");
            writer.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    static void writeFile(String fileName, int id, int[] arr) { //Writes in the file on the given path the results vertically
        PrintWriter writer;
        String path = "data/" + fileName;
        try{
            writer = new PrintWriter(path);
            for (int i=0; i<arr.length ; i++)
            {
                writer.print(arr[i] + "\n");
            }
            writer.close();
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("No such file exists.");
        }
    }

    public static void main(String[] args) {
        String fileName;
        Random rand_N = new Random();
        Random rand_f = new Random();
        int N;
        int f;
        int id = 0;
        int files_to_create = 100; //total number of files to be created for each N
        for (int i = 0; i<3;i++){ //there are 3 types of folders
            N = rand_N.nextInt(1000); //array for storing the folders in each .txt file
            int [] nums =  new int[N]; 
            for(int j = 0; j<files_to_create; j++){
                fileName = "test" + Integer.toString(id) + ".txt";
                File file = new File("data/" + fileName);
                file.getParentFile().mkdirs(); 
                try {
                    file.createNewFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                id++;
                for(int k = 0; k<N; k++){
                    f = rand_f.nextInt(1000000);
                    nums[k] = f;
                }
                writeFile(fileName, id, nums);
            }
        } // Created all the files needed
        String [] arguements = new String[1];
        int [][] results = new int [3*files_to_create][3];
        for(int i=0; i<3*files_to_create; i++){
            arguements[0] = "data/test" + Integer.toString(i)+ ".txt";
            Greedy.main(arguements);
            results[i][0] = Greedy.get_sum_Disks();
            results[i][1] = Greedy.get_sum_Folders();
            Sort.main(arguements);
            Greedy.main(arguements);
            results[i][2] = Greedy.get_sum_Disks();
        }
        PrintWriter writer;
        String path = "data/results.txt" ;
        try{
            writer = new PrintWriter(path);
            for (int j=0; j<3; j++){
                for (int i=0; i<3*files_to_create ; i++)
                {
                    writer.print(results[i][j] + "\n");  
                }
                writer.print("***\n");
            }
            writer.close();
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("No such file exists.");
        }
        
        int sum_disks_rand;
        int sum_disks_sort;

        for (int i=0; i<3;i++){ //for each N
            sum_disks_rand = 0; //sum of disks used in random greedy
            sum_disks_sort = 0; //sum of disks used in sorted greedy
            for(int j=0+(i*files_to_create); j<files_to_create+(i*files_to_create); j++){ //manipulate the j so we get the correct portion of data from the results table
                sum_disks_rand += results[j][0];
                sum_disks_sort += results[j][2];
            }
            N = results[(i*files_to_create)][1]; //get the number of N again from the results table

            System.out.printf("For N = %d:\nThe average amount of disks used without sorting is: %f\nThe average amount of disks used with sorting is: %f\n",N,(float)sum_disks_rand/files_to_create,(float)sum_disks_sort/files_to_create);
        }
       
    }
}
