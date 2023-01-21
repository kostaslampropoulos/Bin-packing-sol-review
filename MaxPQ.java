public class MaxPQ{
    private Disk[] pq; //priority queque of disks
    private int N; //pointer to last disk
    
    MaxPQ(int maxN){ 
        pq = new Disk[maxN+1]; 
        N = 0; 
    }
    
    private boolean less(int i, int j) {
        return pq[i].getFreeSpace() < pq[j].getFreeSpace(); //the key for ajdusting the queue is the remaining storage of each disk
    }
    
    private void exch(int i, int j) {
        Disk t = pq[i]; //exchange 2 disks
        pq[i] = pq[j]; 
        pq[j] = t;
    }
    
    public void swim(int k){
        while (k > 1 && less(k/2, k)) { //let the disk k swim towards the top (root) of the queue
            exch(k, k/2); k = k/2; } 
    }
    public void sink(int k, int N){ //sink the top disk
        while (2*k <= N) {
            int j = 2*k;
            if (j < N && less(j, j+1)) j++;
            if (!less(k, j)) break;
            exch(k, j); k = j; }
    }

    boolean empty(){ 
        return N == 0; 
    }
    
    void insert(Disk v){
        pq[++N] = v; //add a disk
        swim(N); //place it in the correct spot
    }
    
    Disk root(){
        return pq[1]; //return the root (top disk, largest available storage)
    }

    int leaf(){
        return N; //return the position of the last disk to be inserted
    }

    Disk getmax() {
        exch(1, N); //give out the root(top disk) and re-adjust the queue accordingly
        sink(1, N-1); 
        return pq[N--]; 
    }
}