package pl.krejzolekpro.system.objects.utils;

import pl.krejzolekpro.system.objects.Ticket;

import java.util.ArrayList;
import java.util.List;

public class TicketUtil {

    public static List<Ticket> ticketList = new ArrayList<>();

    public static void add(Ticket ticket){
        if(ticketList.contains(ticket)){
            return;
        }
        ticketList.add(ticket);
    }

    public static Ticket getTicketByID(String ID){
        for(Ticket ticket : ticketList){
            if(ticket.getID().equals(ID)){
                return ticket;
            }
        }
        return null;
    }
}
