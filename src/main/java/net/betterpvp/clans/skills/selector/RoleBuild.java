package net.betterpvp.clans.skills.selector;

import net.betterpvp.clans.skills.Types;
import net.betterpvp.clans.skills.selector.skills.Skill;

import java.util.ArrayList;


public class RoleBuild {

    private boolean active;
    private String role;
    private String name;
    private BuildSkill sword;
    private BuildSkill axe;
    private BuildSkill passiveA, passiveB, global;
    private BuildSkill bow;
    private int id;
    private int points = 12;

    public RoleBuild(String role, int id) {
        this.role = role;
        this.id = id;
    }

    public void deleteBuild() {
        sword = null;
        axe = null;
        passiveA = null;
        passiveB = null;
        global = null;
        bow = null;
        points = 12;

    }

    public void setActive(boolean b) {
        this.active = b;
    }

    public boolean isActive() {
        return active;
    }

    public int getPoints() {
        return points;
    }

    public void addPoint() {
        points++;
    }

    public void takePoint() {
        points--;
    }

    public void takePoints(int amount) {
        points -= amount;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BuildSkill getSword() {
        return sword;
    }

    public void setSword(BuildSkill sword) {
        this.sword = sword;
    }

    public BuildSkill getAxe() {
        return axe;
    }

    public void setAxe(BuildSkill axe) {
        this.axe = axe;
    }

    public void setBow(BuildSkill bow) {
        this.bow = bow;
    }

    public BuildSkill getBow() {
        return bow;
    }

    public void setGlobal(BuildSkill global) {
        this.global = global;
    }

    public BuildSkill getGlobal() {
        return global;
    }

    public BuildSkill getPassiveA() {
        return passiveA;
    }

    public BuildSkill getPassiveB() {
        return passiveB;
    }

    public void setPassiveA(BuildSkill passive) {
        this.passiveA = passive;
    }

    public void setPassiveB(BuildSkill passive) {
        this.passiveB = passive;
    }

    public int getID() {
        return id;
    }

    public void setID(int id) {
        this.id = id;
    }

    public ArrayList<Skill> getActiveSkills() {
        ArrayList<Skill> skills = new ArrayList<>();
        if (getSword() != null) {
            skills.add(getSword().getSkill());
        }
        if (getAxe() != null) {
            skills.add(getAxe().getSkill());
        }
        if (getBow() != null) {
            skills.add(getBow().getSkill());
        }
        if (getPassiveA() != null) {
            skills.add(getPassiveA().getSkill());
        }
        if (getPassiveB() != null) {
            skills.add(getPassiveB().getSkill());
        }
        if (getGlobal() != null) {
            skills.add(getGlobal().getSkill());
        }
        return skills;
    }

    public BuildSkill getBuildSkill(Types type) {
        switch (type) {
            case SWORD:
                return getSword();
            case AXE:
                return getAxe();
            case PASSIVE_A:
                return getPassiveA();
            case BOW:
                return getBow();
            case GLOBAL:
                return getGlobal();
            case PASSIVE_B:
                return getPassiveB();
        }

        return null;
    }

    public void setSkill(Types type, Skill skill, int level) {
        switch (type) {
            case SWORD:
                setSword(new BuildSkill(skill, level));
                break;
            case AXE:
                setAxe(new BuildSkill(skill, level));
                break;
            case PASSIVE_A:
                setPassiveA(new BuildSkill(skill, level));
                break;
            case BOW:
                setBow(new BuildSkill(skill, level));
                break;
            case GLOBAL:
                setGlobal(new BuildSkill(skill, level));
                break;
            case PASSIVE_B:
                setPassiveB(new BuildSkill(skill, level));
                break;
        }

    }

    public void setSkill(Types type, BuildSkill skill) {
        switch (type) {
            case SWORD:
                setSword(skill);
                break;
            case AXE:
                setAxe(skill);
                break;
            case PASSIVE_A:
                setPassiveA(skill);
                break;
            case BOW:
                setBow(skill);
                break;
            case GLOBAL:
                setGlobal(skill);
                break;
            case PASSIVE_B:
                setPassiveB(skill);
                break;

        }

    }

}