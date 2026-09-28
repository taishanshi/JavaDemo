package state;

public class UpState implements ElevatorState{
    @Override
    public void openDoor(Elevator elevator) {
        System.out.println("正在上行,不能开门.");
    }

    @Override
    public void closeDoor(Elevator elevator) {
        System.out.println("上行状态,无需关门.");
    }

    @Override
    public void goUp(Elevator elevator) {
        System.out.println("上行状态,无需操作");
    }

    @Override
    public void goDown(Elevator elevator) {
        System.out.println("电梯上行,不能下行");
    }

    @Override
    public void stop(Elevator elevator) {
        System.out.println("电梯已停止.");
        elevator.setState(Elevator.stopState);
    }
}
