package visotor;

public class SafetyInspector implements Visitor{

    @Override
    public void visit(RollerCoaster rollerCoaster) {
        System.out.println("师傅检修过山车,掉了个轮子");

    }

    @Override
    public void visit(FerrisWheel ferrisWheel) {
        System.out.println("师傅检修摩天轮,掉了个螺丝");

    }

    @Override
    public void visit(MerryGoRound merryGoRound) {
        System.out.println("师傅检修旋转木马,掉了个弹簧!!");

    }
}
