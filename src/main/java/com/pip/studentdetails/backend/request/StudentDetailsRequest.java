package com.pip.studentdetails.backend.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;


public record StudentDetailsRequest (

        /* Unique identifier of the student and Required and cannot be blank.*/
        @NotBlank
        String studentId,

        /* Identifier for the subject for which marks are submitted. */
        @NotBlank
        String subjectId,

        /* Marks obtained by the student in the given subject and Must not be null.*/
        @NotNull
        Integer marks
) {}