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


        DoubleValue a = new DoubleValue("20");
        System.out.println(a.getVal());
        DoubleValue b = new DoubleValue(10.0);
        System.out.println(b.getVal());
        b.add(new DoubleValue(10));
        System.out.println(b.getVal());
        System.out.println(b.add(new DoubleValue(10.0)).equals(a));
        System.out.println(b.add(new DoubleValue(10.0)).eq(a));


    }
}
