package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the tasks database table.
 * 
 */
@Entity
@Table(name="tasks")
@NamedQuery(name="Task.findAll", query="SELECT t FROM Task t")
public class Task implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="task_id")
	private int taskId;

	@Column(name="task_description")
	private String taskDescription;

	@Temporal(TemporalType.DATE)
	@Column(name="task_due_date")
	private Date taskDueDate;

	@Column(name="task_name")
	private String taskName;

	@Temporal(TemporalType.DATE)
	@Column(name="task_start_date")
	private Date taskStartDate;

	@Column(name="task_status")
	private byte taskStatus;

	@Column(name="task_user_id")
	private int taskUserId;

	public Task() {
	}

	public int getTaskId() {
		return this.taskId;
	}

	public void setTaskId(int taskId) {
		this.taskId = taskId;
	}

	public String getTaskDescription() {
		return this.taskDescription;
	}

	public void setTaskDescription(String taskDescription) {
		this.taskDescription = taskDescription;
	}

	public Date getTaskDueDate() {
		return this.taskDueDate;
	}

	public void setTaskDueDate(Date taskDueDate) {
		this.taskDueDate = taskDueDate;
	}

	public String getTaskName() {
		return this.taskName;
	}

	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}

	public Date getTaskStartDate() {
		return this.taskStartDate;
	}

	public void setTaskStartDate(Date taskStartDate) {
		this.taskStartDate = taskStartDate;
	}

	public byte getTaskStatus() {
		return this.taskStatus;
	}

	public void setTaskStatus(byte taskStatus) {
		this.taskStatus = taskStatus;
	}

	public int getTaskUserId() {
		return this.taskUserId;
	}

	public void setTaskUserId(int taskUserId) {
		this.taskUserId = taskUserId;
	}

}