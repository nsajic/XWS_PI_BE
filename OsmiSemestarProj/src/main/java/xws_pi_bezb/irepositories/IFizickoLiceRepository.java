package xws_pi_bezb.irepositories;

import org.springframework.data.jpa.repository.JpaRepository;

import xws_pi_bezb.models.FizickoLice;

public interface IFizickoLiceRepository extends JpaRepository<FizickoLice, Long>{
	FizickoLice findById(Long id);
}