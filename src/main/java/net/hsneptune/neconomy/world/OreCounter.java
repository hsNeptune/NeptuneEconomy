package net.hsneptune.neconomy.world;
import net.hsneptune.neconomy.NeptuneEconomy;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.world.PersistentState;

public class OreCounter extends PersistentState {

    private static int goldCount;
    private static int diamondCount;
    private static int ironCount;
    private static int emeraldCount;
    public static int debrisCount;

    public OreCounter() {
        this.goldCount = 0;
        this.diamondCount = 0;
        this.ironCount = 0;
        this.emeraldCount = 0;
        this.debrisCount = 0;
    }


    public void setDebrisCount(int debrisCount) {
        OreCounter.debrisCount = debrisCount;
        markDirty();
    }

    public void setEmeraldCount(int emeraldCount) {
        OreCounter.emeraldCount = emeraldCount;
        markDirty();
    }

    public void setDiamondCount(int diamondCount) {
        OreCounter.diamondCount = diamondCount;
        markDirty();
    }

    public void setGoldCount(int goldCount) {
        OreCounter.goldCount = goldCount;
        markDirty();
    }

    public void setIronCount(int ironCount) {
        OreCounter.ironCount = ironCount;
        markDirty();
    }

    public static int getDebrisCount() {
        return debrisCount;
    }
    public static int getGoldCount() {
        return goldCount;
    }
    public static int getEmeraldCount() {
        return emeraldCount;
    }
    public static int getDiamondCount() {
        return diamondCount;
    }
    public static int getIronCount() {
        return ironCount;
    }
    public static void getCounts(){
        NeptuneEconomy.LOGGER.info("GOLD: {}\nIRON: {}\nDIAMOND: {}\nEMERALD: {}\nANCIENT DEBRIS: {}", goldCount, ironCount, diamondCount, emeraldCount, debrisCount);
    }

    public void addGold(int delta) {
        goldCount += delta;
        markDirty();
    }
    public void addIron(int delta) {
        ironCount += delta;
        markDirty();
    }
    public void addDiaz(int delta) {
        diamondCount += delta;
        markDirty();
    }
    public void addEmerald(int delta) {
        emeraldCount += delta;
        markDirty();
    }
    public void addDebris(int delta) {
        debrisCount += delta;
        markDirty();
    }

    @Override
    public NbtCompound writeNbt(NbtCompound nbt, RegistryWrapper.WrapperLookup registryLookup) {
        nbt.putInt("goldCount", this.goldCount);
        nbt.putInt("diamondCount", this.diamondCount);
        nbt.putInt("ironCount", this.ironCount);
        nbt.putInt("emeraldCount", this.emeraldCount);
        nbt.putInt("debrisCount", this.debrisCount);
        return nbt;
    }

    /**
     * Factory method to create a new instance and load data from NbtCompound.
     * This is called when existing data is found in the world save.
     */
    public static OreCounter createFromNbt(NbtCompound nbt, RegistryWrapper.WrapperLookup registryLookup) {
        OreCounter data = new OreCounter();
        data.goldCount = nbt.getInt("goldCount");
        data.diamondCount = nbt.getInt("diamondCount");
        data.ironCount = nbt.getInt("ironCount");
        data.emeraldCount = nbt.getInt("emeraldCount");
        data.debrisCount = nbt.getInt("debrisCount");
        return data;
    }

    /**
     * Factory method to create a brand new instance when no data exists.
     * This is called when a new world is created or the data file is not found.
     */
    public static OreCounter createNew() {
        return new OreCounter(); // Returns a new OreCounter with default values (all zeros)
    }

    // This defines the Type for your PersistentState, used by PersistentStateManager
    public static final Type<OreCounter> TYPE = new Type<>(
            OreCounter::createNew,      // If no data exists, create a new instance with default values
            OreCounter::createFromNbt,  // If data exists, load from NBT
            null                        // Optional: DataFixTypes, usually null for custom mod data
    );
    public static void init(){
        NeptuneEconomy.LOGGER.info("ORE COUNTERS INITIALIZED");
    }

}
