import sun.awt.image.ImageWatched;

import javax.xml.crypto.Data;
import java.io.*;
import java.util.*;

public class DataFrame{

    public class DFGroup implements Groupby{
        DataFrame frame;
        DFGroup(String[] kolumny, ArrayList<Class<? extends Value>> typy){
            frame = new DataFrame(kolumny, typy);
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

        while ((strLine = br.readLine()) != null)   {
            strLine = br.readLine();
            String[] currentItem = strLine.split("[,]");
            for(int j = 0; j<currentItem.length; j++) {
                table.get(j).obj.add(Value.getInstance(table.get(j).type));
            }
//            ArrayList<Value> tmpItem = new ArrayList<>();
//            int i=0;
//            for (Class<? extends Value> currentType : typy){
//                //tmpItem.add(create());
////                switch (currentType){
////                    case "double":
////                        tmpItem.add(Double.parseDouble(currentItem[i]));
////                        break;
////                    case "Double":
////                        tmpItem.add(Double.parseDouble(currentItem[i]));
////                        break;
////                    case "Integer":
////                        tmpItem.add(Integer.parseInt(currentItem[i]));
////                        break;
////                    case "int":
////                        tmpItem.add(Integer.parseInt(currentItem[i]));
////                        break;
////                    case "String":
////                        tmpItem.add(currentItem[i]);
////                        break;
////                    case "string":
////                        tmpItem.add(currentItem[i]);
////                        break;
////                }
//                i++;
//            }
//            this.add(tmpItem);
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

    class SortingDataFrame implements Comparator<ArrayList<Value>>{
        private String[] parameters;

        private SortingDataFrame(String[] parameters) {
            this.parameters = parameters;
        }

        @Override
        public int compare(ArrayList<Value> o1, ArrayList<Value> o2) {
            System.out.println("zaczynam porownywac");
            System.out.println(o1.get(0));
            System.out.println(o2.get(0));
            for(String currentCol : parameters){
                System.out.println(currentCol);
                System.out.println(o1.get(getColNumberInRow(currentCol)));
                if (!o1.get(getColNumberInRow(currentCol)).equals(o2.get(getColNumberInRow(currentCol)))){
                    System.out.println(o1.get(getColNumberInRow(currentCol)).lte(o2.get(getColNumberInRow(currentCol))));
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
        DFGroup returnable = new DFGroup(getColNames(),types);
        ArrayList<ArrayList<Value>> ar = getArrayList();
        Collections.sort(ar,new SortingDataFrame(colnames));
        for(ArrayList<Value> curItem : ar) {
            returnable.frame.add(curItem);
        }
        returnable.frame.print();
        return returnable;
    }

    public LinkedList<DataFrame> groupby(String colname){
        LinkedList<DataFrame> returnable = new LinkedList<>();
        System.out.println(returnable);
        int colNum = 0;
        for(Column currentCol : table){
            System.out.println("Przetrwarzam kolumnę: ");
            System.out.println(currentCol.name);
            if(currentCol.name.equals(colname)){
                for(int i=0; i<table.size(); i++){
                    System.out.println(getItem(i));
                    boolean isThereAny = false;
                    for(DataFrame currentDf : returnable){
                        System.out.println("iterating through returnable list");
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
                    System.out.println(returnable);
                }
            }
            colNum++;
        }
        return returnable;
    }
}
