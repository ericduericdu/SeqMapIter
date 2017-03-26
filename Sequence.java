public class Sequence extends Element 
{
    public Element content;                     //each seq contains data and next "pointer"
    public Sequence next;
    
    public Sequence()           
    { }
    
    public Sequence(Element e, Sequence next)   //multi-purpose constr
    {
        this.content = e;
        this.next = next; 
    }
    
    public Sequence(Element elem)
    {
        this.content = elem;
    }
    
    void Print()
    {                               //iterate through a seq and call virtual print
        Sequence curr = this;
        
        System.out.printf("[ ");

        for(int i = 0; i < this.length(); i++, curr = curr.next)
        {
            curr.content.Print();
            System.out.print(" ");
            
        }
            
        System.out.printf("]");
    }
    
    Element first()
    {
        return this.content;         //this?
    }

    Sequence rest()
    {
        return this.next;
    }
    
    int length()
    {
        int len = 0;
        for(Sequence curr = this; curr != null; curr = curr.next)
            len++;
            
        return len;
    }
    
    void add(Element elem, int pos)
    {                                   
        Sequence curr_e = this;
        Sequence prev = null;
        
        for(int i = 0; curr_e != null && i < pos; curr_e = curr_e.next, i++)        //step through seqs
            prev = curr_e;
            
        if(prev == null)                                //very first item
        {
            if(curr_e.content != null)                  //make new seq with data from this
                this.next = new Sequence(this.content, this.next);
                
            this.content = elem;
        }
        
        else
            prev.next = new Sequence(elem, curr_e);
    }        
        
    void delete(int pos)    
    {
        Sequence curr_e = this;
        Sequence prev = null;
        
        for(int i = 0; i < pos && curr_e != null; i++, curr_e = curr_e.next)
            prev = curr_e;
        
        if(prev == null && curr_e != null)
        {
            this.content = this.next.content;
            this.next = this.next.next;
        }
        else
            prev.next = curr_e.next;
    }    

    public Element index(int pos)
    {
        Sequence curr = this;
        if(pos > (this.length()-1))           //pos not in range
            System.exit(0);
        
        for(int i = 0; i < pos; i++)            //follow next "pointers"
            curr = curr.next;
            
        return curr.content;
    }

    public Sequence flatten()
    {
        Sequence newSq = null;      //declare flattened sequence
        Sequence curr = this;       //holds current content
        Sequence end = null;        //the "Tail" of sequence
        
        while(curr != null)         //navigates through sequence until the end
        {
            if(curr.content instanceof Sequence)                //if content current content is a sequence
            {
                Sequence converted = (Sequence)curr.content;    //casts content to Sequence
                Sequence head = converted.flatten();            //recursively calls sequence within sequence
                
                if(newSq == null)                                //if new sequence has not been made yet. (first "level")
                {
                    newSq = head;                                    //attach head to sequence
                    end = newSq;                                     //keeps track of the end of sequence
                    
                    while(end.next != null)                         //move to end of list
                        end = end.next;
                }
                else
                {
                    end.next = head;                                //attach current node to end of list
                     
                     while(end.next != null)                             //move to the end
                        end = end.next;
                }
                
            }
            else
            {
                if(newSq == null)                                 //if flattened sequence has not been made yet
                {
                    newSq = new Sequence();                      //create
                    newSq.content = curr.content;                   //put the content into it (int or char)
                    end = newSq;                                    //keep track of end of sequence
                }
                else                                             //if sequence has been made
                {
                    Sequence attach = new Sequence();           //make a new one to attach to current one
                    attach.content = curr.content;
                    
                    end.next = attach;                          //keep track of end
                    end = attach;
                }
            }
            
            curr = curr.next;                                   //move on to next object
        }
        
        return newSq;
    }
    
    public Sequence copy()
    {
        Sequence deepSq = new Sequence();                     //make new sequence to add objects
        
        Sequence curr = this;                                   //working with the current element
        
        int length = curr.length();                             //get the length of the current element
        
        for(int i = 0; i < length; i++)
        {
            if(curr.content instanceof Sequence)                //if sequence
            {
                Sequence temp = (Sequence)curr.content;         //cast a temp Sequence
                deepSq.add(temp.copy(), i);                     //recursively call for deep copy if sequence
            }
            else
            {
                if(curr.content instanceof MyChar) //if it is char value
                {
                    MyChar ch = (MyChar)curr.content;   //make new char to add to deepSq
                    MyChar cha = new MyChar(ch.Get());
                    deepSq.add(cha, i);  
                }
                else //if integer
                {
                    MyInteger in = (MyInteger)curr.content;     //makes new int value to add to sequence
                    MyInteger ina = new MyInteger(in.Get());
                    deepSq.add(ina, i);  
                }
                    
               // deepSq.add(curr.content, i);                         //or just add to sequence
                //deepSq.append(curr.content);
            }
      
            curr = curr.next;
        }
        
        return deepSq;
        
    }
    
    public SequenceIterator begin()     //points to front
    {
        SequenceIterator it = new SequenceIterator();
        
        it.currItem = this;
        return it;
    }
    
    public SequenceIterator end()
    {
        SequenceIterator it = new SequenceIterator();
        
        Sequence traverse = this;
        
        for(int i = 0; i < this.length(); i++)  //traverse through whole sequence
        {
            traverse = traverse.next;
        }
        
        it.currItem = traverse; //back of sequence
    
        return it;
    }
    
}