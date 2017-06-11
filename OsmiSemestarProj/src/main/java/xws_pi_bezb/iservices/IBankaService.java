package xws_pi_bezb.iservices;

import java.util.List;

import org.springframework.stereotype.Component;

import xws_pi_bezb.models.Banka;

public interface IBankaService {

	List<Banka> findAll();
	
	Banka findOne(Long id);

	void save(Banka banka);

	void delete(Long id);
	
	List<Banka> getBySearch(Banka banka);

	
}
