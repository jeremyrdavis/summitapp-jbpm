package com.redhat.jboss.summit;

import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

public class StaffingRequest {

	private String jobCode;
	
	/**
	 * Email of the approving manager
	 */
	private String approvingManager;

	private boolean approved;

	public StaffingRequest() {
	};

	public StaffingRequest(String jobCode) {
		this.jobCode = jobCode;
	}

	public StaffingRequest(String jobCode, boolean approved) {
		this.jobCode = jobCode;
		this.approved = approved;
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder(1450207409, -1692382659).append(jobCode)
				.append(approved).hashCode();
	};

	@Override
	public String toString() {
		return new ToStringBuilder(this).append("jobCode", jobCode)
				.append("approved", approved).toString();
	}

	// ----------------------------------------------------------------------------------------
	// generated getters and setters
	// ----------------------------------------------------------------------------------------
	public String getJobCode() {
		return jobCode;
	}

	public void setJobCode(String jobCode) {
		this.jobCode = jobCode;
	}

	public boolean isApproved() {
		return approved;
	}

	public void setApproved(boolean approved) {
		this.approved = approved;
	}

	public String getApprovingManager() {
		return approvingManager;
	}

	public void setApprovingManager(String approvingManager) {
		this.approvingManager = approvingManager;
	}

	
}
