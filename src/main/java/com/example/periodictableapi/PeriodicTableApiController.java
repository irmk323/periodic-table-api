package com.example.periodictableapi;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.example.periodictableapi.exception.InputNotFoundException;

@RestController
public class PeriodicTableApiController {
	
	@Autowired
	private PeriodicTableApiDaoService service;
		
	@GetMapping(path = "/all_list")
	public List<HashMap<String, String>> allList() throws IOException {
		return service.findAll();
	}
	
	@GetMapping(path = "/group/{number}")
	public List<PeriodicTable> group(@PathVariable String number) {
		List<PeriodicTable> pt = service.findByGroup(number);
		if(pt.size()==0) {
			throw new InputNotFoundException("there is no such group number = : " + number);
		}
		return pt;
	}
	
	@GetMapping(path = "/period/{period}")
	public List<PeriodicTable> period(@PathVariable String period) {
		List<PeriodicTable> pt = service.findByPeriod(period);
		if(pt.size()==0) {
			throw new InputNotFoundException("there is no such period = : " + period);
		}
		return pt;
	}
	
	@GetMapping(path = "/individual/{atomic_number}")
	public List<HashMap<String, Object>> individual(@PathVariable String atomic_number) {
		List<HashMap<String, Object>> pt = service.findByIndividual(atomic_number);
		if(pt.size()==0) {
			throw new InputNotFoundException("there is no such atomic_number = : " + atomic_number);
		}
		return pt;
	}

}
