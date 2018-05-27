package pl.krejzolekpro.system.utils;

public class PriceUtil {

    private static PriceUtil instance;

    public Double minutePrice = 0.50;

    public PriceUtil(){
        instance = this;
    }

    public static PriceUtil getInstance(){
        if(instance == null){
            return new PriceUtil();
        }
        return instance;
    }

    public Double calculatePrice(int time, double discount){
        Double price = time * this.minutePrice;
        if(discount == 0D){
            return price;
        }
        discount = discount / 100;
        Double percent = 1D - discount;
        return price * percent;
    }

    public boolean setMinutePrice(Double price){
        if(price < 0){
            return false;
        }
        this.minutePrice = price;
        return true;
    }

}
