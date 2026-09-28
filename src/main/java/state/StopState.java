package state;

public class StopState implements ElevatorState{

    @Override
    public void openDoor(Elevator elevator) {
        System.out.println("门打开了.");
        elevator.setState(Elevator.doorOpenState);
    }

    @Override
    public void closeDoor(Elevator elevator) {
        System.out.println("门是关闭状态,不能执行关闭.");
    }

    @Override
    public void goUp(Elevator elevator) {
        System.out.println("电梯开始上行.");
        elevator.setState(Elevator.upState);
    }

    @Override
    public void goDown(Elevator elevator) {
        System.out.println("电梯开始下行.");
        elevator.setState(Elevator.downState);
    }

    @Override
    public void stop(Elevator elevator) {
        System.out.println("已经是停止状态,无需操作.");
    }
}
