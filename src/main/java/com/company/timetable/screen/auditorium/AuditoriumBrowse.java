package com.company.timetable.screen.auditorium;

import io.jmix.ui.screen.*;
import com.company.timetable.entity.Auditorium;

@UiController("Auditorium.browse")
@UiDescriptor("auditorium-browse.xml")
@LookupComponent("auditoriumsTable")
public class AuditoriumBrowse extends StandardLookup<Auditorium> {
}