import java.io.*;
import java.util.ArrayList;

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

    public static void main (String[] args) throws IOException {

        IntegerValue id = new IntegerValue(1);
        IntegerValue id1 = new IntegerValue(2);
        IntegerValue id2 = new IntegerValue(3);
        IntegerValue f = new IntegerValue(20);
        IntegerValue f1 = new IntegerValue(20);
        IntegerValue f2 = new IntegerValue(5);
        StringValue fss = new StringValue("banknot 20 zlotych");
        StringValue fss2 = new StringValue("moneta 20 zlotych");
        StringValue fss3 = new StringValue("moneta 5 zlotych");

        String[] nazwy = {"id", "wartosc", "opis"};
        ArrayList<Class<? extends Value>> typy = new ArrayList<>();
        typy.add(id.getClass());
        typy.add(f.getClass());
        typy.add(fss.getClass());
        ArrayList<Value> item1 =new ArrayList<>();
        item1.add(id);
        item1.add(f);
        item1.add(fss);
        ArrayList<Value> item2 =new ArrayList<>();
        item2.add(id1);
        item2.add(f1);
        item2.add(fss2);
        ArrayList<Value> item3 =new ArrayList<>();
        item3.add(id2);
        item3.add(f2);
        item3.add(fss3);
        DataFrame nowarama = new DataFrame(nazwy,typy);
        nowarama.add(item1);
        nowarama.add(item3);
        nowarama.add(item2);

        nowarama.print();
        String[] sort = { "wartosc","id",};
        DataFrame.DFGroup nowagrupa = nowarama.groupby(sort);





    }
}
