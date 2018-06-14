package br.com.demo.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import br.com.demo.model.User;

public interface UserRepository extends PagingAndSortingRepository<User, Long>{

	User findByUsername(String username);
}
