package matyliano.enums;

public enum TaskStatus {
    TO_DO("To do"), IN_PROGRESS("in progress"), DONE("done");

    private final String value;

    TaskStatus(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
