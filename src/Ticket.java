import java.time.LocalDateTime;

public class Ticket {
    private Show show;
    private LocalDateTime dateTime;
    private Seat seat;
    private TicketType ticketType;
    private int price;

    public Ticket(Show show, LocalDateTime dateTime, Seat seat, TicketType ticketType) {
        this.show = show;
        this.dateTime = dateTime;
        this.seat = seat;
        this.ticketType = ticketType;
        switch (ticketType) {
            case ADULT:
                this.price = 50;
                break;
            case CHILD:
                this.price = 15;
                break;
            case STUDENT:
                this.price = 25;
                break;
            default:
                throw new IllegalArgumentException("Invalid ticket type");
        }
    }

    public Show getShow() {
        return show;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public Seat getSeat() {
        return seat;
    }

    public TicketType getTicketType() {
        return ticketType;
    }

    public int getPrice() {
        return price;
    }
}
