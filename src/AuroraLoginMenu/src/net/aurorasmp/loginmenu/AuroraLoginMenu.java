package net.aurorasmp.loginmenu;

import fr.xephi.authme.api.v3.AuthMeApi;
import fr.xephi.authme.events.LoginEvent;
import fr.xephi.authme.events.RegisterEvent;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public final class AuroraLoginMenu extends JavaPlugin implements Listener {

    public static final String MENU_TITLE = "§d✦ §lAURORA SMP §d✦";
    private final ConcurrentHashMap<UUID, Inventory> activeMenus = new ConcurrentHashMap<>();

    @Override
    public void onEnable() {
        Bukkit.getPluginManager().registerEvents(this, this);
        getLogger().info("AuroraLoginMenu enabled successfully.");
    }

    @Override
    public void onDisable() {
        for (Player player : Bukkit.getOnlinePlayers()) {
            if (activeMenus.containsKey(player.getUniqueId())) {
                player.closeInventory();
            }
        }
        activeMenus.clear();
    }

    @EventHandler(priority = EventPriority.NORMAL)
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        Bukkit.getScheduler().runTaskLater(this, () -> {
            if (player.isOnline() && !AuthMeApi.getInstance().isAuthenticated(player)) {
                openBackgroundMenu(player);
            }
        }, 1L);
    }

    private void openBackgroundMenu(Player player) {
        Inventory inv = Bukkit.createInventory(player, 54, MENU_TITLE);

        ItemStack blackPane = createItem(Material.BLACK_STAINED_GLASS_PANE, " ");
        ItemStack purplePane = createItem(Material.PURPLE_STAINED_GLASS_PANE, " ");
        ItemStack magentaPane = createItem(Material.MAGENTA_STAINED_GLASS_PANE, " ");

        // Decorative borders
        for (int i = 0; i < 54; i++) {
            if (i < 9 || i >= 45 || i % 9 == 0 || i % 9 == 8) {
                inv.setItem(i, (i % 2 == 0) ? purplePane : blackPane);
            } else {
                inv.setItem(i, blackPane);
            }
        }

        // Corner accents
        inv.setItem(0, magentaPane);
        inv.setItem(8, magentaPane);
        inv.setItem(45, magentaPane);
        inv.setItem(53, magentaPane);

        // Center Title (Slot 13)
        List<String> starLore = new ArrayList<>();
        starLore.add(ChatColor.GRAY + "Welcome to Aurora SMP Network!");
        starLore.add(ChatColor.DARK_PURPLE + "Survival • LifeSteal • Donut Economy");
        starLore.add("");
        starLore.add(ChatColor.YELLOW + "⚡ Purpur 1.21.1 Core / 1.21.11 Compatible");
        inv.setItem(13, createItem(Material.NETHER_STAR, ChatColor.LIGHT_PURPLE + "" + ChatColor.BOLD + "✦ AURORA SMP ✦", starLore));

        // Authentication Item (Slot 22)
        boolean isRegistered = AuthMeApi.getInstance().isRegistered(player.getName());
        List<String> authLore = new ArrayList<>();
        if (isRegistered) {
            authLore.add(ChatColor.GRAY + "Please enter your password in the");
            authLore.add(ChatColor.GRAY + "modal prompt above to continue.");
        } else {
            authLore.add(ChatColor.GRAY + "Please create a secure password in the");
            authLore.add(ChatColor.GRAY + "modal prompt above to register.");
        }
        authLore.add("");
        authLore.add(ChatColor.DARK_AQUA + "• Password: 5 - 30 characters");
        authLore.add(ChatColor.RED + "• Session Timeout: 75 seconds");
        inv.setItem(22, createItem(Material.WRITABLE_BOOK,
                isRegistered ? (ChatColor.GOLD + "" + ChatColor.BOLD + "✦ LOGIN REQUIRED ✦") : (ChatColor.GREEN + "" + ChatColor.BOLD + "✦ REGISTRATION REQUIRED ✦"),
                authLore));

        // Economy Info (Slot 30)
        List<String> ecoLore = new ArrayList<>();
        ecoLore.add(ChatColor.GRAY + "• Donut Orders (/orders)");
        ecoLore.add(ChatColor.GRAY + "• Server & Player Shop (/shop)");
        ecoLore.add(ChatColor.GRAY + "• Auction House (/ah)");
        ecoLore.add(ChatColor.GRAY + "• Nether Portal Random TP (/rtp)");
        inv.setItem(30, createItem(Material.EMERALD, ChatColor.GREEN + "" + ChatColor.BOLD + "✦ ECONOMY & ORDERS ✦", ecoLore));

        // Combat & PvP Info (Slot 32)
        List<String> pvpLore = new ArrayList<>();
        pvpLore.add(ChatColor.GRAY + "• Steal hearts upon slaying enemies.");
        pvpLore.add(ChatColor.GRAY + "• PvP-only combat tagging (CombatLogX).");
        pvpLore.add(ChatColor.GRAY + "• Simple Voice Chat listening on UDP 24454.");
        inv.setItem(32, createItem(Material.REDSTONE, ChatColor.RED + "" + ChatColor.BOLD + "✦ LIFESTEAL & COMBAT ✦", pvpLore));

        activeMenus.put(player.getUniqueId(), inv);
        player.openInventory(inv);
    }

    private ItemStack createItem(Material material, String name) {
        return createItem(material, name, null);
    }

    private ItemStack createItem(Material material, String name, List<String> lore) {
        ItemStack item = new ItemStack(material);
        ItemMeta meta = item.getItemMeta();
        if (meta != null) {
            meta.setDisplayName(name);
            if (lore != null) {
                meta.setLore(lore);
            }
            item.setItemMeta(meta);
        }
        return item;
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onInventoryClick(InventoryClickEvent event) {
        if (event.getWhoClicked() instanceof Player player) {
            if (activeMenus.containsKey(player.getUniqueId())) {
                event.setCancelled(true);
            }
        }
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onInventoryDrag(InventoryDragEvent event) {
        if (event.getWhoClicked() instanceof Player player) {
            if (activeMenus.containsKey(player.getUniqueId())) {
                event.setCancelled(true);
            }
        }
    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void onLogin(LoginEvent event) {
        Player player = event.getPlayer();
        closeAndClean(player);
    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void onRegister(RegisterEvent event) {
        Player player = event.getPlayer();
        if (AuthMeApi.getInstance().isAuthenticated(player)) {
            closeAndClean(player);
        }
    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void onQuit(PlayerQuitEvent event) {
        activeMenus.remove(event.getPlayer().getUniqueId());
    }

    private void closeAndClean(Player player) {
        if (activeMenus.remove(player.getUniqueId()) != null) {
            Runnable closeTask = player::closeInventory;
            Bukkit.getScheduler().runTask(this, closeTask);
        }
    }
}
