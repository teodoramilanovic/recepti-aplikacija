package local.rps.omiljen_recept;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface OmiljenReceptRepository extends JpaRepository <OmiljenRecept, Integer> {	
	public List<OmiljenRecept> findByReceptId(int id);

	public List<OmiljenRecept> findByKolekcijaId(int id);
	
	@Transactional
	@Modifying
	@Query("DELETE FROM OmiljenRecept WHERE recept_id=?1")
	public void deleteByReceptId(int id);
}

