package vn.co.vis.common.log;

public enum LoggerType {
    REQUEST("requestLog"),
    APPLICATION("applicationLog"),
    API("apiLog"),
    SQL("sqlLog");

    private final String loggerName;

    LoggerType(String loggerName) {
        this.loggerName = loggerName;
    }

    public String getLoggerName() {
        return loggerName;
    }
}
