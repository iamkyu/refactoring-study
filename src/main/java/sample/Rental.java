package sample;

/**
 * @author Kj Nam
 * @since 2016-09-06
 */
public class Rental {
    private Movie movie;
    private int daysRented;

    public Rental(Movie movie, int daysRented) {
        this.movie = movie;
        this.daysRented = daysRented;
    }

    public Movie getMovie() {
        return movie;
    }

    public int getDaysRented() {
        return daysRented;
    }

    // 비디오 종류별 대여료 계산
    public double getCharge() {
        double result = 0;
        switch (getMovie().getPriceCode()) {
            case Movie.REGULAR:
                result += 2;
                if (getDaysRented() > 2)
                    result += (getDaysRented()-2) * 1.5;
                break;

            case Movie.NEW_RELEASE:
                result += getDaysRented() * 3;
                break;

            case Movie.CHILDRENS:
                result += 1.5;
                if (getDaysRented() > 3)
                    result += (getDaysRented()-3) * 1.5;
                break;
        }
        return result;
    }

    public int getFrequentRenterPoints() {
        //최신물을 이틀 이상 대여하면 보너스 포인트 지급
        if ((getMovie().getPriceCode() == Movie.NEW_RELEASE) && getDaysRented() > 1)
            return 2;
        else
            return 1;
    }
}
