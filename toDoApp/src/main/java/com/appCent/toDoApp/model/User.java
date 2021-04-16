package com.appCent.toDoApp.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.couchbase.core.index.CompositeQueryIndex;
import org.springframework.data.couchbase.core.index.QueryIndexed;
import org.springframework.data.couchbase.core.mapping.Document;
import org.springframework.data.couchbase.core.mapping.Field;
import org.springframework.data.couchbase.core.mapping.id.GeneratedValue;
import org.springframework.data.couchbase.core.mapping.id.GenerationStrategy;


import java.util.List;



@Document
@Data
@CompositeQueryIndex(fields = {"id","name"})
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Setter(AccessLevel.PUBLIC)
@Getter(AccessLevel.PUBLIC)
@ApiModel(value = "User", description = "User Api model for documentation")
public class User {

    @NonNull
    @Id
    @GeneratedValue(strategy = GenerationStrategy.UNIQUE)
    @ApiModelProperty(value="user id in uuid format")
    private String id;

    @NonNull
    @Field
    @QueryIndexed
    private String name;

    @NonNull
    @Field
    private String password;

    @Field
    @QueryIndexed
    private String email;

    @Field
    @NonNull
    @ApiModelProperty(value = "list of tasks that user created")
    List<ToDoTask> tasks;


}
