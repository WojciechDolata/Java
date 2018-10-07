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
        DataFrame rama = new DataFrame(p,d );
        ArrayList<Object> arraylist = new ArrayList<Object>();
        ArrayList<Column> arraylist2 = new ArrayList<Column>();
        arraylist.add(233);
        arraylist.add("Rahul");
        rama.add(arraylist);
        arraylist2 = rama.get( p,false );
        System.out.println(arraylist2.get(0).name);
        rama.table.get(0).name= "jajka";

        System.out.println(arraylist2.get(0).name);

    }
}
