package it.unibo.oop.lab.advanced;

public enum MessageEnum {
/**
 * Error message.
 */
ERROR_MESSAGE("[ERROR]", "Error Message"),
/**
 * Warning message.
 */
WARNING_MESSAGE("[WARNING]", "Warning Message"),
/**
 * Warning message.
 */
INFO_MESSAGE("[INFO]", "Info Message");


private final String tag;
private final String description;

MessageEnum(final String tag, final String description) {
    this.tag = tag;
    this.description = description;
}

public String getTag() {
    return this.tag;
}

public String getDescription() {
    return this.description;
}

}
