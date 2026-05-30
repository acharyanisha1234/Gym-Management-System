package Gym;

/**
* Write a description of class RegularMember here.
 *
 * @author (Nisha Acharya)
 * @version (a version number or a date)
 */
class RegularMember extends  GymMember {
    // Private attributes
    private final int attendanceLimit = 30;
    private boolean isEligibleForUpgrade;
    private String removalReason;
    private String referralSource;
    private String plan;
    private double price;

    // Constructor accepting 9 parameters
    public RegularMember(int id, String name, String location, String phone, String email, 
                         String gender, String DOB, String membershipStartDate, String referralSource) {
        // Call to superclass constructor with 8 parameters
        super(id, name, location, phone, email, gender, DOB, membershipStartDate);
        this.referralSource = referralSource;
        this.isEligibleForUpgrade = false;
        this.plan = "basic"; // Default plan
        this.price = 6500; // Default price
        this.removalReason = "";
    }

    // Accessor methods for all attributes
    public int getAttendanceLimit() {
        return attendanceLimit;
    }

    public boolean getIsEligibleForUpgrade() {
        return isEligibleForUpgrade;
    }

    public String getRemovalReason() {
        return removalReason;
    }

    public String getReferralSource() {
        return referralSource;
    }

    public String getPlan() {
        return plan;
    }

    public double getPrice() {
        return price;
    }

    // Implementation of abstract method markAttendance
    @Override
    public void markAttendance() {
        attendance++;
        loyaltyPoints += 5; // Increase loyalty points by 5
        if (attendance >= attendanceLimit) {
            isEligibleForUpgrade = true;
        }
    }

    // Method to get the price based on plan
    public double getPlanPrice(String plan) {
        switch (plan.toLowerCase()) {
            case "basic":
                return 6500;
            case "standard":
                return 12500;
            case "deluxe":
                return 18500;
            default:
                return -1; // Invalid plan
        }
    }

    // Method to upgrade plan
    public String upgradePlan(String newPlan) {
        if (!isEligibleForUpgrade) {
            return "Member is not eligible for an upgrade yet.";
        }

        if (newPlan.equalsIgnoreCase(plan)) {
            return "You are already subscribed to the " + plan + " plan.";
        }

        double newPrice = getPlanPrice(newPlan);
        if (newPrice == -1) {
            return "Invalid plan selected. Upgrade failed.";
        }

        plan = newPlan;
        price = newPrice;
        isEligibleForUpgrade = false; // Reset eligibility after upgrade
        return "Plan upgraded to " + plan + " with price " + price;
    }

    // Method to revert regular member
    public void revertRegularMember(String removalReason) {
        super.resetMember(); // Reset member details from superclass
        isEligibleForUpgrade = false;
        plan = "basic";
        price = 6500;
        this.removalReason = removalReason;
    }

    // Method to display details of the RegularMember
    @Override
    public void display() {
        super.display(); // Call the superclass display method
        System.out.println("Plan: " + plan);
        System.out.println("Price: " + price);
        if (!removalReason.isEmpty()) {
            System.out.println("Removal Reason: " + removalReason);
    }
    }
}
