package Study;

public class Message {
    private String content;
    private int id;

    public Message(String content, int id) {
        this.content = content;
        this.id = id;
    }

    public String getInfo() {
        return this.content + " id: " + this.id;
    }
}