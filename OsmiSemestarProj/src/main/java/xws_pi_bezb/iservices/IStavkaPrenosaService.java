package xws_pi_bezb.iservices;

import org.springframework.stereotype.Service;

import xws_pi_bezb.models.StavkaPrenosa;

@Service
public interface IStavkaPrenosaService{

	void save (StavkaPrenosa stavkaPrenosa);
	
	
}
