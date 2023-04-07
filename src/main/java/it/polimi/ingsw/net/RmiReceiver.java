package it.polimi.ingsw.net;

import java.beans.Statement;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.concurrent.ExecutorService;

public class RmiReceiver<L extends RemoteInterface, R extends  RemoteInterface> implements InvocationHandler {

    private final ExecutorService executorService;
    private final L localTarget;
    private RmiConnectionManager<L,R> rmiConnectionManager;
    public RmiReceiver(ExecutorService executorService, L localTarget){
        this.executorService = executorService;
        this.localTarget = localTarget;
    }

    public void setRmiConnectionManager(RmiConnectionManager<L,R> rmiConnectionManager){
        this.rmiConnectionManager = rmiConnectionManager;
    }
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) {
        try {
            java.beans.Statement st = new Statement(localTarget, method.getName(), args);
            executorService.submit(()->{
                try {
                    st.execute();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
        }catch(Exception ex){rmiConnectionManager.connectionDown();}
        return null;
    }
}
