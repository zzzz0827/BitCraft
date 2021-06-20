package com.bc.bcplugin.GUI.have;

import org.bukkit.entity.HumanEntity;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class OpenCoinHaveGUIEvent extends Event {
    private static final HandlerList HANDLERS = new HandlerList();
    public HumanEntity ent;

    public OpenCoinHaveGUIEvent(HumanEntity ent) {
        this.ent = ent;
    }

    public HandlerList getHandlers() {
        return HANDLERS;
    }

    public static HandlerList getHandlerList() {
        return HANDLERS;
    }
}

