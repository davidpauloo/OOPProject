import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import com.toedter.calendar.JDateChooser;
import java.awt.Color;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JOptionPane;

public class checker {

    private JFrame frmCalendar;
    private List<String> reservations;
    private Date selectedDate;
    private String selectedStartTime;
    private String selectedEndTime;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    checker window = new checker();
                    window.frmCalendar.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public checker() {
        initialize();
        reservations = loadReservations();
    }

    private void initialize() {
        frmCalendar = new JFrame();
        frmCalendar.setTitle("Calendar");
        frmCalendar.setBounds(100, 100, 1000, 600);
        frmCalendar.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frmCalendar.getContentPane().setLayout(null);
        frmCalendar.setLocationRelativeTo(null);

        List<String> startOptions = generateTimeOptionsStart();
        DefaultComboBoxModel<String> comboBoxModelStart = new DefaultComboBoxModel<>(startOptions.toArray(new String[0]));

        List<String> endOptions = generateTimeOptionsEnd();
        DefaultComboBoxModel<String> comboBoxModelEnd = new DefaultComboBoxModel<>(endOptions.toArray(new String[0]));

        JDateChooser daychecker = new JDateChooser();
        daychecker.getCalendarButton().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                selectedDate = daychecker.getDate();
                String selectedDateString = String.format("Selected Date: %tF", selectedDate);
                System.out.println(selectedDateString);

                try {
                    FileWriter writer = new FileWriter("date_time.txt", true);
                    writer.write(selectedDateString + "\n");
                    writer.close();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });
        daychecker.setBounds(218, 205, 243, 20);
        frmCalendar.getContentPane().add(daychecker);
        daychecker.setForeground(new Color(0, 0, 0));
        daychecker.setDateFormatString("MMMM d, y");

        JComboBox<String> startBox = new JComboBox<>(comboBoxModelStart);
        startBox.setBounds(507, 205, 195, 22);
        frmCalendar.getContentPane().add(startBox);

        JComboBox<String> endBox = new JComboBox<>(comboBoxModelEnd);
        endBox.setBounds(507, 266, 195, 22);
        frmCalendar.getContentPane().add(endBox);

        JButton btnNewButton = new JButton("Check Availability");
        btnNewButton.setBounds(403, 326, 146, 23);
        frmCalendar.getContentPane().add(btnNewButton);

        JLabel lblNewLabel = new JLabel("New label");
        lblNewLabel.setIcon(new ImageIcon(checker.class.getResource("/IMAGES/checkingpage.png")));
        lblNewLabel.setBounds(0, 0, 984, 594);
        frmCalendar.getContentPane().add(lblNewLabel);

        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (selectedDate != null && selectedStartTime != null && selectedEndTime != null) {
                    String selectedStartDateTime = getFormattedDateTime(selectedDate, selectedStartTime);
                    String selectedEndDateTime = getFormattedDateTime(selectedDate, selectedEndTime);
                    if (isTimeRangeAvailable(selectedStartDateTime, selectedEndDateTime)) {
                        String selectedDateTime = selectedStartDateTime + " - " + selectedEndDateTime;
                        reservations.add(selectedDateTime);
                        saveReservations(reservations);
                        JOptionPane.showMessageDialog(frmCalendar, "Date and time are available. Reservation made.", "Success", JOptionPane.INFORMATION_MESSAGE);
                        try {
                            FileWriter writer = new FileWriter("reservations.txt", true);
                            writer.write(selectedDateTime + "\n");
                            writer.close();
                        } catch (IOException ex) {
                            ex.printStackTrace();
                        }
                    } else {
                        JOptionPane.showMessageDialog(frmCalendar, "Date and time are not available.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(frmCalendar, "Please select a date and time range.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        endBox.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                selectedEndTime = (String) endBox.getSelectedItem();
                System.out.println("Selected end time: " + selectedEndTime);
            }
        });

        startBox.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                selectedStartTime = (String) startBox.getSelectedItem();
                System.out.println("Selected start time: " + selectedStartTime);
            }
        });
    }

    private List<String> generateTimeOptionsStart() {
        List<String> options = new ArrayList<>();
        for (int hour = 7; hour <= 22; hour++) {
            options.add(String.format("%02d:00", hour));
        }
        return options;
    }

    private List<String> generateTimeOptionsEnd() {
        List<String> options = new ArrayList<>();
        for (int hour = 7; hour <= 22; hour++) {
            options.add(String.format("%02d:00", hour));
        }
        return options;
    }

    private String getFormattedDateTime(Date date, String time) {
        return String.format("%tF %s", date, time);
    }

    private boolean isTimeRangeAvailable(String startDateTime, String endDateTime) {
        for (String reservation : reservations) {
            String[] parts = reservation.split(" - ");
            String existingStartDateTime = parts[0];
            String existingEndDateTime = parts[1];
            if (isDateTimeOverlap(startDateTime, endDateTime, existingStartDateTime, existingEndDateTime)) {
                return false; // Reservation time range overlaps with existing reservation
            }
        }
        return true; // Reservation time range is available
    }

    private boolean isDateTimeOverlap(String startDateTime1, String endDateTime1, String startDateTime2, String endDateTime2) {
        // Convert date-time strings to Date objects for comparison
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        try {
            Date start1 = format.parse(startDateTime1);
            Date end1 = format.parse(endDateTime1);
            Date start2 = format.parse(startDateTime2);
            Date end2 = format.parse(endDateTime2);

            // Check for overlap
            return start1.before(end2) && start2.before(end1);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return false;
    }

    private List<String> loadReservations() {
        List<String> reservations = new ArrayList<>();
        try {
            Path path = Paths.get("reservations.txt");
            if (Files.exists(path)) {
                reservations = Files.readAllLines(path);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return reservations;
    }

    private void saveReservations(List<String> reservations) {
        try {
            FileWriter writer = new FileWriter("reservations.txt");
            for (String reservation : reservations) {
                writer.write(reservation + "\n");
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
