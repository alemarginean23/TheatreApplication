import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Scanner;

public class TheaterApplication {

    static Collection<Show> shows;
    private static Collection<Ticket> tickets;

    public static void main(String[] args) {
        // Restore data from file if it exists
        restoreData();

        // Initialize collections if they are null
        if (shows == null) {
            shows = new ArrayList<>();
        }
        if (tickets == null) {
            tickets = new ArrayList<>();
        }

        // Start application and handle user input
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.print("> ");
            String input = scanner.nextLine();
            String[] tokens = input.split(" ");
            if (tokens.length == 0) {
                continue;
            }
            String command = tokens[0];
            switch (command) {
                case "mt":
                    handleTicketCommand(tokens);
                    break;
                case "ms":
                    handleShowCommand(tokens);
                    break;
                case "st":
                    handleListCommand(tokens);
                    break;
                case "am":
                    handleAmountCommand(tokens);
                    break;
                case "x":
                    handleExitCommand();
                    return;
                default:
                    System.out.println("Invalid command. Please try again.");
                    break;
            }
        }
    }

    private static void handleTicketCommand(String[] command) {
        if (command.length < 5) {
            System.out.println("Invalid command format. Correct format: mt [adult/child/student] <play_name> <date_time> <seat>");
            return;
        }

        TicketType ticketType;
        switch (command[1].toLowerCase()) {
            case "adult":
                ticketType = TicketType.ADULT;
                break;
            case "child":
                ticketType = TicketType.CHILD;
                break;
            case "student":
                ticketType = TicketType.STUDENT;
                break;
            default:
                System.out.println("Invalid ticket type. Must be 'adult', 'child', or 'student'.");
                return;
        }

        Show show = TheaterHelper.findByName(shows, command[2]);
        if (show == null) {
            System.out.println("Show not found.");
            return;
        }

        LocalDateTime dateTime;
        try {
            dateTime = LocalDateTime.parse(command[3], DateTimeFormatter.ofPattern("dd/MM/yy-HH:mm"));
        } catch (DateTimeParseException e) {
            System.out.println("Invalid date and time format. Correct format: dd/mm/yy-hh:mm");
            return;
        }

        String[] seatTokens = command[4].split("-");
        if (seatTokens.length != 2) {
            System.out.println("Invalid seat format. Correct format: row-number");
            return;
        }

        int row, number;
        try {
            row = Integer.parseInt(seatTokens[0]);
            number = Integer.parseInt(seatTokens[1]);
        } catch (NumberFormatException e) {
            System.out.println("Invalid seat format. Row and number must be integers.");
            return;
        }

        if (row < 1 || row > 30 || number < 1 || number > 18) {
            System.out.println("Invalid seat. Must be between 1-30 for row and 1-18 for number.");
            return;
        }

        Seat seat = new Seat(row, number);
        Ticket ticket = new Ticket(show, dateTime, seat, ticketType);
        tickets.add(ticket);
        System.out.println("Ticket created successfully for show " + show.getName() + " on " + dateTime.format(DateTimeFormatter.ofPattern("dd/MM/yy HH:mm")) + " at seat " + seat);
    }

    private static void handleShowCommand(String[] tokens) {
        if (tokens.length != 4) {
            System.out.println("Invalid command. Correct format is: ms <play_name> <director> <description>");
            return;
        }
        String playName = tokens[1];
        String director = tokens[2];
        String description = tokens[3];
        Show show = new Show(playName, director, description);
        shows.add(show);
        System.out.println("Show added successfully");
    }


    private static void handleListCommand(String[] tokens) {
        if (tokens.length == 1) {
            // list all shows
            for (Show show : shows) {
                System.out.println(show);
            }
        } else if (tokens.length == 2) {
            // list tickets for a specific show
            Show show = TheaterHelper.findByName(shows, tokens[1]);
            if (show == null) {
                System.out.println("Show not found");
                return;
            }
            for (Ticket ticket : tickets) {
                if (ticket.getShow().equals(show)) {
                    System.out.println(ticket);
                }
            }
        } else {
            System.out.println("Invalid command");
        }
    }



    private static void handleAmountCommand(String[] tokens) {
        if (tokens.length < 2) {
            System.out.println("Invalid command format. Use 'amount <show name>'");
            return;
        }
        String name = tokens[1];
        Show show = TheaterHelper.findByName(shows, name);
        if (show == null) {
            System.out.println("Show not found");
            return;
        }
        double totalAmount = 0.0;
        for (Ticket ticket : tickets) {
            if (ticket.getShow() == show) {
                totalAmount += ticket.getPrice();
            }
        }
        System.out.println("Total amount made from show '" + name + "' : $" + totalAmount);
    }



    private static void handleExitCommand() {
        // Implement handling of exit command here
    }

    private static void restoreData() {
        // Implement restoring data from file here
    }

    private static void saveData() {
        // Implement saving data to file here
    }
}