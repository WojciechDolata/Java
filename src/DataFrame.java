import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DataFrame {

    public ArrayList<Column> table = new ArrayList<>(0);

    private int iterator = 0;

    public DataFrame(){

    }

    public DataFrame(String[] kolumny, ArrayList<Class<? extends Value>> typy){

        int ite=0;
        for(String columnaName : kolumny) {
            table.add(new Column(columnaName,typy.get(ite)));
            ite++;
        }
    }

    public DataFrame(String fileName, ArrayList<Class<? extends Value>> typy, boolean header) throws IOException {
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
            System.out.println(currentCol.name);
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
            //returnable.add(getItem(i));
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
}
