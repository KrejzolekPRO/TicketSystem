package pl.krejzolekpro.system.objects;

import pl.krejzolekpro.system.objects.utils.TicketUtil;
import pl.krejzolekpro.system.utils.IDUtil;

public class Ticket {

    private String ID;
    private long timeStart;
    private long timeEnd;
    private double price;

    public Ticket(long timeStart, long time, double price) {
        ID = IDUtil.generate();
        this.timeStart = timeStart;
        this.timeEnd = timeStart + time;
        this.price = price;
        TicketUtil.add(this);
    }

    public String getID() {
        return ID;
    }

    public long getTimeStart() {
        return timeStart;
    }

    public long getTimeEnd() {
        return timeEnd;
    }

    public double getPrice() {
        return price;
    }

    public boolean delete(){
        if(TicketUtil.ticketList.contains(this)){
            TicketUtil.ticketList.remove(this);
            return true;
        }
        return false;
    }
}
