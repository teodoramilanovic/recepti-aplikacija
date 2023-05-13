package local.rps.grupa_jela_recepta;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface GrupaJelaReceptaRepository extends JpaRepository <GrupaJelaRecepta, Integer> {
	
	@Transactional
	@Modifying
	@Query("DELETE FROM GrupaJelaRecepta WHERE recept_id=?1")
	public void deleteByReceptId(int id);

	public List<GrupaJelaRecepta> findByGrupaJelaId(int id);

	public List<GrupaJelaRecepta> findByReceptId(int id);

}
