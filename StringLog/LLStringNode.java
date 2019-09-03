public class LLStringNode 
{
    private String info;
    private LLStringNode link;
  
    public LLStringNode(String info)
    {
        this.info = info;
        link = null;
    }
 
    // Sets info string of this LLStringNode.
    public void setInfo(String info)
    {
        this.info = info;
    }

    // Returns info string of this LLStringNode.
    public String getInfo()
    {
        return info;
    }
 
    // Sets link of this LLStringNode.
    public void setLink(LLStringNode link)
    {
        this.link = link;
    }

    // Returns link of this LLStringNode.
    public LLStringNode getLink()
    {
        return link;
    }
}
 
 