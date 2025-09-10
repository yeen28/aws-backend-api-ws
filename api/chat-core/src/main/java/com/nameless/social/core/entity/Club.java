package com.nameless.social.core.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Club extends BaseTimeEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(nullable = false)
	private String name;

	private String description;

	private String icon;

	@Setter
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "group_id")
	private Group group;

	@OneToMany(mappedBy = "club")
	private List<UserClub> participants = new ArrayList<>();

	@OneToMany(mappedBy = "club")
	private List<Quest> quest = new ArrayList<>();

	public Club(String name) {
		this.name = name;
	}

	public Club(long id, String name) {
		this.id = id;
		this.name = name;
	}
}
