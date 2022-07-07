package com.company.timetable.screen.teacher;

import io.jmix.ui.screen.*;
import com.company.timetable.entity.Teacher;

@UiController("Teacher.browse")
@UiDescriptor("teacher-browse.xml")
@LookupComponent("teachersTable")
public class TeacherBrowse extends StandardLookup<Teacher> {
}