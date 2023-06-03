import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class ReservationViewer {

    private JFrame frame;
    private JTextArea textArea;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    ReservationViewer window = new ReservationViewer();
                    window.frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public ReservationViewer() {
        initialize();
    }

    private void initialize() {
        frame = new JFrame();
        frame.setBounds(100, 100, 450, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(10, 11, 414, 201);
        frame.getContentPane().add(scrollPane);

        textArea = new JTextArea();
        scrollPane.setViewportView(textArea);

        JButton btnLoadReservations = new JButton("Load Reservations");
        btnLoadReservations.setBounds(160, 223, 130, 23);
        frame.getContentPane().add(btnLoadReservations);

        btnLoadReservations.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                List<String> reservations = loadReservations();
                reservations = removeDuplicateReservations(reservations);
                sortReservations(reservations);
                displayReservations(reservations);
            }
        });
    }

    private List<String> loadReservations() {
        List<String> reservations = new ArrayList<>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader("reservations.txt"));
            String line;
            while ((line = reader.readLine()) != null) {
                reservations.add(line);
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return reservations;
    }

    private List<String> removeDuplicateReservations(List<String> reservations) {
        Set<String> uniqueReservations = new TreeSet<>();
        for (String reservation : reservations) {
            uniqueReservations.add(reservation);
        }
        return new ArrayList<>(uniqueReservations);
    }

    private void sortReservations(List<String> reservations) {
        SimpleDateFormat format = new SimpleDateFormat("y-MM-dd HH:mm");
        reservations.sort(new Comparator<String>() {
            public int compare(String r1, String r2) {
                try {
                    Date d1 = format.parse(r1.split(" - ")[0]);
                    Date d2 = format.parse(r2.split(" - ")[0]);
                    return d1.compareTo(d2);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                return 0;
            }
        });
    }

    private void displayReservations(List<String> reservations) {
        StringBuilder sb = new StringBuilder();
        SimpleDateFormat inputFormat = new SimpleDateFormat("y-MM-dd HH:mm");
        SimpleDateFormat outputFormat = new SimpleDateFormat("MMMM d, y - HH:mm");
        for (String reservation : reservations) {
            String[] parts = reservation.split(" - ");
            try {
                Date startDate = inputFormat.parse(parts[0]);
                Date endDate = inputFormat.parse(parts[1]);
                String formattedReservation = outputFormat.format(startDate) + " - " + outputFormat.format(endDate);
                sb.append(formattedReservation).append("\n\n");
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        textArea.setText(sb.toString());
    }
}
