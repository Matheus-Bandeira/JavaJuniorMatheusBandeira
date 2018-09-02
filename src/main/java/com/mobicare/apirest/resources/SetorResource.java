package com.mobicare.apirest.resources;

import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mobicare.apirest.event.RecursoCriadoEvent;
import com.mobicare.apirest.model.Colaborador;
import com.mobicare.apirest.model.Setor;
import com.mobicare.apirest.repository.ColaboradorRepository;
import com.mobicare.apirest.repository.SetorRepository;

@RestController
@RequestMapping("/setores")
public class SetorResource {

	@Autowired
	SetorRepository setorRepository;
	
	@Autowired
	private ApplicationEventPublisher publisher;
	
	@Autowired
	ColaboradorRepository colaboradorRepository;
	
	@Autowired
	ColaboradorResorce cr ;

	@GetMapping
	public List<Setor> listar() {
		List<Colaborador> colaboradores = colaboradorRepository.findAll();
		List<Setor> setores = setorRepository.findAll();
		
		for(Setor s: setores) {
			
		}
		
		return setorRepository.findAll();
	}

	@PostMapping
	public ResponseEntity<Setor> criar(@Valid @RequestBody Setor setor, HttpServletResponse response) {
		Setor setorSalvo = setorRepository.save(setor);
		publisher.publishEvent(new RecursoCriadoEvent(this, response, setor.getId()));
		return ResponseEntity.status(HttpStatus.CREATED).body(setorSalvo);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Setor> buscarPeloId(@PathVariable Long id) {
		Setor setor = setorRepository.findOne(id);
		return setor != null ? ResponseEntity.ok(setor) : ResponseEntity.notFound().build();
	}

	// @GetMapping("/{codigo}")
	// public ResponseEntity<Categoria> buscarPeloCodigo(@PathVariable Long codigo)
	// {
	// Categoria categoria = categoriaRepository.findOne(codigo);
	// return categoria != null ? ResponseEntity.ok(categoria) :
	// ResponseEntity.notFound().build();
	// }

	// @PostMapping
	// public ResponseEntity<Categoria> criar(@Valid @RequestBody Categoria
	// categoria, HttpServletResponse response) {
	// Categoria categoriaSalva = categoriaRepository.save(categoria);
	//
	// publisher.publishEvent(new RecursoCriadoEvent(this, response,
	// categoria.getCodigo()));
	//
	// return ResponseEntity.status(HttpStatus.CREATED).body(categoriaSalva);
	// }
}
