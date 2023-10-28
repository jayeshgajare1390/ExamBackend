package net.examsection.springboot.controller;

import net.examsection.springboot.helper.helper;
import net.examsection.springboot.model.*;
import net.examsection.springboot.repository.*;
import net.examsection.springboot.service.ExamService;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
@RestController
@CrossOrigin("*")
public class ExcelController {
    @Autowired
    private final InfoRepository infoRepository;
    @Autowired
    private final AmountsRepository amountsRepository;

    @Autowired
    private FacultyPaymentRepository facultyPaymentRepository;


    @Autowired
    private ExamRepository examRepository;
    @Autowired
    private BlockRepository blockRepository;
    @Autowired
    private final StrngthRepository strngthRepository;
    @Autowired
    private final TeachersRepository teachersRepository;
    @Autowired
    private ExamService productService;

    public ExcelController(InfoRepository infoRepository, AmountsRepository amountsRepository, StrngthRepository strngthRepository, TeachersRepository teachersRepository) {
        this.infoRepository = infoRepository;
        this.amountsRepository = amountsRepository;
        this.strngthRepository = strngthRepository;
        this.teachersRepository = teachersRepository;
    }

    @PostMapping("/studentdataexam/upload")
    public ResponseEntity<?> upload(
            @RequestParam("File") MultipartFile file, //6
            @RequestParam("Dates") String date,   //2
            @RequestParam("Courses") String course,
            @RequestParam("Year") String Year, //3
            @RequestParam("Subjects") String Subject, //4
            @RequestParam("Option") String option, //1
            @RequestParam("Slots") String Slot ,//5
            @RequestParam("CorseCode") String CorseCode //
           ) {

    	date = date.replaceAll("\\[|\\]|\"", "");  
    	course=course.replaceAll("\\[|\\]|\"", "");
    	Subject = Subject.replaceAll("\\[|\\]|\"", "");  
    	Slot=Slot.replaceAll("\\[|\\]|\"", "");
    	CorseCode=CorseCode.replaceAll("\\[|\\]|\"", "").toUpperCase();
         // Print the resulting arrays
        if (helper.checkExcelFormat(file)) {
        	
        	int count =this.productService.saveAllData(option, date,  Year, Subject, Slot,  course ,CorseCode);
        	
            this.productService.save(file,count);
            return ResponseEntity.ok(Map.of("message", "File is uploaded and data is saved to db"));
        }
        
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Please upload an excel file.");
    }

    @GetMapping("/studentdataexam")
    public List<ExamSection> getAllProduct() {
        return this.productService.getAllProducts();
    }
    @GetMapping("/studentdataexam1")
    public List<InfoTable> getAllProduct1() {
        return this.productService.getAllProducts1();
    }
    @GetMapping("/strengths")
    public List<BlocksAndStrengths> findAllStrength() {
        return this.productService.findAllStrength();
    }
    @GetMapping("/innerjoin")
    public List<Object[]> innerjoin() {
        return this.productService.innerjoin();
    }
    @GetMapping("/count")
    public int count() {
        return this.productService.count();
    }
    @GetMapping("/count1")
    public int count1() {
        return this.productService.count1();
    }

    @GetMapping("/getById")
    public List<ExamSection> fetchrecords() {
        return this.productService.findById();
    }
    @GetMapping("/getinfo")
    public Optional<List<InfoTable>> fetchinfo() {
        return this.productService.findinfo();
    }
    @GetMapping("/delete/{i}")
    public ResponseEntity<String> deleteById(@PathVariable("i") int id) {
        String message = this.productService.deleteById(id);
        // You can also create a custom response object if you have more data to return.
        
        // Return a JSON response with a message
        return ResponseEntity.ok("{ \"message\": \"" + message + "\" }");
    }
    @PostMapping("/updateData")
    public ResponseEntity<String> updateData(@RequestBody List<BlocksAndStrengths> data) {
        try {
        	
           this.productService.updateData(data);

            // Return a response, e.g., "Data updated successfully"
            return ResponseEntity.ok("Data updated successfully");
        } catch (Exception e) {
            // Handle the exception and return an error response
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("An error occurred while processing the request.");
        }

    }

    @GetMapping("/blocks")
    public List<Object[]> findBlockByPRN(
            @RequestParam("blockNo") int blockNo
//	    @RequestParam("selectedDate") String selectedDate
    ) {
        System.out.println("Hello World");
        List<Object[]> userList = new ArrayList<>();
        userList = this.examRepository.findBlockByPRN(blockNo);

        return userList;
    }

    @GetMapping("/output")
    public List<ExamSection> output() {
        return this.productService.fetchData();
    }
    @GetMapping("/output1")
    public List<ExamSection> outputnew() {
        return this.productService.findByprogramname1();
    }
    @GetMapping("/Allinfo")
    public List<InfoTable> Allinfo() {
        return this.productService.Allinfo();
    }
    @GetMapping("/getlastentry")
    public int getlastentry() {
        return this.productService.getlastentry();
    }
    @GetMapping("/counter")
    public int counter() {
        return this.productService.cntr();
    }
    @PostMapping("/ab/{id}")
    public ResponseEntity<List<ExamSection>> handleInfoid(@PathVariable int id) {
        List<ExamSection> examSection = productService.getstudent(id);
        return ResponseEntity.ok(examSection);
    }
    @GetMapping("/main")
    public List<BlocksAndStrengths> mainbuilding() {
        return this.productService.mainbuilding();
    }@GetMapping("/new")
    public List<BlocksAndStrengths> newbuilding() {
        return this.productService.newbuilding();
    }
    @PostMapping("/strength")
    public String processStrengthData(
    		@RequestParam("blocks") Integer[] blocks,
    	    @RequestParam("strengths") Integer[] strengths,
    	    @RequestParam("buildings") String[] buildings,
    	    @RequestParam("totalCount") int totalCount
        ) {    	
            return this.productService.setStrengths(blocks,strengths,buildings,totalCount);
}
    @GetMapping("/all")
    public List<Teachers> getAllAbsentStudents() {
        return teachersRepository.findAll();
    }
    @GetMapping("/fetch1")
    public List<Teacher1> fetch1(){
        return productService.fetchrecords1();
    }

    @DeleteMapping("/deleteAll")
    public String deleteAllTeacher1Data() {
        productService.deleteAllTeacher1Data();
        return "All data deleted from Teacher1 table.";
    }

    @PostMapping("/save")
    public void saveTeacher1(@RequestBody Teachers teacher) {
        productService.saveTeachers(teacher);
    }

    @DeleteMapping("/delete/{teacherName}")
    public String deleteTeacherByName(@PathVariable String teacherName) {
        try {
            productService.deleteTeacherByName(teacherName);
            return "Teacher with name " + teacherName + " deleted successfully.";
        } catch (Exception e) {
            return "Error deleting teacher: " + e.getMessage();
        }
    }

    @PostMapping("/save-teachers")
    public void saveTeachersData(@RequestBody List<HistoryTeachers> historyTeachersList) {
        productService.saveSelectedTeachersData(historyTeachersList);
    }


//    @PostMapping("/save15")
//    public ResponseEntity<?> saveBlocks(@RequestBody List<Integer> blocks) {
//        productService.saveBlocks(blocks);
//        return ResponseEntity.ok("Data saved successfully in the second table.");
//    }



//    @PostMapping("/save-blocks-data")
//    public ResponseEntity<String> saveBlocksData(@RequestBody List<Integer> blocksArray) {
//        // Process the blocksArray as needed
//        for (Integer block : blocksArray) {
//             productService.saveBlocksData(block);
//        }
//
//        return ResponseEntity.ok("Blocks data saved successfully.");
//    }


    @PostMapping("/save-blocks-data")
    public ResponseEntity<String> saveBlocksData(@RequestBody List<Integer> blockData) {
        try {
            // Iterate through the list of numbers and save them individually
            for (Integer value : blockData) {
                Block block = new Block();
                block.setValue(value); // Set the "value" property with the number
                productService.saveBlock(block);
            }
            return ResponseEntity.ok("Data saved successfully.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to save data.");
        }
    }



    @GetMapping("/values")
    public List<Integer> getBlockValues() {
        List<Integer> blockValues = blockRepository.getBlockValues();
        return blockValues;
    }



    @GetMapping("/viewhistory")
    public List<ExamSection> getAllExamSections() {
        // Use the repository to fetch all exam sections from the database
        List<ExamSection> examSections = examRepository.findAll();
        return examSections;
    }

//Jayesh AbsentStudent data
//    @PostMapping("/saveAbsentData")
//    public ResponseEntity<String> saveAbsentStudents(@RequestBody List<AbsentStudent> absentStudents) {
//        for (AbsentStudent student : absentStudents) {
//            absentStudentRepository.save(student);
//        }
//
//        return new ResponseEntity<>("Data saved successfully", HttpStatus.CREATED);
//    }
//
//    @GetMapping("/report/absent")
//    public List<AbsentStudent> findAbsentStudents(
//            @RequestParam String examYear,
//            @RequestParam String year,
//            @RequestParam String examination) {
//        return absentStudentRepository.findCustomAbsentStudents(examYear, year, examination);
//    }
//
//
//    @GetMapping("/all16")
//    public List<AbsentStudent> getAllAbsentStudents1() {
//        return absentStudentRepository.findAll();
//    }
//
//    @GetMapping("/{classId}")
//    public Map<String, List<AbsentStudent>> getAbsentStudentsByClass(@PathVariable String classId) {
//        List<AbsentStudent> absentStudents = absentStudentRepository.findByyear(classId);
//
//        Map<String, List<AbsentStudent>> groupedData = absentStudents.stream()
//                .collect(Collectors.groupingBy(AbsentStudent::getProgramname));
//
//        return groupedData;
//    }
//    //    @GetMapping("/exists")
////    public ResponseEntity<String> checkIfExists(@RequestParam String date, @RequestParam Integer blockNo) {
////        boolean exists = absentStudentRepository.existsByDateAndBlockNo(date, blockNo);
////        String message = exists ? "Record exists" : "Record does not exist";
////        return ResponseEntity.ok(message);
////    }
//    @GetMapping("/report/generated")
//    public ResponseEntity<Boolean> isReportGenerated(
//            @RequestParam Integer blockNumber,
//            @RequestParam String date) {
//        List<AbsentStudent> existingData = absentStudentRepository.findAbsentStudentsByBlockNoAndDate(blockNumber, date);
//        boolean reportGenerated = !existingData.isEmpty();
//        return new ResponseEntity<>(reportGenerated, HttpStatus.OK);
//    }
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


    //Sumit controllers

    @PostMapping("/set-amounts")
    public ResponseEntity<String> setAmounts(@RequestBody Map<String, Integer> amounts) {
        boolean success = productService.setAmounts(amounts);
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
//            @RequestParam String sem,
            @RequestParam String exam) {

//    	System.out.println("Received parameters - Year: " + year + ", Std: " + std + ", Sem: " + sem + ", Exam: " + exam);
        // Call your service method to fetch data based on user inputs
        List<FacultyPayment> facultyPayments = productService.getFacultyPayments(year, std, exam);

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
//            System.out.println("Sem: " + payment.getSem());
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

    @GetMapping("/search")
    public List<Map<String, Object>> searchByDateRange(
            @RequestParam String startDate,
            @RequestParam String endDate) {
        List<Object[]> results = infoRepository.findInfoByDateRange(startDate, endDate);

        List<Map<String, Object>> response = new ArrayList<>();
        for (Object[] result : results) {
            Map<String, Object> item = new HashMap<>();
            item.put("date", result[0]); // 'date' corresponds to the first column in the result
            item.put("slot", result[1]); // 'slot' corresponds to the second column in the result
            response.add(item);
        }

        return response;
    }


    @GetMapping("/blockNosInDateRange")
    public List<Integer> findBlockNosInDateRange(
            @RequestParam("startDate") String startDate,
            @RequestParam("endDate") String endDate) {
        List<Integer> blockNos = productService.findBlockNosInDateRange(startDate, endDate);
        return blockNos;
    }

    @GetMapping("/all123")
    public List<HistoryTeachers> getAllTeachers() {
        return productService.getAllTeachers();
    }
}
