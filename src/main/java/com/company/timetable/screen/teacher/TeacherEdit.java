package com.company.timetable.screen.teacher;

import io.jmix.ui.screen.*;
import com.company.timetable.entity.Teacher;

@UiController("Teacher.edit")
@UiDescriptor("teacher-edit.xml")
@EditedEntityContainer("teacherDc")
public class TeacherEdit extends StandardEditor<Teacher> {
}