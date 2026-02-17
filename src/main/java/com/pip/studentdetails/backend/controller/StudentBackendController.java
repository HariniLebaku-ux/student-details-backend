
package com.pip.studentdetails.backend.controller;

import com.pip.studentdetails.backend.request.StudentDetailsRequest;
import com.pip.studentdetails.backend.request.StudentRequest;
import com.pip.studentdetails.backend.response.ApiResponse;
import com.pip.studentdetails.backend.service.StudentBackendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

/** REST Controller responsible for exposing backend APIs related to Student and StudentDetails **/
@RestController
@Validated
@RequestMapping("/backend")
public class StudentBackendController {

    /**
     * This service provides operations related to student ranking and topper
     * calculation for department-wise academic reports
     */
    @Autowired
    @Qualifier("StudentBackendServiceImpl")
    private StudentBackendService studentBackendService;

    /**POST /backend/call-to-backend - call backend student-detail-backend api */
    @PostMapping("/call-to-backend")
    public String testCallFromUI() {
        return "Hello Call from UI to backend works!";
    }

    /**
     * Creates a new student record in the database student backend
     * @param studentRequest Request DTO containing studentId, departmentId and sectionId.
     * @return ResponseEntity with ApiResponse and CREATED status.
     */
    @PostMapping("/create-student")
    public ResponseEntity<ApiResponse<Void>> createStudent(@Valid @RequestBody StudentRequest studentRequest) {
        studentBackendService.createStudent(studentRequest);
        ApiResponse<Void> response = new ApiResponse<>(
            HttpStatus.CREATED.value(), "Student Inserted Sucessfully"
        );
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    /**
     * Insert the student-details data to DB using below Post api from backend
     */
    @PostMapping("/create-studentdetails")
    public ResponseEntity<ApiResponse<Void>> createStudentDetails(@Valid @RequestBody StudentDetailsRequest studentDetailsRequest) {
        studentBackendService.createStudentDetails(studentDetailsRequest);
        ApiResponse<Void> response = new ApiResponse<>(
            HttpStatus.CREATED.value(), "Student Details Inserted Sucessfully"
        );
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    /**
     * fetch the details backend api to retrieve department wise Topper based on departmentId
     */
    @PostMapping("/department-topper/{departmentId}")
    public String getDepartmentTopperDetails(@PathVariable @NotBlank @Pattern(regexp = "[A-Z_]{3,10}$") String departmentId){
        return studentBackendService.getDepartmentTopperDetails(departmentId);
    }

    /**
     * fetch the details backend api to retrieve department wise ranking based on departmentId
     */
    @PostMapping("/departmentWise-ranking/{departmentId}")
    public String getDepartmentWiseRankingDetails(@PathVariable @NotBlank @Pattern(regexp = "[A-Z_]{3,10}$") String departmentId) {
        return studentBackendService.getDepartmentWiseRankingDetails(departmentId);
    }

    /**
     * fetch the details backend api to retrieve department wise pass percentage
     */
    @PostMapping("/departmentWise-passPecentage")
    public String getDepartmentWisePassPercentageDetails() {
         return studentBackendService.getDepartmentWisePassPercentageDetails();
    }
}
