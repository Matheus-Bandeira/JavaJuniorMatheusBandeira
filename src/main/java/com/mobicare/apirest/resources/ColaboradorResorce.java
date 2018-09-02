package com.mobicare.apirest.resources;

import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.expression.spel.CompilablePropertyAccessor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.mobicare.apirest.event.RecursoCriadoEvent;
import com.mobicare.apirest.model.Colaborador;
import com.mobicare.apirest.model.Setor;
import com.mobicare.apirest.repository.ColaboradorRepository;
import com.mobicare.apirest.service.ColaboradorService;

@RestController
@RequestMapping("/colaboradores")
public class ColaboradorResorce {

	@Autowired
	private ColaboradorRepository colaboradorRepository;

	@Autowired
	private ColaboradorService colaboradorService;

	@Autowired
	private ApplicationEventPublisher publisher;

	@GetMapping
	public List<Colaborador> buscarTodos() {
		return colaboradorRepository.findAll();
	}

	@GetMapping("/{id}")
	public ResponseEntity<Colaborador> buscarPeloId(@PathVariable Long id) {
		Colaborador colaborador = colaboradorRepository.findOne(id);
		return colaborador != null ? ResponseEntity.ok(colaborador) : ResponseEntity.notFound().build();
	}

	@PostMapping
	public ResponseEntity<Colaborador> criar(@Valid @RequestBody Colaborador colaborador,
			HttpServletResponse response) {

		Colaborador colaboradorSalvo = colaboradorRepository.save(colaborador);
		publisher.publishEvent(new RecursoCriadoEvent(this, response, colaborador.getId()));
		return ResponseEntity.status(HttpStatus.CREATED).body(colaboradorSalvo);
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Long id) {
		colaboradorRepository.delete(id);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Colaborador> atualizar(@PathVariable Long id, @Valid @RequestBody Colaborador colaborador) {
		Colaborador colaboradorSalvo = colaboradorService.atualizar(id, colaborador);
		return ResponseEntity.ok(colaboradorSalvo);
	}

	// @PutMapping("/{codigo}")
	// public ResponseEntity<Pessoa> atualizar(@PathVariable Long codigo, @Valid
	// @RequestBody Pessoa pessoa){
	// Pessoa pessoaSalva = pessoaService.atualizar(codigo, pessoa);
	// return ResponseEntity.ok(pessoaSalva);
	// }

}
