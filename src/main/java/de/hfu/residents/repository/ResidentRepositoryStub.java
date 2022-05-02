package de.hfu.residents.repository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import de.hfu.residents.domain.Resident;

public class ResidentRepositoryStub implements ResidentRepository {
	private List<Resident> residents;
	
	@SuppressWarnings("deprecation")
	public ResidentRepositoryStub() {
		// create example objects
		this.residents = new ArrayList<>();
		this.residents.add(new Resident("Michael", "Meyer", "Beispielstrasse 1337", "Beispielstadt", new Date(2000, 05, 03)));
		this.residents.add(new Resident("Susanne", "Beispielfrau", "EineGasse 12", "EineStadt", new Date(1998, 07, 12)));
		this.residents.add(new Resident("Max", "Mustermann", "Musterstrasse 1", "Musterstadt", new Date(1987, 01, 30)));
	}
	
	public List<Resident> getResidents(){
		return this.residents;
	}
}
