
public abstract class Value{
    Value(){

    }
    public Object val;

    public abstract String toString();
    public abstract Value add(Value);
    public abstract Value sub(Value);
    public abstract boolean eq(Value);
    public abstract Value ite(Value);
    public abstract Value gte(Value);
    public abstract boolean neq(Value);
    public abstract boolean equals(Object other);
    public abstract int hashCode();
    public abstract B create(String s);
    
}