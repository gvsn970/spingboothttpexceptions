package com.onpassive.springbootexceptionhttps.permissions;

import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.onpassive.springbootexceptionhttps.roles.RolesModel;

@Entity
@Table(name = "permissions")
public class PermissionModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotNull
	@Size(max = 100)
	@Column(unique = true)
	private String permissionName;

	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "created_at")
	private Date createdAt = new Date();

	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "last_updated_at")
	private Date lastUpdatedAt = new Date();

	//@ManyToMany(fetch = FetchType., cascade = { CascadeType.MERGE, CascadeType.PERSIST })
	@ManyToMany(mappedBy = "permissions")
	private Set<RolesModel> role;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPermissionName() {
		return permissionName;
	}

	public void setPermissionName(String permissionName) {
		this.permissionName = permissionName;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Date getLastUpdatedAt() {
		return lastUpdatedAt;
	}

	public void setLastUpdatedAt(Date lastUpdatedAt) {
		this.lastUpdatedAt = lastUpdatedAt;
	}

	public Set<RolesModel> getRole() {
		return role;
	}

	public void setRole(Set<RolesModel> role) {
		this.role = role;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("PermissionModel [id=");
		builder.append(id);
		builder.append(", permissionName=");
		builder.append(permissionName);
		builder.append(", createdAt=");
		builder.append(createdAt);
		builder.append(", lastUpdatedAt=");
		builder.append(lastUpdatedAt);
		builder.append(", role=");
		builder.append(role);
		builder.append("]");
		return builder.toString();
	}

	
}
