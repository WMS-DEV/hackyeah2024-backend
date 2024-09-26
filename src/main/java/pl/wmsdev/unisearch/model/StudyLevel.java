package pl.wmsdev.unisearch.model;

public enum StudyLevel {
    UNDERGRAD("pierwszego stopnia"),
    GRADUATE("drugiego stopnia"),
    FULL("jednolite magisterskie");

    private String colName;

    StudyLevel(String name) {
        this.colName = name;
    }

    public String getColName() {
        return colName;
    }
}
