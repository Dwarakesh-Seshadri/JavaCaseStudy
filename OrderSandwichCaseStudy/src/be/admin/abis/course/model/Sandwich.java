package be.admin.abis.course.model;

import be.admin.abis.course.enumeration.Language;

public class Sandwich {
    private int itemNumber;
    private String typeNed;
    private String nameNed;
    private String typeFr;
    private String namefr;
    private boolean veggie;
    private boolean breadType;
    private double price;
    private static int counter = 0;

    public int getItemNumber() {
        return itemNumber;
    }

    public Sandwich() {
        itemNumber = ++counter;
    }

    public String getTypeNed() {
        return typeNed;
    }

    public void setTypeNed(String typeNed) {
        this.typeNed = typeNed;
    }

    public String getNameNed() {
        return nameNed;
    }

    public void setNameNed(String nameNed) {
        this.nameNed = nameNed;
    }

    public String getTypeFr() {
        return typeFr;
    }

    public void setTypeFr(String typeFr) {
        this.typeFr = typeFr;
    }

    public String getNamefr() {
        return namefr;
    }

    public void setNamefr(String namefr) {
        this.namefr = namefr;
    }

    public boolean isVeggie() {
        return veggie;
    }

    public void setVeggie(boolean veggie) {
        this.veggie = veggie;
    }

    public boolean isBreadType() {
        return breadType;
    }

    public void setBreadType(boolean breadType) {
        this.breadType = breadType;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }


    @Override
    public String toString() {
        return "Sandwich{" +
                "itemNumber=" + itemNumber +
                ", typeNed='" + typeNed + '\'' +
                ", nameNed='" + nameNed + '\'' +
                ", typeFr='" + typeFr + '\'' +
                ", namefr='" + namefr + '\'' +
                ", veggie=" + veggie +
                ", breadType=" + breadType +
                ", price=" + price +
                '}';
    }

    public String formatSandwich(Language language){
        if (language == Language.Nederlands){
            return (itemNumber + " " + typeNed + " " + nameNed);
        } else {
            return (itemNumber + " " + typeFr + " " + namefr);
        }
    }
}
