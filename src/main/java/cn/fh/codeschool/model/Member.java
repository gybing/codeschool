package cn.fh.codeschool.model;
// Generated Apr 29, 2014 2:35:49 PM by Hibernate Tools 4.0.0

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


/**
 * Member generated by hbm2java
 */

@NamedQuery(
		name = "Member.findMember",
		query = "select m from Member m " +
				"where m.username=:username"
		)

@Entity
@Table(name = "member", schema = "public")
public class Member implements java.io.Serializable {

	private int id;
	private String username;
	private String nickName;
	private String password;
	private Date birthday;
	private Integer gender;
	private String qqNumber;
	private String emailAddress;
	private String phoneNumber;
	private String bio;
	private String occupation;
	private Integer coursesFinishedAmount;
	private Integer point;
	private Integer maxConsecution;
	private Long rank;
	
	// 注册时间
	private Date registerDate;
	
	// 主页被赞的次数
	private Integer thumbAmount; 
	
	// 朋友
	private Member parent;
	private List<Member> friendList = new ArrayList<Member>();
	
	// 发表的帖子
	private List<Post> postList = new ArrayList<Post>();
	private List<Postback> postbackList = new ArrayList<Postback>();
	
	// 最近活动
	private List<RecentActivity> recentActivity = new ArrayList<RecentActivity>();
	
	private String startedCourseIds; // 已经开始学习的课程id, 以 ';'分隔
	private String finishedSectionIds; // 保存用户通过的小节的id,以 ';'分隔
	private List<Integer> finishedSectionIdList = new ArrayList<Integer>(); // 对 finishedSectionIds进行封装
	private List<Integer> startedCourseIdList = new ArrayList<Integer>(); // 对 startedCourseIds进行封装
	
	private byte[] avatar;
	
	private Set<Role> roles = new HashSet<Role>(0);
	private Set<MemberAcquiredBadges> memberAcquiredBadgeses = new HashSet<MemberAcquiredBadges>(0);
	
	//private Set<CourseSection> finishedSections = new HashSet<CourseSection>(0);

	public Member() {
		// 初始化 this.finishedSectionIdList成员
		
	}

	public Member(int id, String username) {
		this.id = id;
		this.username = username;
	}
	public Member(int id, String username, String nickName, String password,
			Date birthday, Integer gender, String qqNumber,
			String emailAddress, String phoneNumber, String bio,
			String occupation, Integer coursesFinishedAmount, Integer point,
			Integer maxConsecution, Long rank, Set<Role> memberRoles,
			Set<MemberAcquiredBadges> memberAcquiredBadgeses) {
		this.id = id;
		this.username = username;
		this.nickName = nickName;
		this.password = password;
		this.birthday = birthday;
		this.gender = gender;
		this.qqNumber = qqNumber;
		this.emailAddress = emailAddress;
		this.phoneNumber = phoneNumber;
		this.bio = bio;
		this.occupation = occupation;
		this.coursesFinishedAmount = coursesFinishedAmount;
		this.point = point;
		this.maxConsecution = maxConsecution;
		this.rank = rank;
		this.roles = memberRoles;
		this.memberAcquiredBadgeses = memberAcquiredBadgeses;
	}
	
	/**
	 * 赞的数量+1
	 * @return
	 */
	@Transient
	public Integer thumbUp() {
		this.thumbAmount += 1;
		
		return this.thumbAmount;
	}
	
	/**
	 * 判断用户是否有username这个朋友
	 * @param username
	 * @return
	 */
	@Transient
	public boolean hasFriend(String username) {
		for (Member m : friendList) {
			System.out.println("用户好友:" + m.getUsername() + ", 对比好友:" + username);
			if (username.equals(m.getUsername())) {
				return true;
			}
		}
		
		return false;
	}
	
	/**
	 * 返回用户已经完成的小节数
	 * @return
	 */
	@Transient
	public int finishedSectionAmount() {
		return this.finishedSectionIds.split(";").length;
	}
	
	/**
	 * 判断当前用户是否有指定的role
	 * @param roleName
	 * @return
	 */
	@Transient
	public boolean hasRole(String roleName) {
		for (Role r : this.getRoles()) {
			if (r.getRoleName().equals(roleName)) {
				return true;
			}
		}
		
		return false;
	}
	
	/**
	 * 检测this.startedCourseIds中是否包含指定id
	 * @param id
	 * @return
	 */
	@Transient
	public boolean includeCourseId(Integer id) {
		if (null == this.startedCourseIds) {
			return false;
		}

		String[] ids = this.startedCourseIds.split(";");
		for (String sId : ids) {
			if (sId.equals(id.toString())) {
				return true;
			}
		}
		
		return false;
	}
	
	/**
	 * 添加一个courseId
	 * @param id
	 */
	@Transient
	public void addCourseId(Integer id) {
		if (null == this.startedCourseIds) {
			this.startedCourseIds = "";
		}
		
		this.startedCourseIds += id.toString() + ";";
	}
	
	/**
	 * 添加一个sectionId
	 * @param id
	 */
	@Transient
	public void addSectionId(Integer id) {
		if (null == this.finishedSectionIds) {
			this.finishedSectionIds = "";
		}

		this.finishedSectionIds += id.toString() + ';';
	}
	
	/**
	 * 增加1点分值
	 */
	@Transient
	public void increasePoint() {
		this.setPoint(this.getPoint() + 1);
	}

/*	@OneToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "member_section",
		joinColumns = @JoinColumn(name = "member_id"),
		inverseJoinColumns = @JoinColumn(name = "section_id")
			)
	public Set<CourseSection> getFinishedSections() {
		return finishedSections;
	}*/

/*	public void setFinishedSections(Set<CourseSection> finishedSections) {
		this.finishedSections = finishedSections;
	}*/

	@Id
	@GeneratedValue
	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Column(name = "username", nullable = false, length = 64)
	@NotNull
	@Size(max = 64)
	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Column(name = "nick_name", length = 64)
	@Size(max = 64)
	public String getNickName() {
		return this.nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	@Column(name = "password", length = 64)
	@Size(max = 64)
	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "birthday", length = 13)
	public Date getBirthday() {
		return this.birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	@Column(name = "gender")
	public Integer getGender() {
		return this.gender;
	}

	public void setGender(Integer gender) {
		this.gender = gender;
	}

	@Column(name = "qq_number", length = 30)
	@Size(max = 30)
	public String getQqNumber() {
		return this.qqNumber;
	}

	public void setQqNumber(String qqNumber) {
		this.qqNumber = qqNumber;
	}

	@Column(name = "email_address", length = 32)
	@Size(max = 32)
	public String getEmailAddress() {
		return this.emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	@Column(name = "phone_number", length = 20)
	@Size(max = 20)
	public String getPhoneNumber() {
		return this.phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	@Column(name = "bio")
	public String getBio() {
		return this.bio;
	}

	public void setBio(String bio) {
		this.bio = bio;
	}

	@Column(name = "occupation", length = 128)
	@Size(max = 128)
	public String getOccupation() {
		return this.occupation;
	}

	public void setOccupation(String occupation) {
		this.occupation = occupation;
	}

	@Column(name = "courses_finished_amount")
	public Integer getCoursesFinishedAmount() {
		return this.coursesFinishedAmount;
	}

	public void setCoursesFinishedAmount(Integer coursesFinishedAmount) {
		this.coursesFinishedAmount = coursesFinishedAmount;
	}

	@Column(name = "point")
	public Integer getPoint() {
		// 如果point为空则置为0
		this.point = this.point == null ? 0 : this.point;
		return this.point;
	}

	public void setPoint(Integer point) {
		this.point = point;
	}

	@Column(name = "max_consecution")
	public Integer getMaxConsecution() {
		return this.maxConsecution;
	}

	public void setMaxConsecution(Integer maxConsecution) {
		this.maxConsecution = maxConsecution;
	}

	@Column(name = "rank")
	public Long getRank() {
		return this.rank;
	}

	public void setRank(Long rank) {
		this.rank = rank;
	}

/*	@OneToMany(fetch = FetchType.EAGER, mappedBy = "member")
	public Set<MemberRole> getMemberRoles() {
		return this.memberRoles;
	}*/

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "member_role",
			joinColumns = @JoinColumn(name = "member_id"),
			inverseJoinColumns = @JoinColumn(name = "role_id")
		)
	public Set<Role> getRoles() {
		return this.roles;
	}

	public void setRoles(Set<Role> memberRoles) {
		this.roles = memberRoles;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "member")
	public Set<MemberAcquiredBadges> getMemberAcquiredBadgeses() {
		return this.memberAcquiredBadgeses;
	}

	public void setMemberAcquiredBadgeses(
			Set<MemberAcquiredBadges> memberAcquiredBadgeses) {
		this.memberAcquiredBadgeses = memberAcquiredBadgeses;
	}

	@Column(name = "avatar_image")
	public byte[] getAvatar() {
		return avatar;
	}

	public void setAvatar(byte[] avatar) {
		this.avatar = avatar;
	}

	@Column(name = "finished_section_ids", columnDefinition="TEXT")
	public String getFinishedSectionIds() {
		if (null == this.finishedSectionIds) {
			this.finishedSectionIds = "";
		}
		
		return finishedSectionIds;
	}

	public void setFinishedSectionIds(String finishedSectionIds) {
		this.finishedSectionIds = finishedSectionIds;
	}

	@Column(name = "started_course_ids", columnDefinition="TEXT")
	public String getStartedCourseIds() {
		if (null == this.startedCourseIds) {
			this.startedCourseIds = "";
		}
		return startedCourseIds;
	}

	public void setStartedCourseIds(String startedCourseIds) {
		this.startedCourseIds = startedCourseIds;
	}

	@Transient
	public List<Integer> getFinishedSectionIdList() {
		// 如果List还没有值,则创建List
		if (false == this.getFinishedSectionIds().equals("") && 0 == this.finishedSectionIdList.size()) {
			String[] ids = this.finishedSectionIds.split(";");
			
			for (String id : ids) {
				this.finishedSectionIdList.add(Integer.valueOf(id));
			}
		}

		return finishedSectionIdList;
	}

	public void setFinishedSectionIdList(List<Integer> finishedSectionIdList) {
		this.finishedSectionIdList = finishedSectionIdList;
	}

	@Transient
	public List<Integer> getStartedCourseIdList() {
		// 如果List还没有值,则创建List
		if (false == this.getStartedCourseIds().equals("") && 0 == this.startedCourseIdList.size()) {
			String[] ids = this.getStartedCourseIds().split(";");
			
			for (String id : ids) {
				this.startedCourseIdList.add(Integer.valueOf(id));
			}
		}
			
		return startedCourseIdList;
	}

	public void setStartedCourseIdList(List<Integer> startedCourseIdList) {
		this.startedCourseIdList = startedCourseIdList;
	}

	@ManyToOne
	public Member getParent() {
		return parent;
	}

	public void setParent(Member parent) {
		this.parent = parent;
	}

	@OneToMany(mappedBy = "parent", fetch = FetchType.EAGER)
	public List<Member> getFriendList() {
		return friendList;
	}

	public void setFriendList(List<Member> friendList) {
		this.friendList = friendList;
	}

	@OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
	public List<RecentActivity> getRecentActivity() {
		return recentActivity;
	}

	public void setRecentActivity(List<RecentActivity> recentActivity) {
		this.recentActivity = recentActivity;
	}

	@Column(name = "thumb_amount")
	public Integer getThumbAmount() {
		if (null == this.thumbAmount) {
			this.thumbAmount = 0;
		}
		
		return thumbAmount;
	}

	public void setThumbAmount(Integer thumbAmount) {
		this.thumbAmount = thumbAmount;
	}

	@Column(name = "register_date")
	public Date getRegisterDate() {
		return registerDate;
	}

	public void setRegisterDate(Date registerDate) {
		this.registerDate = registerDate;
	}

	@OneToMany(mappedBy = "author")
	public List<Post> getPostList() {
		return postList;
	}

	public void setPostList(List<Post> postList) {
		this.postList = postList;
	}

	@OneToMany(mappedBy = "author")
	public List<Postback> getPostbackList() {
		return postbackList;
	}

	public void setPostbackList(List<Postback> postbackList) {
		this.postbackList = postbackList;
	}


}
