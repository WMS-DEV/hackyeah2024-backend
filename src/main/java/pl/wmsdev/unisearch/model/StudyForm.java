package pl.wmsdev.unisearch.model;

public enum StudyForm {
    STATIONARY("stacjonarne"),
    REMOTE("niestacjonarne");

    private String colName;

    StudyForm(String colName) {
        this.colName = colName;
    }

    public String getColName() {
        return colName;
    }
}
