package Mediator;

public interface Mediator {
    void register(Talker talker);
    void sendToAll(String message);
}
