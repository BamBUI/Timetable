package com.company.timetable.screen.ccuclasses;

import io.jmix.ui.screen.*;
import com.company.timetable.entity.CCUClasses;

@UiController("CCUClasses.edit")
@UiDescriptor("ccu-classes-edit.xml")
@EditedEntityContainer("cCUClassesDc")
public class CCUClassesEdit extends StandardEditor<CCUClasses> {
}