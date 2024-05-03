public abstract class WordList{
    public WordNode first;
    public WordNode last;
    public int length;
    public WordList(){
        WordNode e = new WordNode(null);
        first = e;
        last = e;
        length = 0;
    }
    public void append(Word s){//Add item to list
        WordNode nWord = new WordNode(s);
        last.next = nWord;
        last = nWord;
        length++;
    }
}
