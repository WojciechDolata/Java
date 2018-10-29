import sun.awt.SunHints;
import sun.awt.image.ImageWatched;

import javax.xml.crypto.Data;
import java.io.*;
import java.lang.reflect.Array;
import java.util.*;

public class DataFrame{

    public class DFGroup implements Groupby{
        DataFrame frame;
        String [] colToSort;
        LinkedList<DataFrame> separatedDFs;
        DFGroup(String[] kolumny, ArrayList<Class<? extends Value>> typy, String[] colnames){
            colToSort = colnames;
            frame = new DataFrame(kolumny, typy);
            //separatedDFs = frame.toList(colnames);
        }
        @Override
        public DataFrame max() {


            return null;
        }

        @Override
        public DataFrame mean() {
            return null;
        }

        @Override
        public DataFrame std() {
            return null;
        }

        @Override
        public DataFrame sum() {
            return null;
        }

        @Override
        public DataFrame var() {
            return null;
        }

        @Override
        public DataFrame apply(Applyable a) {
            return null;
        }
    }

    public ArrayList<Column> table = new ArrayList<>(0);
    public ArrayList<Class<? extends Value>> types = new ArrayList<>();

    private int iterator = 0;

    public DataFrame(){

    }

    public DataFrame(String[] kolumny, ArrayList<Class<? extends Value>> typy){
        types=typy;
        int ite=0;
        for(String columnaName : kolumny) {
            table.add(new Column(columnaName,typy.get(ite)));
            ite++;
        }
    
    
    }

    public DataFrame(String fileName, ArrayList<Class<? extends Value>> typy, boolean header) throws IOException {
        types=typy;
        FileInputStream fstream = new FileInputStream(fileName);
        BufferedReader br = new BufferedReader(new InputStreamReader(fstream));

        String strLine;
        String[] names;
        if(header) {
            strLine = br.readLine();
            names = strLine.split("[,]");
        }
        else {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Prosze mi podac te nazwy (po przecinku)");
            strLine = scanner.nextLine();
            names = strLine.split("[,]");
        }

        int noColumns=0;
        for(String columnaName : names) {
            table.add(new Column(columnaName,typy.get(noColumns)));
            noColumns++;
        }

        int i = 0;
        while ((strLine = br.readLine()) != null)   {
            strLine = br.readLine();
            String[] currentItem = strLine.split("[,]");
            for(int j = 0; j<currentItem.length; j++) {

                table.get(j).obj.add(Value.getInstance(table.get(j).type));
                table.get(j).obj.get(i).set(currentItem[j]);
            }
            i++;
        }

        br.close();
    }

    public int size(){
        return table.get(0).obj.size();
    }

    public Column get(String colname) {
        for(Column currentColumn : table) {
            if(currentColumn.name.equals(colname))
                return currentColumn;
        }
        return null;
    }

    public ArrayList<Column> get(String [] cols, boolean copy) throws CloneNotSupportedException {
        ArrayList<Column> toBeReturned = new ArrayList<Column>();
        for(String currentColName : cols) {
            for(Column currentCol : table) {
                if(currentColName.equals(currentCol.name)) {
                    if(copy) {
                        toBeReturned.add(currentCol.clone());
                    }
                    else {
                        toBeReturned.add(currentCol);
                    }
                }
            }
        }
        return toBeReturned;
    }

    public int getColNum(){
        int i=0;
        for(Column currentCol : this.table)
            i++;
        return i;
    }

    //dziala
    public String[] getColNames(){
        String[] returnable = new String[getColNum()];
        int i=0;
        for(Column currentCol : this.table) {
            returnable[i] = currentCol.name;
            i++;
        }
        return returnable;
    }

    //dziala
    public DataFrame iloc(int i){//trzeba by cos dopisac do ilocow obu
        DataFrame returnable = new DataFrame();
        //returnable.add(getItem(i));
        return returnable;
    }

    //dziala
    public DataFrame iloc(int from, int to){
        DataFrame returnable = new DataFrame();

        for(int i = from; i <= to; i++){
            returnable.add(getItem(i));
        }
        return returnable;
    }

    public void print(){
        for(String currentName: getColNames()){
            System.out.println(currentName);
        }
        for(int i=0; i<size(); i++){
            System.out.println(getItem(i));
        }
    }

    //getItem działa
    public ArrayList<Value> getItem (int index){
        ArrayList<Value> returnable = new ArrayList<>();
        for(Column currentCol : table) {
            returnable.add(currentCol.obj.get(index));
        }
        return returnable;
    }
    //add działa
    public void add(ArrayList<Value> item){
        int k = 0;

        for(Value currentField : item) {
            this.table.get(k).obj.add(currentField);
            k++;
        }
    }
    //tu można zrobić zliczanie jaki jest najpopularniejszy item zeby go eliminowac do sparsa
    public Object mostCommon(){
        //for(Column.ob)
        return null;
    }

    public static String sledzParser(String a) {
        switch(a) {
            case "String":
                a = "java.lang.String";
                break;
            case "int":
                a = "java.lang.Integer";
                break;
            case "Int":
                a = "java.lang.Integer";
                break;
            case "double":
                a = "java.lang.Double";
                break;
            case "Double":
                a = "java.lang.Double";
                break;
            case "float":
                a = "java.lang.Float";
                break;
            case "Float":
                a = "java.lang.Float";
                break;
            case "char":
                a = "java.lang.Character";
                break;
            case "Character":
                a = "java.lang.Character";
                break;
            case "byte":
                a = "java.lang.Byte";
                break;
            case "Byte":
                a = "java.lang.Byte";
                break;
            case "boolean":
                a = "java.lang.Boolean";
                break;
            case "Boolean":
                a = "java.lang.Boolean";
                break;
            case "long":
                a = "java.lang.Long";
                break;
            case "Long":
                a = "java.lang.Long";
                break;
        }
        return a;
    }

    private int getColNumberInRow (String a){
        int i=0;
        for(String current:this.getColNames()){
            if(current.equals(a)) {
                return i;
            }
            i++;
        }
        return 0;
    }

    public LinkedList<DataFrame> tolist(String[] colnames){

        LinkedList<DataFrame> returnable = new LinkedList<>();
        int[] colIds = new int[colnames.length];

        for( int i = 0; i< colnames.length; i++){
            colIds[i] = getColNumberInRow(colnames[i]);
        }

        boolean stillEquals = true;
        DataFrame toAdd = new DataFrame(getColNames(), types);

        for( int i =0; i<size()-1; i++){

            stillEquals = true;

            for(int colIterator = 0; colIterator<colnames.length; colIterator++){

                if(!getItem(i).get(colIds[colIterator]).eq(getItem(i+1).get(colIds[colIterator]))){
                    stillEquals = false;
                }
            }

            toAdd.add(getItem(i));

            if(!stillEquals) {
                returnable.add(toAdd);
                toAdd = new DataFrame(getColNames(),types);
            }
        }

        if(stillEquals){
            toAdd.add(getItem(size()-1));
            returnable.add(toAdd);
        }

        return returnable;
    }


    class SortingDataFrame implements Comparator<ArrayList<Value>>{
        private String[] parameters;

        private SortingDataFrame(String[] parameters) {
            this.parameters = parameters;
        }

        @Override
        public int compare(ArrayList<Value> o1, ArrayList<Value> o2) {
            for(String currentCol : parameters){
                if (!o1.get(getColNumberInRow(currentCol)).equals(o2.get(getColNumberInRow(currentCol)))){
                    return o1.get(getColNumberInRow(currentCol)).lte(o2.get(getColNumberInRow(currentCol))) ?  -1 : 1;
                }
            }
            return 1;
        }
    }

    public ArrayList<ArrayList<Value>> getArrayList(){
        ArrayList<ArrayList<Value>> returnable = new ArrayList<>();
        for(int i =0; i<size(); i++){
            returnable.add(getItem(i));
        }
        return returnable;
    }

    //tu trzeba robić
    public DFGroup groupby(String[] colnames){
        DFGroup returnable = new DFGroup(getColNames(),types, colnames);
        ArrayList<ArrayList<Value>> ar = getArrayList();
        //Arrays.sor
        Collections.sort(ar,new SortingDataFrame(colnames));

        for(ArrayList<Value> curItem : ar) {
            returnable.frame.add(curItem);
        }
        returnable.frame.print();
        returnable.separatedDFs = returnable.frame.tolist(colnames);
        //System.out.println(returnable.frame.);
        return returnable;
    }

    public LinkedList<DataFrame> groupby(String colname){
        LinkedList<DataFrame> returnable = new LinkedList<>();
        int colNum = 0;
        for(Column currentCol : table){
            if(currentCol.name.equals(colname)){
                for(int i=0; i<table.size(); i++){
                    boolean isThereAny = false;
                    for(DataFrame currentDf : returnable){
                        if(currentDf.table.get(colNum).obj.get(0).equals(getItem(i).get(getColNumberInRow(colname)))){
                            currentDf.add(getItem(i));
                            isThereAny = true;
                        }
                    }
                    if(!isThereAny){
                        DataFrame tmpFrame = new DataFrame(getColNames(),types);
                        tmpFrame.add(getItem(i));
                        returnable.add(tmpFrame);
                    }
                }
            }
            colNum++;
        }
        return returnable;
    }
}
