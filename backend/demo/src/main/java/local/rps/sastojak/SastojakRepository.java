package local.rps.sastojak;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface SastojakRepository extends JpaRepository <Sastojak, Integer> {
	
	@Query("SELECT s FROM Sastojak s WHERE s.naziv LIKE CONCAT('%',:naziv,'%')")
	public List<Sastojak> getAllSastojciByNaziv(@Param("naziv") String naziv);	
}
