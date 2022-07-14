package com.company.timetable.screen.ccuclasses;

import com.company.timetable.entity.Auditorium;
import com.company.timetable.entity.Group;
import com.company.timetable.entity.Teacher;
import io.jmix.ui.Notifications;
import io.jmix.ui.RemoveOperation;
import io.jmix.ui.component.*;
import io.jmix.ui.model.CollectionPropertyContainer;
import io.jmix.ui.screen.*;
import com.company.timetable.entity.CCUClasses;
import org.springframework.beans.factory.annotation.Autowired;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;


@UiController("CCUClasses.edit")
@UiDescriptor("ccu-classes-edit.xml")
@EditedEntityContainer("cCUClassesDc")
public class CCUClassesEdit extends StandardEditor<CCUClasses> {
    int inputCapacity = 0;
    int auditoryCapacity = 0;

    int countTeach = 0, countGroup;

    @Autowired
    private TextField<String> timeField;

    @Autowired
    private Notifications notifications;

    @Autowired
    private EntityPicker<Teacher> teacherField;

    @Autowired
    private EntityPicker<Auditorium> classesField;

    @Autowired
    private Table<Group> groupTable;

    @Autowired
    private GroupBoxLayout groupBox;

    @Autowired
    private CollectionPropertyContainer<Group> groupDc;

    //Проверка есть ли такое время у преподавателя уже.
    @Subscribe("groupTable")
    public void onGroupTableSelection(Table.SelectionEvent<Group> event) {

        List<CCUClasses> getTeachTime;
        CCUClasses getGroupTime;
        List<String> getAudTime;

        getAudTime = Collections.singletonList(timeField.getValue());

        String var1 = null;

        teacherField.getValue().getClasses().isEmpty();
        getGroupTime = (groupDc.getItem().getSchedule());

        getTeachTime = (teacherField.getValue().getClasses());

        for (int i = 0; i < getTeachTime.size();i++){

            if( Objects.equals(getTeachTime.get(i).getTime(), getAudTime.get(0))){
                countTeach++;
                if(Objects.equals(getGroupTime.getTime(),getAudTime.get(0))){
                        notifications.create()
                                .withCaption("Совпадение у группы")
                                .withDescription("Поменяйте группу, у неё уже есть пара в это время")
                                .show();
                        disableCommitActions();
                }
                if(countTeach > 1){
                    notifications.create()
                            .withCaption("Совпадение у преподавателя")
                            .withDescription("Поменяйте преподавателя, у него уже есть пара в это время")
                            .show();
                    disableCommitActions();
                }
            }
        }
    }



    @Install(to = "groupTable.add", subject = "afterCloseHandler")
    private void groupTableAddAfterCloseHandler(AfterCloseEvent afterCloseEvent) {
        inputCapacity +=1;
        auditoryCapacity = Integer.parseInt((classesField.getValue().getCapacity()));
        //Получаем вместимость аудитории
        if(inputCapacity <= auditoryCapacity){
            commitChanges().then(() -> {
                commitActionPerformed = true;});
        }
        else {
            notifications.create()
                    .withCaption("Превышена вместимость аудитории.")
                    .show();
            disableCommitActions();
        }
    }



    @Install(to = "groupTable.exclude", subject = "afterActionPerformedHandler")
    private void groupTableExcludeAfterActionPerformedHandler(RemoveOperation.AfterActionPerformedEvent<Group> afterActionPerformedEvent) {
        inputCapacity+=1;
    }
}