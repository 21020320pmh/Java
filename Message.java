package Study;

public class Message {
    private final String content;
    private final int id;

    public Message(String content, int id) {
        this.content = content;
        this.id = id;
    }

    public String getInfo() {
        return this.content + " id: " + this.id;
    }
}