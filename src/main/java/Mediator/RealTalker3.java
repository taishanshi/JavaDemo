package Mediator;

public class RealTalker3 implements Talker{
    private String name = "王五";
    private Mediator mediator;
    @Override
    public String getName() {
        return name;
    }

    @Override
    public void send(String message) {
        System.out.println(name + "发给中介消息: " + message);
        mediator.sendToAll(message);
    }

    @Override
    public void receive(String message) {
        System.out.println(name + "收到消息: " + message);
    }

    @Override
    public void setMediator(Mediator mediator) {
        this.mediator = mediator;
    }
}
