package local.rps.tag;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface TagRepository extends JpaRepository <Tag, Integer> {
	
	@Query("SELECT t FROM Tag t WHERE t.naziv LIKE CONCAT('%',:naziv,'%')")
	public List<Tag> getAllTagoviByNaziv(@Param("naziv") String naziv);	
}