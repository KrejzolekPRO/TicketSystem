package pl.krejzolekpro.system.utils;

import pl.krejzolekpro.system.objects.Ticket;

public class MessageUtil {

    private static MessageUtil instance;

    public MessageUtil(){
        instance = this;
    }

    public static MessageUtil getInstance(){
        if(instance == null){
            return new MessageUtil();
        }
        return instance;
    }

    public void sendTicketInformation(Ticket ticket){
        send("  Numer identyfikacji: #" + ticket.getID() + ".", true);
        send("  Zakupiono dnia: " + TimeUtil.parseTime(ticket.getTimeStart()) + ".", true);
        send("  Data rozpoczecia: " + TimeUtil.parseTime(ticket.getTimeStart()) + ".", true);
        send("  Data zakonczenia: " + TimeUtil.parseTime(ticket.getTimeEnd()) + ".", true);
        send("  Cena: " + ticket.getPrice() + "zl.", true);
        send("  Waznosc: " + (ticket.getTimeEnd() < System.currentTimeMillis() ? "bilet jest nie wazny." : "bilet jest wazny."), true);
    }

    public void send(String text, boolean sendtag){
        System.out.println((sendtag ? "[TicketSystem] " : "") + text);
    }
}
