package visotor;

import java.util.ArrayList;
import java.util.List;

public class AmusementPark {
    List<Element> elementList = new ArrayList<>();

    public void addAttraction(Element element) {
        elementList.add(element);
    }

    // 让所有设施接受同一个访问者
    public void accept(Visitor visitor) {
        for (Element attraction : elementList) {
            attraction.accept(visitor);
        }
    }
}
