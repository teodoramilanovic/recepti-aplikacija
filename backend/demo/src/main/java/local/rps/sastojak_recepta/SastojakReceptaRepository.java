package local.rps.sastojak_recepta;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface SastojakReceptaRepository extends JpaRepository <SastojakRecepta, Integer> {	
	public List<SastojakRecepta> findByReceptId(int id);
}
