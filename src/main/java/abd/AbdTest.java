package abd;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class AbdTest {
    public static void main(String[] args) {
        Calculator calculator = new Calculator();
        RealService realService = new RealService();
        IService is = (IService)Proxy.newProxyInstance(
                realService.getClass().getClassLoader(),
                realService.getClass().getInterfaces(),
                new InvocationHandler() {
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

                        if(method.getName().equals("plus"))
                            return (int)method.invoke(realService, (int)args[0], args[1]) + 1;
                        return method.invoke(realService, args[0], args[1]);
                    }
                }
        );

        System.out.println(""+is.plus(1,2));
    }
}
