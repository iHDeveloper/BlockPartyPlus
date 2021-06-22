package me.iHDeveloper.api.npc;

import com.mojang.authlib.GameProfile;
import net.minecraft.server.v1_8_R3.EntityPlayer;
import net.minecraft.server.v1_8_R3.MinecraftServer;
import net.minecraft.server.v1_8_R3.PlayerInteractManager;
import net.minecraft.server.v1_8_R3.WorldServer;

class Entity extends EntityPlayer {
  public Entity(MinecraftServer server, WorldServer world, GameProfile profile, PlayerInteractManager manager) {
    super(server, world, profile, manager);
    world.addEntity((net.minecraft.server.v1_8_R3.Entity)this);
  }
  
  public void move(double d0, double d1, double d2) {}
}
