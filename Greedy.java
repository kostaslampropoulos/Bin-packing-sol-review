import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Greedy {
    static int sum_Disks;
    static int sum_Folders;
    
    public static int get_sum_Disks(){
        return sum_Disks;
    }

    public static int get_sum_Folders(){
        return sum_Folders;
    }
    
    public static void main(String[] args) {
        sum_Folders = 0;
        BufferedReader reader = null;
        String l;
        try{
            String path = args[0]; //path of the txt file
            reader = new BufferedReader(new FileReader(new File(path)));
            
            l = reader.readLine();
            int n = 0;
            while (l != null) {
                if(l.equals("")){ //in case there are blank lines in the txt file with the folders
                    l = reader.readLine(); //Skip and read the next line
                    continue;
                }
                l = reader.readLine();
                n++; //count how many lines (folders)
            }
            
            reader = new BufferedReader(new FileReader(new File(path)));
            l = reader.readLine();
            MaxPQ pq = new MaxPQ(n); //create a queue that will definetly fit every folder
            int id=0; //unique id for each disk
            int sum = 0; //sum of all MB of folders
            boolean error = false;
            while (l != null) {
                if(l.equals("")){ //in case there are blank lines in the txt file with the folders
                    l = reader.readLine(); //Skip and read the next line
                    continue;
                }
                int folder = Integer.parseInt(l.trim());
                sum +=folder;
                if (folder>0 && folder<=1000000){ //check for incorrect size
                    if (pq.root()==null){ //if the PQ is empty
                        Disk d = new Disk<Integer>(id); //create a new disk
                        d.saveData(folder); //add the new folder
                        pq.insert(d); //put the disk in the PQ
                        id++;
                    }
                    else if (pq.root().getFreeSpace()<folder){ //if the top disk with the most availble storage doesnt fit the new folder
                        Disk d = new Disk<Integer>(id); //create a new disk
                        d.saveData(folder); //add the new folder
                        pq.insert(d); //put the disk in the PQ
                        id++;
                    }
                    else{
                        pq.root().saveData(folder); //add the folder to the top disk
                        pq.sink(1,pq.leaf()); //adjust the PQ
                    }
                    sum_Folders++;
                }
                else{
                    System.out.println("Invalid folder size!");
                    error = true;
                    break;
                }
                l = reader.readLine();
            }
            if (!error){
                System.out.printf("\nSum of all folders = %f TB\nTotal number of disks used = %d\n\n",((float)sum)/1000000,id);
                /*while(!pq.empty()){
                    System.out.printf("id %d %d: ",pq.root().get_id(),pq.root().getFreeSpace());
                    pq.getmax().getFolders();
                }*/
                sum_Disks = id;
            }
        } catch (IOException e) {
            //Inform the user that the File is not readable
            System.out.println("\nError reading file...\n");
        }
    }
}
