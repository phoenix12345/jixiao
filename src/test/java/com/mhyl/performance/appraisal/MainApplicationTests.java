package com.mhyl.performance.appraisal;

import com.mhyl.performance.appraisal.utils.PwdUtils;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

class MainApplicationTests {

    public static void main(String[] args) {
        System.out.println(PwdUtils.createSalt());
    }
}
