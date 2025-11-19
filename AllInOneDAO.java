package com.mycompany.finals;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AllInOneDAO {

    // ---------- GUEST DAO ----------
    public static void saveGuest(Guest g) {
        try (Connection conn = DBConnection.getConnection()) {
            String sql = "INSERT INTO guest VALUES (0,?,?,?,?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, g.getFirstName());
            stmt.setString(2, g.getLastName());
            stmt.setString(3, g.getAddress());
            stmt.setString(4, g.getPhoneNumber());
            stmt.executeUpdate();
        } catch (Exception e) { e.printStackTrace(); }
    }

    public static List<Guest> getAllGuests() {
        List<Guest> list = new ArrayList<>();
        try (Connection conn = DBConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM guest");
            ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Guest g = new Guest();
                g.setGuestID(rs.getInt("guestID"));
                g.setFirstName(rs.getString("firstname"));
                g.setLastName(rs.getString("lastname"));
                g.setAddress(rs.getString("address"));
                g.setPhoneNumber(rs.getString("phone"));
                list.add(g);
            }
        } catch (Exception e) { e.printStackTrace(); }
        return list;
    }

    public static Guest getGuestById(int id) {
        Guest g = null;
        try (Connection conn = DBConnection.getConnection()) {
            String sql = "SELECT * FROM guest WHERE guestID=?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                g = new Guest();
                g.setGuestID(id);
                g.setFirstName(rs.getString("firstname"));
                g.setLastName(rs.getString("lastname"));
                g.setAddress(rs.getString("address"));
                g.setPhoneNumber(rs.getString("phone"));
            }
        } catch (Exception e) { e.printStackTrace(); }
        return g;
    }

    public static void updateGuest(Guest g) {
        try (Connection conn = DBConnection.getConnection()) {
            String sql = "UPDATE guest SET firstname=?, lastname=?, address=?, phone=? WHERE guestID=?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, g.getFirstName());
            stmt.setString(2, g.getLastName());
            stmt.setString(3, g.getAddress());
            stmt.setString(4, g.getPhoneNumber());
            stmt.setInt(5, g.getGuestID());
            stmt.executeUpdate();
        } catch (Exception e) { e.printStackTrace(); }
    }

    public static void deleteGuest(int id) {
        try (Connection conn = DBConnection.getConnection()) {
            PreparedStatement stmt = conn.prepareStatement("DELETE FROM guest WHERE guestID=?");
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (Exception e) { e.printStackTrace(); }
    }



    // ---------- ROOM DAO ----------
    public static void saveRoom(Room r) {
        try (Connection conn = DBConnection.getConnection()) {
            String sql = "INSERT INTO room VALUES (0,?,?,?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            
            stmt.setString(2, r.getRoomType());
            stmt.setString(3, r.getStatus());
            stmt.executeUpdate();
        } catch (Exception e) { e.printStackTrace(); }
    }

    public static List<Room> getAllRooms() {
        List<Room> list = new ArrayList<>();
        try (Connection conn = DBConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM room");
            ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Room r = new Room();
                
                r.setRoomID(rs.getInt("roomID"));
                r.setRoomType(rs.getString("type"));
                r.setStatus(rs.getString("status"));
                list.add(r);
            }
        } catch (Exception e) { e.printStackTrace(); }
        return list;
    }

    public static void updateRoom(Room r) {
        try (Connection conn = DBConnection.getConnection()) {
            String sql = "UPDATE room SET roomNumber=?, type=?, status=? WHERE roomID=?";
            PreparedStatement stmt = conn.prepareStatement(sql);
         
            stmt.setString(2, r.getRoomType());
            stmt.setString(3, r.getStatus());
            stmt.setInt(4, r.getRoomID());
            stmt.executeUpdate();
        } catch (Exception e) { e.printStackTrace(); }
    }

    public static void deleteRoom(int id) {
        try (Connection conn = DBConnection.getConnection()) {
            PreparedStatement stmt = conn.prepareStatement("DELETE FROM room WHERE roomID=?");
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (Exception e) { e.printStackTrace(); }
    }



    // ---------- RESERVATION DAO ----------
    public static void saveReservation(Reservation r) {
        try (Connection conn = DBConnection.getConnection()) {
            String sql = "INSERT INTO reservation VALUES (0,?,?,?,?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, r.getGuestID());
            stmt.setInt(2, r.getRoomID());
            stmt.setString(3, r.getCheckIn());
            stmt.setString(4, r.getCheckOut());
            stmt.executeUpdate();
        } catch (Exception e) { e.printStackTrace(); }
    }

    public static List<Reservation> getAllReservations() {
        List<Reservation> list = new ArrayList<>();
        try (Connection conn = DBConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM reservation");
            ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Reservation r = new Reservation();
                r.setReservationID(rs.getInt("reservationID"));
                r.setGuestID(rs.getInt("guestID"));
                r.setRoomID(rs.getInt("roomID"));
                r.setCheckIn(rs.getString("checkin"));
                r.setCheckOut(rs.getString("checkout"));
                list.add(r);
            }
        } catch (Exception e) { e.printStackTrace(); }
        return list;
    }

    // ---------- PAYMENT DAO ----------
    public static void savePayment(Payment p) {
        try (Connection conn = DBConnection.getConnection()) {
            String sql = "INSERT INTO payment VALUES (0,?,?,?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, p.getReservationID());
            stmt.setDouble(2, p.getAmount());
            stmt.setString(3, p.getDate());
            stmt.executeUpdate();
        } catch (Exception e) { e.printStackTrace(); }
    }

    public static List<Payment> getAllPayments() {
        List<Payment> list = new ArrayList<>();
        try (Connection conn = DBConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM payment");
            ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Payment p = new Payment();
                p.setPaymentID(rs.getInt("paymentID"));
                p.setReservationID(rs.getInt("reservationID"));
                p.setAmount(rs.getDouble("amount"));
                p.setDate(rs.getString("date"));
                list.add(p);
            }
        } catch (Exception e) { e.printStackTrace(); }
        return list;
    }

}
