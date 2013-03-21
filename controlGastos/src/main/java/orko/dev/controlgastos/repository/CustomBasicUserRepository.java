package orko.dev.controlgastos.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import orko.dev.controlgastos.model.security.Principal;

public interface CustomBasicUserRepository<T> {
	
	List<T> findAllByUser(Principal principalLogin);

	Page<T> findAllByUser(Principal principalLogin, Pageable pageRequest);
	
}
