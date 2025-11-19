/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.finals;

/**
 *
 * @author gamin
 */
public class MainClass {
    public static void main(String[] args) {
        Dashboard dash = new Dashboard();
        new DashboardController(dash);
        dash.setVisible(true);
    }
}

