package visotor;

public class RollerCoaster implements Element {
    private final String name = "RollerCoaster";

    public String getName() {
        return name;
    }
    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}
