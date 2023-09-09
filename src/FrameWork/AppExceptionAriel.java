package FrameWork;

public class AppExceptionAriel extends Exception  {

    public AppExceptionAriel(Throwable cause, Object className, String message) {
        super(className +":"+message, cause);
        setDebuggingLog();
    }

    public AppExceptionAriel(Throwable cause, Object className) {
        super(className +":" + "Error no especificado", cause);
        setDebuggingLog();
    }

    public AppExceptionAriel(String message) {
        super("Error en clase no especificada "+":"+message);
        setDebuggingLog();
    }

    void setDebuggingLog(){
        System.out.println("-------------------------");
        System.out.println("{AppExceptionAriel}");
        System.out.println(getMessage());
        System.out.println(getCause());
        System.out.println("-------------------------");
    }
}
