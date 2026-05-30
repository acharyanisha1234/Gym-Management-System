package Gym;

import java.io.Serializable;

/**
 * Represents a Premium Member in the Gym Management System.
 * Premium members have additional attributes such as personal trainers,
 * payment details, and discounts.
 * 
 * @author 
 * @version 
 */
public class PremiumMember extends GymMember implements Serializable {
    private static final long serialVersionUID = 1L;

    // Attributes
    private String plan;
    private double price;
    private boolean isFullPayment = false; // Indicates if full payment is completed
    private double paidAmount = 0; // Amount paid by the member
    private double discountAmount = 0; // Discount amount
    private String personalTrainer;

    // Constructor
    public PremiumMember(int id, String name, String location, String phone, String email, String gender, String dob, String plan, String trainer) {
        super(id, name, location, phone, email, gender, dob, trainer);
        this.plan = plan;
        this.price = getPlanPrice(plan); // Set price based on the plan
        this.personalTrainer = trainer;
    }

    // Accessor methods
    public String getPlan() {
        return plan;
    }

    public void setPlan(String plan) {
        this.plan = plan;
        this.price = getPlanPrice(plan); // Update price when plan changes
    }

    public double getPrice() {
        return price;
    }

    public boolean isFullPayment() {
        return isFullPayment;
    }

    public void setFullPayment(boolean isFullPayment) {
        this.isFullPayment = isFullPayment;
    }

    public double getPaidAmount() {
        return paidAmount;
    }

    public double getDiscountAmount() {
        return discountAmount;
    }

    public String getPersonalTrainer() {
        return personalTrainer;
    }

    public void setPersonalTrainer(String personalTrainer) {
        this.personalTrainer = personalTrainer;
    }

    // Method to calculate the price based on the plan
    public double getPlanPrice(String plan) {
        switch (plan.toLowerCase()) {
            case "basic":
                return 50000;
            case "standard":
                return 75000;
            case "deluxe":
                return 100000;
            default:
                return -1; // Invalid plan
        }
    }

    // Method to calculate the discount
    public void calculateDiscount() {
        if (isFullPayment) {
            discountAmount = price * 0.10; // 10% discount
        } else {
            discountAmount = 0; // No discount if payment is incomplete
        }
    }

    // Method to pay the due amount
    public String payDueAmount(double amount) {
        if (isFullPayment) {
            return "Payment is already completed. No further payments are required.";
        }

        paidAmount += amount;

        if (paidAmount >= price) {
            isFullPayment = true;
            paidAmount = price; // Ensure no overpayment
            return "Payment completed successfully!";
        }

        double remainingAmount = price - paidAmount;
        return "Payment successful! Remaining amount to be paid: " + remainingAmount;
    }

    // Method to upgrade the plan
    public String upgradePlan(String newPlan) {
        if (newPlan.equalsIgnoreCase(plan)) {
            return "You are already subscribed to the " + plan + " plan.";
        }

        double newPrice = getPlanPrice(newPlan);
        if (newPrice == -1) {
            return "Invalid plan selected. Upgrade failed.";
        }

        plan = newPlan;
        price = newPrice;
        return "Plan upgraded to " + plan + " with price " + price;
    }

    // Method to revert premium member details
    public void revertPremiumMember() {
        super.resetMember(); // Reset member details from superclass
        plan = "basic";
        price = getPlanPrice(plan);
        isFullPayment = false;
        paidAmount = 0;
        discountAmount = 0;
        personalTrainer = "";
    }

    // Method to mark attendance
    @Override
    public void markAttendance() {
        attendance++;
        loyaltyPoints += 10; // Increase loyalty points by 10 for premium members
    }

    // Method to display details of the PremiumMember
    @Override
    public void display() {
        super.display(); // Call the superclass display method
        System.out.println("Plan: " + plan);
        System.out.println("Price: " + price);
        System.out.println("Paid Amount: " + paidAmount);
        System.out.println("Full Payment: " + (isFullPayment ? "Yes" : "No"));
        System.out.println("Discount Amount: " + discountAmount);
        System.out.println("Personal Trainer: " + personalTrainer);
    }
}

