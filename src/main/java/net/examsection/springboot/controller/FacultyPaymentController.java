package net.examsection.springboot.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import net.examsection.springboot.model.Amounts;
import net.examsection.springboot.model.FacultyPayment;
import net.examsection.springboot.repository.AmountsRepository;
import net.examsection.springboot.service.FacultyPaymentService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class FacultyPaymentController {
    @Autowired
    private FacultyPaymentService facultyPaymentService;

    @Autowired
    private AmountsRepository amountsRepository;
    
    @PostMapping("/set-amounts")
    public ResponseEntity<String> setAmounts(@RequestBody Map<String, Integer> amounts) {
        boolean success = facultyPaymentService.setAmounts(amounts);
        if (success) {
            return ResponseEntity.ok("Amounts set successfully.");
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to set amounts.");
        }
    }
    
    @GetMapping("/amounts/{post}")
    public int getAmountByPost(@PathVariable String post) {
        Amounts amounts = amountsRepository.findByPost(post);
        return amounts != null ? amounts.getAmount() : 0;
    }
    
    
    @GetMapping("/faculty-payments")
    public List<FacultyPayment> getFacultyPayments(
            @RequestParam String year,
            @RequestParam String std,
            @RequestParam String sem,
            @RequestParam String exam) {
    	
    	System.out.println("Received parameters - Year: " + year + ", Std: " + std + ", Sem: " + sem + ", Exam: " + exam);
        // Call your service method to fetch data based on user inputs
        List<FacultyPayment> facultyPayments = facultyPaymentService.getFacultyPayments(year, std, sem, exam);
        
        for (FacultyPayment payment : facultyPayments) {
            int noOfSupervisions = payment.getNoOfSupervisions();
            String facultyStatus = payment.getFacultyStatus();
//            double totalAmount = facultyPaymentService.calculateTotalAmount(noOfSupervisions, facultyStatus);
            double totalAmount1 = payment.getTotalAmount();
            payment.setTotalAmount(totalAmount1);
//            System.out.println(totalAmount1);
        }
        
        
        // Perform any additional calculations as needed
        System.out.println("Returned data: " + facultyPayments);
        System.out.println("Returned data:");
        for (FacultyPayment payment : facultyPayments) {
            System.out.println("Faculty Name: " + payment.getFacultyName());
            System.out.println("Year: " + payment.getYear());
            System.out.println("Std: " + payment.getStd());
            System.out.println("Sem: " + payment.getSem());
            System.out.println("Exam: " + payment.getExam());
            System.out.println("No of Supervisions: " + payment.getNoOfSupervisions());
            System.out.println("Total Amount: " + payment.getTotalAmount());
            System.out.println("Faculty Status: " + payment.getFacultyStatus());
            System.out.println("---------------------");
//            System.out.println("Received rates from frontend User:"+rateService.a);
        }
        // Return the data as a response
        return facultyPayments;
    }
}
