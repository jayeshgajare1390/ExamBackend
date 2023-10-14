package net.examsection.springboot.controller;

import net.examsection.springboot.helper.helper;
import net.examsection.springboot.model.BlocksAndStrengths;
import net.examsection.springboot.model.ExamSection;
import net.examsection.springboot.model.InfoTable;
import net.examsection.springboot.service.ExamService;
import java.util.List;
import java.util.Map;
import java.util.Optional;

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
import org.springframework.web.multipart.MultipartFile;
@RestController
@CrossOrigin("*")
public class ExcelController {

    @Autowired
    private ExamService productService;

    @PostMapping("/studentdataexam/upload")
    public ResponseEntity<?> upload(
            @RequestParam("File") MultipartFile file, //6
            @RequestParam("Dates") String date,   //2
            @RequestParam("Courses") String course,
            @RequestParam("Year") String Year, //3
            @RequestParam("Subjects") String Subject, //4
            @RequestParam("Option") String option, //1
            @RequestParam("Slots") String Slot //5
           ) {

    	date = date.replaceAll("\\[|\\]|\"", "");  
    	course=course.replaceAll("\\[|\\]|\"", "");
    	Subject = Subject.replaceAll("\\[|\\]|\"", "");  
    	Slot=Slot.replaceAll("\\[|\\]|\"", "");
    	
         // Print the resulting arrays
        if (helper.checkExcelFormat(file)) {
        	
        	int count =this.productService.saveAllData(option, date,  Year, Subject, Slot,  course);
        	
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
    @GetMapping("/delete/{id}")
    public String deleteById(@PathVariable("id") Long id) {
        return this.productService.deleteById(id);
    }
    @PostMapping("/updateData")
    public ResponseEntity<String> updateData(@RequestBody List<BlocksAndStrengths> data) {
        try {
        	for(BlocksAndStrengths  data1:data) {
        		System.out.print(data1.getBlocks());
        	}
           this.productService.updateData(data);

            // Return a response, e.g., "Data updated successfully"
            return ResponseEntity.ok("Data updated successfully");
        } catch (Exception e) {
            // Handle the exception and return an error response
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("An error occurred while processing the request.");
        }
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
    	for(int i=0;i< strengths.length;i++) {
    		System.out.println("BLOCKS :"+blocks[i]);
    		System.out.println("STRENGTHS:"+strengths[i]);
    		System.out.println("buildings:"+buildings[i]);
    	}
    	System.out.println(totalCount);
    	
    	
            return this.productService.setStrengths(blocks,strengths,buildings,totalCount);
}}
