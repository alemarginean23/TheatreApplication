import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Show {
    private String name;
    private String director;
    private String description;
    private Collection<Ticket> tickets;

    public Show(String name, String director, String description) {
        this.name = name;
        this.director = director;
        this.description = description;
        this.tickets = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public String getDirector() {
        return director;
    }

    public void addTicket(Ticket ticket) {
        this.tickets.add(ticket);
    }

    public String getDescription() {
        return description;
    }

    public List<Ticket> getTickets() {
        return (List<Ticket>) tickets;
    }
}
