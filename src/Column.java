import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

public class Column {

    public String name;

    public ArrayList<Object> obj;

    public Column clone() {
        Column returnable = new Column(name, "notimportant");
        for(Object a : obj) {
            returnable.obj.add(a);
        }
        return returnable;
    }

    public Column(String nameToBe, String dataType) {
        this.name = nameToBe;
        obj = new ArrayList<Object>();
    }
}
