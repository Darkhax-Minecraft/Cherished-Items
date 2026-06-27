package net.darkhax.cherisheditems.common;

import net.darkhax.pricklemc.common.api.annotations.RangedInt;
import net.darkhax.pricklemc.common.api.annotations.Value;

public class Config {

    private static final int EXTENDED_TIME = 10 * 60 * 20;

    @Value(comment = "Customize despawn time for renamed items.", writeDefault = false)
    public TimeFeature renamed_items = new TimeFeature(true, EXTENDED_TIME);

    @Value(comment = "Customize despawn time for enchanted items.", writeDefault = false)
    public TimeFeature enchanted_items = new TimeFeature(true, EXTENDED_TIME);

    @Value(comment = "Customize the default despawn time for items", writeDefault = false)
    public TimeFeature default_time = new TimeFeature(false, 5 * 60 * 20);

    @Value(comment = "Customize despawn times for items in the cherisheditems:shortened_despawn_time tag.", writeDefault = false)
    public TimeFeature shortened_tag = new TimeFeature(true, 1 * 60 * 20);

    @Value(comment = "Customize despawn times for items in the cherisheditems:extended_despawn_time tag.", writeDefault = false)
    public TimeFeature extended_tag = new TimeFeature(true, EXTENDED_TIME);

    public static class TimeFeature {

        @Value(comment = "Determines if the changes should be applied.")
        public boolean enabled;

        @Value(comment = "The amount of ticks before the item despawns.")
        @RangedInt(min = 1)
        public int despawn_time;

        public TimeFeature(boolean enabled, int time) {
            this.enabled = enabled;
            this.despawn_time = time;
        }
    }
}