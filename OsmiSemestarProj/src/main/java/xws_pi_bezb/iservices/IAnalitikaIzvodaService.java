package xws_pi_bezb.iservices;

import org.springframework.stereotype.Service;

import xws_pi_bezb.models.AnalitikaIzvoda;

@Service
public interface IAnalitikaIzvodaService{

	void save (AnalitikaIzvoda analitikaIzvoda);
}
