public class MapIterator
{
    public Map currPair;
    
    public MapIterator advance()
    {
        this.currPair = this.currPair.nextPair;
        
        return this;
    }
    
    public Pair get()
    {
        return currPair.pack;
    }
    
    public boolean equal(MapIterator other)
    {
        return this.get() == other.get();
    }
    


}