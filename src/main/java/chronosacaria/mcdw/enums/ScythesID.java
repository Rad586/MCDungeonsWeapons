package chronosacaria.mcdw.enums;

import chronosacaria.mcdw.Mcdw;
import chronosacaria.mcdw.bases.McdwScythe;
import chronosacaria.mcdw.configs.McdwNewStatsConfig;
import chronosacaria.mcdw.items.ItemsInit;
import net.minecraft.item.ToolMaterial;
import net.minecraft.item.ToolMaterials;

import java.util.EnumMap;
import java.util.HashMap;

import static chronosacaria.mcdw.Mcdw.CONFIG;

public enum ScythesID implements IMcdwWeaponID, IMeleeWeaponID {
    SICKLE_FROST_SCYTHE(ToolMaterials.DIAMOND,4, -2.5f),
    SICKLE_JAILORS_SCYTHE(ToolMaterials.IRON,4, -2.25f),
    SICKLE_SKULL_SCYTHE(ToolMaterials.DIAMOND,4, -2.5f),
    SICKLE_SOUL_SCYTHE(ToolMaterials.DIAMOND,3, -2.25f);

    private final ToolMaterial material;
    private final int damage;
    private final float attackSpeed;

    ScythesID(ToolMaterial material, int damage, float attackSpeed) {
        this.material = material;
        this.damage = damage;
        this.attackSpeed = attackSpeed;
    }

    public static HashMap<ScythesID, Boolean> getEnabledItems(){
        return Mcdw.CONFIG.mcdwEnableItemsConfig.scythesEnabled;
    }

    public static EnumMap<ScythesID, McdwScythe> getItemsEnum() {
        return ItemsInit.scytheItems;
    }

    public static HashMap<ScythesID, Integer> getSpawnRates() {
        return Mcdw.CONFIG.mcdwNewlootConfig.scytheSpawnRates;
    }

    public static HashMap<ScythesID, MeleeStats> getWeaponStats() {
        return CONFIG.mcdwNewStatsConfig.scytheStats;
    }

    @Override
    public Boolean isEnabled(){
        return getEnabledItems().get(this);
    }

    @Override
    public McdwScythe getItem() {
        return getItemsEnum().get(this);
    }

    @Override
    public Integer getItemSpawnRate() {
        return getSpawnRates().get(this);
    }

    @Override
    public HashMap<ScythesID, MeleeStats> getWeaponStats(McdwNewStatsConfig mcdwNewStatsConfig) {
        return mcdwNewStatsConfig.scytheStats;
    }

    @Override
    public MeleeStats getWeaponItemStats() {
        return getWeaponStats().get(this);
    }

    @Override
    public MeleeStats getWeaponItemStats(McdwNewStatsConfig mcdwNewStatsConfig) {
        return mcdwNewStatsConfig.scytheStats.get(this);
    }

    @Override
    public ToolMaterial getMaterial(){
        return material;
    }

    @Override
    public int getDamage(){
        return damage;
    }

    @Override
    public float getAttackSpeed(){
        return attackSpeed;
    }

    @Override
    public McdwScythe makeWeapon() {
        McdwScythe mcdwScythe = new McdwScythe(ItemsInit.stringToMaterial(this.getWeaponItemStats().material),
                this.getWeaponItemStats().damage, this.getWeaponItemStats().attackSpeed);

        getItemsEnum().put(this, mcdwScythe);
        return mcdwScythe;
    }
}