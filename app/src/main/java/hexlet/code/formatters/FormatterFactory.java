package hexlet.code.formatters;

public class FormatterFactory {
    public static Formatter getFormatter(String format) {
        if ("plain".equalsIgnoreCase(format)) {
            return new PlainFormatter();
        } else if ("json".equalsIgnoreCase(format)) {
            return new JsonFormatter();
        }
        // Можно добавить другие форматеры здесь
        return new StylishFormatter();
    }
}
