package chains;

public class ThirdInterceptor implements Interceptor{
    @Override
    public Result intercept(Chain chain) {
        SendTxt sendTxt = chain.sendTxt();
        Result result = new Result();
        result.id  = sendTxt.id;
        result.result = "Third " + chain.proceed(sendTxt).result;
        return result;
    }
}
