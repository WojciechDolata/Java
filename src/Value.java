
public abstract class Value{
    Value(){

    }

    public abstract String toString();
    public abstract Value add(Value other);
    public abstract Value sub(Value other);
    public abstract Value mul(Value other);
    public abstract Value div(Value other);
    public abstract Value pow(Value other);
    public abstract boolean eq(Value other);
    public abstract boolean lte(Value other);
    public abstract boolean gte(Value other);
    public abstract boolean neq(Value other);
    public abstract boolean equals(Object other);
    public abstract int hashCode();
    public abstract Value create(String s);
    public abstract Object getVal();
    public static Value getInstance(Class c){
        switch (c.getName()){
            case "IntegerValue":
                return new IntegerValue();
            case "DoubleValue":
                return new DoubleValue();
            case "StringValue":
                return new StringValue();
            case "FloatValue":
                return new FloatValue(0);
            case "DateTimeValue":
                return new DateTimeValue("0-0-0","0:0");
            default:
                return null;
        }
    }
}