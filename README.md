# Aurora SMP (1.21.11 Lifesteal & Economy)

Aurora SMP is an enterprise-optimized Minecraft **1.21.11** Lifesteal and Economy server built on **Purpur**, configured for high-performance dedicated hosting (Ryzen 9 / 5600X, 12GB RAM, 50GB SSD).

Universal cross-version protocol support is pre-installed via the ViaVersion suite, allowing all players across Minecraft versions **1.8 through 1.21.11+ / modern clients** to connect.

## Core Features & Economy

* **Lifesteal Mechanics**:
  * [LifeSteal](plugins/LifeSteal/config.yml): 20-heart (40 HP) cap, custom heart items (`/withdrawheart`), and a strict 1-heart floor limit (players never lose or drop hearts below 1 heart).
* **DonutSMP Buy-Order Marketplace**:
  * [DonutOrders](plugins/DonutOrders/config.yml): Player-driven buy and sell order exchange (`/orders` and `/order`) with automated escrow payouts.
* **Farming Economy**:
  * [EconomyShopGUI](plugins/EconomyShopGUI/shops/Farming.yml): Custom DonutSMP crop prices for Sugar Cane, Cactus, Bamboo, Wheat, and Mob items to incentivize massive automated farming.
  * [Fadah](plugins/Fadah/config.yml): Auction house (`/ah`) for player item auctions.
* **Cross-Version Client Compatibility**:
  * [ViaVersion](plugins/ViaVersion.jar): Upward protocol compatibility for 1.21.x through 1.21.11+ and modern releases.
  * [ViaBackwards](plugins/ViaBackwards.jar): Downward compatibility for 1.9 through 1.20.x clients.
  * [ViaRewind](plugins/ViaRewind.jar): Legacy compatibility for 1.8.x and 1.7.x PvP clients.
* **Combat & Anticheat**:
  * [CombatLogX](plugins/CombatLogX/config.yml): 15-second combat tagging, immediate disconnect death punishment, and blocked escape commands (`/tpa`, `/home`, `/spawn`, `/shop`, `/ah`, `/orders`, `/kit`, `/ec`).
  * [GrimAC](plugins/GrimAC/config.yml): Async predictive movement and combat anticheat.
* **Rank & Server Visuals**:
  * [TAB](plugins/TAB/config.yml): Branded Aurora SMP gradient headers, footers, nametags, and DonutSMP sidebar scoreboard showing Hearts (`%player_health%❤`), Balance, and Combat Status.
  * [LuckPerms](plugins/LuckPerms/): Fast permissions and group management.
  * [Vault](plugins/Vault/): Unified economy bridge.
  * [PlayerKits 2](plugins/PlayerKits2/): Tiered PvP and starter kit system (`/kit`).
  * [WorldGuard](plugins/WorldGuard/) & [FastAsyncWorldEdit (FAWE)](plugins/FastAsyncWorldEdit/): Spawn protection and low-memory asynchronous terrain editing.
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
