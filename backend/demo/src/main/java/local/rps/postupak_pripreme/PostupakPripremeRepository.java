package local.rps.postupak_pripreme;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PostupakPripremeRepository extends JpaRepository <PostupakPripreme, Integer> {	
	public List<PostupakPripreme> findByReceptId(int id);
}
