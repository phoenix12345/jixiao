package com.mhyl.performance.appraisal;

import com.mhyl.performance.appraisal.utils.PwdUtils;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ApiApplicationTests {

    @Test
    void contextLoads() {
        System.out.println(PwdUtils.createSalt());
    }

}
