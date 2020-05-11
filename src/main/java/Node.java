public class Node {
    private int nodeID;
    private int port;
    private int ip;
    int id_counter=1;

    public Node(int port, int ip) {
        this.ip=ip;
        this.nodeID=id_counter; //TODO 160 bits
        id_counter++;
        this.port=port;
    }
    
    public int get_Port(){
        return this.port;
    }
    public void to_String(){
        System.out.println("nodeID: " + this.nodeID + " ip: "+this.ip + " port: "+this.port);
    }

}