package issou.cli;

public class Log {

    private final boolean first;
    private final String msg;

    public Log(boolean first, String msg){
        this.first = first;
        this.msg = msg;
    }

    @Override
    public String toString(){
        String first = this.first ? "P1" : "P2";
        return first+" : "+msg;
    }
}
