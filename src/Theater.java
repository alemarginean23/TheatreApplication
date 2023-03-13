import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.io.Serializable;

public class Theater implements Serializable {

    private List<Show> shows;
    private List<Ticket> tickets;

    public Theater() {
        shows = new ArrayList<Show>();
        tickets = new ArrayList<Ticket>();
    }

    public void addShow(Show show) {
        shows.add(show);
    }

    public void addTicket(Ticket ticket) {
        tickets.add(ticket);
    }

    public Show findShowByName(String name) {
        return TheaterHelper.findByName(shows, name);
    }

    public List<Ticket> findTicketsByShowName(String name, String dateTime) {
        Show show = findShowByName(name);
        List<Ticket> foundTickets = new ArrayList<Ticket>();
        for (Ticket ticket : tickets) {
            if (ticket.getShow() == show && ticket.getDateTime().equals(dateTime)) {
                foundTickets.add(ticket);
            }
        }
        return foundTickets;
    }

    public int calculateMoneyByShowName(String name) {
        Show show = findShowByName(name);
        int money = 0;
        for (Ticket ticket : tickets) {
            if (ticket.getShow() == show) {
                money += ticket.getPrice();
            }
        }
        return money;
    }



}
