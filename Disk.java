import javax.sound.sampled.SourceDataLine;

class Disk<Integer> implements Comparable<Disk>{ 

    private int storage; //available storage of disk
    private int id; //unique id of disk
    private StringStackImpl<Integer> folders; //stack of folders in disk
    

    Disk(int id){ 
        this.id = id;
        storage = 1000000; //1 TB storage
        folders = new StringStackImpl<Integer>();
    }

    public int get_id(){
        return this.id;
    }

    public int getFreeSpace(){
        return this.storage;
    }

    public void getFolders(){
        folders.printStack(System.out); //print all the folders in a line
        System.out.println();
    }

    public void saveData(Integer folder){
        folders.push(folder); //add the new folder to the folders
        this.storage -=(int)folder; //adjust the remaing storage
    }

    public int compareTo(Disk B) {
        if (storage > B.getFreeSpace()){
            return 1;
        } else if (storage < B.getFreeSpace()){
            return -1;
        }
        return 0;
    }
}