package com.bc.bcplugin.GUI;

import org.bukkit.entity.HumanEntity;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class OpenCoinAboutGUIEvent extends Event {
    private static final HandlerList HANDLERS = new HandlerList();
    public HumanEntity ent;

    public OpenCoinAboutGUIEvent(HumanEntity ent) {
        this.ent = ent;
    }

    public HandlerList getHandlers() {
        return HANDLERS;
    }

    public static HandlerList getHandlerList() {
        return HANDLERS;
    }
}
