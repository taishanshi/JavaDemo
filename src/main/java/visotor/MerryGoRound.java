package visotor;

public class MerryGoRound implements Element {
    private String name = "MerryGoRound";

    public String getName() {
        return name;
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}
