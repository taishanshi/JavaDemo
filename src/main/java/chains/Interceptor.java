package chains;

public interface Interceptor {
    Result intercept(Chain chain);

    interface Chain {
        SendTxt sendTxt();
        Result proceed(SendTxt sendTxt);
    }
}
