package com.quarkus.todo.model;

import java.time.LocalDateTime;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;

@Entity
@Table(name = "todo")
@NamedNativeQueries({
		@NamedNativeQuery(name = "LIST_TODO", query = ""
				+ "SELECT id, name, dateCreation, status, moneyhubId, providerId, accountType, correlationId FROM todo", resultClass = Todo.class),
		@NamedNativeQuery(name = "DELETE_TODO", query = "DELETE todo WHERE id = :id"),
		@NamedNativeQuery(name = "SEARCH_BY_ID", query = ""
				+ "SELECT id, name, dateCreation,status, moneyhubId, providerId, accountType, correlationId FROM todo where id = :id", resultClass = Todo.class),
		@NamedNativeQuery(name = "UPDATE_TODO", query = "UPDATE todo "
				+ "set name = :name, dateCreation = :dateCreation,status = :status WHERE id = :id"), })
public class Todo extends PanacheEntityBase {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "name", length = 250, nullable = false)
	private String name;

	@Column(name = "dateCreation", nullable = false, updatable = false)
	@CreationTimestamp
	private LocalDateTime dateCreation;

	@Column(name = "status", length = 250, nullable = false)
	private String status;

	@Column(name="moneyhubId", length = 255, nullable = false)
	private String moneyhubId;

	@Column(name = "providerId", length = 255, nullable = false)
	private String providerId;

	@Column(name = "accountType", length = 255)
	private String accountType;

	@Column(name = "correlationId", length = 255, nullable = false)
	private String correlationId;

	public String getMoneyhubId() {
		return moneyhubId;
	}

	public void setMoneyhubId(String moneyhubId) {
		this.moneyhubId = moneyhubId;
	}

	public String getProviderId() {
		return providerId;
	}

	public void setProviderId(String providerId) {
		this.providerId = providerId;
	}

	public String getAccountType() {
		return accountType;
	}

	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}

	public String getCorrelationId() {
		return correlationId;
	}

	public void setCorrelationId(String correlationId) {
		this.correlationId = correlationId;
	}

	public Todo(Long id) {
		this.id = id;
	}

	public Todo() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public LocalDateTime getDateCreation() {
		return dateCreation;
	}

	public void setDateCreation(LocalDateTime dateCreation) {
		this.dateCreation = dateCreation;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
}
