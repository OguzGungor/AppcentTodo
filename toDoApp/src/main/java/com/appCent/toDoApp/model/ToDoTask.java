package com.appCent.toDoApp.model;

import com.appCent.toDoApp.util.Priority;
import com.appCent.toDoApp.util.Status;
import io.swagger.annotations.ApiModel;
import lombok.*;
import org.springframework.data.couchbase.core.mapping.Document;
import org.springframework.data.couchbase.core.mapping.Field;

import java.util.Date;


@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Setter(AccessLevel.PUBLIC)
@Getter(AccessLevel.PUBLIC)
@ApiModel(value = "Task" , description = "Template of task in todo application")
public class ToDoTask {

    private long id;

    @NonNull
    private String description;

    private Status status = Status.NOT_STARTED;

    private Priority priority = Priority.LOW;

    private Date deadline;
}
