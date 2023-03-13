import java.io.*;
import java.util.Collection;

public class TheaterHelper {
    public static Show findByName(Collection<Show> shows, String name) {
        for (Show show : shows) {
            if (show.getName().equals(name)) {
                return show;
            }
        }
        return null;
    }

    public static double calculateRevenue(Collection<Ticket> tickets, Show show) {
        double revenue = 0;
        for (Ticket ticket : tickets) {
            if (ticket.getShow().equals(show.getName())) {
                revenue += ticket.getPrice();
            }
        }
        return revenue;
    }



    public static void saveToFile(Collection<Show> shows, Collection<Ticket> tickets) {
        try {
            FileOutputStream fileOut = new FileOutputStream("theater.dat");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(shows);
            out.writeObject(tickets);
            out.close();
            fileOut.close();
        } catch (IOException i) {
            i.printStackTrace();
        }
    }

    public static void restoreFromFile() {
        try {
            FileInputStream fileIn = new FileInputStream("theater.dat");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            Collection<Show> shows = (Collection<Show>) in.readObject();
            Collection<Ticket> tickets = (Collection<Ticket>) in.readObject();
            in.close();
            fileIn.close();
        } catch (IOException i) {
            i.printStackTrace();
            return;
        } catch (ClassNotFoundException c) {
            System.out.println("Class not found");
            c.printStackTrace();
            return;
        }
    }
}
