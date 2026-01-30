package com.insanecraft;

import com.insanecraft.core.altar.CelestialAltar;
import com.insanecraft.core.altar.StarlightPool;
import com.insanecraft.core.enchant.EnchantDefinition;
import com.insanecraft.core.enchant.EnchantedItem;
import com.insanecraft.core.enchant.EnhancementRecipe;
import com.insanecraft.core.enchant.OverenchantmentTable;
import com.insanecraft.core.enchant.XPEnhancer;
import com.insanecraft.core.forge.RubyForge;
import com.insanecraft.core.harvester.VoidHarvester;
import com.insanecraft.core.harvester.VoidHarvesterState;
import com.insanecraft.core.harvester.VoidHarvesterTier;
import com.insanecraft.core.item.ItemStack;
import com.insanecraft.core.material.GearAttributeService;
import com.insanecraft.core.material.GearAttributes;
import com.insanecraft.core.material.MaterialDefinition;
import com.insanecraft.core.material.MaterialRegistry;
import com.insanecraft.core.material.Rarity;
import com.insanecraft.core.material.RubyTier;
import com.insanecraft.core.ore.OreUpgrader;
import com.insanecraft.core.ore.OreUpgradeRecipe;
import com.insanecraft.core.player.PlayerInventory;
import com.insanecraft.core.recipe.CraftingRecipe;
import com.insanecraft.core.recipe.RecipeRegistry;
import com.insanecraft.core.ritual.MoonstoneRitual;
import com.insanecraft.core.service.CraftingService;
import com.insanecraft.core.service.OreUpgradeService;
import com.insanecraft.core.totem.StormTotem;
import com.insanecraft.core.totem.StormTotemCharge;
import com.insanecraft.core.xp.XPGenerator;
import com.insanecraft.core.xp.XPGeneratorState;

import java.util.List;
import java.util.Optional;

/**
 * Entry point placeholder for the InsaneCraft mod.
 * <p>
 * This is intentionally framework-agnostic so the codebase can be adapted to the
 * chosen mod loader (Forge, Fabric, or MCreator Java exports).
 */
public final class InsaneCraftMod {
    private final OreUpgrader oreUpgrader = new OreUpgrader();
    private final OverenchantmentTable overenchantmentTable = new OverenchantmentTable();
    private final XPGenerator xpGenerator = new XPGenerator();
    private final RecipeRegistry recipeRegistry = new RecipeRegistry();
    private final MaterialRegistry materialRegistry = new MaterialRegistry();
    private final CraftingService craftingService = new CraftingService(recipeRegistry);
    private final OreUpgradeService oreUpgradeService = new OreUpgradeService(oreUpgrader);
    private final GearAttributeService gearAttributeService = new GearAttributeService();
    private final RubyForge rubyForge = new RubyForge(gearAttributeService);
    private final MoonstoneRitual moonstoneRitual = new MoonstoneRitual();
    private final CelestialAltar celestialAltar = new CelestialAltar();
    private final StormTotem stormTotem = new StormTotem();
    private final VoidHarvester voidHarvester = new VoidHarvester();
    private final XPEnhancer xpEnhancer = new XPEnhancer(overenchantmentTable, List.of(
        new EnhancementRecipe(
            "fortune",
            30,
            List.of(new ItemStack("ruby_gem", 2), new ItemStack("xp_crystal", 1))
        ),
        new EnhancementRecipe(
            "vein_miner",
            50,
            List.of(new ItemStack("moonstone", 1), new ItemStack("nether_star", 1))
        )
    ));

    public void initialize() {
        oreUpgrader.register();
        overenchantmentTable.register();
        xpGenerator.register();
        seedMaterials();
        seedCraftingRecipes();
    }

    public Optional<OreUpgradeRecipe> tryUpgrade(List<ItemStack> inputs) {
        return oreUpgrader.findRecipe(inputs);
    }

    public List<EnchantDefinition> listOverenchantments() {
        return overenchantmentTable.getSupportedEnchants();
    }

    public boolean canApplyOverenchant(String id, int level, boolean allowExclusive) {
        return overenchantmentTable.canApply(id, level, allowExclusive);
    }

    public boolean canApplyXpEnhancement(String enchantId, int level, int xpLevels, List<ItemStack> materials) {
        return xpEnhancer.canApply(enchantId, level, xpLevels, materials);
    }

    public XPGeneratorState createGeneratorState() {
        return new XPGeneratorState(1, 0);
    }

    public XPGenerator getXpGenerator() {
        return xpGenerator;
    }

    public RecipeRegistry getRecipeRegistry() {
        return recipeRegistry;
    }

    public MaterialRegistry getMaterialRegistry() {
        return materialRegistry;
    }

    public CraftingService getCraftingService() {
        return craftingService;
    }

    public OreUpgradeService getOreUpgradeService() {
        return oreUpgradeService;
    }

    public GearAttributes buildRubyGear(RubyTier tier, double baseDamage, double baseSpeed) {
        return gearAttributeService.buildRubyAttributes(tier, baseDamage, baseSpeed);
    }

    public RubyForge getRubyForge() {
        return rubyForge;
    }

    public MoonstoneRitual getMoonstoneRitual() {
        return moonstoneRitual;
    }

    public CelestialAltar getCelestialAltar() {
        return celestialAltar;
    }

    public StormTotem getStormTotem() {
        return stormTotem;
    }

    public VoidHarvester getVoidHarvester() {
        return voidHarvester;
    }

    private void seedMaterials() {
        materialRegistry.register(new MaterialDefinition(
            "ruby",
            Rarity.RARE,
            "Rarer than diamonds with speed bonus variants."
        ));
        materialRegistry.register(new MaterialDefinition(
            "moonstone",
            Rarity.EPIC,
            "Mystic ore found in moonlit biomes."
        ));
        materialRegistry.register(new MaterialDefinition(
            "storm_shard",
            Rarity.RARE,
            "Charged fragments used to awaken storm totems."
        ));
        materialRegistry.register(new MaterialDefinition(
            "storm_core",
            Rarity.EPIC,
            "Condensed storm energy for higher tier weather rituals."
        ));
        materialRegistry.register(new MaterialDefinition(
            "void_fragment",
            Rarity.LEGENDARY,
            "Residue harvested from void machinery."
        ));
    }

    private void seedCraftingRecipes() {
        recipeRegistry.register(new CraftingRecipe(
            "overenchantment_table",
            List.of(
                new ItemStack("crying_obsidian", 4),
                new ItemStack("diamond_block", 2),
                new ItemStack("nether_star", 1)
            ),
            new ItemStack("overenchantment_table", 1),
            20
        ));
        recipeRegistry.register(new CraftingRecipe(
            "xp_generator",
            List.of(
                new ItemStack("iron_block", 4),
                new ItemStack("redstone_block", 2),
                new ItemStack("xp_bottle", 8)
            ),
            new ItemStack("xp_generator", 1),
            10
        ));
        recipeRegistry.register(new CraftingRecipe(
            "ruby_pickaxe",
            List.of(
                new ItemStack("ruby_gem", 3),
                new ItemStack("stick", 2)
            ),
            new ItemStack("ruby_pickaxe", 1),
            0
        ));
        recipeRegistry.register(new CraftingRecipe(
            "ruby_forge",
            List.of(
                new ItemStack("lava_bucket", 1),
                new ItemStack("ruby_block", 2),
                new ItemStack("nether_star", 1)
            ),
            new ItemStack("ruby_forge", 1),
            15
        ));
        recipeRegistry.register(new CraftingRecipe(
            "moonstone_ritual",
            List.of(
                new ItemStack("moonstone", 2),
                new ItemStack("amethyst_block", 1),
                new ItemStack("gold_block", 1)
            ),
            new ItemStack("moonstone_ritual", 1),
            12
        ));
        recipeRegistry.register(new CraftingRecipe(
            "celestial_altar",
            List.of(
                new ItemStack("amethyst_block", 2),
                new ItemStack("moonstone", 2),
                new ItemStack("diamond_block", 1)
            ),
            new ItemStack("celestial_altar", 1),
            18
        ));
        recipeRegistry.register(new CraftingRecipe(
            "storm_totem",
            List.of(
                new ItemStack("storm_shard", 3),
                new ItemStack("lightning_rod", 1),
                new ItemStack("lapis_block", 1)
            ),
            new ItemStack("storm_totem", 1),
            8
        ));
        recipeRegistry.register(new CraftingRecipe(
            "void_harvester",
            List.of(
                new ItemStack("obsidian", 4),
                new ItemStack("crying_obsidian", 2),
                new ItemStack("nether_star", 1)
            ),
            new ItemStack("void_harvester", 1),
            20
        ));
    }

    public static void main(String[] args) {
        InsaneCraftMod mod = new InsaneCraftMod();
        mod.initialize();

        PlayerInventory inventory = new PlayerInventory();
        inventory.add(new ItemStack("crying_obsidian", 4));
        inventory.add(new ItemStack("diamond_block", 10));
        inventory.add(new ItemStack("nether_star", 2));
        inventory.add(new ItemStack("ruby_gem", 4));
        inventory.add(new ItemStack("xp_crystal", 1));
        inventory.add(new ItemStack("lava_bucket", 1));
        inventory.add(new ItemStack("ruby_block", 2));
        inventory.add(new ItemStack("moonstone", 2));
        inventory.add(new ItemStack("amethyst_block", 1));
        inventory.add(new ItemStack("gold_block", 1));
        inventory.add(new ItemStack("echo_shard", 2));
        inventory.add(new ItemStack("storm_shard", 2));
        inventory.add(new ItemStack("storm_core", 2));
        inventory.add(new ItemStack("lapis_block", 1));
        inventory.add(new ItemStack("lightning_rod", 1));
        inventory.add(new ItemStack("void_fuel", 40));
        inventory.add(new ItemStack("void_fragment", 24));

        Optional<ItemStack> oreUpgrade = mod.getOreUpgradeService().upgrade(List.of(
            new ItemStack("crying_obsidian", 1),
            new ItemStack("diamond_block", 8)
        ), inventory);
        System.out.println("Upgrade result: " + oreUpgrade.orElse(null));

        Optional<ItemStack> crafted = mod.getCraftingService().craft(List.of(
            new ItemStack("crying_obsidian", 4),
            new ItemStack("diamond_block", 2),
            new ItemStack("nether_star", 1)
        ), inventory, 25);
        System.out.println("Crafting output: " + crafted.orElse(null));

        System.out.println("Overenchantments: " + mod.listOverenchantments());
        System.out.println("Can apply fortune 15: " + mod.canApplyOverenchant("fortune", 15, true));

        Optional<EnchantedItem> enchanted = mod.xpEnhancer.apply(
            "ruby_pickaxe",
            "fortune",
            15,
            35,
            List.of(new ItemStack("ruby_gem", 2), new ItemStack("xp_crystal", 1))
        );
        System.out.println("Enchanted item: " + enchanted.orElse(null));

        XPGeneratorState state = mod.createGeneratorState();
        int requiredXp = mod.getXpGenerator().getRequiredXpForNextLevel(state.getLevel());
        state.tryUpgrade(10, requiredXp, mod.getXpGenerator().getMaxLevel());
        System.out.println("XP Generator level: " + state.getLevel());

        Optional<GearAttributes> forgeResult = mod.getRubyForge().upgrade(
            RubyTier.LEGENDARY,
            6.0,
            1.2,
            List.of(new ItemStack("ruby_gem", 2), new ItemStack("nether_star", 1)),
            inventory
        );
        System.out.println("Ruby forge result: " + forgeResult.orElse(null));

        ItemStack blessing = mod.getMoonstoneRitual().perform(
            List.of(new ItemStack("moonstone", 2), new ItemStack("amethyst_block", 1)),
            inventory
        );
        System.out.println("Moonstone ritual: " + blessing);

        StarlightPool starlightPool = new StarlightPool(60);
        Optional<ItemStack> altarResult = mod.getCelestialAltar().perform(
            List.of(
                new ItemStack("moonstone", 2),
                new ItemStack("nether_star", 1),
                new ItemStack("diamond_block", 2)
            ),
            inventory,
            starlightPool
        );
        System.out.println("Celestial altar: " + altarResult.orElse(null));
        System.out.println("Starlight remaining: " + starlightPool.getStarlight());

        Optional<StormTotemCharge> stormCharge = mod.getStormTotem().charge(
            List.of(new ItemStack("storm_core", 1), new ItemStack("lightning_rod", 1)),
            inventory
        );
        System.out.println("Storm totem charge: " + stormCharge.orElse(null));

        VoidHarvesterState harvesterState = new VoidHarvesterState(VoidHarvesterTier.BASIC, 0);
        boolean fueled = mod.getVoidHarvester().addFuel(new ItemStack("void_fuel", 20), inventory, harvesterState);
        System.out.println("Void harvester fueled: " + fueled);
        Optional<ItemStack> harvested = mod.getVoidHarvester().harvest(harvesterState);
        System.out.println("Void harvester output: " + harvested.orElse(null));
        Optional<VoidHarvesterTier> upgraded = mod.getVoidHarvester().upgrade(
            harvesterState,
            List.of(new ItemStack("void_fragment", 8), new ItemStack("ruby_gem", 2)),
            inventory
        );
        System.out.println("Void harvester upgrade: " + upgraded.orElse(null));

        System.out.println("Inventory: " + inventory);
        System.out.println("Materials: " + mod.getMaterialRegistry().getMaterials());
    }
}
