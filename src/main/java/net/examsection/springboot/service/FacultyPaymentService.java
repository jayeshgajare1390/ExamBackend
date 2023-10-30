package net.examsection.springboot.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.examsection.springboot.model.Amounts;
import net.examsection.springboot.model.FacultyPayment;
import net.examsection.springboot.repository.AmountsRepository;
import net.examsection.springboot.repository.FacultyPaymentRepository;



@Service
public class FacultyPaymentService {
	
	private final AmountsRepository amountsRepository;
	
    @Autowired
    private FacultyPaymentRepository facultyPaymentRepository;
    

    @Autowired
    public FacultyPaymentService(AmountsRepository amountsRepository) {
        this.amountsRepository = amountsRepository;
    }
    
 // FacultyPaymentService.java

    public List<FacultyPayment> getFacultyPayments(String year, String std, String sem, String exam) {
        // Fetch data from the database based on the provided parameters
        List<FacultyPayment> facultyPayments = facultyPaymentRepository.findDistinctByYearAndStdAndSemAndExam(year, std, sem, exam);

        // Create a map to store faculty data after processing
        Map<String, FacultyPayment> processedFacultyData = new HashMap<>();

        // Process the fetched data to calculate No Of Supervisions
        for (FacultyPayment payment : facultyPayments) {
            String facultyName = payment.getFacultyName();

            // Check if we have already processed this faculty
            if (processedFacultyData.containsKey(facultyName)) {
                // If already processed, increment the number of supervisions
                FacultyPayment existingFaculty = processedFacultyData.get(facultyName);
                existingFaculty.setNoOfSupervisions(existingFaculty.getNoOfSupervisions() + 1);
            } else {
                // If not processed yet, set the number of supervisions to 1
                payment.setNoOfSupervisions(1);
                processedFacultyData.put(facultyName, payment);
            }
        }

        // Calculate Total Amount based on faculty status and number of supervisions
//        for (FacultyPayment payment : processedFacultyData.values()) {
//            double totalAmount = calculateTotalAmount(payment.getNoOfSupervisions(), payment.getFacultyStatus());
//            payment.setTotalAmount(totalAmount); // Set the calculated Total Amount
//        }

        // Convert the map to a list of faculty data
        List<FacultyPayment> distinctFacultyPayments = new ArrayList<>(processedFacultyData.values());

        // Return the list of processed FacultyPayment objects
        return distinctFacultyPayments;
    }
    
    public boolean setAmounts(Map<String, Integer> amounts) {
        try {
            for (Map.Entry<String, Integer> entry : amounts.entrySet()) {
                String post = entry.getKey();
                Integer amount = entry.getValue();
                Amounts amountsEntity = amountsRepository.findByPost(post);

                if (amountsEntity != null) {
                    // If the record for this post already exists, update the amount
                    amountsEntity.setAmount(amount);
                } else {
                    // If the record doesn't exist, create a new one
                    amountsEntity = new Amounts();
                    amountsEntity.setPost(post);
                    amountsEntity.setAmount(amount);
                }

                amountsRepository.save(amountsEntity);
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }


//    public double calculateTotalAmount(int noOfSupervisions, String facultyStatus) {
//        double ratePerSupervision;
//
//        switch (facultyStatus) {
//            case "Senior Supervisor":
//                ratePerSupervision = 1000;
//                break;
//            case "Supervisor":
//                ratePerSupervision = 1500;
//                break;
//            case "Junior Supervisor":
//                ratePerSupervision = 1000;
//                break;
//            default:
//                ratePerSupervision = 0;
//                break;
//        }
//
//        return noOfSupervisions * ratePerSupervision;
//    }
    
    
}

