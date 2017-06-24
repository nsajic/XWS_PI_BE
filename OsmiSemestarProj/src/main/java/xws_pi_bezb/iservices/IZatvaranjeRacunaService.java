package xws_pi_bezb.iservices;

import org.springframework.stereotype.Service;

import xws_pi_bezb.models.ZatvaranjeRacuna;


@Service
public interface IZatvaranjeRacunaService {
	
	void save (ZatvaranjeRacuna zatvaranjeRacuna);

}
