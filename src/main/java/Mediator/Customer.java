package Mediator;

import java.util.Scanner;

public class Customer {
    public static void main(String[] args) {
        Talker talker = new RealTalker1();
        Talker talker2 = new RealTalker2();
        Talker talker3 = new RealTalker3();

        Mediator mediator = new TalkRoom();
        talker.setMediator(mediator);
        talker2.setMediator(mediator);
        talker3.setMediator(mediator);

        mediator.register(talker);
        mediator.register(talker2);
        mediator.register(talker3);

        talker.send("hello, 我是张三");
        talker2.send("hello, 我是张四");
        talker3.send("hello, 我是王五");
    }
}

