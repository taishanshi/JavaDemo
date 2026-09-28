package chains;

import java.util.ArrayList;
import java.util.List;

public class RealChain implements Interceptor.Chain {
    private List<Interceptor> interceptors = new ArrayList<>();
    private int index = 0;
    private SendTxt sendTxt;

    public RealChain(List<Interceptor> interceptors, int index, SendTxt sendTxt) {
        this.interceptors = interceptors;
        this.index = index;
        this.sendTxt = sendTxt;
    }

    @Override
    public SendTxt sendTxt() {
        return sendTxt;
    }

    @Override
    public Result proceed(SendTxt sendTxt) {
        if(index >= interceptors.size()) {
            Result result = new Result();
            result.id = index;
            result.result = "done";
            return result;
        }
        this.sendTxt = sendTxt;
        RealChain realChain = new RealChain(interceptors, index+1, sendTxt);
        Interceptor interceptor = realChain.interceptors.get(index);
        Result result = interceptor.intercept(realChain);
        return result;
    }
}
