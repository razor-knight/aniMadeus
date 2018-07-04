package net.mrporky.anisoc.util;

import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.entities.Game;
import net.mrporky.anisoc.Main;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class NameManager {
    private JDA jda;
    private List<String> list;
    private String filePath;
    private Game.GameType type = Game.GameType.DEFAULT;
    private boolean setup = false;
    int lineCount = 0;

    public NameManager(String filePath) {
        this.filePath = filePath;
    }

    public NameManager(String filePath, Game.GameType type, JDA jda) {
        this.filePath = filePath;
        this.type = type;
        this.jda = jda;
    }

    public boolean getSetup(){
        return setup;
    }

    public void updateGame(){
        try {
            BufferedReader in = new BufferedReader(new FileReader(filePath));
            String str;

            list = new ArrayList<String>();
            while ((str = in.readLine()) != null) {
                list.add(str);
            }
            String[] stringArr = list.toArray(new String[0]);
            Random random = new Random();
            int rand = random.nextInt(list.size());

            Main.jda.getPresence().setGame(Game.of(type, list.get(random.nextInt(list.size()))));

            setup = true;
        }catch(IOException e){
            System.out.println("Could not read the file: " + filePath);
        }
    }

    public List<String> getList(){
        return list;
    }
}

