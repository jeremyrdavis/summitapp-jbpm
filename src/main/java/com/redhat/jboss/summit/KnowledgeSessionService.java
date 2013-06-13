package com.redhat.jboss.summit;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.drools.KnowledgeBase;
import org.drools.runtime.StatefulKnowledgeSession;
import org.drools.runtime.process.ProcessInstance;
import org.jbpm.task.Task;
import org.jbpm.task.TaskData;
import org.jbpm.task.TaskService;
import org.jbpm.task.query.TaskSummary;
import org.jbpm.task.service.local.LocalHumanTaskService;

public class KnowledgeSessionService {

	private KnowledgeBase knowledgeBase;
	private StatefulKnowledgeSession ksession;
	
	/**
	 * Local task service
	 */
	private TaskService taskSvc;
	
	public KnowledgeSessionService() {
		ksession = knowledgeBase.newStatefulKnowledgeSession();
		taskSvc = LocalHumanTaskService.getTaskService(ksession);
	}
	
	public Long createStaffingRequest(StaffingRequest staffingRequest){
		HashMap<String, Object> map = new HashMap();
		map.put("staffingRequest",staffingRequest);
		map.put("approver", staffingRequest.getApprovingManager());
		ProcessInstance processInstance = ksession.startProcess("", map);
		return processInstance.getId();
	}
	
	/**
	 * Returns all Tasks for the user
	 * @param userId
	 * @return
	 */
	public List getTasks(String userId){
		List<TaskSummary> tasks = taskSvc.getTasksAssignedAsPotentialOwner(userId, "en-US");
		return tasks;
		//TODO
		//create a return object to hold the processIds
	}
	
	
	public void completeTask(Long taskId, boolean approved){
			// store task id in db and get process from it
			Task task = taskSvc.getTask(taskId);
			TaskData data = task.getTaskData();
			Long workItemId = data.getWorkItemId();
			Map params = new HashMap<String, Boolean>();
			params.put("approval", approved);
			ksession.getWorkItemManager().completeWorkItem(workItemId, params);
	}
	
}