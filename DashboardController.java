package com.mycompany.finals;

public class DashboardController {

    Dashboard view;

    public DashboardController(Dashboard v) {
        this.view = v;

        // Buttons from your Dashboard
        view.guestBTN.addActionListener(e -> openGuest());
        view.roomBTN.addActionListener(e -> openRoom());
        view.reserveBTN.addActionListener(e -> openReservation());
        view.payBTN.addActionListener(e -> openPayment());
        view.exitBTN.addActionListener(e -> System.exit(0));
    }

    private void openGuest() {
        new GuestView().setVisible(true);
    }

    private void openRoom() {
        new RoomView().setVisible(true);
    }

    private void openReservation() {
        new ReservationView().setVisible(true);
    }

    private void openPayment() {
        new PaymentView().setVisible(true);
    }
}
