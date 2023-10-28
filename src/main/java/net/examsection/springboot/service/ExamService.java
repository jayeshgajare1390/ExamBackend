package net.examsection.springboot.service;
import net.examsection.springboot.model.*;
import net.examsection.springboot.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import jakarta.transaction.Transactional;
import net.examsection.springboot.helper.helper;

import java.util.*;
import java.io.IOException;

@Service
public class ExamService {
	@Autowired
	private AmountsRepository amountsRepository;

	@Autowired
	private FacultyPaymentRepository facultyPaymentRepository;
	@Autowired
	private final BlockRepository blockRepository;


	@Autowired
	private final HistoryTeachersRepository historyTeachersRepository;
	@Autowired
	private final TeachersRepository teachersRepository;

	@Autowired
	private Teacher1Repository teacher1Repository;
    private ExamRepository productRepo;
	@Autowired
	private InfoRepository InfoRepo;
	@Autowired
	private ExamRepository ExamRepo;
	@Autowired
	private StrngthRepository StrengthRepo;
	@Transactional(rollbackOn = Exception.class)
	public void save(MultipartFile file , int countz) {
        try {
        	
            List<ExamSection> products = helper.convertExcelToListOfExamSection(file.getInputStream(),countz);
            this.productRepo.saveAll(products);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public List<ExamSection> getAllProducts() {
        return this.productRepo.findAll();
    }
    public List<Object[]> innerjoin() {
        return this.InfoRepo.innerJoinQuery();
    }
    public List<InfoTable> getAllProducts1() {
        return this.InfoRepo.findAll();
    }
    public int getlastentry() {
        return this.InfoRepo.getlastentry();
    }
	public int count() {
		return (int) this.InfoRepo.count();
	}
	public int count1() {
		return (int) this.ExamRepo.count();
	}
	public List<ExamSection> findById() {
		int infoid =this.InfoRepo.getlastentry();
		
		return this.productRepo.findByInfoTableId(infoid);
	}
	public List<BlocksAndStrengths> findAllStrength() {

		return this.StrengthRepo.findAll();
	}
	public Optional<List<InfoTable>> findinfo() {
		int infoid =this.InfoRepo.getlastentry();
		
		return this.InfoRepo.findInfo(infoid);
	}
	public String deleteById(int id) {
		 this.InfoRepo.deleteById(id);
		 this.productRepo.deleteinfo(id);
		return "Deleted";
	}
	
   
	public List<ExamSection> findByprogramname1(){
		int infoid =this.InfoRepo.getlastentry();
		int allStudents = this.productRepo.counter(infoid); // Fetch all students
		int counter =0;
		
		System.out.println(infoid);
		if(infoid==0) {
			infoid=1;
		}
	        List<ExamSection> patternedStudents = new ArrayList<>();
	        for(int i=0;i<allStudents-1;i++) {
	        	 if(counter<allStudents) {
	        	patternedStudents.add(productRepo.findByProgramNameAndInfoTableId("Computer Science",infoid).get(i));
	        	patternedStudents.add(productRepo.findByProgramNameAndInfoTableId("AIDS",infoid).get(i));
	        	patternedStudents.add(productRepo.findByProgramNameAndInfoTableId("Mechanical",infoid).get(i));
	        	
	        	 }
	        	 else {
	        		 break;
	        	 }
	        	 counter=counter+3;
	        	 
	        	 
	        }
	        return patternedStudents;
	}
    public List<ExamSection> fetchData() {
    	int infoTableId = this.InfoRepo.getlastentry();
        return productRepo.getExamInfoWithInnerJoinByInfoTableId(infoTableId);
    }
    public int cntr() {
    	int v =this.InfoRepo.getlastentry();
        return this.productRepo.counter(v);
    }

    @Transactional
	 public int saveAllData(String option, String date, String year, String subject, String slot,  String course, String code) {
	        List<InfoTable> savedData = new ArrayList<>();
	        InfoTable examSection = new InfoTable();
	        	examSection.setDate(date);
	        	examSection.setStartTime(slot);
	        	examSection.setOption(option);
	        	examSection.setCourseCode(code);
	        	examSection.setSubject(subject);
	        	examSection.setYear(year);
	        	examSection.setCourse(course);
	        	savedData.add(examSection);
	        	this.InfoRepo.saveAll(savedData);
	        	System.out.println(this.InfoRepo.getlastentry());
	        	return this.InfoRepo.getlastentry();
	        }

	public List<InfoTable> Allinfo() {
		// TODO Auto-generated method stub
		return this.InfoRepo.findAlldesc();
	}
	public List<ExamSection> getstudent(int request) {
		
		return this.productRepo.findByInfoTableId((long)request);
	}
	public List<BlocksAndStrengths> mainbuilding() {
		// TODO Auto-generated method stub
		return this.StrengthRepo.mainbuilding();
	}
	public List<BlocksAndStrengths> newbuilding() {
		// TODO Auto-generated method stub
		return this.StrengthRepo.newbuilding();
	}
	public void addBlockStrength(int totalCount, List<BlocksAndStrengths> selectedData) {
	    List<ExamSection> abc = productRepo.getallrecent(totalCount);
	    
	    for (BlocksAndStrengths ab : selectedData) {
	        int strength = ab.getStrengths();
	        int blockno = ab.getBlocks();
	        int counter = 0;

	        for (ExamSection ab1 : abc) {
	            if (counter < strength) {
	                ab1.setBlock_no(blockno);
	                counter++;
	            } else {
	                // Reset counter and move to the next strength if available
	                counter = 0;
	                strength = getNextStrength(selectedData, strength);
	                if (strength == 0) {
	                    // If no more strengths are available, exit the loop
	                    break;
	                }
	                blockno = getBlockForNextStrength(selectedData, strength);
	            }
	        }
	    }
	}

	// Helper method to get the next strength from selectedData
	private int getNextStrength(List<BlocksAndStrengths> selectedData, int currentStrength) {
	    int nextStrength = 0;
	    for (BlocksAndStrengths data : selectedData) {
	        if (data.getStrengths() > currentStrength) {
	            nextStrength = data.getStrengths();
	            break;
	        }
	    }
	    return nextStrength;
	}

	// Helper method to get the block number for the next strength
	private int getBlockForNextStrength(List<BlocksAndStrengths> selectedData, int nextStrength) {
	    for (BlocksAndStrengths data : selectedData) {
	        if (data.getStrengths() == nextStrength) {
	            return data.getBlocks();
	        }
	    }
	    return 0; // Return a default block number if not found
	}
	 private final SessionFactory sessionFactory; // Assuming you have a properly configured Hibernate SessionFactory
	 @Autowired
	    public ExamService(AmountsRepository amountsRepository, BlockRepository blockRepository, HistoryTeachersRepository historyTeachersRepository, TeachersRepository teachersRepository, SessionFactory sessionFactory, ExamRepository productRepo) {
		 this.amountsRepository = amountsRepository;
		 this.blockRepository = blockRepository;


		 this.historyTeachersRepository = historyTeachersRepository;
		 this.teachersRepository = teachersRepository;
		 this.sessionFactory = sessionFactory;
	        this.productRepo = productRepo;
	    }
	 public String setStrengths(Integer[] blocks, Integer[] strengths, String[] buildings, int totalCount) {
	        // Open a Hibernate session and start a transaction
	        try (Session session = sessionFactory.openSession()) {
	            Transaction transaction = session.beginTransaction();

	            List<ExamSection> abc = productRepo.getallrecent(totalCount);
//	            System.out.println(abc.size());
	            Collections.reverse(abc);
	            int count1 = 0, counter = 0, i = 0;

	            for (ExamSection ab1 : abc) {
	                if (i >= strengths.length || i >= blocks.length) {
	                    break;
	                }

	                ab1.setBlock_no(blocks[i]);
	                ab1.setStrength(strengths[i]);
	                ab1.setBuilding(buildings[i]);
	                // Mark the entity as dirty or modified
	                session.update(ab1);

	                counter++;

	                if (counter >= strengths[i]) {
	                    i++;
	                    counter = 0;
	                }

	                count1++;

	                if (count1 >= totalCount) {
	                    break;
	                }
	            }

	            // Commit the transaction to persist changes to the database
	            transaction.commit();
	        }

	        return "Changes have been persisted to the database.";
	    }
	public void updateData(List<BlocksAndStrengths> data) {
	    // First, delete all existing entries
	    this.StrengthRepo.deleteAll();

	    // Then, save each item individually
	    for (BlocksAndStrengths item : data) {
	    	
	        BlocksAndStrengths b = new BlocksAndStrengths(); // Create a new object for each item
	     //   System.out.println(b.getBlocks());
	        b.setBlocks(item.getBlocks());
	        b.setBuilding(item.getBuilding());
	        b.setStrengths(item.getStrengths());
	        this.StrengthRepo.save(b);
	    }
	}

	public List<Teacher1> fetchrecords1(){
		return teacher1Repository.findAll();
	}

	public void deleteAllTeacher1Data() {
		teacher1Repository.deleteAll();
	}

	public void saveTeachers(Teachers teacher) {

		teachersRepository.save(teacher);
	}

	public void deleteTeacherByName(String teacherName) {
		teachersRepository.deleteByTeacherName(teacherName);
	}

	public void saveSelectedTeachersData(List<HistoryTeachers> historyTeachersList) {
		historyTeachersRepository.saveAll(historyTeachersList);
	}





	public Block saveBlock(Block block) {
		return blockRepository.save(block);
	}


	public List<FacultyPayment> getFacultyPayments(String year, String std, String exam) {
		// Fetch data from the database based on the provided parameters
		List<FacultyPayment> facultyPayments = facultyPaymentRepository.findDistinctByYearAndStdAndExam(year, std, exam);

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
	public List<Integer> findBlockNosInDateRange(String startDate, String endDate) {
		return productRepo.findDistinctBlockNosInDateRange(startDate, endDate);
	}

	public List<HistoryTeachers> getAllTeachers() {
		return historyTeachersRepository.findAll();
	}
}
