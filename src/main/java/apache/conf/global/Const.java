package apache.conf.global;

public class Const {
    public final static String newLine = System.getProperty("line.separator");
    public final static String staticModulesSearchString = ".*\\(static\\).*";
    public final static String sharedModulesSearchString = ".*\\(shared\\).*";
    public final static String staticModulesReplaceString = "\\(static\\)";
    public final static String sharedModulesReplaceString = "\\(shared\\)";
    public final static String staticModulesType = "static";
    public final static String sharedModulesType = "shared";
    public final static String replaceCommaSpacesRegex = "(\\s*,\\s*)";
    public final static String replaceSpacesInValuesRegex = "\\s+(?=([^\"']*[\"'][^\"']*[\"'])*[^\"']*$)";
    public final static String defineDirective = "Define";
}