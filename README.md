# Aurora SMP (1.21.11 Lifesteal & Economy)

Aurora SMP is an enterprise-optimized Minecraft **1.21.11** Lifesteal and Economy server built on **Purpur**, configured for high-performance dedicated hosting (Ryzen 9 / 5600X, 12GB RAM, 50GB SSD).

Universal cross-version protocol support is pre-installed via the ViaVersion suite, allowing all players across Minecraft versions **1.8 through 1.21.11+ / modern clients** to connect.

## Core Features & Economy

* **Authentication System**:
  * [AuthMeReloaded](plugins/AuthMe.jar) & [FlexLoginUI](plugins/FlexLoginUI/configs/en.yml): Modern interactive modal dialog authentication with password hashing, session timeout, movement freezing, and PacketEvents packet interception matching the MineDream dialog UI.
* **Island Lobby & Hub**:
  * [Lobby World](lobby/): BreadBuilds Island Lobby configured via [Multiverse-Core](plugins/Multiverse-Core/) and protected by [WorldGuard](plugins/WorldGuard/worlds/lobby/regions.yml) (PvP disabled, fall damage denied, peaceful, invincibility enabled). Players access via `/lobby` or `/spawn`.
  * **Nether Portal RTP**: The large Nether Portal in the Island Lobby is wired via [Multiverse-Portals](plugins/Multiverse-Portals/portals.yml) and [Multiverse-CommandDestination](plugins/Multiverse-CommandDestination/config.yml) to automatically execute [BetterRTP](plugins/BetterRTP/config.yml) (`/rtp`), instantly scattering players safely into the survival wilderness.
* **DonutSMP Economy**:
  * [DonutOrders](plugins/DonutOrders/config.yml): Player-driven buy and sell order exchange (`/orders` and `/order`) with automated escrow payouts.
  * [EconomyShopGUI](plugins/EconomyShopGUI/shops/Farming.yml): DonutSMP crop prices for Sugar Cane, Cactus, Bamboo, Wheat, and Mob items to incentivize massive automated farming, along with `/sell` commands.
  * [Fadah](plugins/Fadah/config.yml): Player-to-player auction house (`/ah`).
  * Pure Survival Balance: Player kits completely disabled to enforce genuine grind-and-trade survival progression.
* **Lifesteal Mechanics**:
  * [LifeSteal](plugins/LifeSteal/config.yml): 20-heart (40 HP) cap, custom heart items (`/withdrawheart`), and strict 1-heart floor limit (players never drop or lose hearts below 1 heart).
* **Social & Proximity Voice**:
  * [Simple Voice Chat](plugins/VoiceChat.jar): Low-latency proximity voice chat on UDP port 24454 configured in [voicechat-server.properties](plugins/voicechat/voicechat-server.properties).
* **Anti-Xray & Security**:
  * Lightweight Anti-Xray ([config/paper-world-defaults.yml](config/paper-world-defaults.yml)): Engine-Mode 1 hides underground diamond, netherite debris, emerald, gold, iron, and chests without the CPU or packet overhead of fake block generation.
* **Combat & Anticheat**:
  * [Lightning Anticheat](plugins/LightningAC.jar): High-performance fork of GrimAC utilizing an asynchronous predictive 1:1 movement simulation engine with optimized reach, interaction, and movement checks.
  * [CombatLogX](plugins/CombatLogX/config.yml): Strict PvP-only combat tagging (natural damage and mob taggers stripped so fall, fire, and PvE never tag). 15-second timer, disconnect death punishment, and blocked escape commands (`/tpa`, `/home`, `/spawn`, `/lobby`, `/shop`, `/ah`, `/orders`, `/rtp`, `/ec`).
* **Visuals & Permissions**:
  * [TAB](plugins/TAB/config.yml): Aurora SMP gradient headers, footers, nametags, and DonutSMP sidebar scoreboard showing Hearts (`%player_health%❤`), Balance, and Combat Status.
  * [LuckPerms](plugins/LuckPerms/yaml-storage/groups/default.yml): File-based YAML permissions for instant deployability.
  * [Vault](plugins/Vault/): Unified economy bridge.
  * [FastAsyncWorldEdit (FAWE)](plugins/FastAsyncWorldEdit/): Spawn protection and low-memory asynchronous terrain editing with lobby schematic included.
  * [CoreProtect](plugins/CoreProtect/): High-speed rollbacks with hopper/fluid logging stripped to protect the 50GB storage limit.

## Hardware & Performance Tuning

* **Memory Allocation**: 10GB JVM Heap (`-Xms10G -Xmx10G`) with 2GB reserved for off-heap Netty and system overhead.
* **SIMD Vectorization**: Vector API incubator module enabled (`--add-modules=jdk.incubator.vector`) for Ryzen Zen processors.
* **Entity & Redstone Engine**: Pufferfish Dynamic Activation of Brains (DAB) and Paper `ALTERNATE_CURRENT` redstone engine enabled.

## Starting the Server

```bash
chmod +x start.sh
./start.sh
```

## License & Legal Protection

Copyright (c) 2026 Developer Army.

Licensed under the Apache License, Version 2.0. See the [LICENSE](LICENSE) and [NOTICE](NOTICE) files for complete licensing and attribution terms.
