package com.nameless.social.core.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "`group`")
public class Group extends BaseTimeEntity { // Category
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(nullable = false)
	private String name;

	private String description;
	private String icon;
	private String tag;

	@OneToMany(mappedBy = "group")
	private List<UserGroup> userGroups = new ArrayList<>();

	@OneToMany(mappedBy = "group")
	private List<Club> clubs = new ArrayList<>();

	public Group(String name, String tags) {
		this.name = name;
		this.tag = tags;
	}

	public Group(long id, String name, String tags) {
		this.id = id;
		this.name = name;
		this.tag = tags;
	}
}
