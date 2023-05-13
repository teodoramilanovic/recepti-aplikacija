package local.rps.recept;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ReceptRepository extends JpaRepository <Recept, Integer> {	
	@Query("SELECT r FROM Recept r WHERE r.naziv LIKE CONCAT('%',:naziv,'%')")
	public List<Recept> getAllReceptiByNaziv(@Param("naziv") String naziv);

	public List<Recept> findByKorisnikId(int id, Sort sort);
	
	@Query("SELECT r FROM Recept r WHERE r.tezinaPripreme=?1")
	public List<Recept> findByTezinaPripreme(String tezina);

	public List<Recept> findByKorisnikId(int id);
	
}
