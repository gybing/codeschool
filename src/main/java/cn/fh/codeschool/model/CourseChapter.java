package cn.fh.codeschool.model;
// Generated Apr 29, 2014 2:35:49 PM by Hibernate Tools 4.0.0

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


/**
 * CourseChapter generated by hbm2java
 */

@NamedQuery(
		name = "CourseChapter.findCourseChapters",
		query = "select c from CourseChapter c where " +
				"c.course = :course"
		)

@Entity
@Table(name = "course_chapter", schema = "public")
public class CourseChapter implements java.io.Serializable {

	private int id;
	private Course course;
	private String chapterName;
	private String memo;
	private Integer finishedMemberAmount;
	//private Set<AllBadges> allBadgeses = new HashSet<AllBadges>(0);
	//private Set<CourseSection> courseSections = new HashSet<CourseSection>(0);
	private List<CourseSection> courseSections = new ArrayList<CourseSection>();
	private List<AllBadges> allBadgeses = new ArrayList<AllBadges>();

	public CourseChapter() {
		System.out.println("####### courseChapter constructed!");
	}

	public CourseChapter(int id, Course course) {
		this.id = id;
		this.course = course;
	}
	public CourseChapter(int id, Course course, String chapterName,
			String memo, Integer finishedMemberAmount,
			List<AllBadges> allBadgeses, List<CourseSection> courseSections) {
		this.id = id;
		this.course = course;
		this.chapterName = chapterName;
		this.memo = memo;
		this.finishedMemberAmount = finishedMemberAmount;
		this.allBadgeses = allBadgeses;
		this.courseSections = courseSections;
	}

	@Id
	@Column(name = "id", unique = true, nullable = false)
	@GeneratedValue
	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "course_id", unique = false, nullable = false)
	@NotNull
	public Course getCourse() {
		return this.course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

	@Column(name = "chapter_name", length = 64)
	@Size(max = 64)
	public String getChapterName() {
		return this.chapterName;
	}

	public void setChapterName(String chapterName) {
		this.chapterName = chapterName;
	}

	@Column(name = "memo")
	public String getMemo() {
		return this.memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	@Column(name = "finished_member_amount")
	public Integer getFinishedMemberAmount() {
		return this.finishedMemberAmount;
	}

	public void setFinishedMemberAmount(Integer finishedMemberAmount) {
		this.finishedMemberAmount = finishedMemberAmount;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "courseChapter")
	public List<AllBadges> getAllBadgeses() {
		return this.allBadgeses;
	}

	public void setAllBadgeses(List<AllBadges> allBadgeses) {
		this.allBadgeses = allBadgeses;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "courseChapter")
	public List<CourseSection> getCourseSections() {
		return this.courseSections;
	}

	public void setCourseSections(List<CourseSection> courseSections) {
		this.courseSections = courseSections;
	}

	public String toString() {
		return this.chapterName;
	}
}
