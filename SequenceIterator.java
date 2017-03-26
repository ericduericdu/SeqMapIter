public class SequenceIterator extends Sequence
{
    public Sequence currItem;
    
    public SequenceIterator advance()       
    {
        this.currItem = this.currItem.next;       //follow the next "pointers"
        
        return this;
        
    }
    
    public Element get()
    {
        return currItem.content;                    //currItem should always point to an Element object
    }
    
    public boolean equal(SequenceIterator other)    //compare seqs
    {
        if(this.currItem == other.currItem)
        {
            return true; 
        }
            
        else 
        {
            return false;
        }
    }
    
    
    
    
    
}