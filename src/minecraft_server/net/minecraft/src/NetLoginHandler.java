package net.minecraft.src;

import java.io.IOException;
import java.net.Socket;
import java.util.*;
import java.util.logging.Logger;
import net.minecraft.server.MinecraftServer;

public class NetLoginHandler extends NetHandler
{

    public static Logger logger = Logger.getLogger("Minecraft");
    private static Random rand = new Random();
    public NetworkManager netManager;
    public boolean finishedProcessing;
    private MinecraftServer mcServer;
    private int loginTimer;
    private String username;
    private Packet1Login packet1login;
    private String serverId;

    public NetLoginHandler(MinecraftServer minecraftserver, Socket socket, String s)
        throws IOException
    {
        finishedProcessing = false;
        loginTimer = 0;
        username = null;
        packet1login = null;
        serverId = "";
        mcServer = minecraftserver;
        netManager = new NetworkManager(socket, s, this);
        netManager.chunkDataSendCounter = 0;
    }

    public void tryLogin()
    {
        if(packet1login != null)
        {
            doLogin(packet1login);
            packet1login = null;
        }
        if(loginTimer++ == 600)
        {
            kickUser("Took too long to log in");
        } else
        {
            netManager.processReadPackets();
        }
    }

    public void kickUser(String s)
    {
        try
        {
            logger.info((new StringBuilder()).append("Disconnecting ").append(getUserAndIPString()).append(": ").append(s).toString());
            netManager.addToSendQueue(new Packet255KickDisconnect(s));
            netManager.serverShutdown();
            finishedProcessing = true;
        }
        catch(Exception exception)
        {
            exception.printStackTrace();
        }
    }

    public void handleHandshake(Packet2Handshake packet2handshake)
    {
        netManager.addToSendQueue(new Packet2Handshake("-"));
    }

    public void handleLogin(Packet1Login packet1login)
    {
        username = packet1login.username;
        if(packet1login.protocolVersion != 233001)
        {
            if(packet1login.protocolVersion > 233001)
            {
                kickUser("The server is using an older version of the game - 233001.");
            } else
            {
                kickUser("The server is using a newer version of the game - 233001!");
            }
            return;
        }
        doLogin(packet1login);
    }

    public void doLogin(Packet1Login packet1login)
    {
        EntityPlayerMP entityplayermp = mcServer.configManager.login(this, packet1login.username);
        if(entityplayermp != null)
        {
            mcServer.configManager.readPlayerDataFromFile(entityplayermp);
            entityplayermp.Sets(mcServer.getWorldManager(entityplayermp.dimension));
            entityplayermp.itemInWorldManager.setWorld((WorldServer)entityplayermp.worldObj);
            logger.info((new StringBuilder()).append(getUserAndIPString()).append(" logged in with entity id ").append(entityplayermp.entityId).append(" at (").append(entityplayermp.posX).append(", ").append(entityplayermp.posY).append(", ").append(entityplayermp.posZ).append(")").toString());
            WorldServer worldserver = mcServer.getWorldManager(entityplayermp.dimension);
            ChunkCoordinates chunkcoordinates = worldserver.getSpawnPoint();
            entityplayermp.itemInWorldManager.func_35695_b(worldserver.getWorldInfo().getGameType());
            NetServerHandler netserverhandler = new NetServerHandler(mcServer, netManager, entityplayermp);
            netserverhandler.sendPacket(new Packet1Login("", entityplayermp.entityId, worldserver.getRandomSeed(), entityplayermp.itemInWorldManager.getGameType(), (byte)worldserver.worldProvider.worldType, (byte)worldserver.difficultySetting, (byte)worldserver.worldYMax, (byte)mcServer.configManager.getMaxPlayers()));
            netserverhandler.sendPacket(new Packet6SpawnPosition(chunkcoordinates.posX, chunkcoordinates.posY, chunkcoordinates.posZ));
            mcServer.configManager.func_28170_a(entityplayermp, worldserver);
            mcServer.configManager.sendPacketToAllPlayers(new Packet3Chat((new StringBuilder()).append("\247e").append(entityplayermp.username).append(" has joined the game!").toString()));
            mcServer.configManager.playerLoggedIn(entityplayermp);
            netserverhandler.teleportTo(entityplayermp.posX, entityplayermp.posY, entityplayermp.posZ, entityplayermp.rotationYaw, entityplayermp.rotationPitch);
            mcServer.networkServer.addPlayer(netserverhandler);
            netserverhandler.sendPacket(new Packet4UpdateTime(worldserver.getWorldTime()));
            PotionEffect potioneffect;
            for(Iterator iterator = entityplayermp.func_35183_ak().iterator(); iterator.hasNext(); netserverhandler.sendPacket(new Packet41EntityEffect(entityplayermp.entityId, potioneffect)))
            {
                potioneffect = (PotionEffect)iterator.next();
            }

            entityplayermp.func_20057_k();
        }
        finishedProcessing = true;
    }

    public void handleErrorMessage(String s, Object aobj[])
    {
        logger.info((new StringBuilder()).append(getUserAndIPString()).append(" lost connection").toString());
        finishedProcessing = true;
    }

    public void handleServerPing(Packet254ServerPing packet254serverping)
    {
        try
        {
            String s = (new StringBuilder()).append(mcServer.motd).append("\247").append(mcServer.configManager.playersOnline()).append("\247").append(mcServer.configManager.getMaxPlayers()).toString();
            netManager.addToSendQueue(new Packet255KickDisconnect(s));
            netManager.serverShutdown();
            mcServer.networkServer.func_35505_a(netManager.getSocket());
            finishedProcessing = true;
        }
        catch(Exception exception)
        {
            exception.printStackTrace();
        }
    }

    public void registerPacket(Packet packet)
    {
        kickUser("Protocol error");
    }

    public String getUserAndIPString()
    {
        if(username != null)
        {
            return (new StringBuilder()).append(username).append(" [").append(netManager.getRemoteAddress().toString()).append("]").toString();
        } else
        {
            return netManager.getRemoteAddress().toString();
        }
    }

    public boolean isServerHandler()
    {
        return true;
    }

    static String getServerId(NetLoginHandler netloginhandler)
    {
        return netloginhandler.serverId;
    }

    static Packet1Login setLoginPacket(NetLoginHandler netloginhandler, Packet1Login packet1login)
    {
        return netloginhandler.packet1login = packet1login;
    }

}
