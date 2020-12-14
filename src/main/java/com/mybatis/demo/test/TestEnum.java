package com.mybatis.demo.test;

public class TestEnum {

    public static void main(String[] args) {
        Order o = new Order();
        o.status = State.PAYED;
        State[] states = State.values();
        for(State s : states){
            System.out.println(s.name()+"  "+s.getComment()+"   "+s.ordinal() + "  " + s.toString());
        }
    }
}

class Order {
    public State status = State.UNPAY;
}
enum State {
    UNPAY("未支付"),
    PAYED("已支付"),
    SENT("已发货"),
    RECEIVED("已收货");

    private String comment;
    private State (String s){
        this.comment = s;
    }
    public String getComment(){
        return comment;
    }
}

class State2{
    public static final State2 UNPAY = new State2("未支付");
    public static final State2 PAYED = new State2("已支付");
    public static final State2 SENT = new State2("已发货");
    public static final State2 RECEIVED = new State2("已收货");

    private String commet;
    private State2(String s){
        commet = s;
    }
    public String getName(){
        return commet;
    }
}