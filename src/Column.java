import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

public class Column {

    public String name;
    public String type;

    public ArrayList<Object> obj;

    public Column clone() {
        Column returnable = new Column(name, type);
        for(Object a : obj) {
            returnable.obj.add(a);
        }
        return returnable;
    }

    public Column(String nameToBe, String dataType) {
        this.name = nameToBe;
        this.type = dataType;
        obj = new ArrayList<Object>();
    }
}
