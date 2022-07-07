package com.company.timetable.screen.group;

import io.jmix.ui.screen.*;
import com.company.timetable.entity.Group;

@UiController("Group_.browse")
@UiDescriptor("group-browse.xml")
@LookupComponent("groupsTable")
public class GroupBrowse extends StandardLookup<Group> {
}