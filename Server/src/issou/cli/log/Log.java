package issou.cli.log;

public class Log {

    private final LogDest logDest;
    private final String msg;

    public Log(LogDest logDest, String msg){
        this.logDest = logDest;
        this.msg = msg;
    }

    @Override
    public String toString(){
        return logDest.name()+" : "+msg;
    }
}
