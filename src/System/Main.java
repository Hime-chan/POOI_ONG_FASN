package System;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;
import java.util.Scanner;

import DAO.Conn;
import Fasn.Veterinario;

public class Main {
	
    public static void main(String[] args) {
    	MenuManager mm = new MenuManager();
    	mm.menus.get("principal").ExibirMenu();
    }
}
