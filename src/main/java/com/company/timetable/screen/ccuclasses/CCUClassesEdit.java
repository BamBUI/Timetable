package com.company.timetable.screen.ccuclasses;

import com.company.timetable.entity.Auditorium;
import com.company.timetable.entity.Group;
import com.company.timetable.entity.Teacher;
import io.jmix.core.FileStorageException;
import io.jmix.core.Messages;
import io.jmix.ui.Notifications;
import io.jmix.ui.RemoveOperation;
import io.jmix.ui.action.Action;
import io.jmix.ui.action.entitypicker.EntityLookupAction;
import io.jmix.ui.component.*;
import io.jmix.ui.model.CollectionPropertyContainer;
import io.jmix.ui.screen.*;
import com.company.timetable.entity.CCUClasses;
import org.springframework.beans.factory.annotation.Autowired;

import javax.inject.Named;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;


@UiController("CCUClasses.edit")
@UiDescriptor("ccu-classes-edit.xml")
@EditedEntityContainer("cCUClassesDc")
public class CCUClassesEdit extends StandardEditor<CCUClasses> {
    int inputCapacity = 0, auditoryCapacity;
    int countTeach = 0, countTeachDay = 0, countGroup = 0, countGroupDay = 0, countAuditory = 0, countAuditoryDay = 0;

    @Autowired
    private TextField<String> timeField;

    @Autowired
    private Messages messages;
    @Autowired
    private DateField<String> dayField;
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


    @Autowired
    private Button groupCheck;

    @Autowired
    private Button teacherCheck;

    @Autowired
    private Button auditoryCheck;

    @Autowired
    private MessageBundle messageBundle;

    @Subscribe("groupCheck")
    public void onGroupCheckClick(Button.ClickEvent event) {
        //Проверка совпадений для группы
        //Работает при нажатие на группу, нужно для того чтобы проверять конкретную группу

        List<CCUClasses> getGroup;
        List<String> getAudTime;
        List<String> getAudDate;
        getAudDate = Collections.singletonList(dayField.getValue());
        getAudTime = Collections.singletonList(timeField.getValue());
        getGroup = (groupDc.getItem().getSchedule());
        countGroup = 0;

        for (int i = 0 ; i < getGroup.size(); i++)
        {
            //Проверка группы по дню
            // Если у группы есть запись о дне, то продолжаем её проверять.
            // Проверка нужна, потому что 08:10 может быть хоть 1-го января, хоть 2-го.
            if (Objects.equals(getGroup.get(i).getDay() ,getAudDate.get(0))){
                //Проверка времени
                //Если группа имеет запись о времени - то выкидываем нотификацию.
                if(Objects.equals(getGroup.get(i).getTime(),getAudTime.get(0))) {
                    countGroup++;
                    if (countGroup > 1) {
                        String message1 = messages.getMessage("com.company.timetable.entity.ccuclasses/GroupTimeCase");
                        String message2 = messages.getMessage("com.company.timetable.entity.ccuclasses/GroupError");
                        notifications.create()
                                .withCaption(message2)
                                .withDescription(message1)
                                .withContentMode(ContentMode.TEXT)
                                .show();
                    }
                    else {
                        String message1 = messages.getMessage("com.company.timetable.entity.ccuclasses/AllGood");
                        String message2 = messages.getMessage("com.company.timetable.entity.ccuclasses/AllGoodCaption");
                        notifications.create()
                                .withCaption(message2)
                                .withDescription(message1)
                                .withContentMode(ContentMode.TEXT)
                                .show();
                    }
                }
                else{
                    String message1 = messages.getMessage("com.company.timetable.entity.ccuclasses/AllGood");
                    String message2 = messages.getMessage("com.company.timetable.entity.ccuclasses/GroupError");
                    notifications.create()
                            .withCaption(message2)
                            .withDescription(message1)
                            .withContentMode(ContentMode.TEXT)
                            .show();
                }
            }
            else{
                String message1 = messages.getMessage("com.company.timetable.entity.ccuclasses/AllGood");
                String message2 = messages.getMessage("com.company.timetable.entity.ccuclasses/GroupError");
                notifications.create()
                        .withCaption(message2)
                        .withDescription(message1)
                        .withContentMode(ContentMode.TEXT)
                        .show();
            }
        }
    }

    @Subscribe("teacherCheck")
    public void onTeacherCheckClick(Button.ClickEvent event) {

        List<CCUClasses> getTeacher;
        List<String> getAudTime;
        List<String> getAudDate;

        getTeacher = (teacherField.getValue().getClasses());
        getAudDate = Collections.singletonList(dayField.getValue());
        getAudTime = Collections.singletonList(timeField.getValue());
        //getAudName = Collections.singletonList(classesField.getValue());
        countTeach = 0;
        teacherField.getValue().getClasses().isEmpty();

        for (int i = 0; i < getTeacher.size();i++)
        {
            //Проверка преподавателя по дню
            // Если у преподавателя есть запись о дне, то продолжаем её проверять.
            // Проверка нужна, потому что 08:10 может быть хоть 1-го января, хоть 2-го.
            if(Objects.equals(getTeacher.get(i).getDay(), getAudDate.get(0)) )
            {
                //Проверка времени у преподавателя
                if(Objects.equals(getTeacher.get(i).getTime(), getAudTime.get(0))){
                    countTeach++;
                    if(countTeach > 1){
                        String message1 = messages.getMessage("com.company.timetable.entity.ccuclasses/TeacherTimeCase");
                        String message2 = messages.getMessage("com.company.timetable.entity.ccuclasses/TeacherError");
                        notifications.create()
                                .withCaption(message2)
                                .withDescription(message1)
                                .withContentMode(ContentMode.TEXT)
                                .show();
                    }
                    else {
                        String message1 = messages.getMessage("com.company.timetable.entity.ccuclasses/AllGood");
                        String message2 = messages.getMessage("com.company.timetable.entity.ccuclasses/AllGoodCaption");
                        notifications.create()
                                .withCaption(message2)
                                .withDescription(message1)
                                .withContentMode(ContentMode.TEXT)
                                .show();
                    }
                }
                else{
                    String message1 = messages.getMessage("com.company.timetable.entity.ccuclasses/AllGood");
                    String message2 = messages.getMessage("com.company.timetable.entity.ccuclasses/AllGoodCaption");
                    notifications.create()
                            .withCaption(message2)
                            .withDescription(message1)
                            .withContentMode(ContentMode.TEXT)
                            .show();
                }
            }
            else{
                String message1 = messages.getMessage("com.company.timetable.entity.ccuclasses/AllGood");
                String message2 = messages.getMessage("com.company.timetable.entity.ccuclasses/AllGoodCaption");
                notifications.create()
                        .withCaption(message2)
                        .withDescription(message1)
                        .withContentMode(ContentMode.TEXT)
                        .show();
            }
        }
    }

    @Subscribe("auditoryCheck")
    public void onAuditoryCheckClick(Button.ClickEvent event) {
        List<CCUClasses> getAuditory;
        List<String> getAudTime;
        List <String> getAudDate;

        getAuditory = (classesField.getValue().getClasses());
        getAudDate = Collections.singletonList((dayField.getValue()));
        getAudTime = Collections.singletonList(timeField.getValue());

        countAuditory = 0;
        for (int i = 0; i < getAuditory.size();i++){
            if(Objects.equals(getAuditory.get(i).getDay(), getAudDate.get(0)) ){
                if(Objects.equals(getAuditory.get(i).getTime(), getAudTime.get(0))){
                   countAuditory++;
                   if(countAuditory>1){
                       String message1 = messages.getMessage("com.company.timetable.entity.ccuclasses/AuditoryTimeCase");
                       String message2 = messages.getMessage("com.company.timetable.entity.ccuclasses/AuditoryError");
                       notifications.create()
                               .withCaption(message2)
                               .withDescription(message1)
                               .withContentMode(ContentMode.TEXT)
                               .show();
                   }
                   else {
                       String message1 = messages.getMessage("com.company.timetable.entity.ccuclasses/AllGood");
                       String message2 = messages.getMessage("com.company.timetable.entity.ccuclasses/AllGoodCaption");
                       notifications.create()
                               .withCaption(message2)
                               .withDescription(message1)
                               .withContentMode(ContentMode.TEXT)
                               .show();
                   }
                }
                else{
                    String message1 = messages.getMessage("com.company.timetable.entity.ccuclasses/AllGood");
                    String message2 = messages.getMessage("com.company.timetable.entity.ccuclasses/AllGoodCaption");
                    notifications.create()
                            .withCaption(message2)
                            .withDescription(message1)
                            .withContentMode(ContentMode.TEXT)
                            .show();
                }
            }
            else{
                String message1 = messages.getMessage("com.company.timetable.entity.ccuclasses/AllGood");
                String message2 = messages.getMessage("com.company.timetable.entity.ccuclasses/AllGoodCaption");
                notifications.create()
                        .withCaption(message2)
                        .withDescription(message1)
                        .withContentMode(ContentMode.TEXT)
                        .show();
            }
        }
    }



    @Install(to = "groupTable.add", subject = "afterCloseHandler")
    private void groupTableAddAfterCloseHandler(AfterCloseEvent afterCloseEvent) {
        //Проверка вместимости аудитории
        //Для корректной работы нужно удалить из таблицы все группы, а затем начать накидывать новые
        inputCapacity = groupDc.getItems().size();
        auditoryCapacity = Integer.parseInt((classesField.getValue().getCapacity()));
        if(inputCapacity <= auditoryCapacity){
            commitChanges().then(() -> {
                commitActionPerformed = true;});
        }
        else {
            String message1 = messages.getMessage("com.company.timetable.entity.ccuclasses/AuditoryExceedNotification");
            String message2 = messages.getMessage("com.company.timetable.entity.ccuclasses/AuditoryError");
            notifications.create()
            .withCaption(message2)
            .withDescription(message1)
            .withContentMode(ContentMode.TEXT)
            .show();
        }
    }



    @Install(to = "groupTable.exclude", subject = "afterActionPerformedHandler")
    private void groupTableExcludeAfterActionPerformedHandler(RemoveOperation.AfterActionPerformedEvent<Group> afterActionPerformedEvent) {
        inputCapacity = groupDc.getItems().size();
        inputCapacity--;
    }

}