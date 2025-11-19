package com.mycompany.finals;

import java.awt.event.*;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class AllInOneController {

    GuestView gv;
    RoomView rv;
    ReservationView resv;
    PaymentView pv;
   
    private Guest selectedGuest;
   private Room selectedRoom;
   private Reservation selectedReservation;
   private Payment selectedPayment;
    
    

    public AllInOneController(GuestView gv, RoomView rv, ReservationView resv, PaymentView pv) {
        this.gv = gv;
        this.rv = rv;
        this.resv = resv;
        this.pv = pv;

       
        gv.addBTN.addActionListener(e -> addGuest());
        

        gv.jTable2.getSelectionModel().addListSelectionListener(e -> tableSelectGuest());

        
        rv.roomBTN.addActionListener(e -> addRoom());

       
        resv.reserveBTN.addActionListener(e -> addReservation());

        
        pv.payBTN.addActionListener(e -> addPayment());

        
        loadGuestTable();
        loadRoomCombo();
        loadRoomTable();
    }

    // ======================================================
    //                   GUEST SECTION
    // ======================================================
    private void addGuest() {
        Guest g = new Guest();
        g.setFirstName(gv.jTextField1.getText());
        g.setLastName(gv.jTextField5.getText());
        g.setAddress(gv.jTextField6.getText());
        g.setPhoneNumber(gv.jTextField7.getText());

        AllInOneDAO.saveGuest(g);
        loadGuestTable();
        clearGuestForm();
        JOptionPane.showMessageDialog(gv, "Guest Added!");
    }

    private void tableSelectGuest() {
        int row = gv.jTable2.getSelectedRow();
        if (row >= 0) {
            selectedGuest = new Guest();
            selectedGuest.setGuestID(Integer.parseInt(gv.jTable2.getValueAt(row, 0).toString()));
            selectedGuest.setFirstName(gv.jTable2.getValueAt(row, 1).toString());
            selectedGuest.setLastName(gv.jTable2.getValueAt(row, 2).toString());
            selectedGuest.setAddress(gv.jTable2.getValueAt(row, 3).toString());
            selectedGuest.setPhoneNumber(gv.jTable2.getValueAt(row, 4).toString());
        }
    }

    private void loadGuestForEdit() {
        if (selectedGuest == null) {
            JOptionPane.showMessageDialog(gv, "Select a guest.");
            return;
        }

        gv.jTextField1.setText(selectedGuest.getFirstName());
        gv.jTextField5.setText(selectedGuest.getLastName());
        gv.jTextField6.setText(selectedGuest.getAddress());
        gv.jTextField7.setText(selectedGuest.getPhoneNumber());
    }

    private void updateGuest() {
        if (selectedGuest == null) return;

        selectedGuest.setFirstName(gv.jTextField1.getText());
        selectedGuest.setLastName(gv.jTextField5.getText());
        selectedGuest.setAddress(gv.jTextField6.getText());
        selectedGuest.setPhoneNumber(gv.jTextField7.getText());

        AllInOneDAO.updateGuest(selectedGuest);
        loadGuestTable();
        clearGuestForm();
        JOptionPane.showMessageDialog(gv, "Guest Updated!");
    }

    private void deleteGuest() {
        if (selectedGuest == null) return;

        AllInOneDAO.deleteGuest(selectedGuest.getGuestID());
        loadGuestTable();
        clearGuestForm();
        JOptionPane.showMessageDialog(gv, "Guest Deleted!");
    }

    private void loadGuestTable() {
        List<Guest> list = AllInOneDAO.getAllGuests();
        DefaultTableModel model = (DefaultTableModel) gv.jTable2.getModel();
        model.setRowCount(0);

        for (Guest g : list) {
            model.addRow(new Object[]{
                g.getGuestID(), g.getFirstName(), g.getLastName(), g.getAddress(), g.getPhoneNumber()
            });
        }
    }

    private void clearGuestForm() {
        gv.jTextField1.setText("");
        gv.jTextField5.setText("");
        gv.jTextField6.setText("");
        gv.jTextField7.setText("");
        selectedGuest = null;
    }
    private void addRoom() {
        Room r = new Room();
        
        r.setRoomType(rv.comboBox.getActionCommand().toString());
        r.setStatus(rv.jRadioButton4.getSelectedIcon().toString());

        AllInOneDAO.saveRoom(r);
        loadRoomTable();
        loadRoomCombo();
        JOptionPane.showMessageDialog(rv, "Room Added!");
    }

    private void loadRoomTable() {
        List<Room> list = AllInOneDAO.getAllRooms();
        DefaultTableModel model = (DefaultTableModel) rv.jTable2.getModel();
        model.setRowCount(0);

        for (Room r : list) {
            model.addRow(new Object[]{
                r.getRoomID(), r.getRoomType(), r.getStatus()
            });
        }
    }

    private void loadRoomCombo() {
        resv.roomBox.removeAllItems();
        for (Room r : AllInOneDAO.getAllRooms()) {
            if (r.getStatus().equals("Available")) {
                resv.roomBox.addItem(r.getRoomType() + " - ID:" + r.getRoomID());
            }
        }
    }
    private void addReservation() {
        Reservation r = new Reservation();

        // guest id from text
        r.setGuestID(Integer.parseInt(resv.jTextField1.getText()));

        // Extract room ID
        String selectedRoom = resv.roomBox.getActionCommand().toString();
        int roomID = Integer.parseInt(selectedRoom.split("ID:")[1]);
        r.setRoomID(roomID);

        r.setCheckIn(resv.jTextField2.getText());
        r.setCheckOut(resv.jTextField3.getText());

        AllInOneDAO.saveReservation(r);
        JOptionPane.showMessageDialog(resv, "Reservation Added!");

        loadRoomCombo();
        loadRoomTable();
    }



 
    private void addPayment() {
        Payment p = new Payment();

        p.setReservationID(Integer.parseInt(pv.jTextField1.getText()));
        p.setAmount(Double.parseDouble(pv.jTextField2.getText()));
        p.setDate(pv.jTextField3.getText());

        AllInOneDAO.savePayment(p);
        JOptionPane.showMessageDialog(pv, "Payment Successful!");
    }

}
