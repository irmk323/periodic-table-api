package com.example.periodictableapi;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;


@Component
public class PeriodicTableApiDaoService {
	
	    public static final String NA = "n/a";
		String classPath =  "./src/main/resources/json/periodic_table.json";
		File json = new File(classPath);	
		
		public List<HashMap<String, String>> findAll(){
			ObjectMapper mapper = new ObjectMapper();
			
			List<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
			try {
				PeriodicTable[] periodicTable = mapper.readValue(json, PeriodicTable[].class);
				for (PeriodicTable oneTable : periodicTable) {
					HashMap<String, String> item = new HashMap<String, String>();	
					item.put("name", oneTable.getName());
					item.put("atomic_number", oneTable.getAtomic_number());
				    list.add(item);
				}
			} catch (JsonParseException e) {
				e.printStackTrace();
			} catch (JsonMappingException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}		
			return list;
		}

		public List<PeriodicTable> findByGroup(String number) {

			ObjectMapper mapper = new ObjectMapper();
			List<PeriodicTable> list = new ArrayList<>();
			try {
				PeriodicTable[] periodicTable = mapper.readValue(json, PeriodicTable[].class);
				for (PeriodicTable oneTable : periodicTable) {
					
					String group_number = groupNumberParser(oneTable.getGroup_block());
					if(group_number.equals(number)) {
						list.add(oneTable);
					}	    
				}
			} catch (JsonParseException e) {
				e.printStackTrace();
			} catch (JsonMappingException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}		
			return list;
		
		}
		


		public List<PeriodicTable> findByPeriod(String period) {

			ObjectMapper mapper = new ObjectMapper();
			List<PeriodicTable> list = new ArrayList<>();
			try {
				PeriodicTable[] periodicTable = mapper.readValue(json, PeriodicTable[].class);
				for (PeriodicTable oneTable : periodicTable) {
					if(oneTable.getPeriod().equals(period)) {
						list.add(oneTable);
					}
				}
			} catch (JsonParseException e) {
				e.printStackTrace();
			} catch (JsonMappingException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}		
			return list;
		
		}

		public List<HashMap<String, Object>> findByIndividual(String atomic_number) {
			ObjectMapper mapper = new ObjectMapper();
			List<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();
			try {
				PeriodicTable[] periodicTable = mapper.readValue(json, PeriodicTable[].class);
				for (PeriodicTable oneTable : periodicTable) {
					
					if(oneTable.getAtomic_number().equals(atomic_number)) {
						HashMap<String, Object> item = new HashMap<String, Object>();
						item.put("atomic_number", oneTable.getAtomic_number());
						item.put("name", oneTable.getName());
						if(oneTable.getAlternative_name().equals(NA)) {
							item.put("alternative_name", "none");
						}else {
							item.put("alternative_name", oneTable.getAlternative_name());
						}
						item.put("name_symbol", oneTable.getSymbol());
						item.put("appearance", oneTable.getAppearance());
						
						HashMap<String, Object> discover = discovererParser(oneTable.getDiscovery());

							item.put("discoverers", discover.get("discoverers"));
							item.put("discovery_year", discover.get("discovery_year"));

						String group_number = groupNumberParser(oneTable.getGroup_block());
						if(group_number.equals(NA)) {
							item.put("group", "0");
						}else {
							Integer int_group_number= Integer.valueOf(group_number);
							item.put("group", int_group_number);
						}
						item.put("Period", oneTable.getPeriod());
					    list.add(item);
					}
				}
			} catch (JsonParseException e) {
				e.printStackTrace();
			} catch (JsonMappingException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}		
			return list;
		}

		private String groupNumberParser(String group_block) {
			 String[] tempArray = group_block.split(" ");
			 String parced = tempArray[1].replace(",", "");
			 if(parced.matches("[0-9]+")) {
				  return parced;
			 }else {
				 return NA;
			 }
		}
		private HashMap<String, Object> discovererParser(String discovery) {
			HashMap<String, Object> discoverer_year = new HashMap<String, Object>();
			
			if(discovery.equals(NA)) {
				discoverer_year.put("discoverers", "");
				discoverer_year.put("discovery_year", "unknown");
			} else if(discovery.substring(discovery.length() - 6).matches("\\([0-9]+\\)")) {
				String[] discoverers = discovery.substring(0, discovery.length() - 7).split(", ");;
				discoverer_year.put("discoverers", discoverers);
				discoverer_year.put("discovery_year", 
						discovery.substring(discovery.length() - 5 , discovery.length() - 1));
			}else {
				discoverer_year.put("discoverers", "");
				discoverer_year.put("discovery_year", discovery);
			}
			 return discoverer_year;
		}

}
