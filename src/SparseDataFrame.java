
import javax.xml.crypto.Data;
import java.util.ArrayList;
        import java.util.List;

public class SparseDataFrame extends DataFrame{
    public boolean isNumeric(String str)
    {
        try
        {
            double d = Double.parseDouble(str);
        }
        catch(NumberFormatException nfe)
        {
            return false;
        }
        return true;
    }
    private int objectIterator = 0;
    public ArrayList<SparseColumn> table = new ArrayList<>();
    private Object elementToHide = new Object();
    private int iterator = 0;
    public SparseDataFrame(String[] columns, String[] dataTypes, String hide) {
        elementToHide = hide;
        if(hide.contains(".")) {
            elementToHide = Double.parseDouble(hide);
        }
        else if(isNumeric(hide)){
            elementToHide = Integer.parseInt(hide);
        }
        else{
            elementToHide = hide;
        }
        for(int i=0; i<columns.length; i++) {
            table.add(new SparseColumn(columns[i], dataTypes[i], hide));
        }

    }
    public void add(ArrayList<Object> item){
        int k=0;
        for(Object currentObj : item) {
            if(currentObj != elementToHide) {
                table.get(k).obj.add(new CooValue(objectIterator, currentObj));
            }
            k++;
        }
        objectIterator++;
    }

    public void print(){
        for(SparseColumn currentCol : table) {
            System.out.println(currentCol.name + " " + currentCol.type);
            for(CooValue currentVal : currentCol.obj){
                System.out.println(currentVal.id + " " + currentVal.val);
            }
        }
    }

    public SparseDataFrame(DataFrame unconvertedFrame) {
        int numCol=0;
        for(Column currentCol : unconvertedFrame.table){
            numCol++;
        }
        String[] columns = new String[numCol + 1];
        String[] dataTypes = new String[numCol + 1];
        int k=0;
        for(Column currentCol : unconvertedFrame.table){
            columns[k] = currentCol.name;
            dataTypes[k] = currentCol.type;
            k++;
        }
        k=0;
        String hide = ""; // to mozna brac z mostCommon z dataFrame
        elementToHide = hide;
        if(hide.contains(".")) {
            elementToHide = Double.parseDouble(hide);
        }
        else if(isNumeric(hide)){
            elementToHide = Integer.parseInt(hide);
        }
        else{
            elementToHide = hide;
        }
        for(int i=0; i<columns.length; i++) {
            table.add(new SparseColumn(columns[i], dataTypes[i], hide));
        }

        for(int i=0; i<unconvertedFrame.size(); i++){
            this.add(getItem(i));
            iterator++;
        }
    }

    public ArrayList<Object> getItem(int id) {
        ArrayList<Object> returnable = new ArrayList<>();
        for(SparseColumn currentCol : this.table){
            returnable.add(currentCol.copyItemId(id));
        }

        return returnable;
    }

    public DataFrame toDense(){
        String[] columns = new String[iterator + 1];
        String[] dataTypes = new String[iterator + 1];
        int k=0;
        for(SparseColumn currentCol : this.table) {
            columns[k] = currentCol.name;
            dataTypes[k] = currentCol.type;
            k++;
        }
        DataFrame returnable = new DataFrame(columns,dataTypes);
        for(int i = 0; i<objectIterator; i++) {
            returnable.add(this.getItem(i));
        }


        return returnable;
    }
}