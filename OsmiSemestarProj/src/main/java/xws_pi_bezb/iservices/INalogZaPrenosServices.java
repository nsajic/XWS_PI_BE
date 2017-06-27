package xws_pi_bezb.iservices;

import java.util.List;

import xws_pi_bezb.models.NalogZaPrenos;

public interface INalogZaPrenosServices {
	void save(NalogZaPrenos nalog);
	void delete(NalogZaPrenos nalog);
	
	List<NalogZaPrenos> findAll();
	NalogZaPrenos findOne(Long id);
}
