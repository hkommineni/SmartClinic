package queue;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller    
@RequestMapping(path="/clinic") 
public class DoctorController {
	@Autowired 
	private DoctorRepository doctorRepository;
	
	@GetMapping(path="/addDoctor") 
	public @ResponseBody String addNewDoctor (@RequestParam String firstName, @RequestParam String lastName
			, @RequestParam String email, @RequestParam String qualification) {
				
		System.out.println("firstName: "+firstName);
		Doctor doc = new Doctor();
		doc.setFirstName(firstName);
		doc.setLastName(lastName);
		doc.setEmail(email);
		doc.setQualification(qualification);
		doctorRepository.save(doc);
		return "Saved";
	}
	
	@GetMapping(path="/allDoctors")
	public @ResponseBody Iterable<Doctor> getAllDoctors() {
		return doctorRepository.findAll();
	}
	
	@GetMapping(path="/getDoctorByName")
	public @ResponseBody List<Doctor> getDoctorByName(@RequestParam String firstName, @RequestParam String lastName) {
		System.out.println("firstName: "+firstName);
		return doctorRepository.findByFirstNameAndLastname(firstName, lastName);
	}
	
	@GetMapping(path="/getDoctorByEmail")
	public @ResponseBody List<Doctor> getDoctorByEmail(@RequestParam String email) {
		System.out.println("firstName: "+email);
		return doctorRepository.findByEmail(email);
	}
	
	@GetMapping(path="/getDoctorsByClinic")
	public @ResponseBody List<Doctorwidget> getDoctorsByClinic(@RequestParam Integer id) {
		System.out.println("id: "+id);
		List<Doctorwidget> docWidgetLst = new ArrayList<Doctorwidget>();
		List docwid = doctorRepository.findByClinic(id);
		for(int i=0; i<docwid.size();i++){
			System.out.println("i: "+docwid.get(i).toString());
			Object[] data =  (Object[]) docwid.get(i);
				Doctorwidget dw = new Doctorwidget();
				dw.setDoctorFirstName((String) data[0]);
				dw.setDoctorLastName((String) data[1]);
				dw.setDocQualification((String) data[2]);
				dw.setClinicName((String) data[3]);
				String startDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format((Timestamp) data[4]);
				String endDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format((Timestamp) data[5]);
				dw.setStartTime(startDate);
				dw.setEndTime(endDate);
				docWidgetLst.add(dw);
//			}
		}
		return docWidgetLst;
	}
}
