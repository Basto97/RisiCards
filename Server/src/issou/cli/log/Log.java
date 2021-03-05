package issou.cli.log;

public class Log {

    private final LogDest logDest;
    private final boolean needResponse;
    private final String msg;

    public Log(LogDest logDest, boolean needResponse, String msg){
        this.logDest = logDest;
        this.msg = msg;
        this.needResponse = needResponse;
    }

    public void sendResponse(String res){

    }

    public LogDest getLogDest(){
        return logDest;
    }

    public boolean isNeedResponse() {
        return needResponse;
    }

    @Override
    public String toString(){
        return logDest.name()+" : "+msg;
    }
}
