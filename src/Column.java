import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

public class Column implements Cloneable{

    public String name;
    public Class<? extends Value> type;

    public ArrayList<Value> obj;

    public Column clone() throws CloneNotSupportedException{

        return (Column) super.clone();
//        Column returnable = new Column(name, type);
//        for(Object a : obj) {
//            returnable.obj.add(a);
//        }
//        return returnable;
    }

    public Column(String nameToBe, Class<? extends Value> dataType) {
        this.name = nameToBe;
        this.type = dataType;
        obj = new ArrayList<Value>();
    }
}
