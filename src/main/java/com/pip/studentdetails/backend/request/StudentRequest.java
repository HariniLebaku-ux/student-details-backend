package com.pip.studentdetails.backend.request;

import javax.validation.constraints.NotBlank;

public record StudentRequest (

        /* Unique identifier of the studentId and  Must not be null, empty, or contain only spaces.*/
        @NotBlank
        String studentId,

        /* Identifier of the studentName and  Must not be null, empty, or contain only spaces.*/
        @NotBlank
        String studentName,

        /*Identifier of the departmentId from department to which the student belongs and Must not be blank.*/
        @NotBlank
        String departmentId,

        /*Identifier of the section assigned to the student and Must not be blank.*/
        @NotBlank
         String sectionId
) {}
