public class Fight {
    public static void goblinAttack(Object x, Goblin g){
        if (x instanceof Human){
            ((Human) x).setHP(((Human)x).getHP() - (int)(Math.random() * g.getSTR()+ 1));
        } else if ( x instanceof Player){
            ((Player) x).setHP(((Player)x).getHP()- (int)(Math.random() *g.getSTR()+1));
        }

    }
    public static void humanAttacks(Human h, Goblin g){
        g.setHP(g.getHP()- (int)(Math.random() * h.getSTR()+1));
    }
    public static void playerAttacks(Player p, Goblin g){
        g.setHP(g.getHP()- (int)(Math.random() * p.getSTR()+1));
    }
    public static String goblinVsHuman(Human h,Goblin g, HumansVsGoblins game){
        String output = "";
        while (h.getHP() > 0 && g.getHP() > 0){
            goblinAttack(h,g);
            humanAttacks(h,g);
        }
        if (h.getHP()<= 0 && g.getHP()<= 0){
            game.rGoblin(g);
            game.rHuman(h);
            output += "The human and goblin both died." + "\n";
        }else if(h.getHP()<= 0){
            game.rHuman(h);
            output += "The goblin killed the human." + "\n";
        }else if (g.getHP()<= 0){
            game.rGoblin(g);
            output+= "The human killed the goblin!" +"\n";
        }
        return output;
    }
    public static String playerVsGoblin(Player p, Goblin g, HumansVsGoblins game){
        String output = "";
        while(p.getHP() >0 && g.getHP()>0){
            goblinAttack(p,g);
            playerAttacks(p,g);
        }
        if(p.getHP() <= 0 && g.getHP() <= 0){
            game.setGaming(false);
            output += "You and the goblin both died: YOU LOSE!" + "\n";
        }else if(p.getHP() <= 0){
            game.setGaming(false);
            output += "The goblin killed you: YOU LOSE!" + "\n";
        }else if(g.getHP() <= 0){
            game.rGoblin(g);
            output += "You killed one goblin. Keep going!" + "\n";
        }
        return output;
    }
}
