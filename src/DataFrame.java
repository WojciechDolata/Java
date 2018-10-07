import java.util.ArrayList;
import java.util.List;

public class DataFrame {

    public ArrayList<Column> table = new ArrayList<>(0);

    private int iterator = 0;

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
        for(String columnaName : kolumny) {
            table.add(new Column(columnaName,""));
        }
    }
    public int size(){
        return table.get(0).obj.size();
        //return iterator;
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

    public String getColNames(){
        String returnable = "";
        for(Column currentCol : table) {
            returnable += currentCol.name +", ";
        }
        return returnable.replace(returnable.substring(returnable.length()-2), "");
    }

    public DataFrame iloc(int i){
        String[] colNames = { getColNames()};
        DataFrame returnable = new DataFrame(colNames,colNames);
        returnable.add(getItem(i));
        return returnable;
    }

    public DataFrame iloc(int from, int to){
        String[] colNames = { getColNames()};
        DataFrame returnable = new DataFrame(colNames,colNames);
        for(String ss : colNames) System.out.println(ss);
        for(int i = from; i <= to; i++){
            System.out.println(getItem(i));
            System.out.println(returnable.size());
            returnable.add(getItem(i));
        }
        return returnable;
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
            this.table.get(k).obj.add(currentField);
            k++;
        }
        //iterator++;
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
