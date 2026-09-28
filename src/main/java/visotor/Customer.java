package visotor;

public class Customer {
    public static void main(String[] args) {
        AmusementPark amusementPark = new AmusementPark();
        amusementPark.addAttraction(new RollerCoaster());
        amusementPark.addAttraction(new FerrisWheel());
        amusementPark.addAttraction(new MerryGoRound());

        Visitor visitor1 = new Tourist();
        Visitor visitor2 = new SafetyInspector();

        amusementPark.accept(visitor1);
        amusementPark.accept(visitor2);
    }
}
