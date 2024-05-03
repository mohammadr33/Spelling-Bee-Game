public class WordNode{
    protected Word data;
    protected WordNode next;
    public WordNode(Word w){
        data = w;
        next = null;
    }
    public Word getData(){//Get data from node
        return data;
    }
    public WordNode getNext(){
        return next;
    }
    public void setNext(WordNode next){//Sets the data 
        this.next = next;
    }
}    