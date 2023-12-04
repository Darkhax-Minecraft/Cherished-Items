package net.darkhax.cherisheditems;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

public class Config {

    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().excludeFieldsWithoutExposeAnnotation().create();

    @Expose
    @SerializedName("renamed_items_despawn_time")
    public TimeFeature renamedItems = new TimeFeature(true, 6000);

    @Expose
    @SerializedName("enchanted_items_despawn_time")
    public TimeFeature enchantedItems = new TimeFeature(true, 6000);

    @Expose
    @SerializedName("default_despawn_times")
    public TimeFeature defaultTime = new TimeFeature(false, 6000);

    @Expose
    @SerializedName("shortened_time_tag")
    public TimeFeature shortenedTag = new TimeFeature(true, 1200);

    @Expose
    @SerializedName("extended_time_tag")
    public TimeFeature extendedTag = new TimeFeature(true, 12000);

    public void validate() {

        this.renamedItems.validate("renamed_items_despawn_time");
        this.enchantedItems.validate("enchanted_items_despawn_time");
        this.defaultTime.validate("default_despawn_times");
        this.shortenedTag.validate("shortened_time_tag");
        this.extendedTag.validate("extended_time_tag");
    }

    public static class TimeFeature {

        @Expose
        @SerializedName("enabled")
        public boolean enabled;

        @Expose
        @SerializedName("despawn_time")
        public int despawnTime;

        public TimeFeature(boolean enabled, int time) {

            this.enabled = enabled;
            this.despawnTime = time;
        }

        protected void validate(String name) {

            if (this.enabled && this.despawnTime < 0) {

                Constants.LOG.warn("Feature {} has negative time of {} which has caused the feature to be disabled. Please disable the feature directly in the future.", name, this.despawnTime);
                this.enabled = false;
            }
        }
    }

    public static Config load(File configFile) {

        Config config = new Config();

        // Attempt to load existing config file
        if (configFile.exists()) {

            try (FileReader reader = new FileReader(configFile)) {

                config = GSON.fromJson(reader, Config.class);
                Constants.LOG.info("Loaded config file.");
            }

            catch (Exception e) {

                Constants.LOG.error("Could not read config file {}. Defaults will be used.", configFile.getAbsolutePath(), e);
            }
        }

        else {

            Constants.LOG.info("Creating a new config file at {}.", configFile.getAbsolutePath());
            configFile.getParentFile().mkdirs();
        }

        try (FileWriter writer = new FileWriter(configFile)) {

            GSON.toJson(config, writer);
            Constants.LOG.info("Saved config file.");
        }

        catch (Exception e) {

            Constants.LOG.error("Could not write config file '{}'!", configFile.getAbsolutePath(), e);
        }

        return config;
    }
}