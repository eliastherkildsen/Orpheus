package mediaplayer.orpheus.util;

public class debugMessage {
    public static void debug (Object instance, String message){
        System.out.printf("%s[%s]%s %s%s%n", AnsiColorCode.ANSI_BLUE, instance.getClass().getSimpleName() , AnsiColorCode.ANSI_YELLOW, message, AnsiColorCode.ANSI_RESET);
    }
    public static void error (Object instance, String message){
        System.out.printf("%s[%s]%s %s%s%n", AnsiColorCode.ANSI_BLUE, instance.getClass().getName() , AnsiColorCode.ANSI_RED, message, AnsiColorCode.ANSI_RESET);
    }
}
