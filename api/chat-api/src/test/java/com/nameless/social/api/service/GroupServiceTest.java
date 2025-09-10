package com.nameless.social.api.service;

import com.nameless.social.api.model.GroupInfoModel;
import com.nameless.social.api.model.GroupModel;
import com.nameless.social.api.repository.*;
import com.nameless.social.api.repository.user.UserRepository;
import com.nameless.social.core.entity.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class GroupServiceTest {
	private GroupService groupService;

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private GroupRepository groupRepository;
	@Autowired
	private ClubRepository clubRepository;
	@Autowired
	private QuestRepository questRepository;
	@Autowired
	private UserGroupRepository userGroupRepository;
	@Autowired
	private UserQuestRepository userQuestRepository;

	private User user;
	private Group group;
	private Club club;
	private Quest quest;

	@BeforeEach
	void setUp() {
		groupService = new GroupService(userQuestRepository, userGroupRepository, questRepository, clubRepository, userRepository, groupRepository);

		user = userRepository.save(TestDataFactory.createUser("test token", "test", "test@test.com"));
		group = groupRepository.save(new Group("test Group", "[\"절약\", \"투자\", \"예산관리\", \"자동화\"]"));
		club = new Club("ClubA");
		club.setGroup(group);
		club = clubRepository.save(club);
		quest = questRepository.save(Quest.builder().name("test Quest").build());
		userGroupRepository.save(new UserGroup(user, group));
	}

	@Test
	@DisplayName("유저 이메일로 그룹 단 건 조회")
	void getGroupByUserEmailTest() {
		// given

		// when
		GroupModel result = groupService.getGroupByUserEmail(user);

		// then
		assertThat(result).isNotNull();
//		assertThat(result.getName()).isEqualTo(group.getName());
	}

	@Test
	@DisplayName("그룹 정보 조회")
	void getGroupInfoTest() {
		// given

		// when
		GroupInfoModel result = groupService.getGroupInfo(group.getName());

		// then
		assertThat(result.getName()).isEqualTo("test Group");
		assertThat(result.getGroupId()).isEqualTo(group.getId());
		assertThat(result.getClubList().get(0).getName()).isEqualTo("ClubA");
	}

	@Test
	@DisplayName("그룹 목록 조회")
	void getGroupListTest() {
		// given

		// when
		List<GroupInfoModel> result = groupService.getGroupList();

		// then
		assertThat(result).isNotNull();
		assertThat(result.get(0).getName()).isEqualTo("test Group");
		assertThat(result.get(0).getGroupId()).isEqualTo(group.getId());
		assertThat(result.get(0).getClubList().get(0).getName()).isEqualTo("ClubA");
	}
}
