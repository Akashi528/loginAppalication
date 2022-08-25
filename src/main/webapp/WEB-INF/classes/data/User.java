package data;
/*用户bean用与存放和获取用户信息*/
public class User {
    private String userID;//用户名
    private String passWord;//密码
    private String question; //找回密码验证问题
    private String answer;//密保答案

    public String getUserID() {
        return userID;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }
}
