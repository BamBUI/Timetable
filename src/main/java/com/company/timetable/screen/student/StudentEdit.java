package com.company.timetable.screen.student;

import io.jmix.ui.screen.*;
import com.company.timetable.entity.Student;

@UiController("Student.edit")
@UiDescriptor("student-edit.xml")
@EditedEntityContainer("studentDc")
public class StudentEdit extends StandardEditor<Student> {
}