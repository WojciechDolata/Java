import java.util.ArrayList;
import java.util.List;

public class SparseDataFrame extends DataFrame{
    public ArrayList<CooValue> items = new ArrayList<CooValue>;
    public ArrayList<String> colNames = new ArrayList<String>;
    public ArrayList<String> colTypes = new ArrayList<String>;
    public SparseDataFrame(String[] columns, String[] dataTypes, String hide) {
        for(int i=0; i<columns.length; i++) {
            colNames.add(columns[i]);
            colTypes.add(dataTypes[i]);
        }

    }
    public SparseDataFrame(DataFrame unconvertedFrame) {


    }
}