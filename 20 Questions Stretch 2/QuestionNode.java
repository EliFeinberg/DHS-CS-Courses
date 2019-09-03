
public class QuestionNode {
    public String info;
    public QuestionNode yes,no;
    public QuestionNode(String info) {
        this.info = info;
        yes = null;
        no = null;
    }

    public QuestionNode(String info, QuestionNode y, QuestionNode n) {
        this.info = info;
        yes = y;
        no = n; 
    }                      

    public boolean isAnswerNode()
    {
        return yes == null && no == null;
    }
}