package chains;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;

public class Customer {
    public static void main(String[] args) {
        List<Interceptor> list = new ArrayList<>();
        list.add(new FirstInterceptor());
        list.add(new SecondInterceptor());
        list.add(new ThirdInterceptor());
        SendTxt sendTxt = new SendTxt();
        sendTxt.id = 1;
        sendTxt.txt = "text";
        RealChain realChain = new RealChain(list, 0, sendTxt);
        System.out.println(realChain.proceed(sendTxt).result);
    }
}
