public class User {
    public String mobileNumber;
    public String emailAddress;
    public String fullName;
    public String whereHearAbout;
    public String promoCode;
    public String businessRegistrationNumber;
    public String date;
    public String month;
    public String year;
    public String nationality;
    public String gender;
    public String interestedProduct;
    public String businessName;
    public String registrationType;
    public String industry;
    public String subIndustry;
    public String companyName;
    public String directorFullName;
    public String directorEmailAddress;

    public User(String mobileNumber, String emailAddress, String fullName, String whereHearAbout, String promoCode,
                String businessRegistrationNumber, String date, String month, String year, String nationality,
                String gender, String interestedProduct, String businessName, String registrationType, String industry,
                String subIndustry) {
        this.mobileNumber = mobileNumber;
        this.emailAddress = emailAddress;
        this.fullName = fullName;
        this.whereHearAbout = whereHearAbout;
        this.promoCode = promoCode;
        this.businessRegistrationNumber = businessRegistrationNumber;
        this.date = date;
        this.month = month;
        this.year = year;
        this.nationality = nationality;
        this.gender = gender;
        this.interestedProduct = interestedProduct;
        this.businessName = businessName;
        this.registrationType = registrationType;
        this.industry = industry;
        this.subIndustry = subIndustry;
        this.companyName = companyName;
        this.directorFullName = directorFullName;
        this.directorEmailAddress = directorEmailAddress;
    }

}
