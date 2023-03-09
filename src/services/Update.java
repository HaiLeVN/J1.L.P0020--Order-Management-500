/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import dto.I_SubMenu;
import java.util.ArrayList;
import utils.Utils;

/**
 *
 * @author Thanh Hai
 */
public class Update extends ArrayList<String> implements I_SubMenu {

    @Override
    public void addItem(String str) {
        this.add(str);
    }

    @Override
    public int getChoice() {
        int result = -1;
        result = Utils.getInt("Input your choice:", 1, 3);
        return result;
    }

    @Override
    public void showMenu() {
        for (int i = 0; i < this.size(); i++) {
            System.out.println(this.get(i).toString());
        }
    }
}
