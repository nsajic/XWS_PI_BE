package xws_pi_bezb.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import xws_pi_bezb.irepositories.INaseljenoMestoRepository;
import xws_pi_bezb.iservices.INaseljenoMestoService;
import xws_pi_bezb.models.NaseljenoMesto;

@Service
public class NaseljenoMestoService implements INaseljenoMestoService{

	@Autowired
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
}
