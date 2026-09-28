package state;

public class Elevator {
    public static DoorCloseState doorCloseState = new DoorCloseState();
    public static DoorOpenState doorOpenState = new DoorOpenState();
    public static DownState downState = new DownState();
    public static UpState upState = new UpState();
    public static StopState stopState = new StopState();

    private ElevatorState state;
    public Elevator() {
        this.state = stopState;
    }
    public ElevatorState getState() {
        return state;
    }

    public void setState(ElevatorState state) {
        this.state = state;
    }

    public void operate(int i) {
        if (i == 1) {
            state.openDoor(this);
        } else  if (i == 2) {
            state.closeDoor(this);
        }  else  if (i == 3) {
            state.goUp(this);
        }  else  if (i == 4) {
            state.goDown(this);
        }  else  if (i == 5) {
            state.stop(this);
        }
    }
}
