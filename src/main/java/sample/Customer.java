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
        Enumeration rentalsEm = rentals.elements();
        String result = getName() + "고객님의 대여 기록\n";
        while (rentalsEm.hasMoreElements()) {
            Rental each = (Rental) rentalsEm.nextElement();

            // 이번에 대여하는 비디오 정보와 대여료를 출력
            result += "\t" + each.getMovie().getTitle() + "\t" + String.valueOf(each.getCharge()) + "\n";
        }
        // 푸터 행 추가
        result += "누적 대여료: " + String.valueOf(getTotalCharge()) + "\n";
        result += "적립 포인트: " + String.valueOf(getTotalFrequentRenterPoints());
        return result;
    }

    private int getTotalFrequentRenterPoints() {
        int result = 0;
        Enumeration rentalsEm = rentals.elements();
        while (rentalsEm.hasMoreElements()) {
            Rental each = (Rental) rentalsEm.nextElement();
            result += each.getFrequentRenterPoints();
        }
        return result;
    }

    private double getTotalCharge() {
        double result = 0;
        Enumeration rentalsEm = rentals.elements();
        while (rentalsEm.hasMoreElements()) {
            Rental each = (Rental) rentalsEm.nextElement();
            result += each.getCharge();
        }
        return result;
    }

    // 비디오 종류별 대여료 계산
    private double amountFor(Rental aRental) {
        return aRental.getCharge();
    }
}
