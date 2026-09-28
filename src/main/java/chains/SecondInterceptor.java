package chains;

public class SecondInterceptor implements Interceptor{
    @Override
    public Result intercept(Chain chain) {
        SendTxt sendTxt = chain.sendTxt();
        Result result = new Result();
        result.id  = sendTxt.id;
        result.result = "Second " + chain.proceed(sendTxt).result;
        return result;
    }
}
