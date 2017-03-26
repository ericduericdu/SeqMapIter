public class Pair extends Element
{
    MyChar key;
    Element value;
    
    
    public Pair(MyChar keyy, Element val)           //basic constr, inits pairs
    {
        this.key = keyy;
        this.value = val;
    }
    
    public void Print()
    {
        System.out.print("(");

        key.Print();
        System.out.print(" ");
        value.Print();

        System.out.print(")");
        
    }
}