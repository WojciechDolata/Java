
public abstract class Value{
    Value(){

    }

    public abstract String toString();
    public abstract Value add(Value);
    public abstract Value sub(Value);
    public abstract Value eq(Value);
    public abstract Value ite(Value);
    public abstract Value gte(Value);
    public abstract Value neq(Value);
    public abstract boolean equals(Object other);
    public abstract int hashCode();
    public abstract B create(String s);
    
}