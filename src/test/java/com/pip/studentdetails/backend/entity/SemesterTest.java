package com.pip.studentdetails.backend.entity;

import org.junit.jupiter.api.Test;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Fast unit tests for the Semester JPA entity (no persistence involved).
 */
class SemesterTest {

    @Test
    void semesterDefaultConstructor_success() {
        Semester semester = new Semester();

        semester.setSemesterId("SEM_1");
        semester.setSemesterName("Semester 1");

        // subjects should be initialized to empty LinkedHashSet by default per the entity
        assertThat(semester.getSemesterId()).isEqualTo("SEM_1");
        assertThat(semester.getSemesterName()).isEqualTo("Semester 1");
        assertThat(semester.getSubjects())
                .isNotNull()
                .isEmpty();
        assertThat(semester.getSubjects()).isInstanceOf(LinkedHashSet.class);
    }

    @Test
    void semesterConvenienceConstructor_success() {
        Semester semester = new Semester();
        semester.setSemesterId("SEM_1");
        semester.setSemesterName("Semester 1");

        Subject sub1 = new Subject();
        sub1.setSubjectId("SUB_001");
        sub1.setSubjectName("Mathematics");
        sub1.setSemester(semester); // back-reference if your Subject has it

        Subject sub2 = new Subject();
        sub2.setSubjectId("SUB_002");
        sub2.setSubjectName("Physics");
        sub2.setSemester(semester);

        Set<Subject> subjects = new LinkedHashSet<>(List.of(sub1, sub2));

        Semester withAll = new Semester("SEM_1", "Semester 1", subjects);

        assertThat(withAll.getSemesterId()).isEqualTo("SEM_1");
        assertThat(withAll.getSemesterName()).isEqualTo("Semester 1");
        assertThat(withAll.getSubjects())
                .hasSize(2)
                .containsExactly(sub1, sub2); // preserves insertion order because LinkedHashSet
    }

    @Test
    void subjectsCollection_shouldPreserveInsertionOrder() {
        Semester sem = new Semester();
        sem.setSemesterId("SEM_2");
        sem.setSemesterName("Semester 2");

        Subject s1 = new Subject();
        s1.setSubjectId("SUB_101");
        s1.setSubjectName("Chemistry");
        s1.setSemester(sem);

        Subject s2 = new Subject();
        s2.setSubjectId("SUB_102");
        s2.setSubjectName("Biology");
        s2.setSemester(sem);

        Subject s3 = new Subject();
        s3.setSubjectId("SUB_103");
        s3.setSubjectName("English");
        s3.setSemester(sem);

        LinkedHashSet<Subject> order = new LinkedHashSet<>();
        order.add(s1);
        order.add(s2);
        order.add(s3);

        sem.setSubjects(order);

        assertThat(sem.getSubjects())
                .containsExactly(s1, s2, s3); // verify insertion order
    }

    @Test
    void modifySubjects_addAndRemove_shouldReflectInCollection() {
        Semester semester = new Semester();
        semester.setSemesterId("SEM_3");
        semester.setSemesterName("Semester 3");

        Subject subject1 = new Subject();
        subject1.setSubjectId("SUB_201");
        subject1.setSubjectName("Economics");
        subject1.setSemester(semester);

        Subject subject2 = new Subject();
        subject2.setSubjectId("SUB_202");
        subject2.setSubjectName("Statistics");
        subject2.setSemester(semester);

        semester.setSubjects(new LinkedHashSet<>());
        semester.getSubjects().add(subject1);
        semester.getSubjects().add(subject2);

        assertThat(semester.getSubjects()).hasSize(2).containsExactly(subject1, subject2);

        // remove one
        semester.getSubjects().remove(subject1);
        assertThat(semester.getSubjects()).hasSize(1).containsExactly(subject2);
    }

    @Test
    void nullableAtLevel_semesterNameCanBeNullInPojo() {
        // Note: @Column(nullable = false) is a DB constraint, but can still hold null in unit tests.
        Semester sem = new Semester();
        sem.setSemesterId("SEM_4");
        sem.setSemesterName(null);

        assertThat(sem.getSemesterId()).isEqualTo("SEM_4");
        assertThat(sem.getSemesterName()).isNull();
    }

    @Test
    void backReferences_subjectsShouldPointToSameSemester_whenManuallyLinked() {
        Semester semester = new Semester();
        semester.setSemesterId("SEM_5");
        semester.setSemesterName("Semester 5");

        Subject subject1 = new Subject();
        subject1.setSubjectId("SUB_301");
        subject1.setSubjectName("History");
        subject1.setSemester(semester);

        Subject subject2 = new Subject();
        subject2.setSubjectId("SUB_302");
        subject2.setSubjectName("Geography");
        subject2.setSemester(semester);

        LinkedHashSet<Subject> set = new LinkedHashSet<>();
        set.add(subject1);
        set.add(subject2);
        semester.setSubjects(set);

        // Validate the back-reference (only works if Subject has getSemester())
        assertThat(semester.getSubjects())
                .allSatisfy(sub -> {
                    assertThat(sub.getSemester()).isSameAs(semester);
                });
    }
}