package local.rps.tag_recepta;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TagReceptaRepository extends JpaRepository <TagRecepta, Integer> {	
	public List<TagRecepta> findByReceptId(int id);
	public List<TagRecepta> findByTagId(int id);
}
