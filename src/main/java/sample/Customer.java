package sample;

import java.util.Enumeration;
import java.util.Vector;

/**
 * @author Kj Nam
 * @since 2016-09-06
 */
public class Customer {
    private String name;
    private Vector rentals = new Vector();

    public Customer(String name) {
        this.name = name;
    }

    public void addRental(Rental arg) {
        rentals.addElement(arg);
    }

    public String getName() {
        return name;
    }

    public String statement() {
        double totalAmount = 0;
        int frequentRenterPoints = 0;
        Enumeration rentalsEm = rentals.elements();
        String result = getName() + "고객님의 대여 기록\n";
        while (rentalsEm.hasMoreElements()) {
            double thisAmount = 0;
            Rental each = (Rental) rentalsEm.nextElement();
            thisAmount = amountFor(each);

            // 적립포인트 1  증가
            frequentRenterPoints++;
            //최신물을 이틀 이상 대여하면 보너스 포인트 지급
            if ((each.getMovie().getPriceCode() == Movie.NEW_RELEASE) && each.getDaysRented() >1)
                frequentRenterPoints++;

            // 이번에 대여하는 비디오 정보와 대여료를 출력
            result += "\t" + each.getMovie().getTitle() + "\t" + String.valueOf(thisAmount) + "\n";
            //현재까지 누적된 총 대여료
            totalAmount += thisAmount;
        }
        // 푸터 행 추가
        result += "누적 대여료: " + String.valueOf(totalAmount) + "\n";
        result += "적립 포인트: " + String.valueOf(frequentRenterPoints);
        return result;
    }

    // 비디오 종류별 대여료 계산
    private double amountFor(Rental each) {
        int thisAmount = 0;
        switch (each.getMovie().getPriceCode()) {
            case Movie.REGULAR:
                thisAmount += 2;
                if (each.getDaysRented() > 2)
                    thisAmount += (each.getDaysRented()-2) * 1.5;
                break;

            case Movie.NEW_RELEASE:
                thisAmount += each.getDaysRented() * 3;
                break;

            case Movie.CHILDRENS:
                thisAmount += 1.5;
                if (each.getDaysRented() > 3)
                    thisAmount += (each.getDaysRented()-3) * 1.5;
                break;
        }
        return thisAmount;
    }
}
