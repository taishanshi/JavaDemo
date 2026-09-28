package visotor;

import state.Elevator;

public interface Element {
    void accept(Visitor visitor);
}
