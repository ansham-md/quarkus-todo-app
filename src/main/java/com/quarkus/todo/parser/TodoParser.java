package com.quarkus.todo.parser;

import com.quarkus.todo.dto.TodoDTO;
import com.quarkus.todo.model.Todo;

public class TodoParser extends BaseParser<Todo, TodoDTO> {

	public static TodoParser get() {
		return new TodoParser();
	}

	@Override
	public Todo toEntity(TodoDTO dto) {
		Todo entity = new Todo();

		entity.setId(dto.getId());
		entity.setName(dto.getName());
		entity.setDateCreation(dto.getDateCreation());
		entity.setStatus(dto.getStatus());
		entity.setProviderId(dto.getProviderId());
		entity.setMoneyhubId(dto.getMoneyhubId());
		entity.setAccountType(dto.getAccountType()!=null ?
				dto.getAccountType() : null);
		entity.setCorrelationId(dto.getCorrelationId());
		return entity;
	}

	@Override
	public TodoDTO toDTO(Todo entity) {
		TodoDTO dto = new TodoDTO();

		dto.setId(entity.getId());
		dto.setName(entity.getName());
		dto.setDateCreation(entity.getDateCreation());
		dto.setStatus(entity.getStatus());
		dto.setAccountType(entity.getAccountType()!=null ?
				entity.getAccountType() : null);
		dto.setProviderId(entity.getProviderId());
		dto.setMoneyhubId(entity.getMoneyhubId());
		dto.setCorrelationId(entity.getCorrelationId());
		return dto;
	}
}
