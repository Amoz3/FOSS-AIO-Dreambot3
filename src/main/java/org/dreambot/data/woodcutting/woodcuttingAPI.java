package org.dreambot.data.woodcutting;

import org.dreambot.api.methods.grandexchange.GrandExchange;
import org.dreambot.api.methods.skills.Skill;
import org.dreambot.api.methods.skills.Skills;

public class woodcuttingAPI {


    public static String bestAxe(){
        if (Skills.getRealLevel(Skill.WOODCUTTING) < 6){
            return "Bronze axe";
        }
        else if (Skills.getRealLevel(Skill.WOODCUTTING) < 11 && Skills.getRealLevel(Skill.WOODCUTTING) >= 6){
            return "Steel axe";
        }
        else if (Skills.getRealLevel(Skill.WOODCUTTING) < 11 && Skills.getRealLevel(Skill.WOODCUTTING) >= 6){
            return "Steel axe";
        }
        else if (Skills.getRealLevel(Skill.WOODCUTTING) < 21 && Skills.getRealLevel(Skill.WOODCUTTING) >= 11){
            return "Black axe";
        }
        else if (Skills.getRealLevel(Skill.WOODCUTTING) < 31 && Skills.getRealLevel(Skill.WOODCUTTING) >= 21){
            return "Mithril axe";
        }
        else if (Skills.getRealLevel(Skill.WOODCUTTING) < 41 && Skills.getRealLevel(Skill.WOODCUTTING) >= 31){
            return "Adamant axe";
        }
        else if (Skills.getRealLevel(Skill.WOODCUTTING) >= 41){
            return "Rune axe";
        }
        else{
        return null;
        }
    }


}
