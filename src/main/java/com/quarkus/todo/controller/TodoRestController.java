package com.quarkus.todo.controller;

import java.util.Set;
import java.util.stream.Collectors;

import com.quarkus.todo.annotations.IsValidEnum;
import com.quarkus.todo.constants.ProvidersConstants;
import com.quarkus.todo.dto.TodoDTO;
import com.quarkus.todo.service.TodoService;
import jakarta.annotation.security.PermitAll;
import jakarta.inject.Inject;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Valid;
import jakarta.validation.Validator;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.enums.SchemaType;

import static com.quarkus.todo.constants.HeaderConstants.*;

@Path("todo")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class TodoRestController {

	@Inject
	TodoService service;

	@Inject
	Validator validator;

	@GET
	@Operation(summary = "List To Do, description = Returns a list of Todo.class")
	@APIResponse(responseCode = "200", description = " Get to-do list",
			content = {@Content(
					mediaType = "application/json",
					schema = @Schema(implementation = TodoDTO.class, type = SchemaType.ARRAY))
					})
	@PermitAll
	public Response listAllTask() {
		return Response.status(Response.Status.OK).entity(service.list()).build();
	}

	@POST
	@Operation(summary = "Add a task summary", description = "Add a task description")
	@APIResponse(responseCode = "201", description = "create a new task",
			content = {@Content(
					mediaType = "application/json",
					schema = @Schema(implementation = TodoDTO.class))
					})
	public Response addTask(@Size(min = 2,max = 10,message = "Invalid header length {} \"x-mh-id\"")
								@HeaderParam(X_GTTP_MH_ID) final String moneyHubId,
							@IsValidEnum(value = ProvidersConstants.class,message = "Invalid Header Enum Param")
								@HeaderParam(X_GTTP_PROVIDER_ID) final String providerId,
							@NotNull @Size(min = 3,max = 21,message = "Invalid header length {} \"x-gttp-txn-correlation-id\"")
								@HeaderParam(X_GTTP_TXN_CORRELATION_ID) final String correlationId,
								@HeaderParam(X_GTTP_ACCOUNT_TYPE) final String accountType,
							@Valid TodoDTO todo) {
		todo.setHeaders(moneyHubId,providerId,accountType,correlationId);
		Set<ConstraintViolation<TodoDTO>> errors = validator.validate(todo);
		if (errors.isEmpty()) {
			service.insert(todo);
		} else {
			throw new NotFoundException(errors.stream().map(ConstraintViolation::getMessage).collect(Collectors.joining(", ")).toString());
		}
		return Response.status(Response.Status.CREATED).build();
	}

	@DELETE
	@Path("/{id}")
	@Operation(summary = "Delete a task summary", description = "Delete a task description")
	@APIResponse(responseCode = "202", description = "delete task",
			content = {@Content(
					mediaType = "application/json",
					schema = @Schema(implementation = TodoDTO.class))
					})
	public Response deleteTask(@PathParam("id") Long id) {
		service.delete(id);
		return Response.status(Response.Status.ACCEPTED).build();
	}

	@PUT
	@Path("/{id}/{status}")
	@Operation(summary = "Edit status", description = "Edit a task based on ID")
	@APIResponse(responseCode = "200", description = "update a task",
			content = {@Content(
					mediaType = "application/json",
					schema = @Schema(implementation = TodoDTO.class))
					})
	public Response updateTask(@PathParam("id") Long id, @PathParam("status") String status) {
		service.updateStatus(id, status);
		return Response.status(Response.Status.OK).build();
	}
}
