package com.gravypod.AllAdmin.permissions;

import java.util.HashMap;


public class PermissionData {
	
	private static Group defaultGroup;
	private static HashMap<String, Group> groups;
	
	public static Group getDefaultGroup() {
    
	    return defaultGroup;
    }
	
	public static void setDefaultGroup(Group defaultGroup) {
	    PermissionData.defaultGroup = defaultGroup;
    }
	
	public static HashMap<String, Group> getGroups() {
	    return groups;
    }
	
	public static void setGroups(HashMap<String, Group> groups) {
	    PermissionData.groups = groups;
    }
	
}
