package com.pip.studentdetails.backend.entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

/**
* The STUDENT_DETAILS table uses a composite primary key:(student_id, subject_id)
 */
@Embeddable
public class StudentDetailsId implements Serializable {

    /**
     * studentId is part of the composite key.
     */
    @Column(name="student_id")
     private String studentId; // STUDENT_ID (FK to STUDENT)

    /**
     * subjectId is part of the composite key.
     */
    @Column(name="subject_id")
     private String subjectId; // SUBJECT_ID (FK to SUBJECT)

    /**
     * Default constructor required by JPA. JPA uses reflection to create objects.
     */
    public StudentDetailsId() {}


    /**
     * Convenience constructor for easy creation of the composite key.
     * @param studentId student identifier
     * @param subjectId subject identifier
     */
    public StudentDetailsId(String studentId, String subjectId) {
         this.studentId = studentId;
         this.subjectId = subjectId;
     }
    // -------------------- Getters and Setters --------------------
     public String getStudentId() { return studentId; }
     public void setStudentId(String studentId) { this.studentId = studentId; }

     public String getSubjectId() { return subjectId; }
     public void setSubjectId(String subjectId) { this.subjectId = subjectId; }

    /**
     * Two StudentDetailsId objects are considered equal if both:
     * studentId matches AND subjectId matches
     */
    @Override
     public boolean equals(Object obj) {
         if (this == obj) return true;      // Same object reference
         if (!(obj instanceof StudentDetailsId)) return false;
         StudentDetailsId that = (StudentDetailsId) obj;

        // Compare key fields
         return Objects.equals(studentId, that.studentId) &&
                 Objects.equals(subjectId, that.subjectId);
     }

    /**
     * hashCode() must be consistent with equals().
     * It is required for correct behavior in HashSet/HashMap and Hibernate caching.
     */
     @Override
     public int hashCode() {
         return Objects.hash(studentId, subjectId);
     }
 }
