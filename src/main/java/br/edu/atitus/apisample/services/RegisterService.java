package br.edu.atitus.apisample.services;

import org.springframework.stereotype.Service;

import br.edu.atitus.apisample.entities.RegisterEntity;
import br.edu.atitus.apisample.repositories.RegisterRepository;

import java.util.UUID;
import java.util.List;

@Service
public class RegisterService {
	
	private final RegisterRepository repository;
	
	public RegisterService(RegisterRepository repository) {
		this.repository = repository;
	} 
	
	public RegisterEntity save(RegisterEntity newRegister) throws Exception {
		
		if (newRegister.getLatitude() < -90 || newRegister.getLatitude() > 90) 
			throw new Exception("Latitude Inválida: A latitude deve estar entre -90 e 90.");
		
		if (newRegister.getLongitude() < -90 || newRegister.getLongitude() > 90) 
			throw new Exception("Longitude Inválida: A longitude deve estar entre -90 e 90.");
		
		if (newRegister.getUser() == null || newRegister.getUser().getId() == null)
			throw new Exception("Usuário Inválido: O usuário não foi encontrado ou o ID está ausente.");
		
		return repository.save(newRegister);
	}
	
	public RegisterEntity findById(UUID id) throws Exception {
		
		return repository.findById(id)
				.orElseThrow(() -> new Exception("Registro não encontrado para o ID fornecido."));
	}
	
	
	public List<RegisterEntity> findAll() throws Exception {
		List<RegisterEntity> registers = repository.findAll();
		
		if (registers.isEmpty()) {
			throw new Exception("Nenhum registro encontrado.");
		}
		
		return registers;
	}
	
	public void deleteById(UUID id) throws Exception {

        repository.deleteById(id);
	}
}
