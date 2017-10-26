package futuremotorproto;


import java.util.ArrayList;
import java.util.List;


/**
 *
 * @author gergohajzer
 */
public class Node {
    private String name;            //name of the node
    private int number;             //number of people in node
    private List<String> links;     //link to other nodes
    
    
    //constructors
    public Node(){
        this.name = "null";
        this.number = 0;
        this.links = new ArrayList<>();
    }
    public Node(String name, int number,List<String> links){
        this.name = name;
        this.number = number;
        this.links = links;
    }
    public Node(String name){
        this.name = name.replaceAll("/home/gergohajzer/NetBeansProjects/FutureMotorProto/", "");
        this.number = 0;
        this.links = new ArrayList<>();
    }
    
    public String getName(){
        return this.name;
    }
    public int getNumber(){
        return this.number;
    }
    public List getLinks(){
        return this.links;
    }
    public String getLink(int i){
        return this.links.get(i);
    }
    public void setName(String name){
        this.name = name;
    }
    public void setNumber(int number){
        this.number=number;
    }
    public void setLinks(List<String> links){
        this.links = links;
    }
    public void setLink(String link){
        this.links.add(link);
    }
}
