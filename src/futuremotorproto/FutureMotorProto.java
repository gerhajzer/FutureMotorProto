package futuremotorproto;


import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


/**
 *
 * @author gergohajzer
 */
public class FutureMotorProto {

    private static List<Node> graph;        //graph, cointaning the nodes
    private static int iterations = 300;     //pieces of simulations
    
    public FutureMotorProto(){
        this.graph = new ArrayList<>();
        readFiles("City");
        Matrix myVec = createVector();
        Matrix myMatrix = createMatrix();
        myMatrix.show();
        iterations(myVec,myMatrix);
    }
    //return the population of Debrecen
    public int sumNumber(){    
        int sum = 0;
        for(Node n : graph){
            sum += n.getNumber();
        }
        return sum;
    }
    //create the vector
    public Matrix createVector(){
        int size = graph.size();
        double sum = sumNumber();
        double[][] array = new double[1][size];
        for (int i = 0; i < size; i++) {
            array[0][i] = graph.get(i).getNumber()/sum;
        }
        Matrix myVec = new Matrix(array);
        return myVec;
    }
    //create the matrix
    public Matrix createMatrix(){
        int size = graph.size();
        double sum = sumNumber();
        double[][] array1 = new double[size][size];
        
        for (int i = 0; i < size; i++) {
            double linksCount = graph.get(i).getLinks().size();
            for (int j = 0; j < linksCount; j++) {
                String actual = "City/Debrecen" + graph.get(i).getLink(j);
                for (int k = 0; k < size; k++) {
                    if (actual.equals(graph.get(k).getName())) {
                        array1[i][k] = 1.0/linksCount;
                    }
                }
            }
        }
        
        Matrix myMatrix = new Matrix(array1);
        return myMatrix;
    }
    //run the iterations
    public void iterations(Matrix myVec, Matrix myMatrix){
        for (int i = 0; i <= iterations; i++) {
            System.out.println(i + ". iteration");
            myVec = myVec.times(myMatrix);
            myVec.show();
            System.out.printf("%9.8f ",myVec.sum());
            System.out.println();
        }
    }
    
    //read the files recursively
    public void readFiles(String sDir){
        File[] Files = new File(sDir).listFiles();
        String name;
        int number;
        List<String> links = new ArrayList<>();
        for(File file: Files){
          if(file.isFile()){
              try{
                  java.util.Scanner in = new java.util.Scanner(new java.io.FileReader(file.getAbsoluteFile()));
                  Node node = new Node(file.getPath());
                  node.setNumber(in.nextInt());
                  
                  while(in.hasNext()){
                      node.setLink(in.next());
                  }
                  graph.add(node);
              }catch(IOException ex){
                  ex.printStackTrace();
              }
          } else {
          }
          if(file.isDirectory()){
            readFiles(file.getAbsolutePath());
          }
        }
    }
    
    public static void main(String[] args) {
        FutureMotorProto proba = new FutureMotorProto();
        System.out.println("Population of Debrecen: " + proba.sumNumber());       
    }
    
}
