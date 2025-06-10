package be.admin.abis.course.enumeration;

public enum Language {

    Nederlands("NL"),
    French("FR");

    private String abbreviation;

    Language(String abbreviation) {
        this.abbreviation = abbreviation;
    }

    public String getAbbreviation() {
        return abbreviation;
    }

    public void setAbbreviation(String abbreviation) {
        this.abbreviation = abbreviation;
    }
}
