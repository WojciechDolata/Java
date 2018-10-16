import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DataFrame {

    public ArrayList<Column> table = new ArrayList<>(0);

    private int iterator = 0;

    public DataFrame(){

    }

    public DataFrame(String[] kolumny, String[] typy){
//        for(String className : typy) {
//            className = DataFrame.sledzParser(className);
//        Class<?> typ;
//        try {
//            typ = Class.forName(className);
//            Class typ1 = typ.getClass();
//            List<typ1> kol = new ArrayList<>();
//        } catch(Exception e) {
//            System.out.println(e);
//        }
//
//            Class cls = Class.forName(className);
//            Integer num = 4;
//
//            Class cls = className.getClass();
//            Object a = new cls();
//            Object obj = (Object) cls.newInstance();
//            List<cls> kol = new ArrayList<>();
//        }
//        int i = 0;
//        Column[] tabCol = new Column[100];
//        for(String columnaName : kolumny) {
//            tabCol[i] = new Column(columnaName, "");
//            i++;
//        }
//        i=0;
        int ite=0;
        for(String columnaName : kolumny) {
            table.add(new Column(columnaName,typy[ite]));
            ite++;
        }
    }

    public DataFrame(String fileName, String[] typy, boolean header) throws IOException {
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
            table.add(new Column(columnaName,typy[noColumns]));
            noColumns++;
        }

        while ((strLine = br.readLine()) != null)   {
            strLine = br.readLine();
            String[] currentItem = strLine.split("[,]");
            ArrayList<Object> tmpItem = new ArrayList<>();
            int i=0;
            for (String currentType : typy){
                switch (currentType){
                    case "double":
                        tmpItem.add(Double.parseDouble(currentItem[i]));
                        break;
                    case "Double":
                        tmpItem.add(Double.parseDouble(currentItem[i]));
                        break;
                    case "Integer":
                        tmpItem.add(Integer.parseInt(currentItem[i]));
                        break;
                    case "int":
                        tmpItem.add(Integer.parseInt(currentItem[i]));
                        break;
                    case "String":
                        tmpItem.add(currentItem[i]);
                        break;
                    case "string":
                        tmpItem.add(currentItem[i]);
                        break;
                }
                i++;
            }
            this.add(tmpItem);
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

    public ArrayList<Column> get(String [] cols, boolean copy) {
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
    public DataFrame iloc(int i){
        DataFrame returnable = new DataFrame(getColNames(),getColNames());
        returnable.add(getItem(i));
        return returnable;
    }

    //dziala
    public DataFrame iloc(int from, int to){
        DataFrame returnable = new DataFrame(getColNames(),getColNames());

        for(int i = from; i <= to; i++){
            returnable.add(getItem(i));
        }
        return returnable;
    }

    public void print(){
        System.out.println(getColNames());
        for(int i=0; i<size(); i++){
            System.out.println(getItem(i));
        }
    }

    //getItem działa
    public ArrayList<Object> getItem (int index){
        ArrayList<Object> returnable = new ArrayList<>();
        for(Column currentCol : table) {
            returnable.add(currentCol.obj.get(index));
        }
        return returnable;
    }
    //add działa
    public void add(ArrayList<Object> item){
        int k = 0;

        for(Object currentField : item) {
            if (!item.get(k).getClass().getName().equals(sledzParser(this.table.get(k).type))) {
                throw new ArithmeticException("Wrong data type");
            }
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
