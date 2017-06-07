package xws_pi_bezb.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import xws_pi_bezb.irepositories.IRacunRepository;
import xws_pi_bezb.iservices.IRacunService;
import xws_pi_bezb.models.Racun;


@Service
public class RacunService implements IRacunService{
	
	@Autowired
	private IRacunRepository racunRepository;
	
	@Override
	public List<Racun> findAll() {
		// TODO Auto-generated method stub
		return racunRepository.findAll();
	}

	/*@Override
	public List<Racun> getBySearchText(String serachText) {
		// TODO Auto-generated method stub
		return null;
	}*/

	@Override
	public Racun findOne(Long id) {
		// TODO Auto-generated method stub
		return racunRepository.findById(id);
	}

	@Override
	public void save(Racun racun) {
		racunRepository.save(racun);
		
	}

	@Override
	public void delete(Long id) {
		racunRepository.delete(id);
		
	}

	/*@Autowired
	private INaseljenoMestoRepository naseljenoMestoRepository;

	@Override
	public void delete(Long id) {
		naseljenoMestoRepository.delete(id);
	}

	@Override
	public List<NaseljenoMesto> findAll() {
		return naseljenoMestoRepository.findAll();
	}

	@Override
	public NaseljenoMesto findOne(Long id) {
		return naseljenoMestoRepository.findById(id);
	}

	@Override
	public void save(NaseljenoMesto naseljenoMesto) {
		naseljenoMestoRepository.save(naseljenoMesto);
	}

	@Override
	public List<NaseljenoMesto> getBySearchText(String serachText) {
		List<NaseljenoMesto> naseljenaMesta = new ArrayList<NaseljenoMesto>();
		for(NaseljenoMesto naseljenoMesto : naseljenoMestoRepository.findAll()){
			if(naseljenoMesto.getNaziv().toLowerCase().contains(serachText.toLowerCase())||
				naseljenoMesto.getPttOznaka().toLowerCase().contains(serachText.toLowerCase())||
				naseljenoMesto.getDrzava().getNazivDrzave().toLowerCase().contains(serachText.toLowerCase())){
				naseljenaMesta.add(naseljenoMesto);
			}
		}
		return naseljenaMesta;
	}

	@Override
	public List<NaseljenoMesto> getByDrzava(Drzava drzava) {
		List<NaseljenoMesto> naseljenaMesta = new ArrayList<NaseljenoMesto>();
		for(NaseljenoMesto naseljenoMesto : naseljenoMestoRepository.findAll()){
			if(naseljenoMesto.getDrzava().getId() == drzava.getId()){
				naseljenaMesta.add(naseljenoMesto);
			}
		}
		return naseljenaMesta;
	}*/
}
