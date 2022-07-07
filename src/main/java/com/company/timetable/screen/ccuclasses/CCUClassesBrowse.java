package com.company.timetable.screen.ccuclasses;

import io.jmix.ui.screen.*;
import com.company.timetable.entity.CCUClasses;

@UiController("CCUClasses.browse")
@UiDescriptor("ccu-classes-browse.xml")
@LookupComponent("cCUClassesesTable")
public class CCUClassesBrowse extends StandardLookup<CCUClasses> {
}