
public class IntegerValue extends Value{
    //public Integer val = 0;
    public IntegerValue(Integer valToBe){
        val = new Integer(valToBe);
    }
    public IntegerValue(){
        val = new Integer(0);
    }

    public String toString(){
        return String.valueOf(val);
    }

    public Value add(IntegerValue other){
        return new IntegerValue(val+other.val);
    }

    public Value sub(IntegerValue other){
        this.val = other.val;
        return this;
    }

    public boolean eq(IntegerValue other){
        return this.equals(other.val);
    }

    public boolean neq(IntegerValue other){
        return !this.equals(other.val);
    }

    public boolean equals(Object other){
        if (other.equals(this.val))
            return true;
        return false;
    }

    public int hashCode(){
        return this.val.hashCode();
    }
    


}