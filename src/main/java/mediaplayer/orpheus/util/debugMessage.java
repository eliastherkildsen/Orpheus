package mediaplayer.orpheus.util;

public class debugMessage {
    /**
     * Shows a formated Debug message in the cmd.
     * @param instance Object
     * @param message String
     */
    public static void debug (Object instance, String message){
        System.out.printf("%s[%s]%s %s%s%n", AnsiColorCode.ANSI_BLUE, instance.getClass().getSimpleName() , AnsiColorCode.ANSI_YELLOW, message, AnsiColorCode.ANSI_RESET);
    }
    /**
     * Shows a formated Debug message in the cmd.
     * @param instance Object
     * @param message String
     */
    public static void error (Object instance, String message){
        System.out.printf("%s[%s]%s %s%s%n", AnsiColorCode.ANSI_BLUE, instance.getClass().getSimpleName() , AnsiColorCode.ANSI_RED, message, AnsiColorCode.ANSI_RESET);
    }

    /**
     * Show a formated debug message in the cmd.
     * @param instance Object
     * @param message String
     */
    public static void success (Object instance, String message){
        System.out.printf("%s[%s]%s %s%s%n", AnsiColorCode.ANSI_GREEN, instance.getClass(), AnsiColorCode.ANSI_YELLOW, message, AnsiColorCode.ANSI_RESET);
    }
    /**
     * Shows a formated Debug message in the cmd.
     * To handle edge cases like static methods.
     * @param clazz Class
     * @param message String
     */
    public static void debug (Class<?> clazz, String message){
        System.out.printf("%s[%s]%s %s%s%n", AnsiColorCode.ANSI_BLUE, clazz.getSimpleName(), AnsiColorCode.ANSI_YELLOW, message, AnsiColorCode.ANSI_RESET);
    }

    /**
     * Shows a formated Debug message in the cmd.
     * To handle edge cases like Static methods.
     * @param clazz Class
     * @param message String
     */
    public static void error (Class<?> clazz, String message){
        System.out.printf("%s[%s]%s %s%s%n", AnsiColorCode.ANSI_RED, clazz.getSimpleName(), AnsiColorCode.ANSI_YELLOW, message, AnsiColorCode.ANSI_RESET);
    }
    /**
     * Shows a formated Debug message in the cmd.
     * To handle edge cases like Static methods.
     * @param clazz Class
     * @param message String
     */
    public static void success (Class<?> clazz, String message){
        System.out.printf("%s[%s]%s %s%s%n", AnsiColorCode.ANSI_GREEN, clazz.getSimpleName(), AnsiColorCode.ANSI_YELLOW, message, AnsiColorCode.ANSI_RESET);
    }
}
