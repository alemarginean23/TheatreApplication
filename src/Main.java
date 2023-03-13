import java.io.*;
import java.time.LocalDateTime;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        // Initialize the shows collection
        TheaterApplication.shows = new ArrayList<Show>();

        // Create a new show
        Show show = new Show("The Lion King", "Julie Taymor", "A musical based on the Disney animated film.");

        // Add tickets for the show
        Ticket t1 = new Ticket(show, LocalDateTime.of(2022, 1, 1, 20, 0), new Seat(1, 1), TicketType.ADULT);
        Ticket t2 = new Ticket(show, LocalDateTime.of(2022, 1, 1, 20, 0), new Seat(1, 2), TicketType.CHILD);
        Ticket t3 = new Ticket(show, LocalDateTime.of(2022, 1, 1, 20, 0), new Seat(2, 1), TicketType.STUDENT);

        // Add the tickets to the show
        show.addTicket(t1);
        show.addTicket(t2);
        show.addTicket(t3);

        // Add the show to the shows collection
        TheaterApplication.shows.add(show);

        // Display the total amount of money made from ticket sales for the show
        System.out.println("Total Amount: " + TheaterHelper.calculateRevenue(show.getTickets(), show));
    }
}



