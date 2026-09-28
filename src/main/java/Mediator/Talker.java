package Mediator;

public interface Talker {
    String getName();
    void setMediator(Mediator mediator);
    void send(String message);
    void receive(String message);
}
