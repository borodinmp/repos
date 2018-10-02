package home.repos;

import home.domain.Message;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface MessageRepo extends CrudRepository<Message, Long>{

    List<Message> findByNameOrgLikeOrInnLikeOrOgrnnLikeOrAddressOrgLike(String nameOrg, String inn, String ogrnn, String addressOrg);

    List<Message> deleteById(Integer id);
}


