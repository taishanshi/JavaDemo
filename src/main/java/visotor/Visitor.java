package visotor;

import state.Elevator;

public interface Visitor {
    void visit(RollerCoaster rollerCoaster);
    void visit(FerrisWheel ferrisWheel);
    void visit(MerryGoRound merryGoRound);
}
