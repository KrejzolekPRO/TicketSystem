package pl.krejzolekpro.system.utils;

import pl.krejzolekpro.system.objects.Ticket;
import pl.krejzolekpro.system.objects.utils.TicketUtil;

import java.util.Random;

public class IDUtil {

    private static String alphabet = "ABCDEFGHIJKLMNOPQRSTUWZ";
    private static Random random = new Random();

    public static String generate(){
        StringBuilder id = new StringBuilder();
        while(id.length() != 3){
            id.append(alphabet.charAt(random.nextInt(alphabet.length()-1)+1));
        }
        id.append(random.nextInt(89)+10);
        if(isIDSet(id.toString())){
            return generate();
        }
        return id.toString();
    }

    private static Boolean isIDSet(String ID){
        for(Ticket ticket : TicketUtil.ticketList){
            if(ticket.getID().equals(ID)){
                return true;
            }
        }
        return false;
    }
}
