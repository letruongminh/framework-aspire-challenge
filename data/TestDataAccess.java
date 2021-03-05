import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class TestDataAccess {

    /**
     * This function is to read data from csv file located in data/uatdata
     *
     * @param file
     * @return an instance of User
     * @throws IOException
     */
    public static User readAllData(String file) throws IOException {
        String mobileNumber = "";
        String emailAddress = "";
        String fullName = "";
        String whereHearAbout = "";
        String promoCode = "";
        String businessRegistrationNumber = "";
        String date = "";
        String month = "";
        String year = "";
        String nationality = "";
        String gender = "";
        String interestedProduct = "";
        String businessName = "";
        String registrationType = "";
        String industry = "";
        String subIndustry = "";
        String systemPath = System.getProperty("user.dir");

        try {
            // Try creating an object of file reader
            FileReader fileReader = new FileReader(systemPath + "/data/uatdata/" + file);
            CSVReader csvReader = new CSVReaderBuilder(fileReader)
                    .withSkipLines(1)
                    .build();
            List<String[]> allData = csvReader.readAll();
            for (String[] row : allData) {
                mobileNumber = row[0];
                emailAddress = row[1];
                fullName = row[2];
                whereHearAbout = row[3];
                promoCode = row[4];
                businessRegistrationNumber = row[5];
                date = row[6];
                month = row[7];
                year = row[8];
                nationality = row[9];
                interestedProduct = row[10];
                gender = row[11];
                businessName = row[12];
                registrationType = row[13];
                industry = row[14];
                subIndustry = row[15];
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return new User(mobileNumber, emailAddress, fullName, whereHearAbout, promoCode, businessRegistrationNumber,
                date, month, year, nationality, gender, interestedProduct, businessName, registrationType, industry,
                subIndustry);
    }
}
