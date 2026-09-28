package Mediator;

import java.util.ArrayList;
import java.util.List;

public class TalkRoom implements Mediator {
    private List<Talker> talkers = new ArrayList<Talker>();
    @Override
    public void register(Talker talker) {
        talkers.add(talker);
    }

    @Override
    public void sendToAll(String message) {
        for (Talker talker : talkers) {
            talker.receive(message);
        }
    }
}
