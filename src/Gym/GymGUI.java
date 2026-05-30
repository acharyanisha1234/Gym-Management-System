package Gym;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.io.*;

public class GymGUI {
    private JFrame frame;
    private ArrayList<Member> gymMembers = new ArrayList<>(); // Unified list for all members

    // Input fields
    private JTextField idField, nameField, locationField, phoneField, emailField, referralField, amountField,
            removalField, trainerField;
    private JComboBox<String> dobYearBox, dobMonthBox, dobDayBox;
    private JComboBox<String> msYearBox, msMonthBox, msDayBox;
    private JRadioButton maleBtn, femaleBtn, otherBtn;
    private ButtonGroup genderGroup;

    private JComboBox<String> planBox;

    public GymGUI() {
        frame = new JFrame("Gym Management System");
        frame.setSize(1000, 750);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);
        frame.getContentPane().setBackground(new Color(255, 255, 255));// light green color
        
        // Modern font
        Font labelFont = new Font("Segoe UI", Font.BOLD, 14);
        Font textFieldFont = new Font("Segoe UI", Font.PLAIN, 14);
        Font buttonFont = new Font("Segoe UI", Font.BOLD, 13);

        // Main panel with shadow effect
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(null);
        mainPanel.setBounds(20, 10, 940, 690);
        mainPanel.setBackground(new Color(255, 182, 193));
        mainPanel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(200, 200, 200)),
            BorderFactory.createEmptyBorder(15, 15, 15, 15)
        ));

        // Add title at the top
        JLabel titleLabel = new JLabel("Gym Management System", JLabel.CENTER);
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 24)); // Larger font for the title
        titleLabel.setForeground(new Color(50, 50, 50)); // Dark gray color
        titleLabel.setBounds(10, 5, 900, 35); // Position at the top of the frame
        mainPanel.add(titleLabel);

        // Section titles - adjusted for title space
        JLabel personalInfoLabel = new JLabel("Personal Information");
        personalInfoLabel.setFont(new Font("Segoe UI", Font.BOLD, 16));
        personalInfoLabel.setForeground(new Color(70, 70, 70));
        personalInfoLabel.setBounds(20, 50, 300, 25);

        JLabel membershipInfoLabel = new JLabel("Membership Information");
        membershipInfoLabel.setFont(new Font("Segoe UI", Font.BOLD, 16));
        membershipInfoLabel.setForeground(new Color(70, 70, 70));
        membershipInfoLabel.setBounds(400, 50, 300, 25);

        // ID - adjusted positions for all components to account for title
        JLabel idLabel = createLabel("ID:", 20, 90);
        idField = createTextField(150, 90);

        // Name
        JLabel nameLabel = createLabel("Name:", 20, 130);
        nameField = createTextField(150, 130);

        // Location
        JLabel locationLabel = createLabel("Location:", 20, 170);
        locationField = createTextField(150, 170);

        // Phone
        JLabel phoneLabel = createLabel("Phone:", 20, 210);
        phoneField = createTextField(150, 210);

        // Email
        JLabel emailLabel = createLabel("Email:", 20, 250);
        emailField = createTextField(150, 250);

        // Gender (Radio Buttons)
        JLabel genderLabel = createLabel("Gender:", 20, 290);
        maleBtn = createRadioButton("Male", 150, 290, 60);
        femaleBtn = createRadioButton("Female", 220, 290, 80);
        otherBtn = createRadioButton("Other", 310, 290, 70);
        genderGroup = new ButtonGroup();
        genderGroup.add(maleBtn);
        genderGroup.add(femaleBtn);
        genderGroup.add(otherBtn);

        // DOB
        JLabel dobLabel = createLabel("DOB:", 20, 330);
        dobYearBox = createComboBox(getYearOptions(), 150, 330, 70);
        dobMonthBox = createComboBox(getMonthOptions(), 230, 330, 70);
        dobDayBox = createComboBox(getDayOptions(), 310, 330, 70);

        // Membership Start Date
        JLabel msLabel = createLabel("Start Date:", 20, 370);
        msYearBox = createComboBox(getYearOptions(), 150, 370, 70);
        msMonthBox = createComboBox(getMonthOptions(), 230, 370, 70);
        msDayBox = createComboBox(getDayOptions(), 310, 370, 70);

        // Referral
        JLabel referralLabel = createLabel("Referral Source:", 20, 410);
        referralField = createTextField(150, 410);

        // Paid Amount
        JLabel amountLabel = createLabel("Paid Amount:", 20, 450);
        amountField = createTextField(150, 450);

        // Removal Reason
        JLabel removalLabel = createLabel("Removal Reason:", 20, 490);
        removalField = createTextField(150, 490);

        // Trainer Name
        JLabel trainerLabel = createLabel("Trainer's Name:", 20, 530);
        trainerField = createTextField(150, 530);

        // Plan (For Regular Members)
        JLabel planLabel = createLabel("Plan (Regular):", 400, 90);
        String[] plans = { "Basic", "Standard", "Deluxe" };
        planBox = createComboBox(plans, 530, 90, 150);

        // Pricing cards
        JPanel regularCard = createPricingCard("Regular Plan Price", "Rs. 1000", 400, 130, 280, 80);
        JPanel premiumCard = createPricingCard("Premium Charge", "Rs. 2000", 400, 220, 280, 80);
        JPanel discountCard = createPricingCard("Discount Amount", "Rs. 500", 400, 310, 280, 80);

        // Buttons with default styling (no specific colors)
        JButton addRegularBtn = createButton("Add Regular Member", null, 400, 410, 180, 35);
        JButton addPremiumBtn = createButton("Add Premium Member", null, 600, 410, 180, 35);
        JButton activateMembership = createButton("Activate Membership", null,   400, 460, 180, 35);
        JButton deactivateMembership = createButton("Deactivate Membership", null,  600, 460, 180, 35);
        JButton markAttendanceBtn = createButton("Mark Attendance", null,400, 510, 180, 35 );
        JButton revertMemberBtn = createButton("Revert Member", null,    400, 610, 180, 35);
        JButton revertPaymentBtn = createButton("Revert Payment", null, 1000, 600, 180, 35);
        JButton displayBtn = createButton("Display Members", null,  400, 560, 180, 35);
        JButton loadBtn = createButton("Load from File", null, 600, 660, 180, 35);
        JButton clearBtn = createButton("Clear Fields", null, 600, 610, 180, 35);
        JButton saveBtn = createButton("Save to File", null,  400, 660, 180, 35);
        

        JButton payDueBtn = createButton("Pay Due", null, 600, 560, 180, 35);
        mainPanel.add(payDueBtn);

        // Initialize the discount button
        JButton btnDiscount = createButton("Apply Discount", null, 600, 510, 180, 35);
        mainPanel.add(btnDiscount);

        btnDiscount.addActionListener(new ActionListener() {
    public void actionPerformed(ActionEvent e) {
        String memberID = JOptionPane.showInputDialog("Enter Member ID to apply discount:");
        if (memberID == null || memberID.trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Please enter a valid Member ID.");
            return;
        }

        Member m = null;
        for (Member member : gymMembers) {
            if (member.getId() == Integer.parseInt(memberID)) {
                m = member;
                break;
            }
        }

        if (m == null) {
            JOptionPane.showMessageDialog(null, "Member not found.");
            return;
        }
        double discountPercent = 500;

        if (m instanceof PremiumMember) {
            discountPercent = 20;
        } else if (m instanceof RegularMember) {
            discountPercent = 10;
        }

        if (discountPercent == 0) {
            JOptionPane.showMessageDialog(null, "No eligible discount for this member.");
        } else {
            double originalPrice = 1000; 
            double discountAmount = (originalPrice * discountPercent) / 100;
            double finalPrice = originalPrice - discountAmount;

            JOptionPane.showMessageDialog(null,
                "Member Type: " + m.getClass().getSimpleName() + "\n" +
                "Original Price: " + originalPrice + "\n" +
                "Discount (" + discountPercent + "%): " + discountAmount + "\n" +
                "Price After Discount: " + finalPrice);
        }
    }
});


        // Add action listeners
        addRegularBtn.addActionListener(e -> addRegularMember());
        addPremiumBtn.addActionListener(e -> addPremiumMember());
        activateMembership.addActionListener(e -> activateMembership());
        deactivateMembership.addActionListener(e -> deactivateMembership());
        markAttendanceBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String memberID = JOptionPane.showInputDialog("Enter Member ID to mark attendance:");
                boolean found = false;
                
                for (Member member : gymMembers) {
                    if (member.getId() == Integer.parseInt(memberID)) {
                        member.attendance += 1;
                        JOptionPane.showMessageDialog(null, "Attendance marked for Member: " + member.getName());
                        found = true;
                        break;
                    }
                }
                
                if (!found) {
                    JOptionPane.showMessageDialog(null, "Member not found.");
                }
            }
        });
         payDueBtn.addActionListener(new ActionListener() {
    @Override
    public void actionPerformed(ActionEvent e) {
        String memberID = JOptionPane.showInputDialog("Enter Premium Member ID to complete payment:");
        if (memberID != null && !memberID.trim().isEmpty()) {
            try {
                int id = Integer.parseInt(memberID);
                boolean found = false;
                for (Member member : gymMembers) {
                    if (member.getId() == id && member instanceof PremiumMember) {
                        PremiumMember pm = (PremiumMember) member;
                        pm.setFullPayment(true);
                        JOptionPane.showMessageDialog(null,
                                "Payment completed for Premium Member: " + pm.getName());
                        found = true;
                        break;
                    }
                }
                if (!found) {
                    JOptionPane.showMessageDialog(null, "Premium Member not found.");
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Please enter a valid numeric Member ID.");
            }
        }
    }
});

        revertMemberBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String memberID = JOptionPane.showInputDialog("Enter Member ID to remove:");
                boolean found = false;
                
                for (Member member : gymMembers) {
                    if (member.getId() == Integer.parseInt(memberID)) {
                        gymMembers.remove(member);
                        JOptionPane.showMessageDialog(null, "Member removed: " + member.getName());
                        found = true;
                        break;
                    }
                }
                
                if (!found) {
                    JOptionPane.showMessageDialog(null, "Member not found.");
                }
            }
        });
        
        revertPaymentBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String memberID = JOptionPane.showInputDialog("Enter Member ID to revert payment:");
                if (memberID != null && !memberID.trim().isEmpty()) {
                    try {
                        int id = Integer.parseInt(memberID);
                        boolean found = false;
                        
                        for (Member member : gymMembers) {
                            if (member.getId() == id) {
                                if (member instanceof PremiumMember) {
                                    PremiumMember premiumMember = (PremiumMember) member;
                                    premiumMember.setFullPayment(false);
                                    JOptionPane.showMessageDialog(null, 
                                        "Payment reverted for Premium Member: " + member.getName() + 
                                        "\nMember needs to complete payment again.");
                                } else if (member instanceof RegularMember) {
                                    RegularMember regularMember = (RegularMember) member;
                                    JOptionPane.showMessageDialog(null, 
                                        "Payment reverted for Regular Member: " + member.getName() + 
                                        "\nMember needs to complete payment again.");
                                }
                                found = true;
                                break;
                            }
                        }
                        
                        if (!found) {
                            JOptionPane.showMessageDialog(null, "Member not found.");
                        }
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(null, "Please enter a valid member ID.");
                    }
                }
            }
        });
        
        displayBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                displayMembersInNewFrame();
            }
        });
        
        clearBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clearFields();
            }
        });

        saveBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveToFile();
            }
        });

        loadBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loadFromFile();
            }
        });

        // Add components to main panel
        mainPanel.add(personalInfoLabel);
        mainPanel.add(membershipInfoLabel);
        mainPanel.add(idLabel);
        mainPanel.add(idField);
        mainPanel.add(nameLabel);
        mainPanel.add(nameField);
        mainPanel.add(locationLabel);
        mainPanel.add(locationField);
        mainPanel.add(phoneLabel);
        mainPanel.add(phoneField);
        mainPanel.add(emailLabel);
        mainPanel.add(emailField);
        mainPanel.add(genderLabel);
        mainPanel.add(maleBtn);
        mainPanel.add(femaleBtn);
        mainPanel.add(otherBtn);
        mainPanel.add(dobLabel);
        mainPanel.add(dobYearBox);
        mainPanel.add(dobMonthBox);
        mainPanel.add(dobDayBox);
        mainPanel.add(msLabel);
        mainPanel.add(msYearBox);
        mainPanel.add(msMonthBox);
        mainPanel.add(msDayBox);
        mainPanel.add(referralLabel);
        mainPanel.add(referralField);
        mainPanel.add(amountLabel);
        mainPanel.add(amountField);
        mainPanel.add(removalLabel);
        mainPanel.add(removalField);
        mainPanel.add(trainerLabel);
        mainPanel.add(trainerField);
        mainPanel.add(planLabel);
        mainPanel.add(planBox);
        mainPanel.add(regularCard);
        mainPanel.add(premiumCard);
        mainPanel.add(discountCard);
        mainPanel.add(addRegularBtn);
        mainPanel.add(addPremiumBtn);
        mainPanel.add(activateMembership);
        mainPanel.add(deactivateMembership);
        mainPanel.add(markAttendanceBtn);
        mainPanel.add(revertMemberBtn);
        mainPanel.add(revertPaymentBtn);
        mainPanel.add(displayBtn);
        mainPanel.add(clearBtn);
        mainPanel.add(saveBtn);
        mainPanel.add(loadBtn);

        frame.add(mainPanel);
        frame.setVisible(true);
    }

    // Helper methods for creating consistent UI elements
    private JLabel createLabel(String text, int x, int y) {
        JLabel label = new JLabel(text);
        label.setFont(new Font("Segoe UI", Font.BOLD, 14));
        label.setForeground(new Color(70, 70, 70));
        label.setBounds(x, y, 120, 25);
        return label;
    }

    private JTextField createTextField(int x, int y) {
        JTextField textField = new JTextField();
        textField.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        textField.setBounds(x, y, 200, 30);
        textField.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(200, 200, 200)),
            BorderFactory.createEmptyBorder(5, 5, 5, 5)
        ));
        return textField;
    }

    private JRadioButton createRadioButton(String text, int x, int y, int width) {
        JRadioButton radioButton = new JRadioButton(text);
        radioButton.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        radioButton.setBounds(x, y, width, 25);
        radioButton.setBackground(Color.WHITE);
        radioButton.setActionCommand(text); // Set action command to button text
        return radioButton;
    }

    private JComboBox<String> createComboBox(String[] items, int x, int y, int width) {
        JComboBox<String> comboBox = new JComboBox<>(items);
        comboBox.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        comboBox.setBounds(x, y, width, 30);
        comboBox.setBackground(Color.WHITE);
        comboBox.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200)));
        return comboBox;
    }

    private JPanel createPricingCard(String title, String value, int x, int y, int width, int height) {
        JPanel card = new JPanel();
        card.setLayout(new BorderLayout());
        card.setBounds(x, y, width, height);
        card.setBackground(new Color(245, 245, 245));
        card.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(220, 220, 220)),
            BorderFactory.createEmptyBorder(10, 15, 10, 15)
        ));
        
        JLabel titleLabel = new JLabel(title);
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 14));
        titleLabel.setForeground(new Color(100, 100, 100));
        
        JLabel valueLabel = new JLabel(value);
        valueLabel.setFont(new Font("Segoe UI", Font.BOLD, 16));
        valueLabel.setForeground(new Color(70, 70, 70));
        
        card.add(titleLabel, BorderLayout.NORTH);
        card.add(valueLabel, BorderLayout.CENTER);
        
        return card;
    }

    private JButton createButton(String text, Color bgColor, int x, int y, int width, int height) {
        JButton button = new JButton(text);
        button.setBounds(x, y, width, height);
        
        // Only set the background and foreground colors if a color is specified
        if (bgColor != null) {
            button.setBackground(bgColor);
            button.setForeground(Color.WHITE);
        } else {
            // Use default system button appearance
            button.setBackground(null);
            button.setForeground(null);
        }
        
        button.setFont(new Font("Segoe UI", Font.BOLD, 13));
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createEmptyBorder(5, 15, 5, 15));
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        // Modify the hover effect to handle null colors
        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                if (bgColor != null) {
                    button.setBackground(bgColor.darker());
                }
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                if (bgColor != null) {
                    button.setBackground(bgColor);
                }
            }
        });
        
        return button;
    }

    private String[] getYearOptions() {
        String[] years = new String[100];
        for (int i = 0; i < 100; i++) {
            years[i] = String.valueOf(2025 - i); // Generate years from 2025 to 1925
        }
        return years;
    }

    private String[] getMonthOptions() {
        return new String[] { "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12" };
    }

    private String[] getDayOptions() {
        String[] days = new String[31];
        for (int i = 0; i < 31; i++) {
            days[i] = String.format("%02d", i + 1); // Generate days from 01 to 31
        }
        return days;
    }

    private void clearFields() {
        idField.setText("");
        nameField.setText("");
        locationField.setText("");
        phoneField.setText("");
        emailField.setText("");
        referralField.setText("");
        amountField.setText("");
        removalField.setText("");
        trainerField.setText("");
        genderGroup.clearSelection();
        dobYearBox.setSelectedIndex(0);
        dobMonthBox.setSelectedIndex(0);
        dobDayBox.setSelectedIndex(0);
        msYearBox.setSelectedIndex(0);
        msMonthBox.setSelectedIndex(0);
        msDayBox.setSelectedIndex(0);
        planBox.setSelectedIndex(0);
    }

    private void saveToFile() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("members.dat"))) {
            oos.writeObject(gymMembers);
            JOptionPane.showMessageDialog(null, "Members saved to file successfully.");
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error saving members to file: " + e.getMessage());
        }
    }

    private void loadFromFile() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("members.dat"))) {
            gymMembers = (ArrayList<Member>) ois.readObject();
            JOptionPane.showMessageDialog(null, "Members loaded from file successfully.");
        } catch (IOException | ClassNotFoundException e) {
            JOptionPane.showMessageDialog(null, "Error loading members from file: " + e.getMessage());
        }
    }

    private void displayMembersInNewFrame() {
        JFrame displayFrame = new JFrame("Members List");
        displayFrame.setSize(800, 600);
        displayFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        displayFrame.setLayout(new BorderLayout());

        JTextArea textArea = new JTextArea();
        textArea.setEditable(false);

        StringBuilder memberDetails = new StringBuilder();
        for (Member member : gymMembers) {
            memberDetails.append("ID: ").append(member.getId()).append("\n")
                         .append("Name: ").append(member.getName()).append("\n")
                         .append("Location: ").append(member.location).append("\n")
                         .append("Phone: ").append(member.phone).append("\n")
                         .append("Email: ").append(member.email).append("\n")
                         .append("Gender: ").append(member.gender).append("\n")
                         .append("DOB: ").append(member.dob).append("\n")
                         .append("Trainer: ").append(member.trainer).append("\n");

            if (member instanceof RegularMember) {
                memberDetails.append("Referral Source: ").append(((RegularMember) member).getReferralSource()).append("\n");
            } else if (member instanceof PremiumMember) {
                memberDetails.append("Plan: ").append(((PremiumMember) member).getPlan()).append("\n");
            }

            memberDetails.append("Active Status: ").append(member.activeStatus ? "Active" : "Inactive").append("\n")
                         .append("Attendance: ").append(member.attendance).append("\n\n");
        }

        if (memberDetails.length() == 0) {
            memberDetails.append("No members to display.");
        }

        textArea.setText(memberDetails.toString());
        JScrollPane scrollPane = new JScrollPane(textArea);
        displayFrame.add(scrollPane, BorderLayout.CENTER);

        displayFrame.setVisible(true);
    }

    private void addRegularMember() {
        try {
            int id = Integer.parseInt(idField.getText());
            String name = nameField.getText();
            String location = locationField.getText();
            String phone = phoneField.getText();
            String email = emailField.getText();
            if (genderGroup.getSelection() == null) {
                JOptionPane.showMessageDialog(null, "Please select a gender.");
                return;
            }
            String gender = genderGroup.getSelection().getActionCommand();
            String dob = dobYearBox.getSelectedItem() + "-" + dobMonthBox.getSelectedItem() + "-" + dobDayBox.getSelectedItem();
            String referralSource = referralField.getText();
            String trainerName = trainerField.getText();

            if (name.isEmpty() || location.isEmpty() || phone.isEmpty() || email.isEmpty() || trainerName.isEmpty() || referralSource.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Some of the required fields are empty.");
                return;
            }

            RegularMember newMember = new RegularMember(id, name, location, phone, email, gender, dob, trainerName, referralSource);
            gymMembers.add(newMember);
            JOptionPane.showMessageDialog(null, "Regular Member Successfully Added");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
        }
    }

    private void addPremiumMember() {
        try {
            int id = Integer.parseInt(idField.getText());
            String name = nameField.getText();
            String location = locationField.getText();
            String phone = phoneField.getText();
            String email = emailField.getText();
            if (genderGroup.getSelection() == null) {
                JOptionPane.showMessageDialog(null, "Please select a gender.");
                return;
            }
            String gender = genderGroup.getSelection().getActionCommand();
            String dob = dobYearBox.getSelectedItem() + "-" + dobMonthBox.getSelectedItem() + "-" + dobDayBox.getSelectedItem();
            String plan = planBox.getSelectedItem().toString();
            String trainerName = trainerField.getText();

            if (name.isEmpty() || location.isEmpty() || phone.isEmpty() || email.isEmpty() || trainerName.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Some of the required fields are empty.");
                return;
            }

            PremiumMember newMember = new PremiumMember(id, name, location, phone, email, gender, dob, plan, trainerName);
            gymMembers.add(newMember);
            JOptionPane.showMessageDialog(null, "Premium Member Successfully Added");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
        }
    }

    private void activateMembership() {
        String memberID = JOptionPane.showInputDialog("Enter Member ID to activate:");
        try {
            int id = Integer.parseInt(memberID);
            boolean found = false;

            for (Member member : gymMembers) {
                if (member.getId() == id) {
                    member.activeStatus = true;
                    JOptionPane.showMessageDialog(null, "Membership Activated for Member: " + member.getName());
                    found = true;
                    break;
                }
            }

            if (!found) {
                JOptionPane.showMessageDialog(null, "Member not found.");
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "Invalid Member ID.");
        }
    }

    private void deactivateMembership() {
        String memberID = JOptionPane.showInputDialog("Enter Member ID to deactivate:");
        try {
            int id = Integer.parseInt(memberID);
            boolean found = false;

            for (Member member : gymMembers) {
                if (member.getId() == id) {
                    member.activeStatus = false;
                    JOptionPane.showMessageDialog(null, "Membership Deactivated for Member: " + member.getName());
                    found = true;
                    break;
                }
            }

            if (!found) {
                JOptionPane.showMessageDialog(null, "Member not found.");
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "Invalid Member ID.");
        }
    }

    private void calculateDiscount() {
        boolean discountApplied = false;
        StringBuilder discountDetails = new StringBuilder();

        for (Member member : gymMembers) {
            if (member instanceof PremiumMember) {
                PremiumMember premiumMember = (PremiumMember) member;

                if (premiumMember.isFullPayment()) {
                    premiumMember.calculateDiscount(); // Calculate the discount
                    discountDetails.append("ID: ").append(premiumMember.getId())
                                   .append(", Name: ").append(premiumMember.getName())
                                   .append(", Discount: ").append(premiumMember.getDiscountAmount())
                                   .append("\n");
                    discountApplied = true;
                }
            }
        }

        if (discountApplied) {
            JOptionPane.showMessageDialog(null, "Discounts Applied:\n" + discountDetails.toString());
        } else {
            JOptionPane.showMessageDialog(null, "No eligible premium members for discount.");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new GymGUI());
    }
}

// Member classes needed for the application to work
class Member implements Serializable {
    private static final long serialVersionUID = 1L;
    protected int id;
    protected String name;
    protected String location;
    protected String phone;
    protected String email;
    protected String gender;
    protected String dob;
    protected String trainer;
    protected boolean activeStatus = false;
    protected int attendance = 0;
    
    public Member(int id, String name, String location, String phone, String email, String gender, String dob, String trainer) {
        this.id = id;
        this.name = name;
        this.location = location;
        this.phone = phone;
        this.email = email;
        this.gender = gender;
        this.dob = dob;
        this.trainer = trainer;
    }
    
    public int getId() {
        return id;
    }
    
    public String getName() {
        return name;
    }
}

class RegularMember extends Member {
    private static final long serialVersionUID = 1L;
    private String referralSource;
    
    public RegularMember(int id, String name, String location, String phone, String email, String gender, String dob, String trainer, String referralSource) {
        super(id, name, location, phone, email, gender, dob, trainer);
        this.referralSource = referralSource;
    }

    public String getReferralSource() {
        return referralSource;
    }

    public void setReferralSource(String referralSource) {
        this.referralSource = referralSource;
    }
}

class PremiumMember extends Member {
    private static final long serialVersionUID = 1L;
    private String plan;
    private boolean isFullPayment = false; // Indicates if full payment is completed
    private double premiumCharge = 50000; // Example premium charge
    private double discountAmount = 0; // Discount amount

    public PremiumMember(int id, String name, String location, String phone, String email, String gender, String dob, String plan, String trainer) {
        super(id, name, location, phone, email, gender, dob, trainer);
        this.plan = plan;
    }

    public String getPlan() {
        return plan;
    }

    public void setPlan(String plan) {
        this.plan = plan;
    }

    public boolean isFullPayment() {
        return isFullPayment;
    }

    public void setFullPayment(boolean isFullPayment) {
        this.isFullPayment = isFullPayment;
    }

    public double getDiscountAmount() {
        return discountAmount;
    }

    public void calculateDiscount() {
        if (isFullPayment) {
            discountAmount = premiumCharge * 0.10; // 10% discount
        } else {
            discountAmount = 0; // No discount if payment is incomplete
        }
    }
}