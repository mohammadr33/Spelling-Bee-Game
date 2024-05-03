public class SortedWordList extends WordList{
    public SortedWordList(){
        super();
    }
    public void add(Word w){
        WordNode x = new WordNode(w);
        WordNode input1 = first;
        WordNode input2 = first.next;
        while(input2 != null && x.data.getWord().compareTo(input2.data.getWord()) > 0){//Checks to sort the new value into its correct position by comparing to previous items
            input1 = input1.next;
            input2 = input2.next;
        }
        x.next = input2;
        input1.next = x;
    }
    public void print(){
        WordNode input = first.next;
        while(input != null){//Prints out entire list
            System.out.println(input.data.getWord());
                input = input.next;
            
        }
    } 
    public void clear(){//Clears the list
        first.setNext(null);
    }  
}