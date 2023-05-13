package local.rps.komentar;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface KomentarRepository extends JpaRepository <Komentar, Integer> {	
	public List<Komentar> findByReceptId(int id);
}
