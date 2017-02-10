package queue;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller    // this class is a Controller
@RequestMapping(path="/clinic") 
public class PatientController {
	
	@Autowired 
	private PatientRepository patientRepository;
	
	@GetMapping(path="/addPatient") // Map ONLY GET Requests
	public @ResponseBody String addNewPatient (@RequestParam String firstName, @RequestParam String lastName
			, @RequestParam String email) {
				
		System.out.println("firstName: "+firstName);
		Patient p = new Patient();
		p.setFirstName(firstName);
		p.setLastName(lastName);
		p.setEmail(email);
		patientRepository.save(p);		
		return "Saved";
	}
	
	@GetMapping(path="/allPatients")
	public @ResponseBody Iterable<Patient> getAllPatients() {
		return patientRepository.findAll();
	}
	
	@GetMapping(path="/getPatientByName")
	public @ResponseBody List<Patient> getPatientByName(@RequestParam String firstName, @RequestParam String lastName) {
		
		System.out.println("firstName: "+firstName);
		return patientRepository.findByFirstNameAndLastname(firstName, lastName);
	}
	
	@GetMapping(path="/getPatientByEmail")
	public @ResponseBody List<Patient> getPatientByEmail(@RequestParam String email) {
		
		System.out.println("firstName: "+email);
		return patientRepository.findByEmail(email);
	}
	

}
