import com.pip.studentdetails.backend.StudentBackendApplication;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

@SpringBootTest(classes = StudentBackendApplication.class)
public class StudentBackendApplicationTest {

    @Test
    void contextLoads() {
        // verifies Spring container boots
    }

    // ✔ Safe test for main method
    @Test
    void mainMethodRunsWithoutException() {
        assertDoesNotThrow(() ->
                StudentBackendApplication.main(new String[]{})
        );
    }

}
