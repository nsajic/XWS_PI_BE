package xws_pi_bezb.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import xws_pi_bezb.helpers.Helpers;
import xws_pi_bezb.irepositories.IValutaRepository;
import xws_pi_bezb.iservices.IValutaService;
import xws_pi_bezb.models.Valuta;

@Service
public class ValutaService implements IValutaService {

	@Autowired
	private IValutaRepository valutaRepository;

	@Override
	public List<Valuta> findAll() {
		// TODO Auto-generated method stub
		return valutaRepository.findAll();
	}

	@Override
	public Valuta findOne(Long id) {
		// TODO Auto-generated method stub
		return valutaRepository.findById(id);
	}

	@Override
	public void save(Valuta valuta) {
		// TODO Auto-generated method stub
		valutaRepository.save(valuta);
	}

	@Override
	public void delete(Long id) {
		// TODO Auto-generated method stub
		valutaRepository.delete(id);
	}

	@Override
	public List<Valuta> getBySearch(Valuta valuta) {
		List<Valuta> valute = new ArrayList<Valuta>();

		boolean praznaPretraga = true;

		for (Valuta valutaFor : valutaRepository.findAll()) {

			if (!Helpers.isNullOrEmpty(valuta.getNazivValute())) {
				praznaPretraga = false;
				if (valutaFor.getNazivValute().toLowerCase().contains(valuta.getNazivValute().toLowerCase())) {
					if (!valute.contains(valutaFor)) {
						valute.add(valutaFor);
					}
				}
			}

			if (!Helpers.isNullOrEmpty(valuta.getSifraValute())) {
				praznaPretraga = false;
				if (valutaFor.getSifraValute().toLowerCase().contains(valuta.getSifraValute().toLowerCase())) {
					if (!valute.contains(valutaFor)) {
						valute.add(valutaFor);
					}
				}
			}

			if (praznaPretraga) {
				valute = valutaRepository.findAll();
			}
		}
		return valute;
	}
}
