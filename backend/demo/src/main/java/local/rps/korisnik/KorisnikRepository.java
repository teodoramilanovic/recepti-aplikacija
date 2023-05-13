package local.rps.korisnik;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface KorisnikRepository extends JpaRepository <Korisnik, Integer> {
	@Query("FROM Korisnik WHERE email = ?1")
	public List<Korisnik> findByEmail(String email);
}