package state;

public interface ElevatorState {
    void openDoor(Elevator elevator);
    void closeDoor(Elevator elevator);
    void goUp(Elevator elevator);
    void goDown(Elevator elevator);
    void stop(Elevator elevator);
}
