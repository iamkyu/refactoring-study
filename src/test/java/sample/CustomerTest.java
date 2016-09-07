package sample;


import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

/**
 * @author Kj Nam
 * @since 2016-09-07
 */
public class CustomerTest {

    private Movie 정글북;
    private Movie 바람과함께사라지다;
    private Rental 렌탈;
    private Customer 고객1;

    @Before
    public void setUp() {
        고객1 = new Customer("고객1");
        정글북 = new Movie("정글북", Movie.CHILDRENS);
        바람과함께사라지다 = new Movie("바람과함께사라지다", Movie.NEW_RELEASE);
    }

    @After
    public void tearDown() {

    }

    @Test
    public void 대여_발생() {
        //given
        렌탈 = new Rental(바람과함께사라지다, 5);

        //when
        고객1.addRental(렌탈);

        //then
        String message = 고객1.statement();
        assertThat(message, is("고객1고객님의 대여 기록\n" +
                             "\t바람과함께사라지다\t15.0\n" +
                             "누적 대여료: 15.0\n" +
                             "적립 포인트: 2"));
    }
}