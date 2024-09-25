package ru.akvine.trimly.constants;

import java.util.regex.Pattern;

public final class RegExpConstants {
    public final static Pattern httpsUrlPattern = Pattern.compile("^https:\\/\\/[a-zA-Z0-9\\-_.]+(\\.[a-zA-Z0-9\\-_.]+)+(\\/[a-zA-Z0-9\\-_.@?^=%&:~+#]*)?$");
    public final static Pattern httpUrlPattern = Pattern.compile("^http:\\/\\/[a-zA-Z0-9\\-_.]+(\\.[a-zA-Z0-9\\-_.]+)+(\\/[a-zA-Z0-9\\-_.@?^=%&:~+#]*)?$");
}
