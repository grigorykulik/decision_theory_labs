// Класс элемента матрицы.
public class Element {
    //Доход при соответствующем размере спроса и заказа
    private double value;
    //Размер спроса
    private double demand;
    //Размер заказа
    private double order;


    public Element () {}

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public double getDemand() {
        return demand;
    }

    public void setDemand(double demand) {
        this.demand = demand;
    }

    public double getOrder() {
        return order;
    }

    public void setOrder(double order) {
        this.order = order;
    }
}