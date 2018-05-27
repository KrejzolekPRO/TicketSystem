package pl.krejzolekpro.system;

import pl.krejzolekpro.system.data.FileManager;
import pl.krejzolekpro.system.objects.Ticket;
import pl.krejzolekpro.system.objects.utils.TicketUtil;
import pl.krejzolekpro.system.utils.MessageUtil;
import pl.krejzolekpro.system.utils.PriceUtil;
import pl.krejzolekpro.system.utils.TicketGUI;
import pl.krejzolekpro.system.utils.TimeUtil;

import java.util.Scanner;

public class Main {

    private static MessageUtil messageUtil = MessageUtil.getInstance();
    //private static TicketGUI ticketGUI = TicketGUI.getInstance();
    private static PriceUtil priceUtil = PriceUtil.getInstance();
    private static FileManager fileManager = FileManager.getInstance();

    public static void main(String[] args) {
        //ticketGUI.showMainGUI();
        fileManager.load();
	    Scanner scanner = new Scanner(System.in);
        messageUtil.send(" ----------- [Glowne komendy] ----------- ", false);
        messageUtil.send("  > dodaj - tworzy nowy bilet.", true);
        messageUtil.send("  > lista - wyswietla wszystkie bilety.", true);
        messageUtil.send("  > sprawdz - sprawdza pojedynczy bilet.", true);
        messageUtil.send("  > usun - usuwa dany bilet.", true);
        messageUtil.send("  > cena - zmien cene, lub zobacz informacje o niej.", true);
        messageUtil.send("  > wyczysc - czysci ekran.", true);
        messageUtil.send("  > pomoc - wyswietla pomocne komendy.", true);
        messageUtil.send("  Komenda 'anuluj' - anuluje dana operacje.", true);
        messageUtil.send(" ----------- [Glowne komendy] ----------- ", false);
	    while(scanner.hasNextLine()){
	        switch(scanner.nextLine().toLowerCase()){
                case "dodaj":
                    messageUtil.send("Podaj czas biletu w minutach i obnizke w formacie (czas obnizka) - (100 10) - 100 zlotych i 10 procent obniżki.", true);
                    try{
                        String[] ints = scanner.nextLine().split(" ");
                        int minutes = Integer.parseInt(ints[0]);
                        double discount = Double.parseDouble(ints[1]);
                        long time = minutes * 60 * 1000;
                        Ticket ticket = new Ticket(System.currentTimeMillis(), time, priceUtil.calculatePrice(minutes, discount));
                        messageUtil.send(" -----------", false);
                        messageUtil.send("Stworzono nowy bilet o ID: #" + ticket.getID() + ".", true);
                        messageUtil.send("Do zaplaty: " + priceUtil.calculatePrice(minutes, discount) + "zl.", true);
                        messageUtil.send("Bilet wazny do: " + TimeUtil.parseTime(ticket.getTimeEnd()) + ".", true);
                        messageUtil.send(" -----------", false);
                        break;
                    }catch(Exception e){
                        messageUtil.send("Format jest nieporawny. Dodawanie anulowane.", true);
                        break;
                    }
                case "lista":
                    if(TicketUtil.ticketList.size() == 0 || TicketUtil.ticketList == null){
                        messageUtil.send("Nie ma zadnego biletu.", true);
                        break;
                    }
                    messageUtil.send("Lista wszystkich biletow: (" + TicketUtil.ticketList.size() + ")", true);
                    for(Ticket ticket : TicketUtil.ticketList){
                        messageUtil.send(" -----------", false);
                        messageUtil.sendTicketInformation(ticket);
                        messageUtil.send(" -----------", false);
                    }
                    break;
                case "sprawdz":
                    messageUtil.send("Podaj ID biletu, aby wyswietlic informacje o nim.", true);
                    String IDinfo = scanner.nextLine();
                    if(TicketUtil.getTicketByID(IDinfo) == null){
                        messageUtil.send("Taki bilet nie istnieje. Powrocono do glownego menu.", true);
                        break;
                    }
                    messageUtil.send(" -----------", false);
                    messageUtil.sendTicketInformation(TicketUtil.getTicketByID(IDinfo));
                    messageUtil.send(" -----------", false);
                    break;
                case "usun":
                    messageUtil.send("Podaj ID pojedynczego biletu, aby go usunac, lub wpisz 'ALL', aby usunac wszystkie (wymagane dodatkowe potwierdzenie.)", true);
                    String IDdelete = scanner.nextLine();
                    if(IDdelete.equals("ALL")){
                        messageUtil.send("Czy na pewno chcesz usunac " + TicketUtil.ticketList.size() + " biletow? (tak/nie)", true);
                        String validity = scanner.nextLine();
                        if(validity.equals("tak")){
                            Long now = System.currentTimeMillis();
                            TicketUtil.ticketList.removeAll(TicketUtil.ticketList);
                            messageUtil.send(" -----------", false);
                            messageUtil.send("Usunieto wszystkie bilety w czasie " + (System.currentTimeMillis() - now) + "ms.", true);
                            messageUtil.send(" -----------", false);
                            break;
                        }
                        messageUtil.send(" -----------", false);
                        messageUtil.send("Usuwanie wszystkich biletow zostalo anulowane.", true);
                        messageUtil.send(" -----------", false);
                        break;
                    }
                    if(TicketUtil.getTicketByID(IDdelete) == null){
                        messageUtil.send("Taki bilet nie istnieje.", true);
                        break;
                    }
                    if(TicketUtil.getTicketByID(IDdelete).delete()){
                        messageUtil.send(" -----------", false);
                        messageUtil.send("Usunieto bilet o ID: #" + IDdelete + ".", true);
                        messageUtil.send(" -----------", false);
                        break;
                    }
                    messageUtil.send("Wystapil blad, bilet nie zostal usuniety. Powrocono do glownego menu.", true);
                    break;
                case "cena":
                    messageUtil.send("Podaj argument. (ustaw, informacje)", true);
                    String argument = scanner.nextLine();
                    if(argument.equals("ustaw")) {
                        messageUtil.send("Podaj cene za jedna minute waznego biletu. (Nie uzywaj kropki, uzywaj PRZECINKA)", true);
                        try {
                            Double price = scanner.nextDouble();
                            if (!priceUtil.setMinutePrice(price)) {
                                messageUtil.send("Nieprawidlowy format. Powrocono do glownego menu.", true);
                                break;
                            }
                            messageUtil.send(" -----------", false);
                            messageUtil.send("Ustawiono nowa cene na: " + price + "zl.", true);
                            messageUtil.send(" -----------", false);
                            break;
                        } catch (Exception e) {
                            messageUtil.send("Nieprawidlowy format. Powrocono do glownego menu.", true);
                            break;
                        }
                    }
                    if (argument.equals("informacje")) {
                        messageUtil.send(" -----------", false);
                        messageUtil.send("Aktualna cena wynosi: " + priceUtil.minutePrice + "zl.", true);
                        messageUtil.send(" -----------", false);
                        break;
                    }
                    messageUtil.send("Niepoprawny argument. Powrocono do glownego menu.", true);
                    break;
                case "wyczysc":
                    for(int i=0; i<100; i++){
                        messageUtil.send(" ", false);
                    }
                    messageUtil.send(" ----------- [Glowne komendy] ----------- ", false);
                    messageUtil.send("  > dodaj - tworzy nowy bilet.", true);
                    messageUtil.send("  > lista - wyswietla wszystkie bilety.", true);
                    messageUtil.send("  > sprawdz - sprawdza pojedynczy bilet.", true);
                    messageUtil.send("  > usun - usuwa dany bilet.", true);
                    messageUtil.send("  > cena - zmien cene, lub zobacz informacje o niej.", true);
                    messageUtil.send("  > wyczysc - czysci ekran.", true);
                    messageUtil.send("  > pomoc - wyswietla pomocne komendy.", true);
                    messageUtil.send("  Komenda 'anuluj' - anuluje dana operacje.", true);
                    messageUtil.send(" ----------- [Glowne komendy] ----------- ", false);
                    break;
                case "pomoc":
                    messageUtil.send(" ----------- [Glowne komendy] ----------- ", false);
                    messageUtil.send("  > dodaj - tworzy nowy bilet.", true);
                    messageUtil.send("  > lista - wyswietla wszystkie bilety.", true);
                    messageUtil.send("  > sprawdz - sprawdza pojedynczy bilet.", true);
                    messageUtil.send("  > usun - usuwa dany bilet.", true);
                    messageUtil.send("  > cena - zmien cene, lub zobacz informacje o niej.", true);
                    messageUtil.send("  > wyczysc - czysci ekran.", true);
                    messageUtil.send("  > pomoc - wyswietla pomocne komendy.", true);
                    messageUtil.send("  Komenda 'anuluj' - anuluje dana operacje.", true);
                    messageUtil.send(" ----------- [Glowne komendy] ----------- ", false);
                    break;
            }
        }
    }
}
