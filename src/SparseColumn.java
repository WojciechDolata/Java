import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

public class SparseColumn {

    public String name;
    public String type;
    public Object toHide;
    public ArrayList<CooValue> obj;

//    public Column clone() {
//        Column returnable = new Column(name, type);
//        for(Object a : obj) {
//            returnable.obj.add(a);
//        }
//        return returnable;
//    }

    public Object copyItemId(int id){
        for(CooValue currentVal : obj){
            if(currentVal.id == id)
                return currentVal.val;
        }
        return toHide;
    }

    public SparseColumn(String nameToBe, String dataType, Object hide) {
        this.name = nameToBe;
        this.type = dataType;
        obj = new ArrayList<CooValue>();
        toHide = hide;
    }
}
