package kr.happynewyear.library.test

import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.annotation.DirtiesContext
import org.springframework.test.annotation.DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD

@SpringBootTest
@DirtiesContext(classMode = AFTER_EACH_TEST_METHOD)
abstract class ApplicationTest
