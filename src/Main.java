import javax.xml.crypto.Data;
import java.util.ArrayList;
import java.util.List;

public class Main {

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

    public static void main (String[] args) {
        String [] p = {"aaa" , "bbb" };
        String [] d = {"int", "int"};
        SparseDataFrame nowaRama = new SparseDataFrame(p,d,"0");

        ArrayList<Object> nowyObj = new ArrayList<>();
        ArrayList<Object> nowyObj2 = new ArrayList<>();
        nowyObj.add(0,0);
        nowyObj.add(1,1);
        nowaRama.add(nowyObj);
        nowyObj2.add(0,2);
        nowyObj2.add(1,200);
        nowaRama.add(nowyObj2);
        nowaRama.print();

//        String [] l = {"aaa" , "bbb" };
//        String [] r = {"int", "int"};
//        DataFrame rama = new DataFrame(l,r );
//        ArrayList<Object> arraylist = new ArrayList<Object>();
//        ArrayList<Column> arraylist2 = new ArrayList<Column>();
//        arraylist.add(233);
//        arraylist.add(333);
//        rama.add(arraylist);
//        arraylist2 = rama.get( l,false );
//        rama.table.get(0).name= "jajka";
//        System.out.println(rama.table.get(0).obj.get(0));
//        System.out.println(rama.table.get(1).obj.get(0));
//        SparseDataFrame tmpRama = new SparseDataFrame(rama);
//        tmpRama.print();

        DataFrame jeszcze1 = nowaRama.toDense();
        SparseDataFrame tmpRama2 = new SparseDataFrame(jeszcze1);
        tmpRama2.print();
    }
}
