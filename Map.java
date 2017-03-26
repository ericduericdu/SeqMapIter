public class Map extends Element
{
    public Pair pack;                   //similar to Sequence class,
                                        //data and next "pointer"
    public Map nextPair;
    
    public Map()                        //appends dummy pair to end, for easy end()
    { 
        MyChar blank = new MyChar('@');
        MyChar blank2 = new MyChar('y');
        Pair filler = new Pair(blank, blank2);
        pack = filler;
        
    }
    
    public void add(Pair inval)
    {
        int findPos = 0;                                    //position of Pair insertion
        
        if(pack != null)
        {
            MapIterator it;
            Pair curr;
            
            for(it = this.begin(); !it.equal(this.end()); it.advance()) //iterates through the map
            {
                curr = it.get();
                
                if(inval.key.Get() >= curr.key.Get())
                {
                    findPos++; //counts how many it goes through
                }
                else
                {
                    break;
                }
            }
        }
        else //base case of empty map
        {
            pack = inval;
            return;
        }
    
        Map addition = new Map(); //new map to add
        
        if(findPos == 0) //case that it is added to front
        {
            addition.pack = pack;               //copies front to new one
            addition.nextPair = nextPair;
            pack = inval;                       //copies new to front
            nextPair = addition;
        }
        else
        {
            Map curr = this;
        
            int i = 0;
            while(i < findPos-1)        //find the position before where the new one goes
            {
                curr = curr.nextPair;
                i++;
            }
            addition.pack = inval;          //copies content into new one, and connects back to it
            addition.nextPair = curr.nextPair;
            curr.nextPair = addition;
        }

    }
    
    void Print()
    {
      

        System.out.print("[ ");
        
        Map curr = this;

        for(MapIterator it = this.begin(); !it.equal(this.end()); it.advance()) //iterates through and prints entire map
        {
            it.get().Print();
            System.out.print(" ");
        }

        System.out.print("]");

    }

    public MapIterator begin()                      //same as Seq
    {
        MapIterator it = new MapIterator();

        it.currPair = this;
        return it;
    }

    public MapIterator end()                //points to sentinel
    {
        MapIterator it = new MapIterator();

        Map curr = this;
        Map prev = null;

        while(curr != null)
        {
            prev = curr;
            curr = curr.nextPair;
        }
        it.currPair = prev;

        return it;
    }
    

    public MapIterator find(MyChar key)
    {
        MapIterator iter = new MapIterator();
        
        Map curr = this;
        
        while(curr != null && curr.pack.key.Get() != key.Get())     /iterates until it finds the matching key
            curr = curr.nextPair;

        if(curr == null)    //no matching key
            return end();
        
        iter.currPair = curr;

        return iter;
    }
}