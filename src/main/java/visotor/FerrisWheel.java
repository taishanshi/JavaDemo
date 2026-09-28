package visotor;

public class FerrisWheel implements Element{
    private final String name = "FerrisWheel";

    public String getName() {
        return name;
    }
    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}
