package com.pip.studentdetails.backend.request;

import javax.validation.constraints.NotBlank;

public record StudentRequest (

        /**
         * Unique identifier of the studentand  Must not be null, empty, or contain only spaces.
         */
        @NotBlank
        String studentId,

        @NotBlank
        String studentName,

        /**
         * Identifier of the department to which the student belongs and Must not be blank.
         */
        @NotBlank
        String departmentId,

        /**
         * Identifier of the section assigned to the student and Must not be blank.
         */
        @NotBlank
         String sectionId
) {}
