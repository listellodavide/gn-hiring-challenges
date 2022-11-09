package net.goldbach.streams.payload;

public class HotBeverageOrder {

    private int id;
    private String hotDrinkName;
    private Boolean withMilk;
    private Boolean withSugar;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getHotDrinkName() {
        return hotDrinkName;
    }

    public void setHotDrinkName(String hotDrinkName) {
        this.hotDrinkName = hotDrinkName;
    }

    public Boolean getWithMilk() {
        return withMilk;
    }

    public void setWithMilk(Boolean withMilk) {
        this.withMilk = withMilk;
    }

    public Boolean getWithSugar() {
        return withSugar;
    }

    public void setWithSugar(Boolean withSugar) {
        this.withSugar = withSugar;
    }

    @Override
    public String toString() {
        return "HotBeverageOrder{" +
                "id=" + id +
                ", hotDrinkName='" + hotDrinkName + '\'' +
                ", withMilk=" + withMilk +
                ", withSugar=" + withSugar +
                '}';
    }
}
