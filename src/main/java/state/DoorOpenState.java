package state;

public class DoorOpenState implements ElevatorState{
    @Override
    public void openDoor(Elevator elevator) {
        System.out.println("门已打开,无需操作.");
    }

    @Override
    public void closeDoor(Elevator elevator) {
        System.out.println("正在关门.");
        elevator.setState(Elevator.doorCloseState);
    }

    @Override
    public void goUp(Elevator elevator) {
        System.out.println("门未关, 不能上行.");
    }

    @Override
    public void goDown(Elevator elevator) {
        System.out.println("门未关, 不能下行.");
    }

    @Override
    public void stop(Elevator elevator) {
        System.out.println("已经是停止状态, 无需操作.");
    }
}
