/**
 * This LLNode object defines an implementation of Comparable nodes for a linked list
 * 
 * @author  Tom Bredemeier
 * @version January 31, 2013
 */

public class LLNode<T>
{
    private T info;
    private LLNode<T> link;
  
    public LLNode(T info)
    {
        this.info = info;
        link = null;
    }
 
    // Sets info string of this LLStringNode.
    public void setInfo(T info)
    {
        this.info = info;
    }

    // Returns info string of this LLStringNode.
    public T getInfo()
    {
        return info;
    }
 
    // Sets link of this LLStringNode.
    public void setLink(LLNode<T> link)
    {
        this.link = link;
    }

    // Returns link of this LLStringNode.
    public LLNode<T> getLink()
    {
        return link;
    }
}
 
 