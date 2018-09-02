package com.mobicare.apirest.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.mobicare.apirest.model.Colaborador;
import com.mobicare.apirest.repository.ColaboradorRepository;

@Service
public class ColaboradorService {

	@Autowired
	private ColaboradorRepository colaboradorRepository;

	public Colaborador atualizar(Long id, Colaborador colaborador) {
		Colaborador colaboradorSalvo = buscarPeloId(id);
		BeanUtils.copyProperties(colaborador, colaboradorSalvo, "id");
		return colaboradorRepository.save(colaboradorSalvo);
	}

	private Colaborador buscarPeloId(Long id) {
		Colaborador colaboradorSalvo = colaboradorRepository.findOne(id);

		if (colaboradorSalvo == null) {
			throw new EmptyResultDataAccessException(1);
		}

		return colaboradorSalvo;
	}
}
