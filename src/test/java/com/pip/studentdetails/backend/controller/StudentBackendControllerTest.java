package com.pip.studentdetails.backend.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.pip.studentdetails.backend.request.StudentDetailsRequest;
import com.pip.studentdetails.backend.request.StudentRequest;
import com.pip.studentdetails.backend.service.StudentBackendService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.nio.file.Files;
import java.nio.file.Path;

import static org.hamcrest.Matchers.equalTo;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(StudentBackendController.class)
@ImportAutoConfiguration
class StudentBackendControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean(name="StudentBackendServiceImpl")
    private StudentBackendService studentBackendService;

    @Autowired
    private ObjectMapper mapper;

    @Test
    void callToBackend_success() throws Exception {

        Mockito.reset(studentBackendService);

        mockMvc.perform(post("/backend/call-to-backend")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.TEXT_PLAIN))
                .andExpect(content().string(equalTo("Hello Call from UI to backend works!")));
    }

    @Test
    void callToBackend_wrongMethod_returnsMethodNotAllowed() throws Exception {
        mockMvc.perform(get("/backend/call-to-backend"))
                .andExpect(status().isMethodNotAllowed());
    }

    @Test
    void createStudentSuccess() throws Exception {
        StudentRequest request = new StudentRequest("ST_001","John","DEPT_CS", "A");
        Mockito.doNothing()
                .when(studentBackendService)
                .createStudent(Mockito.any());

        mockMvc.perform(post("/backend/create-student")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(request)))
                .andExpect(status().isCreated());
    }

    @Test
    void createStudentValidationFailure() throws Exception {
        StudentRequest request = new StudentRequest(null, null,null, null);
        Mockito.doNothing()
                .when(studentBackendService)
                .createStudent(Mockito.any());

        mockMvc.perform(post("/backend/create-student")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(request)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void createStudentDataIntegrityViolationFailure() throws Exception {
        StudentRequest request = new StudentRequest("ST_001", "John","DEPT_CS", "A");
        Mockito.doThrow(new DataIntegrityViolationException("Primarykey Violation - ST_001 is already available"))
                .when(studentBackendService)
                .createStudent(Mockito.any());

        mockMvc.perform(post("/backend/create-student")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(request)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void createStudentInternalServerErrorFailure() throws Exception {
        StudentRequest request = new StudentRequest("ST_002", "John","DEPT_CS", "A");
        Mockito.doThrow(new RuntimeException("Exception occurred"))
                .when(studentBackendService)
                .createStudent(Mockito.any());

        mockMvc.perform(post("/backend/create-student")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(request)))
                .andExpect(status().isInternalServerError());
    }

    @Test
    void createStudentDetailsSuccess() throws Exception {
        StudentDetailsRequest request = new StudentDetailsRequest("ST_001", "SUB_001", "John",100);
        Mockito.doNothing()
                .when(studentBackendService)
                .createStudentDetails(Mockito.any());

        mockMvc.perform(post("/backend/create-studentdetails")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(request)))
                .andExpect(status().isCreated());
    }

    @Test
    void createStudentDetailsValidationFailure() throws Exception {
        StudentDetailsRequest request = new StudentDetailsRequest(null, null, null,null);
        Mockito.doNothing()
                .when(studentBackendService)
                .createStudentDetails(Mockito.any());

        mockMvc.perform(post("/backend/create-studentdetails")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(request)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void createStudentDetailsDataIntegrityViolationFailure() throws Exception {
        StudentDetailsRequest request = new StudentDetailsRequest("ST_001", "SUB_001", "John",100);
        Mockito.doThrow(new DataIntegrityViolationException("Primarykey Violation - ST_001, SUB_001 is already available"))
                .when(studentBackendService)
                .createStudentDetails(Mockito.any());

        mockMvc.perform(post("/backend/create-studentdetails")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(request)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void createStudentDetailsInternalServerErrorFailure() throws Exception {
        StudentDetailsRequest request = new StudentDetailsRequest("ST_001", "SUB_001", "John",100);
        Mockito.doThrow(new RuntimeException("Exception Occurred"))
                .when(studentBackendService)
                .createStudentDetails(Mockito.any());

        mockMvc.perform(post("/backend/create-studentdetails")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(request)))
                .andExpect(status().isInternalServerError());
    }

    @Test
    void getDepartmentTopperDetailsSuccess() throws Exception {

        String departmentId = "DEPT_IT";

        // Load JSON file from resources
        String mockResponse = Files.readString(Path.of("src/test/resources/mock/DepartmentWiseTopperDetails.json"));
        Mockito.when(studentBackendService.getDepartmentTopperDetails(departmentId))
                .thenReturn(mockResponse);

        mockMvc.perform(post("/backend/department-topper/{departmentId}", departmentId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }


    @Test
    void getDepartmentTopperDetailsFailure() throws Exception {

        String departmentId="1234567";
        mockMvc.perform(post("/backend/department-topper/{departmentId}", departmentId))
                .andExpect(status().isBadRequest());
    }

    @Test
    void getDepartmentTopperDetailsException() throws Exception {

        String departmentId="DEPT_IT";
        Mockito.doThrow(new RuntimeException("Exception Occurred"))
                .when(studentBackendService)
                .getDepartmentTopperDetails(Mockito.any());
        mockMvc.perform(post("/backend/department-topper/{departmentId}", departmentId))
                .andExpect(status().isInternalServerError());
    }

    @Test
    void getDepartmentWiseRankingDetailsSuccess() throws Exception {

        String departmentId="DEPT_IT";
        String mockResponse = Files.readString(Path.of("src/test/resources/mock/DepartmentWiseRankingDetails.json"));

        Mockito.when(studentBackendService.getDepartmentWiseRankingDetails(departmentId)).thenReturn(mockResponse);

        mockMvc.perform(post("/backend/departmentWise-ranking/{departmentId}", departmentId)
                        .contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
    }


    @Test
    void getDepartmentWiseRankingDetailsFailure() throws Exception {

        String departmentId="1234567";
        mockMvc.perform(post("/backend/departmentWise-ranking/{departmentId}", departmentId))
                .andExpect(status().isBadRequest());
    }

    @Test
    void getDepartmentWiseRankingDetailsException() throws Exception {

        String departmentId="DEPT_IT";
        Mockito.doThrow(new RuntimeException("Exception Occurred"))
                .when(studentBackendService)
                .getDepartmentWiseRankingDetails(Mockito.any());
        mockMvc.perform(post("/backend/departmentWise-ranking/{departmentId}", departmentId))
                .andExpect(status().isInternalServerError());
    }

    @Test
    void getDepartmentWisePassPercentageDetailsSuccess() throws Exception {

        String mockResponse = Files.readString(
                Path.of("src/test/resources/mock/DepartmentWisePassPercentageDetails.json")
        );

        Mockito.when(studentBackendService.getDepartmentWisePassPercentageDetails())
                .thenReturn(mockResponse);

        mockMvc.perform(post("/backend/departmentWise-passPecentage")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void getDepartmentWisePassPercentageDetailsException() throws Exception {

        Mockito.doThrow(new RuntimeException("Exception Occurred"))
                .when(studentBackendService)
                .getDepartmentWisePassPercentageDetails();
        mockMvc.perform(post("/backend/departmentWise-passPecentage"))
                .andExpect(status().isInternalServerError());
    }


}