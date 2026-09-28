package state;

public class DoorCloseState implements ElevatorState{

    @Override
    public void openDoor(Elevator elevator) {
        System.out.println("正在开门.");
        elevator.setState(Elevator.doorOpenState);
    }

    @Override
    public void closeDoor(Elevator elevator) {
        System.out.println("已经关门,无需操作");
    }

    @Override
    public void goUp(Elevator elevator) {
        System.out.println("正在上行.");
        elevator.setState(Elevator.upState);
    }

    @Override
    public void goDown(Elevator elevator) {
        System.out.println("正在下行.");
        elevator.setState(Elevator.downState);
    }

    @Override
    public void stop(Elevator elevator) {
        System.out.println("已经停止,无需操作.");
    }
}
