package com.denorro.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotNull;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Technology {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@NotNull
	private String skill;
	
	@ManyToMany(fetch=FetchType.LAZY, mappedBy="skills")
	@JsonIgnore
	private Set<Developer> developers = new HashSet<>();
	
	@NotNull
	private String link;
	
	@Column(name="repository")
	private String versionControlRepo;
	
	@CreationTimestamp
	private Date created;
	
	@UpdateTimestamp
	private Date updated;
	
	public Technology() {}
	
	public Technology(@NotNull String skill, String link, String versionControlRepo) {
		super();
		this.skill = skill;
		this.link = link;
		this.versionControlRepo = versionControlRepo;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getSkill() {
		return skill;
	}

	public void setSkill(String skill) {
		this.skill = skill;
	}

	public Set<Developer> getDevelopers() {
		return developers;
	}

	public void setDevelopers(Set<Developer> developers) {
		this.developers = developers;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public String getVersionControlRepo() {
		return versionControlRepo;
	}

	public void setVersionControlRepo(String versionControlRepo) {
		this.versionControlRepo = versionControlRepo;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public Date getUpdated() {
		return updated;
	}

	public void setUpdated(Date updated) {
		this.updated = updated;
	}

	@Override
	public String toString() {
		return "Technology [id=" + id + ", skill=" + skill + ", link=" + link + ", versionControlRepo="
				+ versionControlRepo + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((created == null) ? 0 : created.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((link == null) ? 0 : link.hashCode());
		result = prime * result + ((skill == null) ? 0 : skill.hashCode());
		result = prime * result + ((versionControlRepo == null) ? 0 : versionControlRepo.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Technology other = (Technology) obj;
		if (created == null) {
			if (other.created != null)
				return false;
		} else if (!created.equals(other.created))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (link == null) {
			if (other.link != null)
				return false;
		} else if (!link.equals(other.link))
			return false;
		if (skill == null) {
			if (other.skill != null)
				return false;
		} else if (!skill.equals(other.skill))
			return false;
		if (versionControlRepo == null) {
			if (other.versionControlRepo != null)
				return false;
		} else if (!versionControlRepo.equals(other.versionControlRepo))
			return false;
		return true;
	}
}
