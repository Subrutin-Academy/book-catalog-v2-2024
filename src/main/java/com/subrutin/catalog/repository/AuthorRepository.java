package com.subrutin.catalog.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.subrutin.catalog.domain.Author;
import com.subrutin.catalog.dto.AuthorQueryDTO;

public interface AuthorRepository extends JpaRepository<Author, Long>{

	//method name convention
	//find+keyword
	//sql -> select * from Author a where a.id= :authorId
	public Optional<Author> findById(Long id);
	
	public List<Author> findBySecureIdIn(List<String> authorIdList);
		
	public Optional<Author> findBySecureId(String id);
	//where id = :id AND deleted=false
	public Optional<Author> findByIdAndDeletedFalse(Long id);

	
	//sql -> select a from Author a where a.author_name = :authorName
	public List<Author> findByNameLike(String authorName);
	
	@Query("SELECT new com.subrutin.catalog.dto.AuthorQueryDTO(b.id, ba.name) "
			+ "FROM Book b "
			+ "JOIN b.authors ba "
			+ "WHERE b.id IN :bookIdList")
	public List<AuthorQueryDTO> findAuthorsByBookIdList(List<Long> bookIdList); 
	
}
