package com.feliz.workshopmongo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.feliz.workshopmongo.domain.User;
import com.feliz.workshopmongo.dto.UserDTO;
import com.feliz.workshopmongo.repository.UserRepository;
import com.feliz.workshopmongo.service.exception.ObjectNotFoundException;

@Service
public class UserService {

	@Autowired
	private UserRepository repo;

	public List<User> findAll() {
		return repo.findAll();
	}

	public User findById(String id) {
		Optional<User> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado"));

	}
	
	public User insert(User obj) {
		
		return repo.insert(obj);
	}
	
	public void delete(String id) {
		repo.deleteById(id);
	}
	
	public void updateData(User newobj, User obj ) {
		newobj.setName(obj.getName());
		newobj.setEmail(obj.getEmail());
		
	}
	
	public User update(User obj) {
		User newobj = findById(obj.getId());
		updateData(newobj, obj);
		return repo.save(newobj);
	}
	
	public User fromDTO(UserDTO objDto) {
		
		return new User(objDto.getId(), objDto.getName(), objDto.getEmail());
		
	}

}
