package visotor;

public class Tourist implements Visitor{

    @Override
    public void visit(RollerCoaster rollerCoaster) {
        System.out.println("游客玩过山车,惊叫连连");

    }

    @Override
    public void visit(FerrisWheel ferrisWheel) {
        System.out.println("游客玩摩天轮,心情愉悦");

    }

    @Override
    public void visit(MerryGoRound merryGoRound) {
        System.out.println("游客玩旋转木马,好玩!!");

    }
}
