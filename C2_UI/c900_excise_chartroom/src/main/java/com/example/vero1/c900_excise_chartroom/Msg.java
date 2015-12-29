package com.example.vero1.c900_excise_chartroom;

/**
 * 消息类
 */
public class Msg {
    //消息内容
    private String content;
    //消息类型
    private int type;
    public static int SEND_TYPE=1;
    public static int RE_TYPE=2;

    public Msg(String content, int type) {
        this.content = content;
        this.type = type;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
