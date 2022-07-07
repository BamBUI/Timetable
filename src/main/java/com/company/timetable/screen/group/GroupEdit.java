package com.company.timetable.screen.group;

import io.jmix.ui.screen.*;
import com.company.timetable.entity.Group;

@UiController("Group_.edit")
@UiDescriptor("group-edit.xml")
@EditedEntityContainer("groupDc")
public class GroupEdit extends StandardEditor<Group> {
}