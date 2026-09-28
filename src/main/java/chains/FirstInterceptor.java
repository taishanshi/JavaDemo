package chains;

public class FirstInterceptor implements Interceptor {
    @Override
    public Result intercept(Chain chain) {
        SendTxt sendTxt = chain.sendTxt();
        Result result = new Result();
        result.id  = sendTxt.id;
        result.result = "First " + chain.proceed(sendTxt).result;
        return result;
    }
}
