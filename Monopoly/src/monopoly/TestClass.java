/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package monopoly;

/**
 *
 * @author benjh
 */
public class TestClass {
    public static void main(String[] args) {
        
        MonopolyView view = new MonopolyView();
        MonopolyModel model = new MonopolyModel();
        MonopolyController control = new MonopolyController(view, model);
    }
}
