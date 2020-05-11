import java.util.ArrayList;
import java.util.Arrays;

public class KBucket {
    private ArrayList<Node> Kbucket=new ArrayList<Node>();

    public void add_to_KBucket(Node node){
        Kbucket.add(node);
    }

    public void to_String(){
        //System.out.println(Arrays.toString(Kbucket.toArray()));
        for (int i = 0; i < Kbucket.size(); i++) {
            Node temp=Kbucket.get(i);
            temp.to_String();
        }
        if(Kbucket.size()==0){
            System.out.println("No entries in this KBucket yet");
        }
    }
    

   
}