package tracker;

import org.junit.jupiter.api.*;

class UserTest {
    static int number = 14;

    UserTest() {
        number *= 2;
    }

    @BeforeAll
    static void method3() {
        number += 11;
    }

    @BeforeEach
    void method2() {
        number -= 4;
    }

    @AfterAll
    static void method4() {
        number /= 3;
    }

    @AfterEach
    void method5() {
        System.out.println(number);
    }

    @Test
    void method6() {
        number += 9;
    }
}
