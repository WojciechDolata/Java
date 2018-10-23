interface Groupby{
    DataFrame max();
    DataFrame mean();
    DataFrame std();
    DataFrame sum();
    DataFrame var();
    DataFrame apply(Applyable a);
}