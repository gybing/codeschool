package cn.fh.codeschool.model;
// Generated Apr 29, 2014 2:35:49 PM by Hibernate Tools 4.0.0

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;


/**
 * Course generated by hbm2java
 */
@NamedQuery(
		name = "Course.findAllCourses",
		query = "select c from Course c"
		)

@Entity
@Table(name = "course")
public class Course implements java.io.Serializable {

	private int id;
	private String courseName;
	private Integer chapterAmount;
	private Integer sectionAmount;
	private Integer finishedMemberAmount;
	private String courseCategory;
	private String courseDescription;

	private List<CourseChapter> courseChapters = new ArrayList<CourseChapter>();
	private List<AllBadges> allBadgeses = new ArrayList<AllBadges>();
	
	private String courseLanguage; // 以 ; 隔开

	public Course() {
		System.out.println("~~~~~~~~~~~~~Course constructed!");
	}

	public Course(int id) {
		this.id = id;
	}
	public Course(int id, String courseName, Integer chapterAmount,
			Integer sectionAmount, Integer finishedMemberAmount,
			String courseCategory, List<CourseChapter> courseChapters,
			List<AllBadges> allBadgeses) {
		this.id = id;
		this.courseName = courseName;
		this.chapterAmount = chapterAmount;
		this.sectionAmount = sectionAmount;
		this.finishedMemberAmount = finishedMemberAmount;
		this.courseCategory = courseCategory;
		this.courseChapters = courseChapters;
		this.allBadgeses = allBadgeses;
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

	@Column(name = "course_name", length = 64)
	@Size(max = 64)
	public String getCourseName() {
		return this.courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	@Column(name = "chapter_amount")
	public Integer getChapterAmount() {
		return this.chapterAmount;
	}

	public void setChapterAmount(Integer chapterAmount) {
		this.chapterAmount = chapterAmount;
	}

	@Column(name = "section_amount")
	public Integer getSectionAmount() {
		if (null == this.sectionAmount) {
			this.sectionAmount = 0;
		}
		
		return this.sectionAmount;
	}

	public void setSectionAmount(Integer sectionAmount) {
		this.sectionAmount = sectionAmount;
	}

	@Column(name = "finished_member_amount")
	public Integer getFinishedMemberAmount() {
		return this.finishedMemberAmount;
	}

	public void setFinishedMemberAmount(Integer finishedMemberAmount) {
		this.finishedMemberAmount = finishedMemberAmount;
	}

	@Column(name = "course_category", length = 64)
	@Size(max = 64)
	public String getCourseCategory() {
		return this.courseCategory;
	}

	public void setCourseCategory(String courseCategory) {
		this.courseCategory = courseCategory;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "course", cascade = CascadeType.REMOVE)
	public List<CourseChapter> getCourseChapters() {
		System.out.println("获取章节：" + this.courseChapters);
		
		return this.courseChapters;
	}

	public void setCourseChapters(List<CourseChapter> courseChapters) {
		this.courseChapters = courseChapters;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "course")
	public List<AllBadges> getAllBadgeses() {
		return this.allBadgeses;
	}

	public void setAllBadgeses(List<AllBadges> allBadgeses) {
		this.allBadgeses = allBadgeses;
	}

	@Column(name = "course_description")
	public String getCourseDescription() {
		return courseDescription;
	}

	/**
	 * @param courseDescription the courseDescription to set
	 */
	public void setCourseDescription(String courseDescription) {
		this.courseDescription = courseDescription;
	}

	@Column(name = "course_language")
	public String getCourseLanguage() {
		return courseLanguage;
	}

	public void setCourseLanguage(String courseLanguage) {
		this.courseLanguage = courseLanguage;
	}

}
