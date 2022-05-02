package de.hfu;

import org.junit.Test;
import static org.junit.Assert.*;

import java.util.Date;
import java.util.List;

import org.junit.Before;

import de.hfu.residents.domain.Resident;
import de.hfu.residents.repository.ResidentRepositoryStub;
import de.hfu.residents.service.BaseResidentService;
import de.hfu.residents.service.ResidentServiceException;

public class ResidentsStubTest {
	private BaseResidentService residentService;
	private Resident michael;
	private Resident susanne;
	private Resident max;
	
	@SuppressWarnings("deprecation")
	@Before
	public void initTest() {
		this.residentService = new BaseResidentService();
		this.residentService.setResidentRepository(new ResidentRepositoryStub());
		this.michael = new Resident("Michael", "Meyer", "Beispielstrasse 1337", "Beispielstadt", new Date(2000, 05, 03));
		this.susanne = new Resident("Susanne", "Beispielfrau", "EineGasse 12", "EineStadt", new Date(1998, 07, 12));
		this.max = new Resident("Max", "Mustermann", "Musterstrasse 1", "Musterstadt", new Date(1987, 01, 30));
	}
	
	@Test(expected=ResidentServiceException.class, timeout=1000)
	public void testUniqueWildcard() throws ResidentServiceException {
		residentService.getUniqueResident(
				new Resident("*", "*", "*", "*", null));
	}
	
	@Test(expected=ResidentServiceException.class, timeout=1000)
	public void testUniquekeinErgebnis() throws ResidentServiceException {
		residentService.getUniqueResident(
				new Resident("a", "b", "c", "d", null));
	}
	
	@Test
	public void testGetUniqueResident() throws ResidentServiceException {
		assert(residentService.getUniqueResident(michael).getGivenName().equals(michael.getGivenName()));
		assert(residentService.getUniqueResident(susanne).getStreet().equals(susanne.getStreet()));
		assert(residentService.getUniqueResident(max).getDateOfBirth().equals(max.getDateOfBirth()));
	}
	
	@Test
	public void testGetFilteredResidentList() {
		List<Resident> test1 = residentService.getFilteredResidentsList(
				new Resident("Sus*", "*", "*", "*", null));
		assert(test1.get(0).getGivenName().equals("Susanne"));
		
		List<Resident> test2 = residentService.getFilteredResidentsList(
				new Resident("Michael", "*", null, "Beispielstadt", null));
		assert(test2.get(0).getGivenName().equals("Michael"));
		
		List<Resident> test3 = residentService.getFilteredResidentsList(
				new Resident(null, null, "*", null, null));
		assert(test3.size() == 3);
		
		List<Resident> test4 = residentService.getFilteredResidentsList(
				new Resident("Moritz", null, null, "*", null));
		assert(test4.isEmpty());
		
		List<Resident> test5 = residentService.getFilteredResidentsList(max);
		assert(test5.get(0).getGivenName().equals("Max"));
	}

}
